<template>
  <h2>Register new account</h2>
  <form @submit.prevent="">
  <div>
    <label>Username</label>
    <input type="username" />
  </div>
  <div>
    <label>Password</label>
    <input type="password"  />
  </div>

  <button type="submit">Create account</button>
</form>
</template>