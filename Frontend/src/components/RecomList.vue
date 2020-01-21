<template>
    <div class="row my-4">
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
            imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFDv-k4e9ktvohNni1tj2CJSNUZZJ0nU7DKfR_GVRVqZS7aexZ&s",
        }
    }
}
</script>

<style>

</style>