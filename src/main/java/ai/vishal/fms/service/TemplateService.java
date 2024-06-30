package ai.vishal.fms.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ai.vishal.fms.model.dto.Business;
import ai.vishal.fms.model.dto.BusinessType;
import ai.vishal.fms.model.dto.Customer;
import ai.vishal.fms.model.dto.DocumentDetails;
import ai.vishal.fms.model.dto.FilesTemplate;
import ai.vishal.fms.repository.BusinessRepository;
import ai.vishal.fms.repository.CustomerRepository;
import ai.vishal.fms.repository.TemplateRepository;

@Service
public class TemplateService{

    private TemplateRepository filesTemplateRepository;
    private CustomerRepository customerRepository;
    private BusinessRepository businessRepository;



    public TemplateService(TemplateRepository templateRepository ,CustomerRepository customerRepository , BusinessRepository businessRepository){
        this.filesTemplateRepository = templateRepository;
        this.customerRepository = customerRepository;
        this.businessRepository = businessRepository;
    }

    public Optional<FilesTemplate> getTemplate(String templateId){
        return filesTemplateRepository.findById(templateId);
    }

    public void addTempleate(FilesTemplate template , String customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);
        filesTemplateRepository.save(template);
        if(customer.isPresent()){
            customer.get().getTemplateIds().add(template.getId());
            customerRepository.save(customer.get());
        }
    }

    public void addFileDetails(DocumentDetails documentDetails , String customerId){
        // find the template by year and customerid
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<FilesTemplate> template = null;
        BusinessType businessType = null;
        if(customer.isPresent()){
            Optional<Business> business = businessRepository.findById(customer.get().getBusinessId());
            if(business.isPresent()){
                businessType = business.get().getBusinessType();
            }
        }
        else{
            throw new RuntimeException("Customer not found");
        }
        // get the template object depending on the business type and add the details
    }
}
