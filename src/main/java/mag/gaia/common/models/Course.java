package mag.gaia.common.models;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "cou_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couId;
    @Column(name = "cou_name")
    private String couName;
    @Column(name = "cou_pic")
    private String couPic;
    @Column(name = "cou_vid")
    private String couVideo;
    @Column(name = "cou_des")
    private String couDescription;
    @Column(name = "smallCategory_name")
    private String couSmall;
    @Column(name = "bigCategory_name")
    private String couBig;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //放弃外键维护权，mapperBy：对方配置关系的属性名称
    private List<Chapter> chapters = new ArrayList<>();
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //放弃外键维护权，mapperBy：对方配置关系的属性名称
    private List<Note> notes = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "sta_id")
    @JSONField(serialize = false)
    private Staff staff;
    @ManyToMany(mappedBy = "courses",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JSONField(serialize = false)
    private List<Member> members=new ArrayList<>();
    private Date created;
    private Date modified;

    public Course() {
        this.created = new Date();
        this.modified = this.created;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getCouSmall() {
        return couSmall;
    }

    public void setCouSmall(String couSmall) {
        this.couSmall = couSmall;
    }

    public String getCouBig() {
        return couBig;
    }

    public void setCouBig(String couBig) {
        this.couBig = couBig;
    }

    public String getCouPic() {
        return couPic;
    }

    public void setCouPic(String couPic) {
        this.couPic = couPic;
    }

    public Long getCouId() {
        return couId;
    }

    public void setCouId(Long couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getCouVideo() {
        return couVideo;
    }

    public void setCouVideo(String couVideo) {
        this.couVideo = couVideo;
    }

    public String getCouDescription() {
        return couDescription;
    }

    public void setCouDescription(String couDescription) {
        this.couDescription = couDescription;
    }
}
