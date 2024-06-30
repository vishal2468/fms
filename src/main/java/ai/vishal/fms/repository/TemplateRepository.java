package ai.vishal.fms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ai.vishal.fms.model.dto.FilesTemplate;

public interface TemplateRepository extends CrudRepository<FilesTemplate, String>{

    Optional<FilesTemplate> findByCustomerIdAndYear(String customerId, int year);
    
}
