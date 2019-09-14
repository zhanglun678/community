package com.zl.mapper;

import com.zl.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modified,avatarUrl) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    int insert(User user);
    @Select("select * from user where token = #{token}")
    User findUserByToken(@Param("token") String token);
    @Select("select * from user where account_id = #{accountId}")
    User findUserByAccountId(@Param("accountId") String accountId);
    @Update("update user set token = #{token},gmt_modified=#{gmtModified},name=#{name},avatar_url=#{avatarUrl} where account_id = #{accountId}")
    int updateUser(User user);

}
