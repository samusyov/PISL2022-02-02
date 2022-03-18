package by.it.group973602.onufriyuk.lesson01;

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
    //решение практически невозможно найти интуитивно
    //вам потребуется дополнительный поиск информации
    //см. период Пизано
    int size = 6 * m + 1;
    long[] pizano = new long[size];
    pizano[0] = 1;
    pizano[1] = 1;
    for (int i = 2; i < size; i++) {
      pizano[i] = (pizano[i - 1] + pizano[i - 2]) % m;
      if (pizano[i] == 1 && pizano[i - 1] == 0) {
        int ind = (int) (n % i) - 1;
        return pizano[ind];
      }
    }
    return 0L;
  }


}
