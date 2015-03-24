package com.socialmap.server.utils;

import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.user.Role;
import com.socialmap.server.model.user.User;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yy on 3/4/15.
 */
public class App {
    public static String realm;
    public static Image defaultUserAvatar;
    public static Image defaultUserBgimge;
    public static ApplicationContext context;
    public static Map<Integer, Role> roles = new HashMap<Integer, Role>();

    public static void encryptPassword(User user){
        String s = String.format("%s:%s:%s", user.getUsername(), App.realm, user.getPassword());
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        String hex = new String(Hex.encode(digest.digest(s.getBytes())));
        user.setPassword(hex);
    }
}
