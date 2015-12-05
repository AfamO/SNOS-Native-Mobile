/**
 * Your application code goes here
 */

package userclasses;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.push.Push;
import com.teledom.snosapp.App_InformationChecker;
import generated.StateMachineBase;
import com.codename1.ui.*; 
import com.codename1.ui.events.*;
import com.codename1.ui.util.Resources;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;
import org.json.*;
import com.teledom.snosapp.JSONConnectionObject;
import static com.teledom.snosapp.JSONConnectionObject.IsNodataConnected;
import com.teledom.snosapp.ListMVC;
import com.teledom.snosapp.SqliteDB;
import com.teledom.snosapp.LoginDetailsPersistentStore;
import static com.teledom.snosapp.JSONConnectionObject.RetrievedRowSize;
import static com.teledom.snosapp.JSONConnectionObject.device_contactNumberCounter;
import java.util.Hashtable;
/**
* This class encapsulates and defines ALL the methods needed to directly communicate and interact with the GUI as well
* defines other applications important methods.
* @param inputcheck App_InformationChecker object that is used for input validation.
* @param jscnb JSONConnectionObject object that represents and is used to interact with the web service class-JSONConnectionObject.java.
* @param loginform Form object that stores the current instance of LoginForm object whenever the user signs in at the application start-up.
* @param userid String object that represents the user's name for sign in purposes.
* @param pass String object that represents the user's password for sign in purposes.
* @param url String object that represents the remote URL needed to connect to remote web service.
* @param lstmvc ListMVC object that represents the Lists Object form storing the Security alerts and their corresponding details(dates,action).
* @param lgacompfinder Component object that helps to locate local government list combo box component.
* @param status Label object that represents that displays the current status message.
* @param emailtxtfield TextField object that represents the user's email field.
* @param ntwrkmanager NetworkManager object that represents  the network manager class which facilitates connection to web service.
* @param WebServiceReturnedResult String object that represents the result of each request processed by web service.
* @param Statusmsg String object that represents the status message.
* @param gooleAuthKey_ProjectNumber String object that represents the Google's authenticating Project Number for push notification service.
* @param Statusmsg String object that represents the status message.
* @param gooleAuthKey_ProjectNumber String object that represents the Google's authenticating Project Number for push notification service.
* @param clientname TextField object that represents the user's name field.
* @param mobilenumber TextField object that represents the user's mobile number field.
* @param email1 TextField object that represents the user's email field.
* @param lga TextField object that represents the user's local govt area field.
* @param state TextField object that represents the user's state field.
* @param build_type TextField object that represents the user's building type field.
* @param loc TextField object that represents the user's location field.
* @param rela TextField object that represents the client's relationship with the contact person.
* @param descrpt TextArea object that represents the user's description field.
* @param add TextArea object that represents the user's address field.
* @param userdata String object that represents and holds the client's profile data as retrieved from web service.
* @param next Button object that represents the "NEXT" button field.
* @param prev Button object that represents the "PREVIOUS" button field.
* @param IsLoggedIn Boolean object that represents and checks whether or not a client has logged in.
* @param uig UserInforGet object accessing the Get and Set class of this application.
* @param i integer variable that serves as a counter that is used to monitor or/and debug the thread run method processing.
* @author Afam,Charles;
* @see JSONConnectionObject.java,ListMVC.java,SqliteDB.java
* @version 1.0 
*/
public class StateMachine extends StateMachineBase 
{
    private App_InformationChecker inputcheck= new App_InformationChecker();
    static JSONConnectionObject jscnb;
    static Form loginform;
    public static String userid,pass="";
    public static String url="";
    static ListMVC lstmvc;
    Pubnub pb;
    static Component lgacompfinder;
    public  static Label status;
    public static TextField emailtxtfield,gsmtxtfield;
    static NetworkManager ntwrkmanager;
    public static String WebServiceReturnedResult="fo";
    public static String Statusmsg,downloadtype="";
    private static String gooleAuthKey_ProjectNumber="920181369538";
    private TextField clientname,mobilenumber,email1,lga,state,build_type,loc,rela;
    private TextArea add,descrpt;
    public static TextField vp;
    public String userdata[];
    public static Button next,prev;
    public static boolean IsLoggedIn,IsSignedIn=false;
    private com.teledom.snosapp.UserInforGet uig;
    SqliteDB sqldb= new SqliteDB();
    int i=0;
    static String resf;
    static Label butt;
    /**
    * This one argument constructor is used to initialize GUI resource files.
    * @param resFile String object that represents the GUI initialization resource file.
    */
    public StateMachine(String resFile)
    {
        
        //loads the resource file
        super(resFile);
        resf=resFile;
        this.RegisterforPush();
         
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur.
    }
    /**
    * This no argument constructor is used to initialize this class object's-when called from any other part
    * of this application.
    */
    public StateMachine()
    {
        //loads the resource file
        super(resf);
        //sets the remote url for web services access.
        //url="http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing";
        url="http://www.snosfortress.com/servlets/RequestProcessing";
        //Storage.getInstance().deleteStorageFile("IsDevice_Id_Registered");
        //Storage.getInstance().writeObject("alerts_limit","400");
        //StateMachine.WriteLoginDetails("", "");
    }
    /**
    * This method is and should be used to initialize variables instead of
    * the constructor/class scope to avoid race conditions.
    * @param resFile String object that represents the GUI initialization resource file.
    */
    @Override
    protected void initVars(Resources res)
    {
         Util.register("LoginDetailsPersistentStore", LoginDetailsPersistentStore.class);
         //sets the remote url for web services access.
         //url="http://localhost:8084/SnoSSoftwareTest3/servlets/RequestProcessing";
         //url="http://www.snosfortress.com/servlets/RequestProcessing";
         //registers for push notification if this the first time of running this app on the mobile device.
         
    }
    public  void ConfigureRegistration_LoginLabels()
    {
        //get the device id for blackberry-bb10 android native app.
        String device_id=Storage.getInstance().readObject("device_id").toString();
                //"5957281768275968";
        //Push.getDeviceKey()
        //Is the client device id with SNOS already? I mean has he registered?
        if(Storage.getInstance().readObject("IsDevice_Id_Registered")==null)
        {
            if(jscnb==null)
            {
                //then establish a connection to the web service passing the remote url.
                JSONConnectionObject  jcb=new JSONConnectionObject(url);
                //store this object to be used through out this client's session. 
                setJSONConnObj(jcb);
                //make it the current object to be used when needed
                jscnb=jcb;
            }
            jscnb.checkEmailAddress_OR_GSM_OR_Device_ID_BeforeRegistration(device_id, "device_id");
            Display.getInstance().callSerially(new Runnable()
            {
                public void run()
                {
                    Log.p("form type=="+jscnb.getFormType());
                }
                
            });
            if(jscnb.getFormType().equalsIgnoreCase("true"))
            {
                //store it in the memory for future reference
                Storage.getInstance().writeObject("IsDevice_Id_Registered", "yes");
                Log.p("IsDevice_Id_Registered status written successfully");
                //Since he/she has the client downloaded his/her data on his/her mobile phone before?
                //then let him/her login
                Dialog.show("Notification", "You have registered for SNOS services and thus you will be redirected to Login page\nThank you.", "OK", null);
                showForm("Login_Form",null);
                findHomeRegBut().setVisible(false);
                findHomeRegBut().getComponentForm().revalidate();
            }
            else
            {
                //Is there no internet connection
            if(IsNodataConnected)
            {
                //do nothing
            }
            else
            {
                Dialog.show("Notification", "You have not registered for SNOS services and thus you will be redirected to Registration page\nThank you.", "OK", null);
                Log.p("You have not registered!!!");
                //Then take him/her to set up snos account
                findHomeRegBut().setText("You have not registered!!!");
                findHomeLoginBut().setVisible(false);
                findHomeRegBut().setEnabled(true);
                findHomeRegBut().getComponentForm().revalidate();
                findHomeLoginBut().getComponentForm().revalidate();
                showForm("Reg_Form",null);
            }
            }
        }
        else
        {
            //then let him/her login
            showForm("Login_Form",null);
            findHomeRegBut().setVisible(false);
            findHomeRegBut().getComponentForm().revalidate();
        }
    }
     /**
     * This is an action event method that is triggered-and displays Login Form- when somebody clicks a button/label 
     * to login to the application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onRegForm_NotLogginedButAction(Component c, ActionEvent event)
     {
         ConfigureRegistration_LoginLabels();
     }
     /**
     * This method registers for PUSH notification services for the whole application.
     */
     private void RegisterforPush()
     {
         //gets Hashtable object
         Hashtable meta = new Hashtable();
         //puts the google push key and google authentication needed for push registration.
         meta.put(com.codename1.push.Push.GOOGLE_PUSH_KEY, gooleAuthKey_ProjectNumber);
         //calls register push method.
         Display.getInstance().registerPush(meta, true);
     }
     private void SendPush()
     {
         String DestinationDevice = Push.getDeviceKey();
         //"5957281768275968";
         //Dialog.show("Device Push Key","This is my Push key:"+Push.getDeviceKey(),"OK", null);
         //Push.getDeviceKey();
         if(DestinationDevice.equals(""))
         {
             DestinationDevice = null;
         }
         String pushMessage="hEre I am Urgent Assistance";
         boolean prodEnvironment = false;
         String googleServerKey = "AIzaSyBT837SlAr-XzPtHzufXNF0Hbfe6ggaFKU";
         String iOSCertURL = "http://www.snosfortress.com/aps_development.cert";
         String iOSCertPassword = "iospass";
         String bbPushURL = "http://www.bbpush.com/ios.cert";
         String bbAppId = "1sandkdkdkd";
         String bbPassword = "mybbpass.com";
         String bbPort = "233";
         //bbPushURL, bbAppId, bbPassword, bbPort
         Push.sendPushMessage(pushMessage, DestinationDevice,prodEnvironment, googleServerKey,
         iOSCertURL, iOSCertPassword);
     }
     /**
     * This is an action event overriding method that carries out the first stage of client registration when somebody clicks a button/label 
     * to sign up with the application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onRegForm_RegButAction(Component c, ActionEvent event)
     {
         //gets the client surname field
         String sn=findNameTF().getText();
         //gets the client firstname field
         String fn=findFirstnameTF().getText();
         //gets the client other name field by joining both the first and othername fields.
         String on=fn+" "+findOthernameTF().getText();
         System.out.println("The sn= "+sn+" while on="+on);
         //gets other needed fields
         String mail=findEmailTF().getText();
         String fone=findMobTF().getText();
         String address=findAddTA().getText();
         String state=(String)findStaCB().getSelectedItem();
         String lga=(String)findLgaCB().getSelectedItem();
         String pass =findPassTF().getText();//gets the password field text.
         String vpass=findVpassTF().getText();//gets the verified or confirmed password field text.
         //Was the validation of the above fields successful?
         if(this.ClientDataRegValidation()==true)
         {
             //discard the first digit of the gsm fone digits. 
             fone=fone.substring(1, fone.length());
             //concatenate 234 to the rest so that the server side servlet will accept the format.
             fone="234"+fone;
             //get the gets and set object by calling the appropriate constructor.
             uig =new com.teledom.snosapp.UserInforGet(sn,on,fone,address,lga,state,mail,pass,vpass);
             //insert to the remote db via web service and calls the next stage of the registration.
             //for error re-redirection purposes
             WebServiceReturnedResult="form1";
             ConnectAddAndMoveToNext(uig,"form1");
         }
     }
     /**
     * This is an action event overriding method that carries out the 3rd stage of client registration.
     * @param c Component object that represents the GUI of the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onContactsForm_ContButAction(Component c, ActionEvent event)
     {
         //gets the contact surname field
         String surname = findNameTF().getText();
         //gets the othe firstname field
         String othername =findOthernameTF().getText();
         //gets the client full name by joining both the first and othername fields.
         String name2=surname+" "+othername;
         //gets other needed fields
         String mobile =findMobTF().getText();
         String email = findEmailTF().getText();
         String address = findAddTA().getText();
         String rela = (String)findRelaCB().getSelectedItem();  //gets the client relationship with the contact.
         //Was the validation of the above fields successful?
         if(this.ContactsValidation()==true)
         {
             //discard the first digit of the gsm fone digits. 
             mobile=mobile.substring(1, mobile.length());
             //concatenate 234 to the rest so that the server side servlet will accept the format.
             mobile="234"+mobile;
             String extra_contact =(String)findContactsOnOffSwitch().getSelectedItem();
             //did the client select to register additional contacts or not?
             if(findContactsOnOffSwitch().getSelectedIndex()==0)
             {
                 Dialog.show("Error:","You must select either 'yes' or 'no'","OK", null);
             }
             else
             {
                 System.out.println("This is the extra_contact value:"+extra_contact);
                 //get the gets and set object by calling the appropriate constructor.
                 uig =new com.teledom.snosapp.UserInforGet(extra_contact,name2,rela,address,email,mobile);
                 //insert to the remote db via web service and calls the next stage of the registration.
                 //for error re-redirection purposes
                 WebServiceReturnedResult="form3";
                 ConnectAddAndMoveToNext(uig,"form3");
             }
         }
     }
     /**
     * This is an action event overriding method that carries out the 2nd stage of client registration.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onDeviceForm_DevButAction(Component c, ActionEvent event)
     {
         //gets all the needed fields and their texts.
         String devicename = findDevNameTF().getText();
         String desc = findDesTA().getText();
         String address = findAddTA2().getText();
         String Loc = findLocTF().getText();
         String statee = (String)findStaCB().getSelectedItem();
         String lgaa =(String)findLgaCB().getSelectedItem();
         String build_typee=findBuildTypeTF().getText();
         //Was the validation of the above fields successful?
         if(this.DeviceValidation()==true)
         {
             String extra_sensor=(String)findDeviceOnOffSwitch().getSelectedItem();
             //did the client select to register additional device/object or not?
             if(findDeviceOnOffSwitch().getSelectedIndex()==0)
             {
                 Dialog.show("Warning","You must select either 'yes' or 'no' in the 'extra object' box","OK", null);
             }
             else
             {
                 System.out.println("This is the extra_sensor value:"+extra_sensor);
                 //get the gets and set object by calling the appropriate constructor.       
                 uig =new com.teledom.snosapp.UserInforGet(extra_sensor,devicename,desc,build_typee,Loc,address,lgaa,statee);
                 //for error re-redirection purposes
                 WebServiceReturnedResult="form2";
                 //insert to the remote db via web service and calls the next stage of the registration.
                 ConnectAddAndMoveToNext(uig,"form2");
             }
         }
     }
     public void NavigateBackToLoginForm()
     {
         showForm("Login_Form",null);
     }
     public void NavigateToForm(String form)
     {
         showForm(form,null);
     }
     /**
     * This method connects to the remote server via the web service and inserts the appropriate client registration data of each stage 
     * @param uig UserInforGet object that represents the get and set class.
     * @param formtype1 String object that represents the particular form registration stage .E.g: form1, form2, form3(corresponding to each stage of client registration)
     */
     public void ConnectAddAndMoveToNext(com.teledom.snosapp.UserInforGet uig,String formtype1)
     {
         //start a thread or runnable to get ready to accept the form response result from the web service-after processing
        // this.StartThread();
         //Has the client not made any connection to the web service previously?
         if(jscnb==null)
         {
             //then establish a connection to the web service passing the remote url.
             JSONConnectionObject  jcb=new JSONConnectionObject(url);
             //store this object to be used through out this client's session. 
             setJSONConnObj(jcb);
             //make it the current object to be used when needed
             jscnb=jcb;
         }
         //register the client
         jscnb.ClientRegisteration(formtype1, uig);
     }
     @Override
     /**
     * This is an action event overriding method that takes the client to a form for  the 1st stage of client registration.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     protected void onLoginForm_SignedUpButAction(Component c, ActionEvent event)
     {
        
       ConfigureRegistration_LoginLabels();
       
     }
     /**
     * This is an action event overriding method that allows the client to be logged in to access his/her security alerts and do other things.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onLoginForm_LoginButAction(Component c, ActionEvent event)
     {
         //gets the client login parameters.
         userid=findUserTF().getText();
         pass=findPassTF().getText();
         //Validate the above fields.
         if(LoginValidation())
         {
             //Has connection to web service been established already?
             //this prevents creatiing unecessary and repeated connection object.
             if(jscnb==null)
             {
                 //then establish a connection to the web service passing the remote url.
                 JSONConnectionObject  jcb=new JSONConnectionObject(url);
                 //store this object to be used through out this client's session.
                 setJSONConnObj(jcb);
                 jscnb=jcb;
             }
             //This indicates that the button clicked was logged in button for error troubleshooting.
             StateMachine.WebServiceReturnedResult="logIn";
             //Make a call to a remote web service using a ConnectionRequest
             jscnb.ClientLogin(userid,pass);
             Log.p("Call the Alerts Form Page Serially");
             Display.getInstance().callSerially(new Runnable()
             {
                 public void run()
                 {
                     Log.p("The form type before="+jscnb.getFormType());
                     //Was the logging attempt successful?
                     if(jscnb.getFormType().equals("loggedIn"))
                     {
                         //populate the form with the sms alerts and display the form to the user
                         jscnb.getAndDisplayLocalSMS(0, 10, "");
                         //listen to PubNub Subscribed messages
                         listenToMessages();
                     }
                     Log.p("The form type after="+jscnb.getFormType());
                 }
                });
                
                //Is there data connection?
                if(IsNodataConnected)
                {
                    //Display appropriate message
                    ListMVC.status.setText("You have no data or internet connection!");
                }
                else
                {
                    Log.p("You have data or internet connection!");
                }
         }
     }
     /*
      * Do PubNub Listening here
      */
     private void listenToMessages() {
     
         try {
        pb = new Pubnub("pub-c-19f34098-ac37-4a87-a2f3-2e00cf65b650", "sub-c-5bda3e3a-9435-11e5-bcd8-0619f8945a4f");
        pb.subscribe("Channel-SNOS1", new Callback() {
            @Override
            public void successCallback(String channel, final Object message, String timetoken) {
                    Display.getInstance().callSerially(new Runnable()  {
                        public void run(){
                            respond(message.toString());
                        }
                    });
            }
        });
    } catch(PubnubException err) {
        Log.e(err);
        Dialog.show("Error", "There was a communication error: " + err, "OK", null);
    }
    }
    private void respond(String response)
    {
        Dialog.show("PubNub Security Alert", response, "OK", null);
    }
     public Form getAlertForm()
     {
         return showForm("Alert_Form",null);
     }
     /**
     * This method validates and filters the client's login form text fields. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean LoginValidation()
     {
         String userid=findUserTF().getText();
         String pass=findPassTF().getText();
         if(userid.equals("") ||userid == null)
         {
             Dialog.show("Warning","userid Field should not be Empty","OK", null);
             return false;
         } 
         else if (!inputcheck.checkEmail(userid))
         {
             Dialog.show("Warning","Invalid Character in userid Field,\n"
             + "userid is your Email e.g teledom@snosfortress.com","OK", null);
             return false;
         }
         else if (pass.equals("") || pass == null) 
         {
             Dialog.show("Warning"," password should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkPassword(pass))
         {
             Dialog.show("Warning","Invalid Character in password Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else
         {
            return true;
         }
     }
     /**
     * This method validates and filters the client's personal registration form text fields. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean ClientDataRegValidation()
     {
         //gets the client surname field
         String sn=findNameTF().getText();
         //gets the client firstname field
         String fn=findFirstnameTF().getText();
         //gets the client other name field by joining both the first and othername fields.
         String on=fn+" "+findOthernameTF().getText();
         System.out.println("The sn= "+sn+" while on="+on);
         String fullname=sn+on;
         //gets other needed fields
         String mail=findEmailTF().getText();
         String fone=findMobTF().getText();
         String address=findAddTA().getText();
         String statee=(String)findStaCB().getSelectedItem();
         String lgaa=(String)findLgaCB().getSelectedItem();
         String pass =findPassTF().getText();//gets the password field text.
         String vpass=findVpassTF().getText();//gets the verified or confirmed password field text.
         if (fullname.equals("") || fullname == null) 
         {
             Dialog.show("Warning","FullName should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkSurname(fullname))
         {
             Dialog.show("Warning","Invalid Character in FullName Field, Enter letter A-Z","OK", null);
             return false;
         }
         
         else if (fone.equals("") || fone == null)
         {
             Dialog.show("Warning","Mobile Number should not be Empty","OK", null);
             return false;
         }
         
         else if (!inputcheck.checkGsm(fone))
         {
             Dialog.show("Warning","Invalid Character in Mobile Number Field,\n"
             + " Phone Number format is 080######## or 070########","OK", null);
             return false;
         }
         
         else if(mail.equals("") || mail == null)
         {
             Dialog.show("Warning","Email Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkEmail(mail))
         {
             Dialog.show("Warning","Invalid Character in Email Field,\n"
             + " Email format is teledom@gmail.com","OK", null);
             return false;
         }
         else if(address.equals("") || address == null)
         {
             Dialog.show("Warning"," Address should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(address))
         {
             Dialog.show("Warning","Invalid Character in  Address Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if(statee.equals("") ||statee == null)
         {
             Dialog.show("Warning","State is not selected","OK", null);
             return false;
         }
         else if (!inputcheck.checkState(statee))
         {
             Dialog.show("Warning","Invalid Character in State Field, Enter letter A-Z,a-b, etc","OK", null);
             return false;
         }
         else if(lgaa.equals("") ||lgaa == null)
         {
             Dialog.show("Warning","LGA is not selected","OK", null);
             return false;
         }
         else if (!inputcheck.checkState(lgaa))
         {
             Dialog.show("Warning","Invalid Character in LGA Field, Enter letter A-Z,a-b, etc","OK", null);
             return false;
         }
         else if (pass.equals("") || pass == null)
         {
             Dialog.show("Warning"," password should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkPassword(pass))
         {
             Dialog.show("Warning","Invalid Character in password Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (vpass.equals("") || vpass == null)
         {
             Dialog.show("Warning"," Verify password should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkPassword(vpass))
         {
             Dialog.show("Warning","Invalid Character in verify password Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (!pass.equals(vpass))
         {
             Dialog.show("Warning","Password Mismatch,Check Password and Verify Password","OK", null);
             return false;
         }
         else
         {
           return true;
         }
     }
     /**
     * This method validates and filters the client's device/object form text fields. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean DeviceValidation()
     {
         String devicename = findDevNameTF().getText();
         String desc = findDesTA().getText();
         String add = findAddTA2().getText();
         String build=findBuildTypeTF().getText();
         String Loc = findLocTF().getText();
         if (devicename.equals("") || devicename == null)
         {
             Dialog.show("Warning"," Device Name should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(devicename))
         {
             Dialog.show("Warning","Invalid Character in Device Name Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (desc.equals("") || desc == null)
         {
             Dialog.show("Warning"," Description should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(desc))
         {
             Dialog.show("Warning","Invalid Character in Description Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (add.equals("") || add == null)
         {
             Dialog.show("Warning"," Address should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(add))
         {
             Dialog.show("Warning","Invalid Character in Address Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (build.equals("") || build == null)
         {
             Dialog.show("Warning","Property Type should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(build))
         {
             Dialog.show("Warning","Invalid Character in Property Type Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (Loc.equals("") || Loc == null)
         {
             Dialog.show("Warning","Location should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(Loc))
         {
             Dialog.show("Warning","Invalid Character in Location Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if(findStaCB().getSelectedIndex()==0)
         {
             Dialog.show("Warning","State is not selected","OK", null);
             return false;
         }
         else if(findLgaCB().getSelectedIndex()==0)
         {
             Dialog.show("Warning","LGA is not selected","OK", null);
             return false;
         }
         else
         {
             return true;
         }
     }
     /**
     * This method validates and filters the client's contacts form text fields. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean ContactsValidation()
     {
         String surname = findNameTF().getText();
         String othername =findOthernameTF().getText();
         String mobile =findMobTF().getText();
         String email = findEmailTF().getText();
         String address = findAddTA().getText();
         if (surname.equals("") || surname == null) 
         {
             Dialog.show("Warning","Surname should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkSurname(surname))
         {
             Dialog.show("Warning","Invalid Character in surname Field, Enter letter A-Z","OK", null);
             return false;
         }
         else if (!inputcheck.checkMidname(othername))
         {
             Dialog.show("Warning","Invalid Character in Othername Field, Enter letter A-Z","OK", null);
             return false;
         } 
         else if (mobile.equals("") || mobile == null)
         {
             Dialog.show("Warning","Mobile Number should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkGsm(mobile))
         {
             Dialog.show("Warning","Invalid Character in Mobile Number Field,\n"
             + " Phone Number format is 080######## or 070########","OK", null);
             return false;
         }
         else if (email.equals("") || email == null)
         {
             Dialog.show("Warning","Email Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkEmail(email))
         {
             Dialog.show("Warning","Invalid Character in Email Field,\n"
             + " Email format is teledom@gmail.com","OK", null);
             return false;
         }
         else if (address.equals("") || address == null)
         {
             Dialog.show("Warning"," Address should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(address))
         {
             Dialog.show("Warning","Invalid Character in  Address Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if(findRelaCB().getSelectedIndex()==0)
         {
             Dialog.show("Warning","Relationship is not selected","OK", null);
             return false;
         }
         else
         {
             return true;
         }
     }
     /**
     * This method validates and filters the client's personal profile form text fields. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean ValidateEditedProfile()
     {
         String fullname=clientname.getText();
         String address=add.getText();
         String state=findStateTF().getText();
         String lga=findLgaTF().getText();
         String mail=email1.getText();
         String fone=mobilenumber.getText();
         
         if (fullname.equals("") || fullname == null)
         {
             Dialog.show("Warning","Fullname should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkSurname(fullname))
         {
             Dialog.show("Warning","Invalid Character in surname Field, Enter letter A-Z","OK", null);
             return false;
         }
         /*
          * Forget about validating phone number and email since they are the
          * primary means of identifying a client and thus the Administrator should update them or be presnt/aware 
          * that they are edited.
         else if (fone.equals("") || fone == null)
         {
             Dialog.show("Warning","Mobile Number should not be Empty","OK", null);
             return false;
         }
         
         else if (!inputcheck.checkGsm(fone))
         {
             Dialog.show("Warning","Invalid Character in Mobile Number Field,\n"
             + " Phone Number format is 080######## or 070########","OK", null);
             return false;
         }
         else if (mail.equals("") || mail == null)
         {
             Dialog.show("Warning","Email Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkEmail(mail))
         {
             Dialog.show("Warning","Invalid Character in Email Field,\n"
             + " Email format is teledom@gmail.com","OK", null);
             return false;
         }
         */
         else if (address.equals("") || address == null)
         {
             Dialog.show("Warning"," Address should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(address))
         {
             Dialog.show("Warning","Invalid Character in  Address Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if(state.equals("") || state==null)
         {
             Dialog.show("Warning","State should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkState(state))
         {
             Dialog.show("Warning","Invalid Character in  State Field, Enter letter A-Z, etc","OK", null);
             return false;
         }
         else if(lga.equals("") || lga==null)
         {
             Dialog.show("Warning","LGA should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checklGA(lga))
         {
             Dialog.show("Warning","Invalid Character in LGA Field, Enter letter A-Z, etc","OK", null);
             return false;
         }
         else
         {
             return true;
         }
     }
     /**
     * This method validates and filters the client's ChangeLoginDetails form text fields. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean ValidateChangeLoginDetails()
     {
         String oldpass=findOldPassTF().getText();
         String pass =findPassTF().getText();
         String vpass=findVpassTF().getText();
         if (oldpass.equals("") || oldpass == null)
         {
             Dialog.show("Warning","OldPassword Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkPassword(pass))
         {
             Dialog.show("Warning","Invalid Character in OldPassword Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (pass.equals("") || pass == null)
         {
             Dialog.show("Warning"," password should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkPassword(pass))
         {
             Dialog.show("Warning","Invalid Character in password Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (vpass.equals("") || vpass == null)
         {
             Dialog.show("Warning"," Verify password should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkPassword(vpass))
         {
             Dialog.show("Warning","Invalid Character in verify password Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (!pass.equals(vpass))
         {
             Dialog.show("Warning","Password Mismatch,Check Password and Verify Password","OK", null);
             return false;
         }
         else 
         {
             return true;
         }
     }
     /**
     * This method validates and filters the client's Edited Contacts Details form text fields. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean ValidateEditedContacts()
     {
         String fullname = findNameTF().getText();
         String mobile =findMobTF().getText();
         String email = findEmailTF().getText();
         String address = findAddTA().getText();
         String relation=findRelaTF().getText();
         if (fullname.equals("") || fullname == null) 
         {
             Dialog.show("Warning","Surname should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkFullname(fullname))
         {
             Dialog.show("Warning","Invalid Character in surname Field, Enter letter A-Z","OK", null);
             return false;
         }
         else if (mobile.equals("") || mobile == null)
         {
             Dialog.show("Warning","Mobile Number should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkGsm(mobile))
         {
             Dialog.show("Warning","Invalid Character in Mobile Number Field,\n"
             + " Phone Number format is 080######## or 070########","OK", null);
             return false;
         }
         else if (email.equals("") || email == null)
         {
             Dialog.show("Warning","Email Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkEmail(email))
         {
             Dialog.show("Warning","Invalid Character in Email Field,\n"
             + " Email format is teledom@gmail.com","OK", null);
             return false;
         }
         else if (address.equals("") || address == null)
         {
             Dialog.show("Warning"," Address should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(address))
         {
             Dialog.show("Warning","Invalid Character in  Address Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if(relation.equals("") || relation==null)
         {
             Dialog.show("Warning","Relationship is Field should not be Empty","OK", null);
             return false; 
         }
         else if(!inputcheck.checkRelation(relation))
         {
             Dialog.show("Warning","Invalid Character in Relationship Field, Enter letter A-Z, etc","OK", null);
             return false;
         }
         else
         {
             return true;
         }
     }
     /**
     * This method validates and filters the client's Edited Device Details form text fields. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean ValidateEditedDevice()
     {
         String devicebname = findDevNameTF().getText();
         String desc = findDesTA().getText();
         String add = findAddTA2().getText();
         String build=findBuildTypeTF().getText();
         String Loc = findLocTF().getText(); 
         String state=findStateTF().getText();
         String lga=findLgaTF().getText();
         if (devicebname.equals("") || devicebname == null)
         {
             Dialog.show("Warning"," Device Name should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(devicebname))
         {
             Dialog.show("Warning","Invalid Character in Device Name Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (desc.equals("") || desc == null)
         {
             Dialog.show("Warning"," Description should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(desc))
         {
             Dialog.show("Warning","Invalid Character in Description Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (add.equals("") || add == null)
         {
             Dialog.show("Warning"," Address should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(add))
         {
             Dialog.show("Warning","Invalid Character in Address Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (build.equals("") || build == null)
         {
             Dialog.show("Warning","Property Type should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(build))
         {
             Dialog.show("Warning","Invalid Character in Property Type Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (Loc.equals("") || Loc == null)
         {
             Dialog.show("Warning","Location should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkAddress(Loc))
         {
             Dialog.show("Warning","Invalid Character in Location Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if(state.equals("") || state==null)
         {
             Dialog.show("Warning","State Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkState(state))
         {
             Dialog.show("Warning","Invalid Character in State Field, Enter letter A-Z etc","OK", null);
             return false;
         }
         else if(lga.equals("") || lga==null)
         {
             Dialog.show("Warning","LGA Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checklGA(lga))
         {
             Dialog.show("Warning","Invalid Character in LGA Field, Enter letter A-Z etc","OK", null);
             return false;
         }
         else
         {
             return true;
         }
     }
     /**
     * This method validates and filters the client's email of the password to recover. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean ValidatePasswordRecovery()
     {
         String email = findPassRecoverUserTF().getText();
         if (email.equals("") || email == null)
         {
             Dialog.show("Warning","Email Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkEmail(email))
         {
             Dialog.show("Warning","Invalid Character in Email Field,\n"
             + " Email format is teledom@gmail.com","OK", null);
             return false;
         }
         else
         {
             return true;
         }
     }
     /**
     * This method validates and filters the client's sent TOKEN of the password to recover. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean ValidateToken()
     {
         String token = findTokenTF().getText();
         if (token.equals("") || token == null)
         {
             Dialog.show("Warning","Token Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkToken(token))
         {
             Dialog.show("Warning","Invalid Character in Token Field, \n"
             + " Token format is ######","OK", null);
             return false;
         }
         else
         {
             return true;
         }
     }
     /**
     * This method validates and filters the client's Login Details to reset. 
     * @return Boolean -It returns true if everything went well otherwise false.
     */
     public boolean ValidateResetLoginDetails()
     {
         String email = findResetEmailTF().getText();
         String pass =findPassTF().getText();
         String vpass=findVpassTF().getText();
         if (email.equals("") || email == null)
         {
             Dialog.show("Warning","Email Field should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkEmail(email))
         {
             Dialog.show("Warning","Invalid Character in Email Field,\n"
             + " Email format is teledom@gmail.com","OK", null);
             return false;
         }
         else if (pass.equals("") || pass == null)
         {
             Dialog.show("Warning"," password should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkPassword(pass))
         {
             Dialog.show("Warning","Invalid Character in password Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (vpass.equals("") || vpass == null)
         {
             Dialog.show("Warning"," Verify password should not be Empty","OK", null);
             return false;
         }
         else if (!inputcheck.checkPassword(vpass))
         {
             Dialog.show("Warning","Invalid Character in verify password Field, Enter letter A-Z,0-9, etc","OK", null);
             return false;
         }
         else if (!pass.equals(vpass))
         {
             Dialog.show("Warning","Password Mismatch,Check Password and Verify Password","OK", null);
             return false;
         }
         else 
         {
             return true;      
         }
     }
     /**
     * This method saves client Login Details to mobile device-at the installation of this application on it.
     * @param userid String object representing the user name 
     * @param pass String object representing the user's password 
     */
     public static void WriteLoginDetails(String userid,String pass)
     {
         //get an object of the class below.
         LoginDetailsPersistentStore data = new LoginDetailsPersistentStore();
         //use it to set the user's credentials.
         data.setUserName(userid);
         data.setPassword(pass);
         //write the info locally.
         Storage.getInstance().writeObject("LoginDetails", data);
         Log.p("Info: " +"THE USER LOGGED INFO SAVED SUCCESSFULLY");
     }
     /**
     * This method gets the client locally saved username from the mobile device. 
     * @return String -It returns the username as a string.
     */
     public  String userlogin_name()
     {
         //get an object of the class below.
         LoginDetailsPersistentStore log = (LoginDetailsPersistentStore)Storage.getInstance().readObject("LoginDetails");
         //get the  locally stored info.
         String username= log.getUserName();
         return username;
     }
     /**
     * This method gets the client locally saved user password from the mobile device. 
     * @return String -It returns the password as a string.
     */
     public  String userlogin_pass()
     {
         //get an object of the class below.
         LoginDetailsPersistentStore pas = (LoginDetailsPersistentStore)Storage.getInstance().readObject("LoginDetails");
         String pass2= pas.getPassword();
         return pass2;
     }
     /**
     * This is an action event overriding method that initiates 1st stage of client PasswordRecovery,
     * by sending a generated token the client's email.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onPasswordRecovery_TokenLoginButAction(Component c, ActionEvent event)
     {
         String email = findPassRecoverUserTF().getText();
         //carries out the needed validation of the above.
         if(this.ValidatePasswordRecovery()==true)
         {
             //Has connection to web service been established already?
             if(jscnb==null)
             {
                 JSONConnectionObject  jcb=new JSONConnectionObject(url);
                 setJSONConnObj(jcb);
                 jscnb=jcb;
             }
             //send the token email via web service.
             jscnb.SendTokenEmail(email);
         }
     }
     /**
     * This is an action event overriding method that initiates 3rd stage of client PasswordRecovery,
     * by creating a NEW  client's password .
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onResetLoginDetails_EditLoginButAction(Component c, ActionEvent event)
     {
         //get the needed fields and their texts.
         String email = findResetEmailTF().getText();
         String pass =findPassTF().getText();
         String vpass=findVpassTF().getText(); 
        // the needed validation of the above.
        if(this.ValidateResetLoginDetails()==true)
        {
             //Has connection to web service been established already?
            //this prevents creatiing unecessary and repeated connection object.
            if(jscnb==null)
            {
                JSONConnectionObject  jcb=new JSONConnectionObject(url);
                //store the new object in the current session
                setJSONConnObj(jcb);
                jscnb=jcb;
            }
            //then create the new password remotely and locally
            jscnb.CreateUserNewPassword(email, pass, vpass);
            
            
        }
     }
     /**
     * This is an action event overriding method that initiates 2nd stage of client PasswordRecovery,
     * by VERIFYING the recovery token sent to the client's email.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onVerifyToken_PSLoginButAction(Component c, ActionEvent event)
     {
         String token = findTokenTF().getText();
         if(this.ValidateToken()==true)
         {
             //Has connection to web service been established already?
             //this prevents creatiing unecessary and repeated connection object.
             if(jscnb==null)
             {
                 JSONConnectionObject  jcb=new JSONConnectionObject(url);
                 setJSONConnObj(jcb);
                 jscnb=jcb;
             }
             //then-remotely- check if the token exists
             jscnb.CheckIfClientTokenExists(token);
         }
     }
     /**
     * This is an action event overriding method that navigates the client to the about page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onLoginForm_AboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client to the Emergency Contacts page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onLoginForm_EmeButAction(Component c, ActionEvent event)
     {
         showForm("Emergency_Contacts_Inform_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client to the Splash Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onLoginForm_HomeButAction(Component c, ActionEvent event)
     {
         showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to the about page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onAboutForm_AboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to the Emergency Contacts Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onAboutForm_AboutContactsButAction(Component c, ActionEvent event)
     {
         showForm("Emergency_Contacts_Inform_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to the Splash Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onAboutForm_AboutHomeButAction(Component c, ActionEvent event)
     {
         showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to the About Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onRegForm_RegAboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to the Emergency Contacts Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onRegForm_RegContButAction(Component c, ActionEvent event)
     {
         showForm("Emergency_Contacts_Inform_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to the Splash Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onRegForm_RegHomeButAction(Component c, ActionEvent event)
     {
         showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Client Personal Registration Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onSplashForm_HomeRegButAction(Component c, ActionEvent event)
     {
         ConfigureRegistration_LoginLabels();
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Client's Sign in Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onSplashForm_HomeLoginButAction(Component c, ActionEvent event)
     {
         showForm("Login_Form",null);
         //ConfigureRegistration_LoginLabels();
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Splash page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onSplashForm_HomeButAction(Component c, ActionEvent event)
     {
         showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to About Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onSplashForm_AboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null);      
     }
     /**
     * This is an action event overriding method that navigates the client-again- to the Emergency Contact Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onSplashForm_EmeButAction(Component c, ActionEvent event)
     {
         showForm("Emergency_Contacts_Inform_Form",null);     
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Splash page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEmergencyContactsInformForm_EmeHomeAction(Component c, ActionEvent event)
     {
           showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to About page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEmergencyContactsInformForm_EmeAboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null); 
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Emergency contacts page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEmergencyContactsInformForm_EmeContactButAction(Component c, ActionEvent event) 
     {
         showForm("Emergency_Contacts_Inform_Form",null); 
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Splash page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEditRegInformForm_EditRegHomeButAction(Component c, ActionEvent event)
     {
         showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to About page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEditRegInformForm_EditRegAboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null); 
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Emergency Contacts page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEditRegInformForm_EditRegContButAction(Component c, ActionEvent event)
     {
         showForm("Emergency_Contacts_Inform_Form",null); 
     }
     @Override
     /**
     * This is an action event overriding method that navigates the client-again- to Splash page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     protected void onEditContactForm_EditConHomeButAction(Component c, ActionEvent event)
     {
         showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to the About page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEditContactForm_EditConAboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to the Emergency page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEditContactForm_EditConContactButAction(Component c, ActionEvent event)
     {
         showForm("Emergency_Contacts_Inform_Form",null); 
     }
     /**
     * This is an action event overriding method that saves the changes the client made to his profile/her
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEditRegInformForm_EditRegButAction(Component c, ActionEvent event)
     {
         if(this.ValidateEditedProfile()==true)
         {
             //then carry out the update remotely and locally.
             DisplayUserProfile(false);
          }
     }
     @Override
     /**
     * This is an action event overriding method that navigates the client-again- to Splash page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     protected void onEditLogin_EditLoginHomeButAction(Component c, ActionEvent event)
     {
         showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to About page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEditLogin_EditLoginAboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Emergency Contacts page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEditLogin_EditLoginEmeButAction(Component c, ActionEvent event) 
     {
         showForm("Emergency_Contacts_Inform_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Splash page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onPasswordRecovery_PSHomeButAction(Component c, ActionEvent event)
     {
         showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to About page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onPasswordRecovery_PSAboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null);
     }
     @Override
     /**
     * This is an action event overriding method that navigates the client-again- to Emergency Contacts page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     protected void onPasswordRecovery_PSEmeButAction(Component c, ActionEvent event)
     {
         showForm("Emergency_Contacts_Inform_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client to Password Recovery page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onSplashForm_SetButAction(Component c, ActionEvent event)
     {
         showForm("Password_Recovery",null);
     }
     /**
     * This is an action event overriding method that navigates-again-the client to Password Recovery page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onAboutForm_AboutSettingButAction(Component c, ActionEvent event)
     {
         showForm("Password_Recovery",null); 
     }
     /**
     * This is an action event overriding method that navigates the client to Password Recovery page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEmergencyContactsInformForm_EmeSettingButAction(Component c, ActionEvent event)
     {
         showForm("Password_Recovery",null);
     }
     /**
     * This is an action event overriding method that navigates the client to Token Verification page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onPasswordRecovery_VerifyButtAction(Component c, ActionEvent event)
     {
         showForm("VerifyToken",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Splash page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onVerifyToken_PSHomeButAction(Component c, ActionEvent event)
     {
         showForm("Splash_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to About page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onVerifyToken_PSAboutButAction(Component c, ActionEvent event)
     {
         showForm("About_Form",null); 
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Emergency Contacts page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onVerifyToken_PSEmeButAction(Component c, ActionEvent event)
     {
         showForm("Emergency_Contacts_Inform_Form",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Password Recovery page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onVerifyToken_PSSetButAction(Component c, ActionEvent event)
     {
         showForm("Password_Recovery",null);
     }
     /**
     * This is an action event overriding method that navigates the client-again- to Password Recovery page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onLoginForm_RecoverButAction(Component c, ActionEvent event)
     {
         showForm("Password_Recovery",null);
     }
     /**
     * This method changes the client's password.
     */
     public void ChangeUserPassword()
     {
         //Has the web service been connected to before?
         //This avoids unecessary repetitive web service connections objects
         if(jscnb==null)
         {
             JSONConnectionObject  jcb=new JSONConnectionObject(url);
             setJSONConnObj(jcb);
             jscnb=jcb;
         }
         //gets the neede details
         String oldpass=findOldPassTF().getText();
         String newpass=findPassTF().getText();
         String vnewpass=findVpassTF().getText();
         //then update the password remotely and locally
         jscnb.ChangePassWord(oldpass, newpass, vnewpass);
     }
     /**
     * This methods sets and stores the Web Service connection object-the first time the object was created-so as to be re-used later
     * through out the whole application running session.
     * @param jscob JSONConnectionObject object the object to be stored and set.
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     public static void setJSONConnObj(JSONConnectionObject jscob)
     {
        jscnb=jscob;
     }
     /**
     * This methods sets and stores the Login Form object-the first time the user signs in at each application's start up -so as to be re-used later
     * through out the whole application running session.
     * @param formobject Form object the object to be stored and set.
     */
     public static void setFormObj(Form formobject)
     {
        loginform=formobject;
     }
     /**
     * This is method retrieves an object of Login Form-stored whenever the user starts up the application and signs in to the application- page to be re-used when needed.
     * @return Form -It returns the form object in question.
     */
     public static Form getFormObj()
     {
        return loginform;
     }
     /**
     * This methods sets and stores the Network Manager(used to connect to the web service) object-the first time it is created -so as to be re-used later
     * through out the whole application running session.
     * @param nm NetworkManager object in question.
     */
     public static void setNetworkManagerObj(NetworkManager nm)
     {
         ntwrkmanager=nm;
     }
     /**
     * This method retrieves the  Network Manager object for web service connection..
     * @return NetworkManager -It returns the NetworkManager object in question.
     */
     public static NetworkManager getNetworkManagerObj()
     {
         return ntwrkmanager;
     }
     /**
     * This methods retrieves Web Service connection object-stored the first time the object was created-so as to be re-used later
     * through out the whole application running session.
     * @return JSONConnectionObject -It returns the JSONConnectionObject object in question.
     */
     public static JSONConnectionObject getJSONConnObj()
     {
         return jscnb;
     }
     
     /**
     * This methods retrieves Alerts Lists object-stored the first  time the object was created-so as to be re-used later
     * through out the whole application running session.
     * @return ListMVC -It returns the ListMVC object in question.
     */
     public static ListMVC getListMVCObj()
     {
         return lstmvc;
     }
     /**
     * This methods sets and stores Alerts Lists object-the first time the object is created-so as to be re-used later
     * through out the whole application running session.
     * @param lmvcobj ListMVC object in question.
     */
     public static void setListMVCObj(ListMVC lmvcobj)
     {
         lstmvc=lmvcobj;
     }
     /**
     * This is an action event overriding method that retrieves the lists of all the local governments belonging to the client's state of origin.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onRegForm_StaCBAction(Component c, ActionEvent event) 
     {
         //get the client's state of origin
         String statee=(String)findStaCB().getSelectedItem();
         //Has the web service been connected to intially?
         if(jscnb==null)
         {
             JSONConnectionObject  jcb=new JSONConnectionObject(url);
             setJSONConnObj(jcb);
             jscnb=jcb;
         }
         //get the lga combo box object
         lgacompfinder=findLgaCB();
         //get the local government areas list.
         jscnb.GetLGALists(statee,"form1");
     }
     /**
     * This methods sets and stores the above retrieved local government areas lists to a GUI component/combo box
     * @param lgalist String array object representing the local government lists in question .
     */
     public static  void SetLGAListModel(String []lgalist)
     {
         //get a list
         List lm= (List)lgacompfinder;
         if(lgacompfinder==null)
         {
             System.out.println("This componet is null");
         }
         else
         {
             //sets the local government as a ListModel component to be selected by the client
             lm.setModel(new com.codename1.ui.list.DefaultListModel(lgalist));
         }
     }
     /**
     * This is an action event overriding method that retrieves the lists of all the local governments belonging to the client's device/object state of origin.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onDeviceForm_StaCBAction(Component c, ActionEvent event)
     {
         //get the client's state of origin
         String statee=(String)findStaCB().getSelectedItem();
         //Has the web service been connected to intially?
         if(jscnb==null)
         {
             JSONConnectionObject  jcb=new JSONConnectionObject(url);
             setJSONConnObj(jcb);
             jscnb=jcb;
         }
         //get the lga combo box object
         lgacompfinder=findLgaCB();
         //get the local government areas list.
         jscnb.GetLGALists(statee,"form2");
     }
     /**
     * This is an action event overriding method that verifies whether a particular email address has been registered with this application or not
     * during the first stage of client registration.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onRegForm_EmailTFAction(Component c, ActionEvent event)
     {
         //gets the email address in question
         String emailadd=findEmailTF(c.getComponentForm()).getText();
         //Has the web service been connected to intially?
         if(jscnb==null)
         {
             JSONConnectionObject  jcb=new JSONConnectionObject(url);
             setJSONConnObj(jcb);
             jscnb=jcb;
         }
         //get a status message component
         status=findStatusLab(c.getComponentForm());
         emailtxtfield=findEmailTF(c.getComponentForm());
         //Set the type of verification to be email since you are checking whether an email address exists or not.
         String type="email";
         //Has this email been registered already?
         if(jscnb.checkEmailAddress_OR_GSM_OR_Device_ID_BeforeRegistration(emailadd,type))
         {
             
         }
         else
         {
             
         }
     }
     /**
     * This methods retrieves,displays-and updates- client's personal profile data.
     * @param view boolean object indicating whether the client wants to just VIEW only his/her data or UPDATE and then VIEW them.
     */
     public void DisplayUserProfile(final boolean view)
     {
         //Has the web service been connected to intially?
         if(jscnb==null)
         {
             JSONConnectionObject  jcb=new JSONConnectionObject(url);
             setJSONConnObj(jcb);
             jscnb=jcb;
         }
         //Does the client want to update his data and not view them?
         if(!view)
         {
             //get all the data needed for the update
             String fullname=clientname.getText();
             String address=add.getText();
             String statee=state.getText();
             String lgaa=lga.getText();
             String mail=email1.getText();
             String fone=mobilenumber.getText();
             uig =new com.teledom.snosapp.UserInforGet(fullname,fone,address,lgaa,statee,mail,"");
             //carry out the appropriate action depending on the value of view: true =display data only; false=update and then display data
             jscnb.View_UpdateClientProfile(view, uig);
         }
         else
         {
             //go to the form that will display the profile
             showForm("Edit_Reg_Inform_Form",null);
         }
     }
     /**
     * This methods retrieves,displays(either current, previous or next set of data)-and updates- client's object/device profile data.
     * @param view String object indicating whether the client wants to just VIEW his/her data only or UPDATE and then VIEW  them.
     */
     public void DisplayDeviceProfile(String view)
     {
         //Has the web service been connected to intially?
         if(jscnb==null)
         {
             JSONConnectionObject  jcb=new JSONConnectionObject(url);
             setJSONConnObj(jcb);
             jscnb=jcb;
         }
         //Does the client want to update his data and not view them?
         if(view.equalsIgnoreCase("false"))
         {
             //Then get all the data needed for the update
             String devicename=clientname.getText();
             String address=add.getText();
             String descr=descrpt.getText();
             String loca=loc.getText();
             String statee=state.getText();
             String lgaa=lga.getText();
             String build_typee=build_type.getText();
             uig =new com.teledom.snosapp.UserInforGet("",devicename,descr,build_typee,loca,address,lgaa,statee);
             //carry out the appropriate action depending on the value of view: "true" =display data only; "false"=update and then display data; "Next"="display more or next set of data";"Prev"="display previous data".
             jscnb.View_UpdateDeviceProfile(view, uig);
         }
         else
         {
             showForm("Edit_Device_Form",null);  
         }
         
     }
     /**
     * This methods retrieves,displays(either current, previous or next set of data)-and updates- client's contacts profile data.
     * @param view String object indicating whether the client wants to just VIEW his/her data only or UPDATE and then VIEW  them.
     */
     public void DisplayContactProfile(String view)
     {
         //Has the web service been connected to intially?
         if(jscnb==null)
         {
             JSONConnectionObject  jcb=new JSONConnectionObject(url);
             setJSONConnObj(jcb);
             jscnb=jcb;
         }
         //Does the client want to update his data and not view them?
         if(view.equalsIgnoreCase("false"))
         {
             String name2=clientname.getText();
             String mail=email1.getText();
             String fone=mobilenumber.getText();
             String address=add.getText();
             String relat_pos=rela.getText();
             uig =new com.teledom.snosapp.UserInforGet("",name2,relat_pos,address,mail,fone);
             //carry out the appropriate action depending on the value of view: "true" =display data only; "false"=update and then display data;"Next"="display more or next set of data than";"Prev"="display previous data".
             jscnb.View_UpdateContactsProfile(view, uig);
         }
         else
         {
             showForm("Edit_Contact_Form",null);  
         }
     }
     /**
     * This is an action event overriding method that calls a method that updates and changes the client's password.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected void onEditLogin_EditLoginButAction(Component c, ActionEvent event)
     {
         //validate the password fields
         if(ValidateChangeLoginDetails())
         {
             //updates the password
             ChangeUserPassword();
         }
     }
     /**
     * This is an action event overriding method that LOGS OUT and navigates the client-again- to the Sign in Form page of this application.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
     protected boolean onAlertFormLogout()
     {
         showForm("Login_Form",null);
         return true;
     }
     /**
     * This is an action event overriding method that calls a method which retrieves and displays client's personal profiles.
     */
     @Override
     protected boolean onAlertFormProfile()
     {
         DisplayUserProfile(true);
         return true;
     }
     /**
     * This is an action event overriding method that calls a method which retrieves and displays client's objects/device profiles.
     */
     @Override
     protected boolean onAlertFormDevice()
     {
         DisplayDeviceProfile("true");
         return true;
     }
     /**
     * This is an action event overriding method that calls a method which retrieves and displays client's contacts profiles.
     */
     @Override
     protected boolean onAlertFormContacts()
     {
         DisplayContactProfile("true");
         return true;
     }
     /**
     * This is an action event overriding method that navigates the client to a form where he/she can edit his/her login parameters.
     */
     @Override
     protected boolean onAlertFormChangeLoginDetails()
     {
         
         showForm("Edit_Login",null);
         return true;
     }
     /**
     * This is an action event overriding method that calls a method which retrieves and displays client's contacts profiles.
     * @param c Component object that represents the GUI the current component
     * @param event ActionEvent object that represents the GUI the current triggered event action
     */
     @Override
    protected void onEditContactForm_EdittContactButtonAction(Component c, ActionEvent event)
    {
        //then carry out the update remotely and locally
        DisplayContactProfile("false");    
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays MORE or NEXT set of client's contacts profiles.
    * @param c Component object that represents the GUI the current component
    * @param event ActionEvent object that represents the GUI the current triggered event action
    */
    @Override
    protected void onEditContactForm_NextButAction(Component c, ActionEvent event)
    {
        //Increment the counter
        device_contactNumberCounter++;
        //DisplayContactProfile("Next");
        ShowContactsData(c.getComponentForm());
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays PREVIOUS set of client's contacts profiles.
    * @param c Component object that represents the GUI the current component
    * @param event ActionEvent object that represents the GUI the current triggered event action
    */
    @Override
    protected void onEditContactForm_PreButAction(Component c, ActionEvent event)
    {
        //Decrement the counter
        device_contactNumberCounter--;
        //DisplayContactProfile("Prev");
        ShowContactsData(c.getComponentForm());
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays MORE or NEXT set of client's objects profiles.
    * @param c Component object that represents the GUI the current component
    * @param event ActionEvent object that represents the GUI the current triggered event action
    */
    @Override
    protected void onEditDeviceForm_NextButAction(Component c, ActionEvent event)
    {
        //Increment the counter
        device_contactNumberCounter++; 
       // DisplayDeviceProfile("Next");
        ShowDeviceData(c.getComponentForm());
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays PREVIOUS set of client's objects profiles.
    * @param c Component object that represents the GUI the current component
    * @param event ActionEvent object that represents the GUI the current triggered event action
    */
    @Override
    protected void onEditDeviceForm_PreButAction(Component c, ActionEvent event)
    {
        //Decrement the counter
        device_contactNumberCounter--;
        //DisplayDeviceProfile("Prev");
        ShowDeviceData(c.getComponentForm());
    }
    /**
    * This is an action event overriding method that calls a method which  updates,retrieves and displays client's objects profiles.
    * @param c Component object that represents the GUI the current component
    * @param event ActionEvent object that represents the GUI the current triggered event action
    */
    @Override
    protected void onEditDeviceForm_SaveButtAction(Component c, ActionEvent event)
    {
        //the carry out the update remotely and locally
        DisplayDeviceProfile("false");
    }
    /**
    * This is an action event overriding method that navigates the client to a form where he/she can Sign in.
    */
    @Override
    protected boolean onEditRegInformFormLogout()
    {
        showForm("Login_Form",null);
        return true;
    }
    /**
    * This is an action event overriding method that refreshes and re-loads client's security alerts when he/she is viewing personal data..
    */
    @Override
    protected boolean onEditRegInformFormInbox()
    {
        System.out.println( "This is mapform that is about to show");
        //gets the stores Alert List object
        lstmvc=StateMachine.getListMVCObj();
        //re-populates the Lists with the sms alerts.
        lstmvc.buildAll(); 
        return true;
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays client's objects profiles.
    */
    @Override
    protected boolean onEditRegInformFormDevice()
    {
        DisplayDeviceProfile("true");
        return true;
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays client's contacts profiles.
    */
    @Override
    protected boolean onEditRegInformFormContacts()
    {
        DisplayContactProfile("true");
        return true;
    }
    /**
    * This is an action event overriding method that LOGS OUT and navigates the client-again- to the Sign in Form page of this application.
    */
    @Override
    protected boolean onEditDeviceFormLogout()
    {
        showForm("Login_Form",null);
        return true;
    }
    /**
    * This is an action event overriding method that refreshes and re-loads client's security alerts when he/she is viewing device data.
    */
    @Override
    protected boolean onEditDeviceFormInbox()
    {
        System.out.println( "This is mapform that is about to show11");
        //gets the stores Alert List object
        lstmvc=StateMachine.getListMVCObj();
        //re-populates the Lists with the sms alerts.
        lstmvc.buildAll(); 
        return true;
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays client's contacts profiles.
    */
    @Override
    protected boolean onEditDeviceFormContacts()
    {
        DisplayContactProfile("true");
        return true;
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays client's personal profiles.
    */
    @Override
    protected boolean onEditDeviceFormProfile()
    {
        DisplayUserProfile(true);
        return true;
    }
    /**
    * This is an action event overriding method that LOGS OUT and navigates the client-again- to the Sign in Form page of this application.
    */
    @Override
    protected boolean onEditContactFormLogout()
    {
        showForm("Login_Form",null); 
        return true;
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays client's objects profiles.
    */
    @Override
    protected boolean onEditContactFormDevice()
    {
        DisplayDeviceProfile("true");
        return true;
    }
    /**
    * This is an action event overriding method that refreshes and re-loads client's security alerts when he/she is viewing Contacts data.
    */
    @Override
    protected boolean onEditContactFormInbox()
    {
        System.out.println( "This is alert form that is about to show111");
        //gets the stored Alert List object
        lstmvc=StateMachine.getListMVCObj();
        //re-populates the Lists with the sms alerts.
        lstmvc.buildAll();  
        return true;
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays client's personal profiles.
    */
    @Override
    protected boolean onEditContactFormProfile()
    {
        DisplayUserProfile(true);
        return true;
    }
    /**
    * This is an action event overriding method that LOGS OUT and navigates the client-again- to the Sign in Form page of this application.
    */
    @Override
    protected boolean onEditLoginLogout()
    {
        showForm("Login_Form",null);  
        return true;
    }
    /**
    * This is an action event overriding method that refreshes and re-loads client's security alerts when he/she is editing his/her Login data data.
    */
    @Override
    protected boolean onEditLoginInbox()
    {
        System.out.println( "This is an alert form that is about to show");
        //gets the stored Alert List object
        lstmvc=StateMachine.getListMVCObj();
        //re-populates the Lists with the sms alerts
        lstmvc.buildAll();     
        return true;
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays client's personal profiles.
    */
    @Override
    protected boolean onEditLoginProfile()
    {
        DisplayUserProfile(true);  
        return true;
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays client's contacts profiles.
    */
    @Override
    protected boolean onEditLoginContacts()
    {
        DisplayContactProfile("true");
        return true;
    }
    /**
    * This is an action event overriding method that calls a method which retrieves and displays client's objects profiles.
    */
    @Override
    protected boolean onEditLoginDevice()
    {
        DisplayDeviceProfile("true");  
        return true;
    }
    private void ShowUserData(Form f)
    {
        //retrieves the biodata from the client's mobile device.
        String []userdata1=sqldb.getClientsBiodataStoredInfor();
        //Log.p("Info: " +"THE USER PROFILE NAME from sqlitedb : "+userdata1[0]);
        //Log.p("Info: " +"THE USER PROFILE EMAIL ADDRESS from sqlitedb : "+userdata1[1]);
        //get all the necessary text field components from the Edit_Reg_Inform_Form
        status=findStatusLab(f);
        clientname=findNameTF(f);
        mobilenumber=findMobTF(f);
        email1=findEmailTF(f);
        add=findAddTA(f);//get the address field component
        state=findStateTF(f);
        lga=findLgaTF(f);
        //display the appropriate status message
        status.setText("Here are "+userdata1[0]+"'s Personal Data");
        //display all the appropriate biodata info to their appropriate text fields.
        clientname.setText(userdata1[0]);
        email1.setText(userdata1[1]);
        email1.setEditable(false);
        mobilenumber.setEditable(false);
        mobilenumber.setText(userdata1[2]);
        add.setText(userdata1[3]);
        state.setText(userdata1[4]);
        lga.setText(userdata1[5]);
    }
    public void ShowContactsData(Form f)
    {
        //get the row size
        String rowSize=(String)Storage.getInstance().readObject("ContactsRowSize");
        RetrievedRowSize=Integer.parseInt(rowSize);
        //Retrieve the contacts data locally
        //Retrieve the biodata from the client's mobile device.
        System.out.println("The counter value after clicking NEXT or PREVIOUS="+device_contactNumberCounter);
        userdata=sqldb.getClientsContactsBiodataStoredInfor(device_contactNumberCounter);
        Log.p("Info: " +"THE contact PROFILE NAME from sqlitedb : "+userdata[0]);
        Log.p("Info: " +"THE contact PROFILE EMAIL ADDRESS from sqlitedb : "+userdata[1]);
        //Tells the thread's run method in StateMachine, to navigate to client's contact's profile page and display his/her contacts profile
        //get all the necessary text field component from the Edit_Contact_Form
        next=findNextBut(f);
        prev=findPreBut(f);
        status=findStatusLab(f);
        clientname=findNameTF(f);
        mobilenumber=findMobTF(f);
        email1=findEmailTF(f);
        add=findAddTA(f);
        rela=findRelaTF(f);
        Log.p("My rowsize="+RetrievedRowSize);
        Log.p("My counter="+device_contactNumberCounter);
        int currentcount=JSONConnectionObject.device_contactNumberCounter+1;
        status.setText("Your Contacts' Data:Row "+currentcount+" of "+JSONConnectionObject.RetrievedRowSize);
        clientname.setText(userdata[0]);
        email1.setText(userdata[1]);
        mobilenumber.setText(userdata[2]);
        add.setText(userdata[3]);
        rela.setText(userdata[4]);
        //Is this the ONLY contacts profile in the array and does the array  not contain other profiles?
        if(device_contactNumberCounter==0&&RetrievedRowSize==1)
        {
             //Then disable the previous and next since there is ONLY 1 item to view.
            prev.setEnabled(false);
            next.setEnabled(false);
        }
        //Is this the first contacts profile in the array and does the array contain other profiles?
       else if(device_contactNumberCounter==0&&RetrievedRowSize!=1)
        {
             //Then disable the previous since there are no previous items to view.
            prev.setEnabled(false);
        }
        //Is the current contacts profile the last in the array of contacts profiles?
        else if(device_contactNumberCounter+1==RetrievedRowSize &&RetrievedRowSize!=1)
        {
            //then enable the previous and disable the next button(since there is no item to view next)
            prev.setEnabled(true);
            next.setEnabled(false);
        }
        //Is the current contacts profile neither the last nor the first in the array of contacts profiles?
        else if(device_contactNumberCounter<RetrievedRowSize&&RetrievedRowSize!=1)
        {
            //then enable both buttons since the client can view either next or previous items in this case.
            prev.setEnabled(true);
            next.setEnabled(true);
        }
    }
    public void ShowDeviceData(Form f)
    {
        //get the device/object rowsize as stored locally
        String rowSize=(String)Storage.getInstance().readObject("DeviceRowSize");
        RetrievedRowSize=Integer.parseInt(rowSize); 
        userdata=sqldb.getClientsDevice_ObjectStoredInfor(JSONConnectionObject.device_contactNumberCounter);
        Log.p("Info: " +"THE Device PROFILE NAME from sqlitedb : "+userdata[0]);
        Log.p("Info: " +"THE Device PROFILE build_type from sqlitedb : "+userdata[1]);
        //get all the necessary text field component from the Edit_Device_Form
        status=findStatusLab(f);
        next=findNextBut(f);
        prev=findPreBut(f);
        clientname=findDevNameTF(f);
        descrpt=findDesTA(f);
        build_type=findBuildTypeTF(f);//get the building type field component
        loc=findLocTF(f);//get the location field component
        add=findAddTA2(f);//get the address field component
        state=findStateTF(f);
        lga=findLgaTF(f);
        prev.setEnabled(false);
        next.setEnabled(false);
        Log.p("The user data="+userdata[0]);
        //get the current index of the device profile in the array that stores all the device profiles/biodata.
        int currentcount=JSONConnectionObject.device_contactNumberCounter+1;
        //display it as a status message in the form: e.g: 1 of 5  where 1=index; 5=total number of device profile
        status.setText("Your Object's Data:Row "+currentcount+" of "+JSONConnectionObject.getRetrievedRowSize());
        //display all the appropriate biodata info to their corresponding text fields.
        clientname.setText(userdata[0]);
        descrpt.setText(userdata[3]);
        add.setText(userdata[4]);
        build_type.setText(userdata[1]);
        loc.setText(userdata[2]);
        state.setText(userdata[5]);
        lga.setText(userdata[6]);
        Log.p("My rowsize="+RetrievedRowSize);
        Log.p("My counter="+device_contactNumberCounter);
        //Is this the ONLY device profile in the array and does the array  not contain other profiles?
        if(device_contactNumberCounter==0&&RetrievedRowSize==1)
        {
             //Then disable the previous and next since there is ONLY 1 item to view.
            prev.setEnabled(false);
            next.setEnabled(false);
        }
        //Is this the first device profile in the array and does the array contain other profiles?
        if(device_contactNumberCounter==0&&RetrievedRowSize!=1)
        {
            //Then disable the previous since there are no previous items to view.
            prev.setEnabled(false);
        }
        //Is the current device/object profile the last in the array of object profiles?
        else if(device_contactNumberCounter+1==RetrievedRowSize &&RetrievedRowSize!=1)
        {
            //then enable the previous and disable the next button(since there is no item to view next)
            prev.setEnabled(true);
            next.setEnabled(false);
        }
        //Is the current device/object profile neither the last nor the first in the array of object profiles?
        else if(device_contactNumberCounter<RetrievedRowSize&&RetrievedRowSize>1)
        {
            //then enable both buttons since the client can view either next or previous items in this case.
            prev.setEnabled(true);
            next.setEnabled(true);
        }
    }
    @Override
    protected void beforeEditRegInformForm(Form f) 
    {
        ShowUserData(f);
    }
    @Override
    protected void beforeEditDeviceForm(Form f)
    {
        ShowDeviceData(f);
    }

    @Override
    protected void beforeEditContactForm(Form f)
    {
        ShowContactsData(f);
    }

    @Override
    protected void onRegForm_MobTFAction(Component c, ActionEvent event)
    {
        //gets the email address in question
        String gsm=findMobTF(c.getComponentForm()).getText();
        //Has the web service been connected to intially?
        if(jscnb==null)
        {
            JSONConnectionObject  jcb=new JSONConnectionObject(url);
            setJSONConnObj(jcb);
            jscnb=jcb;
        }
        //get a status message component
        status=findStatusLab(c.getComponentForm());
        gsmtxtfield=findMobTF(c.getComponentForm());
        //Set the type of verification to be email since you are checking whether an email address exists or not.
        String type="gsm";
        //Has this email been registered already?
        if(jscnb.checkEmailAddress_OR_GSM_OR_Device_ID_BeforeRegistration(gsm,type))
        {
        }
        else
        {
             
        }
    }
}