package com.example.project.serivce.impl;

import com.alibaba.fastjson.JSONArray;
import com.example.project.jwt.JwtUtil;
import com.example.project.mapper.UserMapper;
import com.example.project.pojo.Resdata;
import com.example.project.pojo.User;
import com.example.project.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * User service
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Retrieve password
     * @param user
     * @return
     */
    @Override
    public String retrievepassword(@RequestBody User user){
        if (user != null){
            User user1 = userMapper.QueryUsersByUsernameAdnPhoneNumberAndEmail(user);
            if (
                    user1.getUsername().equals(user.getUsername()) &&
                    user1.getEmail().equals(user.getEmail()) &&
                    user1.getPhoneNumber().equals(user.getPhoneNumber())){

                //Update password
                userMapper.UpdateUserPassword(user);

                return "0x0001";
            }else{
                return "0x0000";
            }
        }
        return "0x0000";
    }

    /**
     * User registration
     * @param user
     * @return
     */
    @Override
    public String userRegistration( User user) {
        System.err.println(user);
        //Non-empty judgment
        if (user != null) {
            //check if the username is already registered
            User users = userMapper.checkIfTheUsernameIsAlreadyRegistered(user.getUsername(),user.getPhoneNumber());

            if (users == null){
                //Write data to mysql
                userMapper.registerauser(user);
                return "0x0001";
            }
        }
        return "0x0002";
    }


    /**
     *Check if the user is logged in
     * @param token
     * @return
     */
    @Override
    public String checkinsin(String token) {
        String username = JwtUtil.analysisJWT(token);
        User user = userMapper.QueryUserbyUsername(username);
        if (user == null){
            user = userMapper.QueryUserbyphoneNumber(username);
        }
        System.err.println(user.getRole());
        return JSONArray.toJSONString(user);
    }

    /**
     * user Login
     * @param user
     * @return String.class
     */
    @Override
    public String userLogin(User user,String select){
        System.err.println(user);
        Resdata resdata = new Resdata();
        //Non-empty judgment
        if (user!=null){
            User user1;

            if (select.equals("2")){
                user.setPhoneNumber(user.getUsername());
                user1 = userMapper.QueryUsersByPhonenumberAndPassword(user);
                if (user1.getPhoneNumber().equals(user.getPhoneNumber()) && user1.getPassword().equals(user.getPassword())){
                    resdata.setCode("0x0000");
                    //Generate jwt
                    resdata.setJwt(JwtUtil.createJWT(user));
                    return JSONArray.toJSONString(resdata);
                }else{
                    resdata.setCode("0x0001");
                    return JSONArray.toJSONString(resdata);
                }

            }else {
                user1 = userMapper.QueryUsersByUsernameAndPassword(user);
                if (user1 != null &&user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword())){
                    resdata.setCode("0x0000");
                    //Generate jwt
                    resdata.setJwt(JwtUtil.createJWT(user));
                    return JSONArray.toJSONString(resdata);
                }else{
                    resdata.setCode("0x0001");
                    return JSONArray.toJSONString(resdata);
                }
            }
        }
        return JSONArray.toJSONString(resdata);
    }
}
