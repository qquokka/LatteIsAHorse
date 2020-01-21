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
        width: 100% !important;
        position: absolute !important;
        top: 0;
        left: 0;
        z-index: 0;
        filter: brightness(50%);
        background-size: cover !important;
    }
</style>