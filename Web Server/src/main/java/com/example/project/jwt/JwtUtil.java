package com.example.project.jwt;


import com.example.project.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This tool class is responsible for generating and parsing JWT
 */
public class JwtUtil {
    /**
     * the key needed to generate or parse the token
     */
    private final static String JWTKey = "kehu1";

    /**
     * Generate ciphertext
     * @return
     */
    public static String createJWT(User user){
        //Build jwt token
        JwtBuilder builder = Jwts.builder();
        builder.setIssuer("kehu1"); //Issuer
        builder.setIssuedAt(new Date()); //Issuing time
        builder.setSubject("kehu1");   //theme
        builder.setExpiration(new Date(System.currentTimeMillis()+3600000*10));  //Expiration time is set to 360000 seconds

        //Custom information Custom load
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        builder.addClaims(map); //Add load

        builder.signWith(SignatureAlgorithm.HS256,JWTKey); //1. Algorithm 2. Key
        //Generate ciphertext
        String jstString = builder.compact();
        return jstString;
    }

    /**
     * Resolve key
     * @param token Pass in the token and get the login username from the data parsed by the token
     * @return
     */
    public static String analysisJWT(String token){
        Claims body = Jwts.parser()
                //Key
                .setSigningKey(JWTKey)
                //The token to be parsed
                .parseClaimsJws(token)
                //Get the parsed data
                .getBody();
        System.out.println(body.get("username"));
        return (String) body.get("username");
    }
}
