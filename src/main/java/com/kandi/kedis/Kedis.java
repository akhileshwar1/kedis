package com.kandi.kedis;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// A wrapper over kmap that provides an interface that takes in a command.
class Kedis{
  private KMap kMap;
  public Kedis(){
    this.kMap = new KMap();
  }

  public String runCommand(String str){
    String[] words = str.split(" ");
      int len = words.length;
      String type = words[0];
      if(type.equals("SET") && len == 3){
        String key = words[1];
        String value = words[2];
        return kMap.set(key, value);
      }
      else if(type.equals("LSET") && len == 4){
        String key = words[1];
        String index = words[2];
        String value = words[3];
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(index);
        if(matcher.matches()){
          return kMap.lSet(key, Integer.parseInt(index), value);
        }
        else{
          return "INDEX NOT AN INTEGER";
        }
      }
      else if(type.equals("GET") && len == 2){
        String key = words[1];
        return kMap.get(key);
      }
      else if(type.equals("LGET") && len == 3){
        String key = words[1];
        String index = words[2];
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(index);
        if(matcher.matches()){
          return kMap.lGet(key, Integer.parseInt(index));
        }
        else{
          return "INDEX NOT AN INTEGER";
        }
      }
      else if(type.equals("LADD")){
        String key = words[1];
        String[] values = new String[len - 2];
        for(int i = 2; i< len; i++){
          values[i - 2] = words[i];
        }
        return kMap.lAdd(key, values);
      }
    else{
      return "NOT A VALID COMMAND";
    }
  }


}
