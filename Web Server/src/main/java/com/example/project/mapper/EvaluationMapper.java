package com.example.project.mapper;

import com.example.project.pojo.Evaluation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Mapper
public interface EvaluationMapper {
    // Add User Reviews
    @Insert("insert into evaluation value(null,#{evaluationtext},#{username},#{imaurl})")
    public void addUserReviews(Evaluation evaluation);

    /**
     * get all data
     */
    @Select("select *from evaluation")
    public ArrayList<Evaluation> getAllDate();

    @Select("select *from evaluation where username = #{username} ")
    public ArrayList<Evaluation> getlistByUsername(@Param("username")String username);
}
