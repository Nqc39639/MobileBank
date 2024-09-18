package com.mobileBank.controller;

import com.github.pagehelper.PageInfo;
import com.mobileBank.pojo.AdminInfo;
import com.mobileBank.pojo.AdminLog;
import com.mobileBank.pojo.UserInfo;
import com.mobileBank.pojo.UserLog;
import com.mobileBank.service.AdminInfoService;
import com.mobileBank.service.AdminLogService;
import com.mobileBank.service.UserInfoService;
import com.mobileBank.service.UserLogService;
import com.mobileBank.utils.DataInfo;
import com.mobileBank.utils.DateTime;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminInfoController {
    @Autowired
    private AdminInfoService adminInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private AdminLogService adminLogService;

    @RequestMapping("")
    public String check(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/index";
        }
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(AdminInfo adminInfo, HttpSession session, Model model) {
        Integer flag = adminInfoService.checkAdminInfo(adminInfo);
        if (flag == 1) {
            adminInfo = adminInfoService.selectAdminInfoByAccount(adminInfo.getAccount());
            session.setAttribute("adminInfo", adminInfo);
            AdminLog adminLog = new AdminLog();
            adminLog.setAdminId(adminInfo.getId());
            adminLog.setType("登录");
            adminLog.setOperationTime(DateTime.getNowDateTime());
            adminLog.setReserveTime(DateTime.getAfterDateTime());
            adminLogService.addAdminLog(adminLog);
            return "admin/index";
        } else if (flag == 2) {
            model.addAttribute("msg", "2");
            // return "2";
        } else {
            model.addAttribute("msg", "0");
            // return "0";
        }
        return "admin/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        AdminLog adminLog = new AdminLog();
        adminLog.setAdminId(adminInfo.getId());
        adminLog.setType("登出");
        adminLog.setOperationTime(DateTime.getNowDateTime());
        adminLog.setReserveTime(DateTime.getAfterDateTime());
        adminLogService.addAdminLog(adminLog);
        session.removeAttribute("adminInfo");
        return "admin/login";
    }

    @RequestMapping("/home")
    public String home(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/home";
        }
    }

    @RequestMapping("/index")
    public String index(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/index";
        }
    }

    @RequestMapping("/adminInfoSettingPage")
    public String adminInfoSettingPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/adminInfoSetting";
        }
    }

    @RequestMapping("/updateAdminPasswordPage")
    public String updateAdminPasswordPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/updateAdminPassword";
        }
    }

    @RequestMapping("/updateAdminPassword")
    @ResponseBody
    public DataInfo updateAdminPassword(String oldPassword, String newPassword, HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (!oldPassword.equals(adminInfo.getPassword())) {
            return DataInfo.fail();
        }
        adminInfo.setPassword(newPassword);
        adminInfoService.updateAdminInfo(adminInfo);
        AdminLog adminLog = new AdminLog();
        adminLog.setAdminId(adminInfo.getId());
        adminLog.setType("修改登录密码");
        adminLog.setOperationTime(DateTime.getNowDateTime());
        adminLog.setReserveTime(DateTime.getAfterDateTime());
        adminLogService.addAdminLog(adminLog);
        return DataInfo.ok();
    }

    @RequestMapping("/statActiveStatusOfUserPage")
    public String statActiveStatusOfUserPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/statActiveStatusOfUser";
        }
    }

    @RequestMapping("/statTradeMoneyPage")
    public String statTradeMoneyPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/statTradeMoney";
        }
    }

    @RequestMapping("/normalUserPage")
    public String normalUserPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/normalUser";
        }
    }

    /**
     * 查询正常用户数据
     */
    @RequestMapping("/readerNormalUser")
    @ResponseBody
    public DataInfo readerNormalUser(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit, HttpServletRequest request) {
        String searchParams = request.getParameter("searchParams") == null ? "" : request.getParameter("searchParams");
        if (searchParams.isEmpty()) {
            PageInfo<UserInfo> pageInfo = userInfoService.selectNormalUserInfo(pageNum, limit);
            return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
        } else {
            PageInfo<UserInfo> pageInfo = userInfoService.selectNormalUserInfoBySearch(searchParams, pageNum, limit);
            return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
        }
    }

    /**
     * 功能：跳转到增加用户的页面
     */
    @RequestMapping("/addUserInfoPage")
    public String toAddUserInfoPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/usersTable/add";
        }
    }

    /**
     * 功能：增加用户信息
     */
    @RequestMapping("/addUserInfo")
    @ResponseBody
    public Integer addUserInfo(HttpSession session, HttpServletRequest request) {
        UserInfo userInfo = getUserInfo(request);
        Integer result = userInfoService.addUserInfo(userInfo);
        if (result > 0) {
            AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
            AdminLog adminLog = new AdminLog();
            adminLog.setAdminId(adminInfo.getId());
            adminLog.setType("添加用户信息");
            adminLog.setOperationTime(DateTime.getNowDateTime());
            adminLog.setReserveTime(DateTime.getAfterDateTime());
            adminLog.setComment("添加的用户id: " + userInfoService.selectUserInfoByAccount(userInfo.getAccount()).getId());
            adminLogService.addAdminLog(adminLog);
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 伪删除正常用户
     */
    @RequestMapping("/deleteNormalUserInfo")
    @ResponseBody
    public String deleteNormalUserInfo(HttpSession session, HttpServletRequest request) {
        String idString = request.getParameter("idString");
        List<String> idList = Arrays.asList(idString.split(","));
        int[] ids = new int[idList.size()];
        for (int i = 0; i < idList.size(); i++) {
            ids[i] = Integer.parseInt(idList.get(i));
        }
        Integer result = userInfoService.updateStatusNormalUser(ids);
        if (result > 0) {
            AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
            AdminLog adminLog = new AdminLog();
            adminLog.setAdminId(adminInfo.getId());
            adminLog.setType("注销用户信息");
            adminLog.setOperationTime(DateTime.getNowDateTime());
            adminLog.setReserveTime(DateTime.getAfterDateTime());
            adminLog.setComment("注销的用户id列表: " + Arrays.toString(ids));
            adminLogService.addAdminLog(adminLog);
            return "success";
        } else {
            return "failure";
        }
    }

    /**
     * 功能：跳转到修改用户信息的页面
     */
    @RequestMapping("/updateUserInfoPage")
    public String updateUserInfoPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/usersTable/edit";
        }
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/update")
    @ResponseBody
    public DataInfo updateUserInfo(HttpSession session, HttpServletRequest request) {
        UserInfo userInfo = getUserInfo(request);
        Integer result = userInfoService.updateUserInfo(userInfo);
        if (result == 1) {
            AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
            AdminLog adminLog = new AdminLog();
            adminLog.setAdminId(adminInfo.getId());
            adminLog.setType("修改用户信息");
            adminLog.setOperationTime(DateTime.getNowDateTime());
            adminLog.setReserveTime(DateTime.getAfterDateTime());
            adminLog.setComment("修改的用户id: " + userInfo.getId());
            adminLogService.addAdminLog(adminLog);
            return DataInfo.ok();
        } else {
            return DataInfo.fail();
        }
    }

    /**
     * 功能：跳转到查看用户信息的页面
     */
    @RequestMapping("/lookUserInfoPage")
    public String lookUserInfoPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {

            return "admin/usersTable/look";
        }
    }

    @RequestMapping("/abnormalUserPage")
    public String abnormalUserPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/abnormalUser";
        }
    }

    /**
     * 查询已注销用户数据
     */
    @RequestMapping("/readerAbnormalUser")
    @ResponseBody
    public DataInfo readerAbnormalUser(UserInfo userInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit, HttpServletRequest request) {
        String searchParams = request.getParameter("searchParams") == null ? "" : request.getParameter("searchParams");
        if (searchParams.isEmpty()) {
            PageInfo<UserInfo> pageInfo = userInfoService.selectAbnormalUserInfo(pageNum, limit);
            return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
        } else {
            PageInfo<UserInfo> pageInfo = userInfoService.selectAbnormalUserInfoBySearch(searchParams, pageNum, limit);
            return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
        }
    }

    /**
     * 伪删除正常用户
     */
    @RequestMapping("/returnNormalUserInfo")
    @ResponseBody
    public String returnNormalUserInfo(HttpSession session, HttpServletRequest request) {
        String idString = request.getParameter("idString");
        List<String> idList = Arrays.asList(idString.split(","));
        int[] ids = new int[idList.size()];
        for (int i = 0; i < idList.size(); i++) {
            ids[i] = Integer.parseInt(idList.get(i));
        }
        Integer result = userInfoService.updateStatusAbnormalUser(ids);
        if (result > 0) {
            AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
            AdminLog adminLog = new AdminLog();
            adminLog.setAdminId(adminInfo.getId());
            adminLog.setType("恢复已注销用户");
            adminLog.setOperationTime(DateTime.getNowDateTime());
            adminLog.setReserveTime(DateTime.getAfterDateTime());
            adminLog.setComment("恢复的用户id列表: " + Arrays.toString(ids));
            adminLogService.addAdminLog(adminLog);
            return "success";
        } else {
            return "failure";
        }
    }

    @RequestMapping("/normalPayAccountPage")
    public String normalPayAccountPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/normalPayAccount";
        }
    }

    @RequestMapping("/abnormalPayAccountPage")
    public String abnormalPayAccountPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/abnormalPayAccount";
        }
    }

    @RequestMapping("/userLogPage")
    public String userLogPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/userLog";
        }
    }

    /**
     * 查询用户日志
     */
    @RequestMapping("/readerUserLog")
    @ResponseBody
    public DataInfo readerUserLog(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit, HttpServletRequest request) {
        String searchParams = request.getParameter("searchParams") == null ? "" : request.getParameter("searchParams");
        if (searchParams.isEmpty()) {
            PageInfo<UserLog> pageInfo = userLogService.selectAllUserLog(pageNum, limit);
            return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
        } else {
            PageInfo<UserLog> pageInfo = userLogService.selectUserLogBySearch(searchParams, pageNum, limit);
            return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
        }
    }

    @RequestMapping("/adminLogPage")
    public String adminLogPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/adminLog";
        }
    }

    /**
     * 查询管理员日志
     */
    @RequestMapping("/readerAdminLog")
    @ResponseBody
    public DataInfo readerAdminLog(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "15") Integer limit, HttpServletRequest request) {
        String searchParams = request.getParameter("searchParams") == null ? "" : request.getParameter("searchParams");
        if (searchParams.isEmpty()) {
            PageInfo<AdminLog> pageInfo = adminLogService.selectAllAdminLog(pageNum, limit);
            return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
        } else {
            PageInfo<AdminLog> pageInfo = adminLogService.selectAdminLogBySearch(searchParams, pageNum, limit);
            return DataInfo.ok("成功", pageInfo.getTotal(), pageInfo.getList());
        }
    }

    @RequestMapping("/iconsPage")
    public String iconsPage(HttpSession session) {
        AdminInfo adminInfo = (AdminInfo) session.getAttribute("adminInfo");
        if (adminInfo == null) {
            return "admin/login";
        } else {
            return "admin/icon";
        }
    }

    /**
     * 功能：获取UserInfo信息
     */
    @SneakyThrows
    private UserInfo getUserInfo(HttpServletRequest request) {
        String id = request.getParameter("id") == null ? "" : request.getParameter("id");
        String realName = request.getParameter("realname");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String IDCard = request.getParameter("IDCard");
        String telephone = request.getParameter("telephone");
        String address = request.getParameter("address");
        String birthPlace = request.getParameter("birthPlace");
        String creditGrade = request.getParameter("creditGrade");
        String status = request.getParameter("status");
        UserInfo userInfo = new UserInfo();
        if (!id.isEmpty()) userInfo.setId(Integer.valueOf(id));
        if (realName != null && !realName.isEmpty()) userInfo.setRealname(realName);
        userInfo.setAccount(account);
        userInfo.setPassword(password);
        if (IDCard != null && !IDCard.isEmpty()) {
            userInfo.setIDCard(IDCard);
            // 获取身份证号的倒数第二位，并将其转为数字
            int gender = Character.getNumericValue(userInfo.getIDCard().charAt(16));
            if (gender % 2 == 0) {
                userInfo.setSex(0);   // 偶数为女性
            } else {
                userInfo.setSex(1);   // 奇数为男性
            }
        }
        if (telephone != null && !telephone.isEmpty()) userInfo.setTelephone(telephone);
        if (address != null && !address.isEmpty()) userInfo.setAddress(address);
        if (birthPlace != null && !birthPlace.isEmpty()) userInfo.setBirthPlace(birthPlace);
        if (creditGrade != null && !creditGrade.isEmpty()) userInfo.setCreditGrade(Integer.valueOf(creditGrade));
        if (status != null && !status.isEmpty()) userInfo.setStatus(Integer.valueOf(status));
        return userInfo;
    }

}
