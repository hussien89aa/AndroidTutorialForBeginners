
//runOnUiThread
class MyThread extends Thread{
	 @Override
            public void run() {

               runOnUiThread(new Runnable() //run on ui thread
                 {
                  public void run() 
                  { 
                    Log.i("timeleft",""+timeleft);  
                    //update ui
 
                  }
                 });


           }
}

MyThread t1 =new MyThread();  
t1.start(); 

/*
   txtv.post(new Runnable() {
                    public void run(){
                        //TODO
                        txtv.setText("hi");
                    }
                });

                */

       //other thread

class Multi3 implements Runnable{  
public void run(){  
System.out.println("thread is running...");  
}  
  
Multi3 m1=new Multi3();  
Thread t1 =new Thread(m1);  
t1.start(); 


