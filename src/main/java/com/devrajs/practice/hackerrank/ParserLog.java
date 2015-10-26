package com.devrajs.practice.hackerrank;

/**
 * Created by devraj.singh on 9/6/15.
 */
public class ParserLog {

    static void parseLog(String line)
    {
        System.out.println(parseUtil(line));
    }

    static String parseUtil(String line)
    {
        String[] arr = line.split(" ");
        String temp=arr[4];
        int orderId=Integer.parseInt(temp.substring(temp.indexOf(','),temp.indexOf(')')));
        String status="";
        if(arr[2].equalsIgnoreCase("{wait_mkt,new,no_reason}"))
            status="Order " + orderId + " sent to exchange";
        else if(arr[2].equalsIgnoreCase("{open,ack,no_reason}"))
            status="Order " + orderId + " acknowledged";
        else if(arr[2].equalsIgnoreCase("{wait_mkt,cancel,no_reason}"))
            status="Order " + orderId + " cancel requested";
        else if (arr[2].equalsIgnoreCase("{closed,canceled,no_reason}"))
            status="Order " + orderId + " cancel confirmed - order closed";
        else
            status="Not able to parse the log";

        String shareName=arr[7];
        String action=arr[8];
        String qty=arr[9];
        String price=arr[10];
        status += "\t" + action + " " + qty +" "+ shareName + " @"+price;
        return status;
    }

    public static void main(String[] args)
    {

    }


}
