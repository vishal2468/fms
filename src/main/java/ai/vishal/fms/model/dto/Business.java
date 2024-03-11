package ai.vishal.fms.model.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Business {

    @Id
    @GeneratedValue()
    int businessId;

    @JsonManagedReference
    @OneToMany(mappedBy = "business")
    List<Customer> customers;

    String businessName;
}
