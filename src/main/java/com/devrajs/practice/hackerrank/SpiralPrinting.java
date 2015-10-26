package com.devrajs.practice.hackerrank;

/**
 * Created by devraj.singh on 9/6/15.
 */
public class SpiralPrinting {

    static void spiralPrinting(int[][] matrix, int m,int n)
    {
        int x1=0,y1=0,x2=m,y2=n;
        while(x1<=x2 && y1<=y2)
        {
            for(int j=y1;j<=y2;j++)
                System.out.print(matrix[x1][j] + "  ");
            x1++;
            for(int i=x1;i<=x2;i++)
                System.out.print(matrix[i][y2] + "  ");
            y2--;
            if(x1<=x2){
            for(int j=y2;j>=y1;j--)
                System.out.print(matrix[x2][j] + "  ");
            x2--;}
            if(y1<=y2){
            for(int i=x2;i>=x1;i--)
                System.out.print(matrix[i][y1] + "  ");
            y1++;}
        }
    }

    public static void main(String[] args)
    {
        int[][] mat = {{1,2,3,4,5,6},{7,8,9,10,11,12},{13,14,15,16,17,18}};
        spiralPrinting(mat,2,5);
    }

}
