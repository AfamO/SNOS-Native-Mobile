/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package com.teledom.snosapp;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Dialog;
import com.codename1.io.NetworkManager;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.Storage;
import com.codename1.system.DefaultCrashReporter;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.util.Hashtable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import userclasses.StateMachine;
import static userclasses.StateMachine.WebServiceReturnedResult;
/**
*This class encapsulates and defines ALL the methods needed to make connections to remote web service and returns the appropriate results.
* @param loginform Form object that stores the current instance of LoginForm object whenever the user signs in at the application start-up.
* @param url String object that represents the remote URL needed to connect to remote web service.
* @param content_type String object that represents the request content type.
* @param request ConnectionRequest object that represents the request object to sent to the remote web service.
* @param ntwrkmanager NetworkManager object that represents  the network manager class which facilitates connection to web service.
* @param hashtableObj Hashtable object that represents the hash table used to get all the parsed raw data returned from the remote server.
* @param status String object that represents that the current status of json result or message returned from the remote web service.(Generally: 1=success,0=wrong input,3=failure)
* @param lga String array object that represents the user's local govt area.
* @param lstmvc ListMVC object that represents the Lists Object form storing the Security alerts and their corresponding details(dates,action).
* @param WebServiceReturnedResult String object that represents the result of each request processed by web service.
* @param IsEmailRegistered Boolean variable that represents and checks whether a particular email address has already been registered in the remote database.
* @param IssmsStoredlocally Boolean variable that represents and checks whether client's sms alerts have already been stored in his/her local phone..
* @param sToredAlertsLimits int variable that represents the limit of number of alerts allowed to be stored locally.
* @param device_contactNumberCounter int variable that represents the user's device or contact counter and index number.
* @param total_current_sms_DiFF int variable that represents the difference between the sms total count and sms current count.
* @param RetrievedRowSize int variable that represents the row size and total number of the client's device or contacts data.
* @param SMSTotalCount String object that represents and holds the total number of  sms alerts.(E.g: 1-10 of 50); 50=sms total count.
* @param MinimumBound String object that represents and holds the sms minium bound in the range(E.g: 1-10 of 50); 1=minimum bound.
* @param Min String object that represents and holds the incrementer of sms current count.(E.g: Min=SMScurrentcount+10);
* @param SMSCurrentCount String object that represents and holds the sms current returned count in the range(E.g: 1-10 of 50); 10=sms current count.
* @param user String object that represents and holds the client's username.
* @param pass String object that represents and holds the client's password.
* @param table_id String object that represents and holds the device/object or contact's data table id.
* @param snos_ID String object that represents and holds the client's SNOS Number(also known as client identification number).
* @param Statusmsg String object that represents and holds the json status message sent by the web service.
* @param IsNodataConnected Boolean variable that represents and checks whether or not there is data/internet connection on the client's phone.
* @param IsNextTransitioned Boolean variable that represents and checks whether or not the client succeeded in clicking and viewing NEXT sms alerts.
* @param IslocalStorageretrieved Boolean variable that represents and checks whether or not there is a successful retrieval of data locally. 
* @param IsUserFirstTime Boolean variable that represents and checks whether or not the client is signing into this application for the first time.
* @param IsSearchCalled Boolean variable that represents and checks whether or not the client has carried out a search.
* @param sqldb SqliteDB object that represents an instance of class SqliteDB used to call the class methods.
* @param alert_text String array  object that represents and stores sms alerts texts.
* @param alert_date String array  object that represents and stores sms alerts dates.
* @param userProfileData String array  object that represents and stores the client's personal data.
* @param DeviceProfileData String array  object that represents and stores the client's object/device data
* @param ContactsProfileData String array  object that represents and stores the client's contacts data.
* @author Afam;
* @see StateMachine.java,ListMVC.java,SqliteDB.java
* @version 1.0 
*/
public class JSONConnectionObject 
{
    String url,content_type;
    //private volatile String WebServiceReturnedResult="";
    volatile ConnectionRequest request=null;
    NetworkManager ntwrkmanager;
    Hashtable hashtableObj,hs1;
    String status="0";
    StateMachine sm;
    String lga[]=null;
    ListMVC lstmvc;
    boolean IsEmailRegistered,IssmsStoredlocally=false;
    private int sToredAlertsLimits=0;
    public static int device_contactNumberCounter,total_current_sms_DiFF,RetrievedRowSize=0;
    static String SMSTotalCount,MinimumBound,Min,SMSCurrentCount,user,pass,table_id,snos_ID,Statusmsg="";
    public static boolean IsNodataConnected,IsUserSessionStillOn, IsNextTransitioned,IslocalStorageretrieved,IsUserFirstTime,IsSearchCalled=false;
    public  SqliteDB sqldb;
    String []alert_text=new String[10];
    String []alert_date=new String[10];
    public String[]userProfileData, DeviceProfileData,ContactsProfileData;
    /**
    * This one argument constructor is used for needed start-up initializations like url, obtaining network manager object etc.
    * @param url String object that represents the web services remote url.
    */
    public JSONConnectionObject(String url)
    {
        //sets the url
        this.url=url;
        //sets the request content type
        content_type="application/json";
        //gets an instance of the sqlite db
        sqldb=new SqliteDB();
        //get the object of StateMachine class.
        sm=new StateMachine();
        //get an instance of network manager
        ntwrkmanager=NetworkManager.getInstance();
        /*
        //Add a Listener for possible error in remote connection
        ntwrkmanager.addErrorListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt)
            {
                //Since it can't connect to internet, then there must be no data connection in the client's phone.
                IsNodataConnected=true;
                if(!sqldb.IsSMSStoredLocally())
                {
                    //Dialog.show("No Data Connection", "YOU need to have internet/data connection\n in your mobile phone before you can download your data.\nThank you.", "Ok",null);
                    //go back to re-login
                    //sm.NavigateBackToLoginForm();
                }
                //Did the client click Login button?
                else if(StateMachine.WebServiceReturnedResult.equalsIgnoreCase("logIn"))
                {
                    Log.p("GET READY TO VIEW YOUR LOCAL ALERTS SINCE THERE IS NO DATA CONNECTION");
                    LogInClientLocally();
                    StateMachine.WebServiceReturnedResult="fo";
                }
               //evt.consume();//prevents further processing 
            }//ends the action performed method.
        });//ends the action anonymous inner class.
        */
    }//ends the constructor.
    /**
     * This allows a client to successfully login accessing the information stored at his/her local phone
     */
    public void LogInClientLocally()
    {
        //now sets that local retrieval has not been done.
        IslocalStorageretrieved=false;
        //sets this to null so that you can re-login again after logging out
        StateMachine.setListMVCObj(null);
         //Is the client's login detail not found in the phone memory?
         if(Storage.getInstance().readObject("LoginDetails")==null)
         {
             //Then display appropriate message
             Dialog.show("No Personal Data Found", "Please ensure that you have registered ,and thus downloaded and configured this app for personal use.\nThank you.", "Ok",null);
         }
         else
         {
             //Is sms stored locally but has not yet been retrieved
             if(sqldb.IsSMSStoredLocally() && !IslocalStorageretrieved)
             {
                 //Does the client's login detail equal the ones entered in the lofin form.
                 if(sm.userlogin_name().equals(user) && sm.userlogin_pass().equals(pass))
                 {
                    //Is this app retrieving-in this app running session- alert locally for the 1st time? 
                    if(!IsUserSessionStillOn)
                    {
                        //state that log in was successfull.
                        StateMachine.IsLoggedIn=true;
                    }
                     StateMachine.IsSignedIn=true;
                     //IslocalStorageretrieved=false;
                     IsUserSessionStillOn=true;
                     //retrieve and display the client's sms alerts.
                     
                     getAndDisplayLocalSMS(0,10,"");
                     Log.p("Here I am ooh!!!");
                     //Display appropriate message
                     ListMVC.status.setText("You have no data or internet connection!");
                     
                 }
                 else
                 {
                     //display the error message
                     Dialog.show("Unknown User", "Please ensure that you have entered correctly your login credentials\n before you can use this application.\nThank you.", "Ok",null);
                 }
             }
         }//ends the first else statement.
    }
    /**
    * This method locally retrieves and displays sms alerts.
    * @param min int variable that represents the minimum number range to retrieve from.
    * @param max int variable that represents the maximum number range to retrieve.
    * @param type String object that represents whether this method is being called first time(""), under next button("next") or previous button("prev")
    */
    public void getAndDisplayLocalSMS(int min, int max,String type)
    {
        //Is there any locally stored alert?
        if(sqldb.IsSMSStoredLocally())
        {
            //Initialize both current and total sms counts for any 
            //initial access like the greater(>) comparison immediately below.
            SMSTotalCount=Integer.toString(sqldb.getLocalAlertsNumber());
            //Is this method being called the first time?
            if(min==0 &&type.equalsIgnoreCase(""))
            {
                //then makes the current count to be 0
                SMSCurrentCount=Integer.toString(0);
            }
            //Has the current count exceeded the total count in the process of clicking "NEXT" sms button.
            if(Integer.parseInt(JSONConnectionObject.SMSCurrentCount)>Integer.parseInt(JSONConnectionObject.SMSTotalCount) && type.equalsIgnoreCase("next"))
            {
                //them make the minimum number range to be total count minus diferent between total and current count
                min=Integer.parseInt(JSONConnectionObject.SMSTotalCount)-total_current_sms_DiFF;
            }
            //Has the sms list form not yet been displayed or instantiated?
            if(StateMachine.getListMVCObj()==null)
            {
                //then retrieve the needed alerts from phone db or memory.
                sqldb.getSmsAlertsStoredInfor(min,max);
                //store the retrived alerts in a List object inside ListMVC class.
                ListMVC.SECURITY_ALERTS_DATA=sqldb.getStoredSmsAlerts();
                //then get a new instance of list mvc class.
                lstmvc=new ListMVC(ListMVC.SECURITY_ALERTS_DATA,sm.getAlertForm());
                //sets the incremental min to be accessed and incremented during "NEXT" or "PREVIOUS" sms buttons clicks.
                Min=Integer.toString(min);
                //now sets that local retrieval has been done.
                IslocalStorageretrieved=true;
                //stores the ListMVC object to be re-used later.
                StateMachine.setListMVCObj(lstmvc);
            }
            else
            {
                //then access the already stored one.
                lstmvc=StateMachine.getListMVCObj();
                System.out.println("The min before qUery iSSSS:"+min);
                //then retrieve the needed alerts from phone db or memory.
                sqldb.getSmsAlertsStoredInfor(min,max);
                //store the retrived alerts in a List object inside ListMVC class.
                ListMVC.SECURITY_ALERTS_DATA=sqldb.getStoredSmsAlerts();
                
            }
            //did the client click "NEXT" sms
            if(type.equalsIgnoreCase("next"))
            {
                //gets and increment sms current count by 10.
                int smscurr=Integer.parseInt(JSONConnectionObject.SMSCurrentCount)+10;
                SMSCurrentCount=Integer.toString(smscurr);
                //gets the MinimumBound by decrementing sms current count by 9.
                MinimumBound=Integer.toString(smscurr-9);
                //gets the mimimum range passed as an aregument.
                Min=Integer.toString(min);
                //states that "NEXT" has been clicked.
                IsNextTransitioned=true;
                //Is current count now greater than total count?
                if(Integer.parseInt(JSONConnectionObject.SMSCurrentCount)>Integer.parseInt(JSONConnectionObject.SMSTotalCount))
                {
                    //increment the minimum bound range by 10
                    Min=Integer.toString(min+10);
                }
            }
            else if(type.equals("prev"))
            {
                //states that "NEXT" has not been clicked.
                IsNextTransitioned=false;
                int minrange=(Integer.parseInt(JSONConnectionObject.SMSCurrentCount));
                //gets the minimum range  by incrementing sms current count by 10.
                Min=Integer.toString(min+10);
                //if the minimum range is 10
                if(Integer.parseInt(Min)==10)
                {
                    //then Min -at prev action method- must have been zero-after subtracting 20 from it- before arriving here.
                    Min=Integer.toString(0);
                }
                //decrements the current count by 10
                SMSCurrentCount=Integer.toString(minrange-10);
                //gets the MinimumBound by decrementing sms current count by 19.
                MinimumBound=Integer.toString(minrange-19);
            }
            else
            {
                //sets current count to be 10
                SMSCurrentCount=Integer.toString(10);
                //sets minimum bound to be 1.
                MinimumBound=Integer.toString(1);
            }
            //states to the whole application that login was a success.
            //StateMachine.IsLoggedIn=true;
            //shuts down the network manager obtained at the constructor.
            ntwrkmanager.shutdown();
            //restart again so that the same ConnectionRequest object can be re-used again;
            ntwrkmanager.start();
            Log.p("WHAT !!!!!!!!!!!!!!!!!!!!!!!!");
            lstmvc.buildAll();
        }
    }//ends the method
    /**
     * This method simply processes client's credentials and allows him/her to login-via remote web service.
     * @param username String object that represents the client's username.
     * @param password String object that represents the client's password.
     * @param formobj Form object that represents the client's LoginForm to be directed to on successful login.
     */
    public void ClientLogin(String username,  String password)
    {
        //store these paramters for later references-when the client wants to download his/her alerts.
        user= username;
        pass= password;
        //get a new instance of SqliteDB.
        sqldb=new SqliteDB();
        //then download and configure all his alerts,and other data
        try
         {
            //get a connection request object and define it as an anonymous inner class.
            request =  new ConnectionRequest()
            {
                //this method executes when a response is read and detected
                //The InputStream Object represents the response retrned as an InputStream object.
                protected void readResponse(InputStream input)
                {
                    System.out.println("Hay, my response read is coming here" );
                    //check for null values and display the appropriate message
                    if(input==null)
                    {
                        Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
                    }
                    try
                    {
                        //Get a json parser object
                        JSONParser p = new JSONParser();
                        //parse the input stream and return it as a Hashtable object
                        hashtableObj = p.parse(new InputStreamReader(input));
                        Log.p("The Login Hastable result="+hashtableObj);
                        //gets the status of the json message/output
                        status=(String)hashtableObj.get("status");
                    }
                    catch (Exception ex)
                    {
                        Dialog.show("No Alerts", "We are sorry to inform you that this app can't contact the remote server\n.Please try again or contact the administrator\n", "Ok", null);
                    }
                }//ends the readRespnse method
                //Handle Any Internet Connection Problem.
                @Override
                protected void handleException(Exception err)
                {
                    //do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("The handleExeption stack trace message=");
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    //Since it can't connect to internet, then there must be no data connection in the client's phone.
                    IsNodataConnected=true;
                    if(!sqldb.IsSMSStoredLocally() ||!StateMachine.WebServiceReturnedResult.equalsIgnoreCase("logIn"))
                    {
                        //then display error message since registration process is about  going on when there is no internet connection
                        if(Dialog.show("Connection Error!!", "Are you connected to the internet? Check your connection", "Exit", "Retry"))
                        {
                           
                        }
                        else
                        {
                            retry();
                        }
                    }
                    //Did the client click Login button?
                    else
                    {
                        Log.p("GET READY TO VIEW YOUR LOCAL ALERTS SINCE THERE IS NO DATA CONNECTION");
                        LogInClientLocally();
                        //StateMachine.WebServiceReturnedResult="fo";
                    }
                    //shuts down and restart the app.
                    //Display.getInstance().exitApplication();
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                }
                @Override
                // This method is invoked on the EDT after processing is complete to allow the networking code
                // to update the UI
                protected void postResponse()
                {
                    //ensure that 'status' is not null
                    if(status==null)
                    {
                        Dialog.show("No Data or/and Connection", "Problem connecting with the Server\nDo you want to try again?", "Yes","No");    
                    }
                    //status 1=success
                    else if(status.equals("1"))
                    {
                        //retrieves the alert inbox
                        Vector v = (Vector)hashtableObj.get("inbox");
                        //checks for empty inbox
                        if(v.isEmpty())
                        {
                            Dialog.show("No Alerts","Sorry there are no security alerts found for you.\nThank you.","Ok",null);
                            return;
                        }
                        else
                        {
                            // get the snostype
                            snos_ID=(String)Storage.getInstance().readObject("snostype");
                            //since this the first time of retrieving alerts, makes the minimum bound to be 1
                            MinimumBound="1";
                            //states to the whole application that login was a success.
                            StateMachine.IsLoggedIn=true;
                            //Is sms stored locally?
                            if(sqldb.IsSMSStoredLocally())
                            {
                                //get the alerts limit 
                                sToredAlertsLimits=Integer.parseInt(Storage.getInstance().readObject("alerts_limit").toString());
                                Log.p("Info " + "Local Alert number is:"+sqldb.getLocalAlertsNumber());
                                //Is the total number of stored alert greater or equal to the giving stored alerts limit.
                                if(sqldb.getLocalAlertsNumber()>=sToredAlertsLimits)
                                {
                                    //alerts number has reached or exceeded the maximum number of alerts-that can be stored-locally.
                                    //Then drop and delete  all the stored old alerts to re-store fresh ones.
                                    if(sqldb.DropSqliteSMSAlertsTable())
                                    {
                                        Log.p("Info " +"ALL LOCAL ALERTS DELETED SUCCESSFULLY");
                                        //create the needed tables
                                        sqldb.CreateSqliteSMSAlertsTableOnly();
                                    }
                                    //display appropriate message
                                    Dialog.show("Maximun Alerts Reached", "We are sorry to inform you that you have reached the maximum number of alerts that can be stored in your mobile device\n and thus we are going to delete the old ones and then download new alerts for you.\nThank you.", "Ok",null);
                                    Log.p("Info " +"THE USER SECOND TIME");
                                    Log.p("Info " +"ALERT NUMBER has reached or exceeded the maximum number of alerts-that can be stored-locally.");
                                    Log.p("Status: " +"Start The Download Process neW alerts After Deletion of the old ones : ");
                                    //download and store fresh/new alerts.
                                    DownloadAllClientsData_ORUpdateSecurityAlerts(false);
                                }
                                else
                                {
                                    Log.p("RESPONSE CODE=="+request.getResponseCode());
                                    //it has not reached the maximum alerts number that be stored locally
                                    Log.p("Info: " +"ALERT NUMBER has not reached the maximum alerts number that be stored locally");
                                    Log.p("Info: " +"THE USER local Alerts number is : "+sqldb.getLocalAlertsNumber());
                                    WebServiceReturnedResult="loggedIn";
                                    Log.p("The WebServiceReturnedResult=="+WebServiceReturnedResult);
                                    //Tells this app that there is no internet connection at the moment
                                    IsNodataConnected=false;
                                    //then download the NEWEST alerts since the last update attempt.
                                    //DownloadAllClientsData_ORUpdateSecurityAlerts(formobj,true);
                                }
                            }
                            //Then this must be the user first time of using this app.
                            else
                            {    
                                //these state that the client is using this app for the first time. since no alerts where found stored locally.
                                //downloads the clients personal, device/object as well as contacts info as well and save them in the db.
                                Dialog.show("Personal Data Download and Configuration","Welcome, Please wait why this application downloads ALL your security alerts, personal,device or objects and contacts biodata...\nThank you","Ok",null);
                                //downloads all the clients alerts,personal biodata, device and contacts data for the first time.
                                DownloadAllClientsData_ORUpdateSecurityAlerts(false);
                            }
                        }//ends else statement
                    }//ends status:1 processings
                    //this-status:3- stands for error
                    else if(status.equals("3"))
                    {
                        Statusmsg=(String)hashtableObj.get("msg");
                        
                        Dialog.show("Unknown User", "Unknown UserName or/and Password\nPlease are you sure that you have registered for this service?", "Ok",null);
                    }
                    //this-status:0- stands for nothing found or was found.
                    else if(status.equals("0"))
                    {
                        Dialog.show("No Alerts", "Sorry there are no security alerts found for you", "Ok",null);
                    }
                    //this-status:3f- stands for the fact that the SNOS number(client identification id) has been regustered/comfigured for another mobile device.
                    else if(status.equals("3f"))
                    {
                        Dialog.show("Invalid Client Id", "We are sorry to inform you that this client id has been configured and registered \nin another mobile device for another client.\nPlease do ensure that the login details belong to you.", "Ok",null);
                    }
                    //this-status:3a- means that: 1st stage of registration has been done.
                    else if(status.equals("3a"))
                    {
                        Statusmsg=(String)hashtableObj.get("msg");
                        Dialog.show("Continuation", Statusmsg, "Ok",null);
                        Log.p("Device reg Is edt="+Display.getInstance().isEdt());
                        sm.NavigateToForm("Device_Form");
                        Statusmsg="Please Kindly Complete Your Registration from Object Registration";
                        snos_ID=(String)hashtableObj.get("snos_type");
                        //"form2" means: redirect to 2nd stage form
                        WebServiceReturnedResult="form2";
                    }
                    //this-status:3a- means that:2nd stage of registration has been done.
                    else if(status.equals("3b"))
                    {
                        Statusmsg=(String)hashtableObj.get("msg");
                        Dialog.show("Continuation", Statusmsg, "Ok",null);
                        Statusmsg="Please Kindly Complete Your Registration from Contacts Registration";
                        sm.NavigateToForm("Contacts_Form");
                        snos_ID=(String)hashtableObj.get("snos_type");
                        //"form3" means: redirect to 3rd stage form
                        WebServiceReturnedResult="form3";
                    }
                    //this-status:3c- means that: 3rd stage of registration has been done.
                    else if(status.equals("3c"))
                    {
                        Statusmsg=(String)hashtableObj.get("msg");
                        snos_ID=(String)hashtableObj.get("snos_type");
                        Dialog.show("Registration Almost Completed", Statusmsg, "Ok",null);
                        sm.NavigateBackToLoginForm();
                        //"form3" means: redirect to 3rd stage form
                        WebServiceReturnedResult="form3";
                    }
                    //this-status:2- stands for: no sms alerts found
                    else if(status.equals("2"))
                    {
                        //No Alerts found
                        Dialog.show("No Alerts","Sorry there are no security alerts found for you.\nThank you.","Ok",null);
                        //prepare to display the error message.
                        String [][]all_inbox=new String[10][4];
                        Double smscount=(Double)hashtableObj.get("smscount");
                        SMSCurrentCount=Double.toString(smscount);
                        Double smstotcount=(Double)hashtableObj.get("smstotalcount");
                        SMSTotalCount=Double.toString(smstotcount);
                        SMSCurrentCount=SMSCurrentCount.substring(0, SMSCurrentCount.length()-2);
                        SMSTotalCount=SMSTotalCount.substring(0, SMSTotalCount.length()-2);
                        lstmvc=new ListMVC(all_inbox,sm.getAlertForm());
                        StateMachine.setListMVCObj(lstmvc);
                        ListMVC.SECURITY_ALERTS_DATA=all_inbox;
                        lstmvc.buildAll();
                    }
    
                }//end of post response method
            };//ends the anonymous inner class method
            //sets the necessary request parameters
            request.setUrl(url);
            request.setPost(false);
            request.setContentType(content_type);
            request.addRequestHeader("cache", "false");
            //supports duplicate entry or same url entry-for repeated re-logins.
            request.setDuplicateSupported(true);
            //this tells the servlet web service to execute a "Login" block of statement
            request.addArgument("action", "Login"); 
            //this tells the servlet web service that this request is from a mobile phone.
            request.addArgument("source", "mobile");
            //this tells the servlet web service that this request is from a native SNOS app installed on a mobile phone.
            request.addArgument("mnative", "yes");
            //sets both username and passwords
            request.addArgument("userid", username);
            request.addArgument("password", password);
            //authenticates the login parameters
            if(username.equalsIgnoreCase("") || password.equalsIgnoreCase(""))
            {
                Dialog.show("Invalid Input", "Unknown UserName or/and Password is/are empty", "Ok",null);
            }
            else
            {
                //gets progress object.
                InfiniteProgress ip = new InfiniteProgress();
                Dialog dlg = ip.showInifiniteBlocking();
                //Tells the EDT to dispose the progress bar on request completion.
                //dlg.show();
                request.setDisposeOnCompletion(dlg);
                //dlg =request.getDisposeOnCompletion();
                //sets the log reporting and crash info
                Log.setReportingLevel(Log.REPORTING_DEBUG);
                DefaultCrashReporter.init(true, 2);
                //finally make the request connection by adding it to a queue.
                ntwrkmanager.addToQueueAndWait(request);
                StateMachine.setNetworkManagerObj(ntwrkmanager);
                Log.p("RESPONSE CODE=="+request.getResponseCode());
                System.out.println("Time out duration="+ ntwrkmanager.getTimeout());

            }
        }
        catch (Exception ex) 
        {
            System.out.println("The StackTrace is printed below:");
            Log.p("Error: " +"The StackTrace is printed below:");
            ex.printStackTrace();
            Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
        }
    
    }
    /**
    * This method simply DOWNLOADS client's alerts(old and new ones),bio-data, device and contacts data OR UPDATES his/her alerts.
    * @param Isalertupdate Boolean variable that represents and checks whether the client wants to download and update newest alerts.
    * @param formobj Form object that represents the client's LoginForm to be directed to on successful login.
    */
    public void DownloadAllClientsData_ORUpdateSecurityAlerts(final boolean Isalertupdate)
    {
        try
        {
            //get a connection request object and define it as an anonymous inner class.
            request =  new ConnectionRequest()
            {
                //this method executes when a response is read and detected
                //The InputStream Object represents the response retrned as an InputStream object.
                protected void readResponse(InputStream input)
                {
                    System.out.println("Hay, my response read is coming here" );
                    //check for null values and display the appropriate message
                    if(input==null)
                    {
                        Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
                    }
                    try
                    {
                        //Get a json parser object
                        JSONParser p = new JSONParser();
                        //parse the input stream and return it as a Hashtable object
                        hashtableObj = p.parse(new InputStreamReader(input));
                        System.out.println("hashtable result is" +hashtableObj);
                        //gets the status of the json message/output
                        status=(String)hashtableObj.get("status");
                    }
                    catch (IOException ex)
                    {
                        
                    }
                }
                //Handle Any Internet Connection Problem.
                @Override
                protected void handleException(Exception err)
                {
                    // do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                    //Get the current network manager and shuts it down so that
                    //it can be re-used to make another request
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                }
                @Override
                // This is invoked on the EDT after processing is complete to allow the networking code
                // to update the UI
                protected void postResponse()
                {
                    if(status==null)
                    {
                        Dialog.show("No Data or/and Connection", "Problem connecting with the Server\nDo you want to try again?", "Yes","No");    
                    }
                    else if(status.equals("1"))
                    {
                        Vector deviceVectorArray,contactsVectorArray,alertsVectorArray;
                        hs1=hashtableObj;
                        //get the sms inbox array
                        alertsVectorArray = (Vector)hs1.get("inbox");
                        //get the object or device array
                        deviceVectorArray = (Vector)hs1.get("deviceinbox");
                        if(alertsVectorArray==null)
                        {
                            Dialog.show("No Alerts","Sorry there are no security alerts found for you","Ok",null);
                            return;
                        }
                        else
                        {
                            int InboxCount=0;
                            //get the smscount and total sms count.
                            Double smstotcount=(Double)hashtableObj.get("smstotalcount");
                            SMSTotalCount=Double.toString(smstotcount);
                            //since it is in double format,removes the the decimal point and zero following the integer.
                            SMSTotalCount=SMSTotalCount.substring(0, SMSTotalCount.length()-2);
                            System.out.println(" Downloaded Alerts SMSTotalCount is " +SMSTotalCount);
                            String [][]all_inbox=new String[Integer.parseInt(SMSTotalCount)][4];
                            alert_text=new String[Integer.parseInt(SMSTotalCount)];
                            alert_date=new String[Integer.parseInt(SMSTotalCount)];
                            for(Object currentObj : alertsVectorArray)
                            {
                                Hashtable current = (Hashtable)currentObj;
                                String []inbox=new String[4];
                                //get the alert text and the correponding date.
                                String sms = (String)current.get("sms");
                                String date1 = (String)current.get("date");
                                snos_ID=    (String)current.get("id");
                                Storage.getInstance().writeObject("snostype", snos_ID);
                                //assign them to an array of string
                                inbox[0]="";
                                inbox[1]=sms;
                                inbox[2]=date1;
                                inbox[3]="Action";
                                //populate these arrays with corresponding sms text and dates
                                alert_text[InboxCount]=sms;
                                alert_date[InboxCount]=date1;
                                //Is this the first loop
                                if(InboxCount==0)
                                {
                                    //then store the latest time to be the last_upadte time in the phone internal memeory
                                    //this will be used refernced later for subsequesnt alerts download.
                                    Storage.getInstance().writeObject("lastUpdate_Time", date1);
                                    System.out.println("The LAST UPDATE TIME="+date1);
                                }
                                all_inbox[InboxCount]=inbox;
                                InboxCount++;
                            }
                            //get local db object
                            sqldb=new SqliteDB();
                            //Inserts and stores all the downloaded alerts locally. 
                            sqldb.AddSqliteSmSAlertsInfor(alert_text, alert_date);
                            //Retrieve the first 10 alerts to display for the client to view.
                            WebServiceReturnedResult="loggedIn";
                            Log.p("The WebServiceReturnedResult=="+WebServiceReturnedResult);
                            //getAndDisplayLocalSMS(0,10,"");
                            if(!Isalertupdate)
                            {
                                Dialog.show("Completion of Personal Data Download and Configuration","Congrats,  ALL your security alerts,personal,device or objects and contacts biodata have been completely DOWNLOADED\n and have thus been saved on your phone.\nThank you","Ok",null);
                                Log.p("Info: " +"THE USER's FIRST TIME-START DOWNLOADING INTO THIS MOBILE DEVICE FOR THE FIRST TIME");
                            }
                            //Tells this app that there is no internet connection at the moment
                            IsNodataConnected=false;
                            
                        }//ends the else statement
                        //get ready to retrieve and store the client personal biodata info.
                        String name=(String)hs1.get("client_name");
                        if(name!=null)
                        {
                            Log.p("Info: " +"Congrats there are biodata found and thus downloaded for you. \n");
                            //gets all the client's personal biodata
                            String namea=(String)hashtableObj.get("client_name");
                            String email1=(String)hashtableObj.get("client_email");
                            String fone=(String)hashtableObj.get("fone");
                            String contact=(String)hashtableObj.get("contact");
                            String state=(String)hashtableObj.get("state");
                            String lga=(String)hashtableObj.get("lga");
                            userProfileData=new String[6];
                            userProfileData[0] =namea;
                            userProfileData[1] =email1;
                            userProfileData[2] =fone;
                            userProfileData[3] =contact;
                            userProfileData[4] =state;
                            userProfileData[5] =lga;
                            //Save the client biodata to his/her mobile phone. 
                            sqldb.AddClientBiodataInfor(userProfileData);
                            StateMachine.WriteLoginDetails(user, pass);
                            Log.p("Info: " +"THE USER PROFILE locally SAVED-THIS IS YOUR FIRST TIME : "+userProfileData[0]);
                           
                        }
                        if(deviceVectorArray!=null)
                        {
                            Log.p("Info: " +"Device data found and thus downloaded for you");
                            Double deviceRowsize=(Double)hashtableObj.get("deviceRs");
                            String rowsize=Double.toString(deviceRowsize);
                            rowsize=rowsize.substring(0, rowsize.length()-2);
                            Storage.getInstance().writeObject("DeviceRowSize",rowsize );
                            RetrievedRowSize=Integer.parseInt(rowsize);
                            for(Object currentObj:deviceVectorArray)
                            {
                                Hashtable current=(Hashtable)currentObj;
                                String retvaluecounter=(String)current.get("counter_id"); 
                                device_contactNumberCounter=Integer.parseInt(retvaluecounter);
                                table_id=(String)current.get("table_id");
                                System.out.println("The counter value before incrementing="+device_contactNumberCounter);
                                System.out.println("The RetrievedRowSize value ="+RetrievedRowSize);
                                String namea=(String)current.get("name");
                                String build_type=(String)current.get("build_type");
                                String descrpt=(String)current.get("descrpt");
                                String loc=(String)current.get("loc");
                                String contact=(String)current.get("contact");
                                String state=(String)current.get("state");
                                String lga=(String)current.get("lga");
                                DeviceProfileData=new String[9];
                                DeviceProfileData[0] =namea;
                                DeviceProfileData[1] =build_type;
                                DeviceProfileData[2] =loc;
                                DeviceProfileData[3] =descrpt;
                                DeviceProfileData[4] =contact;
                                DeviceProfileData[5] =state;
                                DeviceProfileData[6] =lga;
                                DeviceProfileData[7] =retvaluecounter;
                                DeviceProfileData[8] =table_id;
                                //Save the client object/device biodata to his/her mobile phone.Since this is his/her first time of using this app. 
                                sqldb.AddClientDevice_ObjectInfor(DeviceProfileData);
                            }
                        }
                        else
                        {
                            Dialog.show("Download Error:Device Not Found","Your device data could not be found and thus could not be downloaded for you","Ok",null);
                        }
                        //get the array of all the contacts data
                        contactsVectorArray = (Vector)hs1.get("contactinbox");
                        if(contactsVectorArray!=null)
                        {
                            Log.p("Info: " +"Contacts data found and thus downloaded for you");
                            Double contactsRowsize=(Double)hashtableObj.get("contactRs");
                            String rowsize=Double.toString(contactsRowsize);
                            rowsize=rowsize.substring(0, rowsize.length()-2);
                            RetrievedRowSize=Integer.parseInt(rowsize);
                            Storage.getInstance().writeObject("ContactsRowSize",rowsize );
                            for(Object currentObj:contactsVectorArray)
                            {
                                Hashtable current=(Hashtable)currentObj;
                                String retvaluecounter=(String)current.get("counter_id"); 
                                device_contactNumberCounter=Integer.parseInt(retvaluecounter);
                                table_id=(String)current.get("table_id");
                                System.out.println("The counter value before incrementing="+device_contactNumberCounter);
                                System.out.println("The RetrievedRowSize value ="+RetrievedRowSize);
                                String namea=(String)current.get("name");
                                String relat_pos=(String)current.get("relat_pos");
                                String fone=(String)current.get("fone");
                                String email1=(String)current.get("client_email");
                                String contact=(String)current.get("contact");
                                ContactsProfileData=new String[7];
                                ContactsProfileData[0] =namea;
                                ContactsProfileData[1] =email1;
                                ContactsProfileData[2] =fone;
                                ContactsProfileData[3] =contact;
                                ContactsProfileData[4] =relat_pos;
                                ContactsProfileData[5] =retvaluecounter;
                                ContactsProfileData[6] =table_id;
                                //Save the client's contacts biodata to his/her mobile phone. 
                                sqldb.AddClientsContactsInfor(ContactsProfileData);
                            }
                           
                        }
                        else
                        {
                            Dialog.show("Download Error:Contacts Not Found","Contacts data not found for you and thus could not be downloaded","Ok",null);
                        }
                    }//ends status:1 block
                    //status 3:error/unknown username and password
                    else if(status.equals("3"))
                    {
                        Statusmsg=(String)hashtableObj.get("msg");
                        Dialog.show("Unknown User", "Unknown UserName or/and Password\nPlease are you sure that you have registered for this service?", "Ok",null);
                    }
                    //status 0:error/unknown output or no processed output from json
                    else if(status.equals("0"))
                    {
                        Dialog.show("No Personal Data for Download", "Sorry there are no personal data found and thus no download for you:Contact the Administrator", "Ok",null);
                    }
                    //shuts down the network manager object
                    ntwrkmanager.shutdown();
                }//end of post response metho
            };
            //sets the  needed request parameters and arguments.
            request.setUrl(url);
            request.setPost(false);
            request.setContentType(content_type);
            request.addRequestHeader("cache", "false");
            //Tells the servelet web service to execute "Login" action block
            request.addArgument("action", "Login");
            //does the client want to just update his/her alerts?
            if(Isalertupdate)
            {
                //then get the last update time stored at the last download attempt locally at th phone internal memory
                String lastUpdateTime=Storage.getInstance().readObject("lastUpdate_Time").toString();
                System.out.println("The LAST UPDATE READ TIME="+lastUpdateTime);
                //add it as an argument to be sent with request object
                request.addArgument("lastUpdate_Time", lastUpdateTime);
                //Tells the servelet web service to prepare to retrieve all the alerts since the last update attempt.
                request.addArgument("alertupdate", "yes");
            }
            //Tells the servelet web service that this comes from a mobile phone and not a desktop web browser.
            request.addArgument("source", "mobile");
            //Tells the servelet web service that this comes from a a native SNOS app sotred on a mobile phone and not a desktop web browser.
            request.addArgument("mnative", "yes");
            //Tells the servelet web service to retrieve all the clients perosnal, device and contacts data
            request.addArgument("download_all_client_personal_device_contacts_data", "alert_download");
            //pass the SNOS number
            request.addArgument("snos_type", snos_ID);
            //pass the login parameters stored when the ClientLogin was called.
            request.addArgument("userid", user);
            request.addArgument("password", pass);
            //pass the device id for tracking purposes
            if(Storage.getInstance().readObject("device_id")!=null)
            {
                request.addArgument("device_id","bb10");
                //gets a progress bar object
                InfiniteProgress ip = new InfiniteProgress();
                //gets a dalog object.
                Dialog dlg = ip.showInifiniteBlocking();
                //disposes the dialog on completion
                request.setDisposeOnCompletion(dlg);
                //dlg =request.getDisposeOnCompletion();
                //dlg.show();
                //sets the log reporting and crash alert
                Log.setReportingLevel(Log.REPORTING_DEBUG);
                DefaultCrashReporter.init(true, 2);
                //finally connects to the remote web service
                ntwrkmanager.addToQueueAndWait(request);
                //store the network manager object to be re-used later in the application.
                StateMachine.setNetworkManagerObj(ntwrkmanager);
        }
            else
            {
                Dialog.show("Device ID Not Found", "The download process will be aborted since we cannot find your device id.\nThank you.", "OK", null);
                sm.NavigateBackToLoginForm();
            }
        }
        catch (Exception ex)
        {
            Log.p("Error: The StackTrace is printed below:");
            ex.printStackTrace();
            Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
        }
    }
    /**
    * This method simply connects and informs the webs service to check and verify whether or not a client's token exists.
    * @param token String object that represents the client's token to be verified.
    */
    public void CheckIfClientTokenExists(String token)
    {
        try
        {
            //get a connection request object and define it as an anonymous inner class.
            request =  new ConnectionRequest()
            {
                //this method executes when a response is read and detected
                //The InputStream Object represents the response retrned as an InputStream object.
                protected void readResponse(InputStream input)
                {
                    System.out.println("Hay, my response read is coming here" );
                    //check for null values and display the appropriate message
                    if(input==null)
                    {
                        Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
                    }
                    try
                    {
                        //Get a json parser object
                        JSONParser p = new JSONParser();
                        //parse the input stream and return it as a Hashtable object
                        hashtableObj = p.parse(new InputStreamReader(input));
                        System.out.println("hashtable result is" +hashtableObj);
                        //gets the status of the json message/output
                        status=(String)hashtableObj.get("status");
                    }
                    catch (IOException ex)
                    {
                        
                    }
                }
                //Handle Any Internet Connection Problem.
                @Override
                protected void handleException(Exception err)
                {
                    // do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                    //Get the current network manager and shuts it down so that
                    //it can be re-used to make another request
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                }
                @Override
                // This is invoked on the EDT after processing is complete to allow the networking code
                // to update the UI
                protected void postResponse()
                {
                    if(status==null)
                    {
                        Dialog.show("No Data or/and Connection", "Problem connecting with the Server\nDo you want to try again?", "Yes","No");
                    }
                    //status 1 means success
                    else if(status.equals("1"))
                    {
                        String msg=(String)hashtableObj.get("msg");
                        Dialog.show("Action Successfull", msg, "Ok",null);
                        sm.NavigateToForm("Reset_LoginDetails");
                        //Inform the threaded run method in StateMachine class that token has been verified 
                        //so that it can carry out appropriate action or navigation.
                        WebServiceReturnedResult="tokenverified";
                    }
                    //status 3: means error after processing
                    else if(status.equals("3"))
                    {
                        String msg=(String)hashtableObj.get("msg");
                        Dialog.show("Unknown Token", msg, "Ok",null);
                    }
                    //status 3: means error occured before processing of input
                    else if(status.equals("0"))
                    {
                        Dialog.show("Unexpected Error", "Error Occured somewhere.\nPlease contact administrator", "Ok",null);
                    }
                    //shits down the network manager
                    ntwrkmanager.shutdown();
                }//end of post response method
            };//ends anonymous inner class for request object
            //sets the needed request object arguments and parameters
            request.setUrl(url);
            request.setPost(false);
            request.setContentType(content_type);
            request.addRequestHeader("cache", "false");
            //This tells the servlet web service to execute an action parameter block called 'Verify Token'
            request.addArgument("action", "Verify Token");
            //This tells the web service that this request is coming from mobile phone
            request.addArgument("source", "mobile");
            //This tells the web service that this request is coming from  a native SNOS app instslled on a mobile phone
            request.addArgument("mnative", "yes");
            //This passess the token to be verified.
            request.addArgument("token", token);
            request.addArgument("id", "verify_token");
            if(token.equalsIgnoreCase(""))
            {
                Dialog.show("Invalid Input", "Token Field cannot be empty", "Ok",null);
            }
            else
            {
                //gets the progres bar, shows it and disposes it on completion.
                InfiniteProgress ip = new InfiniteProgress();
                Dialog dlg = ip.showInifiniteBlocking();
                request.setDisposeOnCompletion(dlg);
                dlg =request.getDisposeOnCompletion();
                dlg.show();
                //configure the log reporting parameters.
                Log.setReportingLevel(Log.REPORTING_DEBUG);
                //reports any possible crash
                DefaultCrashReporter.init(true, 2);
                //Establish connection to the web service
                ntwrkmanager.addToQueue(request);
                //store the netwrk manager to be used later on.
                StateMachine.setNetworkManagerObj(ntwrkmanager);
            }
        }
        catch (Exception ex) 
        {
             Log.p("The StackTrace is printed below:");
             ex.printStackTrace();
             Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
        }
    }
    /**
    * This method simply connects and informs the webs service to generate a token for a client and sends it to his/her email address.
    * @param email String object that represents the client's email where the token is to be sent.
    */
    public void SendTokenEmail(String email)
    {
        try
        {
            //get a connection request object and define it as an anonymous inner class.
            request =  new ConnectionRequest()
            {
                //this method executes when a response is read and detected
                //The InputStream Object represents the response retrned as an InputStream object.
                protected void readResponse(InputStream input)
                {
                    System.out.println("Hay, my response read is coming here" );
                    //check for null values and display the appropriate message
                    if(input==null)
                    {
                        Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
                    }
                    try
                    {
                        //Get a json parser object
                        JSONParser p = new JSONParser();
                        //parse the input stream and return it as a Hashtable object
                        hashtableObj = p.parse(new InputStreamReader(input));
                        System.out.println("hashtable result is" +hashtableObj);
                        //gets the status of the json message/output
                        status=(String)hashtableObj.get("status");
                    }
                    catch (IOException ex)
                    {
                        
                    }
                }
                //Handle Any Internet Connection Problem.
                @Override
                protected void handleException(Exception err)
                {
                    // do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                    //Get the current network manager and shuts it down so that
                    //it can be re-used to make another request
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                }
                @Override
                // This is invoked on the EDT after processing is complete to allow the networking code
                // to update the UI
                protected void postResponse()
                {
                    if(status==null)
                    {
                        Dialog.show("No Data or/and Connection", "Problem connecting with the Server\nDo you want to try again?", "Yes","No");    
                    }
                    //status 1 means success
                    else if(status.equals("1"))
                    {
                        String msg=(String)hashtableObj.get("msg");
                        Dialog.show("Congrat", msg, "Ok",null);
                        //This tells the threaded run method inside StateMachine class that client's 
                        //token was successfully generated and sent to his/her email address.
                        WebServiceReturnedResult="tokenemailsent";
                    }
                    //status 3 means error after processing of the input
                    else if(status.equals("3"))
                    {
                        String msg=(String)hashtableObj.get("msg");
                        Dialog.show("Error Occured", msg, "Ok",null);
                    }
                    //status 0 means error before processing of the input
                    else if(status.equals("0"))
                    {
                        Dialog.show("Unknown User", "Error Occured somewhere.\nPlease contact administrator", "Ok",null);
                    }
                    //shuts down the network manager since post response processing is done.
                    ntwrkmanager.shutdown();
                }//end of post response method
            };
            //sets the needed argument for request object.
            request.setUrl(url);
            request.setPost(false);
            request.setContentType(content_type);
            request.addRequestHeader("cache", "false");
            //This tells the servelet web service to execute "Recover Password" action block.
            request.addArgument("action", "Recover Password");
            //This tells the web service that this request is coming from mobile phone.
            request.addArgument("source", "mobile");
            //This tells the web service that this request comes from native SNOS app installed on a mobile phone
            request.addArgument("mnative", "yes");
            //this pass the email address in question.
            request.addArgument("e-mail", email);
            request.addArgument("id", "recover_pass");
            if(email.equalsIgnoreCase("") )
            {
                Dialog.show("Invalid Input", "The Email Field cannot be empty", "Ok",null);
            }
            else
            {
                //gets progress bar and sets it to dispose its dialog on completion.
                InfiniteProgress ip = new InfiniteProgress();
                Dialog dlg = ip.showInifiniteBlocking();
                request.setDisposeOnCompletion(dlg);
                dlg =request.getDisposeOnCompletion();
                dlg.show();
                //reports the needed log and any crash
                Log.setReportingLevel(Log.REPORTING_DEBUG);
                DefaultCrashReporter.init(true, 2);
                //This establishes a connection to the remote web service.
                ntwrkmanager.addToQueue(request);
                //stores the network manager object to re-used later in this application
                StateMachine.setNetworkManagerObj(ntwrkmanager);
            }
        }
        catch (Exception ex) 
        {
            Log.p("Error:The StackTrace is printed below:");
            ex.printStackTrace();
            Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
        }
    }
    /**
    * This method simply connects and informs the webs service to create a new client's password.
    * @param email String object that represents the client's email.
    * @param newpass String object that represents the client's new password to be created .
    * @param verifynewpass String object that represents the client's confirmation copy of the new password.
    */
    public void CreateUserNewPassword(final String email,final String newpass, String verifynewpass)
    {
        try
        {
            //get a connection request object and define it as an anonymous inner class.
            request =  new ConnectionRequest()
            {
                //this method executes when a response is read and detected
                //The InputStream Object represents the response retrned as an InputStream object.
                protected void readResponse(InputStream input)
                {
                    System.out.println("Hay, my response read is coming here" );
                    //check for null values and display the appropriate message
                    if(input==null)
                    {
                        Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
                    }
                    try
                    {
                        //Get a json parser object
                        JSONParser p = new JSONParser();
                        //parse the input stream and return it as a Hashtable object
                        hashtableObj = p.parse(new InputStreamReader(input));
                        System.out.println("hashtable result is" +hashtableObj);
                        //gets the status of the json message/output
                        status=(String)hashtableObj.get("status");
                    }
                    catch (IOException ex)
                    {
                        
                    }
                }
                //Handle Any Internet Connection Problem.
                @Override
                protected void handleException(Exception err)
                {
                    // do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                    //Get the current network manager and shuts it down so that
                    //it can be re-used to make another request
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                }
                @Override
                // This is invoked on the EDT after processing is complete to allow the networking code
                // to update the UI
                protected void postResponse()
                {
                    if(status==null)
                    {
                        Dialog.show("No Data or/and Connection", "Problem connecting with the Server\nDo you want to try again?", "Yes","No");    
                    }
                    //status 1 means success
                    else if(status.equals("1"))
                    {
                        //Informs the threaded run method in StateMachine class that the client's new password has been created and reset successfully.
                        WebServiceReturnedResult="token_new_password_created";   
                        String msg=(String)hashtableObj.get("msg");
                        StateMachine.WriteLoginDetails(email,newpass);
                        Dialog.show("Congrats!!!", msg, "Ok",null);
                    }
                    //status 3 means error occured after attempting to create new password
                    else if(status.equals("3"))
                    {
                        String msg=(String)hashtableObj.get("msg");
                        Dialog.show("Unknown User", msg, "Ok",null);
                    }
                    else if(status.equals("0"))
                    {
                        Dialog.show("Unknown User", "Error Occured somewhere.\nPlease contact administrator", "Ok",null);
                    }
                    ntwrkmanager.shutdown();
                }//end of post response method
            };//ends request object anonymous inner class.
            //sets the needed argument for request object.
            request.setUrl(url);
            request.setPost(false);
            request.setContentType(content_type);
            request.addRequestHeader("cache", "false");
            //This tells the servelet web service to execute "Create Password" action block.
            request.addArgument("action", "Create Password");
            //This tells the web service that this request is coming from mobile phone.
            request.addArgument("source", "mobile");
            //This tells the web service that this request is coming from native SNOS app installed on a mobile phone
            request.addArgument("mnative", "yes");
            //pass the needed email, newpass and verifynewpass to the web service as request arguments/parameters.
            request.addArgument("e-mail", email);
            request.addArgument("npass", newpass);
            request.addArgument("vnpass", verifynewpass);
            request.addArgument("id", "create_pass");
            if(email.equalsIgnoreCase("") )
            {
                Dialog.show("Invalid Input", "The Email Field cannot be empty", "Ok",null);
            }
            else
            {
                //gets progress bar and sets it to dispose its dialog on completion.
                InfiniteProgress ip = new InfiniteProgress();
                Dialog dlg = ip.showInifiniteBlocking();
                request.setDisposeOnCompletion(dlg);
                dlg =request.getDisposeOnCompletion();
                dlg.show();
                //reports the needed log and any crash
                Log.setReportingLevel(Log.REPORTING_DEBUG);
                DefaultCrashReporter.init(true, 2);
                //This establishes a connection to the remote web service.
                ntwrkmanager.addToQueue(request);
                //stores the network manager object to re-used later in this application
                StateMachine.setNetworkManagerObj(ntwrkmanager);
            }
        }
        catch (Exception ex)
        {
            Log.p("Error:The StackTrace is printed below:");
            ex.printStackTrace();
            Dialog.show("No Data Connection", "Please ensure that you have internet/data connection\n in your mobile phone before you can use this app.", "Ok",null);
        }
    }
    /*
    *This statically returns the sms current count to be used any where in the application.
    */
    public static String getSMSCurrentCount()
    {
        return SMSCurrentCount;
    }
    /**
    * This method simply connects and informs the webs service to start a new client's registration process.
    * @param form_type String object that represents the client's registration form stage/type(form1=1st stage, form2=2nd stage and form3=third stage)
    * @param uig UserInforGet object that represents the client's "get" and "set" object needed to access registration's parameters.
    */
    public void ClientRegisteration(final String form_type, UserInforGet uig)
    {
        //starts an anonymous class for ConnectionRequest
        request =  new ConnectionRequest()
        {
            //this reads response from InputStream
            @Override
            protected void readResponse(InputStream input)
            {
                try
                {
                    //get parser object and parse the InputStream object as hastable object
                    JSONParser p = new JSONParser();
                    hashtableObj = p.parse(new InputStreamReader(input));
                    System.out.println("hashtable result is" +hashtableObj);
                    Log.p("hashtable result is" +hashtableObj);
                    status=(String)hashtableObj.get("status");
                    Statusmsg=(String)hashtableObj.get("msg");
                    Log.p("Status =" +status);
                }
                catch (IOException ex)
                {
                    Dialog.show("No Alerts", "Sorry there are no security alerts found for you\nDo you want to try again?", "Continue","Stop");
                }
            }
            //Handle Any Internet Connection Problem.
            @Override
            protected void handleException(Exception err)
            {
                String redirect_form="";
                if(form_type.equalsIgnoreCase("form1"))
                {
                    redirect_form="Reg_Form";
                }
                else if(form_type.equalsIgnoreCase("form2"))
                {
                    redirect_form="Device_Form";
                }
                else
                {
                    redirect_form="Contacts_Form";
                }
                // do something with err
                Log.p("The handleExeption block message="+err.getMessage());
                Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                //Get the current network manager and shuts it down so that
                //it can be re-used to make another request
                StateMachine.getNetworkManagerObj().shutdown();
                StateMachine.getNetworkManagerObj().start();
                sm.NavigateToForm(redirect_form);
            }
            @Override
            //invoked on the EDT after processing is complete to allow the networking code
            //to update the UI.
            protected void postResponse()
            {
                if(status==null)
                {  
                      Dialog.show("No Data or/and Connection", "Problem connecting with the Server", "Ok",null);        //exit();
                }
                //status 1: means success
                else if(status.equals("1"))
                {
                    Dialog.show("Server Message", Statusmsg, "Ok",null);
                    if(form_type.equalsIgnoreCase("form1"))
                    {
                        //go to device registration
                        sm.NavigateToForm("Device_Form");
                        WebServiceReturnedResult="form2";
                        Log.p("FORM TYPE= "+form_type);
                        StateMachine.WebServiceReturnedResult=WebServiceReturnedResult;
                        
                    }
                    else if(form_type.equalsIgnoreCase("form2"))
                    {
                        Log.p("FORM TYPE= "+form_type);
                        //go to contacts registration
                        sm.NavigateToForm("Contacts_Form");
                        WebServiceReturnedResult="form3";
                        StateMachine.WebServiceReturnedResult=WebServiceReturnedResult;
                    }
                    else if(form_type.equalsIgnoreCase("form3"))
                    {
                        //go to contacts registration
                        sm.NavigateToForm("Contacts_Form");
                        WebServiceReturnedResult="finished";
                        StateMachine.WebServiceReturnedResult=WebServiceReturnedResult;
                    }
                    else
                    {
                        Dialog.show("Alert", Statusmsg, "Ok",null);
                    }
                }
                //status 0: means error even before processing requests inputs.
                else if(status.equals("0"))
                {
                    Dialog.show("Uknown", "Are you sure you are supposed to use this app?", "Ok",null);
                    sm.NavigateBackToLoginForm();
                }
                //status 3: means error after processing request.
                else if(status.equals("3"))
                {
                    Dialog.show("Error Message from the Server", Statusmsg, "Ok",null);
                }
                //shuts down the network manager
                ntwrkmanager.shutdown();
            }//end of post response method
        };
        //sets needed request parameters
        request.setUrl(url);
        request.setPost(false);
        request.setContentType(content_type);
        request.addRequestHeader("cache", "false");
        //Is this the 1st stage of registration?
        if(form_type.equalsIgnoreCase("form1"))
        {
            //This tells the servelet web service to execute an action block called "Next >>" to the next stage.
            //whose id parameter is called:reg1
            request.addArgument("action", "Next >>");
            request.addArgument("id", "reg1");
            //gets and sets the form inputs
            request.addArgument("Sname", uig.getClientSname());
            request.addArgument("Oname", uig.getClientOname());
            request.addArgument("gsm",   uig.getFone());
            request.addArgument("email1",uig.getClientEmail());
            request.addArgument("address",uig.getContact());
            request.addArgument("state", uig.getState());
            request.addArgument("lga",  uig.getLga());
            request.addArgument("pass",  uig.getClientPassword());
            request.addArgument("vpass",  uig.getClientPassword());
        }
        //Is this the 2nd stage of registration?
        else if(form_type.equalsIgnoreCase("form2"))
        {
            //This tells the servelet web service to execute an action block called "Next >>" to the next stage.
            //whose id parameter is called:reg2
            request.addArgument("action", "Next >>");
            request.addArgument("id", "reg2");
            //sets the snos type of the client
            request.addArgument("snos_type", snos_ID);
            //gets and sets the form inputs
            request.addArgument("nam", uig.getClient());
            request.addArgument("description", uig.getDecrpt());
            request.addArgument("house_type",   uig.getBuild());
            request.addArgument("Location",uig.getLoc());
            request.addArgument("address",uig.getContact());
            request.addArgument("state", uig.getState());
            request.addArgument("lga",  uig.getLga());
            request.addArgument("extra_sensor",  uig.getSensor());
        }
        else
        {
            //This tells the servelet web service to execute an action block called "Submitt" to the next stage.
            //whose id parameter is called:reg3
            request.addArgument("action", "Submitt");
            request.addArgument("mnative", "yes");
            request.addArgument("id", "reg3");
            //sets the SNOS type of the client.
            request.addArgument("snos_type", snos_ID);
            //gets and sets the form inputs
            request.addArgument("nam", uig.getClient());
            request.addArgument("relation_pos", uig.getRelation_Position());
            request.addArgument("fone",   uig.getFone());
            request.addArgument("email1",uig.getClientEmail());
            request.addArgument("address",uig.getContact());
            request.addArgument("extra_contact",  uig.getSensor());
        }
        //Informs the servlet web service that the request comes from the mobile phone.
        request.addArgument("source", "mobile");
        //sets and configures the progress bar as well as error log and crash reporting mechanism.
        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        request.setDisposeOnCompletion(dlg);
        Log.setReportingLevel(Log.REPORTING_DEBUG);
        DefaultCrashReporter.init(true, 2);
        //Has the newtwork manager been started before
        if(StateMachine.getNetworkManagerObj()==null)
        {
            ntwrkmanager=NetworkManager.getInstance();
            StateMachine.setNetworkManagerObj(ntwrkmanager);
        }
        else
        {
            ntwrkmanager= StateMachine.getNetworkManagerObj();
        }
        //now makes a connection directly to the web se4vice.
        ntwrkmanager.addToQueue(request);
        System.out.println("The returned form type from method="+WebServiceReturnedResult);
    }
    /*
    * This methods returns the WebServiceReturnedResult object needed in StateMachine Class.
    */
    public String getFormType()
    {
        return WebServiceReturnedResult;
    }
    /*
    * This methods sets and stores the WebServiceReturnedResult object needed in StateMachine Class.
    */
    public void setFormType(String ft)
    {
        WebServiceReturnedResult=ft;
    }
    /*
    * This methods returns web service status message needed in StateMachine Class.
    */
    public String getStatusMessage()
    {
        return Statusmsg;
    }
    /**
    * This method simply connects and informs the webs service to retrieve all the local governments belonging to a state.
    * @param state String object that represents the client's state.
    */
    public void GetLGALists(String state,final String formtype)
    {
        //starts an anonymous class for ConnectionRequest
        request =  new ConnectionRequest()
        {
            //this reads response from InputStream
            @Override
            protected void readResponse(InputStream input)
            {
                System.out.println("Hay, my response read is coming here" );
                try
                {
                    //get parser object and parse the InputStream object as hastable object
                    JSONParser p = new JSONParser();
                    hashtableObj = p.parse(new InputStreamReader(input));
                    System.out.println("hashtable result is" +hashtableObj);
                    status=(String)hashtableObj.get("status");
                    Statusmsg=(String)hashtableObj.get("msg");
                }
                catch (IOException ex)
                {
                    Dialog.show("No Result", "Sorry there are no results found for you\nDo you want to try again?", "Continue","Stop");
                }
            }
            //Handle Any Internet Connection Problem.
            @Override
            protected void handleException(Exception err)
            {
                // do something with err
                Log.p("The handleExeption block message="+err.getMessage());
                Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                String redirect_form="";
                if(formtype.equals("form1"))
                {
                    redirect_form="Reg_Form";
                }
                else
                {
                    redirect_form="Device_Form";
                }
                Log.p("Remember the current redirect form=="+redirect_form);
                //Get the current network manager and shuts it down so that
                //it can be re-used to make another request
                StateMachine.getNetworkManagerObj().shutdown();
                StateMachine.getNetworkManagerObj().start();
                sm.NavigateToForm(redirect_form);
            }

            @Override
            //invoked on the EDT after processing is complete to allow the networking code
            //to update the UI.
            protected void postResponse()
            {
                if(status==null)
                {
                      Dialog.show("No Data or/and Connection", "Problem connecting with the Server", "Ok",null);        //exit();
                }
                //status 1: means success
                else if(status.equals("1"))
                {
                    //get the lga as a Vector
                    Vector v = (Vector)hashtableObj.get("nlga");
                    if(v==null)
                    {
                        Dialog.show("No Alerts", "Sorry there are no local government lists found for you\nDo you want to try again?", "Ok",null);
                        return;
                    }
                    else
                    {
                        lga=new String[v.size()];
                        for(int i=0;i<v.size();i++)
                        {
                            lga[i]=(String)v.get(i);       
                        }
                        StateMachine.SetLGAListModel(lga);
                    }
                }
                //status 2: means error before finishing the processing of the requests inputs.
                else if(status.equals("0"))
                {
                    Dialog.show("Server Communication Error", "Could not return any value from the server.", "Ok",null);
                }
                //status 3: means error after finishing the processing of the requests inputs.
                else if(status.equals("3"))
                {
                    Dialog.show("Unknown Parameter", Statusmsg, "Ok",null);
                }
                //shuts down and thus close the network manager.
                ntwrkmanager.shutdown();
            }//end of post response method
        };
        //sets the needed request parameters
        request.setUrl(url);
        request.setPost(false);
        request.setContentType(content_type);
        request.addRequestHeader("cache", "false");
        //Tells the servelt web service to execute an action block called 'ajax_List'
        request.addArgument("action", "ajax_list");
        //Tells the web service that the request comes from native mobile app stored in the client's phone.
        request.addArgument("source", "mobile");
        request.addArgument("mnative", "yes");
        //sets the state in question.
        request.addArgument("state", state);
        //sets and configures the progress bar as well as error log and crash reporting mechanism.
        InfiniteProgress ip = new InfiniteProgress();
        Dialog dlg = ip.showInifiniteBlocking();
        request.setDisposeOnCompletion(dlg);
        Log.setReportingLevel(Log.REPORTING_DEBUG);
        DefaultCrashReporter.init(true, 2);
        //Is networkmanger object null?
        if(StateMachine.getNetworkManagerObj()==null)
        {
            //gets a new instance
            ntwrkmanager=NetworkManager.getInstance();
            StateMachine.setNetworkManagerObj(ntwrkmanager);
        }
        else
        {
            //gets the old one stored.
            ntwrkmanager= StateMachine.getNetworkManagerObj();
        }
        //now make a connection directly ro the web service.
        ntwrkmanager.addToQueue(request);
    }//ends this method.
    /**
    * This method simply connects and informs the web service to check whether or not a particular client's email address has been registered.
    * @param email_or_gsm_or_deviceid String object that represents the client's email address.
    * @return Boolean representing true or false depending on whether the email is registered or not.
    */
    public boolean checkEmailAddress_OR_GSM_OR_Device_ID_BeforeRegistration(final String email_or_gsm_or_deviceid,final String type)
    {
        //starts an anonymous class for ConnectionRequest
        request =  new ConnectionRequest()
        {
            //this reads response from InputStream
            @Override
            protected void readResponse(InputStream input)
            {
                System.out.println("Hay, my response read is coming here" );
                try
                {
                    //get parser object and parse the InputStream object as hastable object
                    JSONParser p = new JSONParser();
                    hashtableObj = p.parse(new InputStreamReader(input));
                    System.out.println("hashtable result is" +hashtableObj);
                    status=(String)hashtableObj.get("status");
                    Statusmsg=(String)hashtableObj.get("msg");
                }
                catch (IOException ex)
                {
                    Dialog.show("No Result", "Sorry there are no results found for you\nDo you want to try again?", "Continue","Stop");
                }
            }
            //Handle Any Internet Connection Problem.
                @Override
                protected void handleException(Exception err)
                {
                    // do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                    //Get the current network manager and shuts it down so that
                    //it can be re-used to make another request
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                    //Is this request asking for authentication of client's device_push id
                    if(type.equalsIgnoreCase("device_id"))
                    {
                        //returns to splash form or home page
                        sm.NavigateToForm("Splash_Form");
                    }
                    else
                    {
                        //go to first stage of registration form
                        sm.NavigateToForm("Reg_Form");
                    }
                }
                @Override
                //invoked on the EDT after processing is complete to allow the networking code
                //to update the UI.
                protected void postResponse()
                {
                    if(status==null)
                    {
                          Dialog.show("No Data or/and Connection", "Problem connecting with the Server", "Ok",null);        //exit();
                    }
                    //status 1: means success
                    else if(status.equals("1"))
                    {
                        String name = (String)hashtableObj.get("name");
                        if(name==null)
                        {
                            Dialog.show("No Data or/and Connection", "Problem communicating with the Server", "Ok",null);
                            return;
                        }
                        //1:means email registered 0:means email not registered.
                        else if(name.equals("1"))
                        {
                            IsEmailRegistered=true;
                            if(type.equalsIgnoreCase("email"))
                            {
                                 //updates the status message
                                 StateMachine.status.setText("This "+email_or_gsm_or_deviceid+" has been registerd already.Type in another one.");
                                 //empties the email text field.
                                 StateMachine.emailtxtfield.setText("");
                            }
                           else if(type.equalsIgnoreCase("device_id"))
                           {
                               //Simply return true.
                               WebServiceReturnedResult="true";
                           }
                           else
                           {
                               //Updates the status message
                               StateMachine.status.setText("This "+email_or_gsm_or_deviceid+" has been registerd already.Type in another one.");
                               //empties the gsm text field.
                               StateMachine.gsmtxtfield.setText("");
                           }


                        }
                        else
                        {
                            if(type.equalsIgnoreCase("device_id"))
                            {
                                //Simply return true.
                                WebServiceReturnedResult="false";
                            }
                            else
                            {
                                IsEmailRegistered=false;
                                //updates the status message
                                StateMachine.status.setText("Welcome Dearest New Client");
                            }
                            
                            
                        }
                    }
                    //status 0: means error before processing all the request inputs.
                    else if(status.equals("0"))
                    {
                        Dialog.show("Server Communication Error", "Could not return any value from the server.", "Ok",null);
                    }
                    //status 3: means error after processing all the request inputs.
                    else if(status.equals("3"))
                    {
                        Dialog.show("Unknown Parameter", Statusmsg, "Ok",null);
                    }
                    //shits down and thus close the network manager object
                    ntwrkmanager.shutdown();
                }//end of post response method
            };
            //sets the  nedded request parameters
            request.setUrl(url);
            request.setPost(false);
            request.setContentType(content_type);
            request.addRequestHeader("cache", "false");
            //Tells the web service to execute an action block part called: ajax.
            request.addArgument("action", "ajax");
            //Informs the web service the table to search the info in.
            request.addArgument("table", "clients_temp_tab");
            //Tells the web service that this request comes from mobile phone
            request.addArgument("source", "mobile");
            if(type.equalsIgnoreCase("gsm"))
            {
                //discard the first digit of the gsm fone digits. 
                String fone=email_or_gsm_or_deviceid.substring(1, email_or_gsm_or_deviceid.length());
                //concatenate 234 to the rest so that the server side servlet will accept the format.
                fone="234"+fone;
                //sets the gsm number in question.
                request.addArgument("gsm", fone);
            }
            if(type.equalsIgnoreCase("device_id"))
            {
                //sets the device id in question.
                request.addArgument("device_id",email_or_gsm_or_deviceid);
            }
            else
            {
                //sets the email address in question.
                request.addArgument("user", email_or_gsm_or_deviceid);
            }
            //sets and configures the progress bar as well as error log and crash reporting mechanism.
            InfiniteProgress ip = new InfiniteProgress();
            Dialog dlg = ip.showInifiniteBlocking();
            request.setDisposeOnCompletion(dlg);
            Log.setReportingLevel(Log.REPORTING_DEBUG);
            DefaultCrashReporter.init(true, 2);
            //Is networkmanger object null?
            if(StateMachine.getNetworkManagerObj()==null)
            {
                ntwrkmanager=NetworkManager.getInstance();
                StateMachine.setNetworkManagerObj(ntwrkmanager);
            }
            else
            {
                ntwrkmanager= StateMachine.getNetworkManagerObj();
            }
            ntwrkmanager.addToQueueAndWait(request);
            return IsEmailRegistered;
    }
    /**
    * This method connects and informs the web service to retrieve or update client's personal bio-data.
    * @param view Boolean variable that represents whether or not the client wants to just retrieve the data or update already retrieved ones.
    * @param uig UserInforGet object that represents the get and set class needed for any update.
    */
    public void View_UpdateClientProfile(final boolean view, UserInforGet uig)
    {
        //does the client just want to view his/her biodata stored locally
         if(view)
         {
             //retrieves the biodata from the client's mobile device.
             userProfileData=sqldb.getClientsBiodataStoredInfor();
             Log.p("Info: " +"THE USER PROFILE NAME from sqlitedb : "+userProfileData[0]);
             Log.p("Info: " +"THE USER PROFILE EMAIL ADDRESS from sqlitedb : "+userProfileData[1]);
             //Tells the thread's run method in StateMachine, to navigate to client's profile page and display his/her profile
             WebServiceReturnedResult="myprofile";
             //sm.run_Process_WebServiceReturnedResult(WebServiceReturnedResult);
         }
         //then either downlaod the biodata for the first time or update an already existing one.
         else
         {
             //make http request in form of an anonymous class to remote host
             request =  new ConnectionRequest()
             {
                 @Override
                 protected void readResponse(InputStream input)
                 {
                     System.out.println("Hay, my response read is coming here" );
                     try
                     {
                         //get a json parser object to read from the response input stream
                         JSONParser p = new JSONParser();
                         //read from input stream and parse it.
                         hashtableObj = p.parse(new InputStreamReader(input));
                         System.out.println("hashtable result is" +hashtableObj);
                         //get the status of the result and message returned
                         status=(String)hashtableObj.get("status");
                         //get the message returned from the remote server
                         Statusmsg=(String)hashtableObj.get("msg");
                     }
                     catch (IOException ex) 
                     {
                         Dialog.show("No Data Found", "Sorry there are no retrieved biodata found for you\nDo you want to try again?", "Continue","Stop");
                     }
                 }//ends readresponse method
                 //Handle Any Internet Connection Problem.
                @Override
                protected void handleException(Exception err)
                {
                    // do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                    //Get the current network manager and shuts it down so that
                    //it can be re-used to make another request
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                }
                 //delare a Postresponse method for the anonymous class
                 @Override
                 //It is invoked on the EDT after processing is complete to allow the networking code
                 // to update the UI
                 protected void postResponse()
                 {
                     if(status==null)
                     {
                         Dialog.show("No Data or/and Connection", "Problem connecting with the Server\nDo you want to try again?", "Yes","No");
                     }
                     //status 1=success
                     else if(status.equals("1"))
                     {
                         Log.p("HERE I AM nOw!!!");
                         //get the client's name
                         String name=(String)hashtableObj.get("client_name");
                         if(name==null)
                         {
                             Dialog.show("No Data Found", "Sorry there are no retrieved biodata found for you\nDo you want to try again?", "Continue","Stop");
                             return;
                         }
                         else
                         {
                             if(!view)
                             {
                                 Dialog.show("Success", "Your Profile Data update was successfully carried out\n Thank you.", "Ok",null);
                             }
                             String namea=(String)hashtableObj.get("client_name");
                             String email1=(String)hashtableObj.get("client_email");
                             String fone=(String)hashtableObj.get("fone");
                             String contact=(String)hashtableObj.get("contact");
                             String state=(String)hashtableObj.get("state");
                             String lga=(String)hashtableObj.get("lga");
                             userProfileData=new String[6];
                             userProfileData[0] =namea;
                             userProfileData[1] =email1;
                             userProfileData[2] =fone;
                             userProfileData[3] =contact;
                             userProfileData[4] =state;
                             userProfileData[5] =lga;
                             sqldb=new SqliteDB();
                             
                             //Has the client just updated his/her remote data? then update thesame data locally to reflect 
                             if(namea!=null)
                             {
                                 //Update the client biodata in his/her mobile phone.
                                 sqldb.UpdateClientPersonalBiodata(userProfileData);
                                 //retrieve the biodata from the client's mobile device.
                                 userProfileData=sqldb.getClientsBiodataStoredInfor();
                                 Log.p("Info: " +"THE USER PROFILE NAME from sqlitedb : "+userProfileData[0]);
                                 Log.p("Info: " +"THE USER PROFILE EMAIL ADDRESS from sqlitedb : "+userProfileData[1]);
                                 //Tells the thread's run method in StateMachine, to navigate to client's profile page and display his/her profile
                                 Log.p("Is my profile update in EDT="+Display.getInstance().isEdt());
                                 WebServiceReturnedResult="myprofile";
                             }
                         }
                     }
                     //status 0=error/not found
                     else if(status.equals("0"))
                     {
                         Dialog.show("No Data Found", "Sorry there are no retrieved biodata found for you\nDo you want to try again?", "Yes","No");
                     }
                     //status 3=error
                     else if(status.equals("3"))
                     {
                         Dialog.show("Unknown Parameter", Statusmsg, "Ok",null);
                     }
                     //shuts down and thus close the network manager.
                     ntwrkmanager.shutdown();
                 }//end of post response method
             };//end of the anonymous class
             //set the request url
             request.setUrl(url);
             //turn off psot method
             request.setPost(false);
             //set the content type
             request.setContentType(content_type);
             request.addRequestHeader("cache", "false");
             //wants to view profile for the first time.I mean is this his/her first time of using this app?
             if(view && IsUserFirstTime)
             {
                 //action="my profile" means that that the servelet/web service should execute the "my profile" block of statement
                  request.addArgument("action", "my profile");
             }
             //Does the client want to update his already existing biodata?
             else if(!view && !IsUserFirstTime)
             {
                 //action="Update Profile" means that that the servelet/web service should execute the "Update Profile" block of statement
                 request.addArgument("action", "Update Profile");
                 //collect the relevant data from get and set class
                 request.addArgument("name", uig.getClient());
                 request.addArgument("email1",uig.getClientEmail());
                 request.addArgument("address",uig.getContact());
                 request.addArgument("state", uig.getState());
                 request.addArgument("lga",  uig.getLga());
             }
             //sets the SNOS number of the client.
             request.addArgument("snos_type", snos_ID);
             //tells the servlet that the request is coming from a mobile device.
             request.addArgument("source", "mobile");
             //sets up a progress or timer class
             InfiniteProgress ip = new InfiniteProgress();
             Dialog dlg = ip.showInifiniteBlocking();
             request.setDisposeOnCompletion(dlg);
             //reports possible crashings
             Log.setReportingLevel(Log.REPORTING_DEBUG);
             DefaultCrashReporter.init(true, 2);
             //gets an object of network manager
             ntwrkmanager=StateMachine.getNetworkManagerObj();
             //initiates the connection to remote server.
             ntwrkmanager.addToQueue(request);
         }//end the first else statement of this method.
    }
    /*
     * This method returns as an array of string the client personal profile data retrived from remote web serice
     */
    public String[] getMyProfileData()
    {
          //returns the user's biodata object.
          return userProfileData;
    }
    /**
    * This method connects retrieves and updates(locally and remotely) or informs the web service to update(locally and remotely) client's device/object bio-data.
    * @param view String object that represents whether or not the client wants to just retrieve the data or update already retrieved ones.
    * @param uig UserInforGet object that represents the get and set class needed for any update.
    */
    public void View_UpdateDeviceProfile(final String view, UserInforGet uig)
    {
        //does the client just wants to view his/her biodata stored locally
        if(view.equalsIgnoreCase("true"))
        {
            //retrieve the object/devicebiodata from the client's mobile device.
            //get the row size
            String rowSize=(String)Storage.getInstance().readObject("DeviceRowSize");
            device_contactNumberCounter=0;
            RetrievedRowSize=Integer.parseInt(rowSize);
            DeviceProfileData=sqldb.getClientsDevice_ObjectStoredInfor(device_contactNumberCounter);
            Log.p("Info: " +"THE Device PROFILE NAME from sqlitedb : "+DeviceProfileData[0]);
            Log.p("Info: " +"THE Device PROFILE build_type from sqlitedb : "+DeviceProfileData[1]);
            //Tells the thread's run method in StateMachine, to navigate to client's profile page and display his/her device profile
            WebServiceReturnedResult="deviceprofile";
        }
        //Does the view contain "Next" or "Previous"
        else if(view.equalsIgnoreCase("Next")||view.equalsIgnoreCase("Prev"))
        {
            System.out.println("The counter value after clicking NEXT or PREVIOUS="+device_contactNumberCounter);
            //retrieve the object/devicebiodata from the client's mobile device.
            //get the row size
            String rowSize=(String)Storage.getInstance().readObject("DeviceRowSize");
            RetrievedRowSize=Integer.parseInt(rowSize);
            DeviceProfileData=sqldb.getClientsDevice_ObjectStoredInfor(device_contactNumberCounter);
            Log.p("Info: " +"THE Device PROFILE NAME from sqlitedb : "+DeviceProfileData[0]);
            Log.p("Info: " +"THE Device PROFILE build_type from sqlitedb : "+DeviceProfileData[1]);
        }
        //then update-both remotely and locally- already retrieved device/object data.
        else
        {
            //get request object by creating an anonymous class for it.
            request =  new ConnectionRequest()
            {
                @Override
                protected void readResponse(InputStream input)
                {
                    System.out.println("Hay, my response read is coming here" );
                    try
                    {
                        //just read from the response input stream
                        JSONParser p = new JSONParser();
                        hashtableObj = p.parse(new InputStreamReader(input));
                        System.out.println("hashtable result is" +hashtableObj);
                        status=(String)hashtableObj.get("status");
                        Statusmsg=(String)hashtableObj.get("msg");
                    }
                    catch (IOException ex)
                    {
                      Dialog.show("No Data Found", "Sorry there are no device or contacts data found for you\nDo you want to try again?", "Continue","Stop");
                    }
                }
                @Override
                protected void handleException(Exception err)
                {
                    // do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                    //Get the current network manager and shuts it down so that
                    //it can be re-used to make another request
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                }
              //This method is invoked on the EDT after processing is complete to allow the networking code
              // to update the UI
              @Override
              protected void postResponse()
              {
                  if(status==null)
                  {
                      Dialog.show("No Data or/and Connection", "Problem connecting with the Server\nDo you want to try again?", "Yes","No");        //exit();
                  }
                  //status 1: means success
                  else if(status.equals("1"))
                  {
                      String name=(String)hashtableObj.get("name");
                      if(name==null)
                      {
                          Dialog.show("No Objects Data", "Sorry we could not retireve your object's data\nDo you want to try again?", "Ok",null);
                          return;
                      }
                      else
                      {
                          Double did=(Double)hashtableObj.get("did"); 
                          Double rs=(Double)hashtableObj.get("rs"); 
                          String retvaluecounter=Double.toString(did);
                          String rowsize=Double.toString(rs);
                          retvaluecounter=retvaluecounter.substring(0, retvaluecounter.length()-2);
                          device_contactNumberCounter=Integer.parseInt(retvaluecounter);
                          rowsize=rowsize.substring(0, rowsize.length()-2);
                          RetrievedRowSize=Integer.parseInt(rowsize);
                          table_id=(String)hashtableObj.get("id");
                          System.out.println("The counter value before incrementing="+device_contactNumberCounter);
                          System.out.println("The RetrievedRowSize value ="+RetrievedRowSize);
                          String namea=(String)hashtableObj.get("name");
                          String build_type=(String)hashtableObj.get("build_type");
                          String descrpt=(String)hashtableObj.get("descrpt");
                          String loc=(String)hashtableObj.get("loc");
                          String contact=(String)hashtableObj.get("contact");
                          String state=(String)hashtableObj.get("state");
                          String lga=(String)hashtableObj.get("lga");
                          DeviceProfileData=new String[7];
                          DeviceProfileData[0] =namea;
                          DeviceProfileData[1] =build_type;
                          DeviceProfileData[2] =loc;
                          DeviceProfileData[3] =descrpt;
                          DeviceProfileData[4] =contact;
                          DeviceProfileData[5] =state;
                          DeviceProfileData[6] =lga;
                          sqldb=new SqliteDB();
                          //Has the client successfully retrieved his/her remote data? then update thesame data locally to reflect 
                          if(namea!=null)
                          {
                              //Update the client object data in his/her mobile phone.
                              sqldb.UpdateClientDevice_ObjectInfor(DeviceProfileData,device_contactNumberCounter);
                              Dialog.show("Success", "Your object update was successfully carried out", "Ok",null);
                              //retrieve the object/devicebiodata from the client's mobile device.
                              DeviceProfileData=sqldb.getClientsDevice_ObjectStoredInfor(device_contactNumberCounter);
                              Log.p("Info: " +"THE Device PROFILE NAME from sqlitedb : "+DeviceProfileData[0]);
                              Log.p("Info: " +"THE Device PROFILE build_type from sqlitedb : "+DeviceProfileData[1]);
                              //Tells the thread's run method in StateMachine, to navigate to client's profile page and display his/her device profile
                              WebServiceReturnedResult="deviceprofile";
                          }
                      }
                  }
                  //status 0: means error before processing all the request inputs.
                  else if(status.equals("0"))
                  {
                      Dialog.show("No Objects found", "Sorry there are no objects or device data found for you\nDo you want to try again?", "Ok",null);
                  }
                  //status 3: means error after processing all the request inputs.
                  else if(status.equals("3"))
                  {
                      Dialog.show("Unknown Parameter", Statusmsg, "Ok",null);
                  }
                  //this closes the network manager object
                  ntwrkmanager.shutdown();
              }//end of post response method
            };//ends the anonymous inner class
            request.setUrl(url);
            request.setPost(false);
            request.setContentType(content_type);
            request.addRequestHeader("cache", "false");
            if(view.equalsIgnoreCase("false")&&!IsUserFirstTime)
            {
                //action="Update Device" means that that the servlet/web service should execute the "Update Device" block of statement.
                //get the client's device/objects' data
                DeviceProfileData=sqldb.getClientsDevice_ObjectStoredInfor(JSONConnectionObject.device_contactNumberCounter);
                table_id=DeviceProfileData[7];
                Log.p("Info: " +"THE device PROFILE table_id for Update= : "+table_id);
                request.addArgument("action", "Update Device");
                request.addArgument("uid", table_id);
                request.addArgument("did", Integer.toString(device_contactNumberCounter));
                request.addArgument("nam", uig.getClient());
                request.addArgument("description", uig.getDecrpt());
                request.addArgument("house_type",   uig.getBuild());
                request.addArgument("Location",uig.getLoc());
                request.addArgument("address",uig.getContact());
                request.addArgument("state", uig.getState());
                request.addArgument("lga",  uig.getLga());
                //sets the client's SNOS number
                request.addArgument("snos_type", snos_ID);
                //This tells the servlet web service that this request comes from mobile device.
                request.addArgument("source", "mobile");
                InfiniteProgress ip = new InfiniteProgress();
                Dialog dlg = ip.showInifiniteBlocking();
                request.setDisposeOnCompletion(dlg);
                Log.setReportingLevel(Log.REPORTING_DEBUG);
                DefaultCrashReporter.init(true, 2);
                ntwrkmanager=StateMachine.getNetworkManagerObj();
                ntwrkmanager.addToQueue(request);
            }
            
        }//end the first else statement of this method
    }//ends this method
    /*
    * This method gets the retrieved row size of device or contacts data
    */
    public static int getRetrievedRowSize()
    {
        return RetrievedRowSize;
    }
    /**
    * This method connects and informs the web service to change the user's password.
    * @param oldpass String object that represents the user's old password.
    * @param newpass String object that represents the user's new  password.
    * @param vnewpass String object that represents the user's re-typed new password.
    */
    public void ChangePassWord(String oldpass,final String newpass,String vnewpass)
    {
        request =  new ConnectionRequest()
            {
                @Override
                protected void readResponse(InputStream input)
                {
                    System.out.println("Hay, my response read is coming here" );
                    try
                    {
                        //just read from the response input stream
                        JSONParser p = new JSONParser();
                        hashtableObj = p.parse(new InputStreamReader(input));
                        System.out.println("hashtable result is" +hashtableObj);
                        status=(String)hashtableObj.get("status");
                        Statusmsg=(String)hashtableObj.get("msg");
                    }
                    catch (IOException ex)
                    {
                      Dialog.show("No Data Found", "Sorry there are no device or contacts data found for you\nDo you want to try again?", "Continue","Stop");
                    }
                }
                @Override
                protected void handleException(Exception err)
                {
                    // do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                    //Get the current network manager and shuts it down so that
                    //it can be re-used to make another request
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                }
              //This method is invoked on the EDT after processing is complete to allow the networking code
              // to update the UI
              @Override
              protected void postResponse()
              {
                  if(status==null)
                  {
                      Dialog.show("No Data or/and Connection", "Problem connecting with the Server\nDo you want to try again?", "Yes","No");        //exit();
                  }
                  //status 1: means success
                  else if(status.equals("1"))
                  {
                      Dialog.show("Action Successfull", "Your password change was successful\nYou will now be redirected to Login page to re-login with your new password\nThank you.", "Ok",null);
                      //Tells the run method of the threaded StateMachine class that the client has changed his/her password successfull.
                      WebServiceReturnedResult="changepass";
                      StateMachine.WriteLoginDetails(sm.userlogin_name(),newpass);
                      sm.NavigateBackToLoginForm();
                  }
                  //status 0: means error before processing all the inputs of the request
                  else if(status.equals("0"))
                  {
                      Dialog.show("Action Not Successfull", "Sorry We don't know you,Are you sure you have registered for this service?", "Ok",null);
                  }
                  //status 3: means error after processing all the inputs of the request
                  else if(status.equals("3"))
                  {
                      String errmsg=Statusmsg;
                      Dialog.show("Server Error Message", errmsg, "Ok","Cancel");
                  }
              }//end of post response method
            };//end of an anonymous inner class
            //sets the needed request parameters
            request.setUrl(url);
            request.setPost(false);
            request.setContentType(content_type);
            request.addRequestHeader("cache", "false");
            //sets the inputed form data
            request.addArgument("opass", oldpass); 
            request.addArgument("npass", newpass);
            request.addArgument("vnpass", vnewpass);
            //Tells the servlet web service to execute an action block called:"Change My Password" with id called: "change_pass" 
            request.addArgument("action", "Change My Password");
            request.addArgument("id", "change_pass"); 
            //sets the client's SNOS number
            request.addArgument("snos_type", snos_ID);
            //Indicates that this request comes from mobile phone
            request.addArgument("source", "mobile");
            //sets up progress bar as well as logging and crash reporting mechanism.
            InfiniteProgress ip = new InfiniteProgress();
            Dialog dlg = ip.showInifiniteBlocking();
            request.setDisposeOnCompletion(dlg);
            Log.setReportingLevel(Log.REPORTING_DEBUG);
            DefaultCrashReporter.init(true, 2);
            ntwrkmanager=StateMachine.getNetworkManagerObj();
            if(oldpass.equalsIgnoreCase("")||newpass.equalsIgnoreCase("")||vnewpass.equalsIgnoreCase(""))
            {
                Dialog.show("Invalid Input", "None of your input should be empty.\nThank you.", "Ok",null);
            }
            ntwrkmanager.addToQueue(request);
    }
    /**
    * This method retrieves and updates(locally and remotely) or connects and informs the web service to update(locally and remotely) client's contacts bio-data.
    * @param view String object that represents whether or not the client wants to just retrieve the data or update already retrieved ones.
    * @param uig UserInforGet object that represents the get and set class needed for any update.
    */
    public void View_UpdateContactsProfile(final String view, UserInforGet uig)
    {
        //Does the user just want to view his or her contacts data?
        if(view.equalsIgnoreCase("true"))
        {
            //Initialize the counter to be zero since this is the first time of retrieving locally in this session
            device_contactNumberCounter=0;
            //get the row size
            String rowSize=(String)Storage.getInstance().readObject("ContactsRowSize");
            RetrievedRowSize=Integer.parseInt(rowSize);
            //Retrieve the contacts data locally
            //Retrieve the biodata from the client's mobile device.
            ContactsProfileData=sqldb.getClientsContactsBiodataStoredInfor(device_contactNumberCounter);
            Log.p("Info: " +"THE contact PROFILE NAME from sqlitedb : "+ContactsProfileData[0]);
            Log.p("Info: " +"THE contact PROFILE EMAIL ADDRESS from sqlitedb : "+ContactsProfileData[1]);
            //Tells the thread's run method in StateMachine, to navigate to client's contact's profile page and display his/her contacts profile
            WebServiceReturnedResult="contactprofile";
        }
        //Did the user clicke Next or Previous to view the corresponding contacts data.
        else if(view.equalsIgnoreCase("Next")||view.equalsIgnoreCase("Prev"))
        {
            //get the row size
            String rowSize=(String)Storage.getInstance().readObject("ContactsRowSize");
            RetrievedRowSize=Integer.parseInt(rowSize);
            //Retrieve the contacts data locally
            //Retrieve the biodata from the client's mobile device.
            System.out.println("The counter value after clicking NEXT or PREVIOUS="+device_contactNumberCounter);
            ContactsProfileData=sqldb.getClientsContactsBiodataStoredInfor(device_contactNumberCounter);
            Log.p("Info: " +"THE contact PROFILE NAME from sqlitedb : "+ContactsProfileData[0]);
            Log.p("Info: " +"THE contact PROFILE EMAIL ADDRESS from sqlitedb : "+ContactsProfileData[1]);
            //Tells the thread's run method in StateMachine, to navigate to client's contact's profile page and display his/her contacts profile
            WebServiceReturnedResult="contactprofile";
        }
        //then update-both remotely and locally- already retrieved contacts data .
        else
        {
            request =  new ConnectionRequest()
            {
                @Override
                //This methos esxecutes immediately a response  is detected.
                protected void readResponse(InputStream input)
                {
                    System.out.println("Hay, my response read is coming here" );
                    try
                    {
                        //get a parser object to parse the Input Stream Object returned from the web service
                        JSONParser p = new JSONParser();
                        hashtableObj = p.parse(new InputStreamReader(input));
                        System.out.println("hashtable result is" +hashtableObj);
                        //get the status number with the corresponding message.
                        status=(String)hashtableObj.get("status");
                        Statusmsg=(String)hashtableObj.get("msg");
                    } 
                    catch (IOException ex)
                    {
                        Dialog.show("No Alerts", "Sorry there are no security alerts found for you\nDo you want to try again?", "Ok",null);
                    }
                }
                @Override
                protected void handleException(Exception err)
                {
                    // do something with err
                    Log.p("The handleExeption block message="+err.getMessage());
                    Log.p("Connection Err!! Are you connected to the internet? Check your connection");
                    Dialog.show("Connection Err!!", "Are you connected to the internet? Check your connection", "Ok", null);
                    //Get the current network manager and shuts it down so that
                    //it can be re-used to make another request
                    StateMachine.getNetworkManagerObj().shutdown();
                    StateMachine.getNetworkManagerObj().start();
                }
                //This method is invoked on the EDT after processing is complete to allow the networking code
                // to update the UI 
                @Override
                protected void postResponse()
                {
                    //Did status return nothing?
                    if(status==null)
                    {
                        Dialog.show("No Data or/and Connection", "Problem connecting with the Server\nDo you want to try again?", "Ok",null);        //exit();
                    }
                    //status 1: means success
                    else if(status.equals("1"))
                    {
                        String name=(String)hashtableObj.get("name");         
                        if(name==null)
                        {
                            Dialog.show("No Contacts Data", "No information was returned from the Server?", "Yes",null);
                        }
                        else
                        {
                            //get the contact row counter
                            Double did=(Double)hashtableObj.get("did");
                            //get the total contacts number or size as found in the remote database table.
                            Double rs=(Double)hashtableObj.get("rs"); 
                            String retvaluecounter=Double.toString(did);
                            String rowsize=Double.toString(rs);
                            retvaluecounter=retvaluecounter.substring(0, retvaluecounter.length()-2);
                            device_contactNumberCounter=Integer.parseInt(retvaluecounter);
                            rowsize=rowsize.substring(0, rowsize.length()-2);
                            RetrievedRowSize=Integer.parseInt(rowsize);
                            table_id=(String)hashtableObj.get("id");
                            System.out.println("The counter value before incrementing="+device_contactNumberCounter);
                            System.out.println("The RetrievedRowSize value after update ="+RetrievedRowSize);
                            String namea=(String)hashtableObj.get("name");
                            String relat_pos=(String)hashtableObj.get("relat_pos");
                            String fone=(String)hashtableObj.get("fone");
                            String email1=(String)hashtableObj.get("client_email");
                            String contact=(String)hashtableObj.get("contact");
                            ContactsProfileData=new String[5];
                            ContactsProfileData[0] =namea;
                            ContactsProfileData[1] =email1;
                            ContactsProfileData[2] =fone;
                            ContactsProfileData[3] =contact;
                            ContactsProfileData[4] =relat_pos;
                            sqldb=new SqliteDB();
                            //Has the client retrieved his remote data successfully? then update thesame data locally to reflect 
                            if(namea!=null)
                            {
                                //Update the client contacts biodata in his/her mobile phone.
                                sqldb.UpdateClientsContactsInfor(ContactsProfileData, device_contactNumberCounter);
                                //retrieve the same contacts biodata from the client's mobile device.
                                ContactsProfileData=sqldb.getClientsContactsBiodataStoredInfor(device_contactNumberCounter);
                                Dialog.show("Successful Update", "Your contact data update was successfully carried out", "Ok",null);
                                Log.p("Info: " +"THE contact PROFILE NAME from sqlitedb : "+ContactsProfileData[0]);
                                Log.p("Info: " +"THE contact PROFILE EMAIL ADDRESS from sqlitedb : "+ContactsProfileData[1]);
                                //Tells the thread's run method in StateMachine, to navigate to client's contact's profile page and display his/her contacts profile
                                WebServiceReturnedResult="contactprofile";
                            }
                        }//ends the 'else' statement
                    }//ends the 'if' statement
                    //status 0: means error before the completing the processing of all the request inputs
                    else if(status.equals("0"))
                    {
                        Dialog.show("No Contacts Objects", "Sorry there are no contacts objects found for you\n Error Occured while processing your request.Please contact Administrator.\nThank you", "Ok",null);
                    }
                    //status 0: means error before the completing the processing of all the request inputs
                    else if(status.equals("3"))
                    {
                        Dialog.show("Unknown Parameter", Statusmsg, "Ok",null);
                    }
                }//end of post response method
             };
             request.setUrl(url);
             request.setPost(false);
             request.setContentType(content_type);
             request.addRequestHeader("cache", "false");
             //get the contact's data
             ContactsProfileData=sqldb.getClientsContactsBiodataStoredInfor(device_contactNumberCounter);
             table_id=ContactsProfileData[5];
             Log.p("Info: " +"THE contact PROFILE table_id for Update= : "+table_id);
             request.addArgument("action", "Update Contact");
             request.addArgument("uid", table_id);
             request.addArgument("did", Integer.toString(device_contactNumberCounter));
             request.addArgument("nam", uig.getClient());
             request.addArgument("relation_pos", uig.getRelation_Position());
             request.addArgument("fone",   uig.getFone());
             request.addArgument("email1",uig.getClientEmail());
             request.addArgument("address",uig.getContact());
             request.addArgument("snos_type", snos_ID);
             request.addArgument("source", "mobile");
             InfiniteProgress ip = new InfiniteProgress();
             Dialog dlg = ip.showInifiniteBlocking();
             request.setDisposeOnCompletion(dlg);
             Log.setReportingLevel(Log.REPORTING_DEBUG);
             DefaultCrashReporter.init(true, 2);
             ntwrkmanager=StateMachine.getNetworkManagerObj();
             ntwrkmanager.addToQueue(request);
        }//ends the outer 'else' statement
    }//ends this method
}//ends the whole class