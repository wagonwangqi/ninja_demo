package mag.gaia.admin.dto;

import mag.gaia.common.models.SmallCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private Long id;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
