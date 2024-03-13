package ai.vishal.fms.model.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Document {
    @Id
    int documentId;

    @ManyToOne
    @JsonBackReference
    Customer customer;

    String resourcePath;
    String description;
}
