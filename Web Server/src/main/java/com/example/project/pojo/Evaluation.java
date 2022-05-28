package com.example.project.pojo;

import java.io.Serializable;

public class Evaluation  implements Serializable {
    private int id;
    private String evaluationtext;
    private String username;
    private String imaurl;

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", evaluationtext='" + evaluationtext + '\'' +
                ", username='" + username + '\'' +
                ", imaurl='" + imaurl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvaluationtext() {
        return evaluationtext;
    }

    public void setEvaluationtext(String evaluationtext) {
        this.evaluationtext = evaluationtext;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImaurl() {
        return imaurl;
    }

    public void setImaurl(String imaurl) {
        this.imaurl = imaurl;
    }

    public Evaluation() {
    }

    public Evaluation(int id, String evaluationtext, String username, String imaurl) {
        this.id = id;
        this.evaluationtext = evaluationtext;
        this.username = username;
        this.imaurl = imaurl;
    }
}
