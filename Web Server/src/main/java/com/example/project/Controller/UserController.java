package com.example.project.Controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.example.project.pojo.User;
import com.example.project.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;

/**
 * User controller
 */
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAI5tFfSmSBqN2cJ2WjU8QH";
    private static String accessKeySecret = "OrXryk2Q7qcx307HN8dfttkqeVhChP";
    String bucktName = "bxxt-lzj";

    /**
     *
     * @param token
     * @return
     */
    @GetMapping("/user/p/checkinsin")
    public String checkinsin(@RequestParam("token")String token){
        return userService.checkinsin(token);
    }

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/user/uploadFile")
    public String uploadFile(HttpServletRequest request) throws Exception {
        if (request != null){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            //files对应前端的key
            MultipartFile multipartFile = multipartRequest.getFile("file");
            multipartFile.getBytes();//得到文件的二进制流

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            /**
             *  查询文件是否存在
             **/
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            boolean found = false;
            found = ossClient.doesObjectExist("privateproject", "public/"+multipartFile.getOriginalFilename());
            System.out.println(found);
            if (found){
                throw new Exception("介绍图片已经存在");
            }else {
                //将图片上传到oss
                PutObjectRequest putObjectRequest = new PutObjectRequest("privateproject","public/"+multipartFile.getOriginalFilename(), new ByteArrayInputStream(multipartFile.getBytes()));

                // 上传字符串。
                ossClient.putObject(putObjectRequest);
                System.out.println("上传成功");
            }

            // 关闭OSSClient。
            ossClient.shutdown();
        }

        return null;
    }

    /**
     * User registration
     * @param user
     * @return
     */
    @PostMapping("/user/p/userregister")
    public String userRegistration(@RequestBody User user){
        return userService.userRegistration(user);
    }

    /**
     * userlogin
     * @param user
     * @return
     */
    @PostMapping("/user/p/login")
    public String userLogin(@RequestBody User user,@RequestParam("select")String select){
        return userService.userLogin(user,select);
    }

    /**
     * Retrieve password
     * @param user
     * @return
     */
    @RequestMapping("/user/p/retrievepassword")
    public String retrievepassword(@RequestBody User user){
        return userService.retrievepassword(user);
    }
}
