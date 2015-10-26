package com.devrajs.practice.techgig;

/**
 * Created by devraj.singh on 9/12/15.
 */
public class NumOfPath {

    public static int no_of_path(int[] input1,int[] input2)
    {
        int m=input1[0], n=input1[1];
        int[][] mat = convertIntoTwoDim(input2,m,n);
        return traverse(0,0,mat,m,n);
    }

    private static int traverse(int a,int b, int[][] mat, int m, int n)
    {
        if(a>=m || b>=n)
            return 0;
        if(a==m-1 && b==n-1)
            return 1;
        int cell_value = mat[a][b];
        if(cell_value==0) return 0;
        else if(cell_value==1)
            return traverse(a,b+1,mat,m,n);
        else if(cell_value==2)
            return traverse(a+1,b,mat,m,n);
        else if(cell_value==3)
            return traverse(a+1,b+1,mat,m,n);
        else if(cell_value==4)
            return traverse(a+1,b,mat,m,n) + traverse(a,b+1,mat,m,n);
        else if(cell_value==5)
            return traverse(a,b+1,mat,m,n) + traverse(a+1,b+1,mat,m,n);
        else if(cell_value==6)
            return traverse(a+1,b,mat,m,n) + traverse(a+1,b+1,mat,m,n);
        else if(cell_value==7)
            return traverse(a,b+1,mat,m,n) + traverse(a+1,b,mat,m,n) + traverse(a+1,b+1,mat,m,n);
        else
            return 0;
    }

    private static int[][] convertIntoTwoDim(int[] input, int m,int n)
    {
        int[][] mat = new int[m][n];
        for(int i=0;i<m;i++)
            for(int j=0;j<n;j++)
            {
                mat[i][j]=input[i*n+j];
            }
        return mat;
    }

    public static void main(String[] args)
    {
        int[] input = {1,3,0,0,0,0,0,0,4,5,1,0,0,0,0,6,7,6,0,0,0,0,5,0};
        int[] a={4,6};
        System.out.println(no_of_path(a,input));
    }


}
