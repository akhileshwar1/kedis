package com.kandi.kedis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class KMap{
  private HashMap<String, KType> kMap;

  public KMap(){
    kMap = new HashMap<>();
  }

  public synchronized String set(String key, String value){
        System.out.println("Thread " + Thread.currentThread().getId() + " entering set method.");
    KType data = new KType(value);
    kMap.put(key, data);
        System.out.println("Thread " + Thread.currentThread().getId() + " exiting set method.");
    return "OK";
  }

  public synchronized String get(String key){
        System.out.println("Thread " + Thread.currentThread().getId() + " entering get method.");
    if(!kMap.containsKey(key)){
      return "KEY NOT FOUND";
    }

    KType data = kMap.get(key);
 
    if(!data.isString()){
      return "INVALID COMMAND FOR TYPE";
    }

        System.out.println("Thread " + Thread.currentThread().getId() + " exiting get method.");
    return data.str;
  }

  public synchronized String incrBy(String key, int value){
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
  public synchronized String lAdd(String key, String... moreArgs){
    ArrayList<String> list = new ArrayList<>();
    for(String str : moreArgs){
      list.add(str);
    }

    KType data = new KType(list);
    kMap.put(key, data);
    return String.join(",", list);
  }

  public synchronized String lSet(String key, int index, String value){
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

  public synchronized String lGet(String key, int index){
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
