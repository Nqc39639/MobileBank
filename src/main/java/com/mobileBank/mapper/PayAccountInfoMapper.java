package com.mobileBank.mapper;

import com.mobileBank.pojo.PayAccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PayAccountInfoMapper {
    List<PayAccountInfo> selectPayAccountInfoByUserId(@Param("userId") Integer userId);

    PayAccountInfo selectPayAccountInfoById(@Param("id") Integer id);

    Map<String, Object> selectPayeeAccountInfoById(@Param("id") Integer id);

    Integer addPayAccountInfo(PayAccountInfo payAccountInfo);

    PayAccountInfo selectPayAccountInfoByBankCardID(@Param("bankCardID") String bankCardID);

    Integer deletePayAccountInfo(@Param("id") Integer id);

    Integer updatePayAccountBalance(PayAccountInfo payAccountInfo);

    Integer updatePayAccountPassword(@Param("bankCardID") String bankCardID, @Param("password") String password);

}
