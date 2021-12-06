package com.example.fa_blog_study.dao;

import com.example.fa_blog_study.dao.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {

    @Select("SELECT * FROM ms_sys_user WHERE id=#{id}")
    SysUser getUserById(Long id);

    @Select("SELECT * FROM ms_sys_user WHERE account=#{account} AND password=#{password} LIMIT 1")
    SysUser findUser(@Param("account") String account,@Param("password") String password);

    @Select("SELECT * FROM ms_sys_user WHERE account=#{account} LIMIT 1")
    SysUser findUserByAccount(String account);

//    @Insert("INSERT INTO ms_sys_user " +
//            "(account,admin,avatar,create_date,deleted,email," +
//            "last_login,mobile_phone_number,nickname,password,salt,status) " +
//            "VALUES (#{account},#{admin},#{avatar},#{createDate},#{deleted},#{email}," +
//            "#{lastLogin},#{mobilePhoneNumber},#{nickname},#{password},#{salt},#{status})")
    int save(SysUser sysUser);
}
