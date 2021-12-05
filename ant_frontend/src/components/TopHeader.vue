<template>
  <div class="header-container">
    <div class="left">
      <span class="website-name" @click="toHome()">
        蚂蚁博客
      </span>
    </div>

    <div class="middle">
      <span class="search">
        搜索功能，敬请期待
      </span>
    </div>

    <div class="right">
        <span v-show="hasLogin">
          <el-avatar class="avatar"
                     :size="35"
                     :src="user.avatarUrl"
                     @click="toUserHome(user.userName)">
          </el-avatar>
        </span>

        <span v-show="hasLogin">
          <router-link to="/blog/add">写博客</router-link>
          <el-divider direction="vertical"></el-divider>
        </span>

        <router-link v-show="!hasLogin" to="/login">登录</router-link>
        <span v-show="hasLogin">
          <el-link type="danger" @click="logout">退出</el-link>
        </span>
      </div>
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
    toHome() {
      this.$router.push({name: "Home"});
    },
    toUserHome(userName) {
      this.$router.push({name: "UserHome", params: {userName: userName}})
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
.header-container {
  display: flex;
  height: 48px;
  margin: 0 0 20px 0;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .2);
  background-color: white;
}

.website-name {
  cursor: pointer;
}

.left {
  /*width: 400px;*/
  margin: 5px 10px;
  font-size: 24px;
  font-weight: bolder;
}

.middle {
  flex: 1;
  display: flex;
}

.search {
  margin: 5px auto;
}

.right {
  margin: 5px 10px;
  width: 250px;
}

.avatar {
  margin: 4px 20px 0 0;
  cursor: pointer;
}

</style>