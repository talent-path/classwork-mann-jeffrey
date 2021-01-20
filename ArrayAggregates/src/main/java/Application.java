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
//        int [] testArr = {82, 95, 71, 6, 34};
//
//        int min = Aggregate.min( testArr );
//        int max = Aggregate.max( testArr );
//        int sum = Aggregate.sum( testArr );
//        double avg = Aggregate.average( testArr );
//        double sStd = Aggregate.sampleStandardDeviation( testArr );
//        double pStd = Aggregate.populationStandardDeviation( testArr );
//
//        System.out.println( min );
//        System.out.println( max );
//        System.out.println( sum );
//        System.out.println( avg );
//        System.out.println( sStd );
//        System.out.println( pStd );

//        for (int i = 1; i < 20; i++) {
//            System.out.println(fibRecursive(i));
//        }

        RockPaperScissors.play();
    }
}
