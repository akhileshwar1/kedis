package com.kandi.kedis;

interface KMap{

  default String set(String key, String value){
    return "No implementation found";
  }

  default String set(String key, int index, String value){
    return "No implementation found";
  }

  default String get(String key){
    return "No implementation found";
  }

  default String get(String key, int index){
    return "No implementation found";
  }

  default String incrBy(String key, int value){
    return "No implementation found";
  }

}
