<template>
  <v-navigation-drawer app mini-variant class="indigo" dark>
    <v-toolbar flat class="transparent">
      <v-list class="pa-0">
        <v-list-tile avatar>
          <v-list-tile-avatar>
            <img src="https://randomuser.me/api/portraits/men/85.jpg">
          </v-list-tile-avatar>

          <v-list-tile-content>
            <v-list-tile-title>John Leider</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-toolbar>

    <v-list class="pt-0 text-xs-center">
      <v-divider></v-divider>

      <v-list-tile v-for="item in items" :key="item.title" @click="to(item.link)">
        <v-list-tile-action>
          <v-tooltip right nudge-right>
            <template v-slot:activator="{ on }">
              <v-icon v-on="on">{{item.icon}}</v-icon>
            </template>
            <span>{{item.title}}</span>
          </v-tooltip>
        </v-list-tile-action>
      </v-list-tile>
    </v-list>

    <v-toolbar-items class="logout">
      <v-btn class="white--text" color="indigo" @click="logout">
        <!-- 注销 -->
        <v-icon>exit_to_app</v-icon>
      </v-btn>
    </v-toolbar-items>
  </v-navigation-drawer>
</template>

<script>
export default {
  data() {
    return {
      drawer: true,
      items: [
        { title: "Home", icon: "dashboard", link: "/" },
        { title: "Blogs", icon: "view_list", link: "/blogs" },
        { title: "category", icon: "layers", link: "/category" },
        { title: "tags", icon: "turned_in", link: "/tags" }
      ],
      mini: true,
      bottomNav: "recent"
    };
  },
  methods: {
    to(link) {
      this.$router.push(link);
    },
    logout() {
      this.$store.dispatch("Logout").then(res => {
        console.log(res);
        window.location.href = "/";
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.logout {
  position: absolute;
  bottom: 0;
  height: 56px;
  text-align: center;
  width: 64px;
  word-wrap: break-word;
  padding-left: 0px;
}
</style>

