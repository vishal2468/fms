package ai.vishal.fms.controller;
import ai.vishal.fms.model.dto.Customer;
import ai.vishal.fms.model.dto.Document;
import ai.vishal.fms.model.request.AddCustomerRequest;
import ai.vishal.fms.model.request.UpdateCustomer;
import ai.vishal.fms.service.CustomerService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("customer")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/b/{businessId}")
    public void addCustomer(@RequestBody AddCustomerRequest request , @PathVariable int businessId) {
        Customer customer = new Customer();
        customer.setCustomerName(request.getCustomerName());
        customerService.addCustomer(customer);
    }

    @PutMapping("/c/{customerId}")
    public void updateCustomer(@RequestBody UpdateCustomer request , @PathVariable String customerId) {
        customerService.updateCustomer(request, customerId);
    }

    @DeleteMapping("/c/{customerId}")
    public void deleteCustomer(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/c/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        Optional<Customer> customer = customerService.findById(Integer.parseInt(customerId));
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("file/link/upload/c/{customerId}/b/{businessId}/f/{fileName}")
    public ResponseEntity<String> getDocumentUplaodUrl(@PathVariable String customerId , @PathVariable String businessId , @PathVariable String fileName) {
        if(customerService.findById(Integer.parseInt(customerId)).isPresent()){
            return ResponseEntity.ok(customerService.getDocumentUplaodUrlBy(customerId, businessId, fileName));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("file/link/download/c/{customerId}/b/{businessId}/f/{fileName}")
    public ResponseEntity<String> getDocumentDownloadUrl(@PathVariable String customerId , @PathVariable String businessId , @PathVariable String fileName) {
        if(customerService.findById(Integer.parseInt(customerId)).isPresent()){
            return ResponseEntity.ok(customerService.getDocumentDownloadUrlBy(customerId, businessId, fileName));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("file/c/{customerId}/b/{businessId}")
    public ResponseEntity<String> uploadFileToCustomer(@RequestParam("file") MultipartFile file , @PathVariable String customerId , @PathVariable String businessId) {
        Optional<Customer> customer = customerService.findById(Integer.parseInt(customerId));
        if(customer.isPresent()){
            customerService.uploadCustomerFile(file, customer.get(), businessId);
            return ResponseEntity.ok("Upload success");
        }
        return ResponseEntity.badRequest().build();
    }
    
}
