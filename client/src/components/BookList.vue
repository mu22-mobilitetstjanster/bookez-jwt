<script lang="ts">
import bookService from '@/service/bookService';

export default {
  data() {
    return {
      books: [] as Array<string>
    }
  },
  async mounted() {
    try {
      this.books = await bookService.fetchAll();
    } catch(err: any) {
      console.warn("Failed to fetch books", "Code: " + err.code + "(" + err.response.status + ")");
    }
  }
}
</script>

<template>
  <ul>
    <li v-for="(book, index) in books" v-bind:key="index">
      {{ book }}
    </li>
  </ul>
</template>