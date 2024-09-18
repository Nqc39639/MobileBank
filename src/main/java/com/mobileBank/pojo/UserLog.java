package com.mobileBank.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class UserLog {
    private Integer id;   // 编号
    private Integer userId;   // 用户ID【关联】userinfo(id)
    private String type;   // 操作名称 NOT NULL
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operationTime;   // 操作时间 NOT NULL
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date reserveTime;   // 日志保留期限 NOT NULL
    private String comment;   // 备注 DEFAULT NULL
}
