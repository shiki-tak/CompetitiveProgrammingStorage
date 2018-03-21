<template>
  <h1>To Do List</h1>
</template>

<script>
export default {

  data() {
    return {
      todos: ''
    }
  },

  mounted () {
    this.load()
  },

  methods: {
    load() {
      const { todos } =this.$store.dispatch('getTodos')
    }
  }
}
</script>

<style></style>
