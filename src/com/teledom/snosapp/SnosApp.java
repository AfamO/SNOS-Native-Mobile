package com.teledom.snosapp;


import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.push.Push;
import com.codename1.push.PushCallback;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import userclasses.StateMachine;
public class SnosApp implements PushCallback {
   
    private Form current;
    JSONConnectionObject  jcb;
    StateMachine sm;
    Boolean IsPushNotificationReceived=false;
    public void init(Object context) {
        // Pro users - uncomment this code to get crash reports sent to you automatically
        Display.getInstance().addEdtErrorHandler(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                evt.consume();
                Log.p("Exception in AppName version " + Display.getInstance().getProperty("AppVersion", "Unknown"));
                Log.p("OS " + Display.getInstance().getPlatformName());
                Log.p("Error " + evt.getSource());
                Log.p("Current Form " + Display.getInstance().getCurrent().getName());
                Log.e((Throwable)evt.getSource()  );
                Log.sendLog();
                
            }

            
        });
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }
        new StateMachine("/theme");
        //get the below object to get the url assigned at the constructor
        sm=new StateMachine();
        //Has the user already accessed the webservice class?
        if(StateMachine.getJSONConnObj()==null)
        {
            jcb =new JSONConnectionObject(StateMachine.url);
        }
        else
        {
            jcb =StateMachine.getJSONConnObj();
        }
        StateMachine.setJSONConnObj(jcb);
        //write the maximum alert limits to be stored locally
        Storage.getInstance().writeObject("alerts_limit","400");
        //write an bb10-as an id just for the purpose of tracking
        Storage.getInstance().writeObject("device_id", "bb10");
       
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
    public void push(String value) {
        IsPushNotificationReceived=true;
        //since this is BB10-it does not support Push at the time of writing
        //Dialog.show("Push-Security Alert Received", value, "OK", null);
        }
 
    public void registeredForPush(String deviceId) {
        //since this is BB10-it does not support Push at the time of writing
        /*
        Dialog.show("Push Registered", "Device ID: " + deviceId + "\nDevice Key: " + Push.getDeviceKey() , "OK", null);
        Storage.getInstance().writeObject("device_push_id", Push.getDeviceKey());
        */
        
        
    }
    public void pushRegistrationError(String error, int errorCode) {
         //since this is BB10-it does not support Push at the time of writing
        /*
        Dialog.show("Registration Error", "Error " + errorCode + "\n" + error, "OK", null);
        Log.sendLog();
        */
    }
}
