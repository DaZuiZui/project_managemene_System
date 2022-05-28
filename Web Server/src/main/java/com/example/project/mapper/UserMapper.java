package com.example.project.mapper;

import com.example.project.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * user mapper
 */
@Mapper
public interface UserMapper {
    /**
     * Check if the username is already registered
     * @param username
     * @return
     */
    @Select("select *from user where username = #{username} or phoneNumber = #{phoneNumber}")
    public User checkIfTheUsernameIsAlreadyRegistered(@Param("username")String username,@Param("phoneNumber")String phoneNumber);

    /**
     * @param username
     * @return
     */
    @Select("select *from user where username = #{username}")
    public User QueryUserbyUsername(@Param("username")String username);

    /**
     * @param phoneNumber
     * @return
     */
    @Select("select *from user where phoneNumber = #{phoneNumber}")
    public User QueryUserbyphoneNumber(@Param("phoneNumber")String phoneNumber);

    /**
     * register a user
     * @param user
     */
    @Insert({"insert into user value(null,#{user.username},#{user.password},#{user.name},#{user.email},#{user.phoneNumber},'user')"})
    public void registerauser(@Param("user")User user);

    /**
     *
     * @param user
     * @return
     */
    @Select("select *from user where username = #{username} and password = #{password}")
    public User QueryUsersByUsernameAndPassword(User user);

    @Select("select *from user where phoneNumber = #{phoneNumber} and password = #{password}")
    public User QueryUsersByPhonenumberAndPassword(User user);

    @Select("select *from user where username = #{username} and email = #{email} and phoneNumber = #{phoneNumber}")
    public User QueryUsersByUsernameAdnPhoneNumberAndEmail(User user);

    @Update("update user set password = #{password} where username = #{username}")
    public void UpdateUserPassword(User user);


}
