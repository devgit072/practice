package com.devrajs.practice.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by devraj.singh on 9/12/15.
 */
public class BestTeam {

    public static void main(String[] args)
    {
        int[] players=new int[10];
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < 10; i++)
                players[i] = Integer.parseInt(bf.readLine());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(calculateBestTeamValue(players));
    }

    private static int calculateBestTeamValue(int[] players)
    {
        //sort array
        for(int i=0;i<9;i++)
            for(int j=i+1;j<10;j++)
            {
                if(players[i]>players[j])
                {
                    int temp=players[i];
                    players[i]=players[j];
                    players[j]=temp;
                }
            }
        return players[9]+players[7]+players[5];
    }
}
