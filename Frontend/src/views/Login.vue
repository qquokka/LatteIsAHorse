<template >
<div class="container-fluid" :style="{'background-image': `url(${require('../assets/img/loginbg.jpg')})`}">
    <div class="row loginrow">
        <login-form class="logindiv" @login-event="login"/>
    </div>
</div>
</template>

<script>
    import axios from 'axios'
    import LoginForm from '@/components/LoginForm.vue'
    import router from '../router'
        // 특정 폴더명으로 경로가 끝나게 되면, 폴더 내부의 index.js를 뜻함.
    export default {
        name: 'Login',
        components: {
            LoginForm
        },
        methods: {
            login(credentials) {
                console.log('method')
                axios.post('http://127.0.0.1:8000/api-token-auth/', credentials)
                    .then(response => {
                        console.log('로그인성공')
                        console.log(response.data.token)
                        const token = response.data.token
                        this.$session.start()
                        this.$session.set('jwt', token)
                        this.$store.dispatch('login', token)
                        router.push('/')
                    })
            }
        }    
    }
</script>

<style>
.container-fluid {
    height: 100vh;
    position: absolute;
    top:0;
}
.loginrow {
    height: 100%;
}
</style>