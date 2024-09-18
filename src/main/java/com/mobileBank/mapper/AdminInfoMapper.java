package com.mobileBank.mapper;

import com.mobileBank.pojo.AdminInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminInfoMapper {

    Integer checkAdminInfo(AdminInfo adminInfo);

    Integer updateAdminInfo(AdminInfo adminInfo);

    Integer deleteAdminInfo(AdminInfo adminInfo);

    AdminInfo selectAdminInfoById(@Param("id") Integer id);

    AdminInfo selectAdminInfoByAccount(@Param("account") String account);
}
