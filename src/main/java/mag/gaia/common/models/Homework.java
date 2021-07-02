package mag.gaia.common.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "homework")
public class Homework {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "homework_id")
    private Long id;
    private String name;
    private String content;
    @Column(name = "chapter_id")
    private Long chapterId;
    private Date created;
    private Date modified;
    @OneToMany(mappedBy = "homework", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //放弃外键维护权，mapperBy：对方配置关系的属性名称
    private List<Member> members = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
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
}
