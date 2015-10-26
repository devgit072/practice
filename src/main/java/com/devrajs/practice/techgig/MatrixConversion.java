package com.devrajs.practice.techgig;

import java.util.Arrays;

/**
 * Created by devraj.singh on 9/24/15.
 */
public class MatrixConversion {

    public static String operations_seq(int input1,String[] input2,String[] input3)
    {
        final String INVALID="invalid";
        final String YES="yes";
        final String NO="no";

        if(input2.length != input3.length)
            return INVALID;
        int[][] matrix1=formMatrix(input1,input2);
        int[][] matrix2=formMatrix(input1,input3);
        //printMatrix(matrix1,input1);
        //printMatrix(matrix2,input1);
        if(matrix1==null || matrix2==null)
            return INVALID;
        for(int j=0;j<input1;j++)
        {
            //rotate each column one by one
            int temp=matrix1[input1-1][j];
            int i;
            for(i=input1-2;i>=0;i--)
            {
                matrix1[i+1][j]=matrix1[i][j];
            }
            matrix1[0][j]=temp;
            //printMatrix(matrix1,input1);
            if(compareMatrixRowWise(matrix1,matrix2,input1))
                return YES;
            temp=matrix1[0][j];
            for(i=1;i<input1;i++)
            {
                matrix1[i-1][j]=matrix1[i][j];
            }
            matrix1[input1-1][j]=temp;
            //printMatrix(matrix1,input1);

        }
        return NO;
    }

    static void printMatrix(int[][] mat,int n)
    {
        for(int i=0;i<n;i++)
        {
            System.out.println();
            for(int j=0;j<n;j++)
            {
                System.out.print(mat[i][j]+"  ");
            }
        }
    }

    private static int[][] formMatrix(int n,String[] inputs)
    {
        if(inputs.length!=n)
            return null;
        int[][] matrix = new int[n][n];
        int i=0;
        for(String line:inputs)
        {
            String[] rowElements=line.split("#");
            if(n!=rowElements.length) return null;
            int j=0;
            for(String elementStr:rowElements)
            {
                matrix[i][j]=Integer.parseInt(elementStr);
                j++;
            }
            i++;
        }
        return matrix;
    }

    private static boolean compareMatrixRowWise(int[][] matrix1,int[][] matrix2,int n)
    {
        for(int i=0;i<n;i++)
        {
            int[] array1=new int[n];
            int[] array2=new int[n];

            for(int j=0;j<n;j++)
            {
                array1[j]=matrix1[i][j];
                array2[j]=matrix2[i][j];
            }
            if(!checkRotation(array1,array2,n))
                return false;
        }
        return true;
    }

    private static boolean checkRotation(int[] array1,int[] array2,int n)
    {
        StringBuilder str1=new StringBuilder();
        StringBuilder str2=new StringBuilder();
        for(int i:array1)
        {
            str1.append(i+"#");
        }
        for(int i:array2)
        {
            str2.append(i+"#");
        }
        for(int i:array2)
        {
            str2.append(i+"#");
        }
        String op1=new String(str1);
        String op2=new String(str2);
        System.out.println(op1);
        System.out.println(op2);
        boolean contains = op2.contains(op1);
        return contains;
    }

    public static void main(String[] args)
    {
        int n=3;
        String[] arr1={"11#3#44","12#26#13","21#33#21"};
        String[] arr2={"33#44#11","3#13#12","21#26#21"};
        String res=operations_seq(n,arr1,arr2);
        System.out.println(res);
    }
}
