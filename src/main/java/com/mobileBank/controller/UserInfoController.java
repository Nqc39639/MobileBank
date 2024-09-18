package com.mobileBank.controller;

import com.mobileBank.pojo.*;
import com.mobileBank.service.*;
import com.mobileBank.utils.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PayAccountInfoService payAccountInfoService;

    @Autowired
    private PayInfoService payInfoService;

    @Autowired
    private TransferInfoService transferInfoService;

    @Autowired
    private UserLogService userLogService;

    @RequestMapping("")
    public String check(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            return "home";
        }
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(UserInfo userInfo, HttpSession session) {
        Integer flag = userInfoService.checkUserInfo(userInfo);
        if (flag == 1) {
            userInfo = userInfoService.selectUserInfoByAccount(userInfo.getAccount());
            session.setAttribute("userInfo", userInfo);
            UserLog userLog = new UserLog();
            userLog.setUserId(userInfo.getId());
            userLog.setType("登录");
            userLog.setOperationTime(DateTime.getNowDateTime());
            userLog.setReserveTime(DateTime.getAfterDateTime());
            userLogService.addUserLog(userLog);
            return "home";
        } else if (flag == 2) {
            return "2";
        } else {
            return "0";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        UserLog userLog = new UserLog();
        userLog.setUserId(userInfo.getId());
        userLog.setType("登出");
        userLog.setOperationTime(DateTime.getNowDateTime());
        userLog.setReserveTime(DateTime.getAfterDateTime());
        userLogService.addUserLog(userLog);
        session.removeAttribute("userInfo");
        return "home";
    }

    @RequestMapping("/registerPage")
    public String registerPage() {
        return "register";
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(UserInfo userInfo) {
        // 获取身份证号的倒数第二位，并将其转为数字
        int gender = Character.getNumericValue(userInfo.getIDCard().charAt(16));
        if (gender % 2 == 0) {
            userInfo.setSex(0);   // 偶数为女性
        } else {
            userInfo.setSex(1);   // 奇数为男性
        }
        Integer flag = userInfoService.registerUserInfo(userInfo);
        if (flag == 2) {
            return "2";
        } else if (flag == 1) {
            userInfo = userInfoService.selectUserInfoByAccount(userInfo.getAccount());
            UserLog userLog = new UserLog();
            userLog.setUserId(userInfo.getId());
            userLog.setType("注册");
            userLog.setOperationTime(DateTime.getNowDateTime());
            userLog.setReserveTime(DateTime.getAfterDateTime());
            userLogService.addUserLog(userLog);
            return "login";
        } else {
            return "0";
        }
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/personPage")
    public String personPage(HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            List<PayAccountInfo> payAccountInfoList = payAccountInfoService.selectPayAccountInfoByUserId(userInfo.getId());
            Double allBalance = 0.0;
            Double payBalance = 0.0;
            Double payeeBalance = 0.0;
            Date today = new Date();
            Date startDate = DateTime.getFirstDate();
            for (PayAccountInfo payAccountInfo : payAccountInfoList) {
                // 计算总资产
                allBalance = allBalance + payAccountInfo.getBalance();
                // 计算总支出
                List<TransferInfo> transferInfoList = transferInfoService.selectTransferInfoByPayAccountIdAndDateRange(payAccountInfo.getId(), startDate, today);
                List<PayInfo> payInfoList = payInfoService.selectPayInfoByPayAccountIdAndDateRange(payAccountInfo.getId(), startDate, today);
                for (TransferInfo transferInfo : transferInfoList) {
                    payBalance = payBalance + transferInfo.getMoney();
                }
                for (PayInfo payInfo : payInfoList) {
                    payBalance = payBalance + payInfo.getMoney();
                }
                // 计算总收入
                List<TransferInfo> payeeInfoList = transferInfoService.selectTransferInfoByTargetPayAccountIdAndDateRange(payAccountInfo.getId(), startDate, today);
                for (TransferInfo transferInfo : payeeInfoList) {
                    payeeBalance = payeeBalance + transferInfo.getMoney();
                }
            }
            model.addAttribute("payAccountInfoList", payAccountInfoList);
            model.addAttribute("allBalance", allBalance);
            model.addAttribute("payBalance", payBalance);
            model.addAttribute("payeeBalance", payeeBalance);
            return "person";
        }
    }

    @RequestMapping("/transferManagePage")
    public String transferPage(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        } else {
            return "transferManage";
        }
    }

}
