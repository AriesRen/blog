<template>
  <v-layout row pb-2 justify-center>
    <v-flex xs4 class="mt-2 pt-5">
      <v-card class="card--flex-toolbar pt-3" transition="scale-transition">
        <v-card-title>
          <el-divider>
            <h2>用 户 注 册</h2>
          </el-divider>
        </v-card-title>

        <v-card-text class="pb-5">
          <el-form :model="loginForm" :rules="rules" ref="loginForm">
            <el-form-item prop="nickname" class="mb-4">
              <el-input
                prefix-icon="el-icon-user-solid"
                v-model="loginForm.nickname"
                placeholder=" 昵称"
              ></el-input>
            </el-form-item>
            <el-form-item prop="email" class="mb-4">
              <el-input
                prefix-icon="el-icon-user-solid"
                v-model="loginForm.email"
                placeholder=" 注册邮箱/手机号"
              ></el-input>
            </el-form-item>

            <el-form-item class="mb-3">
              <div id="captcha">
                <p id="wait" v-bind:class="{hide: !wait, show: wait}">正在加载验证码......</p>
              </div>
            </el-form-item>

            <el-form-item class="mb-0 mt-0">
              <p class="pb-0 mb-0">
                已有账号
                <a @click="login">去登录</a>
              </p>
            </el-form-item>
            <v-btn block color="indigo" dark depressed @click="submit('loginForm')" large>
              <v-icon size="18" left>send</v-icon>注 册
            </v-btn>
          </el-form>
        </v-card-text>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import { getCaptcha, checkCaptcha } from "../api/auth";

export default {
  data() {
    return {
      loginForm: {
        email: "",
        nickname: ""
      },
      captchaStatus: false,
      wait: true,
      result: {},
      captchaObj: {},
      rules: {
        email: [
          {
            required: true,
            message: "请输入您注册的手机号或邮箱",
            trigger: "blur"
          },
          { min: 3, max: 20, message: "长度为3-20个字符", trigger: "blur" }
        ],
        nickname: [
          { required: true, message: "请输入您的昵称", trigger: "blur" },
          { min: 3, max: 20, message: "长度为3-20个字符", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getInitGeeTest();
  },
  methods: {
    getInitGeeTest() {
      getCaptcha()
        .then(res => {
          window.initGeetest(
            {
              gt: res.data.gt,
              challenge: res.data.challenge,
              offline: !res.data.success,
              new_captcha: true,
              product: "float",
              width: "100%"
            },
            captchaObj => {
              this.captchaObj = captchaObj;
              captchaObj.appendTo("#captcha");

              captchaObj.onReady(() => {
                this.wait = false;
              });
              captchaObj.onSuccess(() => {
                this.result = captchaObj.getValidate();

                checkCaptcha({
                  geetest_challenge: this.result.geetest_challenge,
                  geetest_validate: this.result.geetest_validate,
                  geetest_seccode: this.result.geetest_seccode
                }).then(res => {
                  console.log(res);
                  if (res.data === true) {
                    this.captchaStatus = true;
                  } else {
                    this.captchaStatus = false;
                    captchaObj.reset();
                  }
                });
              });
              captchaObj.onError(error => {
                this.$Message.error("出错啦： " + error);
              });
            }
          );
        })
        .catch(error => {
          console.log(error);
        });
    },
    submit(loginForm) {
      this.$refs[loginForm].validate(valid => {
        if (valid) {
          if (this.captchaStatus === true) {
            this.$router.push(`/forgetmail?email=${this.loginForm.email}`);
          } else {
            console.log("请进行人机验证，滑动验证码");
            this.$message({
              message: "请进行人机验证，滑动验证码",
              type: "error"
            });
            return false;
          }
        } else {
          console.log("error submit!!");
          // this.$refs[loginForm].resetFields();
          this.captchaStatus = false;
          this.captchaObj.reset();
          return false;
        }
      });
    },
    clear() {},
    login() {
      this.$router.push("/login");
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
