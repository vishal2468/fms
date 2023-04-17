package ai.vishal.fms.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    
    public String saveFile(MultipartFile file);

    public MultipartFile getFile(String fileRef);
}
