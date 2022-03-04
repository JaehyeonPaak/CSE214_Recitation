package Recitation_02;

public class Polynomial {
    private double[] coef; //field...

    public Polynomial(double[] coef) { //constructor...
        //trim the leading zeros
        int n = coef.length;
        while(n >= 2 && coef[n-1] == 0) //if coef = [1, 1, 0], remove 0...
            n--;

        this.coef = new double[n]; //declare an array whose size is n...
        for(int i = 0; i < n; i++)
            this.coef[i] = coef[i];
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = coef.length - 1; i >= 0; i--) {
            if(i > 0)
                sb.append(String.format("%g*x^%d + ", coef[i], i));
            else
                sb.append(String.format("%g", coef[i]));
        }
        return sb.toString();
    }
    public Polynomial add(Polynomial that) {
        double[] c = new double[Math.max(this.coef.length, that.coef.length)]; //Math.max(a, b) == a (if a >= b)...
        //TODO implement add
        for(int i = c.length - 1; i >= 0; i--) {
            c[i] = this.coef[i] + that.coef[i];
        }
        return new Polynomial(c);
    }
    public Polynomial mul(Polynomial that) { // (x + 1) * (x - 1) / {1, 1} {-1, 1}...
        double[] c = new double[this.coef.length + that.coef.length - 1]; // c.length == 3...
        //TODO implement mul
        for(int i = c.length - 1; i >= 0; i--) { //2, 1, 0...
            if(i == 2) {
                c[i] = this.coef[i - 1] * that.coef[i - 1];
            }
            else if(i == 1) {
                c[i] = (this.coef[i - 1] * that.coef[i]) + (this.coef[i] * this.coef[i - 1]);
            }
            else {
                c[i] = this.coef[i] * that.coef[i];
            }
        }
        return new Polynomial(c);
    }
    public Polynomial[] longdiv(Polynomial that) {
        //return value: longdiv(...)[0]: quotient,
        //              longdiv(...)[1]: remainder
        double[] quo = new double[this.coef.length - that.coef.length + 1]; //quotient
        double[] num = new double[this.coef.length];  //numerator, remainder
        double[] den = that.coef;   //denominator
        int dd = den.length - 1;    //degree of denominator

        //copy this.coef to num because num will be modified
        for(int i = 0; i < this.coef.length; i++)
            num[i] = this.coef[i];

        //the long division algorithm
        //num -> quo * den + num
        //TODO: implement the rest
        return null;
    }
    public static void main(String[] args) {
        Polynomial a = new Polynomial(new double[] {-1, 1}); //x - 1...
        System.out.println("a: " + a);

        Polynomial b = new Polynomial(new double[] { 1, 1}); //x + 1...
        System.out.println("b: " + b);

        Polynomial c = a.add(b);
        System.out.println("c = (a + b): " + c); //2x...

        Polynomial d = a.mul(b);
        System.out.println("d = (a * b): " + d); //x^2 - 1...

        Polynomial e = d.add(c);
        System.out.println("e = (d + c): " + e); //x^2 + 2x + 1...

        Polynomial[] f = e.longdiv(a);
        System.out.println("e / a: " + f[0]);
        System.out.println("e % a: " + f[1]);
    }
}