<template>
	<div class="hello">
		<h1>{{ msg }}</h1>

		<div class="my-8">
			<image-uploader
				:preview="true"
				:className="['fileinput', { 'fileinput--loaded': hasImage }]"
				capture="environment"
				:debug="2"
				doNotResize="gif"
				:autoRotate="true"
				outputFormat="verbose"
				@input="setImage"
			>
				<label for="fileInput" slot="upload-label">
					<figure>
						<svg
							xmlns="http://www.w3.org/2000/svg"
							width="32"
							height="32"
							viewBox="0 0 32 32"
						>
							<path
								class="path1"
								d="M9.5 19c0 3.59 2.91 6.5 6.5 6.5s6.5-2.91 6.5-6.5-2.91-6.5-6.5-6.5-6.5 2.91-6.5 6.5zM30 8h-7c-0.5-2-1-4-3-4h-8c-2 0-2.5 2-3 4h-7c-1.1 0-2 0.9-2 2v18c0 1.1 0.9 2 2 2h28c1.1 0 2-0.9 2-2v-18c0-1.1-0.9-2-2-2zM16 27.875c-4.902 0-8.875-3.973-8.875-8.875s3.973-8.875 8.875-8.875c4.902 0 8.875 3.973 8.875 8.875s-3.973 8.875-8.875 8.875zM30 14h-4v-2h4v2z"
							></path>
						</svg>
					</figure>
					<span class="upload-caption">{{
						hasImage ? "변경" : "업로드"
					}}</span>
				</label>
			</image-uploader>
		</div>
		<h2>Essential Links</h2>
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
			msg: "Vue Image Upload and Resize Demo",
			hasImage: false,
			image: null,
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
		setImage: function(file) {
			this.hasImage = true;
			this.image = file;
			console.log('Info', file.info)
			console.log('Exif', file.exif)
			console.log(file.blob().slice(0, 10))
		}
	}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#fileInput {
	display: none;
}
h1,
h2 {
	font-weight: normal;
}
ul {
	list-style-type: none;
	padding: 0;
}
li {
	display: inline-block;
	margin: 0 10px;
}
a {
	color: #42b983;
}
.my-8 {
	margin-top: 4rem;
	margin-bottom: 4rem;
}
</style>
