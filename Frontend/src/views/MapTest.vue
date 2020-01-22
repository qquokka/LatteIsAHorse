<template>
<div class="container-fluid">
    <div class="row">

        <div class="d-none d-md-flex col-md-1 menucol-1" style="font-size:1vw">
            <router-link to="/"><i class="fa fa-home fa-4x text-white"></i></router-link> 
            <i class="fab fa-github fa-4x text-white"></i>
            <i class="fab fa-github fa-4x text-white"></i>
            <i class="fab fa-github fa-4x text-white"></i>
            <i class="fab fa-github fa-4x text-white"></i>
            <i class="fab fa-github fa-4x text-white"></i>
            <i class="fab fa-github fa-4x text-white"></i>
        </div>

        <div class="d-none d-md-block col-md-3 bg-white px-0">
            <div class="info-header">
                <h1 class="font-weight-bolder">카페 데일리</h1>
                <small>Cafe' Daily</small>
                <p>대표자: 김태우</p>
                <p class="small">영업시간: 10 A.M. ~ 12 P.M.</p>
            </div>
            <div class="container h-50">
                <div class="row shadow">
                    <div class=" col-4" style="height:150px;background:lightgray;border:1px dotted black" >
                        <p class="mt-5">PICTURE</p> 
                    </div>
                    <div class=" col-4" style="height:150px;background:lightgray;border:1px dotted black" >
                        <p class="mt-5">PICTURE</p> 
                    </div>
                    <div class=" col-4" style="height:150px;background:lightgray;border:1px dotted black" >
                        <p class="mt-5">PICTURE</p> 
                    </div>
                </div>
                <div class="row">
                    <div class=" col-4" style="height:150px;background:lightgray;border:1px dotted black" >
                        <p class="mt-5">PICTURE</p> 
                    </div>
                    <div class=" col-4" style="height:150px;background:lightgray;border:1px dotted black" >
                        <p class="mt-5">PICTURE</p> 
                    </div>
                    <div class=" col-4" style="height:150px;background:lightgray;border:1px dotted black" >
                        <p class="mt-5">PICTURE</p> 
                    </div>
                </div>
                <a href="">#스터디</a><a href=""> #시나몬라떼</a> <a href="">#2층이상</a><a href=""> #대학가</a><a href=""> #저렴이</a>
                <button class="btn btn-block btn-info mt-2">자세히 보기</button>
            </div>
            <div class="container h-25">
                <hr>
                베스트 리뷰
                <hr>
                <p class="border p-2">아메리카노 한잔 시키고 11시간씩 있어도 눈치 안 주세요! 사장님이 안오고 알바만 있거든요!</p>
            </div>
        </div>

	<kakao-map class="col-12 col-md-8" width="100%" height="100vh" elementId="35989832" :markers="markers" />
    </div>
</div>

</template>

<script>
import KakaoMap from '@/components/KakaoMap.vue'

export default {
    name: 'Kakao-map',
    components: {
			KakaoMap
    },
    data() {
        return {
            markers: [
                
                ]
        }
        
    },
    computed: {

    },
    methods: {
    },
    mounted() {
    }
}
</script>

<style>
.menucol-1 {
    display:flex;
    flex-direction:column;
    justify-content: space-between;
    background-color: #42b983;
}

.info-header {
    padding: 1rem;
}

</style>