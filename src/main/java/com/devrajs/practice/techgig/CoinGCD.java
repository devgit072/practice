package com.devrajs.practice.techgig;

/**
 * Created by devraj.singh on 9/25/15.
 */
public class CoinGCD {

    public static int[] coins_value(int[] input1)
    {
        if(input1.length!=2 || (input1[0]<0 || input1[1]<0))
            return new int[]{0,0};
        int a=input1[0];
        int b = input1[1];
        if(a==0)
            return new int[]{0,1};
        if(b==0)
            return new int[]{1,0};

        int gcd=getGCD(a,b);
        //System.out.println(gcd);
        return getMultiples(a,b,gcd);
    }

    private static int[] getMultiples(int a,int b,int gcd)
    {
        if(a==b);
        int m=1,n=1;
        boolean isSwapped=false;
        if(a>b)
        {
            int temp=a;
            a=b;
            b=temp;
            isSwapped=true;
        }
        while((a*m)!=(b*n)+gcd)
        {
            if((a*m) > (b*n + gcd)) n++;
            else if((a*m) < (b*n+gcd)) m++;
            else {
                break;
            }
        }
        if(!isSwapped)
            return new int[]{m, -1 * n};
        else
            return new int[]{-1 * n,m};
    }

    private static int getGCD(int a,int b)
    {
        if(a%b==0) return b;
        else
            return getGCD(b,a%b);
    }

    public static void main(String[] args)
    {
        int[] result = coins_value(new int[]{170,0});
        System.out.print(result[0]+"    "+result[1]);
    }
}
