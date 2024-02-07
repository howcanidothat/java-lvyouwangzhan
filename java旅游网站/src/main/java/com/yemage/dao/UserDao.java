package com.yemage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yemage.domain.UserAdmin;
import com.yemage.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yemage
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    /**
     * 新增账号
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO user ( email, password, salt, confirm_code, activation_time, is_valid) " +
            "VALUES (#{email},#{password},#{salt},#{confirmCode},#{activationTime},#{isValid})")
    int insertUser(UserAdmin user);

    @Insert("INSERT INTO tab_user ( email, password,username) " +
            "VALUES (#{email},#{password},#{username})")
    int insertUser1(User user);

    /**
     * 根据确认码查询用户
     *
     * @param confirmCode
     * @return
     */

    @Select("SELECT id,email, activation_time FROM user WHERE confirm_code = #{confirmCode} AND is_valid = 0")
    UserAdmin selectUserByConfirmCode(@Param("confirmCode") String confirmCode);


    /**
     * 根据确认码查询用户并修改状态值为1（可用）
     *
     * @param confirmCode
     * @return
     */
    @Update("UPDATE user SET is_valid = 1 WHERE confirm_code = #{confirmCode}")
    int updateUserByConfirmCode(@Param("confirmCode") String confirmCode);

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    @Select("SELECT  id,email, password, salt FROM user WHERE email = #{email} AND is_valid = 1")
    List<UserAdmin> selectUserByEmail(@Param("email") String email);

    @Select("SELECT  username,email, password FROM tab_user WHERE email = #{email} ")
    List<User> selectUserAdminByEmail(@Param("email") String email);
}

