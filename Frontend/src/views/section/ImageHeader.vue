<template>
<div id="background" class="container-fluid" :style="{'background-image': `url(${require('../../assets/img/cafebg.jpg')})`}">

</div>
</template>

<script>
export default {
    methods: {
        callBackgrounds() {
            return 
        }
    }
}
</script>

<style>
    #background {
        height: 65vh;
        position: absolute !important;
        top: 0;
        left: 0;
        z-index: -1;
        background-size: cover !important;
        filter: black();
    }
</style>