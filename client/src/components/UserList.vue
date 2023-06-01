<script lang="ts">
import type { UserDetails } from '@/model/UserDetails';
import userService from '@/service/userService';

export default {
  data() {
    return {
      users: [] as Array<UserDetails>
    }
  },
  async mounted() {
    try {
      this.users = await userService.fetchAll();
    } catch(err: any) {
      console.warn("Failed to fetch users", "Code: " + err.code + "(" + err.response.status + ")");
    }
  }
}
</script>

<template>
  <ul>
    <li v-for="(user, index) in users" v-bind:key="index">
      {{ user.username }} || {{ user.role }}
    </li>
  </ul>
</template>