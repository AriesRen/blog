<template>
  <div id="app" v-wechat-title="$route.meta.title">
    <v-app>
      <router-view name="header"/>
      <v-content>
        <v-container fluid>
          <router-view/>
        </v-container>
      </v-content>
      <router-view name="footer"/>
    </v-app>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  }
};
</script>


<style lang="scss">
#app {
  font-family: 'Lato', "PingFang SC", "Microsoft YaHei", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>

<style lang="scss">
body::-webkit-scrollbar {
  width: 5px;
  height: 1px;
}
body::-webkit-scrollbar-thumb {
  border-radius: 10px;
  box-shadow: inset 0 0 5px rgba(0, 0, 80, 0.2);
  background: blue;
}
body::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  border-radius: 10px;
  background: #ededed;
}
</style>

