<template>
<form class="search-form input-group mb-3" @submit.prevent="searchNow">
    <input v-model="query" id="title" type="text" style="height:3rem;border-radius:15px 0 0 15px;border: 2px solid #FFEEAD ;" class="form-control" placeholder="지역명, 카페 이름 등" aria-describedby="button-addon2">
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
    border: 2px solid #FFEEAD ;
    border-radius: 0 15px 15px 0;
}
.search-form {
    width: 55%;
    min-width: 450px;
    
}
</style>
