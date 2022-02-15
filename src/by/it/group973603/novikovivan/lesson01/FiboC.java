package by.it.group973603.novikovivan.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        ArrayList<Long> period = new ArrayList<>();
        period.add(0L);
        period.add(1L);
        long previous = 0, current = 1, temp;
        for (int i = 0; i < m*m; i++) {
            temp = current;
            current = previous + current;
            previous = temp;
            if (previous % m == 0 && current % m == 1) {
                period.remove(period.size() - 1);
                break;
            }
            else period.add(current % m);
        }

        return period.get((int)n % period.size());
    }


}

