package mag.gaia.common.services;

import com.google.inject.Inject;
import mag.gaia.common.dao.UpLoadVideoDao;
import mag.gaia.common.models.Video;
import ninja.uploads.FileItem;

import java.io.IOException;

public class VideoService {
    @Inject
    UpLoadVideoDao upLoadVideoDao;
    public void createFile(String fileName, FileItem upFile) throws IOException {
       upLoadVideoDao.createFile(fileName,upFile);
    }
    public Video getVideo(Long sectionId){
        return upLoadVideoDao.getVideo(sectionId);
    }
    public int getVideoNum(Long sectionId){
        return upLoadVideoDao.getVideoNum(sectionId);
    }
}
