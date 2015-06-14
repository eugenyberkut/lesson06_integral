package integral;

/**
 * Created by Eugeny on 14.06.2015.
 */
public class MyFunction implements Function{
    @Override
    public double f(double x) {
        return Math.sin(x);
    }
}
