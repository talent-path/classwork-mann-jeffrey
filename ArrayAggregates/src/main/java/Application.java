public class Application {
//    application entry point (this method is special)
//    public keyword - controls visibility
//                     allow this function to be called from any package
//    static keyword - associate this method with the class itself
//                     rather than an instance of the class
//                     for now always use public and static

//    void keyword - return type, can be any data type, void returns nothing
//    main keyword - method name, can be anything but main is special
//                   conventionally named with camelCase (not PascalCase)

//    (String[] args) - method parameters
    public static void main(String[] args) {
        int[] sorted = Aggregate.bubbleSort(new int[] {73, 2, 7, 82, 100, 20, 9, 25, 32,
                                        97, 91, 96, 81, 8, 78, 54, 93, 26, 36, 69});
        for (int n : sorted) {
            System.out.println(n);
        }
    }
}
