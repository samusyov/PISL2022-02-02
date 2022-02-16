package by.it.group973603.zemnov.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 43576456;
        int m = 4;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        //
        ArrayList<Integer> seq = new ArrayList<>();
        for (int a = 0, b = 1%m; ;) {
            seq.add(a);
            if (a + b == 0 || (a == 1 && b == 0))
                break;
            int d = (a + b) % m;
            a = b;
            b = d;
        }
        return seq.get((int) n % seq.size());
    }

}

