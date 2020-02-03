<template>
  <div id="search-result">
    <nav-bar />
    <search-bar class="mx-auto"  style="margin-top:9rem" />
    <div class="row px-2">
        <div v-for="post in postData.slice(0, limits)" :key="post.id" class="post-list col-4 p-3">
        <router-link :to="`post/${post.id}/`" class="text-decoration-none">
            <div class="postbody overflow-hidden">
                <img :src="post.thumbnail" width="100%" height="300px">
                <h3 class="p-2 posttitle">{{ post.title }}</h3>
                <p class="" style="font-size:0.8rem"><i class="fas fa-thumbs-up"> {{ post.like_count }}</i></p>
                <p class="mb-0"> {{ post.writer_name }}</p>
                <p class="postcreate" style="top:1rem;left:3rem"> {{ post.created_at.slice(0,10) }} </p>
            </div>
        </router-link>
    </div>
</div>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
import SearchBar from '@/components/SearchBar.vue'
import axios from 'axios'

export default {
    name : 'SearchResult',
    components:{
        NavBar,
        SearchBar
    },
    data(){
        return{

        }
    },
    props :{
        cafes : Array,
        posts : Array
    },
    methods:{
        goToCafePage(cafe_id){
            axios.get(`${this.$store.state.constants.SERVER}/cafe/${cafe_id}`)
                .then(response => {
                
                    console.log(response)
                })
                .catch(error => {
                    console.log(error.response)
                })
        }
    }
    
}
</script>

<style>

</style>