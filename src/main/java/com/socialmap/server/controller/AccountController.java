package com.socialmap.server.controller;

import com.socialmap.server.dao.UserDao;
import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.resource.Team;
import com.socialmap.server.model.sos.SosContact;
import com.socialmap.server.model.user.Role;
import com.socialmap.server.model.user.User;
import com.socialmap.server.service.TeamService;
import com.socialmap.server.service.UserService;
import com.socialmap.server.utils.App;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * Created by yy on 3/6/15.
 */
@RestController
public class AccountController {

    @Autowired
    UserDao userDao;

    @Autowired
    HibernateTemplate ht;

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    /**
     * 添加一个SOS急救联系人
     *
     * @param name
     * @param phone
     * @param relationship
     */
    @RequestMapping(value = "/profile/sos", method = RequestMethod.POST)
    @Transactional
    public void addASosContact(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String relationship
    ) {
        SosContact sosContact = new SosContact();
        sosContact.setName(name);
        sosContact.setPhone(phone);
        User user = userService.getCurrentUser();
        user.getSosContacts().put(relationship, sosContact);
        ht.update(user);
    }

    /**
     * 添加朋友
     *
     * @param id
     */
    @RequestMapping(value = "/profile/friend/{id}", method = RequestMethod.POST)
    @Transactional
    public void addFriend(@PathVariable int id) {
        // FIXME 向id指向的用户发送朋友申请，当对方确认后才能添加成为朋友
        User friend = userService.findUserById(id);
        User user = userService.getCurrentUser();
        user.getFriends().add(friend);
        ht.update(user);
    }

    /**
     * 修改密码
     *
     * @param newPassword
     * @param token
     * @return 返回true代表密码修改成功
     */
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @Transactional
    public boolean changePassword(@RequestParam String newPassword, @RequestParam String token) {
        // TODO 检测 token 是否正确有效
        User user = userService.getCurrentUser();
        user.setPassword(newPassword);
        App.encryptPassword(user);
        ht.update(user);
        return true;
    }

