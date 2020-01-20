<template>
    <div class="input-group mb-3">
        <input v-model="inputText" type="text" class="form-control form-control-lg" placeholder="검색어를 입력하세요">
        <input @click="onInput" type="submit" class="btn btn-lg btn-outline-secondary ml-2 input-group-append" value="검색">
    </div>
</template>

<script>
    export default {
        name: 'SearchBar',
        data() {
            return {
                inputText: ''
            }
        },
        methods: {
            onInput() {
                console.log('==SearchBar==')
                // $emit(이벤트이름, 값) : 커스텀 이벤트 발생
                // 이벤트이름의 이벤트를 발생시킴(트리거)
                this.$emit('input-change-event', this.inputText)
            }
        }
    }
</script>

<style>
</style>