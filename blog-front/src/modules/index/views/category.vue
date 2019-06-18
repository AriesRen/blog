<template>
  <v-container grid-list-xl>
    <v-layout align-center row wrap>
      <v-flex xs12 sm10 offset-sm1>
        <v-card>
          <v-card-text class="body-2">
           
            <div class="pt-2 text-xs-center animated fadeInUp">
              <div class="headline font-weight-bold black--text">分类</div>
              <div class="body-2 black--text font-weight-bold pt-2">总共有 {{ total }}个分类</div>
            </div>
            
            <el-timeline class="pt-5">
              
              <el-timeline-item
                v-for="(category, index) in categories"
                :key="index"
                color="#0bbd87"
                size="large"
                class="subheading black--text font-weight-medium  animated fadeIn"
              >
                <a class="a_link" @click="toCategory(category.name)">{{category.name}}</a> (11)
                <!-- <div class="subheading black--text font-weight-bold">
                  <a @click="toCategory(category.name)">{{category.name}}</a>  {{category.remark}}
                </div>
                <div class="caption">{{category.remark}}</div>-->
                <!-- <v-layout row wrap class="subheading black--text font-weight-medium">
                  <v-flex xs6 class="pt-0">
                    <a class="a_link" @click="toCategory(category.name)">{{category.name}}</a> (11)
                  </v-flex>
                 
                </v-layout> -->
              </el-timeline-item>
             
            </el-timeline>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { getCategories } from "../api/category";
export default {
  data() {
    return {
      categories: [],
      current: 1,
      size: 20,
      total: 0,
      hasNextPage: false,
      hasPreviousPage: false,
      isFirstPage: false,
      isLastPage: false,
      pages: 0,
      priviousDisable: false,
      nextDisable: false
    };
  },
  watch: {
    current: {
      handler(val, oldval) {
        this.getAllCategory(val);
      }
    }
  },
  created() {
    this.getAllCategory(this.current, this.size);
  },
  methods: {
    getAllCategory(current, size) {
      getCategories({ current, size }).then(res => {
        this.categories = res.data.list;
        this.total = res.data.total;
        this.hasNextPage = res.data.hasNextPage;
        this.nextDisable = !res.data.hasNextPage;
        this.hasPreviousPage = res.data.hasPreviousPage;
        this.priviousDisable = !res.data.hasPreviousPage;
        this.isFirstPage = res.data.isFirstPage;
        this.isLastPage = res.isLastPage;
        this.current = res.data.pageNum;
        this.pages = res.data.pages;
      });
    },
    toCategory(name) {
      this.$router.push(`/category/${name}`);
    },
    privious() {
      if (this.hasPreviousPage) {
        this.current -= 1;
      }
    },
    next() {
      if (this.hasNextPage) {
        this.current += 1;
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.body-2 {
  font-size: 15px !important;
}
.a_link {
  color: #000;
  padding-bottom: 3px;
  text-decoration: none;
  position: relative;
}
.a_link::before {
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
.a_link:hover::before {
  transform: scale(1);
}
.a_link:hover {
  color: #5a33a1;
  // border-bottom: 1px solid #5a33a1;
}
</style>
