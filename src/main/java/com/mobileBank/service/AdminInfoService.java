package com.mobileBank.service;

import com.mobileBank.pojo.AdminInfo;

public interface AdminInfoService {
    /**
     * 功能：管理员登录服务
     * @param adminInfo
     * @return
     */
    public Integer checkAdminInfo(AdminInfo adminInfo);

    /**
     * 功能：管理员修改个人信息服务
     * update ... by adminId
     * @param adminInfo
     * @return
     */
    public Integer updateAdminInfo(AdminInfo adminInfo);

    /**
     * 功能：管理员注销服务【伪删除】
     * @param adminInfo
     * @return
     */
    public Integer deleteAdminInfo(AdminInfo adminInfo);
    
    /**
     * 功能：通过id查询用户信息服务
     * @param id
     * @return
     */
    public AdminInfo selectAdminInfoById(Integer id);

    /**
     * 功能：通过account查询用户信息服务
     * @param account
     * @return
     */
    public AdminInfo selectAdminInfoByAccount(String account);
    
}
