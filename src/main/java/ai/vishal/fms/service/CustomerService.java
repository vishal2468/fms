package ai.vishal.fms.service;

import ai.vishal.fms.model.dto.Customer;
import ai.vishal.fms.model.request.UpdateCustomer;
import ai.vishal.fms.repository.CustomerRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

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

}
