<template>
    <div class="col-12 col-sm-6 col-lg-3">
        <div class="card my-2 mx-0">
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
        alt: String,
        i: Number,
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