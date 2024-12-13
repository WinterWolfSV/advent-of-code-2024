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
            Frac b = new Frac(dY[2]).sub(new Frac(dY[0]).mul(new Frac(dX[2]).div(new Frac(dX[0])))).div(new Frac(dY[1]).sub(new Frac(dY[0]).mul(new Frac(dX[1]).div(new Frac(dX[0])))));
            Frac a = new Frac(dX[2]).sub(new Frac(dX[1]).mul(b)).div(new Frac(dX[0]));
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

    public Frac(long numerator) {
        this.n = numerator;
        this.d = 1;
    }

    public Frac(long numerator, long denominator) {
        this.n = numerator;
        this.d = denominator;
        simplify();
    }

    public Frac add(Frac o) {
        return new Frac(this.n * o.d + o.n * this.d, this.d * o.d);
    }

    public Frac sub(Frac o) {
        return new Frac(this.n * o.d - o.n * this.d, this.d * o.d);
    }

    public Frac mul(Frac o) {
        return new Frac(this.n * o.n, this.d * o.d);
    }

    public Frac div(Frac o) {
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
