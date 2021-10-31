<template>
  <div class="m-content">
    <h3>欢迎来到蚂蚁博客</h3>
    <div class="block">
      <el-avatar :size="50" :src="user.avatarUrl"></el-avatar>
      <div>{{ user.userName }}</div>
    </div>

    <div class="maction">
      <!-- 不能这样写。这样写在配置了跨域时有问题，会直接请求后端
        <span><el-link href="/blogs">主页</el-link></span> -->
      <router-link to="/blogs">主页</router-link>
      <el-divider direction="vertical"></el-divider>
      <router-link to="/blog/add">写博客</router-link>
      <el-divider direction="vertical"></el-divider>
      <router-link v-show="!hasLogin" to="/login">登录</router-link>

      <span v-show="hasLogin"><el-link type="danger" @click="logout">退出</el-link></span>
    </div>

  </div>
</template>

<script>
import BlogEdit from "@/views/BlogEdit";

export default {
  name: "Header",
  components: {BlogEdit},
  data() {
    return {
      user: {
        userName: '请先登录',
        avatarUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
      },
      hasLogin: false
    }
  },
  methods: {
    logout() {
      const _this = this
      _this.$axios
          .post("/logout")
          .then(res => {
            _this.$store.commit("REMOVE_INFO")
            _this.$router.push("/login")
          })
    }
  },
  created() {
    if (this.$store.getters.getUser !== null
        && this.$store.getters.getUser.userName !== null) {
      this.user.userName = this.$store.getters.getUser.userName
      this.user.avatarUrl = this.$store.getters.getUser.avatarUrl
      this.hasLogin = true
    }
  }
}
</script>

<style scoped>
.m-content {
  max-width: 960px;
  margin: 0 auto;
  text-align: center;
}

.maction {
  margin: 10px 0;
}
</style>