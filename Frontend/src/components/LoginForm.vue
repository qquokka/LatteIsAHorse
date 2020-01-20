<template>
    <div class="container">
        <h2 class="text-success font-weight-bolder">HaPPY <span class="text-info">LoGIN</span></h2>
        <form class="col-5 mx-auto p-4 shadow-lg border border-top-0" @submit.prevent="login">
            <div class="form-group">
                <input type="text" class="form-control" id="inputId" placeholder="username" v-model="credentials.username">
                <small id="idHelp" class="form-text text-muted">가입하실 때 작성하신 사용자 이름을 입력하세요.</small>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="inputPW" placeholder="password" v-model="credentials.password">
            </div>
            <button type="submit" class="btn btn-outline-warning shadow">로그인</button>
        </form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                credentials: {
                }
            }
        },
        methods: {
            loginLog() {
                console.log('함수가 실행되었습니다!!!!!!!!!!!!!!')
                console.log(this.id)
                console.log(this.password)
            },
            login() {
                console.log('emit')
                this.$emit('login-event', this.credentials)
                this.credentials = {}
            }
        }
    }
</script>

<style>
</style>