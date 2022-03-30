package by.it.group973603.novikovivan.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь.
На площади установлена одна или несколько камер.
Известны данные о том, когда каждая из них включалась и выключалась (отрезки работы)
Известен список событий на площади (время начала каждого события).
Вам необходимо определить для каждого события сколько камер его записали.

В первой строке задано два целых числа:
    число включений камер (отрезки) 1<=n<=50000
    число событий (точки) 1<=m<=50000.

Следующие n строк содержат по два целых числа ai и bi (ai<=bi) -
координаты концов отрезков (время работы одной какой-то камеры).
Последняя строка содержит m целых чисел - координаты точек.
Все координаты не превышают 10E8 по модулю (!).

Точка считается принадлежащей отрезку, если она находится внутри него или на границе.

Для каждой точки в порядке их появления во вводе выведите,
скольким отрезкам она принадлежит.
    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/

public class A_QSort {

    //отрезок
    private class Segment  implements Comparable<Segment>{
        int start;
        int stop;

        Segment(int start, int stop){
            this.start = start;
            this.stop = stop;
            //тут вообще-то лучше доделать конструктор на случай если
            //концы отрезков придут в обратном порядке
        }

        @Override
        public int compareTo(Segment o) {
            if (start < o.start)
                return 1;
            else if (start == o.start)
                return 0;
            else return -1;
        }
    }

//    private static void quickSort(Segment arr[], int from, int to) {
//        if (arr.length == 1) return;
//            int divideIndex = partition(arr, from, to);
//            quickSort(arr, from, divideIndex - 1);
//            quickSort(arr, divideIndex, to);
//    }
//
//    private static int partition(Segment arr[], int from, int to) {
//        int rightIndex = to, leftIndex = from, pivot = (from + to) / 2;
//
//        while (leftIndex <= rightIndex) {
//            while (arr[leftIndex].start < arr[pivot].start) {
//                leftIndex++;
//                System.out.println(leftIndex);
//            }
//            while (arr[rightIndex].start > arr[pivot].start) {
//                rightIndex--;
//            }
//
//            if (leftIndex < rightIndex) {
//                Segment tmp = arr[leftIndex];
//                arr[leftIndex] = arr[rightIndex];
//                arr[rightIndex] = tmp;
//                leftIndex++;
//                rightIndex--;
//            }
//        }
//        return leftIndex;
//    }

    public static void quickSort(Segment[] arr, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        Segment pivot = arr[(leftMarker + rightMarker) / 2];
        do {
            while (arr[leftMarker].compareTo(pivot) == 1) {
                leftMarker++;
            }
            while (arr[rightMarker].compareTo(pivot) == -1) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    Segment tmp = arr[leftMarker];
                    arr[leftMarker] = arr[rightMarker];
                    arr[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        if (leftMarker < rightBorder) {
            quickSort(arr, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(arr, leftBorder, rightMarker);
        }
    }

    int[] getAccessory(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments=new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points=new int[m];
        int[] result=new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i]=new Segment(scanner.nextInt(),scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i]=scanner.nextInt();
        }
        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор

        quickSort(segments, 0, n - 1);
        for (int i = 0; i < n; i++)
            System.out.println(segments[i].start + " " + segments[i].stop);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (points[i] < segments[j].start) break;
                if (points[i] > segments[j].start && points[i] < segments[j].stop)
                    result[i]++;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group973603/novikovivan/lesson05/dataA.txt");
        A_QSort instance = new A_QSort();
        int[] result=instance.getAccessory(stream);
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}
