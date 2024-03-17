package com.kandi.kedis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class KMap{
  private HashMap<String, KType> kMap;

  public KMap(){
    kMap = new HashMap<>();
  }

  public String set(String key, String value){
    KType data = new KType(value);
    kMap.put(key, data);
    return "OK";
  }

  public String get(String key){
    if(!kMap.containsKey(key)){
      return "KEY NOT FOUND";
    }

    KType data = kMap.get(key);
 
    if(!data.isString()){
      return "INVALID COMMAND FOR TYPE";
    }

    return data.str;
  }

  public String incrBy(String key, int value){
    if(!kMap.containsKey(key)){
      // create it. 
      KType data = new KType(String.valueOf(value));
      kMap.put(key, data);
      return "OK";
    }

    KType data = kMap.get(key);

    if(!data.isString()){
      return "INVALID COMMAND FOR TYPE";
    }

    long currValue = Long.parseLong(data.str);
    long newValue = currValue + value;
    data.str = String.valueOf(newValue);
    kMap.put(key, data);
    return "OK";
    
  }

  // LIST FUNCTIONS.
  public String LAdd(String key, String... moreArgs){
    ArrayList<String> list = new ArrayList<>();
    for(String str : moreArgs){
      list.add(str);
    }

    KType data = new KType(list);
    kMap.put(key, data);
    return String.join(",", list);
  }

  public String set(String key, int index, String value){
    if(!kMap.containsKey(key)){
      return "KEY NOT PRESENT";
    }

    KType data = kMap.get(key);

    if(!data.isList()){
      return "INVALID COMMAND FOR TYPE";
    }
    
    data.lst.add(index, value);
    return "OK";
  }

  public String get(String key, int index){
    if(!kMap.containsKey(key)){
      return "KEY NOT PRESENT";
    }

    KType data = kMap.get(key);

    if(!data.isList()){
      return "INVALID COMMAND FOR TYPE";
    }

    List<String> lst = data.lst;
    if(lst.size() <= index){
      return "INDEX NOT FOUND";
    }

    return lst.get(index);
  }

}
