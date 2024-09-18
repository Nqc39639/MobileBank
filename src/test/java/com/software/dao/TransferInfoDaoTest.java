package com.software.dao;

import com.mobileBank.mapper.TransferInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TransferInfoDaoTest {
    @Autowired
    TransferInfoMapper transferInfoMapper;

    @Test
    public void TestSelectRecipientsMapper() {
        List<Map<String, Object>> recipientList = transferInfoMapper.selectRecipients(3);
        for (Map<String, Object> recipient : recipientList) {
            System.out.println(recipient);
        }
    }
}
