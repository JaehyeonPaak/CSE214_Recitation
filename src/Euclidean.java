//
// 0. Download Euclidean.java
// 1. Implement GCDBySub class
// 2. Implement GCDByMod class
//

public class Euclidean {
    public static interface GCD {
        public int gcdImpl(int a, int b);

        //default methods can have implementations
        public default int gcd(int a, int b) {
            return gcdImpl(abs(a), abs(b));
        }
        public default int abs(int a) {
            return a < 0 ? -a : a;
        }
    }

    //TODO: implement GCD using subtraction
    public static class GCDBySub implements GCD {
    }

    //TODO: implement GCD using modulo
    //public static class GCDByMod implements GCD {
    //}

    public static void test(GCD gcd, int a, int b) {
        System.out.println("GCD: " + gcd.gcd(a, b));
    }
    public static void main(String[] args) {
        final int a = 39;
        final int b = -15;

        test(new GCDBySub(), a, b);
        //test(new GCDByMod(), a, b);
    }
}