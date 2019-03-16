<template>
  <div>
    <v-container
      grid-list-xl
      align-center
    >
      <div class="main">
        <h1 class="title">
          Yama Lover's Word
        </h1>
        <div class="links">
          <a
            href="http://127.0.0.1:3000/server/auth/twitter"
            class="button--grey">Twitter Login</a>
        </div>
      </div>
      <v-layout v-for="word in words" :key="word.id">
        <v-card
          class="mx-auto"
        >
          <v-img
            class="word_image"
            v-bind:src="word.background_image_url"
          >
            <v-card-title>
              <v-icon
                large
                left
              >
                mdi-twitter
              </v-icon>
            </v-card-title>

            <v-card-text class="headline font-weight-bold">
              {{ word.text }}
            </v-card-text>

            <v-card-actions>
              <v-list-tile class="grow">
                <v-list-tile-avatar color="grey darken-3">
                  <v-img
                    class="elevation-6"
                    v-bind:src=word.user_image_url
                  ></v-img>
                </v-list-tile-avatar>

                <v-list-tile-content>
                  <v-list-tile-title class="user_name">{{ word.user_name }}</v-list-tile-title>
                </v-list-tile-content>

                <v-layout
                  align-center
                  justify-end
                >
                </v-layout>
              </v-list-tile>
            </v-card-actions>
          </v-img>
        </v-card>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import AppLogo from '~/components/AppLogo.vue'
import axios from 'axios'

export default {
  components: {
    AppLogo
  },
  asyncData ({ params }) {
    return axios
      .get("http://localhost:8080/api/v1/words")
      .then((response) => {
        return { words : response.data }
      })
  },
  data() {
    return {
    }
  }
}
</script>

<style>
.title {
  font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif; /* 1 */
  display: block;
  font-weight: 300;
  font-size: 100px;
  color: #35495e;
  letter-spacing: 1px;
}

.links {
  padding-top: 15px;
}

.main {
  margin-bottom: 5%;
  text-align: center;
}

.v-card__actions {
  position: absolute;
  bottom: 0;
}

.mx-auto {
  margin-bottom: 90px;
  max-width: 600;
  max-height: 400;
  width: 600px;
  height: 400px;
  border-radius: 20px;
}

.headline.font-weight-bold {
  color: white;
}

.v-responsive__content {
  background-color: rgba(0,0,0,0.5);
}

.user_name {
  color: white;
}

</style>

