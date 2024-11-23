package ai.vishal.fms.model.dto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RealEstateBrokerFileTemplate extends FilesTemplate {

    Document landTax;

    @Override
    public Document getDocument(DocumentDetails documentDetails) {
        if(documentDetails.getType().equals("landTax")){
            return landTax;
        }else{
            return null;
        }
    }

    @Override
    public Document addDocument(DocumentDetails documentDetails) {
        if(documentDetails.getType().equals("landTax")){
            landTax = new Document();
            landTax.setDescription(documentDetails.getDescription());
            return landTax;
        }else{
            return null;
        }
    }

    @Override
    public String getResourcePath(DocumentDetails documentDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResourcePath'");
    }
}
