<template>
  <div>
    <nav-bar bgcolor="#FFD6BA" />
    <div class="my-5">
      <img
        width="100%"
        style="margin-bottom: 2rem"
        :src="thumbnail || 'src/assets/noimage.png'"
      />
      <image-uploader
        :preview="false"
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
          <span class="upload-caption">
            {{ thumbnail ? "변경" : "업로드" }}
          </span>
        </label>
      </image-uploader>
    </div>
    <div class="container">
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span
            class="input-group-text input-width justify-content-center"
            id="inputGroup-sizing-default"
            >주소</span
          >
        </div>
        <input type="text" v-model="cafe_address" class="form-control" />
      </div>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span
            class="input-group-text input-width justify-content-center"
            id="inputGroup-sizing-default"
            >카페명</span
          >
        </div>
        <input type="text" v-model="cafe_name" class="form-control" />
      </div>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span
            class="input-group-text input-width justify-content-center"
            id="inputGroup-sizing-default"
            >전화번호</span
          >
        </div>
        <input type="text" v-model="cafe_phone" class="form-control" />
      </div>
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text input-width justify-content-center"
            >설명</span
          >
        </div>
        <textarea class="form-control" v-model="description"></textarea>
      </div>

      <!-- Timetable -->
      <ul class="nav nav-tabs row m-0">
        <li class="nav-item col-4 mx-0 px-0">
          <p class="nav-link active" @click="setTimeDetail(0)">요일별 설정</p>
          <!-- setTimeDetail -->
        </li>
        <li class="nav-item col-4 mx-0 px-0">
          <p class="nav-link active" @click="setTimeDetail(1)">평일/주말</p>
        </li>
        <li class="nav-item col-4 mx-0 px-0">
          <p class="nav-link active" @click="setTimeDetail(2)">일괄 설정</p>
        </li>
      </ul>

      <div id="editCafeTime">
        <div :style="timeIsDetail === 0 ? 'display: block' : 'display: none'">
          <div v-for="i in 6" :key="i" class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">{{ "월화수목금토"[i - 1] }}</span>
            </div>
            <input
              type="time"
              class="opens form-control"
              v-model="time[i][0]"
            />
            <input
              type="time"
              class="closes form-control"
              v-model="time[i][1]"
            />
          </div>
          <div class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">일</span>
            </div>
            <input
              type="time"
              class="opens form-control"
              v-model="time[0][0]"
            />
            <input
              type="time"
              class="closes form-control"
              v-model="time[0][1]"
            />
          </div>
        </div>

        <div :style="timeIsDetail === 1 ? 'display: block' : 'display: none'">
          <div class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">평일</span>
            </div>
            <input
              type="time"
              class="opens form-control"
              v-model="time[1][0]"
            />
            <input
              type="time"
              class="closes form-control"
              v-model="time[1][1]"
            />
          </div>
          <div class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">주말</span>
            </div>
            <input
              type="time"
              class="opens form-control"
              v-model="time[6][0]"
            />
            <input
              type="time"
              class="closes form-control"
              v-model="time[6][1]"
            />
          </div>
        </div>

        <div
          :style="timeIsDetail === 2 ? 'display: flex' : 'display: none'"
          class="input-group"
        >
          <div class="input-group-prepend">
            <span class="input-group-text">매일</span>
          </div>
          <input type="time" class="opens form-control" v-model="time[1][0]" />
          <input type="time" class="closes form-control" v-model="time[1][1]" />
        </div>
      </div>
      <button
        @click="save"
        type="button"
        class="mt-3 btn btn-warning btn-lg btn-block"
      >
        저장
      </button>
    </div>
  </div>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import axios from "axios";

