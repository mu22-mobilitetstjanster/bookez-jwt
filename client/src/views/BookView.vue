<script lang="ts">
  import axios from 'axios';

  export default {
    async mounted() {
      let headers = { 'authorization': 'Bearer ' + localStorage.getItem("JWT_TOKEN") };
      try {
        let resp = await axios.get("http://127.0.0.1:8080/books/", {headers});
        console.log(resp.data);
      } catch(err: any) {
        console.warn(err.code);
      }
    }
  }
</script>

<template>
  <div>
    <h2>Books</h2>
  </div>
</template>