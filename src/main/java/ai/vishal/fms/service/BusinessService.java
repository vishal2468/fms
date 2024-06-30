package ai.vishal.fms.service;

import ai.vishal.fms.model.dto.Business;
import ai.vishal.fms.model.request.UpdateBusiness;
import ai.vishal.fms.repository.BusinessRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessService {

    BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public Business addBusiness(Business business) {
        return  businessRepository.save(business);
    }

    public void updateBusiness(UpdateBusiness updateBusiness , String businessId) {
        Business business = businessRepository.findById(businessId).orElseThrow(() -> new RuntimeException("Resource not found"));
        business.setBusinessName(updateBusiness.getBusinessName());
        business.setDescription(updateBusiness.getDescription());
    }

    public void deleteBusiness(String businessId) {
        // delete business
        businessRepository.deleteById(businessId);
    }

    public Optional<Business> getBusiness(String businessId) {
        // get business
        return businessRepository.findById(businessId);
    }
}
