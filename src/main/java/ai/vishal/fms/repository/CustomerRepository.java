package ai.vishal.fms.repository;

import org.springframework.data.repository.CrudRepository;

import ai.vishal.fms.model.dto.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{
    
}
