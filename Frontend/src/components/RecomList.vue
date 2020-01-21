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
            imgUrl: "https://images.squarespace-cdn.com/content/v1/5b75b481b98a78811d9902c7/1541785543421-QMEFE4R2GI5PHN6SLC1B/ke17ZwdGBToddI8pDm48kFGZJ61ZoMWaIdmMaPzVMxF7gQa3H78H3Y0txjaiv_0fDoOvxcdMmMKkDsyUqMSsMWxHk725yiiHCCLfrh8O1z5QHyNOqBUUEtDDsRWrJLTmo3_5ncHsS_KC884-Z46vJbs9LIIAHSWNIcWxyGW0QvPV35g8WSGumhqg_-ei30TL/_IMG9178-Modifica-Modifica-Modifica.jpg?format=1500w",
        }
    }
}
</script>

<style>

</style>