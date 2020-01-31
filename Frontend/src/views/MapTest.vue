<template>
<div class="container-fluid p-0">
    <div class="row p-0 m-0" :style="`height: ${avheight}px;background-image: linear-gradient(to top, #cfd9df 0%, #e2ebf0 100%);`">
        <div class="d-none d-md-flex col-md-1 menucol-1 py-5" style="font-size:1vw">
            <div class="menu-icon">
            <router-link to="/"><i class="fa fa-home fa-4x "></i></router-link>
            <p class="menu-tex">돌아가기</p>  
            </div>

            <div class="menu-icon"> 
            <i class="fa fa-search fa-4x "></i>
            <p class="menu-tex">검색</p>  
            </div>

            <div class="menu-icon">
            <i class="fas fa-filter fa-4x "></i>
            <p class="menu-tex">필터적용</p>  
            </div>

            <div class="menu-icon">
            <i class="fa fa-map-marked-alt fa-4x "></i>
            <p class="menu-tex">내 위치</p>  
            </div>
        </div>

        <div class="d-none d-md-block col-md-3 bg-white px-0 py-2">
            <div class="info-header">
                <h1 class="font-weight-bolder">카페 데일리</h1>
                <small>Cafe' Daily</small>
                <p>대표자: 김태우</p>
                <p class="small">영업시간: 10 A.M. ~ 12 P.M.</p>
            </div>
            <div class="container">
                <div class="row align-content-center">
                    <div class="frame col-4"  :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`">
                    </div>
                    <div class="frame col-4"  :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`">

                    </div>
                    <div class="frame col-4"  :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`">

                    </div>
                </div>
                <div class="row">
                    <div class="frame col-4"  :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`">

                    </div>
                    <div class="frame col-4"  :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`">

                    </div>
                    <div class="frame col-4"  :style="`background: url('https://picsum.photos/seed/${Math.floor(Math.random() * 10)}/200')`">

                    </div>
                </div>
                <a href="">#스터디</a><a href=""> #시나몬라떼</a> <a href="">#2층이상</a><a href=""> #대학가</a><a href=""> #저렴이</a>
                <button class="btn btn-block btn-info mt-2">자세히 보기</button>
            </div>
            <div class="container h-25">
                <p class="py-2 shadow">베스트 리뷰</p>
                
                <p class="border shadow p-2">아메리카노 한잔 시키고 11시간씩 있어도 눈치 안 주세요! 사장님이 안오고 알바만 있거든요!</p>
            </div>
        </div>

	<kakao-map class="col-12 col-md-8 my-auto shadow pl-1" width="100%" elementId="35989832" :markers="markers" />
    
    </div>
    <Footer class="d-none d-sm-block" style="bottom:0 !important!" />
</div>

</template>

<script>
import KakaoMap from '@/components/KakaoMap.vue'
import Footer from '@/views/section/Footer.vue'

export default {
    name: 'Kakao-map',
    components: {
            KakaoMap,
            Footer
    },
    data() {
        return {
            markers: [
                    {
                        markerPosition: (33.450701, 126.570667)
                    }
                ],
            avheight: 0,
        }
        
    },
    computed: {

    },
    methods: {
    },
    mounted() {
        this.avheight = window.innerHeight - 63.6
    }
}
</script>

<style>
.router-link-active {
    color: gold;
}
.menucol-1 {
    display:flex;
    flex-direction:column;
    justify-content: space-between;background-color: #CDDCDC;
    background-image: linear-gradient(-225deg, #473B7B 0%, #3584A7 51%, #30D2BE 100%);
    }
.menu-icon > i {
    color: rgba(0,0,0,0) !important;
    -webkit-text-stroke: 1px white !important;
}
.menu-tex {
    color: white;
    margin: auto;
    font-weight: 600;
    font-size: 1rem;
    text-align: center;
}
.info-header {
    padding: 1rem;
}
.menu-icon {
    cursor: pointer;
    padding: 5px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    transition: 0.2s
}
.menu-icon:hover {
    background: whitesmoke;
    box-shadow: 2px 2px 10px gray;
    border-radius: 10px;
}
.menu-icon:hover i {
    color: gold !important;
}
.menu-icon:hover .menu-tex{
    color: black;
}
.frame {
    height:120px;
    overflow: hidden;
    box-shadow: 1px 1px 10px black;
    padding: 5px !important;
    background-size: 100%;
}
</style>