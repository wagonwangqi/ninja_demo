package mag.gaia.common.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bigCategory")
public class BigCategory implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "big_categoryName")
    private String bigCategoryName;

    @OneToMany(mappedBy = "bigCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //放弃外键维护权，mapperBy：对方配置关系的属性名称
    private List<SmallCategory> smallCategories = new ArrayList<>();

    public List<SmallCategory> getSmallCategories() {
        return smallCategories;
    }

    public void setSmallCategories(List<SmallCategory> smallCategories) {
        this.smallCategories = smallCategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBigCategoryName() {
        return bigCategoryName;
    }

    public void setBigCategoryName(String bigCategoryName) {
        this.bigCategoryName = bigCategoryName;
    }

}
