package com.software.service;

import com.mobileBank.service.TransferInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TransferInfoServiceTest {
    @Autowired
    TransferInfoService transferInfoService;

    @Test
    public void TestSelectRecipientsService() {
        List<Map<String, Object>> recipientList = transferInfoService.selectRecipients(3);
        for (Map<String, Object> recipient : recipientList) {
            System.out.println(recipient);
        }
    }

}
