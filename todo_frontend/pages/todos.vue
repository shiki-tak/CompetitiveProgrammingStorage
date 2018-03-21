<template>
  <div>
    <h1>To Do List</h1>
    <md-table>
      <md-table-row>
        <md-table-head>ID</md-table-head>
        <md-table-head>Title</md-table-head>
      </md-table-row>
      <md-table-row v-for="(todo, key, index) in todos" :key="index">
        <md-table-cell>{{ todo.id }}</md-table-cell>
        <md-table-cell>{{ todo.title }}</md-table-cell>
      </md-table-row>
    </md-table>
    <md-button class="md-raised">実行</md-button>
  </div>
</template>

<script>
import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/v1/todos/'

export default {
  name: 'TableBasic',

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
