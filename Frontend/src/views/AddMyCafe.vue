<template>
  <div>
		썸네일: <input type="file"><br>
		주소: <input type="text" v-model="cafe.cafe_address"><br>
		카페명: <input type="text" v-model="cafe.cafe_name"><br>
		전화번호: <input type="text" v-model="cafe.cafe_phone"><br>
		설명: <input type="text" v-model="cafe.description"><br>
		<button @click="smt">확인</button>
	</div>
</template>

<script>
export default {
	name: 'addMyCafe',
	data() {
		return {
			cafe: {
							cafe_address: "",
							cafe_name: "",
							cafe_phone: "",
							closed: false,
							description: "",
							thumbnail: null,
							time: [
								[
									null
								]
							]
						}
		}
	},
	methods: {
		smt() {
			console.log(this.cafe.thumbnail)
		}
	},
}
</script>

<style>

</style>