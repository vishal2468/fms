package ai.vishal.fms.model.dto;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Business {

    @Id
    @GeneratedValue()
    String businessId;

    List<String> customerIds;

    String businessName;

    BusinessType businessType;

    String description;
}
