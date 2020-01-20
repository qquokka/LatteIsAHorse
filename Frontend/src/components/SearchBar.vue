<template>
    <md-field>
        <label>지역, 카페 이름 </label>
        <md-input v-model="inputText"></md-input>
        <span class="md-helper-text">Helper text</span>
        <md-button @click="onInput"  type="submit" class=""><md-icon >search</md-icon>검색</md-button>
    </md-field>
</template>

<script>
/* eslint-disable */
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
                this.$emit('input-change-event', this.inputText)
            }
        }
    }
</script>

<style lang="scss" scoped>
label {
    color: white;
}
</style>