package com.mobileBank.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class TransferInfo {
    private Integer id;   // 编号
    private Integer payAccountId;   // 账户ID【关联】payaccountinfo(id)
    private Integer targetPayAccountId;   // 转账对象ID【关联】payaccountinfo(id)
    private Double money;   // 转账金额 NOT NULL
    private String orderNumber;   // 流水号 NOT NULL
    private Date payDate;   // 交易时间 NOT NULL
    private Integer status;   // 交易状态(0为失败, 1为成功, 2为撤销) NOT NULL
}
