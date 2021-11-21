<template>
  <div class="blog-container">
    <Header></Header>

    <div class="article-list">
      <!--<el-timeline>-->
      <!--  <el-timeline-item v-for="blog in blogs" :timestamp="blog.createTime" placement="top" :key="blog.id">-->
      <!--    <el-card>-->
      <!--      <h4>-->
      <!--        <router-link :to="{name: 'BlogDetail', params: {blogId: blog.id}}">-->
      <!--          {{ blog.title }}-->
      <!--        </router-link>-->
      <!--      </h4>-->
      <!--      <p>{{ blog.description }}</p>-->
      <!--    </el-card>-->
      <!--    <button @click="deleteBlog(blog.id)">-->
      <!--      删除-->
      <!--    </button>-->
      <!--  </el-timeline-item>-->
      <!--</el-timeline>-->

      <div v-for="blog of blogs">
        <div class="title">{{ blog.title }}</div>
        <p class="description">{{ blog.description }}</p>
        <span class="date">{{ blog.createTime }}</span>
        <span class="operations">
          <el-button @click="deleteBlog(blog.id)" size="mini">
            编辑
          </el-button>
          <el-button @click="deleteBlog(blog.id)" size="mini">
            删除
          </el-button>
        </span>

        <el-divider></el-divider>
      </div>

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
import Header from "../../components/Header";

export default {
  name: "Blogs",
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
            _this.$router.push()
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
.article-list {
  padding-left: 36px;
  text-align: left;
}

.title {
  font-size: 18px;
}

.description {
  font-size: 14px;
  /*只显示两行*/
  display: -webkit-box;
  overflow: hidden;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.date {
  font-size: 14px;
  color: #5f6471;
}

.operations {
  position: absolute;
  right: 24px;
  margin: 2px;
}

</style>