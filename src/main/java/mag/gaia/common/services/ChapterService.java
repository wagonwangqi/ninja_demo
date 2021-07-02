package mag.gaia.common.services;

import com.google.inject.Inject;
import mag.gaia.common.dao.ChapterDao;
import mag.gaia.common.models.Chapter;
import mag.gaia.common.models.Course;
import mag.gaia.common.models.Homework;
import mag.gaia.common.models.Video;

import java.util.List;

public class ChapterService {
    @Inject
    ChapterDao chapterDao;

    public List<Chapter> chapterList(Course course, Long chapterId) {
        return chapterDao.chapterList(course, chapterId);
    }

    public List<Chapter> chapterList(Course course) {
        return chapterDao.chapterList(course);
    }

    public Video findVideo(Long chapterId) {
        return chapterDao.findVideo(chapterId);

    }
    public List<Course> getCourseList(){
        return chapterDao.getCourseList();
    }
    public List<Chapter>getChapterList(Long courseId){
        return  chapterDao.getChapterList(courseId);
    }
public List<Homework>getHomeworkList(Long chapterId){
        return chapterDao.getHomeworkList(chapterId);
}
}
