package com.kandi.kedis;
import java.util.HashMap;

class KMapString implements KMap{

  private HashMap<String, String> kMapString;

  public KMapString(){
    kMapString = new HashMap<>();
  }

  @Override
  public String set(String key, String value){
    kMapString.put(key, value);
    return "OK";
  }

  @Override
  public String get(String key){
    if(kMapString.containsKey(key)){
      return kMapString.get(key);
    }
    else{
      return "NOT FOUND";
    }
  }

  @Override
  public String incrBy(String key, int value){
    if(kMapString.containsKey(key)){
      
     long currValue = Long.parseLong(kMapString.get(key));
     long newValue = currValue + value;
     kMapString.put(key, String.valueOf(newValue));
     return "OK";
    }
    else{
      // create it. 
      kMapString.put(key, String.valueOf(value));
      return "OK";
    }
  }


}
