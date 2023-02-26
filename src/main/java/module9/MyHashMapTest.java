package module9;

public class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put("Junior", 1000);
        System.out.println("Expected: 1, actual: " + myHashMap.size());
        myHashMap.put("Middle", 2000);
        System.out.println("Expected: 2000, actual: " + myHashMap.get("Middle"));
        System.out.println("Expected: [Junior = 1000, Middle = 2000], actual: " + myHashMap);
        myHashMap.put("Senior", 4000);
        myHashMap.put("Lead", 5000);
        System.out.println("Expected: 4, actual: " + myHashMap.size());
        System.out.println("Expected: [Junior = 1000, Middle = 2000, Senior = 4000, Lead = 5000], actual: " + myHashMap);
        myHashMap.remove("Lead");
        System.out.println("Expected: [Junior = 1000, Middle = 2000, Senior = 4000], actual: " + myHashMap);
        System.out.println("Expected: 3, actual: " + myHashMap.size());
        myHashMap.clear();
        System.out.println("Expected: 0, actual: " + myHashMap.size());
        System.out.println("Expected: [], actual: " + myHashMap);
    }
}
