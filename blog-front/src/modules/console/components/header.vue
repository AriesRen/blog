<template>
  <div>
    <v-toolbar color="white" light fixed app>
      <v-toolbar-side-icon></v-toolbar-side-icon>

      <v-spacer></v-spacer>

      <!-- <v-spacer></v-spacer> -->
      <!-- <v-btn icon>
        <v-icon>search</v-icon>
      </v-btn>-->
      <v-btn outline color="indigo" :to="'/login'">无名万物</v-btn>
    </v-toolbar>
  </div>
</template>

<script>
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
  watch: {
   
  },
  methods: {
    toIndex() {
      this.$router.push("/")
    }
  }
};
</script>

<style lang="scss" scoped>
.logo-title {
  cursor: pointer;
}
</style>
