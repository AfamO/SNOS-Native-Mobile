/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teledom.snosapp;

import com.codename1.io.Util;
import com.codename1.io.Externalizable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 *
 * @author user
 */
public class LoginDetailsPersistentStore  implements  Externalizable {
    
    
    private String userName, Password;
    
    private  int Id;
    
   
    public LoginDetailsPersistentStore() 
    {
    }
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    

  
    public int getVersion() {
        return 1;
    }

    
    public void externalize(DataOutputStream out) throws IOException {
        
        Util.writeUTF(userName, out);
        Util.writeUTF(Password, out);
        out.writeInt(Id);
        
    }

    
    public void internalize(int version, DataInputStream in) throws IOException {
        userName = Util.readUTF(in);
        Password = Util.readUTF(in);
        Id = in.readInt();
        
    }


    public String getObjectId() {
        return "LoginDetailsPersistentStore";
    }
    
    
}
