package com.example.project.serivce;

import com.example.project.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User servcie
 */
@Service
public interface UserService {

    /**
     * Check if the user is logged in
     * @param token
     * @return
     */
    @GetMapping("/user/p/checkinsin")
    public String checkinsin(@RequestParam("token")String token);

    /**
     * user Login
     * @param user
     * @return String.class
     */
    public String userLogin(User user,@RequestParam("select")String select);

    /**
     * User registration
     * @param user
     * @return
     */
    public String userRegistration( User user);

    /**
     * Retrieve password
     * @param user
     * @return
     */
    public String retrievepassword(@RequestBody User user);
}
