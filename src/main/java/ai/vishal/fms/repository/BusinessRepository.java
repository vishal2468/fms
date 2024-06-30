package ai.vishal.fms.repository;

import org.springframework.data.repository.CrudRepository;

import ai.vishal.fms.model.dto.Business;
public interface BusinessRepository extends CrudRepository<Business ,String>{
    
}
