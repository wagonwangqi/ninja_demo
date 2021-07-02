package mag.gaia.common.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import mag.gaia.common.models.*;
import mag.gaia.common.services.GenericService;
import ninja.jpa.UnitOfWork;
import ninja.params.Param;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChapterDao {
    @Inject
    GenericDao<Chapter> chapterGenericDao;
    @Inject
    Provider<EntityManager> entityManagerPovider;
    @Inject
    GenericDao<Course>courseGenericDao;
    @Inject
    GenericService<Section> sectionGenericService;
    @UnitOfWork
    public List<Chapter> chapterList(Course course){
        List<Chapter> chapterList1 = chapterGenericDao.findAll();
        List<Chapter> chapterList =new ArrayList<>();
        for (Chapter chapter : chapterList1) {
            if (chapter.getCourse().getCouId() == course.getCouId()) {
                chapterList.add(chapter);
            }
        }
       return  chapterList;
    }
    @UnitOfWork
    public List<Chapter> chapterList(Course course,Long chapterId){
        List<Chapter> chapterList1 = chapterGenericDao.findAll();
        List<Chapter> chapterList =new ArrayList<>();
        for (Chapter chapter : chapterList1) {
            if(chapter.getId()!=chapterId){
                if (chapter.getCourse().getCouId() == course.getCouId()) {
                    chapterList.add(chapter);
                }
            }
        }
        return  chapterList;
    }
    @UnitOfWork
    public Video findVideo(Long chapterId){
        EntityManager entityManager = entityManagerPovider.get();
        CriteriaQuery criteriaQuery;
        TypedQuery<Video> typedQuery = entityManager.createQuery
                ("select x from Video x where chapter_id = :chapterId",Video.class);
        Video video = typedQuery.setParameter("chapterId", chapterId).getSingleResult();
        return video;

    }
    @UnitOfWork
    public List<Course> getCourseList(){
        List<Course> all = courseGenericDao.findAll();
        return all;
    }
    @UnitOfWork
    public List<Chapter> getChapterList(Long courseId){
        EntityManager entityManager = entityManagerPovider.get();
        TypedQuery<Chapter> typedQuery = entityManager.createQuery
                ("select x from Chapter x where course_id = :courseId",Chapter.class);
        List<Chapter> chapterList = typedQuery.setParameter("courseId", courseId).getResultList();
        return chapterList;
    }
    @Transactional
    public void deleteSection(Long sectionId){
        EntityManager entityManager = entityManagerPovider.get();
        Section section = entityManager.find(Section.class, sectionId);
        entityManager.remove(section);
    }
    @UnitOfWork
    public List<Homework> getHomeworkList(Long chapterId){
        EntityManager entityManager = entityManagerPovider.get();
        TypedQuery<Homework> typedQuery = entityManager.createQuery
                ("select x from Homework x where chapterId = :chapterId",Homework.class);
        List<Homework> homeworkList = typedQuery.setParameter("chapterId", chapterId).getResultList();
        return homeworkList;
    }






}
