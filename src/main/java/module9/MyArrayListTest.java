package module9;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyList<String> myArrayList = new MyArrayList<>();
        myArrayList.add("Java");
        System.out.println("Expected: 1, actual: " + myArrayList.size());
        System.out.println("Expected: Java, actual: " + myArrayList.get(0));
        myArrayList.add("Core");
        System.out.println("Expected: 2, actual: " + myArrayList.size());
        System.out.println("Expected: [Java, Core], actual: " + myArrayList);
        myArrayList.remove(0);
        System.out.println("Expected: Core, actual: " + myArrayList.get(0));
        System.out.println("Expected: 1, actual: " + myArrayList.size());
        System.out.println("Expected: [Core], actual: " + myArrayList);
        myArrayList.clear();
        System.out.println("Expected: 0, actual: " + myArrayList.size());
        System.out.println("Expected: [], actual: " + myArrayList);
    }
}
