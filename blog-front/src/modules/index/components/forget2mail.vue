<template>
  <v-layout row pb-2 justify-center>
    <v-flex xs4 class="mt-5 pt-5" transition="slide-x-reverse-transition">
      <v-card class="card--flex-toolbar pt-3" transition="slide-x-reverse-transition">
        <v-card-title>
          <el-divider>
            <h2>验 证 邮 箱</h2>
          </el-divider>
        </v-card-title>

        <v-card-text class="pb-2">
          <p class="subheading text-xs-center">亲爱的用户，邮件已发送完成，请登录邮箱进行验证</p>

          <p class="body-2 mt-4 black--text text-xs-center">
            已发送邮件至：
            <span class="font-weight-bold">{{email}} </span>
            <a @click="forget"> 修改邮箱</a>
          </p>

          <v-btn block color="indigo" dark depressed large class="mt-4">
            前往邮箱进行验证
          </v-btn>

          <p class="mt-4 mb-0 caption primary--text text-dark-1">
              如果您没有收到邮件， 可能是：
          </p>
          <p class="caption mb-0">
              1、邮件是否被劫持到垃圾邮箱
          </p>
          <p class="caption ">
              2、请检查邮件地址是否正确
          </p>
        </v-card-text>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>

export default {
  data() {
    return {
      email: ''
    };
  },
  created() {
    this.email = this.$route.query.email
  },
  methods: {
    forget() {
        this.$router.push("/forget")
    }
  }
};
</script>

<style lang="scss" scoped>
#captcha {
  width: 100%;
  margin: 0 auto;
  display: inline-block;
  // border: 1px solid #dcdfe6;
  border-radius: 4px;
  height: 40px;
}
.show {
  display: block;
}
.hide {
  display: none;
}
#notice {
  color: red;
}
#wait {
  text-align: left;
  color: #666;
  margin: 0;
}
</style>
