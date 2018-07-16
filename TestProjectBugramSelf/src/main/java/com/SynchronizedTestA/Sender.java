package com.SynchronizedTestA;
//ADAPTED FROM https://www.geeksforgeeks.org/synchronized-in-java/


import java.io.*;
import java.util.*;
 
// A Class used to send a message
public class Sender
{
    public void send(String msg)
    {
        System.out.println("Sending\t"  + msg );
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("Thread  interrupted.");
        }
        System.out.println("\n" + msg + "Sent");
    }
}