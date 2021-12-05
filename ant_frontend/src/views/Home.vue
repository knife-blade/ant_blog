<template>
  <div class="home-container">
    <top-header></top-header>
    <div class="main-container">
      <left-aside class="left">
        <div class="blogger">
          博主列表
        </div>
      </left-aside>
      <user-list class="middle"></user-list>
    </div>

  </div>
</template>

<script>
import TopHeader from "@/components/TopHeader";
import LeftAside from "@/components/LeftAside";
import UserList from "@/views/user/UserList";

export default {
  name: "Home",
  components: {TopHeader, LeftAside, UserList},
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

.blogger {
  font-size: 20px;
  padding: 10px 10px;
  background-color: white;
}

</style>