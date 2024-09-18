package com.mobileBank.mapper;

import com.mobileBank.pojo.TUsersSearch;
import com.mobileBank.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    Integer registerUserInfo(UserInfo userInfo);

    Integer checkUserInfo(UserInfo userInfo);

    Integer updateUserInfo(UserInfo userInfo);

    Integer deleteUserInfo(UserInfo userInfo);

    UserInfo selectUserInfoById(@Param("id") Integer id);

    UserInfo selectUserInfoByAccount(@Param("account") String account);

    UserInfo selectUserInfoByRealName(@Param("realname") String realname);

    List<UserInfo> selectNormalUserInfo();

    List<UserInfo> selectAbnormalUserInfo();

    List<UserInfo> selectNormalUserInfoBySearch(TUsersSearch search);

    Integer updateStatusNormalUser(@Param("ids") int[] ids);

    Integer updateStatusAbnormalUser(@Param("ids") int[] ids);

    Integer addUserInfo(UserInfo userInfo);

}
