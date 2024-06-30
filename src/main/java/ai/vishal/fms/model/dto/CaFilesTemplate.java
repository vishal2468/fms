package ai.vishal.fms.model.dto;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CaFilesTemplate extends FilesTemplate {
    Document form16;
    Document salarySlips;
}
