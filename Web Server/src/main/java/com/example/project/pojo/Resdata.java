package com.example.project.pojo;

/**
 *
 */
public class Resdata {
    private int id;
    private String code;
    private String jwt;

    @Override
    public String toString() {
        return "Resdata{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Resdata() {
    }

    public Resdata(int id, String code, String jwt) {
        this.id = id;
        this.code = code;
        this.jwt = jwt;
    }
}
