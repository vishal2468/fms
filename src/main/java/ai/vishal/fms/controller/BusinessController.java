package ai.vishal.fms.controller;

import ai.vishal.fms.model.dto.Business;
import ai.vishal.fms.model.dto.Customer;
import ai.vishal.fms.model.request.AddBusinessRequest;
import ai.vishal.fms.model.request.UpdateBusiness;
import ai.vishal.fms.service.BusinessService;
import ai.vishal.fms.service.CustomerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("business")
public class BusinessController {

    BusinessService businessService;

    CustomerService customerService;

    public BusinessController(BusinessService businessService , CustomerService customerService) {
        this.businessService = businessService;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Business> addBusiness(@RequestBody AddBusinessRequest request) {
        Business business = new Business();
        business.setBusinessName(request.getBusinessName());

        Customer customer = new Customer();
        customer.setCustomerName("customer1");
        business.setCustomers(Arrays.asList(customer));
        customer.setBusiness(business);

        Business response = businessService.addBusiness(business);
        customerService.addCustomer(customer);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{businessId}")
    public void updateBusiness(@RequestBody UpdateBusiness request , @PathVariable String businessId) {
        businessService.updateBusiness(request , businessId);
    }

    @DeleteMapping("/{businessId}")
    public void deleteBusiness(@PathVariable String businessId) {
        businessService.deleteBusiness(businessId);
    }

    @GetMapping("/{businessId}")
    public ResponseEntity<Business> getBusiness(@PathVariable int businessId) {
        Optional<Business> business = businessService.getBusiness(businessId);
        if(business.isPresent())
            return ResponseEntity.ok(business.get());
        else
            return ResponseEntity.notFound().build();
    }
}
