package ai.vishal.fms.controller;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StorageController {

  @GetMapping("/upload-signed-url")
  public ResponseEntity<String> generateV4PutObjectSignedUrl() throws StorageException, IOException {
    // The ID of your GCP project
    String projectId = "fifth-diode-358105";

    // The ID of your GCS bucket
    String bucketName = "fms-test-bucket";

    // The ID of your GCS object
    String objectName = "link.txt";
    Credentials credentials = GoogleCredentials
            .fromStream(new FileInputStream("/Users/vishalpoddar/Desktop/fifth-diode-358105-06b1e9403d45.json"));

    Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();

    // Define Resource
    BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, objectName)).build();

    // Generate Signed URL
    Map<String, String> extensionHeaders = new HashMap<>();
    extensionHeaders.put("Content-Type", "application/octet-stream");

    URL url =
            storage.signUrl(
                    blobInfo,
                    15,
                    TimeUnit.MINUTES,
                    Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
                    Storage.SignUrlOption.withExtHeaders(extensionHeaders),
                    Storage.SignUrlOption.withV4Signature());

    System.out.println("Generated PUT signed URL:");
    System.out.println(url);
    System.out.println("You can use this URL with any user agent, for example:");
    return
            ResponseEntity.ok(
            "curl -X PUT -H 'Content-Type: application/octet-stream' --upload-file my-file '"
                    + url
                    + "'");
  }


  @GetMapping("/download-signed-url")
  public ResponseEntity<String> generateV4GetObjectSignedUrl() throws StorageException, IOException {
        // The ID of your GCP project
    String projectId = "fifth-diode-358105";

    // The ID of your GCS bucket
    String bucketName = "fms-test-bucket";

    // The ID of your GCS object
    String objectName = "artist.txt";

    Credentials credentials = GoogleCredentials
            .fromStream(new FileInputStream("/Users/vishalpoddar/Desktop/fifth-diode-358105-06b1e9403d45.json"));

    Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

    // Define resource
    BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, objectName)).build();

    URL url =
            storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());

    System.out.println("Generated GET signed URL:");
    System.out.println(url);
    System.out.println("You can use this URL with any user agent, for example:");
    return ResponseEntity.ok("curl '" + url + "'");
  }

}
