package com.software.service;

import com.mobileBank.pojo.UserInfo;
import com.mobileBank.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserInfoServiceTest {
    @Autowired
    UserInfoService userInfoService;

    @Test
    public void TestLoginService() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("1234567801");
        userInfo.setPassword("FFGHHBKLHFDCRTY");
        Integer flag = userInfoService.checkUserInfo(userInfo);
        System.out.println(flag);
    }

    @Test
    public void TestRegisterService() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("user1");
        userInfo.setPassword("123456");
        userInfo.setRealname("ping");
        userInfo.setSex(1);
        userInfo.setIDCard("471302200008115392");
        userInfo.setBirthPlace("河南省");
        Integer flag = userInfoService.registerUserInfo(userInfo);
        System.out.println(flag);
    }
}
