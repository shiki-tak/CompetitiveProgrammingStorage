<template>
  <div>
    <h1>To Do List</h1>
    <ul>
      <li v-for="(todo, key, index) in todos" :key="index">{{ todo.title }}</li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/v1/todos/'

export default {

  data: function() {
    return {
      todos: []
    }
  },

  mounted: function() {
    this.load()
  },

  methods: {
    load() {
      axios.get(BASE_URL).then((res) => {
        for(var i = 0; i < res.data.todos.length; i++) {
          console.log(res.data.todos[i])
          this.todos.push(res.data.todos[i]);
        }
      }, (error) => {
        console.log(error);
      });
    }
  }

}
</script>

<style></style>
