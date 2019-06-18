<template>
  <!-- <v-container grid-list-xl> -->
  <v-layout>
    <v-flex>
      <v-card tile>
        <v-responsive :aspect-ratio="4/1">
          <v-card-title>
            <v-layout row align-center wrap class="pt-2">
              <v-flex xs12 class="text-xs-center">
                <span class="headline a-link" @click="toPost">{{post.postTitle}}</span>
              </v-flex>

              <v-flex xs12 class="text-xs-center caption">
                <span class="mx-2">作者 {{post.postAuthor}}</span>
                |
                <span
                  class="mx-2"
                >发表于 {{post.createdTime | parseTime('{y}-{m}-{d}') }}</span>
                |
                <span class="mx-2">
                  分类于
                  <a @click="toCategory" class="grey--text">{{category.name}}</a>
                </span>
                |
                <span class="mx-2" v-if="tags.length!==0">
                  标签
                  <a class="grey--text" v-for="(tag,index) in tags" :key="index">
                    <span v-if="index+1 < tags.length">{{tag.name}}, </span>
                    <span v-else>{{tag.name}}</span>
                  </a>
                </span>
              </v-flex>
            </v-layout>
          </v-card-title>

          <v-card-text style="line-height: 30px;font-size:16px;" class="pb-0">
            <span v-html="contentDesc"></span>
          </v-card-text>

          <v-card-actions>
            <v-flex xs12 class="text-xs-center">
              <v-btn
                color="indigo"
                active-class="button-active"
                outline
                dark
                depressed
                :to="'/post/' + post.postId"
                class="text-xs-center"
              >阅读全文</v-btn>
            </v-flex>
          </v-card-actions>
        </v-responsive>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import {
  getPostById,
  getTagsByPostId,
  getCategoryByPostId
} from "../../api/blogs";
import { getCategoryById } from "../../api/category";
import { getUserById } from "../../api/user";
import marked from "marked";
export default {
  props: {
    post: {
      postId: Number,
      postTitle: String,
      postContent: String,
      postAuthor: String,
      postAvatar: String,
      postImages: [],
      postDesc: String,
      postStatus: Number,
      userId: Number,
      categoryId: Number,
      createdTime: Number,
      updatedTime: Number,
      createdUser: String,
      updatedUser: String
    }
  },
  computed: {
    contentDesc() {
      let content = this.post.postContent;
      if (content.length < 300) {
        return marked(content);
      } else {
        return marked(this.post.postContent.substring(0, 300) + "...");
      }
    }
  },
  mounted() {
    this.getCategory(this.post.categoryId);
    this.getTags(this.post.postId);
    this.getUser(this.post.userId);
  },
  data() {
    return {
      category: {},
      user: {},
      tags: []
    };
  },
  methods: {
    getCategory(id) {
      getCategoryById(id).then(res => {
        this.category = res.data;
      });
    },
    getTags(postId) {
      getTagsByPostId(postId).then(res => {
        this.tags = res.data;
      });
    },
    getUser(userId) {
      getUserById(userId).then(res => {
        this.user = res.data;
      });
    },
    toPost() {
      this.$router.push(`/post/${this.post.postId}`);
    },
    toCategory() {
      this.$router.push(`/category/${this.category.name}`)
    }
  }
};
</script>

<style lang="scss" scoped>
.a-link {
  color: #000;
  padding-bottom: 3px;
  text-decoration: none;
  position: relative;
  cursor: pointer;
}
.a-link::before {
  content: "";
  position: absolute;
  left: 0;
  bottom: -3px;
  height: 2px;
  width: 100%;
  background: #5a33a1;
  transform: scale(0);
  transition: all 0.2s ease-out;
}
.a-link:hover::before {
  transform: scale(1);
}
.a-link:hover {
  color: #5a33a1;
  // border-bottom: 1px solid #5a33a1;
}
</style>
