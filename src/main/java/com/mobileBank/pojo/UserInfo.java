package com.mobileBank.pojo;

import lombok.Data;

@Data
public class UserInfo {
    private Integer id;   // 编号
    private String account;   // 用户名 NOT NULL
    private String password;   // 密码 NOT NULL
    private String realname;   // 姓名 NOT NULL
    private Integer sex;   // 性别(0为女, 1为男) DEFAULT 1
    private String telephone;   // 手机号 DEFAULT NULL
    private String IDCard;   // 身份证号 NOT NULL
    private String birthPlace;   // 籍贯 NOT NULL
    private String address;   // 地址 DEFAULT NULL
    private Integer creditGrade;   // 信用等级(星级5, 4, 3, 2, 1) DEFAULT 5
    private Integer status;   // 帐号状态(0为注销, 1为正常) DEFAULT 1
}
