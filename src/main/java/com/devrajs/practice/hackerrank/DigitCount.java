package com.devrajs.practice.hackerrank;

/**
 * Created by devraj.singh on 9/6/15.
 */
public class DigitCount {

    static void findingDigits(int[] foo) {

        for(int ele:foo)
        {
            System.out.println(findingDigitsUtil(ele));
        }
    }

    static int findingDigitsUtil(int N)
    {
        int count=0;
        int N_temp=N;
        int div=10;
        while(N_temp!=0)
        {
            int mod=N_temp%div;

            if(mod!=0 && N%mod==0)
            {
                count++;
            }
            N_temp/=div;
        }
        return count;
    }

    public static void main(String[] args)
    {
        int[] arr = new int[]{122,101222};
        findingDigits(arr);
    }
}
