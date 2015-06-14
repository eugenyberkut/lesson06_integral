package main;

import integral.Function;
import integral.IntegralCalculator;
import integral.MyFunction;

/**
 * Created by Eugeny on 14.06.2015.
 */
public class Main {
    double totalResult;
    int runningThreads;
    int steps = 1_000_000_000;
    double a = 0;
    double b = Math.PI;
    public int N_THREADS = 100;

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setN_THREADS(int n_THREADS) {
        N_THREADS = n_THREADS;
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        // Однопоточный режим
//        Function f = new MyFunction();
//        IntegralCalculator calculator = new IntegralCalculator(0, Math.PI, 1_000_000_000, f);
//        double result = calculator.calculate();
//        System.out.println("result = " + result);

        // Многопоточный режим
        runningThreads = N_THREADS;
        totalResult = 0;
        Function f = new MyFunction();
        double delta = (b - a) / N_THREADS;
        long start = System.currentTimeMillis();
        for (int i = 0; i < N_THREADS; i++) {
            new ThreadedCalculator(a + i * delta, a + (i + 1) * delta, steps / N_THREADS, f, this).start();
        }
        // всегда такая конструкция - guarded suspension
        try {
            synchronized (this) {
                while (runningThreads > 0) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        System.out.println("totalResult = " + totalResult);
        System.out.println(finish-start);
    }

    public synchronized void sendResult(double result) {
        totalResult += result;
        runningThreads--;
        notify();
    }
}
