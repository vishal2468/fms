package ai.vishal.fms.model;

import java.util.HashMap;
import java.util.Map;

public class Business implements Entity {
    String businessName;
    String businessId;
    Map<String, File> map = new HashMap<>();
    String userId;

    @Override
    public String getEntityType() {
        return "Business";
    }

    @Override
    public String getEntityId() {
        return businessId;
    }
}
