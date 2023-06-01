<script lang="ts">
  import axios from 'axios';

  export default {
    data() {
      return { username: '', password: ''}
    },
    methods: {
      async handleLogin() {
        try {
          let resp = await axios.post("http://127.0.0.1:8080/auth/login", {username: this.username, password: this.password});
          let token = resp.data;

          localStorage.setItem("JWT_TOKEN", token);
        } catch (err) {
          console.warn(err);
        }
      }
    },
  }
</script>

<template>
  <form @submit.prevent="handleLogin">
    <div>
      <label>Username</label>
      <input type="username" v-model="username" />
    </div>
    <div>
      <label>Password</label>
      <input type="password" v-model="password" />
    </div>

    <button type="submit">Login</button>
  </form>
</template>