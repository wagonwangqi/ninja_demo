package mag.gaia.common.models;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Long memId;
    @Column(name = "login_id")
    private String loginId;
    private String uid;
    private String level;
    private String email;
    private String password;
    @Column(name = "mem_name")
    private String memberName;
    @ManyToMany
    @JoinTable(name = "mem_cou",//中间表的名称
            //joinColumns当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "mem_id",referencedColumnName = "mem_id")},
            //inverseJoinColumns对方对象在中间表中的外键
            inverseJoinColumns = {@JoinColumn(name = "cou_id",referencedColumnName = "cou_id")}
    )
    private List<Course> courses=new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "homework_id")
    @JSONField(serialize = false)
    private Homework homework;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Member() {
        this.uid       = UUID.randomUUID().toString();
        this.level     = "member";
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
