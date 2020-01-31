<template>
<form class="search-form input-group mb-3" @submit.prevent="searchNow">
    <input v-model="query" id="title" type="text" class="form-control" placeholder=" 지역명, 카페 이름 등🔍" aria-describedby="button-addon2" autofocus autocomplete="">
    <div class="input-group-append">
        <button  class="btn btn-info" type="button" id="button-addon2">검색</button>
    </div>
</form>
</template>

<script>
export default {
    name: 'TodoForm',
    data() {
        return {
            query: ''
        }
    },
    methods: {
        searchNow() {
            this.$emit('search-event', this.query)
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
.btn-info {
    background-color: slateblue;
    color: #fff ;
    width: 5rem;
    font-size: 1.25rem;
    border-radius: 0 20px 20px 0;
    border: 0;
}
.search-form {
    width: 60%;
    min-width: 450px;
}
input {
    font-family: "Font Awesome 5 Free";
}
</style>
