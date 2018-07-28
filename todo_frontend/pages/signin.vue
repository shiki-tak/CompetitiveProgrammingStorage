<template>
  <div class="signin">
    <div class="col-sm-4 col-sm-offset-4">
      <h2>Sign In</h2>
      <div class="alert alert-danger" v-if="error">
        <p>{{ error }}</p>
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
      <button class="btn btn-primary" @click="signin()">Sign in</button>
    </div>
  </div>
</template>

<script>

export default {
  layout: 'default',
  data () {
    return {
      credentials: {
        email: '',
        password: ''
      },
      error: ''
    }
  },
  methods: {
    signin () {
      var credentials = {
        name: this.credentials.name,
        email: this.credentials.email,
        password: this.credentials.password
      }
      auth.signin(this, credentials)
      this.$router.push('/')
    }
  }
}
</script>
