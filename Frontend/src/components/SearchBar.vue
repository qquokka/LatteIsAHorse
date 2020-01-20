<template>
<form class="todo-form input-group mb-3" @submit.prevent="todoCreate">
    <input v-model="title" id="title" type="text" class="form-control" placeholder="지역명, 카페 이름 등" aria-describedby="button-addon2">
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
            title: ''
        }
    },
    methods: {
        todoCreate() {
            this.$emit('todoCreate-event', this.title)
        }
    }
}
</script>

<style scoped>
.todo-form {
    width: 55%;
    min-width: 450px;
}
</style>
