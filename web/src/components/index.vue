<template>
    <div>
        <div class="header">
    </div>
    <header class="gird-header">
        <div class="header-fixed">
            <div class="header-inner">
                
                <nav class="nav" id="nav">
                    <ul>
                        <li><a href="http://127.0.0.1:8080/">user</a></li>
                    </ul>
               
               
                </nav>
             
            </div>
        </div>
    </header>
    <div class="doc-container" id="blogindexarticle" style="background: url('https://privateproject.oss-cn-hangzhou.aliyuncs.com/20211120/11.jpg');" v-cloak >
            <div class="container-fixed">
             <div class="container-fixed" style="width: 68%;">
                <div class="col-content"  style="width: 100%;">
                        <article class="article-list bloglist" id="LAY_bloglist" >
                            <div>
                                <section class="article-item zoomIn article" style="border-radius:5px">
                                
                                    <h5 class="title">
                                        <a href="#">Evaluation</a>
                                    </h5>
                                        <p>upload picture</p>
                                        <el-upload
                                                class="upload-demo"
                                                drag
                                                :on-progress="test1"
                                                :on-error="error"
                                                action="http://127.0.0.1:8081/user/uploadFile"
                                                multiple>
                                                <i class="el-icon-upload"></i>
                                                <div class="el-upload__text">Drag the file here, or<em>Click upload</em></div>
                                                <div class="el-upload__tip" slot="tip">Please upload pictures</div>
                                            </el-upload>

                                            <el-input
                                                type="textarea"
                                                :autosize="{ minRows: 2, maxRows: 4}"
                                                placeholder="please enter"
                                                v-model="evaluation.evaluationtext">
                                            </el-input>
        <hr>
                                              <el-button type="primary" v-on:click="submitdata()"> submit</el-button>   <a href="http://127.0.0.1:8080/pu/user/EvaluationList">Click me to view the data I uploaded</a>
                                </section>
                            </div>
                        </article>
                    </div>
                </div>
         
            </div>
        </div>

    <footer class="grid-footer">
        <div class="footer-fixed">
            <div class="copyright">
                <div class="info">
                 
                    <p class="mt05">
                        Copyright 2021
                    </p>
                </div>
            </div>
        </div>
    </footer>
    </div>
</template>

<style>
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }
</style>

<script>
 

    export default {
        data() {
            return {
               desc: "aSDD",
               evaluation:{
                    imaurl: null,
                    username: null,
                    evaluationtext: null,
               },
            
             
                user: null,
            
            };

        },
  
        methods: {
            submitdata(){
          
                this.$axios.post("http://127.0.0.1:8081/user/addReviews",this.evaluation).then((res) => {
                    alert(res.data);
                    window.location.href="http://127.0.0.1:8080/";
                })
            },

             //Check if the user is logged in
             checkinsin(){
                //Get token
                this.token = getCookie("token");
                //Parse the token
                this.$axios.get("http://127.0.0.1:8081/user/p/checkinsin?token="+this.token)
                .then((response) => {
                    if(response.data.errorinformation != null && response.data.username == null ){
                        alert("You have not logged in or your login verification has expired, the system will redirect you to the login page")
                        
                    }else{
       
                            this.user = response.data;
                            if(this.user.role == "admin"){
                                alert("Check that you are an administrator and think you are redirected to the management page")
                                window.location.href="http://127.0.0.1:8080/admin";
                            }

                            this.evaluation.username = response.data.username;
                        }
                 }).catch((res => {
                     alert("Please log in again");
                     window.location.href="http://127.0.0.1:8080/pu/user/login";
                 }))
             },

            test1(response, file, fileList){
                this.evaluation.imaurl = file.name;
            },

            error(){
                alert("The picture nickname needs to be changed");
            }
        },
 
        mounted(){
           this.checkinsin();
        },
   
    }
</script>