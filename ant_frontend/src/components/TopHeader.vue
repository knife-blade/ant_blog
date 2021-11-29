<template>
  <div class="header-container">
      <span class="left">
        <span class="website-name" @click="home()">
          蚂蚁博客
        </span>
      </span>

    <span class="middle">
        搜索功能，敬请期待
    </span>

    <span class="right">
        <span>
          <el-avatar class="avatar" :size="35" :src="user.avatarUrl"></el-avatar>
          <!--<span>{{ user.userName }}</span>-->
        </span>
      <!-- 不能这样写。这样写在配置了跨域时有问题，会直接请求后端
        <span><el-link href="/blogs">主页</el-link></span> -->
        <router-link to="/blogs">主页</router-link>
        <el-divider direction="vertical"></el-divider>
        <router-link to="/blog/add">写博客</router-link>
        <el-divider direction="vertical"></el-divider>
        <router-link v-show="!hasLogin" to="/login">登录</router-link>
        <span v-show="hasLogin">
          <el-link type="danger" @click="logout">退出</el-link>
        </span>
      </span>
  </div>
</template>

<script>
import BlogEdit from "@/views/blog/BlogEdit";

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
            // _this.hasLogin = false
            _this.$store.commit("REMOVE_INFO")
            _this.$router.push({name: "Home"})
          })
    },
    home() {
      this.$router.push({name: "Home"});
    }
  },
  created() {
    if (this.$store.getters.getUser !== null
        && this.$store.getters.getUser.userName !== null) {
      this.user.userName = this.$store.getters.getUser.userName
      this.user.avatarUrl = this.$store.getters.getUser.avatarUrl
      this.hasLogin = this.$store.getters.getHasLogin
    }
  }
}
</script>

<style scoped>
.website-name {
  cursor: pointer;
}

.header-container {
  display: flex;
  height: 48px;
  /*margin: 5px;*/
  border-bottom: 2px solid rgb(126, 150, 130);
}

.left {
  width: 400px;
  margin: 0 0 0 10px;
  font-size: 24px;
  font-weight: bolder;
}

.middle {
  flex: 1;
}

.right {
  width: 250px;
}

.avatar {
  margin: 4px 20px 0 0;
}

</style>