    /**
     * 用户注册时，检查电话，身份证，用户名，或者电子邮件是否可用
     * 请务必只提供一个参数
     *
     * @param phone
     * @param idcard
     * @param username
     * @param email
     * @return
     */
    @RequestMapping(value = "/check/existence", method = RequestMethod.GET)
    @Transactional
    public boolean checkExistence(
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String idcard,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email
    ) {
        if (phone != null) {
            List r = ht.find("from User where phone=?", phone);
            if (r.size() > 0) {
                return true;
            }
        } else if (idcard != null) {
            List r = ht.find("from User where idcard=?", idcard);
            if (r.size() > 0) {
                return true;
            }
        } else if (username != null) {
            List r = ht.find("from User where username=?", username);
            if (r.size() > 0) {
                return true;
            }
        } else if (email != null) {
            List r = ht.find("from User where email=?", email);
            if (r.size() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 注册时提交用户得到的短信验证码，如果正确则可用进行下一步设置密码的操作
     *
     * @param vcode
     * @return
     */
    @RequestMapping(value = "/register/vcode", method = RequestMethod.POST)
    public boolean checkVcode(@RequestParam String vcode) {
        return vcode.equals("1234");
    }

    /**
     * 删除一个SOS联系人
     *
     * @param id
     */
    @RequestMapping(value = "/profile/team/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void deleteASosContact(@PathVariable int id) {
        User user = userService.getCurrentUser();
        for (SosContact contact : user.getSosContacts().values()) {
            if (contact.getId() == id) {
                user.getSosContacts().remove(contact);
                break;
            }
        }
        ht.update(user);
    }

    /**
     * 用户删除头像，换回系统默认头像
     */
    @RequestMapping(value = "/profile/avatar", method = RequestMethod.DELETE)
    @Transactional
    public void deleteAvatar() {
        User user = userService.getCurrentUser();
        Image avatar = user.getAvatar();
        user.setAvatar(App.defaultUserAvatar);
        ht.delete(avatar);
    }

    /**
     * 用户删除头像，换回系统默认头像
     */
    @RequestMapping(value = "/profile/bgimage", method = RequestMethod.DELETE)
    @Transactional
    public void deleteBgimage() {
        User user = userService.getCurrentUser();
        Image bgimage = user.getBgimage();
        user.setAvatar(App.defaultUserBgimge);
        ht.delete(bgimage);
    }

    /**
     * 删除朋友
     *
     * @param id
     */
    @RequestMapping(value = "/profile/friend/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void deleteFriend(@PathVariable int id) {
        User user = userService.getCurrentUser();
        for (User friend : user.getFriends()) {
            if (friend.getId() == id) {
                user.getFriends().remove(friend);
                break;
            }
        }
        ht.update(user);
    }

    /**
     * 获得用户头像
     *
     * @return image/png 类型的HTTP返回值
     */
    @RequestMapping(value = "/profile/avatar", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAvatar() {
        User u = userService.getCurrentUser();
        byte[] image = (byte[]) ht.find("select u.avatar.data from User u where u.id=?", u.getId()).get(0);
        return image;
    }

    /**
     * 获得用户背景图片
     *
     * @return image/png 类型的HTTP返回值
     */
    @RequestMapping(value = "/profile/bgimage", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getBgimage() {
        User u = userService.getCurrentUser();
        byte[] image = (byte[]) ht.find("select u.bgimage.data from User u where u.id=?", u.getId()).get(0);
        return image;
    }

    /**
     * 获取朋友的用户ID列表
     *
     * @return
     */
    @RequestMapping(value = "/profile/friends", method = RequestMethod.GET)
    public List<Integer> getFriends() {
        User user = userService.getCurrentUser();
        List<Integer> friendIdList = new ArrayList<>();
        for (User friend : user.getFriends()) {
            friendIdList.add(friend.getId());
        }
        return friendIdList;
    }

    /**
     * 获取用户资料
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public Map<String, String> getProfile() {
        Map<String, String> profile = new HashMap<>();
        User user = userService.getCurrentUser();
        profile.put("username", user.getUsername());
        profile.put("realname", user.getRealname());
        profile.put("nickname", user.getNickname());
        profile.put("phone", user.getPhone());
        profile.put("email", user.getEmail());
        profile.put("idcard", user.getIdcard());
        profile.put("about", user.getAbout());
        return profile;
    }

    /**
     * 获取SOS联系人列表
     * 每个联系人包括，姓名，联系电话，和当前用户的关系
     *
     * @return
     */
    @RequestMapping(value = "/profile/sos", method = RequestMethod.GET)
    public List<Map<String, String>> getSosContactList() {
        User user = userService.getCurrentUser();
        List<Map<String, String>> list = new ArrayList<>();
        for (Map.Entry<String, SosContact> entry : user.getSosContacts().entrySet()) {
            Map<String, String> sos = new HashMap<>();
            sos.put("name", entry.getValue().getName());
            sos.put("phone", entry.getValue().getPhone());
            sos.put("relationship", entry.getKey());
            sos.put("id", entry.getValue().getId() + "");
            list.add(sos);
        }
        return list;
    }

    /**
     * 获取所在团队的ID列表
     *
     * @return
     */
    @RequestMapping(value = "/profile/teams", method = RequestMethod.GET)
    public List<Integer> getTeams() {
        User user = userService.getCurrentUser();
        List<Integer> teamIdList = new ArrayList<>();
        for (Team team : user.getTeams()) {
            teamIdList.add(team.getId());
        }
        return teamIdList;
    }

    /**
     * 修改密码前需要提供能唯一标识用户身份的信息，来获取修改密码必须的token
     * 一般token通过短信，邮件，以一个网址连接的形式发送给用户，用户点击链接打开浏览器登录网页来修改密码
     * 同时也提供一种以和app交互的模式，通过https加密协议传送token至客户端
     * 所以需要提供参数type来指定获取token的方式
     * sms, email, https
     * type=sms|email 代表使用短信和邮件两种方式来告知用户修改密码的方式
     *
     * @param phone
     * @param idcard
     * @param username
     * @param email
     * @param type
     * @return 现在作为测试，token固定为“abcdefghijklmnopqrstuvwxyz0123456789....”
     */
    @RequestMapping(value = "/password/token", method = RequestMethod.GET)
    @Transactional
    public String getToken(
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String idcard,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam String type
    ) {
        // TODO 完善修改密码时获取必要token的实现
        return "abcdefghijklmnopqrstuvwxyz0123456789....";
    }

    /**
     * 注册时获取验证码，现在仅仅是模拟手机注册，验证码是1234
     *
     * @param phone
     * @return 系统发送验证码成功返回true
     */
    @RequestMapping(value = "/register/vcode", method = RequestMethod.GET)
    public boolean getVcode(@RequestParam String phone) {
        // TODO 向手机号phone发送注册短信验证码
        return true;
    }

    /**
     * 加入到一个团队中
     *
     * @param id
     */
    @RequestMapping(value = "/profile/team/{id}", method = RequestMethod.POST)
    @Transactional
    public void joinTeam(@PathVariable int id) {
        // FIXME 向id指向的团队发送加入申请，当对方确认后才能成为团队的成员
        Team team = ht.load(Team.class, id);
        User user = userService.getCurrentUser();
        user.getTeams().add(team);
        ht.update(user);
    }

    /**
     * 登陆
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Transactional
    public void login() {
        User u = userService.getCurrentUser();
        u.setStatus("online");
        u.setLastLoginTime(new Date());
        ht.update(u);
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/login", method = RequestMethod.DELETE)
    @Transactional
    public void logout() {
        User u = userService.getCurrentUser();
        u.setStatus("offline");
        u.setLastLogoutTime(new Date());
        ht.update(u);
    }

    /**
     * 离开一个团队
     *
     * @param id
     */
    @RequestMapping(value = "/profile/team/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void quitTeam(@PathVariable int id) {
        User user = userService.getCurrentUser();
        for (Team team : user.getTeams()) {
            if (team.getId() == id) {
                user.getTeams().remove(team);
                break;
            }
        }
        ht.update(user);
    }

    /**
     * 注册，暂且 username = phone，
     * 注册的时候需要提供，username，password，phone三个参数
     *
     * @param user
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional
    public void register(@Valid User user) {
        // TODO 确认用户的手机号码已经通过验证

        //加个默认角色
        Role r = (Role) ht.find("from Role where name=?", "ROLE_USER").get(0);
        Set<Role> roles = new HashSet<Role>();
        roles.add(r);
        user.setRoles(roles);

        //设置一个默认头像
        Image avatar = (Image) ht.find("from Image i where i.remarks=?", "默认用户头像").get(0);
        user.setAvatar(avatar);

        App.encryptPassword(user);
        ht.save(user);
    }

    /**
     * 删除账户
     */
    @RequestMapping(value = "/register", method = RequestMethod.DELETE)
    public void unregister() {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 用户上传自定义头像
     *
     * @param data
     */
    @RequestMapping(value = "/profile/avatar", method = RequestMethod.PUT)
    @Transactional
    public void updateAvatar(@RequestParam MultipartFile data) {
        try {
            Image newAvatar = new Image();
            newAvatar.setData(IOUtils.toByteArray(data.getInputStream()));
            User user = userService.getCurrentUser();
            user.setAvatar(newAvatar);

            ht.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户上传自定义背景图片
     *
     * @param data
     */
    @RequestMapping(value = "/profile/bgimage", method = RequestMethod.PUT)
    @Transactional
    public void updateBgimage(@RequestParam MultipartFile data) {
        try {
            Image newBgimage = new Image();
            newBgimage.setData(IOUtils.toByteArray(data.getInputStream()));
            User user = userService.getCurrentUser();
            user.setBgimage(newBgimage);

            ht.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新用户资料
     *
     * @param username
     * @param about
     */
    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    public void updateProfile(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String about
    ) {
        User user = userService.getCurrentUser();
        if (username != null) {
            user.setUsername(username);
        }
        if (about!=null){
            user.setAbout(about);
        }
    }
}
