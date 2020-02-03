<template>
<form class="search-form input-group mb-3" @submit.prevent="searchNow()">
    <input v-model="query" id="title" type="text" class="form-control morph-text" placeholder=" 지역명, 카페 이름 등🔍" aria-describedby="button-addon2" autofocus autocomplete="">
    <div class="input-group-append">
        <button  class="btn" type="submit" id="button-addon2">검색</button>
    </div>
</form>
</template>


<script>
import axios from 'axios'
export default {
    name: 'SearchBar',
    data() {
        return {
            query: ''
        }
    },
    methods: {
       searchNow() {
        axios.get(`${this.$store.state.constants.SERVER}/search/${this.query}`)
          .then(response => {
            //어딘가로 데이터 넘김
            this.$router.push({ name: 'searchresult', 
                                params: { 
                                          cafes : response.data.cafes, 
                                          posts : response.data.posts 
                                        }
                              })
            console.log(response)
          })
          .catch(error => {
            console.log(error.response)
          })
      }
    }
}
</script>

<style scoped>
#title {
    height: 4rem;
    border-radius: 20px 0 0 20px;
    font-size: 1.2rem;
}

.btn {
    border-radius: 50px;
		width: 5rem;
    background: #E6E6FA;
    box-shadow:  26px 26px 51px #c4c4d5, 
                -26px -26px 51px #ffffff;
		transition: 0.2s;
}
.btn:hover {
	border-radius: 50px;
	background: #E6E6FA;
	box-shadow: inset 26px 26px 51px #c4c4d5, 
							inset -26px -26px 51px #ffffff;
	font-weight: 900
}

.search-form {
    width: 60%;
    min-width: 450px;
}

input[type=text] {
	border-radius: 50px;
	border: 0;
	background: #ffffff;
	box-shadow:  6px 16px 31px #d9d9d9, 
							-6px -16px 31px #ffffff;
}
</style>
