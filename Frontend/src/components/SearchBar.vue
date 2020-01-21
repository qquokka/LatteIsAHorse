<template>
<form class="search-form input-group mb-3" @submit.prevent="searchNow">
    <input v-model="query" id="title" type="text" style="height:3rem;border-radius:5px 0 0 5px;" class="form-control" placeholder="지역명, 카페 이름 등" aria-describedby="button-addon2">
    <div class="input-group-append">
        <button  class="btn btn-info" type="button" id="button-addon2">검색</button>
    </div>
</form>
</template>

<script>
export default {
    name: 'TodoForm',
    data() {
        return {
            query: ''
        }
    },
    methods: {
        searchNow() {
            this.$emit('search-event', this.query)
        }
    }
}
</script>

<style scoped>
.btn-info {
    background-color: #88D8B0;
    color: #fff ;
    border-radius: 0 5px 5px 0;
    border: 0;
}
.search-form {
    width: 65%;
    min-width: 450px;
}
</style>
