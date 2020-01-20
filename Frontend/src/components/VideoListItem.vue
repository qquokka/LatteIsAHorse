<template>
    <div @click="selectedVideo" class="card mb-3">
        <div class="card-body">
            <h4 class="card-title">{{ video.snippet.title }}</h4>
            <p class="card-text"><small class="text-muted">created: {{ video.snippet.publishedAt }}</small></p>
        </div>
        <img :src="video.snippet.thumbnails.high.url" class="card-img-top" :alt="video.snippet.title">
    </div>
</template>

<script>
    export default {
        name: 'VideoListItem',
        props: {
            video: {
                type: Object,
                required: true,
            }
        },
        methods: {
            selectedVideo() {
                this.$emit('video-select-event', this.video)
            }
        }

    }
</script>

<style scoped>
div {
    cursor:pointer;
}
div:hover {
    background-color: lightgoldenrodyellow;
}
</style>