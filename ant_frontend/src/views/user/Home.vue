<template>
  <div class="home-page-container">
    <top-header></top-header>
    <div class="main-container">
      <left-aside class="left"></left-aside>
      <blogs class="middle"></blogs>
    </div>
  </div>
</template>

<script>
import Blogs from "@/views/blog/Blogs";
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
}

.middle {
  flex: 1;
}
</style>