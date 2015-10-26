package com.devrajs.practice.geeksforgeeks;

/**
 * Created by devraj.singh on 9/22/15.
 */
public class CircularRobot {

    static boolean isCircular(String paths)
    {
        char[] pathArray = paths.toCharArray();
        int x_curr=0,y_curr=0;
        int x_pos=0,y_pos=1,x_min=2,y_min=3;
        int curr_dir=x_pos;
        for(char ch : pathArray)
        {
            if(ch=='G')
            {
                if(curr_dir == x_pos)
                {
                    x_curr++;
                }
                else if(curr_dir==y_pos)
                {
                    y_curr++;
                }
                else if(curr_dir==x_min)
                {
                    x_curr--;
                }
                else if(curr_dir==y_min)
                {
                    y_curr--;
                }
            }
            else if(ch=='L')
            {
                curr_dir=(curr_dir+1)%4;
            }
            else
            {
                curr_dir=(curr_dir-1)%4;
                if(curr_dir==-1)
                    curr_dir=3;
            }
        }
        if(x_curr==0 && y_curr==0)
            return true;
        else
            return false;
    }

    public static void main(String[] args)
    {
        System.out.println(isCircular("GLGLGLG"));
        System.out.println(isCircular("GLLG"));
        System.out.println(isCircular("GLGLGLGLLRGG"));
    }


}
