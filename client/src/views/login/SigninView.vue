<script lang="ts">
import cacheService from '@/service/cacheService';
export default {
  emits: ['afterSubmit'],
  methods: {
    handleSubmit() {
      // authenticate user... bla 
      cacheService.storeLocal("username", "bob");

      //var username = cacheService.fetchLocal<string>("username");

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
      <input type="username" />
    </div>
    <div>
      <label>Password</label>
      <input type="password"  />
    </div>

    <button type="submit">Login</button>
  </form>
</template>