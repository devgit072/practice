package com.devrajs.practice.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by devraj.singh on 9/6/15.
 */
public class PE99_LargestExponential {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // parse input
        int N = Integer.parseInt(bf.readLine());
        String[] inputs = new String[N];
        for(int i=0;i<N;i++)
        {
            inputs[i]=bf.readLine();
        }
        int k=Integer.parseInt(bf.readLine());
        List<BigInteger> list = new ArrayList<BigInteger>();
        Map<BigInteger,String> map = new HashMap<BigInteger, String>();
        for(String ele : inputs)
        {
            BigInteger value = parsePair(ele);
            map.put(value,ele);
            list.add(value);
        }
        Collections.sort(list);
        BigInteger kth_value = list.get(k-1);
        System.out.println(map.get(kth_value));
    }

    private static BigInteger parsePair(String str)
    {
        String[] base_exp = str.split(" ");
        int base = Integer.parseInt(base_exp[0]);
        int exp = Integer.parseInt(base_exp[1]);
        return calculateExp(base,exp);
    }

    private static BigInteger calculateExp(int b,int e)
    {
        if(e==0)
            return new BigInteger("1");
        else if(e==1)
            return new BigInteger(b+"");
        BigInteger halfValue=calculateExp(b,e/2);
        BigInteger multipliedValue=halfValue.multiply(halfValue);
        if(e%2==0)
        {
            return multipliedValue;
        }
        else
        {
            return multipliedValue.multiply(new BigInteger(b+""));
        }
    }
}
