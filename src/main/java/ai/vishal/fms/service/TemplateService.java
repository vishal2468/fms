package ai.vishal.fms.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import ai.vishal.fms.model.dto.Customer;
import ai.vishal.fms.model.dto.DocumentDetails;
import ai.vishal.fms.model.dto.FilesTemplate;
import ai.vishal.fms.repository.CustomerRepository;
import ai.vishal.fms.repository.TemplateRepository;

@Service
public class TemplateService {

    private TemplateRepository filesTemplateRepository;
    private CustomerRepository customerRepository;

    public TemplateService(TemplateRepository templateRepository, CustomerRepository customerRepository) {
        this.filesTemplateRepository = templateRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<FilesTemplate> getTemplate(String templateId) {
        return filesTemplateRepository.findById(templateId);
    }

    public void addFileDetails(DocumentDetails documentDetails, String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(customerId + " not found"));
        Optional<FilesTemplate> template = filesTemplateRepository.findByCustomerIdAndYear(customerId,
                documentDetails.getYear());
        if (template.isPresent()) {
            template.get().addDocument(documentDetails);
            filesTemplateRepository.save(template.get());
        } else {
            FilesTemplate template2 = FilesTemplate.createTemplate(customer.getBusinessType());
            template2.addDocument(documentDetails);
            filesTemplateRepository.save(template2);
        }

    }
}
