<template>
  <div class="home-container">
    <top-header></top-header>
    <div class="main-container">
      <left-aside class="left">
        博主列表（后期改为导航）
      </left-aside>
      <user-page class="middle"></user-page>
    </div>

  </div>
</template>

<script>
import TopHeader from "@/components/TopHeader";
import LeftAside from "@/components/LeftAside";
import UserPage from "@/views/user/UserPage";

export default {
  name: "Home",
  components: {TopHeader, LeftAside, UserPage}
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