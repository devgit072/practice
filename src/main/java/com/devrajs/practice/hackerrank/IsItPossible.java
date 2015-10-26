package com.devrajs.practice.hackerrank;

/**
 * Created by devraj.singh on 9/16/15.
 */
public class IsItPossible {

    static String isitpossible(int a, int b, int c, int d) {
        boolean result = isitpossibleUtil(a,b,c,d);
        return result?"Yes":"No";
    }

    private static boolean isitpossibleUtil(int a, int b, int c, int d)
    {
        if(a>c || b>d) return false;
        else if(a==c && b==d)
            return true;
        else
            return isitpossibleUtil(a+b,b,c,d) || isitpossibleUtil(a,b+a,c,d);
    }

    public static void main(String[] args)
    {
        System.out.println(isitpossible(1,4,5,9));
    }
}
