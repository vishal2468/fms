package ai.vishal.fms.model.dto;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CaFilesTemplate extends FilesTemplate {
    Document form16;
    Document salarySlips;

    @Override
    public Document getDocument(DocumentDetails documentDetails) {
        if (documentDetails.getType().equals("form16"))
            return form16;
        else if (documentDetails.getType().equals("salarySlips"))
            return salarySlips;
        else
            return null;
    }

    @Override
    public Document addDocument(DocumentDetails documentDetails) {
        if (documentDetails.getType().equals("form16")) {
            form16 = new Document();
            form16.setDescription(documentDetails.getDescription());
            return form16;
        }
        if (documentDetails.getType().equals("salarySlips")) {
            salarySlips = new Document();
            salarySlips.setDescription(documentDetails.getDescription());
            return salarySlips;
        } else {
            return null;
        }
    }

    @Override
    public String getResourcePath(DocumentDetails documentDetails) {
        return documentDetails.year + "/" + documentDetails.type + "/" + documentDetails.name;
    }
}
