package mag.gaia.common.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import mag.gaia.common.models.Collect;
import mag.gaia.common.models.Course;
import mag.gaia.common.models.Member;
import mag.gaia.common.models.Video;
import mag.gaia.common.utils.Page;
import ninja.jpa.UnitOfWork;
import ninja.uploads.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.rmi.CORBA.StubDelegate;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class CourseDao {
    String files = "src/main/java/assets/files/";
    @Inject
    Provider<EntityManager> entityManagerProvider;
    @Inject
    GenericDao<Course> courseGenericDao;
    final Logger logger = LoggerFactory.getLogger(CourseDao.class);
    @Transactional
    public void saveFile(FileItem upFile) throws IOException {
        String fileName = upFile.getFileName();
        File file = new File(files + fileName);
        if (!file.exists()) {
            file.createNewFile();
            int bytesRead = 0;
            InputStream inputStream = upFile.getInputStream();
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        }
    }

    private String getFilename(String path) {
        int index = path.lastIndexOf("\\");

        if (index != -1)
            path = path.substring(index + 1);
        return path;
    }

    @UnitOfWork
    public Page<Course> searchByCourseName(String name, int p, int limit) {
        Page<Course> page = new Page<>(p, limit);
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("name required");
        }
        EntityManager entityManager = entityManagerProvider.get();
        TypedQuery<Course> typedQuery = entityManager.
                createQuery("select x from Course x where couName like:name order by x.couId desc", Course.class);
        List<Course> CourseList = typedQuery.setParameter("name", "%" + name + "%").
                setMaxResults(page.getMaxResults()).setFirstResult(page.getFirstResult()).getResultList();
        logger.info(String.valueOf(CourseList.size()));
        page.setItems(CourseList);
        page.setAllrecords(CourseList.size());
        return page;
    }
    @UnitOfWork
    public List getStudentIdList(Long courseId){
        EntityManager entityManager = entityManagerProvider.get();
        Query query = entityManager.createNativeQuery
                ("select mem_id from Collect  " +
                        "where cou_id =?1 add is_collect = true");
        List resultList = query.setParameter(1, courseId).getResultList();
        return resultList;
    }
    @UnitOfWork
    public List<Member> getStudentList(Long courseId){
        Course course = courseGenericDao.find(courseId);
        List<Member> members = course.getMembers();
        return members;
    }


}
