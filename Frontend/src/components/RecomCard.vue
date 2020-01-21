<template>
    <div class="col-12 col-sm-6 col-lg-3">
        <div class="card my-2">
            <img :src="imgUrl" class="card-img-top" :alt="alt">
            <div class="card-body">
                <h5 class="card-title">{{ i }}번 째 카페 맛집</h5>
                <p class="card-text">분위기가 돋보이는 카페 맛집 어쩌구 저쩌구</p>
            </div>
            <div class="card-footer">
                <small class="text-muted">Last updated 3 mins ago</small>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'RecomCard',
    props: {
        imgUrl: {
            imgUrl: String,
            required: true
            },
        alt: String,
        i: Number,
    },
    data() {
        return {}
    }
}
</script>

<style>
.circular-img {
    border-radius: 50% !important;
}
</style>