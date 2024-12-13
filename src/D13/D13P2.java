package D13;

import util.Util;

public class D13P2 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D13/input.txt", "\n\n");
        long tot = 0;
        for (String d : data) {
            long[] dX = new long[3];
            long[] dY = new long[3];
            String[] tmp;
            for (int i = 0; i < (tmp = d.split("\n")).length; i++) {
                String[] vals = tmp[i].split(",");
                dX[i] = Integer.parseInt(vals[0].split("[+=]")[1]);
                dY[i] = Integer.parseInt(vals[1].split("[+=]")[1]);
            }
            dX[2] += 10000000000000L;
            dY[2] += 10000000000000L;
            Frac b = new Frac(dY[2], 1).subtract(new Frac(dY[0], 1).multiply(new Frac(dX[2], 1).divide(new Frac(dX[0], 1)))).divide(new Frac(dY[1], 1).subtract(new Frac(dY[0], 1).multiply(new Frac(dX[1], 1).divide(new Frac(dX[0], 1)))));
            Frac a = new Frac(dX[2], 1).subtract(new Frac(dX[1], 1).multiply(b)).divide(new Frac(dX[0], 1));
            if (b.isWhole() && a.isWhole()) {
                tot += b.n + a.n * 3;

            }
        }

        System.out.println(tot); // 83102355665474
    }
}

// Floating point division my behated
class Frac {
    public long n;
    public long d;

    public Frac(long numerator, long denominator) {
        this.n = numerator;
        this.d = denominator;
        simplify();
    }

    public Frac subtract(Frac o) {
        return new Frac(this.n * o.d - o.n * this.d, this.d * o.d);
    }

    public Frac multiply(Frac o) {
        return new Frac(this.n * o.n, this.d * o.d);
    }

    public Frac divide(Frac o) {
        return new Frac(this.n * o.d, this.d * o.n);
    }

    public boolean isWhole() {
        return this.d == 1;
    }

    private void simplify() {
        long gcd = gcd(n, d);
        n /= gcd;
        d /= gcd;
        if (d < 0) {
            n = -n;
            d = -d;
        }
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }
}
