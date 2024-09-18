package com.mobileBank.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AdminLog {
    private Integer id;   // 编号
    private Integer adminId;   // 管理员ID【关联】admininfo(id)
    private String type;   // 操作名称 NOT NULL
    private Date operationTime;   // 操作时间 NOT NULL
    private Date reserveTime;   // 日志保留期限 NOT NULL
    private String comment;   // 备注 DEFAULT NULL
}
