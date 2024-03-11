package ai.vishal.fms.model.dto;

import lombok.Data;

@Data
public class Document {
    String businessId;
    String customerId;
    String resourcePath;
    String description;
    String documentId;
}
