package com.devrajs.practice.techgig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by devraj.singh on 10/24/15.
 */

public class MatrixConversion1 {

    public static String operations_seq(int input1,String[] input2,String[] input3)
    {
        String YES="yes";
        String NO="no";
        String INVALID="invalid";

        //Write code here

        int[][] matrix1 = formMatrix(input1,input2);
        if(matrix1==null) return INVALID;

        int[][] matrix2 = formMatrix(input1,input3);
        if(matrix2==null) return INVALID;
        boolean compareMatrix = compareMatrixByElementsFrequency(matrix1,matrix2,input1);
        if(!compareMatrix) return NO;
        if(!checkRotationColumnWise(matrix1,matrix2,input1))
            return NO;

        boolean checkConversion = checkConversion(matrix1,matrix2,input1);
        if(checkConversion)
            return YES;
        else
            return NO;
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

    private static boolean compareMatrixByElementsFrequency(int[][] matrix1,int[][] matrix2, int N)
    {
        Map<Integer,Integer> frequency1=new HashMap<Integer, Integer>();
        Map<Integer,Integer> frequency2=new HashMap<Integer, Integer>();
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                int freq1=0;
                if(frequency1.containsKey(matrix1[i][j]))
                    freq1= frequency1.get(matrix1[i][j]);
                int freq2=0;
                if(frequency2.containsKey(matrix2[i][j]))
                    freq2= frequency2.get(matrix2[i][j]);
                frequency1.put(matrix1[i][j],freq1);
                frequency2.put(matrix2[i][j],freq2);
            }
        }
        for(Map.Entry<Integer,Integer> entry: frequency1.entrySet())
        {
            int key= entry.getKey();
            int value = entry.getValue();
            if(!frequency2.containsKey(key))
                return false;
            int value2 = frequency2.get(key);
            if(value!=value2) return false;
        }
        return true;
    }

    private static boolean checkConversion(int[][] matrix1,int[][] matrix2, int N)
    {
        List<Map<Integer,Integer>> mapList1 = new ArrayList<Map<Integer, Integer>>();
        List<Map<Integer,Integer>> mapList2 = new ArrayList<Map<Integer, Integer>>();

        //prepare mapList

        for(int i=0;i<N;i++)
        {
            Map<Integer,Integer> map1=new HashMap<Integer, Integer>();
            Map<Integer,Integer> map2=new HashMap<Integer, Integer>();
            for(int j=0;j<N;j++)
            {
                int ele1=matrix1[i][j];
                int ele2=matrix2[i][j];
                int count1=map1.containsKey(ele1)?map1.get(ele1):0;
                int count2=map2.containsKey(ele2)?map2.get(ele2):0;
                map1.put(ele1,count1+1);
                map2.put(ele2,count2+1);
            }
            mapList1.add(map1);
            mapList2.add(map2);
        }
        //compare maps created

        int diff=0;
        Integer diffNum = null;

        for(int i=0;i<N;i++)
        {
            Map<Integer,Integer> rowMap1=mapList1.get(i);
            Map<Integer,Integer> rowMap2=mapList2.get(i);
            if(diffNum!=null)
            {
                if(!rowMap2.containsKey(diffNum)) return false;
            }
            for(Map.Entry<Integer,Integer> entry: rowMap1.entrySet())
            {
                int key = entry.getKey();
                int value = entry.getValue();
                if(!rowMap2.containsKey(key))
                {
                    diff++;
                    diffNum=key;
                }
                else
                {
                    int value2 = rowMap2.get(key);
                    if(value-value2 > 1 || value-value2 < -1)
                        return false;
                }
            }
            if(diff>1)
                return false;
            else if(diff==0)
                diffNum=null;
            else
            {
                checkRotationOfArray(matrix1,matrix2,rowMap1,rowMap2,diffNum,i,N);
            }

            diff=0;
        }
        if(diffNum!=null)
        {
            Map<Integer,Integer> rowMap=mapList2.get(0);
            if(!rowMap.containsKey(diffNum))
                return false;
        }
        return true;
    }

    static boolean checkRotationOfArray(int[][] matrix1, int[][] matrix2,Map<Integer,Integer> rowMap1,Map<Integer,Integer> rowMap2, int diffNum,int rowNum, int N)
    {
        String str1="";
        String str2="";

        int j=0;
        for(;j<N;j++)
        {
            str1+=matrix1[rowNum][j]+"#";
            int val = matrix2[rowNum][j];
            if(!rowMap1.containsKey(val))
                str2+=diffNum+"#";
            else
            {
                int v1=rowMap1.get(val);
                int v2=rowMap2.get(val);
                if(v2-v1==1) {
                    str2 += diffNum+"#";
                    break;
                }
                else
                {
                    str2+=val+"#";
                }
            }
        }
        for(;j<N;j++)
        {
            str2+=matrix2[rowNum][j]+"#";
        }
        String combinedStr=str1+"#"+str1;
        return combinedStr.contains(str2);
    }

    public static boolean checkRotationColumnWise(int[][] matrix1 ,int[][] matrix2,int n)
    {
        for(int j=0;j<n;j++)
        {
            boolean sameElementInEntireColumn=true;
            int firstElement=matrix1[0][j];
            for(int i=1;i<n;i++)
            {
                if(matrix1[i][j]!=firstElement)
                {
                    sameElementInEntireColumn=false;
                    break;
                }
            }
            if(sameElementInEntireColumn)
                return true;
        }
        boolean sameMatrix=true;
        for(int i=0;i<n;i++) {
            for (int j = 0; j<n; j++) {
                if(matrix1[i][j] != matrix2[i][j]) {
                    sameMatrix = false;
                    break;
                }
            }
            if(!sameMatrix)
                break;
        }
        if(sameMatrix)
            return false;
        return true;
    }
    public static void main(String[] args)
    {
        int input1=3;
        String[] input2={"11#3#44","12#26#13","21#33#21"};
        String[] input3={"33#44#11","3#13#12","21#26#21"};
        //String[] input2={"11#3#44","12#26#13","21#33#21"};
        //String[] input3={"11#33#21","12#3#44","21#26#13"};

        //String[] input2={"11#3#44","12#26#13","21#33#21"};
        //String[] input3={"11#33#21","12#3#44","21#26#13"};
        //String[] input2={"11#3#44","12#26#13","21#33#21"};
        //String[] input3={"11#33#21","12#3#44","21#26#19"};

        //String[] input2={"11#11","13#12"};
        //String[] input3={"11#11","13#12"};

        String value = operations_seq(input1,input2,input3);
        System.out.println(value);

    }
}
