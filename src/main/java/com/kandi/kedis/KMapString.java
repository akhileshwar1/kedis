package com.kandi.kedis;
import java.util.HashMap;

class KMapString{

  private HashMap<String, String> kMapString;

  private String set(String key, String value){
    kMapString.put(key, value);
    return "OK";
  }

  private String get(String key){
    if(kMapString.containsKey(key)){
      return kMapString.get(key);
    }
    else{
      return "NOT FOUND";
    }
  }

  private String incrBy(String key, Integer value){
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
