package ai.vishal.fms.model.request;

import ai.vishal.fms.model.dto.BusinessType;
import lombok.Data;

@Data
public class AddBusinessRequest {
    String businessName;
    BusinessType businessType;
    String description;
}
