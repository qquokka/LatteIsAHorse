<template>
<div class="container">
    <VideoListItem @video-select-event="onVideoClicked(video)" v-for="video in videos" :key="video.id.videoId" :video="video"/>
</div>
</template>

<script>
    import VideoListItem from './VideoListItem.vue'
    export default {
        name: 'VideoList',
        components:{
            VideoListItem,
        },
        props: {
            videos: {
                type: Array,
                required: true
            }
        },
        methods: {
            onVideoClicked(video) {
                this.$emit('video-select-event', video)
            }
        }
    }
</script>

<style>

</style>