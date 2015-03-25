package com.socialmap.server.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.common.Video;
import com.socialmap.server.model.resource.Team;
import com.socialmap.server.model.sos.SosContact;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by yy on 3/6/15.
 */
@Entity
@org.hibernate.annotations.Entity(
        dynamicInsert = true
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private String about = "";
    private Image avatar;
    private Image bgimage;
    private Date birthday = new Date();
    private Set<ChatGroup> chatGroups = new HashSet<ChatGroup>();
    private Position currentPosition;
    private String    email      = "";
    private boolean   emailValid = false;
    private boolean   enabled    = true;
    private int       exp        = 0;
    private String    footprints = "";
    private Set<User> friends    = new HashSet<>();
    private Gender    gender     = Gender.UNSELECTED;
    private int id;
    private String                  idcard          = "";
    private Date                    lastLoginTime   = new Date();
    private Date                    lastLogoutTime  = new Date();
    private int                     level           = 0;
    private String                  nickname        = "";
    private String                  password        = "";
    private String                  phone           = "";
    private boolean                 realValid       = false;
    private String                  realname        = "";
    private Date                    registerTime    = new Date();
    private Set<Role>               roles           = new HashSet<Role>();
    private int                     securityLevel   = 0;
    private boolean                 sharingPosition = false;
    private Map<String, SosContact> sosContacts     = new HashMap<String, SosContact>();
    private String                  status          = "offline";// online offline
    private Set<Team>               teams           = new HashSet<Team>();
    private float                   tripYears       = 0;
    private String                  username        = "";
    private Set<Video>              videos          = new HashSet<Video>();

    public enum Gender {
        MALE, FEMALE, UNSELECTED
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @OneToOne
    @Cascade(CascadeType.ALL)
    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    @OneToOne
    @Cascade(CascadeType.ALL)
    public Image getBgimage() {
        return bgimage;
    }

    public void setBgimage(Image bgimage) {
        this.bgimage = bgimage;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CHATGROUP_ID")}
    )
    public Set<ChatGroup> getChatGroups() {
        return chatGroups;
    }

    public void setChatGroups(Set<ChatGroup> chatGroups) {
        this.chatGroups = chatGroups;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getFootprints() {
        return footprints;
    }

    public void setFootprints(String footprints) {
        this.footprints = footprints;
    }

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "USER_A_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_B_ID")
    )
    @Cascade(CascadeType.SAVE_UPDATE)
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @ManyToMany
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE")}
    )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }

    @ManyToMany
    @JoinTable(
            name = "SOS_CONTACTS",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SOS_ID")}
    )
    @MapKeyColumn(name = "RELATIONSHIP")
    @Cascade(CascadeType.SAVE_UPDATE)
    public Map<String, SosContact> getSosContacts() {
        return sosContacts;
    }

    public void setSosContacts(Map<String, SosContact> sosContacts) {
        this.sosContacts = sosContacts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TEAM_ID")}
    )
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public float getTripYears() {
        return tripYears;
    }

    public void setTripYears(float tripYears) {
        this.tripYears = tripYears;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany
    @JoinColumn(name = "USER_ID")
    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }

    public boolean isEmailValid() {
        return emailValid;
    }

    public void setEmailValid(boolean emailValid) {
        this.emailValid = emailValid;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isRealValid() {
        return realValid;
    }

    public void setRealValid(boolean realValid) {
        this.realValid = realValid;
    }

    public boolean isSharingPosition() {
        return sharingPosition;
    }

    public void setSharingPosition(boolean sharingPosition) {
        this.sharingPosition = sharingPosition;
    }
}
