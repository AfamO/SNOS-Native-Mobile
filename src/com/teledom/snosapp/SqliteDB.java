/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
* afamsimon@gmail.com
*/
package com.teledom.snosapp;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.ui.Display;
import java.io.IOException;
/**
*
* @author Tele
*/
public class SqliteDB
{
    private Database para_db;
    private String DB_NAME="Tele_snos.db";
    public String [][]all_inbox;
    String []inbox=new String[4];
    String []inboxids=new String[20];
    private int InboxCount=0;
    private int alertCounter=0;
    public static boolean Isdbdropped=false;
    public SqliteDB()
    {
        //DropSqliteDBandTable();
        if(Isdbdropped)
        {
            DropSqliteSMSAlertsTable();
            Isdbdropped=false;
        }
        //CreateSqliteSMSAlertsTableOnly();
        CreateSqliteDBAndTables();
    }
    public boolean IsDBOpnened()
    {
        boolean Isopened=false;
        try
        {
            para_db =  Database.openOrCreate(DB_NAME);
        }
        catch (IOException ex)
        {
            System.out.println(" Error: DB cannot open since SQLITE is not supported\nThank you. ");
        }
        if(para_db==null)
        {
            Isopened=false;
        }
        else
        {
            boolean Isexists = Database.exists(DB_NAME);
            //para_db=null;
            // String path2 = com.codename1.db.Database.getDatabasePath("MyDB.db");
            String path=Display.getInstance().getDatabasePath(DB_NAME);
            System.out.println(path);
            if(!Isexists)
            {
               Isopened=false;
               System.out.println(" Error: DB is not created nor opened because ");
               System.out.println(" Error: It does Not exists ");
            }
            else
            {
                Isopened=true;
                System.out.println(" Error: DB is  Already created ");
                System.out.println(" Error: And thus exists");
            }
        }
        return Isopened;
    }
    /**
    * This method creates the database and all the needed database tables
    **/
    public final void CreateSqliteDBAndTables()
    {
        //create database
        try
        {
            //check if db exists
            boolean Isexists = Database.exists(DB_NAME);
            //Try to open the db or create it if it does not exists
            para_db = Database.openOrCreate(DB_NAME);
            //Does this device support sqlite
            if(para_db == null)
            {
                System.out.println("SQLite is not supported on this platform, writing on internal storage...");
            }
            else
            {
                //then sqlite is supported in this mobile device.
                //if the db does not exists already, go ahead and create the needed tables.
                if (!Isexists)
                {
                    //create all the needed tables since the database would have been created before entering this block.
                    para_db.execute("create table snos_alert (id INTEGER PRIMARY KEY,sms_alert text,date1 text);");
                    para_db.execute("create table clientbiodata (id INTEGER PRIMARY KEY,name text,email1 text,fone text,contact text,state text,lga text);");
                    para_db.execute("create table clientdevicedata (id INTEGER PRIMARY KEY,name text,build_type text,descrpt text,location text,contact text,state text,lga text,counter_id text,table_id text);");
                    para_db.execute("create table clientcontactsdata (id INTEGER PRIMARY KEY,name text,relat_pos text,fone text,email1 text,contact text,counter_id text,table_id text);");
                    Log.p("sqlite DB and Tables Created successfully");
                    String path=Display.getInstance().getDatabasePath(DB_NAME);
                    System.out.println("The created DB path is:"+path);
                    //initialize alert counter internal storage to be 0 since no sms alerts have been inserted so far.
                    Storage.getInstance().writeObject("alertcounter","0");
                    System.out.println("The alertcounter is:"+Storage.getInstance().readObject("alertcounter").toString());
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Sqlite Error "+e.getMessage());
        }
        //close db connection if any
        finally
        {
            try
            {
                if(para_db!=null)
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
            }
            catch (IOException ex)
            {
                
            }
        }
        
    }
    public final void CreateSqliteSMSAlertsTableOnly()
    {
        //create database
        try
        {
            //check if db exists
            boolean Isexists = Database.exists(DB_NAME);
            //Try to open the db or create it if it does not exists
            para_db = Database.openOrCreate(DB_NAME);
            //Does this device support sqlite
            if(para_db == null)
            {
                System.out.println("SQLite is not supported on this platform, writing on internal storage...");
            }
            else
            {
                //then sqlite is supported in this mobile device.
                //if the db  exists already, go ahead and create the needed table.
                if (Isexists)
                {
                    //create all the needed tables since the database would have been created before entering this block.
                    para_db.execute("create table snos_alert (id INTEGER PRIMARY KEY,sms_alert text,date1 text);");
                    Log.p("sqlite SMS ALerts Table Created successfully");
                    String path=Display.getInstance().getDatabasePath(DB_NAME);
                    System.out.println("The created DB path is:"+path);
                    //initialize alert counter internal storage to be 0 since no sms alerts have been inserted so far.
                    Storage.getInstance().writeObject("alertcounter","0");
                    System.out.println("The alertcounter is:"+Storage.getInstance().readObject("alertcounter").toString());
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Sqlite Error "+e.getMessage());
        }
        //close db connection if any
        finally
        {
            try
            {
                if(para_db!=null)
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
            }
            catch (IOException ex)
            {
                
            }
        }
        
    }
    public void AddSqliteSmSAlertsInfor(String [] sms_alerts,String [] receive_time)
    {
        try
        {
            //check if db exists
            boolean Isexists = Database.exists(DB_NAME);
            //Try to open the db
            para_db =Database.openOrCreate(DB_NAME);
           // display its path.
            String path=Display.getInstance().getDatabasePath(DB_NAME);
            System.out.println(path);
            //Is sqlite supported
            if(!IsDBOpnened())
            {
                System.out.println("SQLite is not supported on this platform, writing on internal storage...");
                for(int i=0;i<sms_alerts.length;i++)
                {
                    Storage.getInstance().writeObject("alert_"+i, sms_alerts[i]);
                    Storage.getInstance().writeObject("time_"+i, receive_time[i]);
                    alertCounter=Integer.parseInt( Storage.getInstance().readObject("alertcounter").toString());
                    System.out.println("AlertCounter on internal storage="+alertCounter);
                    alertCounter++;
                    Storage.getInstance().writeObject("alertcounter", Integer.toString(alertCounter));
                    System.out.println("sqlite Info Inserted successfully on internal storage"); 
                }
                return;
            }
            if (Isexists)
            {
                for (int i = 0; i < sms_alerts.length; i++)
                {
                    //Is any alert equals null
                    if(sms_alerts[i]!=null||! sms_alerts[i].equalsIgnoreCase(""))
                    {
                        para_db.execute("insert into snos_alert (sms_alert,date1) values (?,?);", new String[]{sms_alerts[i], "" + receive_time[i]});
                        alertCounter=Integer.parseInt( Storage.getInstance().readObject("alertcounter").toString());
                        alertCounter++;
                        Storage.getInstance().writeObject("alertcounter", Integer.toString(alertCounter));
                    }
                    else
                    {
                        System.out.println("sms empty alert after localdb insertion= "+sms_alerts[i]);
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Sqlite Error "+e.getMessage());
        }
        //close db connection if any
        finally
        {
            try
            {
                if(para_db!=null)
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
            }
            catch (IOException ex)
            {
                
            }
        }
        
        
    }
    public void AddClientBiodataInfor(String[] biodata)
    {
        try
        {
            //check if db exists
            boolean Isexists = Database.exists(DB_NAME);
            //Try to open the db
            para_db =Database.openOrCreate(DB_NAME);
           // display its path.
            String path=Display.getInstance().getDatabasePath(DB_NAME);
            System.out.println(path);
            //Is sqlite supported
            if(!IsDBOpnened())
            {
                //then it is not supported
                System.out.println("SQLite is not supported on this platform, writing on internal storage...");
                Storage.getInstance().writeObject("client_name", biodata[0]);
                Storage.getInstance().writeObject("client_email1", biodata[1]);
                Storage.getInstance().writeObject("client_fone", biodata[2]);
                Storage.getInstance().writeObject("client_contact", biodata[3]);
                Storage.getInstance().writeObject("client_state", biodata[4]);
                Storage.getInstance().writeObject("client_lga", biodata[5]);
                Log.p("sqlite Client biodata Inserted successfully on internal storage"); 
                return;
            }
            if (Isexists)
            {
                //then the db exists and  sqlite  is  thus supported.
                para_db.execute("insert into clientbiodata (name,email1,fone,contact,state,lga) values (?,?,?,?,?,?);", new String[]{biodata[0], "" + biodata[1],"" + biodata[2],"" + biodata[3],"" + biodata[4],"" + biodata[5]});
                System.out.println("sqlite client biodata Inserted successfully ");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Sqlite Error "+e.getMessage());
        }
        //close db connection if any
        finally
        {
            try
            {
                if(para_db!=null)
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
            }
            catch (IOException ex)
            {
                
            }
        }
        
        
    }
    public void AddClientDevice_ObjectInfor(String[] devicedata)
    {
          
        try {
            boolean Isexists = Database.exists(DB_NAME);
            para_db =Database.openOrCreate(DB_NAME);
           // String path2 = com.codename1.db.Database.getDatabasePath("MyDB.db");
            String path=Display.getInstance().getDatabasePath(DB_NAME);
            System.out.println(path);
            //Is sqlite supported
            if(!IsDBOpnened())
            {
                //gets the counter id
                int counter_id=Integer.parseInt(devicedata[7]);
                //make each device data row to be found at position counter_id or i which should be unique for each row of device data
                int i=counter_id;
                System.out.println("SQLite is not supported on this platform, writing on internal storage...");
                Storage.getInstance().writeObject("device_name_"+i, devicedata[0]);
                Storage.getInstance().writeObject("device_build_type_"+i, devicedata[1]);
                Storage.getInstance().writeObject("device_descrpt_"+i, devicedata[2]);
                Storage.getInstance().writeObject("device_location_"+i, devicedata[3]);
                Storage.getInstance().writeObject("device_contact_"+i, devicedata[4]);
                Storage.getInstance().writeObject("device_state_"+i, devicedata[5]);
                Storage.getInstance().writeObject("device_lga_"+i, devicedata[6]);
                Storage.getInstance().writeObject("device_counter_id_"+i, devicedata[7]);
                Storage.getInstance().writeObject("device_table_id_"+i, devicedata[8]);
                Log.p("sqlite Client Devicedata Info Inserted successfully on internal storage"); 
                return;
            }
            //does the table or db already exists?
            if (Isexists)
            {
                para_db.execute("insert into clientdevicedata (name,build_type,descrpt,location,contact,state,lga,counter_id,table_id) values (?,?,?,?,?,?,?,?,?);", new String[]{devicedata[0], "" + devicedata[1],"" + devicedata[2],"" + devicedata[3],"" + devicedata[4],"" + devicedata[5],"" + devicedata[6],"" + devicedata[7],"" + devicedata[8]});
                Log.p("sqlite Client DeviceInfo Inserted successfully ");
            }
            else
            {
                
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Sqlite Error "+e.getMessage());
        }
        //close db connection if any
        finally
        {
            try
            {
                if(para_db!=null)
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
            }
            catch (IOException ex)
            {
                
            }
        }
    }
    public void AddClientsContactsInfor(String[] contactsdata)
    {
        try
        {
            boolean Isexists = Database.exists(DB_NAME);
            para_db =Database.openOrCreate(DB_NAME);
            // String path2 = com.codename1.db.Database.getDatabasePath("MyDB.db");
            String path=Display.getInstance().getDatabasePath(DB_NAME);
            System.out.println(path);
            if(!IsDBOpnened())
            {
                //gets the counter id
                int counter_id=Integer.parseInt(contactsdata[5]);
                //make each contact data row to be found at position counter_id or i which should be unique for each row of contact data
                int i=counter_id;
                System.out.println("SQLite is not supported on this platform, writing on internal storage...");
                Storage.getInstance().writeObject("contacts_name_"+i, contactsdata[0]);
                Storage.getInstance().writeObject("contacts_relat_pos_"+i, contactsdata[1]);
                Storage.getInstance().writeObject("contacts_fone_"+i, contactsdata[2]);
                Storage.getInstance().writeObject("contacts_email1_"+i, contactsdata[3]);
                Storage.getInstance().writeObject("contacts_contact_"+i, contactsdata[4]);
                Storage.getInstance().writeObject("contacts_counter_id_"+i, contactsdata[5]);
                Storage.getInstance().writeObject("contacts_table_id_"+i, contactsdata[6]);
                Log.p("sqlite Client Contacts data Inserted successfully on internal storage"); 
                return;
            }
            //does the table or db already exists?
            if(Isexists)
            {
                para_db.execute("insert into clientcontactsdata (name,relat_pos,fone,email1,contact,counter_id,table_id) values (?,?,?,?,?,?,?);", new String[]{contactsdata[0], "" + contactsdata[1],"" + contactsdata[2],"" + contactsdata[3],"" + contactsdata[4],"" + contactsdata[5],"" + contactsdata[6]});
                Log.p("sqlite Client Contacts Info Inserted successfully ");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Sqlite Error "+e.getMessage());
        }
        //close db connection if any
        finally
        {
            try
            {
                if(para_db!=null)
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
            }
            catch (IOException ex)
            {
                
            }
        }
    }
    public final  boolean DropSqliteSMSAlertsTable()
    {
        //drop alert table in the database 
        boolean Isdroped=false;
        try 
        {
            //Is sqlite supported?
            if(!IsDBOpnened())
            {
                //since sqlite is not supported, delete the sms alerts and their time/dates in internal storage
                int alertsNumber=this.getLocalAlertsNumber();
                System.out.println("SQLite is not supported on this platform, deleting/droping from internal storage...");
                Storage.getInstance().writeObject("alertcounter", Integer.toString(0));
                for(int i=0;i<alertsNumber;i++)
                {
                    Storage.getInstance().deleteStorageFile("alert_"+i);
                    Storage.getInstance().deleteStorageFile("time_"+i);
                    Isdroped=true;
                }
            }
            else
            {
                //sqlite is supported
                //drop the sms alert table
                para_db.execute("drop table snos_alert");
                Isdroped=true;
                System.out.println("This sqlite DB wAs Droped successfully"); 
                Storage.getInstance().writeObject("alertcounter", Integer.toString(0));
            }
        }
        catch(IOException e)
        {
            System.out.println("Sqlite Error:DB could not be dropped because of: "+e.getMessage());
            Isdroped=false;
            e.printStackTrace();
        }
        return Isdroped;
    }
   
    
    public boolean IsSMSStoredLocally()
    {
        //make needed initializations
        Cursor c = null;
        boolean exists=false;
        //Is sqlite supported?
        if(IsDBOpnened())
        {
            //sqlite is supported
            try
            {
                para_db = Database.openOrCreate(DB_NAME);
                //query the db
                c = para_db.executeQuery("select * from snos_alert");
                if(c==null)
                {
                    //no items found
                    c = null;
                    System.out.println("==========SQLite data info does Not exist Already=========");
                }
                else
                {
                    //items are found
                    if (c.next())
                    {
                        exists=true;
                    }
                    System.out.println("==========SQLite data info exists Already=========");
                    System.out.println("SMS Alerts"+" "+"Time Received");
                }
            }
            catch(IOException e)
            {
                exists=false;
                c = null;
                System.out.println("Sqlite Selection Error "+e.getMessage());
            }
            //close db connection if any
            finally
            {
                try
                {
                    if(para_db!=null)
                    {
                        para_db.close();
                        System.out.println("SqliteDB has JUST  CLOSED ");
                    }
                }
                catch (IOException ex)
                {

                }
            }
         }
         else
         {
             //sqlite is not supported
             System.out.println("SQLite is not supported on this platform, reading from internal storage...");
             System.out.println("SMS Alerts"+" "+"Time Received");
             //Is there an laert found in storage:alert_0 ?
             if((String)Storage.getInstance().readObject("alert_"+0)!=null)
             {
                 //An alert was found.
                 exists=true;
             }
             else
             {
                 //An alert was NOT found.
                 c=null;
             }
         }
         return exists;
    }
    public int getLocalAlertsNumber()
    {
        int number=0;
        Cursor c = null;
        //Is sqlite supported
        if(IsDBOpnened())
        {
            //it is supported
            try
            {
                para_db = Database.openOrCreate(DB_NAME);
                System.out.println("==========SQLite supporteddddd=========");
                c = para_db.executeQuery("select * from snos_alert");
                if(c==null)
                {
                    c = null;
                    System.out.println("==========SQLite data info does Not exist Already=========");
                }
                else
                {
                    while (c.next())
                    {
                        number++;
                    }
                }
            }
            catch(IOException e)
            {

            }
            //close db connection if any
            finally
            {
                try
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
                catch (IOException ex)
                {

                }
            }
        }
        else
        {
            //sqlite is not supported
            System.out.println("SQLite is not supported on this platform, reading from internal storage...");
            //read the total alerts number that is stored by this mobile device.  
            number=Integer.parseInt( Storage.getInstance().readObject("alertcounter").toString());
        }
        //return the number red.
        return number;
    }
    public String[][] getStoredSmsAlerts()
    {
        //return the alert array inbox.
        return all_inbox;
    }
    
    
     public void getSmsAlertsStoredInfor(int min,int max)
     {
         if(IsDBOpnened())//use it to test iNternal storage:I mean is sqlite supported in this device?
         {
             //sqlite is supported
             try
             {
                 para_db = Database.openOrCreate(DB_NAME);
                 //query the db 
                 Cursor c = para_db.executeQuery("select * from snos_alert ORDER BY date1  DESC LIMIT "+min+","+max);
                 if(c==null)
                 {
                     //items not found
                     System.out.println("==========SQLite Error: Cursor obj is null=========");
                 }
                 else
                 {
                     //items found
                     System.out.println("==========SQLite Information Testing=========");
                     System.out.println("SMS Alerts"+" "+"Time Received");
                     all_inbox=new String[10][4];
                     InboxCount=0;
                     while (c.next())
                     {
                         Row r = c.getRow();
                         int id = r.getInteger(0);
                         String sms_alerts2 = r.getString(1);
                         String date2=r.getString(2);
                         //System.out.println("Retrieved Data is : "+sms_alerts2+" "+date2);
                         inbox[0]=Integer.toString(id);
                         inbox[1]=sms_alerts2;
                         inbox[2]=date2;
                         inbox[3]="Action";
                         all_inbox[InboxCount][0]=Integer.toString(id);
                         all_inbox[InboxCount][1]=sms_alerts2;
                         all_inbox[InboxCount][2]=date2;
                         all_inbox[InboxCount][3]="Action";
                         //System.out.println("the alert table id at count "+InboxCount+" is="+inbox[0]);
                         InboxCount++;
                     }
                     System.out.println("The row count======="+InboxCount);
                 }
             }
             catch(IOException e)
             {
                 System.out.println("Sqlite Selection Error "+e.getMessage());
             }
             //close db connection if any
             finally
             {
                 try
                 {
                     para_db.close();
                     System.out.println("SqliteDB has JUST  CLOSED ");
                 } 
                 catch (IOException ex)
                 {
                     
                 }
             }
         }
         else
         {
             //sqlite is not supported
             all_inbox=new String[10][4];
             InboxCount=0;
             int totalAlertsNumber=this.getLocalAlertsNumber();
             System.out.println("SQLite is not supported on this platform, reading from internal storage...");
             System.out.println("SMS Alerts"+" "+"Time Received");   
             for(int i=min;i<=min+9 && i<totalAlertsNumber;i++)
             {
                 String alert = (String)Storage.getInstance().readObject("alert_"+i);
                 String time=  (String)Storage.getInstance().readObject("time_"+i);
                 System.out.println("Retrieved Data is : "+alert+" "+time);
                 inbox[0]="";
                 inbox[1]=alert;
                 inbox[2]=time;
                 inbox[3]="Action";
                 all_inbox[InboxCount]=inbox;
                 System.out.println("At "+i+" the internal alert id at "+InboxCount+"="+inbox[1]);
                 InboxCount++;
             }
         }
     }
     public String [] getClientsBiodataStoredInfor()
     {
         //declare the biodata value to return later
         String[] biodata=new String[6];
         if(IsDBOpnened())//use it to test iNternalstorage.I mean is sqlite supported in this device?
         {
             //sqlite is supported.
             try
             {
                 //open the db
                 para_db = Database.openOrCreate(DB_NAME);
                 //execute an sqlite query.
                 Cursor c = para_db.executeQuery("select * from clientbiodata");
                 if(c==null)
                 {
                     //items are not found
                    System.out.println("==========SQLite Error: Cursor obj is null=========");
                 }
                 else
                 {
                     //items are found
                     System.out.println("==========SQLite Information Testing=========");
                     System.out.println("sqlite Client Biodata Info Retrieved are:");
                     while (c.next())
                     {
                         Row r = c.getRow();
                         int id = r.getInteger(0);
                         String name = r.getString(1);
                         String email1=r.getString(2);
                         String fone = r.getString(3);
                         String contact=r.getString(4);
                         String state=r.getString(5);
                         String lga=r.getString(6);
                         System.out.println("Retrieved client's Data is EMAIL : "+email1+" fone: "+fone);
                         biodata[0]=name;
                         biodata[1]=email1;
                         biodata[2]=fone;
                         biodata[3]=contact;
                         biodata[4]=state;
                         biodata[5]=lga;
                     }
                 }
             }
             catch(IOException e)
             {
                 System.out.println("Sqlite Selection Error "+e.getMessage());
             }
             //close db connection if any
             finally
             {
                 try
                 {
                     para_db.close();
                     System.out.println("SqliteDB has JUST  CLOSED ");
                 } 
                 catch (IOException ex)
                 {
                     
                 }
             }
         }
         else
         {
             //SQLite is not supported on this platform
             System.out.println("SQLite is not supported on this platform, reading from internal storage...");
             System.out.println("Internally retrieved clients biodata info:");   
             biodata[0]=(String)Storage.getInstance().readObject("client_name");
             biodata[1]=(String)Storage.getInstance().readObject("client_email1");
             biodata[2]=(String)Storage.getInstance().readObject("client_fone");
             biodata[3]=(String)Storage.getInstance().readObject("client_contact");
             biodata[4]=(String)Storage.getInstance().readObject("client_state");
             biodata[5]=(String)Storage.getInstance().readObject("client_lga");
             System.out.println("Retrieved Data is EMAIL : "+biodata[1]+" fone: "+biodata[2]);
         }
         return biodata;
     }
     public String [] getClientsContactsBiodataStoredInfor(int counter_id)
     {
         //declare the biodata value to return later.
         String[] biodata=new String[6];
          
         if(IsDBOpnened())//use it to test iNternalstorage.I mean is sqlite supported in this device?
         {
             //sqlite is supported.
             try
             {
                 //open the db
                 para_db = Database.openOrCreate(DB_NAME);
                 //execute an sqlite query.
                 Cursor c = para_db.executeQuery("select * from clientcontactsdata where counter_id='"+counter_id+"'");
                 if(c==null)
                 {
                     //no items found
                    System.out.println("==========SQLite Error: Cursor obj is null=========");
                 }
                 else
                 {
                     //items found
                     System.out.println("==========SQLite Information Testing=========");
                     System.out.println("sqlite Client Biodata Info Retrieved are:");
                     while (c.next())
                     {
                         Row r = c.getRow();
                         String name = r.getString(1);
                         String relat_pos=r.getString(2);
                         String fone = r.getString(3);
                         String email1=r.getString(4);
                         String contact=r.getString(5);
                         String table_id=r.getString(7);
                         System.out.println("Retrieved contacts data is name : "+name+" relat_pos: "+relat_pos);
                         biodata[0]=name;
                         biodata[1]=relat_pos;
                         biodata[2]=fone;
                         biodata[3]=email1;
                         biodata[4]=contact;
                         biodata[5]=table_id;
                     }
                 }
             }
             catch(IOException e)
             {
                 System.out.println("Sqlite Selection Error "+e.getMessage());
             }
             //close db connection if any
             finally
             {
                 try
                 {
                     para_db.close();
                     System.out.println("SqliteDB has JUST  CLOSED ");
                 } 
                 catch (IOException ex)
                 {
                     
                 }
             }
         }
         else
         {
             //sqlite is unsupported
             //then read the needed info.
             int i=counter_id;
             System.out.println("SQLite is not supported on this platform, reading from internal storage...");
             System.out.println("Internally retrieved clients contacts biodata info:");   
             biodata[0]=(String)Storage.getInstance().readObject("contacts_name_"+i);
             biodata[1]=(String)Storage.getInstance().readObject("contacts_relat_pos_"+i);
             biodata[2]=(String)Storage.getInstance().readObject("contacts_fone_"+i);
             biodata[3]=(String)Storage.getInstance().readObject("contacts_email1_"+i);
             biodata[4]=(String)Storage.getInstance().readObject("contacts_contact_"+i);
             biodata[5]=(String)Storage.getInstance().readObject("contacts_table_id_"+i);
             System.out.println("Retrieved clients contacts Data is EMAIL : "+biodata[1]+" fone: "+biodata[2]);
         }
         return biodata;
     }
     public String [] getClientsDevice_ObjectStoredInfor(int counter_id)
     {
         //declare the biodata value to return later
         String[] biodata=new String[8];
          
         if(IsDBOpnened())//use it to test iNternalstorage: I mean is sqlite supported in this device?
         {
             //sqlite is supported
             try
             {
                 para_db = Database.openOrCreate(DB_NAME);
                 Cursor c = para_db.executeQuery("select * from clientdevicedata where counter_id='"+counter_id+"' ");
                 if(c==null)
                 {
                     System.out.println("==========SQLite Error: Cursor obj is null=========");
                 }
                 else
                 {
                     System.out.println("==========SQLite Information Testing=========");
                     System.out.println("sqlite Client Biodata Info Retrieved are:");
                     while (c.next())
                     {
                         Row r = c.getRow();
                         String name = r.getString(1);
                         String build_type=r.getString(2);
                         String descrpt = r.getString(3);
                         String location = r.getString(4);
                         String contact=r.getString(5);
                         String state=r.getString(6);
                         String lga=r.getString(7);
                         String table_id=r.getString(9);
                         System.out.println("Retrieved client device Data is Name : "+name+" build_type: "+build_type);
                         biodata[0]=name;
                         biodata[1]=build_type;
                         biodata[2]=descrpt;
                         biodata[3]=location;
                         biodata[4]=contact;
                         biodata[5]=state;
                         biodata[6]=lga;
                         biodata[7]=table_id;
                     }
                 }
             }
             catch(IOException e)
             {
                 System.out.println("Sqlite Selection Error "+e.getMessage());
             }
             //close db connection if any
             finally
             {
                 try
                 {
                     para_db.close();
                     System.out.println("SqliteDB has JUST  CLOSED ");
                 } 
                 catch (IOException ex)
                 {
                     
                 }
             }
         }
         //then retrieve from internal storage since sqlite is not supported.
         else
         {
             int i=counter_id;
             System.out.println("SQLite is not supported on this platform, reading from internal storage...");
             System.out.println("Internally retrieved clients biodata info:");   
             biodata[0]=(String)Storage.getInstance().readObject("device_name_"+i);
             biodata[1]=(String)Storage.getInstance().readObject("device_build_type_"+i);
             biodata[2]=(String)Storage.getInstance().readObject("device_descrpt_"+i);
             biodata[3]=(String)Storage.getInstance().readObject("device_location_"+i);
             biodata[4]=(String)Storage.getInstance().readObject("device_contact_"+i);
             biodata[5]=(String)Storage.getInstance().readObject("device_state_"+i);
             biodata[6]=(String)Storage.getInstance().readObject("device_lga_"+i);
             biodata[7]=(String)Storage.getInstance().readObject("device_counter_id_"+i);
             biodata[8]=(String)Storage.getInstance().readObject("device_table_id_"+i);//device_id,device_table_key
             System.out.println("Retrieved Data is EMAIL : "+biodata[1]+" fone: "+biodata[2]);
         }
         return biodata;
         
     }
     public void UpdateClientPersonalBiodata(String[] biodata)
     {
         try
         {
             boolean Isexists = Database.exists(DB_NAME);
             para_db =Database.openOrCreate(DB_NAME);
             //String path2 = com.codename1.db.Database.getDatabasePath("MyDB.db");
             String path=Display.getInstance().getDatabasePath(DB_NAME);
             System.out.println(path);
             //Is sqlite supported
             if(!IsDBOpnened())
             {
                
                System.out.println("SQLite is not supported on this platform, writing on internal storage...");
                Storage.getInstance().writeObject("client_name", biodata[0]);
                Storage.getInstance().writeObject("client_email1", biodata[1]);
                Storage.getInstance().writeObject("client_fone", biodata[2]);
                Storage.getInstance().writeObject("client_contact", biodata[3]);
                Storage.getInstance().writeObject("client_state", biodata[4]);
                Storage.getInstance().writeObject("client_lga", biodata[5]);
                Log.p("sqlite Client Personal Biodatadata Info Updated successfully on internal storage"); 
                return;
            }
            //does the table or db already exists?
            if (Isexists)
            {
                //then update accordingly.
                para_db.execute("update clientbiodata set name='"+biodata[0]+"', email1='"+biodata[1]+"',fone='"+biodata[2]+"',contact='"+biodata[3]+"',state='"+biodata[4]+"',lga='"+biodata[5]+"' where id='"+1+"'");
                Log.p("sqlite Client Personal Biodatadata Updated successfully ");
            }
            else
            {
                
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Sqlite Error "+e.getMessage());
        }
        //close db connection if any
        finally
        {
            try
            {
                if(para_db!=null)
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
            }
            catch (IOException ex)
            {
                
            }
        }
    }
     /**
     * This updates the client's  object/device data locally on the client's mobile phone.
     * @param devicedata String array object that represents the client's object's data.
     * @param counterid int variable that represents the particular row to be updated in the sqlite table.
     */
     public void UpdateClientDevice_ObjectInfor(String[] devicedata,int counterid)
     {
         try
         {
             boolean Isexists = Database.exists(DB_NAME);
             para_db =Database.openOrCreate(DB_NAME);
             //String path2 = com.codename1.db.Database.getDatabasePath("MyDB.db");
             String path=Display.getInstance().getDatabasePath(DB_NAME);
             System.out.println(path);
             //Is sqlite supported
             if(!IsDBOpnened())
             {
                //gets the counter id
                //make each device data row to be found at position counter_id or i which should be unique for each row of device data
                int i=counterid;
                System.out.println("SQLite is not supported on this platform, writing on internal storage...");
                Storage.getInstance().writeObject("device_name_"+i, devicedata[0]);
                Storage.getInstance().writeObject("device_build_type_"+i, devicedata[1]);
                Storage.getInstance().writeObject("device_descrpt_"+i, devicedata[2]);
                Storage.getInstance().writeObject("device_location_"+i, devicedata[3]);
                Storage.getInstance().writeObject("device_contact_"+i, devicedata[4]);
                Storage.getInstance().writeObject("device_state_"+i, devicedata[5]);
                Storage.getInstance().writeObject("device_lga_"+i, devicedata[6]);
                Storage.getInstance().writeObject("device_counter_id_"+i, devicedata[7]);
                Storage.getInstance().writeObject("device_table_id_"+i, devicedata[8]);
                Log.p("sqlite Client Devicedata Info Updated successfully on internal storage"); 
                return;
            }
            //does the table or db already exists?
            if (Isexists)
            {
                //then update accordingly.
                para_db.execute("update clientdevicedata set name='"+devicedata[0]+"', build_type='"+devicedata[1]+"',descrpt='"+devicedata[2]+"',location='"+devicedata[3]+"',contact='"+devicedata[4]+"',state='"+devicedata[5]+"',lga='"+devicedata[6]+"' where counter_id='"+counterid+"'");
                Log.p("sqlite Client DeviceInfo Updated successfully ");
            }
            else
            {
                
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Sqlite Error "+e.getMessage());
        }
        //close db connection if any
        finally
        {
            try
            {
                if(para_db!=null)
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
            }
            catch (IOException ex)
            {
                
            }
        }
    }
    /**
    * This updates the client's contacts data locally on the client's mobile phone.
    * @param devicedata String array object that represents the client's contact data.
    * @param counterid int variable that represents the particular row to be updated in the sqlite table.
    */
    public void UpdateClientsContactsInfor(String[] contactsdata,int counterid)
    {
        try
        {
            boolean Isexists = Database.exists(DB_NAME);
            para_db =Database.openOrCreate(DB_NAME);
            // String path2 = com.codename1.db.Database.getDatabasePath("MyDB.db");
            String path=Display.getInstance().getDatabasePath(DB_NAME);
            System.out.println(path);
            if(!IsDBOpnened())
            {
                //gets the counter id
                int counter_id=counterid;
                //make each contact data row to be found at position counter_id or i which should be unique for each row of contact data
                int i=counter_id;
                System.out.println("SQLite is not supported on this platform, writing on internal storage...");
                Storage.getInstance().writeObject("contacts_name_"+i, contactsdata[0]);
                Storage.getInstance().writeObject("contacts_relat_pos_"+i, contactsdata[1]);
                Storage.getInstance().writeObject("contacts_fone_"+i, contactsdata[2]);
                Storage.getInstance().writeObject("contacts_email1_"+i, contactsdata[3]);
                Storage.getInstance().writeObject("contacts_contact_"+i, contactsdata[4]);
                Storage.getInstance().writeObject("contacts_counter_id_"+i, contactsdata[5]);
                Storage.getInstance().writeObject("contacts_table_id_"+i, contactsdata[6]);
                Log.p("sqlite Client Contacts data updatted successfully on internal storage"); 
                return;
            }
            //does the table or db already exists?
            if(Isexists)
            {
                //then update accordingly
                para_db.execute("update clientcontactsdata set name='"+contactsdata[0]+"', relat_pos='"+contactsdata[1]+"',fone='"+contactsdata[2]+"',email1='"+contactsdata[3]+"',contact='"+contactsdata[4]+"' where counter_id='"+counterid+"'");
                Log.p("sqlite Client Contacts Info Updated successfully ");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Sqlite Error "+e.getMessage());
        }
        //close db connection if any
        finally
        {
            try
            {
                if(para_db!=null)
                {
                    para_db.close();
                    System.out.println("SqliteDB has JUST  CLOSED ");
                }
            }
            catch (IOException ex)
            {
                
            }
        }
    }
    public String [][] searchLocalStoredAlert(String searchItem,int min,int max)
    {
        //Is SQLite  supported?
          if(IsDBOpnened())
          {
              //SQLite is supported on this platform
              try
              {
                  //open database
                  para_db = Database.openOrCreate(DB_NAME);
                  System.out.println("==========Search BEGINS hia1=========="+min);
                  Cursor c = para_db.executeQuery("select * from snos_alert where  sms_alert like'%"+searchItem+"%' ORDER BY date1  DESC LIMIT "+min+","+max);
                  System.out.println("==========The min=========="+min);
                  System.out.println("==========The max=========="+max);
                  if(c==null)
                  {
                      //no item was found
                      System.out.println("==========SQLite Error: SEARCH Cursor obj is null. THUS SEARCH ATTEMPT FAILED=========");
                  }
                  else
                  {
                      if(c.next())
                      {
                          //item was found,PRINT THE DETAILS OUT
                          Log.p("SEARCH ATTEMPT FOR '"+searchItem+"' was SUCCESSFUL");
                          System.out.println("SMS Alerts"+" "+"Time Received");
                          all_inbox=new String[10][4];
                          InboxCount=0;
                          while (c.next())
                          {
                              //get all the info about the alert found.
                              Row r = c.getRow();
                              int id = r.getInteger(0);
                              String sms_alerts2 = r.getString(1);
                              String date2=r.getString(2);
                              System.out.println("search Retrieved Data is : "+sms_alerts2+" "+date2);
                              inbox[0]=Integer.toString(id);
                              inbox[1]=sms_alerts2;
                              inbox[2]=date2;
                              inbox[3]="Action";
                              all_inbox[InboxCount][0]="";
                              all_inbox[InboxCount][1]=sms_alerts2;
                              all_inbox[InboxCount][2]=date2;
                              all_inbox[InboxCount][3]="Action";
                              InboxCount++;
                          }
                      }
                      else
                      {
                          //no item was found marching the search term
                          InboxCount=0;
                          all_inbox=new String[1][4];
                          //return -1 indicating that no match was found for the search term.
                          all_inbox[InboxCount][0]="-1";
                      }
                      System.out.println("The search row count======="+InboxCount);
                  }
              }
              catch(IOException e)
              {
                  System.out.println("Sqlite Selection Error "+e.getMessage());
              }
              //close db connection if any
              finally
              {
                  try
                  {
                      para_db.close();
                      System.out.println("SqliteDB has JUST  CLOSED ");
                  } 
                  catch (IOException ex)
                  {
                     
                  }
              }
          }
          else
          {
              //SQLite is not supported on this platform
              System.out.println("SQLite is not supported on this platform,SEARCHING from internal storage...");
              //get the total number of alerts stored in this device
              int totalAlertsNumber=this.getLocalAlertsNumber();
              all_inbox=new String[totalAlertsNumber][4];
              boolean IsItemfound=false;
              //converts the search term to uppercase-since it is most likely that all the sms alerts will be in upper case any way.Also 
              //this is so that the string method:"contains(String item)" can work effectively.
              String searchTerm=searchItem.toUpperCase();
              for(int i=0;i<totalAlertsNumber;i++)
              {
                  String alert = (String)Storage.getInstance().readObject("alert_"+i);
                  String time=  (String)Storage.getInstance().readObject("time_"+i);
                  //does this particular alert contain the search term?
                  if(alert.indexOf(searchTerm)!=-1)
                  {
                      //found a match value
                      //get all the info about the alert found
                      inbox[0]="";
                      inbox[1]=alert;
                      inbox[2]=time;
                      inbox[3]="Action";
                      all_inbox[InboxCount][0]="";
                      all_inbox[InboxCount][1]=alert;
                      all_inbox[InboxCount][2]=time;
                      all_inbox[InboxCount][3]="Action";
                      //Assign-true that item was found to the boolean
                      IsItemfound=true;
                      System.out.println("The internal matched search  value at col "+i+" and row "+1+" is: " +alert);
                  }
                  System.out.println("At "+i+" the internal alert id at "+InboxCount+"="+alert);
                  InboxCount++;
              }
              //Was any item found
              if(!IsItemfound)
              {
                  //could not find any march
                  InboxCount=0;
                  all_inbox=new String[1][4];
                  //return -1 indicating that no match was found for the search term.
                  all_inbox[InboxCount][0]="-1";
                  System.out.println("Oops! no marched item found for you ");
              }
              else
              {
                  //a march was found.
                  InboxCount=0;
                  all_inbox=new String[1][4];
                  inbox[0]=Integer.toString(1);
                  // afamsimon@gmail.com
                  all_inbox[InboxCount]=inbox;
                  System.out.println("cONGRAT! marched item found for you ");
              }
          }
          return all_inbox;
      }
}