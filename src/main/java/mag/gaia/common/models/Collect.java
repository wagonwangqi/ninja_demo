package mag.gaia.common.models;

import javax.persistence.*;

@Entity
@Table(name="mem_cou")
public class Collect {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cou_id")
    private Long couId;
    @Column(name = "mem_id")
    private Long memId;
    @Column(name = "is_collect")
    private Boolean isCollect;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouId() {
        return couId;
    }

    public void setCouId(Long couId) {
        this.couId = couId;
    }

    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }

    public Boolean getCollect() {
        return isCollect;
    }

    public void setCollect(Boolean collect) {
        isCollect = collect;
    }
}
