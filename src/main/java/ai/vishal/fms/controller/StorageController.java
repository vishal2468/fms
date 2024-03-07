package ai.vishal.fms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.IOException;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StorageController {

  @GetMapping("/upload")
  public ResponseEntity<String> uploadObject() throws IOException{
    // The ID of your GCP project
    String projectId = "fifth-diode-358105";

    // The ID of your GCS bucket
    String bucketName = "fms-test-bucket";

    // The ID of your GCS object
    String objectName = "artist.txt";

    // The path to your file to upload
    String filePath = "/Users/vishalpoddar/Desktop/artist.txt";

    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    BlobId blobId = BlobId.of(bucketName, objectName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

    // Optional: set a generation-match precondition to avoid potential race
    // conditions and data corruptions. The request returns a 412 error if the
    // preconditions are not met.
    Storage.BlobWriteOption precondition;
    if (storage.get(bucketName, objectName) == null) {
      // For a target object that does not yet exist, set the DoesNotExist precondition.
      // This will cause the request to fail if the object is created before the request runs.
      precondition = Storage.BlobWriteOption.doesNotExist();
    } else {
      // If the destination already exists in your bucket, instead set a generation-match
      // precondition. This will cause the request to fail if the existing object's generation
      // changes before the request runs.
      precondition =
          Storage.BlobWriteOption.generationMatch(
              storage.get(bucketName, objectName).getGeneration());
    }
    storage.createFrom(blobInfo, Paths.get(filePath), precondition);

    return
            ResponseEntity.ok("File %s uploaded to bucket %s as %s".formatted(filePath, bucketName, objectName));
  }

  @GetMapping("/download")
  public ResponseEntity<String> downloadObject() {
    // The ID of your GCP project
    String projectId = "fifth-diode-358105";

    // The ID of your GCS bucket
    String bucketName = "fms-test-bucket";

    // The ID of your GCS object
    String objectName = "artist1.txt";

    // The path to which the file should be downloaded
    String destFilePath = "/Users/vishalpoddar/vishal.txt";

    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

    Blob blob = storage.get(BlobId.of(bucketName, objectName));
    blob.downloadTo(Paths.get(destFilePath));
    return
            ResponseEntity.ok("File %s downloaded from bucket %s as %s".formatted(destFilePath, bucketName, objectName));
  }

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return ResponseEntity.ok("hi");
  }
}
