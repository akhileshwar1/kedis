package com.kandi.kedis;

// A threads poc to test the concurrency of the system.
class Threads {

  private KMap obj;

  public Threads(){
    obj = new KMap();
  }

  public void start() throws InterruptedException{
    new Thread(new Runnable(){
      public void run(){
        obj.set("akhil", "kandi");
        try{
          Thread.sleep(5000);
        }
        catch(InterruptedException e){
          System.out.println("Exception! " + e);
        }
      }
    }).start();

    Thread.sleep(1000);

    int count = 0;
    while(count < 10){
      new Thread(new Runnable(){
        public void run(){
          String value = obj.get("akhil");
          System.out.println("value is " + value); 
        }
      }).start();
      count++;
    }
  }
}
