package by.it.group973603.zhumabekov.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

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
            long pisano_period = pisanoPeriod(m);
            n = n % pisano_period;
            long previous = 0;
            long current = 1;
            long temp=0;

            for(long i =0; i<n-1; i++){
                temp = current;
                current = previous+current;
                previous = temp;
            }
            return (current % m);
            //решение практически невозможно найти интуитивно
            //вам потребуется дополнительный поиск информации
            //см. период Пизано
        }

    long pisanoPeriod(int m){
        int current = 1;
        int previous = 0;
        int temp=0;
        for (int i = 0; i < m * m; i++) {
            temp = current;
            current = (previous + current) % m;
            previous = temp;
            if (previous == 0 && current == 1) {
                return i + 1;
            }
        }
        return 0;
    }
}

