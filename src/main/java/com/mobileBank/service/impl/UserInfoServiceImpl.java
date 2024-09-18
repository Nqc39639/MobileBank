package com.mobileBank.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mobileBank.mapper.UserInfoMapper;
import com.mobileBank.pojo.TUsersSearch;
import com.mobileBank.pojo.UserInfo;
import com.mobileBank.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Integer registerUserInfo(UserInfo userInfo) {
        Integer flag;
        if (userInfoMapper.selectUserInfoByAccount(userInfo.getAccount()) != null) {   // 账号已存在
            flag = 2;
        } else if (userInfoMapper.registerUserInfo(userInfo) != null) {   // 注册成功
            flag = 1;
        } else {   // 注册失败
            flag = 0;
        }
        return flag;
    }

    @Override
    public Integer checkUserInfo(UserInfo userInfo) {
        Integer flag;
        if (userInfoMapper.selectUserInfoByAccount(userInfo.getAccount()) == null) {   // 账号不存在
            flag = 0;
        } else if (userInfoMapper.checkUserInfo(userInfo) != null) {   // 密码正确
            flag = 1;
        } else {   // 密码不正确
            flag = 2;
        }
        return flag;
    }

    @Override
    public Integer updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    @Override
    public Integer deleteUserInfo(UserInfo userInfo) {
        return userInfoMapper.deleteUserInfo(userInfo);
    }

    @Override
    public UserInfo selectUserInfoById(Integer id) {
        return userInfoMapper.selectUserInfoById(id);
    }

    @Override
    public UserInfo selectUserInfoByAccount(String account) {
        return userInfoMapper.selectUserInfoByAccount(account);
    }

    @Override
    public UserInfo selectUserInfoByRealName(String realname) {
        return userInfoMapper.selectUserInfoByRealName(realname);
    }

    @Override
    public PageInfo<UserInfo> selectNormalUserInfo(Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<UserInfo> readerInfoList = userInfoMapper.selectNormalUserInfo();
        return new PageInfo<>(readerInfoList);
    }


    @Override
    public PageInfo<UserInfo> selectAbnormalUserInfo(Integer pageNum, Integer limit) {
        PageHelper.startPage(pageNum, limit);
        List<UserInfo> readerInfoList = userInfoMapper.selectAbnormalUserInfo();
        return new PageInfo<>(readerInfoList);
    }

    @Override
    public PageInfo<UserInfo> selectNormalUserInfoBySearch(String searchParams, Integer pageNum, Integer limit) {
        TUsersSearch search = JSON.parseObject(searchParams, TUsersSearch.class);
        List<UserInfo> allList = userInfoMapper.selectNormalUserInfoBySearch(search);
        allList.sort(new Comparator<UserInfo>() {
            @Override
            public int compare(UserInfo o1, UserInfo o2) {
                if (o1.getId().compareTo(o2.getId()) > 0) {
                    return -1;
                } else if (o1.getId().compareTo(o2.getId()) < 0) {
                    return 1;
                }
                return 0;
            }
        });
        return new PageInfo<>(allList);
    }

    @Override
    public PageInfo<UserInfo> selectAbnormalUserInfoBySearch(String searchParams, Integer pageNum, Integer limit) {
        TUsersSearch search = JSON.parseObject(searchParams, TUsersSearch.class);
        List<UserInfo> allList = userInfoMapper.selectNormalUserInfoBySearch(search);
        allList.sort(new Comparator<UserInfo>() {
            @Override
            public int compare(UserInfo o1, UserInfo o2) {
                if (o1.getId().compareTo(o2.getId()) > 0) {
                    return -1;
                } else if (o1.getId().compareTo(o2.getId()) < 0) {
                    return 1;
                }
                return 0;
            }
        });
        return new PageInfo<>(allList);
    }

    @Override
    public Integer updateStatusNormalUser(int[] ids) {
        return userInfoMapper.updateStatusNormalUser(ids);
    }

    @Override
    public Integer updateStatusAbnormalUser(int[] ids) {
        return userInfoMapper.updateStatusAbnormalUser(ids);
    }

    @Override
    public Integer addUserInfo(UserInfo userInfo) {
        return userInfoMapper.addUserInfo(userInfo);
    }

}
