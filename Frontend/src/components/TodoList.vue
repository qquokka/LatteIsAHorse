<template>
    <div class="todo-list mt-5">
        <h2 class="mb-5">Todo List?</h2>
        <div class="input-group mx-5 text-center">
            <div class="container">
                <div v-for="todo in todos" :key="todo.id" href="#" class="input-group success mx-auto">
                    <input @change="modTodo(todo)" type="checkbox" class="checkbox-custom checkbox-light" v-model="todo.is_completed">
                    <h5 class="col-1 border-bottom">{{ todo.id }}</h5>
                    <h5 class="col border-bottom mr-5">{{ todo.title }}</h5>
                    <button @click='todoDelete(todo.id)' class="ml-auto col-1 btn btn-small btn-outline-danger">삭제</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
    export default {
        name: 'TodoList',
        props: {
            todos: {
                todos: Array,
                required: true
            }
        },
        methods: {
            todoDelete(id) {
                this.$session.start()
                const token = this.$session.get('jwt')
                const options = {
                headers: {
                    Authorization: `JWT ${token}`
                }
                }
                axios.delete(`http://127.0.0.1:8000/api/v1/todos/${id}`, options)
                    .then(response => {
                        console.log(response)
                        this.$emit('todoDelete-event')
                    })
                },
            modTodo(todo) {
                this.$session.start()
                const token = this.$session.get('jwt')
                const options = {
                    headers: {
                        Authorization: `JWT ${token}`
                    }
                }
                axios.put(`http://127.0.0.1:8000/api/v1/todos/${todo.id}`, todo, options)
                    .then(response => {
                        console.log(response)
                        this.$emit('todoModify-event')
                    })
            }
            }
        }
    
</script>

<style>
</style>