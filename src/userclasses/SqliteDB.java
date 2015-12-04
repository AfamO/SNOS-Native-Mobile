/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Display;
import java.io.IOException;



/**
 *
 * @author Tele
 */
public class SqliteDB {
    
    private Database para_db;
    private String DB_NAME="Tele_snos.db";
    
    SqliteDB(){
        
    }
    
    public void AddSqliteInfor(String [] sms_alerts,String [] receive_time)
    {
        //create database and insert information to it
        try {
            boolean created = Database.exists(DB_NAME);
            para_db = Database.openOrCreate(DB_NAME);
           // String path2 = com.codename1.db.Database.getDatabasePath("MyDB.db");
            String path=Display.getInstance().getDatabasePath(DB_NAME);
            System.out.println(path);
            if(para_db == null){
                System.out.println("SQLite is not supported on this platform");
                return;
            }
            if (!created) {
                para_db.execute("create table snos_alert (id INTEGER PRIMARY KEY,sms_alert text,date1 text);");
                 for (int i = 0; i < sms_alerts.length; i++) {
                     
                  para_db.execute("insert into snos_alert (sms_alert,date1) values (?,?);", new String[]{sms_alerts[i], "" + receive_time[i]});
                  para_db.execute(path);
                }
            }
            }catch(IOException e){
                e.printStackTrace();
             System.out.println("Sqlite Error "+e.getMessage());
            }
        
        
    }
    
    
    
    
    public void SqliteCreate()
    {
      //create database and insert information to it
        try {
            boolean created = Database.exists(DB_NAME);
            para_db = Database.openOrCreate(DB_NAME);
           // String path2 = com.codename1.db.Database.getDatabasePath("MyDB.db");
            String path=Display.getInstance().getDatabasePath(DB_NAME);
            System.out.println(path);
            if(para_db == null){
                System.out.println("SQLite is not supported on this platform");
                return;
            }
            if (!created) {
               // para_db.execute("drop table snos_alert");
               // para_db.execute("delete from snos_alert");
                para_db.execute("create table snos_alert (id INTEGER PRIMARY KEY,sms_alert text,date1 text);");
                 System.out.println("Table Created Successfully");
            }else{
                para_db.execute("drop table snos_alert");
                //para_db.execute("delete from snos_alert");
                para_db.execute("create table snos_alert (id INTEGER PRIMARY KEY,sms_alert text,date1 text);");
                System.out.println("Unable to Created Table");
            }
            }catch(IOException e){
                e.printStackTrace();
             System.out.println("Sqlite Error "+e.getMessage());
            }
          
    }
    
    public void AddSqliteInfor22(String sms_alerts,String receive_time)
    {
        //insert information to created database
        try {
            
           para_db = Database.openOrCreate(DB_NAME);
            
               String sqlitequery = "INSERT into snos_alert"  +"(sms_alert,date1)" +
                   "VALUES('" +sms_alerts + "','" + receive_time + "')";
               
                 para_db.execute(sqlitequery);
                 
                
            }catch(IOException e){
                e.printStackTrace();
             System.out.println("SQL Statment Error "+e.getMessage());
            }
        
        
    }
    
    
   
     public void getStoredInfor()
     {
         //select stored information and display it 
         try{
             para_db = Database.openOrCreate(DB_NAME);
             
           Cursor c = para_db.executeQuery("select * from snos_alert"); 
           
            System.out.println("==========SQLite Information Testing=========");
             System.out.println("SMS Alerts"+" "+"Time Received");
            
            while (c.next()) {
            Row r = c.getRow();
            int id = r.getInteger(0);
            String sms_alerts2 = r.getString(1);
            String date2=r.getString(2);
            System.out.println(id+" "+sms_alerts2+" "+date2);
            
            }
         }catch(IOException e){
            System.out.println("Sqlite Selection Error "+e.getMessage());
         }finally {
              try{
                 para_db.close();  
              }catch(IOException e){
                System.out.println("unable to close database Error "+e.getMessage());
              }
            
         }
          
     }
     
     
     
     public void  DeleteSqliteRowInfor(String snos_type) {
        
      try {
           para_db = Database.openOrCreate(DB_NAME);
            
            String delquery = " delete FROM snos_alert where snos_type='"+snos_type+"' " ;
              para_db.execute(delquery);
          
            
          }catch (Exception exc) {
            
            System.out.println("Sqlite Delete Row Error" +exc.toString());
            
         }
           
   }
      
     
     public void  DeleteAllSqliteInfor() {
        
      try {
           para_db = Database.openOrCreate(DB_NAME);
            
            String delquery = " delete FROM snos_alert " ;
              para_db.execute(delquery);
          
            
          }catch (Exception exc) {
            
           System.out.println(" Delete All Sqlite Error" +exc.toString());
            
         }
           
   }
     
     public void  UpdateSqlitedb(String sms_alerts,String receive_time,int id2) {
     
      try {
            para_db = Database.openOrCreate(DB_NAME);

            String upquery = " update snos_alert set sms_alert='"+sms_alerts+"'set date1='"+receive_time+"'where id='"+id2+"' " ;
            
              para_db.execute(upquery);
              
          }catch (Exception exc) {

            System.out.println(" Update Sqlite Error" +exc.toString());

         }


   }
    
}
        