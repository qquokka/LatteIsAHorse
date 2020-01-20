<template>
    <div class="card-deck row">
        <div v-for="i in 3" :key="i" class="col-sm">
            <recom-card :imgUrl="imgUrl" :i="i"/>
        </div>
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