package ai.vishal.fms.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Customer  {
    
    @Id
    @GeneratedValue
    int customerId;

    String customerName;
    
    @ManyToOne
    @JsonBackReference
    Business business;

}
