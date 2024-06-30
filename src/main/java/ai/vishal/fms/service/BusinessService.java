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

    public void updateBusiness(UpdateBusiness business , String businessId) {
        // Update business to database
    }

    public void deleteBusiness(String businessId) {
        // delete business
    }

    public Optional<Business> getBusiness(String businessId) {
        // get business
        return businessRepository.findById(businessId);
    }
}
