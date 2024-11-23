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

    public static FilesTemplate createTemplate(BusinessType businessType){
        if(businessType.equals(BusinessType.CA)){
            return new CaFilesTemplate();
        }
        else if(businessType.equals(BusinessType.REAL_ESTATE_BROKER)){
            return new RealEstateBrokerFileTemplate();
        }
        else return null;
    }

    public abstract Document getDocument(DocumentDetails documentDetails);

    public abstract Document addDocument(DocumentDetails documentDetails);

    public abstract String getResourcePath(DocumentDetails documentDetails);

}
