package experience;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Design a distributed key-value store that can perform the following
 * Functional requirements
 * ● Store (Insert or Update) a set of attributes (value) against a particular key (k)
 * Data type of the key is fixed during table creation.
 * ● Fetch the value stored against a particular key (k)
 * ● Delete a key (k)
 * ● Perform a secondary index scan to fetch all key along with their
 * attributes where one of the attribute values is v.
 * <p>
 * Stretch: Data should be evenly distributed across nodes and failure of one node should move the data to other nodes.
 * <p>
 * Key can have a value consisting of multiple attributes.
 * Each attribute will have name, type associated (primitive types - boolean, double,
 * integer, string) & type has to be identified at run time.
 * 1) Key = delhi has 2 attributes only ( pollution_level & population)
 * 2) Key = jakarta has 3 attributes (latitude, longitude, pollution_level)
 * 3) Key = bangalore has 4 attributes (extra - free_food)
 * 4) Key = india has 2 attributes (capital & population)
 * 5) Key = crocin has 2 attributes (category & manufacturer)
 * Example of Secondary index:
 * ● Get all keys (cities) where pollution_level is high.
 * ● Get all medicines by manufacturer (GSK)
 * So, in a nutshell, value must be strongly typed when defined.
 * Attribute
 * 1. Attribute is uniquely identified by its name (latitude, longitude etc.)
 * 2. Data type of the attribute is defined at the first insert. (i.e. data type of pollution_level is set when key = delhi is inserted)
 * 3. Once data type is associated with a particular attribute, it cannot be changed. (i.e. free_food when defined takes type = boolean, hence, any key when using the attribute - free_food must allow only boolean values on subsequent inserts/updates)
 * Non-functional requirements
 * ● Highly scalable - Support for high throughput with very low latency ● Highly available
 * ● Shared nothing architecture i.e. Support for Multiple nodes and each node is independent & self-sufficient.
 * ● Stretch - Smart client i.e. clients being aware of available servers & makes smart routing based on that available information.
 * Notes
 * ● Preferable Language - Java
 * ● Follow standard OOPs concept and best practices in the industry. ● Make sure code is thread safe.
 * ● Stubs the methods for complex logic with comments supporting your assumptions.
 * ● Write at least 1 test case for each functionality.
 */
class Attribute {
    private final String attributeName;
    private final Class<?> dataType;

    public Attribute(String attributeName, Class<?> dataType) {
        this.attributeName = attributeName;
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "attributeName='" + attributeName + '\'' +
                ", dataType=" + dataType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) o;
        return attributeName.equals(attribute.attributeName) && dataType == attribute.dataType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName, dataType);
    }

    public String getAttributeName() {
        return attributeName;
    }

    public Class<?> getDataType() {
        return dataType;
    }

}

class DataStore {
    Map<String, Map<Attribute, Object>> data = new HashMap<>(); //primary data store
    Map<String, Class<?>> attributeNameToDataTypeMapping = new HashMap<>();
    //secondary indices
    Map<String, Map<String, Set<String>>> stringSecondaryIndex = new HashMap<>();
    //attribute name -> {attribute value -> key}
    //"pollution level" -> {"low" -> {"delhi", "bangalore"} , "high" -> {"mumbai"}}
    Map<String, Map<Integer, Set<String>>> integerSecondaryIndex = new HashMap<>();
    Map<String, Map<Double, Set<String>>> doubleSecondaryIndex = new HashMap<>();
    Map<String, Map<Boolean, Set<String>>> booleanSecondaryIndex = new HashMap<>();

    /**
     * Upsert a attribute with a value against the provided key
     */
    public synchronized void put(String key, Attribute attribute, Object value) {
        if (attributeNameToDataTypeMapping.containsKey(attribute.getAttributeName())) {
            validateAttribute(attribute, value);
        } else {
            attributeNameToDataTypeMapping.put(attribute.getAttributeName(), attribute.getDataType());
        }
        updateSecondaryIndex(key, attribute, value);
        data.computeIfAbsent(key, __ -> new HashMap<>()).put(attribute, value); //upsert
    }

    /**
     * Returns all the attributes with their value associated with a specific key
     */
    public synchronized Map<Attribute, Object> get(String key) {
        return data.computeIfAbsent(key, __ -> new HashMap<>());
    }

    /**
     * Returns the set of primary keys which has the specific attribute with the exact value
     */
    public synchronized Set<String> getSecondaryIndexWithValue(String attributeName, Object attributeValue) {
        if (attributeNameToDataTypeMapping.containsKey(attributeName)) {
            Class<?> dataType = attributeNameToDataTypeMapping.get(attributeName);
            if (dataType.isInstance(attributeValue)) {
                return lookupSecondaryIndex(attributeName, attributeValue, dataType);
            } else {
                throw new RuntimeException("Attribute datatype is different than expected datatype of " + dataType);
            }
        } else {
            throw new RuntimeException("Attribute name " + attributeName + " is not present");
        }
    }

    /**
     * Removes all attributes associated with the provided key
     */
    public synchronized void delete(String key) {
        if (data.containsKey(key)) {
            Map<Attribute, Object> attributes = data.get(key);
            for (Map.Entry<Attribute, Object> attribute : attributes.entrySet()) {
                //TODO Also delete attribute if no other key was referencing this attribute
                removeSecondaryIndex(key, attribute);
            }
            data.remove(key);
        } else {
            throw new RuntimeException("Key not present " + key);
        }
    }

