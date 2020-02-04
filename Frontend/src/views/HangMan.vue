<template>
  <hangman-game :words="words" :show-play-again="false" :lang="ES" @gameFinished="gameFinished" />
</template>
<script>
import "vue-games";
export default {
  data() {
    return {
      words: ["Pera", "Manzana", "Tomate", "Cereza", "Frutilla"]
    };
  },

  methods: {
    gameFinished: function(word, lose) {
      console.log("game finished!!!");
      console.log("user was guessing word:", word);
      console.log("she/he/it lost?", lose);
    }
  }
};
</script> 