package com.devrajs.practice.techgig;

import java.util.Arrays;

/**
 * Created by devraj.singh on 9/24/15.
 */
public class Marathon {

    public static String collegecomparison(int[] input1,int[] input2)
    {
        final String EQUAL="Equal";
        final String UNEQUAL="Unequal";
        final String INVALID="Invalid";

        if(input1.length!=input2.length)
            return INVALID;
        if(isNegativePresent(input1) || isNegativePresent(input2))
            return INVALID;
        if(customComparison(input1,input2))
            return EQUAL;
        else
            return UNEQUAL;
    }

    private static boolean customComparison(int[] input1,int[] input2)
    {
        Arrays.sort(input1);
        Arrays.sort(input2);

        for(int i=0;i<input1.length;i++)
            if(input1[i]!=input2[i])
                return false;
        return true;
    }

    private static boolean isNegativePresent(int[] input)
    {
        for(int i : input)
            if(i<0)
                return true;
        return false;
    }

    public static void main(String[] args)
    {
        int[] a={12,11,5,2,7,5,11};
        int[] b={5,12,5,7,11,2,11,8};
        System.out.println(collegecomparison(a,b));
    }
}
