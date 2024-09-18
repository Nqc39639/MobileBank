package com.mobileBank.controller;

import com.mobileBank.pojo.PayAccountInfo;
import com.mobileBank.pojo.PayInfo;
import com.mobileBank.pojo.TransferInfo;
import com.mobileBank.pojo.UserInfo;
import com.mobileBank.service.PayAccountInfoService;
import com.mobileBank.service.PayInfoService;
import com.mobileBank.service.TransferInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/payAccount")
public class PayAccountInfoController {
    @Autowired
    private PayAccountInfoService payAccountInfoService;

    @Autowired
    private PayInfoService payInfoService;

    @Autowired
    private TransferInfoService transferInfoService;

    //功能：跳转到账户列表页面
    @RequestMapping("/accountListPage")
    public String accountListPage() {
        return "accountList";
    }

    @RequestMapping("/findAccount")
    public String findAccount(HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        List<PayAccountInfo> payAccountInfoList = payAccountInfoService.selectPayAccountInfoByUserId(userInfo.getId());
        model.addAttribute("payAccountInfoList", payAccountInfoList);
        return "accountList";
    }

    @RequestMapping("/trade")
    public String trade(Integer payAccountId, Model model) {
        PayAccountInfo payAccountInfo = payAccountInfoService.selectPayAccountInfoById(payAccountId);
        List<PayInfo> payInfoList = payInfoService.selectPayInfoByPayAccountId(payAccountInfo.getId());
        List<TransferInfo> transferInfoList = transferInfoService.selectTransferInfoByPayAccountId(payAccountInfo.getId());
        List<TransferInfo> payeeInfoList = transferInfoService.selectTransferInfoByTargetPayAccountId(payAccountInfo.getId());
        model.addAttribute("payAccountInfo", payAccountInfo);
        model.addAttribute("payInfoList", payInfoList);
        model.addAttribute("transferInfoList", transferInfoList);
        model.addAttribute("payeeInfoList", payeeInfoList);
        return "trade";
    }

    @RequestMapping("/unlink")
    @ResponseBody
    public String unlink(Integer payAccountId, Model model) {
        Integer flag = payAccountInfoService.deletePayAccountInfo(payAccountId);
        if (flag > 0) {
            return "1";
        } else {
            return "0";
        }
    }

}
