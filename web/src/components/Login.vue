<template>
     <div>
         <div style="width:500px;">
            <div class="main animated zoomIn" v-cloak>
                <form  v-on:submit.prevent="submit()">
                    <img class="mb-4" src="https://privateproject.oss-cn-hangzhou.aliyuncs.com/20211120/use.png" alt="" width="250" height="250">
                    <h1 class="h3 mb-3 font-weight-normal"></h1>
                    <label class="sr-only">Username</label>
                    <div style="margin-top: 15px;">
                        <el-input placeholder="Please enter content" v-model="user.username" class="input-with-select">
                            <el-select v-model="select" slot="prepend" placeholder="pls chose">
                                <el-option label="Email" value="1"></el-option>
                                <el-option label="phonenumber" value="2"></el-option>
                            </el-select>
                        </el-input>
                        </div>
                    <label class="sr-only">Password</label>
                    <input type="password" class="form-control" placeholder="password" required="" id="password" name="password"  v-model="user.password">
                    <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"  style="width:300px;height: 50px;"  v-bind:hidden="formbutton">login</button> 

                    <button class="btn btn-primary" type="button" disabled v-bind:hidden="!formbutton" style="width:300px;height: 50px;"> 
                        <span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>
                            Signing in, please wait......
                    </button>
                    No account yet?<a href="http://127.0.0.1:8080/pu/user/registered">Click me to register</a> <br>
                    forget the password?<a href="http://127.0.0.1:8080/pu/user/retrievepassword">Click to retrieve my password</a>
                </form>
            </div>
         </div>
     </div>
</template>


<script>
    export default {
        name: "login",
 
        data() {
            return{
                formbutton: false,
                select: '1',
                user:{
                    username: null,
                    password: null,
                    ip: '',
                    state: '',
                },
            }
        },

        mounted(){
     
        },

        methods: {
            //Submit login
            submit(){
                this.formbutton = true;
                this.$axios.post("http://127.0.0.1:8081/user/p/login?select="+this.select,this.user)
                .then(function(response){
                    switch(response.data.code){
                        //sign in suceesfully
                        case "0x0000":{
                            //Save login information in cookie
                            setCookie ("token",response.data.jwt);
                             alert("sign in suceesfully!");
                            window.location.href="http://127.0.0.1:8080/";
                            break;
                         
                        }
                        case "0x0001":{
                            alert("Incorrect account or password");
                              window.location.href="http://127.0.0.1:8080/pu/user/login";
                            break;
                        }
                    }
                }).catch(res =>{
                    alert("Server is busy, trying later");
                      window.location.href="http://127.0.0.1:8080/pu/user/login";
                })
            },
        }
    }
</script>

<style scoped>
  @import 'https://dazuiblog.oss-cn-hangzhou.aliyuncs.com/webstatic/dazuiblog/userlogin.css';
  @import 'https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css'; 
</style>
 