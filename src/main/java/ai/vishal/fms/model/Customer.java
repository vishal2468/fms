package ai.vishal.fms.model;

import java.util.HashMap;
import java.util.Map;

public class Customer implements Entity {
    String customerId;
    String customerName;
    String businessId;
    Map<String, File> map = new HashMap<>();
    String userId;

    @Override
    public String getEntityType() {
        return "Customer";
    }

    @Override
    public String getEntityId() {
        return customerId;
    }
}
