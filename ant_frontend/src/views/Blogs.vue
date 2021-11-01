<template>
  <div class="blog-container">
    <Header></Header>

    <div class="block">
      <el-timeline>

        <el-timeline-item v-for="blog in blogs" :timestamp="blog.createTime" placement="top" :key="blog.id">
          <el-card>
            <h4>
              <router-link :to="{name: 'BlogDetail', params: {blogId: blog.id}}">
                {{ blog.title }}
              </router-link>
            </h4>
            <p>{{ blog.description }}</p>
          </el-card>
          <button @click="deleteBlog(blog.id)">
            删除
          </button>
        </el-timeline-item>
      </el-timeline>

      <el-pagination class="block-page"
                     background
                     layout="prev, pager, next"
                     :current-page="current"
                     :page-size="size"
                     :total="total"
                     @current-change=page>
      </el-pagination>

    </div>

  </div>
</template>

<script>
import Header from "../components/Header";

export default {
  name: "Blogs.vue",
  components: {Header},
  data() {
    return {
      blogs: {},
      current: 1,
      total: 0,
      size: 10
    }
  },
  methods: {
    page(currentPage) {
      const _this = this
      _this.$axios.get("/blog/page?current=" + currentPage).then(res => {
        console.log(res)
        _this.blogs = res.data.data.records
        _this.current = res.data.data.current
        _this.total = res.data.data.total
        _this.size = res.data.data.size
      })
    },

    deleteBlog(blogId) {
      const _this = this
      this.$axios.post("/blog/delete?ids=" + blogId,
          {}, {
            headers: {
              "token": localStorage.getItem("token")
            }
          }
      ).then(res => {
        console.log(res)
        _this.$alert('操作成功', '提示', {
          confirmButtonText: '确定',
          callback: action => {
            _this.$router.push("/blogs")
          }
        });
      })
    }
  },

  created() {
    this.page(1)
  }
}
</script>

<style scoped>
.block-page {
  margin: 0 auto;
  text-align: center;
}
</style>