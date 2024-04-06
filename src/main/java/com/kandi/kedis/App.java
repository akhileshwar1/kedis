package com.kandi.kedis;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
     KMap map = new KMap();
     Scanner sc = new Scanner(System.in);
     while(sc.hasNextLine()){
      String line = sc.nextLine();
      String[] words = line.split(" ");
      int len = words.length;
      String type = words[0];
      if(type.equals("SET") && len == 3){
        String key = words[1];
        String value = words[2];
        System.out.println(map.set(key, value));
      }
      else if(type.equals("SET") && len == 4){
        String key = words[1];
        String index = words[2];
        String value = words[3];
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(index);
        if(matcher.matches()){
          System.out.println(map.set(key, Integer.parseInt(index), value));
        }
        else{
          System.out.println("INDEX NOT AN INTEGER");
        }
      }
      else if(type.equals("GET") && len == 2){
        String key = words[1];
        System.out.println(map.get(key));
      }
      else if(type.equals("GET") && len == 3){
        String key = words[1];
        String index = words[2];
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(index);
        if(matcher.matches()){
          System.out.println(map.get(key, Integer.parseInt(index)));
        }
        else{
          System.out.println("INDEX NOT AN INTEGER");
        }
      }
      else if(type.equals("LADD")){
        String key = words[1];
        String[] values = new String[len - 2];
        for(int i = 2; i< len; i++){
          values[i - 2] = words[i];
        }
        System.out.println(map.LAdd(key, values));
      }
    }
    sc.close();
    }
}
