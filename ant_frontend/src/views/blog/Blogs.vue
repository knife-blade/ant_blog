<template>
  <div class="blog-container">
    <Header></Header>

    <div class="article-list">
      <div class="article-item-box" v-for="blog of blogs">
        <h3 class="title" @click="viewBlog(blog.id)">
          {{ blog.title }}
        </h3>

        <!-- <h4 class="title">
          <router-link :to="{name: 'BlogDetail', params: {blogId: blog.id}}">
            {{ blog.title }}
          </router-link>
        </h4>-->

        <p class="description">{{ blog.description }}</p>
        <span class="date">{{ blog.createTime }}</span>

        <span class="operations">
          <el-button @click="editBlog(blog.id)" size="mini">
            编辑
          </el-button>

          <!-- <el-button size="mini">
            <router-link :to="{name: 'BlogEdit', params: {blogId: blog.id}}">&ndash;&gt;
               编辑
            </router-link>
          </el-button>-->

          <el-button @click="deleteBlog(blog.id)" size="mini">
            删除
          </el-button>
        </span>
      </div>

      <div class="page-block">
        <el-pagination
            class="page-container"
            layout="total, prev, pager, next, jumper"
            :current-page="current"
            :page-size="size"
            :total="total"
            @current-change=page>
        </el-pagination>
      </div>
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

    viewBlog(blogId) {
      this.$router.push({name: "BlogDetail", params: {blogId: blogId}})
    },

    editBlog(blogId) {
      this.$router.push({name: "BlogEdit", params: {blogId: blogId}})
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
.article-item-box:hover {
  background-color: rgb(245, 245, 250);
}

.article-list {
  text-align: left;
}

.article-list .article-item-box {
  padding: 16px 24px 13px 24px;
  border-bottom: 1px solid rgb(126, 150, 130);
}

.article-list .article-item-box p.description {
  margin: 8px 0 6px 0;
}

.title {
  cursor: pointer;
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

.divider {
  margin: 24px 0 0 0;
}

.page-block {
  padding: 20px 0;
}

.page-container {
  width: 100px;
  margin: auto;
}


</style>