package ai.vishal.fms.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Document {
    @Id
    int documentId;

    String resourcePath;

    @Column(name = "description")
    String description;

    String businessId;
}
