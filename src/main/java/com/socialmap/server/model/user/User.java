package com.socialmap.server.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.common.Video;
import com.socialmap.server.model.resource.Team;
import com.socialmap.server.model.sos.Sos;

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
    private String about;
    private Image avatar;
    private Image bgimage;
    private Date birthday;
    private Set<ChatGroup> chatGroups = new HashSet<ChatGroup>();
    private Position currentPosition;
    private String   email;
    private boolean  emailValid;
    private boolean enabled = true;
    private int     exp;
    private String  footprints;
    private Gender  gender;
    private int     id;
    private String  idcard;
    private Date    lastLoginTime;
    private Date    lastLogoutTime;
    private int     level;
    private String  nickname;
    private String  password;
    private String  phone;
    private boolean realValid;
    private String  realname;
    private Date    registerTime;
    private Set<Role> roles = new HashSet<Role>();
    private int securityLevel;
    private Map<String, Sos> sosContact = new HashMap<String, Sos>();
    private String status;// online offline
    private Set<Team> teams = new HashSet<Team>();
    private float  tripYears;
    private String username;
    private Set<Video> videos = new HashSet<Video>();

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
    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    @OneToOne
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
            name = "SOS_RELATIONSHIP",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SOS_ID")}
    )
    @MapKeyColumn(name = "RELATIONSHIP")
    public Map<String, Sos> getSosContact() {
        return sosContact;
    }

    public void setSosContact(Map<String, Sos> sosContact) {
        this.sosContact = sosContact;
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
}
