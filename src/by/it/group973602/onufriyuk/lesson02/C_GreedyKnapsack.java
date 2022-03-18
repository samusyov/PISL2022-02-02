package by.it.group973602.onufriyuk.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
  private class Item implements Comparable<Item> {
    int cost;
    int weight;
    double price_kg;  // цена за 1 килограмм предмета

    Item(int cost, int weight) {
      this.cost = cost;
      this.weight = weight;
    }

    @Override
    public String toString() {
      return "Item{" +
              "price_kg=" + price_kg +
              ", cost=" + cost +
              ", weight=" + weight +
              '}';
    }

    @Override
    public int compareTo(Item o) {
      if (this.price_kg > o.price_kg)
        return -1;
      return 1;
    }
  }

  double calc(File source) throws FileNotFoundException {
    Scanner input = new Scanner(source);
    int n = input.nextInt();      //сколько предметов в файле
    int W = input.nextInt();      //какой вес у рюкзака
    Item[] items = new Item[n];   //получим список предметов
    for (int i = 0; i < n; i++) { //создавая каждый конструктором
      items[i] = new Item(input.nextInt(), input.nextInt());
      items[i].price_kg = (double) items[i].cost / items[i].weight;
    }
    Arrays.sort(items);
    for (Item item:items) {
      System.out.println(item);
    }
    System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

    double result = 0;
    for (Item i : items) {
      if (i.weight < W) {
        result += i.cost;
        W -= i.weight;
      }
      else if (i.weight > W && W != 0) {
        result += W * i.price_kg;
        break;
      }
      else break;
    }
    System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
    return result;
  }

  public static void main(String[] args) throws FileNotFoundException {
    long startTime = System.currentTimeMillis();
    String root=System.getProperty("user.dir")+"/src/";
    File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
    double costFinal=new C_GreedyKnapsack().calc(f);
    long finishTime = System.currentTimeMillis();
    System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
  }
}
