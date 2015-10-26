package com.devrajs.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by devraj.singh on 9/3/15.
 */


public class MaxProfit {

    public static int getMaxProfit(List<String> bookings)
    {
        List<Pair> pairList = new ArrayList<Pair>();
        //first sort
        for(String timing:bookings)
        {
            pairList.add(parseString(timing));
        }
        pairList=sortPair(pairList);
        Pair[] pairs=toArray(pairList);
        return calculateProfit(pairs,0,pairs.length-1,new ArrayList<Pair>());
    }

    static int calculateProfit(Pair[] arr, int si, int ei,List<Pair> list)
    {
        if(si>ei) return 0;
        else if(si==ei) return 1;
        int max=Integer.MIN_VALUE;
        for(int i=si;i<=ei;i++)
        {
            int newMax = list.size();
            list.add(arr[i]);
            int end_time_temp = arr[i].endTime;
            int j=i+1;
            while(j<=ei && arr[j].startTime<end_time_temp) j++;
            if(j<=ei)
            {
                int currentBookings = calculateProfit(arr,j,ei,list);
                newMax+=currentBookings;
            }
            if(newMax>max) max=newMax;
            list.remove(arr[i]);
        }
        return max;
    }

    static Pair parseString(String str)
    {
        int startTime;
        int endTime;
        String[] arr=str.split("#");
        startTime=getTime(arr[0]);
        endTime=getTime(arr[1]);
        return new Pair(startTime,endTime);
    }

    static int getTime(String timeStr)
    {
        int time;
        int lenOfStr=timeStr.length();
        time=Integer.parseInt(timeStr.substring(0, lenOfStr - 2));
        if(timeStr.startsWith("am"))
            return time;
        else {
            if(time==12) time=0;
            return time + 12;
        }
    }

    static List<Pair> sortPair(List<Pair> pairList)
    {
        Pair[] arrayOfPairs = toArray(pairList);
        for(int i=1;i<arrayOfPairs.length;i++)
        {
            for(int j=0;j<i;j++)
            {
                if(arrayOfPairs[j].startTime>arrayOfPairs[i].startTime)
                {
                    Pair temp = arrayOfPairs[j];
                    arrayOfPairs[j]=arrayOfPairs[i];
                    arrayOfPairs[i]=temp;
                }
            }
        }
        List<Pair> sortedList = new ArrayList<Pair>();
        for(int i=0;i<arrayOfPairs.length;i++)
        {
            sortedList.add(arrayOfPairs[i]);
        }
        return sortedList;
    }


    static Pair[] toArray(List<Pair> list)
    {
        Pair[] arr = new Pair[list.size()];
        int i=0;
        for(Pair pair: list)
        {
            arr[i++]=pair;
        }
        return arr;
    }
}

class Pair
{
    int startTime;
    int endTime;

    Pair(int startTime,int endTime)
    {
        this.startTime=startTime;
        this.endTime=endTime;
    }
}



class CalculateMaxProfit
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<String>();
        list.add("5am#8am");
        list.add("9am#10am");
        list.add("11am#1pm");
        list.add("8am#3pm");
        list.add("7pm#8pm");
        list.add("7am#9pm");
        list.add("3pm#4pm");
        list.add("4pm#5pm");
        list.add("5pm#6pm");
        System.out.println(MaxProfit.getMaxProfit(list));
    }
}
