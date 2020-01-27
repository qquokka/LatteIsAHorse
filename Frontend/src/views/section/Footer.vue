<template>
<div id="footer" class="container-fluid ">
    <div class="row align-items-center">
        <div class="d-none d-md-flex col-md-4 justify-content-center">
            <p class="footer-brand">LATTE<span style="color:violet;">=</span>HORSE</p>
        </div>
        <div class="col">
            <div class="row justify-content-around py-1">
                <router-link to="#" class="footer-right">이용 약관</router-link>
                <router-link to="#" class="footer-right">개인 정보 취급 방침</router-link>
                <router-link to="#" class="footer-right">가맹 문의</router-link>
                <router-link to="about" class="px-3 text-muted">© 2020 Team HBH</router-link>
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
    background: #3f3f3f;
    height: fit-content;
    align-items: center
}
.footer-brand {
    font-size: 3rem;
    font-weight: 900;
    color: whitesmoke;
    font-family: sans-serif;
    margin:0;
}
.footer-right {
    font-size: 1rem;
    color: white;
    text-align: left;
    padding-left: 1rem;
    padding-right: 1rem;
    border-right: 1px solid gray;
    border-left: 1px solid gray;
    text-decoration: none !important;
}
</style>