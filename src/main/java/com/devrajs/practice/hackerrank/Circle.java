package com.devrajs.practice.hackerrank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devraj.singh on 9/16/15.
 */
public class Circle {

    static int[] GearList(int n, int d, int[] r, int[] c) {
        int[] t=new int[n];

        for(int i=0;i<n;i++)
        {
            List<Integer> candidates=findCandidate(d,r,i,n);
            if(candidates.size()==0)
                t[i]=0;
            else
            {
                List<Integer> leastCostList = findLeastCostIndex(c,candidates);
                if(leastCostList.size()==1)
                    t[i]=leastCostList.get(0)+1;
                else
                {
                    List<Integer> radList = findMaxRadIndex(r,leastCostList);
                    if(radList.size()==1)
                        t[i]=radList.get(0)+1;
                    else
                    {
                        t[i]=findMinIndex(radList);
                    }
                }
            }
        }
        return t;

    }

    private static List<Integer> findCandidate(int D,int[] r, int currentIndex, int n)
    {
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<n;i++)
        {
            //if(i==currentIndex) continue;
            if(r[i]+r[currentIndex]>=D) list.add(i);
        }
        return list;
    }
    private static List<Integer> findLeastCostIndex(int[] c,List<Integer> candidatesValue)
    {
        List<Integer> leastCostIndex=new ArrayList<Integer>();
        int leastCost=Integer.MAX_VALUE;
        for(Integer index : candidatesValue)
        {
            if(c[index]==leastCost) {
                leastCostIndex.add(index);
            }
            else if(c[index]< leastCost)
            {
                leastCostIndex = new ArrayList<Integer>();
                leastCostIndex.add(index);
                leastCost=c[index];
            }
        }
        return leastCostIndex;
    }

    private static List<Integer> findMaxRadIndex(int[] r,List<Integer> costList)
    {
        List<Integer> radList=new ArrayList<Integer>();
        int maxRad=Integer.MIN_VALUE;
        for(Integer index : costList)
        {
            if(r[index]==maxRad) {
                radList.add(index);
            }
            else if(r[index] > maxRad)
            {
                radList = new ArrayList<Integer>();
                radList.add(index);
                maxRad=r[index];
            }
        }
        return radList;
    }

    private static int findMinIndex(List<Integer> list)
    {
        int min = Integer.MAX_VALUE;
        for(Integer index : list)
        {
            if(index<min)
                min=index;
        }
        return min;
    }

    public static void main(String[] args)
    {
        int[] c={1,3,6,2,5};
        int[] r={5,6,8,3,4};
        int[] res=GearList(5, 8, c, r);
        for(int h: res)
        {
            System.out.print(h + "   ");
        }
    }


}
