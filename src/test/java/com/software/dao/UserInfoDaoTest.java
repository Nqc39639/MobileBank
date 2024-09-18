package com.software.dao;

import com.mobileBank.mapper.UserInfoMapper;
import com.mobileBank.pojo.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserInfoDaoTest {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    public void TestLoginMapper() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("1234567801");
        userInfo.setPassword("FFGHHBKLHFDCRTY");
        // Integer flag = userInfoMapper.selectUserInfoByAccount(userInfo.getAccount());
        Integer flag = userInfoMapper.checkUserInfo(userInfo);
        System.out.println(flag==null);
    }

    @Test
    public void TestRegisterMapper() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("user1");
        userInfo.setPassword("123456");
        userInfo.setRealname("ping");
        userInfo.setSex(1);
        userInfo.setIDCard("471302200008115392");
        userInfo.setBirthPlace("河南省");
        Integer flag = userInfoMapper.registerUserInfo(userInfo);
        System.out.println(flag);
    }
}
