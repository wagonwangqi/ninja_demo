package mag.gaia.web.dto;

import java.util.List;

public class MenuDto {
    private String categoryName;
    private List<CourseDto> courses;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CourseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDto> courses) {
        this.courses = courses;
    }
}
