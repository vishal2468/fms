package ai.vishal.fms.controller;
import ai.vishal.fms.model.dto.Customer;
import ai.vishal.fms.model.dto.DocumentDetails;
import ai.vishal.fms.model.request.AddCustomerRequest;
import ai.vishal.fms.model.request.UpdateCustomer;
import ai.vishal.fms.service.CustomerService;
import ai.vishal.fms.service.TemplateService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("customer")
public class CustomerController {

    CustomerService customerService;

    TemplateService templateService;


    public CustomerController(CustomerService customerService , TemplateService templateService) {
        this.customerService = customerService;
        this.templateService = templateService;

    }

    @PostMapping("/b/{businessId}")
    public void addCustomer(@RequestBody AddCustomerRequest request , @PathVariable String businessId) {
        customerService.addCustomer(request, businessId);
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
        Optional<Customer> customer = customerService.findById(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("file/link/upload/c/{customerId}/b/{businessId}")
    public ResponseEntity<String> getDocumentUplaodUrl(@PathVariable String customerId , @PathVariable String businessId , @RequestBody DocumentDetails details) {
        if(customerService.findById(customerId).isPresent()){
            return ResponseEntity.ok(customerService.getDocumentUplaodUrlBy(customerId, businessId, details));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("file/link/download/c/{customerId}/b/{businessId}")
    public ResponseEntity<String> getDocumentDownloadUrl(@PathVariable String customerId , @PathVariable String businessId , @RequestBody DocumentDetails details) {
        if(customerService.findById(customerId).isPresent()){
            return ResponseEntity.ok(customerService.getDocumentDownloadUrlBy(customerId, businessId, details));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("file/upload/c/{customerId}")
    public void addCustomerDocumentDetails(@RequestBody DocumentDetails documentDetails , @PathVariable String customerId){
        templateService.addFileDetails(documentDetails, customerId);
    }
    
}
