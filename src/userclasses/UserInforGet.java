/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userclasses;

/**
 *
 * @author Tele
 */
public class UserInforGet {
    
    
    private String name;
    private String fone;
    private String contact;
    private String loc;
    private String area;
    private String bulid;
    private String lga;
    private String state;
    private String identity;
    private String ClientPassword;
    private String Client_email;
    private String Contact_email;
    private String sensor;
    private String oname,sname;
    //declaration for adding users
    private String OperaName;
    private String userid;
    private String pass;
    private String status;
     //declaration for adding text Messages
    private String message;
    private String dat;
    private String relation_position;
    private String sessid;
    private String link1;
    private String descrpt;
    
    
    
    UserInforGet(){
        
    }
    UserInforGet(String sname,String oname,String fone,String address,String lga,String state,String email1,String password,String snos){
    SetFone(fone);SetClientOname(oname);SetClientSname(sname);
     SetContact(address);SetLga(lga);SetState(state);
     SetClientEmail(email1);SetClientPassword(password);SetClientEmail(email1);
     
    }
     UserInforGet(String extra_sensor,String nam,String descrpt,String build,String loc,String add,String lga,String state){
     SetSensor(extra_sensor);SetClient(nam);
     SetDescrpt(descrpt);SetBuild(build);SetLoc(loc);
     SetContact(add);SetLga(lga);SetState(state);

    }
     UserInforGet(String extra_contact,String nam,String relat_pos,String add,String email1,String fone){
     SetSensor(extra_contact);SetClient(nam);SetRelation_Position(relat_pos);SetContact(add);
     SetClientEmail(email1);SetFone(fone);
     

    }
    
    UserInforGet(String senName,String fon){
     SetSensor(senName);SetFone(fon);
    }
    UserInforGet(String senName){
     SetSensor(senName);
    }
    
    UserInforGet(String nam,String id3,String pv3,String st2){
    SetOperaName(nam);SetUserid(id3);SetPass(pv3);SetStatus(st2);
    }
    
    //set methods for the Information
    public void SetClient(String m){name=m;}
    public void SetClientOname(String fn){oname=fn;}
    public void SetClientSname(String sn){sname=sn;}
    public void SetFone(String fo){fone=fo;}
    public void SetContact(String con){contact=con;}
    public void SetLoc(String lo){loc=lo;}
    public void SetArea(String a){area=a;}
    public void SetBuild(String bu){bulid=bu;}
    public void SetLga(String lg){lga=lg;}
    public void SetState(String sta){state=sta;}
     public void SetIdentity(String ident){identity=ident;}
    public void SetClientPassword(String pass){ClientPassword=pass;}
    public void SetClientEmail(String mail){Client_email=mail;}
    public void SetContactEmail(String mail2){Contact_email=mail2;}
    
     public void SetSensor(String sense){ sensor=sense; }
     public void SetOperaName(String opera){ OperaName=opera; }
     public void SetUserid(String id){ userid=id; }
     public void SetPass(String vas){ pass=vas; }
     public void SetStatus(String stat2){ status=stat2; }
     public void SetMessage(String mes2){ message=mes2; }
     public void SetDate1(String d){dat=d;}
     public void SetRelation_Position(String relatio_pos){this.relation_position=relatio_pos;}
     public void SetSessId(String sessid){this.sessid= sessid;}
     public void SetLink1(String link1){this.link1=link1;}
     public void SetDescrpt(String decr){descrpt=decr;}
    public String getClient(){return name;}
    public String getClientOname(){return oname;}
    public String getClientSname(){return sname;}
    public String getFone(){return fone;}
    public String getContact(){return contact;}
    public String getLoc(){return loc;}
    public String getArea(){return area;}
    public String getBuild(){return bulid;}
    public String getLga(){return lga;}
    public String getState(){return state;}
    public String getIdentity(){return identity;}
    public String getClientPassword(){return ClientPassword;}
    public String getClientEmail(){return Client_email;}
    public String getContactEmail(){return Contact_email;}
    
    public String getSensor(){return sensor;}
    public String getOperaName(){return OperaName;}
    public String getUserid(){return userid;}
    public String getPass(){return pass;}
    public String getStatus(){return status;}
    public String getMess(){return message;}
    public String getDate1(){return dat;}
    public String getRelation_Position(){return this.relation_position;}
    public String getSessionId(){return sessid;}
    public String getLink1(){return this.link1;}
    public String getDecrpt(){return descrpt;}
}