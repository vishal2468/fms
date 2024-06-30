package ai.vishal.fms.service;

import ai.vishal.fms.model.dto.Customer;
import ai.vishal.fms.model.dto.Document;
import ai.vishal.fms.model.request.AddCustomerRequest;
import ai.vishal.fms.model.request.UpdateCustomer;
import ai.vishal.fms.repository.CustomerRepository;
import ai.vishal.fms.repository.DocumentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CustomerService {

    CustomerRepository customerRepository;
    StorageService storageService;
    DocumentRepository documentRepository;

    public CustomerService(CustomerRepository customerRepository , StorageService storageService , DocumentRepository documentRepository) {
        this.customerRepository = customerRepository;
        this.storageService = storageService;
        this.documentRepository = documentRepository;
    }
    
    public void addCustomer(AddCustomerRequest addCustomerRequest , String businessId) {
        Customer customer = new Customer();
        customer.setCustomerName(addCustomerRequest.getCustomerName());
        customer.setBusinessId(businessId);
        customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    public Optional<Customer> findById(String customerId) {
        return customerRepository.findById(customerId);
    }

    public void updateCustomer(UpdateCustomer request, String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            customer.get().setCustomerName(request.getCustomerName());
            customerRepository.save(customer.get());
        }
    }

    public String getDocumentUplaodUrlBy(String customerId, String businessId, String fileName) {
        String filePath = businessId + "/" + customerId;
        return storageService.generateV4PutObjectSignedUrl(filePath, fileName);
    }

    public String getDocumentDownloadUrlBy(String customerId, String businessId, String fileName) {
        String filePath = businessId + "/" + customerId;
        return storageService.generateV4GetObjectSignedUrl(filePath, fileName);
    }

    public Optional<Document> uploadCustomerFile(MultipartFile file, Customer customer, String businessId) {
        String filePath = businessId + "/" + customer.getCustomerId();
        // need to update the customer when a file is uploaded
        Map<String, String> metadata = new HashMap<>();
        metadata.put("business", businessId);
        metadata.put("customer", String.valueOf(customer.getCustomerId()));
        if(storageService.uploadFile(filePath, file , metadata)){
            Document document = new Document();
            document.setResourcePath(filePath+"/"+file.getOriginalFilename());
            documentRepository.save(document);
            customerRepository.save(customer);
            return Optional.of(document);
        }
        return Optional.empty();
    }

}
