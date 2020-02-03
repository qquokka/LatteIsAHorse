<template>
    <div class="row mx-auto my-4">
        <div v-for="cafe in cafeData" :key="cafe.id" class="col-12 col-sm-6 col-lg-4 mx-0">
            <div class="newmorph my-2 mx-0" :style="{ backgroundImage: `url('${cafe.thumbnail}')` }">
                <div class="card-body">
                    <h4 class="cafe-name mx-auto">{{ cafe.cafe_name }}
					<p class= "text-truncate" style="font-size: 0.6rem">{{ cafe.cafe_address }}</p></h4>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios"

export default {
    name: 'RecomList',
    components: {

    },
    data() {
        return {
                imgUrl: `https://picsum.photos/seed/${this.getRandomInt(1, 100)}/200/300?`,
                cafeData: [],
        }
    },
    methods: {
    getRandomInt(min, max) {
        min = Math.ceil(min)
        max = Math.floor(max)
        return Math.floor(Math.random() * (max - min)) + min
    }
    },
  mounted() {
    axios
      .get(`${this.$store.state.constants.SERVER}/cafe`)
      .then(response => {
        this.cafeData = response.data.slice(0,6)
			})
			.catch(err => {
				console.log(err.response)
			})
  },
}
</script>

<style>
.cafe-name {
    background: rgba(255,255,255,0.7);
    transition: 255ms ease-in-out;
    padding: 1rem;
	margin-top: 40% !important;
    width:fit-content;
    border-radius: 50px;
}
.newmorph {
	overflow: hidden;
	height: 0;
	width: 100%;
	padding-bottom: 100%;
	background-size: cover !important;
	border-radius: 50px;
	background: linear-gradient(145deg, #e6e6e6, #ffffff);
	box-shadow:  26px 26px 51px #d9d9d9, 
							-26px -26px 51px #ffffff;
    transition: 125ms ease-in-out
}

.newmorph:hover {
	cursor: pointer;
}
.newmorph:hover .cafe-name {
	margin-top: 0% !important;
    width: 100%;
    border-radius: 0;
}
</style>