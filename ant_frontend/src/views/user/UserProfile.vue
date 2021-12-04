<template>
  <div class="user-profile-container">
    <div class="user-information">
      <el-avatar class="avatar"
                 :size="50"
                 :src="avatarUrl">
      </el-avatar>
      <div class="nick-name">
        {{nickName}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserProfile",
  data() {
    return {
      nickName: '',
      avatarUrl: '',
      blogCount: 0
    }
  },
  methods: {
    getProfile(userName) {
      const _this = this;
      this.$axios
          .get("/user/profile?userName=" + userName)
          .then(user => {
                _this.nickName = user.data.data.nickName;
                _this.avatarUrl = user.data.data.avatarUrl;
                _this.blogCount = user.data.data.blogCount
              }
          )
    }
  },
  created() {
    let userName = this.$route.params.userName;
    this.getProfile(userName);
  }
}
</script>

<style scoped>
.user-information {
  display: flex;
  margin: 20px 20px;
}

.nick-name {
  flex: 1;
  margin: auto 20px;
}

</style>