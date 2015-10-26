package com.devrajs.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by devraj.singh on 9/5/15.
 */

public class CandidateCode
{
    static int[][] adjacencyMatrix;
    static List<List<String>> pathList = new ArrayList<List<String>>();
    static List<String> pathSoFar = new ArrayList<String>();
    static Map<String,Integer> pathCountMap = new HashMap<String, Integer>();
    static List<String> pathIndex=new LinkedList<String>();
    static List<String> solutionSet = new ArrayList<String>();
    public static String[] applyTolls(int input1,int input2,String[] input3)
    {
        //first create adjacency matrix
        createAdjMatrix(input1,input3);
        //find all possible paths and create path in doublyLinkedList
        findAllPaths(adjacencyMatrix,input1);
        processPath();
        String[] solutionArray =toArray(solutionSet);
        return solutionArray;
    }
    private static String[] toArray(List<String> list)
    {
        String[] array=new String[list.size()];
        int i=0;
        for(String ele : list)
        {
            array[i++]=ele;
        }
        return array;
    }

    private static void createAdjMatrix(int numOfVertices,String[] costArray)
    {
        adjacencyMatrix=new int[numOfVertices][numOfVertices];
        for(int i=0;i<numOfVertices;i++)
        {
            for (int j=0;j<numOfVertices;j++)
            {
                adjacencyMatrix[i][j]=0;
            }
        }
        for(String cost:costArray)
        {
            String[] ele=cost.split("#");
            int u=Integer.parseInt(ele[0]);
            int v=Integer.parseInt(ele[1]);
            int u_v_cost=Integer.parseInt(ele[2]);
            adjacencyMatrix[u-1][v-1]=u_v_cost;
            pathIndex.add(u+"#"+v);
        }
    }

    private static void findAllPaths(int[][] adjacencyMatrix,int v)
    {
        findAllPathsUtil(adjacencyMatrix,0,v);
        /*System.out.println("printing matrix");
        for(int i=0;i<v;i++)
        {
            System.out.println();
            for(int j=0;j<v;j++)
                System.out.print(adjacencyMatrix[i][j] + "  ");
        }
        System.out.println();
        for(List<String> path : pathList)
        {
            System.out.println(path.toString());
        }*/
    }
    private static void findAllPathsUtil(int[][] adjacencyMatrix,int u,int v)
    {
        if(u==v-1)
        {
            List<String> newPathList = new ArrayList<String>();
            for(String ele : pathSoFar)
                newPathList.add(ele);
            newPathList.add(0, 1 + "");
            //System.out.println(newPathList);
            pathList.add(newPathList);
            //pathSoFar.remove(pathSoFar.size()-1);
            return;
        }
        for(int i=0;i<v;i++)
        {
            if(adjacencyMatrix[u][i]!=0)
            {
                pathSoFar.add(i+1+"");
                findAllPathsUtil(adjacencyMatrix, i, v);
                pathSoFar.remove(i + 1 + "");
            }
        }
    }

    private static void processPath()
    {
        int maxCost=Integer.MIN_VALUE;
        List<int[]> arrList = new ArrayList<int[]>();
        int tolledPath=0;
        List<String> diffTollList = new ArrayList<String>();
        if(pathList.size()==0)
        {
            solutionSet.add("No Solution");
            return;
        }

        for(List<String> path : pathList)
        {
            int[] arr = new int[path.size()];
            int i=0;
            for(String u : path)
            {
                arr[i++]=Integer.parseInt(u);
            }
            arrList.add(arr);
            int pathCost=0;
            for(i=1;i<arr.length;i++)
            {
                String formedElement = arr[i-1]+"#"+arr[i];
                if(pathCountMap.containsKey(formedElement))
                    pathCountMap.put(formedElement,2);
                else
                    pathCountMap.put(formedElement,1);
                pathCost+=adjacencyMatrix[arr[i-1]-1][arr[i]-1];
            }
            //System.out.println("de1"+pathCost);
            if(pathCost>maxCost)
            {
                maxCost=pathCost;
            }

        }
        //System.out.println("OK"+pathIndex.toString());
        //System.out.println("lo"+pathCountMap.toString());

        for(List<String> path : pathList)
        {
            int[] arr = new int[path.size()];
            int i=0;
            for(String u : path)
            {
                arr[i++]=Integer.parseInt(u);
            }
            arrList.add(arr);
            int pathCost=0;
            for(i=1;i<arr.length;i++)
            {
                pathCost+=adjacencyMatrix[arr[i-1]-1][arr[i]-1];
            }


            if(pathCost<maxCost)
            {
                tolledPath++;
                int diffToll = maxCost-pathCost;
                for(int j=arr.length-1;j>0;j--)
                {
                    String pathKey = arr[j-1]+"#"+arr[j];
                    //System.out.println("pathKey"+pathKey);
                    if(pathCountMap.get(pathKey)==1) {
                        diffTollList.add((pathIndex.indexOf(pathKey)+1) + "#" + diffToll);
                        break;
                    }
                }
            }
        }
        //System.out.println("h:"+diffTollList.toString());
        Collections.sort(diffTollList);
        String solution1=tolledPath + "#" + maxCost;
        //System.out.println("max:" + solution1);
        //System.out.println("toll"+diffTollList.toString());
        if(diffTollList.size()!=0) {
            solutionSet.add(solution1);
            solutionSet.addAll(diffTollList);
        }
        else
            solutionSet.add("No Solution");
    }

    public static void main(String[] args)
    {
        String[] tolls=new String[8];
        tolls[0]="1#2#8";
        tolls[1]="1#4#7";
        tolls[2]="1#5#12";
        tolls[3]="2#3#4";
        tolls[4]="2#4#2";
        tolls[5]="3#6#6";
        tolls[6]="4#6#8";
        tolls[7]="5#6#10";
        String[] tolls1=new String[6];
        tolls1[0]="1#2#7";
        tolls1[1]="1#2#8";
        tolls1[2]="1#3#10";
        tolls1[3]="2#4#4";
        tolls1[4]="2#4#3";
        tolls1[5]="3#4#15";
        String[] sol= applyTolls(6, 8, tolls);
        for(String s: sol)
            System.out.print(s + "    ");
    }
}