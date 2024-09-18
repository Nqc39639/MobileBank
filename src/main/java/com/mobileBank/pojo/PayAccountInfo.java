package com.mobileBank.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class PayAccountInfo {
    private Integer id;   // 编号
    private Integer userId;   // 用户ID【关联】userinfo(id)
    private String bankCardID;   // 银行卡号 NOT NULL
    private String password;   // 账户密码 NOT NULL
    private Double firstBalance;   // 账户初始余额 NOT NULL
    private Double balance;   // 账户目前余额 NOT NULL
    private Date createDate;   // 开户日期 NOT NULL
    private String createAddress;   // 开户网点 DEFAULT NULL
    private Integer status;   // 账户状态(0为解绑, 1为正常) DEFAULT 1
    private String comment;   // 备注 DEFAULT NULL
}
