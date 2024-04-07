package com.kandi.kedis;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// A wrapper over kmap that handles loading and dispatching of commands.
// Any new command has to be loaded and mapped here.
class Kedis{
  private KMap kMap;

  // hashmap of command type: list of arg-types.
  // Example: SET: {String, String}
  private Map<String, String[]> commandTypes;

  public Kedis(){
    this.kMap = new KMap();
    this.commandTypes = new HashMap<>();
    //load the hashmap with kedis commands.
    this.commandTypes.put("SET", new String[]{"String", "String"});
    this.commandTypes.put("GET", new String[]{"String"});
    this.commandTypes.put("LADD", new String[]{"NA"});
    this.commandTypes.put("LSET", new String[]{"String", "int", "String"});
    this.commandTypes.put("LGET", new String[]{"String", "int"});
  }

  public String processCommand(String commandLine) {
    String[] words = commandLine.split(" ");
    if (words.length == 0) {
      return "INVALID COMMAND";
    }

    String command = words[0].toUpperCase();
    if (!commandTypes.containsKey(command)) {
      return "INVALID COMMAND TYPE";
    }

    String[] expectedTypes = commandTypes.get(command);
    if (!expectedTypes[0].equals("NA") && words.length - 1 != expectedTypes.length) {
      return "INCORRECT NO OF ARGS TO THE COMMAND";
    }

    for(int i = 0; i< expectedTypes.length; i++){
      if(expectedTypes[i].equals("NA")){
        break;
      }

      else if(expectedTypes[i].equals("int")){
        String index = words[i + 1];
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(index);
        if(matcher.matches()){
          continue;
        }
        else{
          return "INDEX NOT AN INTEGER";
        }
      }
    }

    // Invoke the corresponding method
    switch (command) {
      case "SET":
      return kMap.set(words[1], words[2]);

      case "GET":
      return kMap.get(words[1]);

      case "LADD":
      String key = words[1];
      String[] values = new String[words.length - 2];
      for(int i = 2; i< words.length; i++){
        values[i - 2] = words[i];
      }
      return kMap.lAdd(key, values);

      case "LSET":
      return kMap.lSet(words[1], Integer.parseInt(words[2]), words[3]);

      case "LGET":
      return kMap.lGet(words[1], Integer.parseInt(words[2]));

      default:
      return "SERVER DOWN";
    }
  }
}
