<template>
<div v-if="video" class='container-fluid'>
    <youtube :video-id="video.id.videoId" player-width="100%" height="80%"/>
    <div class="jumbotron">
    <p class="display-4 text-left">{{ video.snippet.title }}</p>
    <hr>
    <p class="lead">{{ video.snippet.description }}</p>
    </div>
</div>
</template>

<script>
export default {
    name:"VideoDetail",
    props: {
        video: {
            type: Object,
            required: true,
        }
    }

}
</script>

<style scoped>

</style>