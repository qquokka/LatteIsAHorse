<template>
    <div class="card">
        <img :src="imgUrl" class="card-img-top" :alt="imgAlt">
        <div class="card-body">
        <h5 class="card-title">대전 유성 카페 Best 5</h5>
        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
        </div>
        <div class="card-footer">
        <small class="text-muted">Last updated 3 mins ago</small>
        </div>
    </div>
</template>

<script>
export default {
    name: 'EditorPickCard',
    props: {
        imgUrl: {
            imgUrl: String,
            required: true
        },
        imgAlt: String
    },
    data() {
        return {}
    }
}
</script>

<style>

</style>