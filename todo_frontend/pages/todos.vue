<template>
  <h1>To Do List</h1>
  <!-- <ul>
    <li v-for="todo in todos">{{todo}}</li>
  </ul> -->

</template>

<script>
export default {

  data() {
    return {
      todos: []
    }
  },

  async fetch ({ store }) {
    await store.dispatch('getTodos')
    this.todos = store.getters.todos
    console.log(this.todos)
  }
}
</script>

<style></style>
