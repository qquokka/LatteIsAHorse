<template>
<div id="footer" class="container-fluid ">
    <div class="row align-items-center m-0">
        <div class="d-none d-md-flex col-md-5 justify-content-center">
            <p class="footer-brand">LATTE<span style="color:violet;">=</span>HORSE</p><small class="text-white my-auto ml-2">커피와 사람을 잇는 새로운 시대의 플랫폼, 라떼는 말이야</small>
        </div>
        <div class="col offset-md-2">
            <div class="row justify-content-around py-1">
                <router-link to="#" class="footer-right">이용 약관</router-link>
                <router-link to="#" class="footer-right">개인 정보 취급 방침</router-link>
                <router-link to="#" class="footer-right">가맹 문의</router-link>
                <router-link to="about" class="p-3 text-white-50">© 2020 Team HBH</router-link>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {

}
</script>

<style>
#footer {
    background: slateblue;
    height: 64px;
    margin:0;
    align-items: center
}
.footer-brand {
    font-size: 2rem;
    font-weight: 900;
    color: whitesmoke;
    font-family: sans-serif;
    user-select: none;
    margin:0;
}
.footer-right {
    font-size: 1rem;
    color: whitesmoke;
    text-align: left;
    padding: 1rem;
    text-decoration: none !important;
}
</style>