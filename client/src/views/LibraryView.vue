<script lang="ts">
import BookList from '@/components/BookList.vue';
import UserList from '@/components/UserList.vue';
import jwtService from '@/service/jwtService';
import axios from 'axios';

  export default {
    data() {
        return {
            username: jwtService.getProp("username"),
            role: jwtService.getProp("role")
        };
    },
    components: { BookList, UserList }
}
</script>

<template>
  <div>
    <h2>Books</h2>
    <p>Signed in as ({{ role }}) {{ username }}</p>
    <UserList v-if="role === 'ADMIN'" />
    <BookList />
  </div>
</template>