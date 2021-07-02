package mag.gaia.staff.dto;

import java.util.List;

public class BigCategoryDto {

    private String bigCategoryName;
    private List<SmallCategoryDto> smallCategoryDtos;

    public String getBigCategoryName() {
        return bigCategoryName;
    }

    public void setBigCategoryName(String bigCategoryName) {
        this.bigCategoryName = bigCategoryName;
    }

    public List<SmallCategoryDto> getSmallCategoryDtos() {
        return smallCategoryDtos;
    }

    public void setSmallCategoryDtos(List<SmallCategoryDto> smallCategoryDtos) {
        this.smallCategoryDtos = smallCategoryDtos;
    }
}
