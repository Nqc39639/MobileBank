package com.mobileBank.service;

import com.github.pagehelper.PageInfo;
import com.mobileBank.pojo.UserInfo;

public interface UserInfoService {
    /**
     * 功能：用户注册服务
     *
     * @param userInfo
     * @return
     */
    Integer registerUserInfo(UserInfo userInfo);

    /**
     * 功能：用户登录服务——判断登录账号是否存在
     *
     * @param userInfo
     * @return
     */
    Integer checkUserInfo(UserInfo userInfo);

    /**
     * 功能：用户修改个人信息服务
     * update ... by userId
     *
     * @param userInfo
     * @return
     */
    Integer updateUserInfo(UserInfo userInfo);

    /**
     * 功能：用户注销服务【伪删除】
     *
     * @param userInfo
     * @return
     */
    Integer deleteUserInfo(UserInfo userInfo);

    /**
     * 功能：通过id查询用户信息服务
     *
     * @param id
     * @return
     */
    UserInfo selectUserInfoById(Integer id);

    /**
     * 功能：通过account查询用户信息服务
     *
     * @param account
     * @return
     */
    UserInfo selectUserInfoByAccount(String account);

    /**
     * 功能：通过realname查询用户信息服务
     *
     * @param realname
     * @return
     */
    UserInfo selectUserInfoByRealName(String realname);

    /**
     * 功能：管理员查询正常用户
     *
     * @param pageNum
     * @param limit
     * @return
     */
    PageInfo<UserInfo> selectNormalUserInfo(Integer pageNum, Integer limit);

    /**
     * 功能：管理员查询已注销的用户
     *
     * @param
     * @return
     */
    PageInfo<UserInfo> selectAbnormalUserInfo(Integer pageNum, Integer limit);

    /**
     * 功能：管理员查询带参数的正常用户
     *
     * @param
     * @return
     */
    PageInfo<UserInfo> selectNormalUserInfoBySearch(String searchParams, Integer pageNum, Integer limit);

    /**
     * 功能：管理员查询带参数的已注销的用户
     *
     * @param
     * @return
     */
    PageInfo<UserInfo> selectAbnormalUserInfoBySearch(String searchParams, Integer pageNum, Integer limit);

    /**
     * 功能：管理员伪删除正常用户信息
     *
     * @param ids
     * @return
     */
    Integer updateStatusNormalUser(int[] ids);

    /**
     * 功能：管理员恢复已注销用户信息
     *
     * @param ids
     * @return
     */
    Integer updateStatusAbnormalUser(int[] ids);

    /**
     * 功能：管理员增加用户服务
     *
     * @param userInfo
     * @return
     */
    Integer addUserInfo(UserInfo userInfo);
}
