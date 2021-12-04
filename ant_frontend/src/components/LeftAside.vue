<template>
  <div class="left-aside-container">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "LeftAside"
}
</script>

<style scoped>

.left-aside-container {
  width: 300px;
  margin: 0 10px 0 5px;
  background-color: white;
}

</style>