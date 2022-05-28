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
                                        <a href="#"> 
My uploaded data </a>
                                    </h5>
                                   <table class="layui-table" lay-skin="line">
                                                        <colgroup>
                                                            <col width="400">
                                                            <col width="400">
                                                            <col width="300">
                                                        </colgroup>
                                                        <thead>
                                                            <tr>
                                                                <th><b>username</b></th>
                                                                <th><b>text</b></th>
                                                                <th><b>image</b></th>
                                                           
                                                            </tr> 
                                                        </thead>
                                                    
                                                        <tbody v-for="(users,index) in tableData" :key="index">
                                                                <tr>
                                                                    <td>{{users.username}}</td>
                                                                    <td>{{users.evaluationtext}}</td>
                                                                    <td>
                                                                        <div style="width:100% ">
                                                                              <img :src="'https://privateproject.oss-cn-hangzhou.aliyuncs.com/public/'+users.imaurl"  >
                                                                        </div>
                                                                       
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>

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
            
                tableData: null,
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

                            

                        }
                 }).catch((res => {
                     alert("Please log in again2");
                     window.location.href="http://127.0.0.1:8080/pu/user/login";
                 }))


                this.$axios.get("http://127.0.0.1:8081/user/getlist?token="+getCookie("token"))
                .then((response) => {
                    this.tableData =  response.data;
                
                }) 
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