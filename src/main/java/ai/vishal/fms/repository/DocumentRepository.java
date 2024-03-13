package ai.vishal.fms.repository;

import org.springframework.data.repository.CrudRepository;

import ai.vishal.fms.model.dto.Document;

public interface DocumentRepository extends CrudRepository<Document, Integer>{
    
}
