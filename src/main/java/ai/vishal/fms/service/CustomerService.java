package ai.vishal.fms.service;

import ai.vishal.fms.model.dto.Customer;
import ai.vishal.fms.model.request.UpdateCustomer;
import ai.vishal.fms.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CustomerService {

    CustomerRepository customerRepository;
    StorageService storageService;

    public CustomerService(CustomerRepository customerRepository , StorageService storageService) {
        this.customerRepository = customerRepository;
        this.storageService = storageService;
    }
    
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) {
    }

    public Optional<Customer> findById(int customerId) {
        return customerRepository.findById(customerId);
    }

    public void updateCustomer(UpdateCustomer request, String customerId) {
        
    }

    public String getDocumentUplaodUrlBy(String customerId, String businessId, String fileName) {
        String filePath = businessId + "/" + customerId;
        return storageService.generateV4PutObjectSignedUrl(filePath, fileName);
    }

    public String getDocumentDownloadUrlBy(String customerId, String businessId, String fileName) {
        String filePath = businessId + "/" + customerId;
        return storageService.generateV4GetObjectSignedUrl(filePath, fileName);
    }

    public String uploadFile(MultipartFile file, String customerId, String businessId) {
        String filePath = businessId + "/" + customerId;
        return storageService.uploadFile(filePath, file);
    }

    public void addAllCustomers(List<Customer> list) {
        customerRepository.saveAll(list);
    }
}
