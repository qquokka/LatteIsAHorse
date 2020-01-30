<template>
    <div class="row mx-auto my-4">
        <recom-card v-for="i in 4" :imgUrl="imgUrl" :i="i" :key="i"/>
    </div>
</template>

<script>
import RecomCard from './RecomCard.vue'

export default {
    name: 'RecomList',
    components: {
        RecomCard,
    },
    data() {
        return {
            imgUrl: `https://picsum.photos/seed/${this.getRandomInt(1, 100)}/200/300?`,
        }
    },
    methods: {
    getRandomInt(min, max) {
        min = Math.ceil(min)
        max = Math.floor(max)
        return Math.floor(Math.random() * (max - min)) + min
    }
    }
}
</script>

<style>

</style>