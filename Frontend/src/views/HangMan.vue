<template>
  <hangman-game :words="words" :show-play-again="false" :lang="EN" @gameFinished="gameFinished" />
</template>
<script>
import "vue-games";
export default {
  data() {
    return {
      words: ["Macbook", "Apple", "Ssafy", "vuejs", "reactjs", "django", "axios", "takheekim"]
    };
  },

  methods: {
    gameFinished: function(word, lose) {
			if (lose){
				alert("실패입니다")
			} else {
				alert("성공입니다")
			}
			this.$router.push('/')
    }
  }
};
</script> 