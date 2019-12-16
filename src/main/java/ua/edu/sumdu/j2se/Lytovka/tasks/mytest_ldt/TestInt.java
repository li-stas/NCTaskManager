package ua.edu.sumdu.j2se.lytovka.tasks.mytest_ldt;

public class TestInt {
    public static void main(String[] args) {
        Integer nYYYY = 0;
        System.out.println(nYYYY);
        f(nYYYY);
        System.out.println(nYYYY); // выводит 0

        // а вот так
        System.out.println(nYYYY);
        int[] paramInt = new int[1];
        paramInt[0] = nYYYY;
        f1(paramInt);
        nYYYY = paramInt[0];
        System.out.println(nYYYY); // выводит 0
    }

    public static void f(Integer nYYYY) {
        nYYYY = 5;
    }
    public static void f1(int[] nNumParam) {
        int nYYYY = 5;
        nNumParam[0] = nYYYY;
    }

}
