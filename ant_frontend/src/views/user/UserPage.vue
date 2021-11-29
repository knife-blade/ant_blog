<template>
  <div class="user-container">
    <div class="user-list">
      <div class="user-item-box"
           v-for="user of users" @click="toUserHome(user.userName)">
        <el-avatar class="avatar" :size="50" :src="user.avatarUrl"></el-avatar>
        <span class="nick-name">{{user.nickName}}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserPage",
  data() {
    return {
      users: {},
      current: 1,
      total: 0,
      size: 10
    }
  },
  methods: {
    page(current, size) {
      const _this = this
      this.$axios
          .get("/user/page?current=" + current + "&size=" + size)
          .then(res => {
            _this.users = res.data.data.records
            _this.current = res.data.data.current
            _this.total = res.data.data.total
            _this.size = res.data.data.size
          })
    },
    toUserHome() {
      this.$router.push({name: "UserHome"})
    }
  },
  created() {
    this.page(1, 10);
  }

}
</script>

<style scoped>
.nick-name {
  margin: 0 20px 5px 20px;
}

.user-item-box {
  cursor: pointer;
}

</style>