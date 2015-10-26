package com.devrajs.practice.techgig;

/**
 * Created by devraj.singh on 10/24/15.
 */
public class MaxHelicopterLength {

    public static int landingPosition(int[] input1,String[] input2)
    {
        if(input1.length!=2) return -1;
        int M=input1[1], N=input1[0];
        if(M!=input2.length) return -1;
        int[][] booleanMatrix=formBooleanMatrix(M, N, input2);
        if(booleanMatrix==null) return -1;
        int maxLength = calculateMaxArea(booleanMatrix, M,N);
        return maxLength;
    }

    private static int[][] formBooleanMatrix(int m,int n,String[] inputMatrix)
    {
        int[][] matrix = new int[m][n];
        int i=0;
        for(String line:inputMatrix)
        {
            String[] rowElements=line.split("#");
            if(n!=rowElements.length) return null;
            int j=0;
            for(String elementStr:rowElements)
            {
                if(elementStr.equalsIgnoreCase("x"))
                    matrix[i][j]=0;
                else if(elementStr.equalsIgnoreCase("o"))
                    matrix[i][j]=1;
                else
                    return null;
                j++;
            }
            i++;
        }
        return matrix;
    }

    private static int calculateMaxArea(int[][] booleanMatrix, int m, int n)
    {
        int[][] sumMatrix = new int[m][n];
        //initialize first row and column
        for(int i=0;i<m;i++)
            sumMatrix[i][0]=booleanMatrix[i][0];
        for(int j=0;j<n;j++)
            sumMatrix[0][j]=booleanMatrix[0][j];
        for(int i=1;i<m;i++)
        {
            for(int j=1;j<n;j++)
            {
                if(booleanMatrix[i][j]==0)
                    sumMatrix[i][j]=0;
                else {
                    sumMatrix[i][j] = booleanMatrix[i][j] + min(sumMatrix[i-1][j-1],sumMatrix[i-1][j],sumMatrix[i][j-1]);
                }
            }
        }

        //scan largest element
        int MAX = Integer.MIN_VALUE;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                int val = sumMatrix[i][j];
                if(val>MAX)
                    MAX=val;
            }
        }
        return MAX;
    }

    private static int min(int a, int b, int c)
    {
        int MIN = Integer.MAX_VALUE;
        if(a<=MIN) MIN=a;
        if(b<=MIN) MIN=b;
        if(c<=MIN) MIN=c;
        return MIN;
    }

    public static void main(String[] args)
    {
        int[] input1={1,1};
        //String[] input2={"x#o#o#o#x#o","x#o#o#o#x#x","x#o#o#o#x#x","x#o#x#o#o#x"};//,"x#o#x#o#o#x"};
        String[] input2={"o"};//#o#o#o#x#o","x#o#o#o#x#x","x#o#o#o#x#x","x#o#x#o#o#x"};//,"x#o#x#o#o#x"};
        int val = landingPosition(input1,input2);
        System.out.println(val);
    }
}
