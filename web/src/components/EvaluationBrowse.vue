<template>
    <div>
        <div class="header">
    </div>
    <header class="gird-header">
        <div class="header-fixed">
            <div class="header-inner">
                
                <nav class="nav" id="nav">
                    <ul>
                        <li><a href="http://127.0.0.1:8080/admin">admin</a></li>                       
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
                            <div v-for="(evaluation,index) in evaluationlist" :key="index">
                                <section class="article-item zoomIn article" style="border-radius:5px">
                     
                                    <h5 class="title">
                                        <a href="#">{{evaluation.username}} evaluation</a>
                                    </h5>

                                    <div class="content">
                                        <div v-if="evaluation.imaurl == null">
                                            <a href="#" class="cover img-light">
                                                <img src="https://privateproject.oss-cn-hangzhou.aliyuncs.com/20211120/use.png">
                                            </a>
                                        </div>
                                        <div v-else>
                                            <a href="#" class="cover img-light">
                                                <img :src="'https://privateproject.oss-cn-hangzhou.aliyuncs.com/public/'+evaluation.imaurl">
                                            </a>
                                        </div>
                                        
                                       {{evaluation.evaluationtext}}
                                    </div>
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
                evaluationlist: null,
            
            };

        },
  
        methods: {
            submitdata(){
                alert("123")
                this.$axios.post("http://127.0.0.1:8081/user/addReviews",this.evaluation).then((res) => {
                    alert(res.data);
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
                            if(this.user.role != "admin"){
                                alert("You are not an administrator, do not have permission to browse this page")
                                window.location.href="127.0.0.1:8081";
                            }

                            this.evaluation.username = response.data.username;
                        }
                 }).catch((res => {
                     alert("pls change the picture nickname")
                 }))
             },

            test1(response, file, fileList){
                this.evaluation.imaurl = file.name;
            },
 
            //Get data uploaded by users
            getdata(){
                this.$axios.post("http://127.0.0.1:8081/admin/getAllDate").then((res) => {
         
                    this.evaluationlist = res.data;
                  
                })
            },
        },
 

        mounted(){
           this.checkinsin();
            this.getdata();
        },
   
    }
</script>