<template>
  <v-container class="pt-0 pl-0 pr-0 mt-0">
    <v-layout align-center row wrap>
      <v-flex xs12 sm10 offset-sm1>
        <v-card>
          <v-card-title>
            <span class="title font-weight-medium text-capitalize">
              <v-icon>label</v-icon>
              分类: {{category}}
            </span>
          </v-card-title>
        </v-card>
      </v-flex>
      <v-flex xs12 sm10 offset-sm1>
        <v-container grid-list-lg class="pt-0 pl-0 pr-0 mt-3">
          <v-layout row wrap grid-list-sm>
            <v-flex v-for="n in 10" :key="n" xs12 sm6 md4>
              <v-card tile hover>
                <v-img
                  class="white--text"
                  height="200px"
                  :src="'https://picsum.photos/200/300?images='+n"
                >
                  <template v-slot:placeholder>
                    <v-layout fill-height align-center justify-center ma-0>
                      <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                    </v-layout>
                  </template>
                  <v-container fill-height fluid light>
                    <v-layout fill-height light>
                      <v-flex xs12 align-end flexbox>
                        <span class="headline">Top 10 Australian beaches</span>
                      </v-flex>
                    </v-layout>
                  </v-container>
                </v-img>
                <v-card-title>
                  <div>
                    <span class="grey--text">浏览 1,6912 次</span>
                    <br>
                    <span>无名万物</span>
                  </div>
                </v-card-title>

                <v-card-text class="text-truncate">
                  <span>Top 10 Australian beaches</span>
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
                  <v-btn color="indigo" outline dark depressed :to="'/post/1'">阅读全文</v-btn>
                </v-card-actions>
              </v-card>
            </v-flex>
          </v-layout>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      category: ""
    };
  },
  created() {
    this.category = this.$route.params.category;
  }
};
</script>

