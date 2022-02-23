//
// 0. Download Euclidean.java
// 1. Implement GCDBySub class
// 2. Implement GCDByMod class
//

public class Euclidean {
    //Interface named GCD...
    public static interface GCD {
        public int gcdImpl(int a, int b);

        //default methods can have implementations
        //replace the value a and b to absolute values...
        public default int gcd(int a, int b) {
            return gcdImpl(abs(a), abs(b));
        }
        public default int abs(int a) {
            return a < 0 ? -a : a;
        }
    }

    //TODO: implement GCD using subtraction
    //the class GCDBySub is child class of GCD class...
    public static class GCDBySub implements GCD {

        @Override
        public int gcdImpl(int a, int b) {
            if(a > b) {
                return gcdImpl(a - b, b);
            }
            else if(b > a) {
                return gcdImpl(b - a, a);
            }
            else {
                return a;
            }
        }
    }

    //TODO: implement GCD using modulo
    public static class GCDByMod implements GCD {

        @Override
        public int gcdImpl(int a, int b) {
            if(a == 0) {
                return b;
            }
            else if(a > b) {
                return gcdImpl(a % b, b);
            }
            else {
                return gcdImpl(b % a, a);
            }
        }
    }

    public static void test(GCD gcd, int a, int b) {
        System.out.println("GCD: " + gcd.gcd(a, b));
        //calling gcd(a, b) by using virtual method...
    }

    public static void main(String[] args) {
        final int a = 39;
        final int b = -15;

        test(new GCDBySub(), a, b);
        test(new GCDByMod(), a, b);
    }
}