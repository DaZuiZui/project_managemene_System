<template>
    <div>
        <div style="margin: auto;width: 600px" id="enrolldate" class="animated zoomIn" v-cloak>
            <div style="margin: auto;width: 250px">
                <img class="mb-4" src="https://privateproject.oss-cn-hangzhou.aliyuncs.com/20211120/use.png" alt="https://privateproject.oss-cn-hangzhou.aliyuncs.com/20211120/use.png" width="250" height="250">
            </div>
            <form v-on:submit.prevent="submit()">
       <div class="form-group">
                    <label for="email">Email</label>  <span style="color:#FF0000;font-size:14px">{{this.userdataerror.emailerror}}</span>
                    <input type="email" class="form-control" id="email" name="email" v-model="userdata.email">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="password">password</label>
                         <span style="color:#FF0000;font-size:12px">{{this.userdataerror.passworderror}}</span>
                        <input type="password" class="form-control" id="password" name="password" v-model="userdata.password" @keyup="getFullname">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="erpassword">Confirm Password</label>   <span style="color:#FF0000;font-size:14px" >{{this.userdataerror.eqpassworderror}}</span>
                        <input type="password" class="form-control" id="erpassword" name="eqpassword" v-model="userdata.eqpassword"  @keyup="getFullname">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name">name</label>    <span style="color:#FF0000;font-size:14px">{{userdataerror.nameerror}}</span>
                    <input type="text" class="form-control" id="name" name="name" v-model="userdata.name">
                </div>
           
                <div class="form-group">
                    <label for="username">phone number</label>    <span style="color:#FF0000;font-size:14px">{{userdataerror.phoneNumbererror}}</span>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" v-model="userdata.phoneNumber">
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit"  id="sub"  v-bind:hidden="formbutton" style="width:600px;height: 50px;"> register</button> 
                <button class="btn btn-primary" type="button" disabled v-bind:hidden="!formbutton" style="width:600px;height: 50px;"> 
                    <span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>
                    Registering, please wait......
                </button>
                <div style="margin: auto;width: 280px">
                    I already have an account<a href="http://127.0.0.1:8080/pu/user/login">Go to login</a>
                </div>
            </form>
            <div style="margin: auto;width: 204px">
            </div>
        </div>
    </div>
</template>

<script>
    import Axios from 'axios'
    export default {
        name: "registered",

        data() {
            return{
                    //data
                    userdata: {
                        username: null,
                        password: null,
                        eqpassword: null,
                        name: null,
                        email: null,
                        phoneNumber: null,
                    },
                    //Error message prompt data
                    userdataerror: {
                        eqpassworderror1: null,
                        usernameerror: null,
                        passworderror: null,
                        eqpassworderror: null,
                        nameerror: null,
                        emailerror: null,
                        phoneNumbererror: null,
                    },
                    //Whether the user data is legally verified
                    userformif: true,
 
                    //Set the button to be non-clickable
                    formbutton: false,
                }
        },

        methods: {
            getFullname(){
                var str = this.userdata.password
                var reg1 = /[!@#$%^&*()_?<>{}]{1}/;
                var reg2 = /([a-zA-Z0-9!@#$%^&*()_?<>{}]){4,30}/;
                var reg3 = /[a-zA-Z]+/;
                var reg4 = /[0-9]+/;
                if(reg1.test(str) && reg2.test(str) && reg3.test(str) && reg4.test(str)){
                    this.userdataerror.passworderror ="";
                }else if(!reg1.test(str)){
                    this.userdataerror.passworderror ="Must contain a special character";
                }else if(!reg2.test(str)){
                    this.userdataerror.passworderror ="Length is 4-30 bits";
                }else if(!reg3.test(str)){
                    this.userdataerror.passworderror ="Must contain letters";
                }else if(!reg4.test(str)){
                    this.userdataerror.passworderror ="Need to contain numbers";
                }
            },
            //register
            submit() {
                alert("login");
                  //Update verification judged to be true
                    this.userformif = true;
                    this.userdata.username = this.userdata.email;
                   //Determine whether the two passwords entered are the same and not empty
                   if (this.userdata.eqpassword != this.userdata.password || this.userdata.password == null || this.userdata.eqpassword == null || this.userdata.eqpassword.length < 6 || this.userdata.eqpassword.length > 30){
                       if(this.userdata.eqpassword != this.userdata.password){
                            //this.userdataerror.eqpassworderror = "The length of the account must be between 4 and 20 digits and cannot be empty";
                            this.userdataerror.eqpassworderror1 = " ";
                            this.userformif = false;
                       }else{
                            //this.userdataerror.eqpassworderror = "the two passwords must be the same length in 6-30";
                            this.userdataerror.eqpassworderror1 = " ";
                            this.userformif = false;
                       }
                   }else{
                       this.userdataerror.eqpassworderror = "";
                       this.userdataerror.eqpassworderror1 = "";
                   }

                   //Determine whether the name is legal
                   if (this.userdata.name == null){
                       //this.userdataerror.nameerror = "The name cannot be empty";
                       this.userformif = false;
                   }else{
                        if(this.userdata.name.length < 2 || this.userdata.name.length > 30){
                            //this.userdataerror.nameerror = "The length of the nickname should be between 2-30";
                            this.userformif = false;
                        }else{
                            this.userdataerror.nameerror = "";
                        }
                   }

                    //Determine whether the email is legal
                   var emailpattern =  /^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,25}$/;
                   if (!emailpattern.test(this.userdata.email) || this.userdata.email == null ){
                       //this.userdataerror.emailerror = "The email format you entered is incorrect";
                       this.userformif = false;
                   }else{
                       this.userdataerror.emailerror = "";
                   }

                    //Determine whether the phonnumber is legal
                    if (this.userdata.phoneNumber == null){
                            //this.userdataerror.phoneNumbererror = "The name cannot be empty";
                            this.userformif = false;
                        }else{
                                if(this.userdata.phoneNumber.length < 2 || this.userdata.phoneNumber.length > 30){
                                    //this.userdataerror.phoneNumbereerror = "The length of the nickname should be between 2-30";
                                    this.userformif = false;
                                }else{
                                        this.userdataerror.phoneNumbererror = "";
                                }
                    }

                   //If all checks are legal
                   if (this.userformif){
                       this.userdata.username = this.userdata.email;

                      //Update form button is not submittable
                       this.formbutton = true;
                       this.$axios.post("http://127.0.0.1:8081/user/p/userregister",
                       this.userdata 
                       ).then(function (response) {
                            if(response.data == "0x0001"){ 
                                alert("Congratulations on your successful registration, go to login!")
                               window.location.href="http://127.0.0.1:8080/pu/user/login";
                            }
                            else{
                                alert("Your account has been registered by someone else");
                                vm.formbutton = false;
                            }
                       })
                   }else{
                       alert("Your information is incorrectly entered")
                   }
            },
       }
    }
</script>

 
<style scoped>
  @import 'http://www.dazuizui.com:5002/static/userlogin.css';
  @import 'https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css'; 
</style>
 