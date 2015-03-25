package com.socialmap.server.controller;

import com.socialmap.server.model.user.Position;
import com.socialmap.server.model.user.User;
import com.socialmap.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by yy on 3/4/15.
 */
@RestController
public class PositionSharingController {
    @Autowired
    HibernateTemplate ht;

    @Autowired
    UserService userService;

    /**
     * 获取在附近的用户
     */
    @RequestMapping(value = "/share/position/around", method = GET)
    public List<Integer> getAroundUser() {
        List<Integer> userIdList = new LinkedList<>();
        // FIXME 附近的用户暂且返回一个空列表
        return userIdList;
    }

    /**
     * @return 返回当前是否打开了位置分享
     */
    @RequestMapping(value = "/share/position", method = GET)
    public boolean getStatus() {
        return userService.getCurrentUser().isSharingPosition();
    }

    /**
     * 打开或者关闭位置分享（所有人可见）
     */
    @RequestMapping(value = "/share/position", method = POST)
    @Transactional
    public void toggle() {
        User user = userService.getCurrentUser();
        if (user.isSharingPosition()) {
            user.setSharingPosition(false);
        } else {
            user.setSharingPosition(true);
        }
        ht.update(user);
        /*PositionSharing sharing = new PositionSharing();
        sharing.setSponsor(user);
        ht.save(sharing);*/
    }

    /**
     * 更新自己的位置
     *
     * @param position 格式是“经度:纬度:半径”
     */
    @RequestMapping(value = "/share/position", method = PUT)
    @Transactional
    public void updatePosition(@RequestParam String position) {
        Position p = new Position(position);
        User user = userService.getCurrentUser();
        user.setCurrentPosition(p);
        ht.update(user);
    }

}
