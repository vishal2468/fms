package ai.vishal.fms.model.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer  {
    
    @Id
    @GeneratedValue
    String customerId;

    String customerName;
    
    String businessId;

    List<String> templateIds;

}
