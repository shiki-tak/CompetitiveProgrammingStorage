<template>
  <div class="signup">
    <div class="col-sm-4 col-sm-offset-4">
      <h2>Sign Up</h2>
      <div class="alert alert-danger" v-if="error">
        <p>{{ error }}</p>
      </div>

      <div class="form-group">
        <input
          type="text"
          class="form-control"
          placeholder="Enter your name"
          v-model="credentials.name"
          >
      </div>

      <div class="form-group">
        <input
          type="text"
          class="form-control"
          placeholder="Enter your email"
          v-model="credentials.email"
          >
      </div>

      <div class="form-group">
        <input
          type="password"
          class="form-control"
          placeholder="Enter your password"
          v-model="credentials.password"
          >
      </div>
      <button class="btn btn-primary" @click="signup()">Sign up</button>
    </div>
  </div>
</template>

<script>
export default {
  layout: 'default',
  data () {
    return {
      credentials: {
        name: '',
        email: '',
        password: ''
      },
      error: ''
    }
  },
  methods: {
    async signup () {
      try {
        await this.$store.dispatch('signup', {
          name: this.credentials.name,
          email: this.credentials.email,
          password: this.credentials.password
        })
        this.$router.push('/')
      } catch(e) {
        this.error = e.message
      }
    }
  }
}
</script>
