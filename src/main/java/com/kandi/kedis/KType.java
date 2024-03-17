package com.kandi.kedis;
import java.util.List;

class KType {
  String str;
  List<String> lst;
  Type type;

  private enum Type{
    STRING, LIST 
  }

  public KType(String str){
    this.str = str;
    this.type = Type.STRING;
    this.lst = null;
  }

  public KType(List<String> lst){
    this.lst = lst;
    this.type = Type.LIST;
    this.str= null;
  }

  public boolean isString(){
    return this.type == Type.STRING;
  }

  public boolean isList(){
    return this.type == Type.LIST;
  }

}
