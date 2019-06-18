<template>
  <div>
    <v-toolbar color="white" light fixed app>
      <v-toolbar-side-icon></v-toolbar-side-icon>
      <v-toolbar-title class="logo-title" @click="toIndex">{{title}}</v-toolbar-title>

      <div class="ml-2" v-show="!hidden">
        <v-btn color="white" class="hidden-sm-and-down" depressed :to="'/'">首页</v-btn>
        <v-btn color="white" class="hidden-sm-and-down" depressed :to="'/category'">分类</v-btn>
        <v-btn color="white" class="hidden-sm-and-down" depressed :to="'/tags'">标签</v-btn>
        <v-btn color="white" class="hidden-sm-and-down" depressed :to="'/about'">关于</v-btn>
        <v-btn color="white" class="hidden-sm-and-down" depressed :to="'/archive'">归档</v-btn>
        <v-btn color="white" class="hidden-sm-and-down" depressed :to="'/friends'">友链</v-btn>
      </div>
      <div class="ml-4" v-show="hidden" :class="{'animated': hidden, 'fadeInUp': hidden }">
        <h2 class="text-truncate">{{toobarTitle}}</h2>
      </div>
      <v-spacer></v-spacer>

      <span v-if="username===''">
        <v-btn outline color="indigo" class="hidden-sm-and-down" :to="'/login'">登录</v-btn>
        <v-btn outline color="indigo" class="hidden-sm-and-down" :to="'/register'">注册</v-btn>
      </span>
      <span v-else>
        <v-menu :close-on-content-click="false" :nudge-width="150" offset-y open-on-hover>
          <template v-slot:activator="{ on }">
            <v-layout align-center row spacer v-on="on" fill-height>
              <v-flex xs6>
                <v-avatar size="30px">
                  <img
                    src="https://avatars0.githubusercontent.com/u/9064066?v=4&s=460"
                    alt="Avatar"
                  >
                </v-avatar>
              </v-flex>
              <v-flex hidden-xs-only class="ml-2">
                <strong class="text-no-wrap">无名万物</strong>
              </v-flex>
            </v-layout>
          </template>
          <v-card>
            <v-list>
              <v-list-tile avatar>
                <v-list-tile-avatar>
                  <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John">
                </v-list-tile-avatar>

                <v-list-tile-content>
                  <v-list-tile-title @click="window.location.href='console.html'">John Leider</v-list-tile-title>
                  <v-list-tile-sub-title>Founder of Vuetify.js</v-list-tile-sub-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list>

            <v-divider></v-divider>

            <v-list class="pt-0 pb-0">
              <v-list-tile @click="goConsole">
                <v-list-tile-action>
                  <v-icon>people</v-icon>
                </v-list-tile-action>
                <v-list-tile-title>个人中心</v-list-tile-title>
              </v-list-tile>

              <v-list-tile @click="logout">
                <v-list-tile-action>
                  <v-icon>exit_to_app</v-icon>
                </v-list-tile-action>
                <v-list-tile-title>退出</v-list-tile-title>
              </v-list-tile>
            </v-list>
          </v-card>
        </v-menu>
      </span>
    </v-toolbar>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      title: "Aries Blog",
      isLoading: false,
      items: [],
      model: null,
      search: null,
      searching: true
    };
  },
  computed: {
    ...mapGetters({
      username: "username",
      nickname: "nickname",
      toobarTitle: "toobarTitle",
      hidden: 'toobarShow'
    })
  },
  watch: {},
  methods: {
    toIndex() {
      this.$router.push("/");
    },
    goConsole() {
      window.location.href = "console.html";
    },
    async logout() {
      const res = await this.$store.dispatch("Logout");
      console.log(res);
      // this.$store.dispatch("Logout").then(res => {
      //   console.log(res)
      // })
    }
  }
};
</script>

<style lang="scss" scoped>
.logo-title {
  cursor: pointer;
}
</style>
