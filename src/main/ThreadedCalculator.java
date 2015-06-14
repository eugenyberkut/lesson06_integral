package main;

import integral.Function;
import integral.IntegralCalculator;

/**
 * Created by Eugeny on 14.06.2015.
 */
public class ThreadedCalculator extends Thread {

    private IntegralCalculator calculator;
    private Main main;

    /**
     * Создаем новый потоковый калькулятор со ссылкой на main, а в нем создаем обычный IntegralCalculator
     * @param a начало
     * @param b конец интервала
     * @param n к-во шагов
     * @param f функция
     * @param main ссылка на главный объект
     */
    public ThreadedCalculator(double a, double b, int n, Function f, Main main) {
        this.main = main;
        calculator = new IntegralCalculator(a,b,n,f);
    }

    @Override
    public void run() {
        double result = calculator.calculate();
        main.sendResult(result);
    }
}
