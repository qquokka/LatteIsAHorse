
<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
            <search-bar @input-change-event="onInputChange" />
        </div>
      </div>
    </parallax>



    <div class="main main-raised">
      <div class="section text-center">
        <div class="container">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
import SearchBar from '@/components/SearchBar.vue'

export default {
  bodyClass: "index-page",
  components: {
      SearchBar
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/bg_cafe.jpg")
    },
  },
  data() {
    return {
      queries: [],
      suggestedSearch: [],
    };
  },
  methods: {
     onInputChange(value) {
        console.log('==App==')
        console.log(value)
        axios.get(`${serveraddress}`, {
            params: {
              q: value,
            }
          }).then(response => {
            this.suggestedSearch = response.data.items
          })
          .catch(error => {
            console.log(error)
          })
      },
    },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`
      };
    }
  }
};
</script>

<style lang="scss" scoped>
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.contact-form {
  margin-top: 30px;
}

.md-has-textarea + .md-layout {
  margin-top: 15px;
}
</style>
