package mag.gaia.common.services;

import com.google.inject.Inject;
import mag.gaia.common.dao.CollectDao;
import mag.gaia.common.dao.CourseDao;
import mag.gaia.common.enums.Collects;
import mag.gaia.common.models.Collect;
import mag.gaia.common.models.Course;
import mag.gaia.common.models.Member;
import mag.gaia.common.utils.Page;
import ninja.uploads.FileItem;

import java.io.IOException;
import java.util.List;

public class CourseService {
    @Inject
    CourseDao courseDao;
    @Inject
    CollectDao collectDao;
    public  void savePic( FileItem upFilePic) throws IOException {
        courseDao.saveFile(upFilePic);
    }

    public void saveVid(FileItem upFileVideo) throws IOException {
        courseDao.saveFile(upFileVideo);
    }
    public Collects isCollect(Long courseId, Long memberId){
        return  collectDao.isCollect(courseId,memberId);
    }

    public void newCollect(Long courseId, Long memberId) {
        collectDao.newCollect(courseId,memberId);
    }

    public Collect selectCollect(Long courseId, Long memberId) {
        return collectDao.selectCollect(courseId,memberId);
    }

    public void oldCollect(Collect collect) {
        collectDao.oldCollect(collect);
    }

    public Page<Course> searchByCourseName(String name, int p, int limit){
        return courseDao.searchByCourseName(name,p,limit);
    }
    public List<Member> getStudentList(Long courseId){
        return courseDao.getStudentList(courseId);
    }
}
