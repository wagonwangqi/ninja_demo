package mag.gaia.common.dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import mag.gaia.common.models.Collect;
import mag.gaia.common.models.Video;
import ninja.jpa.UnitOfWork;
import ninja.uploads.FileItem;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.*;

public class UpLoadVideoDao {
    String files = "src/main/java/assets/files/";
    @Inject
    Provider<EntityManager> entityManagerProvider;
    @Inject
    GenericDao<Video> videoGenericDao;
    @Transactional
    public  void createFile(String fileName, FileItem upFile) throws IOException {
        //create a same name file
        fileName = getFilename(fileName);
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

        if(index != -1)
            path = path.substring(index+1);
        return path;
    }
@UnitOfWork
    public Video getVideo(Long sectionId){
        EntityManager entityManager = entityManagerProvider.get();
        TypedQuery<Video> query = entityManager.createQuery
                ("select x from Video x " +
                        "where x.section_id = :sectionId ", Video.class);
        Video video = query.setParameter("sectionId", sectionId).getSingleResult();
        return video;
    }
    @UnitOfWork
    public int getVideoNum(Long sectionId){
        EntityManager entityManager = entityManagerProvider.get();

        TypedQuery<Video> query = entityManager.createQuery
                ("select x from Video x " +
                        "where x.section_id = :sectionId ", Video.class);
        int videoNum = query.setParameter("sectionId", sectionId).getResultList().size();

        return videoNum;
    }

}
