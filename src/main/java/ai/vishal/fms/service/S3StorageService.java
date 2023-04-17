package ai.vishal.fms.service;

import org.springframework.web.multipart.MultipartFile;

public class S3StorageService implements StorageService {

    @Override
    public String saveFile(MultipartFile file) {
        return null;
    }

    @Override
    public MultipartFile getFile(String fileRef) {
        return null;
    }

}
