package com.mobileBank.controller;

import com.mobileBank.pojo.PayAccountInfo;
import com.mobileBank.pojo.TransferInfo;
import com.mobileBank.pojo.UserInfo;
import com.mobileBank.pojo.UserLog;
import com.mobileBank.service.PayAccountInfoService;
import com.mobileBank.service.TransferInfoService;
import com.mobileBank.service.UserInfoService;
import com.mobileBank.service.UserLogService;
import com.mobileBank.utils.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/transfer")
public class TransferInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TransferInfoService transferInfoService;

    @Autowired
    private PayAccountInfoService payAccountInfoService;

    @Autowired
    private UserLogService userLogService;

    @RequestMapping("")
    public String check(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            return "transferManage";
        }
    }

    @RequestMapping("/recipientPage")
    public String recipientPage(HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            List<Map<String, Object>> recipientList = transferInfoService.selectRecipients(userInfo.getId());
            // 移除相同的信息，方法一：
            Set<String> uniqueBankCardIDs = new HashSet<>();
            Iterator<Map<String, Object>> iterator = recipientList.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> recipient = iterator.next();
                String bankCardID = (String) recipient.get("bankCardID");
                if (!uniqueBankCardIDs.add(bankCardID)) { // 如果添加失败，说明ID已存在
                    iterator.remove(); // 移除重复的Map
                }
            }
            List<Map<String, Object>> newRecipientList = new ArrayList<>(recipientList); // 原始列表现在只包含唯一的Map

            // 方法二：
            // List<Map<String, Object>> recipientList = transferInfoService.selectRecipients(userInfo.getId());
            // List<Map<String, Object>> newRecipientList = new ArrayList<>();
            // Set<String> uniqueBankCardIDs = new HashSet<>();
            // for (Map<String, Object> recipient : recipientList) {
            //     String bankCardID = (String) recipient.get("bankCardID");
            //     if (uniqueBankCardIDs.add(bankCardID)) { // 如果添加成功，说明ID是唯一的
            //         newRecipientList.add(recipient); // 将独特的Map添加到新列表中
            //     }
            // }

            model.addAttribute("recipientList", newRecipientList);
            return "recipient";
        }
    }

    @RequestMapping("/recipient")
    @ResponseBody
    public String recipient(Integer payeeAccountId, String payeeRealname, String payeeBankCardID, HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            List<PayAccountInfo> payAccountInfoList = payAccountInfoService.selectPayAccountInfoByUserId(userInfo.getId());
            session.setAttribute("payeeAccountId", payeeAccountId);
            session.setAttribute("payeeRealname", payeeRealname);
            session.setAttribute("payeeBankCardID", payeeBankCardID);
            session.setAttribute("payAccountInfoList", payAccountInfoList);
            return "success";
        }
    }

    @RequestMapping("/transferByRecipientPage")
    public String transferByRecipientPage(HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            List<PayAccountInfo> payAccountInfoList = payAccountInfoService.selectPayAccountInfoByUserId(userInfo.getId());
            List<Map<String, Object>> recipientList = transferInfoService.selectRecipients(userInfo.getId());
            model.addAttribute("payAccountInfoList", payAccountInfoList);
            model.addAttribute("recipientList", recipientList);
            return "transferByRecipient";
        }
    }

    @RequestMapping("/transferPage")
    public String transferPage(HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            List<PayAccountInfo> payAccountInfoList = payAccountInfoService.selectPayAccountInfoByUserId(userInfo.getId());
            List<Map<String, Object>> recipientList = transferInfoService.selectRecipients(userInfo.getId());
            model.addAttribute("payAccountInfoList", payAccountInfoList);
            model.addAttribute("recipientList", recipientList);
            return "transfer";
        }
    }

    @RequestMapping("/transferAssurePage")
    public String transferAssurePage(Integer payAccountId, Integer payeeAccountId, HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            PayAccountInfo payAccountInfo = payAccountInfoService.selectPayAccountInfoById(payAccountId);
            Map<String, Object> payeeAccountInfo = payAccountInfoService.selectPayeeAccountInfoById(payeeAccountId);
            model.addAttribute("payAccountInfo", payAccountInfo);
            model.addAttribute("payeeAccountInfo", payeeAccountInfo);
            return "transferAssure";
        }
    }

    @RequestMapping("/transferSubmit")
    @ResponseBody
    public String transfer(Integer payAccountId, Integer payeeAccountId, Integer payeeUserId, String payeeRealname, Double transferBalance, String payPassword, HttpSession session) {
        PayAccountInfo payAccountInfo = payAccountInfoService.selectPayAccountInfoById(payAccountId);
        if (!payPassword.equals(payAccountInfo.getPassword())) {
            return "0";   // 支付密码不正确
        }
        if (payAccountInfo.getBalance() < transferBalance) {
            return "2";   // 余额不足
        }
        UserInfo payeeUserInfo;
        if (payeeRealname == null || payeeRealname.equals("")) {
            payeeUserInfo = userInfoService.selectUserInfoById(payeeUserId);
        } else {
            payeeUserInfo = userInfoService.selectUserInfoByRealName(payeeRealname);
        }
        PayAccountInfo payeeAccountInfo = payAccountInfoService.selectPayAccountInfoById(payeeAccountId);
        if (payeeUserInfo == null || payeeAccountInfo == null) {
            return "3";   // 账户未线上开户
        }
        if (payeeAccountInfo.getBankCardID().equals(payAccountInfo.getBankCardID())) {
            return "4";    // 不能向自己转账
        }
        Double payUserBalance = payAccountInfo.getBalance() - transferBalance;
        Double payeeUserBalance = payeeAccountInfo.getBalance() + transferBalance;
        payAccountInfo.setBalance(payUserBalance);
        payeeAccountInfo.setBalance(payeeUserBalance);
        payAccountInfoService.updatePayAccountBalance(payAccountInfo);
        payAccountInfoService.updatePayAccountBalance(payeeAccountInfo);
        Date date = new Date();
        // 拼接流水账单号码
        String orderNumber = "6001" + DateTime.getNowDateString() + new Random().nextInt(10);
        TransferInfo transferInfo = new TransferInfo();
        transferInfo.setPayAccountId(payAccountId);
        transferInfo.setTargetPayAccountId(payeeAccountId);
        transferInfo.setMoney(transferBalance);
        transferInfo.setOrderNumber(orderNumber);
        transferInfo.setPayDate(date);
        transferInfoService.addTransferInfo(transferInfo);
        // 写入用户日志
        UserInfo payUserInfo = (UserInfo) session.getAttribute("userInfo");
        UserLog userLog = new UserLog();
        userLog.setUserId(payUserInfo.getId());
        userLog.setType("转账给好友");
        userLog.setOperationTime(DateTime.getNowDateTime());
        userLog.setReserveTime(DateTime.getAfterDateTime());
        userLog.setComment("转账账单流水号: " + orderNumber);
        userLogService.addUserLog(userLog);
        // 传出数据结果
        session.setAttribute("payUserInfo", payUserInfo);
        session.setAttribute("payAccountInfo", payAccountInfo);
        session.setAttribute("payeeUserInfo", payeeUserInfo);
        session.setAttribute("payeeAccountInfo", payeeAccountInfo);
        session.setAttribute("transferInfo", transferInfo);
        return "1";   // 转账成功
    }

    @RequestMapping("/transferToOthersPage")
    public String transferToOthersPage(Integer payAccountId, HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            PayAccountInfo payAccountInfo = payAccountInfoService.selectPayAccountInfoById(payAccountId);
            model.addAttribute("payAccountInfo", payAccountInfo);
            return "transferToOthers";
        }
    }

    @RequestMapping("/transferToOthers")
    @ResponseBody
    public String transferToOthers(Integer payAccountId, String payeeRealName, String payeeBankCardID, Double transferBalance, String payPassword, HttpSession session) {
        PayAccountInfo payAccountInfo = payAccountInfoService.selectPayAccountInfoById(payAccountId);
        if (!payPassword.equals(payAccountInfo.getPassword())) {
            return "0";   // 支付密码不正确
        }
        if (payAccountInfo.getBalance() < transferBalance) {
            return "2";   // 余额不足
        }
        UserInfo payeeUserInfo = userInfoService.selectUserInfoByRealName(payeeRealName);
        if (payeeUserInfo == null) {
            return "3";   // 收款人不存在
        }
        PayAccountInfo payeeAccountInfo = payAccountInfoService.selectPayAccountInfoByBankCardID(payeeBankCardID);
        if (payeeAccountInfo == null) {
            return "4";   // 收款账户未开户
        }
        if (!payeeAccountInfo.getUserId().equals(payeeUserInfo.getId())) {
            return "5";    // 收款人或收款账户不正确
        }
        if (payeeBankCardID.equals(payAccountInfo.getBankCardID())) {
            return "6";    // 不能向自己转账
        }
        Double payUserBalance = payAccountInfo.getBalance() - transferBalance;
        Double payeeUserBalance = payeeAccountInfo.getBalance() + transferBalance;
        payAccountInfo.setBalance(payUserBalance);
        payeeAccountInfo.setBalance(payeeUserBalance);
        payAccountInfoService.updatePayAccountBalance(payAccountInfo);
        payAccountInfoService.updatePayAccountBalance(payeeAccountInfo);
        Date date = new Date();
        // 拼接流水账单号码
        String orderNumber = "6001" + DateTime.getNowDateString() + new Random().nextInt(10);
        TransferInfo transferInfo = new TransferInfo();
        transferInfo.setPayAccountId(payAccountId);
        transferInfo.setTargetPayAccountId(payeeAccountInfo.getId());
        transferInfo.setMoney(transferBalance);
        transferInfo.setOrderNumber(orderNumber);
        transferInfo.setPayDate(date);
        transferInfoService.addTransferInfo(transferInfo);
        // 写入用户日志
        UserInfo payUserInfo = (UserInfo) session.getAttribute("userInfo");
        UserLog userLog = new UserLog();
        userLog.setUserId(payUserInfo.getId());
        userLog.setType("转账给其他人");
        userLog.setOperationTime(DateTime.getNowDateTime());
        userLog.setReserveTime(DateTime.getAfterDateTime());
        userLog.setComment("转账账单流水号: " + orderNumber);
        userLogService.addUserLog(userLog);
        // 传出数据结果
        session.setAttribute("payUserInfo", payUserInfo);
        session.setAttribute("payAccountInfo", payAccountInfo);
        session.setAttribute("payeeUserInfo", payeeUserInfo);
        session.setAttribute("payeeAccountInfo", payeeAccountInfo);
        session.setAttribute("transferInfo", transferInfo);
        return "1";   // 转账成功
    }

    @RequestMapping("/successPage")
    public String successPage(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            return "transferSuccess";
        }
    }

}
