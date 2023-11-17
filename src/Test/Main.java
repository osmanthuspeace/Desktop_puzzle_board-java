package Test;

import java.util.Random;
import java.util.Scanner;

public class Main {


    public static void f() {
        //initialize the data.That is,randomly shuffling the data
        int[] temp={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};

        int rr;
        Random r=new Random();
        for(int i=1;i<=16;i++){
            rr=r.nextInt(1,16);
            if(i!=rr) {
                temp[i]^=temp[rr];
                temp[rr]^=temp[i];
                temp[i]^=temp[rr];
            }
        }
        int[][] arr = new int[10][10];
        for(int i = 0; i<=15; i++){//more elegant
            arr[i/4+1][i%4+1]=temp[i+1];
        }
//        arr[4][4]=-1;
        for(int i=1;i<=4;i++){
            for(int j=1;j<=4;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static boolean p(){
        Scanner s=new Scanner(System.in);
        String a=s.next();
        if(a.equals("1"))
            return true;
        return false;
    }
    public static void main(String[] args) {
//        int[][] win={{},{0,1,2,3,4},{0,5,6,7,8},{0,9,10,11,12},{0,13,14,15,16}};
//        for(int i=1;i<=4;i++){
//            for(int j=1;j<=4;j++){
//                System.out.print(win[i][j]+" ");
//            }
//            System.out.println();
//        }
        // 开始计时
        long startTime = System.currentTimeMillis();

        // 调用你的逻辑，这里假设 f() 函数的返回类型为 boolean
        while (p()) {
            // 每次循环休眠一段时间，可以根据需要调整
            try {
                Thread.sleep(100); // 100毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 结束计时
        long endTime = System.currentTimeMillis();

        // 计算时间差并打印结果，保留一位小数
        double elapsedTime = (endTime - startTime) / 1000.0; // 转换为秒
        System.out.printf("从按下A键开始计时到f()函数为真，耗时 %.1f 秒\n", elapsedTime);

    }
}
