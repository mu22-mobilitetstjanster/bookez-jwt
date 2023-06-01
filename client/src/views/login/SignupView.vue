<script lang="ts">
import type { SignupAuthDetails } from '@/model/AuthDetails';
import authService from '@/service/authService';

export default {
  data() {
    return {
      authDetails: {username: '', password: ''} as SignupAuthDetails
    }
  },
  emits: ['afterSubmit'],
  methods: {
    async handleSubmit() {
      await authService.register(this.authDetails);
      this.$emit('afterSubmit');
    }
  }
}
</script>

<template>
  <h2>Create new account</h2>
  <form @submit.prevent="handleSubmit">
    <div>
      <label>Username</label>
      <input type="text" v-model="authDetails.username" />
    </div>
    <div>
      <label>Password</label>
      <input type="password" v-model="authDetails.password"/>
    </div>

    <button type="submit">Register account</button>
  </form>
</template>