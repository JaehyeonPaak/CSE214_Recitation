package Recitation_02;

public class Polynomial {
    private double[] coef;

    public Polynomial(double[] coef) {
        //trim the leading zeros
        int n = coef.length;
        while(n >= 2 && coef[n-1] == 0)
            n--;

        this.coef = new double[n];
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
        double[] c = new double[Math.max(this.coef.length, that.coef.length)];
        //TODO implement add
        return null;
    }
    public Polynomial mul(Polynomial that) {
        double[] c = new double[this.coef.length + that.coef.length - 1];
        //TODO implement mul
        return null;
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
        Polynomial a = new Polynomial(new double[] {-1, 1});
        System.out.println("a: " + a);

        Polynomial b = new Polynomial(new double[] { 1, 1});
        System.out.println("b: " + b);

        Polynomial c = a.add(b);
        System.out.println("c = (a + b): " + c);

        Polynomial d = a.mul(b);
        System.out.println("d = (a * b): " + d);

        Polynomial e = d.add(c);
        System.out.println("e = (d + c): " + e);

        Polynomial[] f = e.longdiv(a);
        System.out.println("e / a: " + f[0]);
        System.out.println("e % a: " + f[1]);
    }
}