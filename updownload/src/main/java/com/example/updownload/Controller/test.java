package com.example.updownload.Controller;

public class test {


    public void jiecheng(int number){
        int[] daan=new int[35660];
        //阶乘的第一位为1
        daan[0]=1;
        //数组长度
        int len=daan.length;
        //进位
        int jingwei=0;

        int a;

        for(int i=2;i<=10000;i++){
            for (int j=0;j<len;j++){
                if (j==0){
                    a=daan[j]*i;
                    daan[j]=a%10;
                    jingwei=a/10;
                }else{
                    a=daan[j]*i+jingwei;
                    jingwei=a/10;
                    daan[j]=a%10;
                }
            }
        }

        for(int k=len-1;k>=0;k--){
            System.out.print(daan[k]);
        }


    }
}
