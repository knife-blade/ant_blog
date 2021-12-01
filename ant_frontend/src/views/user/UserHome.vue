<template>
  <div class="user-home-container">
    <top-header></top-header>
    <div class="main-container">
      <left-aside class="left"></left-aside>
      <BlogList class="middle" ></BlogList>
    </div>
  </div>
</template>

<script>
import BlogList from "@/views/blog/BlogList";
import TopHeader from "@/components/TopHeader";
import LeftAside from "@/components/LeftAside";

export default {
  name: "Home",
  components: {TopHeader, LeftAside, BlogList},
  data() {
    return {
      userName: ""
    }
  },
  created() {
    this.userName = this.$route.params.userName;
  }
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