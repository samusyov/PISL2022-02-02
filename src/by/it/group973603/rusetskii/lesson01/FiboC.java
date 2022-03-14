package by.it.group973603.rusetskii.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.List;

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
        List<Long> list = new ArrayList<>();
        list.add(0L);
        list.add(1L);
        for (int ind = 2; ind<m*6; ind++) {
            list.add((list.get(ind-1) + list.get(ind-2)) % m);
            if(list.get(ind) == 1 && list.get(ind-1) == 0)
                break;
        }
        long period = list.size() - 2;
        int index = (int) (n % period);
        return list.get(index);
    }
}

