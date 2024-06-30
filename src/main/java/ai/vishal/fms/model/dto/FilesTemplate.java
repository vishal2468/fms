package ai.vishal.fms.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public abstract class FilesTemplate {
    @Id
    String id;
    String name;
    String desc;
    int year;

}
