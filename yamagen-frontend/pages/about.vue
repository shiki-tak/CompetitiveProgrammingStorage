<template>
  <div>
    <v-container
      grid-list-xl
      align-center
    >
      <div class="about">
        <div class="bg-image"></div>

      </div>
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
  width: 600px;
  height: 400px;
  border-radius: 20px;
}

.about {
max-height: 300px;
}

</style>

