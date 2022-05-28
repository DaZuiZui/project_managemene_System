package com.example.project.serivce.impl;

import com.alibaba.fastjson.JSONArray;
import com.example.project.jwt.JwtUtil;
import com.example.project.mapper.EvaluationMapper;
import com.example.project.mapper.UserMapper;
import com.example.project.pojo.Evaluation;
import com.example.project.pojo.User;
import com.example.project.serivce.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;
    @Autowired
    private UserMapper userMapperl;

    public String getEvaList(@RequestParam("token") String token){
        String username = JwtUtil.analysisJWT(token);
        if (username.indexOf("@") < 0){
            User user = userMapperl.QueryUserbyphoneNumber(username);
            username = user.getUsername();
        }
        ArrayList<Evaluation> evaluations = evaluationMapper.getlistByUsername(username);
        System.out.println("asd");
        return JSONArray.toJSONString(evaluations);
    }

    /**
     * Add User Reviews
     * @param evaluation
     * @return
     */
    @Override
    public String addUserReviews(@RequestBody Evaluation evaluation){
        if (evaluation != null) {
            evaluationMapper.addUserReviews(evaluation);
        }
        return "添加成功";
    }

    /**
     * get all data
     */
    @Override
    public String getAllDate() {
        return JSONArray.toJSONString(evaluationMapper.getAllDate());
    }
}