export default {
  name: "editMyCafe",
  components: { NavBar },
  data() {
    return {
      hasImage: "",
      cafe_address: "",
			cafe_id: 0,
			cafe_owner_id: 0,
      cafe_name: "",
      cafe_phone: "",
      description: "",
      thumbnail: "",
      thumbnailName: "",
      time: [
        [0, 0],
        [0, 0],
        [0, 0],
        [0, 0],
        [0, 0],
        [0, 0],
        [0, 0]
      ],
      timeOrigin: [],
      timeIsDetail: 0
    };
  },
  methods: {
    getData() {
      axios
        .get(`${this.$store.state.constants.SERVER}/mycafe`, {
          headers: { Authorization: "Bearer " + this.$session.get("jwt") }
        })
        .then(response1 => {
          console.log("my cafe 불러오기", response1);
          axios
            .get(
              `${this.$store.state.constants.SERVER}/cafe/detail/${response1.data.cafe.cafe_id}`
            )
            .then(response => {
              console.log("카페 정보 불러오기", response);
              const info = response.data.cafeinfo;
              this.timeOrigin = info.time;
              for (let key in info) {
                if (key === "time") {
                  let timetable = [];
                  for (let i = 0; i < 7; i++) {
                    timetable.push([
                      info.time[i][0].slice(11, 16),
                      info.time[i][1].slice(11, 16)
                    ]);
                    if (i === 6) {
                      this.$set(this, "time", timetable);
                    }
                  }
                } else {
                  this.$set(this, key, info[key]);
                }
              }
            });
        })
        .catch(error => {
          console.log(error);
        });
    },
    setImage: function(output) {
      this.thumbnail = output.dataUrl;
      this.thumbnailName = output.info.name;
      this.hasImage = true;
    },
    setTimeDetail(level) {
      this.timeIsDetail = level;
    },
    save() {
      // console.log(image);
      console.log("Info", this.thumbnail);
      // console.log("Exif", image.exif);
      fetch(this.thumbnail)
        .then(r => r.blob())
        .then(blobbed => {
          const file = new File([blobbed], this.thumbnailName);
          console.log("file", file);
          const fd = new FormData();
          fd.append("file", file);
          console.log("formData", fd);
          axios
            .post(`${this.$store.state.constants.SERVER}/uploadThumbnail`, fd)
            .then(response => {
              console.log(response);
              this.thumbnail = response.data.fileDownloadUri;
              for (let i = 0; i < 7; i++) {
                for (let j = 0; j < 2; j++) {
                  this.timeOrigin[i][j] =
                    this.timeOrigin[i][j].slice(0, 11) +
                    this.time[i][j] +
                    this.timeOrigin[i][j].slice(16, 20);
                  if (i === 6 && j === 1) {
                    const cafeee = {
                      cafe_address: this.cafe_address,
											cafe_id: this.cafe_id,
											cafe_owner_id: this.cafe_owner_id,
                      cafe_name: this.cafe_name,
                      cafe_phone: this.cafe_phone,
                      closed: false,
                      thumbnail: this.thumbnail,
											mon_open: this.timeOrigin[1][0],
											mon_close: this.timeOrigin[1][1],
											tue_open: this.timeOrigin[2][0],
											tue_close: this.timeOrigin[2][1],
											wed_open: this.timeOrigin[3][0],
											wed_close: this.timeOrigin[3][1],
											thu_open: this.timeOrigin[4][0],
											thu_close: this.timeOrigin[4][1],
											fri_open: this.timeOrigin[5][0],
											fri_close: this.timeOrigin[5][1],
											sat_open: this.timeOrigin[6][0],
											sat_close: this.timeOrigin[6][1],
											sun_open: this.timeOrigin[0][0],
											sun_close: this.timeOrigin[0][1]
                    };
                    console.log("cafe 정보 업데이트", cafeee);

                    axios
                      .patch(
                        `${this.$store.state.constants.SERVER}/cafe`,
                        cafeee,
                        {
                          headers: {
                            Authorization: "Bearer " + this.$session.get("jwt")
                          }
                        }
                      )
                      .then(response2 => {
                        console.log(response2);
                      })
                      .catch(error2 => {
                        console.log(error2);
                      });
                  }
                }
              }
            })
            .catch(error => {
              console.log(error);
            });
        });
    }
  },
  mounted() {
    axios.interceptors.request.use(request => {
      console.log("Starting Request", request);
      return request;
    });
    this.getData();
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
  width: 86.162px !important;
}
#fileInput {
  display: none;
  margin: 2rem 0;
}
</style>
