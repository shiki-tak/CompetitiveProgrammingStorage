<template>
  <v-app id="inspire">
    <v-layout
      wrap
    >
      <v-toolbar-side-icon large class="menu_icon" @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-navigation-drawer
        v-model="drawer"
        absolute
        temporary
      >
        <v-list class="pt-0" dense>
          <v-list-tile
            v-for="item in items"
            :key="item.title"
            @click="handleClick"
            v-bind:to="item.url"
          >
          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-navigation-drawer>
    </v-layout>
    <h1 class="app_title">
      そこに、山があるから
    </h1>
    <nuxt/>
  </v-app>
</template>

<script>
  export default {
    data () {
      return {
        drawer: null,
        items: [
          { title: 'Home', url: '/' },
          { title: 'About', url: '/about' }
        ]
      }
    },
    methods: {
      handleClick() {
        
      }
    }
  }
</script>

<style>
html {
  font-family: "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  font-size: 16px;
  word-spacing: 1px;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  box-sizing: border-box;
}

*, *:before, *:after {
  box-sizing: border-box;
  margin: 0;
}

.button--green {
  display: inline-block;
  border-radius: 4px;
  border: 1px solid #3b8070;
  color: #3b8070;
  text-decoration: none;
  padding: 10px 30px;
}

.button--green:hover {
  color: #fff;
  background-color: #3b8070;
}

.button--grey {
  display: inline-block;
  border-radius: 4px;
  border: 1px solid #35495e;
  color: #35495e;
  text-decoration: none;
  padding: 10px 30px;
  margin-left: 15px;
}

.button--grey:hover {
  color: #fff;
  background-color: #35495e;
}

.layout.wrap {
  max-height: 110px;
}

.app_title {
  font-weight: 300;
  font-size: 32px;
  color: #526488;
  word-spacing: 5px;
  padding-bottom: 15px;
  text-align: center;
}

.menu_icon {
  margin-left: 5%;
  margin-top: 5%;
}
</style>

