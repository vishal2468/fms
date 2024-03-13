package ai.vishal.fms.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    List<Document> documents;

}
