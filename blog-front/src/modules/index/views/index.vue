<template>
  <v-layout justify-center>
    <v-flex xs12>
      <v-container grid-list-xl fluid>
        <v-layout row wrap justify-center>
          <v-flex v-for="post in posts" :key="post.postId" xs12 sm10 md9>
            <!-- <v-card tile>
              <v-img
                class="white--text"
                height="200px"
                :lazy-src="post.postAvatar!==''? post.postAvatar:'https://picsum.photos/200/300?random=2'"
              >
                <v-container fill-height fluid light>
                  <v-layout fill-height light>
                    <v-flex xs12 align-end flexbox>
                      <span class="headline">{{post.postTitle}}</span>
                    </v-flex>
                  </v-layout>
                </v-container>
              </v-img>
              <v-card-title>
                <div>
                  <span class="grey--text">浏览 1,6912 次</span>
                  <br>
                  <span>{{post.postAuthor}}</span>
                </div>
              </v-card-title>

              <v-card-text class="text-truncate animated fadeIn">
                <span>{{post.postDesc}}</span>
              </v-card-text>
              <v-divider></v-divider>
              <v-card-actions>
                <v-btn icon>
                  <v-icon>favorite</v-icon>
                </v-btn>
                <v-btn icon>
                  <v-icon>bookmark</v-icon>
                </v-btn>
                <v-btn icon>
                  <v-icon>share</v-icon>
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn color="indigo" outline dark depressed :to="'/post/' + post.postId">阅读全文</v-btn>
              </v-card-actions>
            </v-card> -->
            <post-card :post="post"  class="animated fadeIn"></post-card>
          </v-flex>

          <br>

          <v-flex  xs12 sm10 md9 class="animated fadeIn">
            <br>
            <v-toolbar light color="white">
              <v-spacer></v-spacer>
              <div>
                <v-pagination
                  v-model="current"
                  :length="pages"
                  circle
                  :total-visible="10"
                  prev-icon="arrow_back"
                  next-icon="arrow_forward"
                  width="100%"
                  @next="nextPage"
                  @previous="previousPage"
                ></v-pagination>
              </div>
              <v-spacer></v-spacer>
            </v-toolbar>
          </v-flex>
        </v-layout>
      </v-container>
    </v-flex>
  </v-layout>
</template>

<script>
import { getPosts } from "../api/blogs";
import PostCard from '../components/post/post-card'
export default {
  components: {
    PostCard
  },
  data() {
    return {
      posts: [],
      current: 1,
      size: 12,
      total: 0,
      hasNextPage: false,
      hasPreviousPage: false,
      isFirstPage: false,
      isLastPage: false,
      pages: 0
    };
  },
  beforeRouteUpdate() {
    this.$router.location()
  },
  watch: {
    current: {
      handler(newValue, oldValue) {
        this.$router.push(`/?page=${newValue}`);
      }
    }
  },
  created() {
    if (this.$route.query.page) {
      this.current = parseInt(this.$route.query.page);
    } else {
      this.current = 1;
    }
    this.getAllPosts(this.current, this.size);
  },
  mounted() {},
  methods: {
    getAllPosts(current, size) {
      getPosts(current, size)
        .then(res => {
          this.total = res.data.total;
          this.hasNextPage = res.data.hasNextPage;
          this.hasPreviousPage = res.data.hasPreviousPage;
          this.isFirstPage = res.data.isFirstPage;
          this.isLastPage = res.data.isLastPage;
          this.pages = res.data.pages;
          this.posts = res.data.list;
        })
        .catch(error => {
          console.log(error);
        });
    },
    nextPage() {
      if (this.hasNextPage) {
        this.$router.push(`/?page=${this.current}`);
      } else {
        console.log("已经是最后一页了");
      }
    },
    previousPage() {
      if (this.hasPreviousPage) {
        this.$router.push(`/?page=${this.current}`);
      } else {
        console.log("已经是第一页了");
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.text-xs-center {
  text-align: center;
}
.full-width {
  width: 100%;
}
</style>

