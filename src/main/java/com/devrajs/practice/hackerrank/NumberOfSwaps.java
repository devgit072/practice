package com.devrajs.practice.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by devraj.singh on 9/25/15.
 */
public class NumberOfSwaps {

    static int getNoOfSwaps(String input1,String input2)
    {
        int numberOf1_1=0;
        int numberOf0_1=0;
        int numberOf1_2=0;
        int numberOf0_2=0;
        int diff=0;
        char[] arr1=input1.toCharArray(),arr2=input2.toCharArray();
        if(input1.length()!=input2.length())
            return -1;
        for(int i=0;i<input1.length();i++)
        {
            if(arr1[i]!=arr2[i])
                diff++;
        }
        if(diff==0)
            return 0;
        if(diff%2==0)
            return diff/2;
        else
            return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input1=bufferedReader.readLine();
        String input2=bufferedReader.readLine();
        System.out.println(getNoOfSwaps(input1,input2));
    }
}
