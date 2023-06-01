<template>
  <RouterView 
    @afterSubmit="() => $router.push('/library')" />
</template>