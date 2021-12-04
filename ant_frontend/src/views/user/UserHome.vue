<template>
  <div class="user-home-container">
    <top-header></top-header>
    <div class="main-container">
      <left-aside class="left">
        <user-profile></user-profile>
      </left-aside>
      <blog-list class="middle" :userName="userName"></blog-list>
    </div>
  </div>
</template>

<script>
import BlogList from "@/views/blog/BlogList";
import TopHeader from "@/components/TopHeader";
import LeftAside from "@/components/LeftAside";
import UserProfile from "@/views/user/UserProfile";

export default {
  name: "Home",
  components: {TopHeader, LeftAside, BlogList, UserProfile},
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