<script lang="ts">
import type { SigninAuthDetails } from '@/model/AuthDetails';
import authService from '@/service/authService';

export default {
  data() {
    return {
      authDetails: {username: '', password: ''} as SigninAuthDetails
    }
  },
  emits: ['afterSubmit'],
  methods: {
    async handleSubmit() {
      await authService.login(this.authDetails);
      this.$emit('afterSubmit');
    }
  }
}
</script>

<template>
  <h2>Sign into your account</h2>
  <form @submit.prevent="handleSubmit">
    <div>
      <label>Username</label>
      <input type="text" v-model="authDetails.username" />
    </div>
    <div>
      <label>Password</label>
      <input type="password" v-model="authDetails.password"/>
    </div>

    <button type="submit">Login</button>
  </form>
</template>