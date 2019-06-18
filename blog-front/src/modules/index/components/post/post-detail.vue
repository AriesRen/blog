<template>
  <v-container grid-list-xl>
    <v-layout align-center row wrap justify-center>
      <v-flex xs12 sm10>
        <v-card>
          <!-- <v-img
            class="white--text"
            height="200px"
            :aspect-ratio="1"
            :src="post.postAvatar!==''? post.postAvatar:'https://picsum.photos/800/200?random=2'"
          > -->
            <v-container fill-height fluid>
              <v-layout fill-height>
                <v-flex xs12 align-end flexbox>
                  <span class="headline">{{post.postTitle}}</span>
                </v-flex>
              </v-layout>
            </v-container>
          <!-- </v-img> -->
          <v-card-title>
            <div>
              <span class="grey--text">
                <v-icon small left class="mr-1">event</v-icon>
                发表于 {{post.createdTime | formatTime }} {{post.createdTime | parseTime }}
              </span>
              <span>&nbsp;&nbsp;</span>
              <span class="grey--text">
                <v-icon small left class="mr-1">event</v-icon>
                更新于 {{post.updatedTime | formatTime }}
              </span>
              <span>&nbsp;&nbsp;</span>
              <span class="grey--text">
                <v-icon small left class="mr-1">event</v-icon>
                分类于 {{ category.name }}
              </span>
              <br>
              <!-- <span>{{ user.nickname }}</span>
              <br>
              <span>Whitsunday Island, Whitsunday Islands</span> -->
            </div>
          </v-card-title>
          <v-card-text class="body-2">
            <p>{{post.postDesc}}</p>
            <p>{{post.postContent}}</p>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn flat color="orange">Share</v-btn>
            <v-spacer></v-spacer>
            <v-btn flat color="orange">Explore</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
      <v-flex xs12 sm10>
        <v-card>
          <v-card-actions>
            <v-btn color="white" depressed>上一篇 数据结构与算法（一） 排序</v-btn>
            <v-spacer></v-spacer>
            <v-btn color="white" depressed>下一篇 数据结构与算法（一） 排序</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
      <v-flex xs12 sm10>
        <v-card>
          <v-card-text>
            <p>asdfadsfasdfadsfadsfadsfasdfadsfasd</p>
          </v-card-text>
          <v-card-actions>
            <v-btn color="white" depressed>提交</v-btn>
            <v-spacer></v-spacer>
            <v-btn color="white" depressed>回答</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
import { getPostById, getTagsByPostId,getCategoryByPostId} from "../../api/blogs";
import { getCategoryById } from '../../api/category'
import { getUserById} from '../../api/user'
export default {
  mounted() {
    const postId = this.$route.params.id
    this.getPost(postId)
    // this.getCategory(postId)
    this.getTags(postId)
    window.addEventListener('scroll', this.handlerScroll)
  },
  destroyed() {
    window.removeEventListener('scroll', this.handlerScroll)
  },
  data() {
    return {
      postId: '',
      post: {},
      category: {},
      tags: [],
      user: {}
    };
  },
  methods: {
    getCategory(categoryId) {
      getCategoryById(categoryId).then(res => {
        console.log(res);
        this.category = res.data;
      });
    },
    getPost(postId) {
      getPostById(postId).then(res => {
        if(res.code === 200) {
          this.post = res.data
          this.getCategory(this.post.categoryId)
          this.getUser(this.post.userId)
        } else {
          console.log("该文章不存在")
          this.$router.push('/404')
        }
      }).catch(error => {
        console.log(error)
      })
    },
    getTags(postId) {
      getTagsByPostId(postId).then(res => {
        this.tags = res.data
      })
    },
    getUser(userId) {
      getUserById(userId).then(res => {
        this.user = res.data
      })
    },
    toCategory() {
      this.$router.push(`/category/${category.name}`)
    },
    toTag(name) {
      this.$router.push(`/tag/${name}`)
    },
    toUser(name) {
      this.$router.push(`/user/{${name}}`)
    },
    handlerScroll() {
      var _ = this
      var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      var documentScrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight
      var windowHeight = document.documentElement || document.body.clientHeight
      if (scrollTop > 80) {
        this.$store.dispatch('ShowToobarTitle', _.post.postTitle)
      } else {
        this.$store.dispatch('HiddenToobarTitle')
      }
    }
  }
};
</script>


<style lang="scss" scoped>
.body-2 {
  text-align: justify;
  vertical-align: middle;
  overflow-wrap: break-word;
  font-family: "PingFang SC", "Microsoft YaHei", sans-serif;
  font-size: 20rem;
  line-height: 2.5;
  color: #222;
}

.hljs {
  border: 0;
  font-size: 12px;
  display: block;
  padding: 1px;
  margin: 0;
  width: 100%;
  font-weight: 200;
  white-space: pre-wrap;
}
.hljs ol {
  list-style: decimal;
  margin: 0px 0px 0 40px !important;
  padding: 0px;
}
.hljs ol li {
  list-style: decimal-leading-zero;
  border-left: 1px solid #ddd !important;
  padding: 5px !important;
  margin: 0 !important;
  white-space: pre;
}
</style>
