<template>
  <v-layout row pb-2 justify-center>
    <v-flex xs4>
      <br>
      <v-card class="card--flex-toolbar">
        <v-card-title>
          <el-divider>
            <h2>用 户 登 录</h2>
          </el-divider>
        </v-card-title>

        <v-card-text>
          <el-form :model="loginForm" :rules="rules" ref="loginForm">
            <el-form-item prop="username" class="mb-4">
              <el-input
                prefix-icon="el-icon-user-solid"
                v-model="loginForm.username"
                placeholder=" 用户名/邮箱/手机号"
              ></el-input>
            </el-form-item>
            <el-form-item prop="password" class="mb-4">
              <el-input
                prefix-icon="el-icon-lock"
                v-model="loginForm.password"
                type="password"
                placeholder=" 密码"
              >
                <el-button slot="append" @click="forget">忘记密码</el-button>
              </el-input>
            </el-form-item>

            <el-form-item class="mb-3">
              <div id="captcha">
                <p id="wait" v-bind:class="{hide: !wait, show: wait}">正在加载验证码......</p>
              </div>
            </el-form-item>
            <el-form-item class="mb-0 mt-0">
                <p class="pb-0 mb-0">还没有账号 <a @click="toRegister">去注册</a></p>
            </el-form-item>

            <v-btn block color="indigo" dark depressed @click="submit('loginForm')" large>
              <v-icon size="18" left>fa fa-sign-in-alt</v-icon>登 录
            </v-btn>
          </el-form>
        
        </v-card-text>
        <el-divider>或者用以下方式登录</el-divider>
        <v-card-actions class="pt-0 mt-0 pb-3">
          <v-layout row>
            <v-flex xs12 sm4 text-xs-center>
              <div>
                <v-btn flat icon color="black">
                  <v-icon>fab fa-github</v-icon>
                </v-btn>
              </div>
            </v-flex>
            <v-flex xs12 sm4 text-xs-center>
              <div>
                <v-btn flat icon color="primary">
                  <v-icon>fab fa-google</v-icon>
                </v-btn>
              </div>
            </v-flex>
            <v-flex xs12 sm4 text-xs-center>
              <div>
                <v-btn flat icon color="green">
                  <v-icon>fab fa-weixin</v-icon>
                </v-btn>
              </div>
            </v-flex>
            <v-flex xs12 sm4 text-xs-center>
              <div>
                <v-btn flat icon color="primary">
                  <v-icon>fab fa-qq</v-icon>
                </v-btn>
              </div>
            </v-flex>
          </v-layout>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
import { getCaptcha, checkCaptcha } from "../api/auth";

export default {
  data() {
    return {
      checkbox: "",
      labelPosition: "right",
      loginForm: {
        username: "",
        password: "",
        remeber: false
      },
      captchaStatus: false,
      wait: true,
      result: {},
      captchaObj: {},
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 3, max: 20, message: "长度为3-20个字符", trigger: "blur" }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      }
    };
  },
  mounted() {
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
            this.$store.dispatch("Login", this.loginForm).then(res => {
              if (res.code === 200) {
                this.$message({
                  message: "登录成功",
                  type: "success",
                  duration: 1000,
                  onClose: () => {
                    this.$router.push("/");
                  }
                });
              } else {
                this.$message({
                  message: res.msg,
                  type: "error"
                });
              }
            });
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
    forget() {
      this.$router.push("/forget")
    },
    toRegister() {
      this.$router.push("/register")
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
