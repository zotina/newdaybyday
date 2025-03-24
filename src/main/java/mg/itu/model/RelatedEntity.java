package mg.itu.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.HashMap;
import java.util.Map;

public class RelatedEntity {
    private Map<String, Object> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    public Map<String, Object> getProperties() {
        return properties;
    } 

    public Object get(String key) {
        return properties.get(key);
    }
}