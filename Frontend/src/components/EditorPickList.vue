<template>
    <div class="card-group my-4">
        <editor-pick-card v-for="i in 3" :imgUrl="imgUrl" :imgAlt="imgAlt" :key="i"/>
    </div>
</template>

<script>
import EditorPickCard from './EditorPickCard.vue'

export default {
    name: 'EditorPickList',
    components: {
        EditorPickCard,
    },
    data () {
        return {
            imgUrl: "http://grandcafetx.com/wp-content/uploads/2019/05/grand-cafe-whitewright-tx.jpg",
            imgAlt: "카페"
        }
    }
}
</script>

<style>

</style>