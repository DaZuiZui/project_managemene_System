package com.example.project.serivce;

import com.example.project.pojo.Evaluation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface EvaluationService {
    /**
     * Add eva Reviews
     * @param evaluation
     * @return
     */
    public String addUserReviews(@RequestBody Evaluation evaluation);

    /**
     * get all data
     */
    @PostMapping ("/user/getAllDate")
    public String getAllDate();
}
