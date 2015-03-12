package com.socialmap.server.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.common.Video;
import com.socialmap.server.model.resource.Team;
import com.socialmap.server.model.sos.Sos;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User implements UserDetails, Serializable {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String realname;
    private String idcard;
    private float tripYears;
    private String footprints;
    private Date birthday;
    private Gender gender;
    private Set<Role> authorities = new HashSet<Role>();
    private Set<Team> teams = new HashSet<Team>();
    private boolean enabled = true;
    private String about;
    private Image avatar;
    private Image bgimage;
    private String phone;
    private String email;
    private Set<Video> videos = new HashSet<Video>();
    private Map<String, Sos> soses = new HashMap<String, Sos>();
    private Date registerTime;
    private Date lastLoginTime;
    private Date lastLogoutTime;
    private int level;
    private int exp;
    private String status;// online offline
    private int securityLevel;
    private boolean emailValid;
    private boolean realValid;
    private Set<ChatGroup> chatGroups = new HashSet<ChatGroup>();

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "chatgroup_id")}
    )
    public Set<ChatGroup> getChatGroups() {
        return chatGroups;
    }

    public void setChatGroups(Set<ChatGroup> chatGroups) {
        this.chatGroups = chatGroups;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }

    public boolean isEmailValid() {
        return emailValid;
    }

    public void setEmailValid(boolean emailValid) {
        this.emailValid = emailValid;
    }

    public boolean isRealValid() {
        return realValid;
    }

    public void setRealValid(boolean realValid) {
        this.realValid = realValid;
    }

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "sos_id")}
    )
    @MapKey(name = "relationship")
    public Map<String, Sos> getSoses() {
        return soses;
    }

    public void setSoses(Map<String, Sos> soses) {
        this.soses = soses;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany
    @JoinColumn(name = "user_id")
    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public float getTripYears() {
        return tripYears;
    }

    public void setTripYears(float tripYears) {
        this.tripYears = tripYears;
    }

    public String getFootprints() {
        return footprints;
    }

    public void setFootprints(String footprints) {
        this.footprints = footprints;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id")}
    )
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Transient
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public enum Gender {
        MALE, FEMALE, UNSELECTED
    }
}
