<template>
  <div id="register" v-title data-title="注册 - For Fun">
    <!--<video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop">
          <source src="../../static/vedio/sea.mp4" type="video/mp4">
      </video>-->

    <div class="me-login-box me-login-box-radius">
      <h1>注册</h1>

      <el-form :model="userForm" :rules="rules" ref="UserInfoForm">
        <el-form-item prop="username">
          <el-input placeholder="用户名" v-model="userForm.username"></el-input>
        </el-form-item>

        <el-form-item prop="nickName">
          <el-input placeholder="昵称" v-model="userForm.nickName"></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input placeholder="密码" v-model="userForm.password"></el-input>
        </el-form-item>

        <el-form-item prop="email">
          <el-input placeholder="邮箱" v-model="userForm.email"></el-input>
        </el-form-item>

        <el-form-item prop="verify">
          <el-input placeholder="验证码" v-model="verify" class="input-with-select">
            <el-button slot="append" :disabled="show" @click="getCode()">发送验证码</el-button>
          </el-input>
        </el-form-item>

        <el-form-item size="small" class="me-login-button">
          <el-button type="primary" @click.native.prevent="register('userForm')">注册</el-button>
        </el-form-item>
      </el-form>

    </div>
  </div>
</template>

<script>
  import {register,getCode} from '@/api/login'

  export default {
    name: 'Register',
    data() {
      return {
        userForm: {
          username: '',
          nickName: '',
          password: '',
          email:'',
        },
        show:false,
        count: "",
        timer: null,
        verify:'',
        rules: {
          username: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          nickName: [
            {required: true, message: '请输入昵称', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {max: 10, message: '不能大于10个字符', trigger: 'blur'}
          ],
          email: [
            {required: true, message: '请输入邮箱', trigger: 'blur'},
          ],
          verify: [
            {required: true, message: '请输入验证码', trigger: 'blur'},
            {max: 6, message: '不符合验证码格式', trigger: 'blur'}
          ],
        }

      }
    },
    methods: {
      async register() {
        this.$refs.UserInfoForm.validate(async valid => {
          if (valid) {
            await register(this.userForm, this.verify)
          }
        })
      },
      async getCode() {
        if (this.checkPhone() == false) {
          return false;
        } else {
          const TIME_COUNT = 60; //更改倒计时时间
          if (!this.timer) {
            this.count = TIME_COUNT;
            this.show = true;
            this.timer = setInterval(() => {
              if (this.count > 0 && this.count <= TIME_COUNT) {
                this.count--;
              } else {
                this.show = false;
                clearInterval(this.timer); // 清除定时器
                this.timer = null;
              }
            }, 1000);
          }
        }
        await getCode(this.userForm.email)
      },
      checkPhone() {
        let email = this.userForm.email;
        if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(email)) {
          this.$message.error("请填写正确的邮箱");
          return false;
        }
      }
    }
  }
</script>

<style scoped>
  #login {
    min-width: 100%;
    min-height: 100%;
  }

  .me-video-player {
    background-color: transparent;
    width: 100%;
    height: 100%;
    object-fit: fill;
    display: block;
    position: absolute;
    left: 0;
    z-index: 0;
    top: 0;
  }

  .me-login-box {
    position: absolute;
    width: 300px;
    height: 450px;
    background-color: white;
    margin-top: 150px;
    margin-left: -180px;
    left: 50%;
    padding: 30px;
  }

  .me-login-box-radius {
    border-radius: 10px;
    box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
  }

  .me-login-box h1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
    vertical-align: middle;
  }

  .me-login-button {
    text-align: center;
  }

  .me-login-button button {
    width: 100%;
  }

</style>
