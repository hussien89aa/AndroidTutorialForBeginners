
myhandler handler =new myhandler();
                // Create Inner Thread Class
class background extends   Thread 
 {
 public void run() 
{                  
Message msgObj = handler.obtainMessage();
Bundle b = new Bundle();
b.putString("message", msg);
msgObj.setData(b);
handler.sendMessage(msgObj);   
 }

}
  
 
// Define the Handler that receives messages from the thread and update the progress
class  myhandler extends Handler {
 public void handleMessage(Message msg) {
                              if ((null != msg))   
                            String aResponse = msg.getData().getString("message");
 
                        }
                    }
 