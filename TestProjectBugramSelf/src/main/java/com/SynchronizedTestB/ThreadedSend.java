package com.SynchronizedTestB;

public class ThreadedSend extends Thread
{
    private String msg;
    private Thread t;
    Sender  sender;
 
    // Recieves a message object and a string
    // message to be sent
    public ThreadedSend(String m,  Sender obj)
    {
        msg = m;
        sender = obj;
    }
 
    public void run()
    {
        // Only one thread can send a message
        // at a time.
        synchronized(sender)
        {
            // synchronizing the snd object
            sender.send(msg);
        }
    }
}
