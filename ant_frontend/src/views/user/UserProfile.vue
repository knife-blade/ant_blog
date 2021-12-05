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

    <div class="data-information">
      <dl>
        <dt>
          {{blogCount}}
        </dt>
        <dd>博客总数</dd>
      </dl>
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
.user-profile-container {
  background-color: white;
}

.user-information {
  display: flex;
  margin: 0 0 20px 10px;
}

.nick-name {
  flex: 1;
  margin: auto 20px;
}

.data-information {
  display: flex;
}

dl {
  margin: 10px;
  text-align: center;
}

dd {
  margin: 0;
}

</style>