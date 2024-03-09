package com.kandi.kedis;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class KMapList{

  private HashMap<String, ArrayList<String>> kMapList;

  public String kList(String key, String... moreArgs){
    ArrayList<String> list = new ArrayList<>();
    for(String str : moreArgs){
      list.add(str);
    }

    kMapList = new HashMap<>();
    kMapList.put(key, list);
    return String.join(",", list);
  }

  public String LINDEX(String key, int index){
    if(kMapList.containsKey(key)){
      List<String> lst = kMapList.get(key);
      if(index<lst.size()){
        return lst.get(index); 
      }
      return  "INDEX NOT FOUND";

    }
    else{
      return "KEY NOT PRESENT";
    }
  }

  public String LSET(String key, int index, String value){
    if(kMapList.containsKey(key)){
      List<String> lst = kMapList.get(key);
        lst.add(index, value); 
        return "OK";
    }
    else{
      return "KEY NOT PRESENT";
    }
  }
}
