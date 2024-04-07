package com.kandi.kedis;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
    Scanner sc = new Scanner(System.in);
     Kedis kedis = new Kedis();
     while(sc.hasNextLine()){
      String line = sc.nextLine();
      System.out.println(kedis.processCommand(line));
    }
    sc.close();
    }
}
