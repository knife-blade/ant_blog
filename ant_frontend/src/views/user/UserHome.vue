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
  margin: 10px 0 0 0;
  border-top: 2px solid rgb(126, 150, 130);
}

.middle {
  flex: 1;
}
</style>