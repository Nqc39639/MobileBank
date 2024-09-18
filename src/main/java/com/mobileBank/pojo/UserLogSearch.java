package com.mobileBank.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class UserLogSearch {
    private String type;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date_min;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date_max;
    private String comment;
}
