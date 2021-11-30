<template>
  <div class="user-home-container">
    <top-header></top-header>
    <div class="main-container">
      <left-aside class="left"></left-aside>
      <blogs class="middle"></blogs>
    </div>
  </div>
</template>

<script>
import Blogs from "@/views/blog/BlogPage";
import TopHeader from "@/components/TopHeader";
import LeftAside from "@/components/LeftAside";
export default {
  name: "Home",
  components: {TopHeader, LeftAside, Blogs}
}
</script>

<style scoped>

.main-container {
  display: flex;
  margin: 0 50px 0 50px;
}

.middle {
  flex: 1;
}
</style>