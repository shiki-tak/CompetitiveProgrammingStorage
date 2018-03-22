<template>
  <div>
    <h1>To Do List</h1>

    <form novalidate class="md-layout" @submit.prevent="validateUser">
      <md-card class="md-layout-item md-size-80 md-small-size-100">
        <md-card-content>
          <md-field>
            <label for="title">Title</label>
            <md-input type="title" name="title" id="title" autocomplete="title" v-model="title"  />
          </md-field>
        </md-card-content>
        <md-card-content>
          <md-field>
            <label for="comment">Comment</label>
            <md-input type="comment" name="comment" id="comment" autocomplete="comment" v-model="comment"  />
          </md-field>
        </md-card-content>
      </md-card>
      <md-card-actions>
        <md-button class="md-fab" v-on:click="createTodo">
          <md-icon>＋</md-icon>
        </md-button>
      </md-card-actions>
    </form>

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
      todos: [],
      title: '',
      comment: ''
    }
  },

  mounted: function() {
    this.load()
  },

  methods: {
    async load() {
      await this.$store.dispatch('getTodos').then((res) => {
        for(var i = 0; i < res.data.todos.length; i++) {
          this.todos.push(res.data.todos[i]);
        }
      }, (error) => {
        console.log(error);
      })
    },

    async createTodo() {
      const todo = { 'title': this.title, 'comment': this.comment }
      await this.$store.dispatch('createTodo', { todo }).then((res) => {
        this.todos.unshift(res.todo.todo);
        this.title = '';
        this.comment = '';

      }, (error) => {
        console.log(error);
      })
    }
  }
}
</script>

<style>
</style>
