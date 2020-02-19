<template>
	<div>
		<div class="my-5">
      <image-uploader
        :preview="true"
        :className="['fileinput', { 'fileinput--loaded': hasImage }]"
        capture="environment"
        :debug="1"
        doNotResize="gif"
        :autoRotate="true"
        outputFormat="verbose"
        @input="setImage"
      >
        <label for="fileInput" slot="upload-label">
          <figure>
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32">
              <path
                class="path1"
                d="M9.5 19c0 3.59 2.91 6.5 6.5 6.5s6.5-2.91 6.5-6.5-2.91-6.5-6.5-6.5-6.5 2.91-6.5 6.5zM30 8h-7c-0.5-2-1-4-3-4h-8c-2 0-2.5 2-3 4h-7c-1.1 0-2 0.9-2 2v18c0 1.1 0.9 2 2 2h28c1.1 0 2-0.9 2-2v-18c0-1.1-0.9-2-2-2zM16 27.875c-4.902 0-8.875-3.973-8.875-8.875s3.973-8.875 8.875-8.875c4.902 0 8.875 3.973 8.875 8.875s-3.973 8.875-8.875 8.875zM30 14h-4v-2h4v2z"
              ></path>
            </svg>
          </figure>
          <span class="upload-caption">
            {{
            hasImage ? "변경" : "업로드"
            }}
          </span>
        </label>
      </image-uploader>
    </div>
		<div class="container">
			<form action="">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text input-width justify-content-center" id="inputGroup-sizing-default">주소</span>
					</div>
					<input type="text" name="cafe_address" class="form-control">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text input-width justify-content-center" id="inputGroup-sizing-default">카페명</span>
					</div>
					<input type="text" name="cafe_name" class="form-control">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text input-width justify-content-center" id="inputGroup-sizing-default">전화번호</span>
					</div>
					<input type="text" name="cafe_phone" class="form-control">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text input-width justify-content-center">설명</span>
					</div>
					<textarea class="form-control" name="description"></textarea>
				</div>
				
				<!-- Timetable -->
				<ul class="nav nav-tabs row m-0">
					<li class="nav-item col-4 mx-0 px-0">
						<p class="nav-link active" @click="setTimeDetail(0)">요일별 설정</p>
						<!-- setTimeDetail -->
					</li>
					<li class="nav-item col-4 mx-0 px-0">
						<p class="nav-link active" @click="setTimeDetail(1)">평일/주말 설정</p>
					</li>
					<li class="nav-item col-4 mx-0 px-0">
						<p class="nav-link active" @click="setTimeDetail(2)">일괄 설정</p>
					</li>
				</ul>
				
				<div id="editCafeTime">
					<div :style="timeIsDetail === 0? 'display: block' : 'display: none'">
						<div v-for="i in 6" :key="i" class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">{{ "월화수목금토"[i - 1] }}</span>
							</div>
							<input type="time" class="opens form-control" :id="`${i}Open`" :name="`${i}Open`">
							<input type="time" class="closes form-control" :id="`${i}Close`" :name="`${i}Close`">
						</div>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">일</span>
							</div>
							<input type="time" class="opens form-control" id="0Open" name="0Open">
							<input type="time" class="closes form-control" id="0Close" name="0Close">
						</div>
					</div>

					<div :style="timeIsDetail === 1? 'display: block' : 'display: none'">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">평일</span>
							</div>
							<input type="time" class="opens form-control" id="11Open" name="1Open">
							<input type="time" class="closes form-control" id="11Close" name="1Close">
						</div>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text">주말</span>
							</div>
							<input type="time" class="opens form-control" id="00Open" name="0Open">
							<input type="time" class="closes form-control" id="00Close" name="0Close">
						</div>
					</div>

					<div :style="timeIsDetail === 2? 'display: flex' : 'display: none'" class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text">매일</span>
						</div>
						<input type="time" class="opens form-control" id="open" name="open">
						<input type="time" class="closes form-control" id="close" name="close">
					</div>
				</div>
				<button @click.prevent="getData">가져오기</button>
				<button @click="save" type="button" class="mt-3 btn btn-warning btn-lg btn-block">저장</button>
			</form>
		</div>
	</div>
</template>

<script>
import axios from 'axios';

export default {
	name: 'editMyCafe',
	data() {
		return {
			hasImage: false,
			image: null,
			
			cafe: null,

			cafe_address: "",
			cafe_name: "",
			cafe_phone: "",
			closed: false,
			description: "",
			thumbnail: null,
			time: [[0, 0]],

			timeIsDetail: 0,
		}
	},
	methods: {
		getData() {
			axios.get(`${this.$store.state.constants.SERVER}/mycafe`, {headers: {Authorization: "Bearer " + this.$session.get("jwt")}})
				.then(response => {
					console.log(response)
				})
				.catch(error => {
					console.log(error)
				})
		},
		setImage: function(image) {
      this.hasImage = true;
			this.image = image;
			// document.querySelector('.img-preview').margin = 15px;
      // console.log(image);
      // console.log("Info", image.info);
      // console.log("Exif", image.exif);
			// fetch(image)
			// .then(r=>r.blob())
			// .then(blobbed => {
			// 	const file = new File([blobbed], image.info.name);
			// 	console.log(file)
      //   const fd = new FormData();
      //   fd.append("file", file);
			// 	console.log(fd);
			// 	axios.post(`${this.$store.state.constants.SERVER}/uploadThumbnail`, fd)
			// 		.then(response => {
			// 			console.log(response)

			// 		})
			// 		.catch(error => {
			// 			console.log(error)
			// 		})

			// });
		},
		setTimeDetail(level) {
			const openTime = (document.getElementById('open') || document.getElementById('1Open')).value
			const closeTime = (document.getElementById('close') || document.getElementById('1Close')).value
			this.timeIsDetail = level

			if (this.timeIsDetail) {
				for(let i = 0; i < 7; i++) {
					document.getElementById(`${i}Open`).value = openTime
					document.getElementById(`${i}Close`).value = closeTime
				}
			} else {
				document.getElementById(`open`).value = openTime
				document.getElementById(`close`).value = closeTime
			}
		},
		save() {
		},
	},
	mounted() {
	}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
/* #something >img {
	max-width: 100%;
	padding: 0
} */
.input-width {
	width:86.162px !important
}
#fileInput {
	display: none;
	margin: 2rem 0;
}
.img-preview {
	max-width: 100%;
	margin-bottom: 2rem;
}
</style>
