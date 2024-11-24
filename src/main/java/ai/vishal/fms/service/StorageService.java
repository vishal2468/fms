package ai.vishal.fms.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class StorageService {

  private static final Credentials credentials;

  // The ID of your GCS bucket
  @Value("${gcp.bucket.name}")
  private String gcpBucketName;

  private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

  static {
    try {
      credentials = GoogleCredentials
          .fromStream(new FileInputStream("/Users/vishalpoddar/Desktop/fms-1-442609-2bd1c4506049.json"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String generateV4PutObjectSignedUrl(String filePath, String fileName) throws StorageException {

    // The ID of your GCS object
    String objectName = filePath+"/"+fileName;

    // The path to your file to upload

    Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

    // Define Resource
    BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(gcpBucketName, objectName)).build();

    // Generate Signed URL
    Map<String, String> extensionHeaders = new HashMap<>();
    extensionHeaders.put("Content-Type", "application/octet-stream");

    URL url = storage.signUrl(
        blobInfo,
        15,
        TimeUnit.MINUTES,
        Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
        Storage.SignUrlOption.withExtHeaders(extensionHeaders),
        Storage.SignUrlOption.withV4Signature());

    logger.info("Generated GET signed URL: {}", url);
    return "curl -X PUT -H 'Content-Type: application/octet-stream' --upload-file my-file '"
        + url
        + "'";
  }

  public String generateV4GetObjectSignedUrl(String filePath, String fileName) throws StorageException {

    // The ID of your GCS object
    String objectName = filePath+"/"+fileName;

    Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

    // Define resource
    BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(gcpBucketName, objectName)).build();

    URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());

    logger.info("Generated GET signed URL: {}", url);
    return "curl '" + url + "'";
  }

  public boolean uploadFile(String filePath , MultipartFile file, Map<String, String> metadata) {
        try {
            String blobName = filePath+"/"+file.getOriginalFilename();

            // Create BlobInfo object with appropriate metadata
            BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(gcpBucketName, blobName))
                                        .setContentType(file.getContentType())
                                        .setMetadata(metadata)
                                        .build();

            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

            // Upload file content to GCP storage
            storage.create(blobInfo, file.getInputStream().readAllBytes());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
