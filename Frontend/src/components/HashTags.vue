<template>
<div class="container">
    <div class="row justify-content-center text-dark hash">
        <a>#인스타맛집</a><a> #브런치</a><a> #24시간</a><a> #스터디</a><a> #조용한곳</a><a> #고급원두</a><a> #핸드드립 </a><a>#비건메뉴</a><a> #노키즈존</a>
    </div>
</div>
</template>

<script>
export default {

}
</script>

<style>
.hash > a {
    cursor: pointer;
    margin: 5px;
    padding: 2px;
    border-radius: 5px;
    background: rgba(255,255,255,0.2);
}
@media only screen and (max-width: 600px) {
    .hash > a {
        background: rgba(44,123,33,0.9); 
    }
}
</style>