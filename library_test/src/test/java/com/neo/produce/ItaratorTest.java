package com.neo.produce;

import org.junit.Test;

/**
 * Created by lidongpeng on 2018/4/13.
 */
public class ItaratorTest {
    /**
     * 递归实现
     *
     * @param n
     * @return
     */
    public static double recursive(long n) {
        if (n == 1) {
            return Math.log(1);
        } else {
            return Math.log(n) + recursive(n - 1);
        }
    }

    /**
     * 非递归实现
     *
     * @param n
     * @return
     */
    public static double directly(long n) {
        double result = 0;
        for (int i = 1; i <= n; i++) {
            result += Math.log(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int i = 5000000;
        long test = System.nanoTime();
        long start1 = System.nanoTime();
        double r1 = recursive(i);
        long end1 = System.nanoTime();
        long start2 = System.nanoTime();
        double r2 = directly(i);
        long end2 = System.nanoTime();

        System.out.println("recursive result:" + r1);
        System.out.println("recursive time used:" + (end1 - start1));
        System.out.println("non-recursive result:" + r2);
        System.out.println("non-recursive time used:" + (end2 - start2));

    }

    int wators=10;
    int leftping=0;
    int charge=3;
    int haveDrink=0;
    public int getDrink(int wators){
        haveDrink=haveDrink+wators;
        leftping=wators+wators%3;
        wators=leftping/3;
        if (leftping<3){
            return haveDrink;
        }else {
            getDrink( wators);
        }
        return haveDrink;
    }
    @Test
    public void testnum(){
       int i= getDrink(10);
        System.out.println(i);
    }

}
