package com.software.dao;

import com.mobileBank.mapper.PayInfoMapper;
import com.mobileBank.pojo.PayInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PayInfoDaoTest {
    @Autowired
    PayInfoMapper payInfoMapper;

    @Test
    public void TestSelectPayInfoByPayAccountIdAndDateRangeMapper() {
        Date today = new Date();
        // 创建一个Calendar实例，并设置时间为今天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        // 将Calendar设置为本月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime(); // 本月第一天

        // 如果将今天作为结束日期，直接使用today变量即可
        Date endDate = today; // 今天作为结束日期

        // 如果你需要本月最后一天，可以进一步设置Calendar
        // calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        // Date endOfMonth = calendar.getTime(); // 本月最后一天
        // Integer flag = userInfoMapper.selectUserInfoByAccount(userInfo.getAccount());
        List<PayInfo> flag = payInfoMapper.selectPayInfoByPayAccountIdAndDateRange(1,startDate,endDate);
        System.out.println(flag);
    }

}