    private void removeSecondaryIndex(String key, Map.Entry<Attribute, Object> attribute) {
        Attribute attributeKey = attribute.getKey();
        Object attributeValue = attribute.getValue();
        if (attributeKey.getDataType().equals(String.class)) {
            stringSecondaryIndex.get(attributeKey.getAttributeName()).get((String) attributeValue).remove(key);
            //remove attribute values with no keys
            stringSecondaryIndex.get(attributeKey.getAttributeName()).remove((String) attributeValue, new HashSet<String>());
        } else if (attributeKey.getDataType().equals(Integer.class)) {
            integerSecondaryIndex.get(attributeKey.getAttributeName()).get((Integer) attributeValue).remove(key);
            //remove attribute values with no keys
            integerSecondaryIndex.get(attributeKey.getAttributeName()).remove((Integer) attributeValue, new HashSet<String>());
        } else if (attributeKey.getDataType().equals(Boolean.class)) {
            booleanSecondaryIndex.get(attributeKey.getAttributeName()).get((Boolean) attributeValue).remove(key);
            //remove attribute values with no keys
            booleanSecondaryIndex.get(attributeKey.getAttributeName()).remove((Boolean) attributeValue, new HashSet<String>());
        } else if (attributeKey.getDataType().equals(Double.class)) {
            doubleSecondaryIndex.get(attributeKey.getAttributeName()).get((Double) attributeValue).remove(key);
            //remove attribute values with no keys
            doubleSecondaryIndex.get(attributeKey.getAttributeName()).remove((Double) attributeValue, new HashSet<String>());
        }
    }

    private Set<String> lookupSecondaryIndex(String attributeName, Object attributeValue, Class<?> dataType) {
        //TODO use strategy design pattern
        if (dataType.equals(String.class)) {
            return stringSecondaryIndex.get(attributeName).getOrDefault((String) attributeValue, new HashSet<>());
        } else if (dataType.equals(Integer.class)) {
            return integerSecondaryIndex.get(attributeName).getOrDefault((Integer) attributeValue, new HashSet<>());
        } else if (dataType.equals(Double.class)) {
            return doubleSecondaryIndex.get(attributeName).getOrDefault((Double) attributeValue, new HashSet<>());
        } else if (dataType.equals(Boolean.class)) {
            return booleanSecondaryIndex.get(attributeName).getOrDefault((Boolean) attributeValue, new HashSet<>());
        }
        throw new RuntimeException("Unsupported datatype " + dataType);
    }

    private void validateAttribute(Attribute attribute, Object value) {
        if (!attributeNameToDataTypeMapping.get(attribute.getAttributeName()).equals(attribute.getDataType())) {
            throw new RuntimeException("DataType of attribute " + attribute.getAttributeName() + " is already defined as " + attribute.getDataType());
        }
        if (!attribute.getDataType().isInstance(value)) {
            throw new RuntimeException("Value provided is not an instance of required datatype " + attribute.getDataType());
        }
    }

    private void updateSecondaryIndex(String key, Attribute attribute, Object value) {
        //TODO use factory design pattern
        if (attribute.getDataType().equals(String.class)) {
            stringSecondaryIndex.computeIfAbsent(attribute.getAttributeName(), __ -> new HashMap<>())
                    .computeIfAbsent((String) value, __ -> new HashSet<>()).add(key);
        } else if (attribute.getDataType().equals(Integer.class)) {
            integerSecondaryIndex.computeIfAbsent(attribute.getAttributeName(), __ -> new HashMap<>())
                    .computeIfAbsent((Integer) value, __ -> new HashSet<>()).add(key);
        } else if (attribute.getDataType().equals(Double.class)) {
            doubleSecondaryIndex.computeIfAbsent(attribute.getAttributeName(), __ -> new HashMap<>())
                    .computeIfAbsent((Double) value, __ -> new HashSet<>()).add(key);
        } else if (attribute.getDataType().equals(Boolean.class)) {
            booleanSecondaryIndex.computeIfAbsent(attribute.getAttributeName(), __ -> new HashMap<>())
                    .computeIfAbsent((Boolean) value, __ -> new HashSet<>()).add(key);
        }
    }
}

public class Cred {
    public static void main(String[] args) {
        DataStore dataStore = new DataStore();
        dataStore.put("delhi", new Attribute("pollution level", String.class), "very high");
        assert (dataStore.get("delhi").isEmpty()); //insert
        System.out.println(dataStore.get("delhi"));
        dataStore.put("delhi", new Attribute("pollution level", String.class), "low"); //update
        System.out.println(dataStore.get("delhi"));
        dataStore.put("bangalore", new Attribute("pollution level", String.class), "low");

        System.out.println(dataStore.getSecondaryIndexWithValue("pollution level", "low"));

        dataStore.put("delhi", new Attribute("free food", Boolean.class), true); //add another attribute
        System.out.println(dataStore.get("delhi"));
        assert (new HashSet<>(Arrays.asList("delhi")).equals(dataStore.getSecondaryIndexWithValue("free food", true)));
        assert (dataStore.getSecondaryIndexWithValue("free food", false).isEmpty());

        dataStore.delete("delhi");
        assert (dataStore.get("delhi").isEmpty());
        assert (dataStore.getSecondaryIndexWithValue("free food", true).isEmpty());
    }
}

