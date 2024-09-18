package com.mobileBank.pojo;

import lombok.Data;

@Data
public class AdminInfo {
    private Integer id;   // 编号
    private String account;   // 用户名 NOT NULL
    private String password;   // 密码 NOT NULL
    private String realname;   // 姓名 NOT NULL
}
