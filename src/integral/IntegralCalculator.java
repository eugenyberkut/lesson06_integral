package integral;

/**
 * Created by Eugeny on 14.06.2015.
 */
public class IntegralCalculator {
    private double a;
    private double b;
    private int n;
    private Function f;

    public IntegralCalculator(double a, double b, int n, Function f) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
    }

    public double calculate() {
        double result = 0;
        double h = (b-a)/n;
        for (int i=0; i<n; i++) {
            result += f.f(a+i*h);
        }
        return result*h;
    }
}
