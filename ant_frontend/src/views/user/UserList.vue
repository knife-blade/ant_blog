<template>
  <div class="user-list-container">
    <div class="user-list">
      <div class="user-item-box"
           v-for="user of users"
           @click="toUserHome(user.userName)">
        <el-avatar class="avatar" :size="50" :src="user.avatarUrl"></el-avatar>
        <div class="nick-name">{{user.nickName}}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserList",
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
    toUserHome(userName) {
      this.$router.push({name: "UserHome", params: {userName: userName}})
    }
  },
  created() {
    this.page(1, 10);
  }

}
</script>

<style scoped>

.user-list-container {
  margin: 0 50px 0 10px;
  background-color: white;
}

.user-item-box {
  cursor: pointer;
  border-bottom: #c2c7d2 solid 1px;
  padding: 3px 0 3px 0;
  display: flex;
}

.user-item-box:hover {
  background-color: rgb(245, 245, 250);
}

.avatar {
  margin: 0 20px 5px 5px;
}

.nick-name {
  margin: auto 10px;
}

</style>