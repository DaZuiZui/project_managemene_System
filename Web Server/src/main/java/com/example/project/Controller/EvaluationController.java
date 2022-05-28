package com.example.project.Controller;

import com.example.project.pojo.Evaluation;
import com.example.project.serivce.impl.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Evaluation Controller
 */
@CrossOrigin
@RestController
public class EvaluationController {
    @Autowired
    private EvaluationServiceImpl evaluationService;

    @GetMapping("/user/getlist")
    public String getEvaList(@RequestParam("token") String token){
        System.out.println("asd");
        return evaluationService.getEvaList(token);
    }

    /**
     * Add User Reviews
     * @param evaluation
     * @return
     */
    @PostMapping("/user/addReviews")
    public String AddUserReviews(@RequestBody Evaluation evaluation){

        return evaluationService.addUserReviews(evaluation);
    }

    /**
     * get all data
     */
    @PostMapping ("/admin/getAllDate")
    public String getAllDate(){
        return evaluationService.getAllDate();
    }
}
