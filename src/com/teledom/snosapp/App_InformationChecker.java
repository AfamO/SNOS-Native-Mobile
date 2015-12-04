package com.teledom.snosapp;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
  //import java.util.regex.*;
   import me.regexp.*;
   
/**
 *
 * @author Tele
 */
public class App_InformationChecker {
    
    
    private static final String GSMNO = "[2-4]{0,3}[0]{1}[7-9]{1}[0-9]{9}";
   // private static final String GSMNO = "[2-4]{0,3}[0]{1}][7-9]{1}[0-9]{9}|[0-9]{11}";
    private static final String LGA="[A-Z,-]{2,200}";
    private static final String TOKEN="[A-Z,a-z,0-9]{6}";
    private static final String STATE="[A-Z,-]{2,150}";
    private static final String RELA="[A-Z,-]{2,150}";
    private static final String EMAIL="[a-z]{2}[a-z,0-9,_.]{1,15}[@]{1}[a-z,0-9,_.]{3,20}[.]{1}[a-z]{3}";
    private static final String SURNAME="([A-Z]{1,250}[-]{0,1}[A-Z]{0,250})";
    private static final String MIDNAME="[A-Z]{0,200}[.]{0,1}";
    private static final String FULLNAME="([A-Z,.]){3,700}[.]{0,1}";
    private static final String ADDRESS="([A-Z,0-9,-_,;,.#, ]){3,1000}[.]{0,1}";
   // private static final String ADDRESS="([A-Z,0-9,-_,;.#[ ]{0,7}]{3,1000})[.]{0,1}";
    private static final String PASSWORD="([A-Z,0-9,_,@,#,.]{3,32})";
    private static final String ADDRESS2="^\\w[\\w ]*\\w$";
    // private Matcher matcher;
 // private Pattern pattern;
    /** Creates a new instance of PatternCheck */
    public App_InformationChecker() 
    {
        
    }
   
    

    public boolean checkSurname(String name2)
    {
        RE check = new RE(SURNAME,RE.MATCH_CASEINDEPENDENT);
        
         //check.setMatchFlags(RE.MATCH_CASEINDEPENDENT);
        //pattern=Pattern.compile(SURNAME,Pattern.CASE_INSENSITIVE);
       // matcher=pattern.matcher(name2);
        if(check.match(name2))
        {
       //   System.out.println("surname is  good");
            return true;
        }
      //  System.out.println("surname is  bad");
         return false;
    }
    
    
    public boolean checkEmail(String email)
    {
        //pattern=Pattern.compile(EMAIL);
       // matcher=pattern.matcher(email);
         RE check = new RE(EMAIL,RE.MATCH_CASEINDEPENDENT);
        if(check.match(email))
        {
             //display("Valid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
           // System.out.println("Email is good");
             return true;
        }
         //display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
       // System.out.println("Email is wrong");
         return false;
    }
    
     public boolean checkGsm(String gsm)
    {
       // pattern=Pattern.compile(GSMNO);
        //matcher=pattern.matcher(gsm);
       RE check = new RE(GSMNO,RE.MATCH_CASEINDEPENDENT); 
        if(check.match(gsm))
        {
       //   System.out.println("Mobile Number is good");
          return true;
        }
         // System.out.println("Mobile Number is wrong");
         return false;
    }
     
      public boolean checkAddress(String add)
    {
        //pattern=Pattern.compile(ADDRESS,Pattern.CASE_INSENSITIVE);
       // matcher=pattern.matcher(add);
        RE check = new RE(ADDRESS2,RE.MATCH_CASEINDEPENDENT);
        if(check.match(add))
        {
          // System.out.println("adddress is good");
            return true;
        }
       //System.out.println("adddress is bad");
         return false;  
    }
     
    
       
    public boolean checkMidname(String Mname2)
    {
       // pattern=Pattern.compile(MIDNAME,Pattern.CASE_INSENSITIVE);
       // matcher=pattern.matcher(Mname2);
        RE check = new RE(MIDNAME,RE.MATCH_CASEINDEPENDENT);
        if(check.match(Mname2))
        {
          //  System.out.println("othername is  good");
            return true;
        }
         return false;
    }
     
      public boolean checkFullname(String Fullname)
    {
       
        RE check = new RE(FULLNAME,RE.MATCH_CASEINDEPENDENT);
        if(check.match(Fullname))
        {
           // System.out.println("fullname is  good");
            return true;
        }
         return false;
    }
    
    
    public boolean checkState(String state)
    {
       
        RE check = new RE(STATE,RE.MATCH_CASEINDEPENDENT);
        if(check.match(state))
        {
          // System.out.println("state is  good"); 
            return true;
        }
        //display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    
    
    public boolean checkToken(String token)
    {
       
        RE check = new RE(TOKEN,RE.MATCH_CASEINDEPENDENT);
        if(check.match(token))
        {
         //System.out.println("Token is  good"); 
            return true;
        }
        //display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    
    
    
     
      public boolean checklGA(String lga)
    {
       // pattern=Pattern.compile(LGA,Pattern.CASE_INSENSITIVE);
       // matcher=pattern.matcher(lga);
        RE check = new RE(LGA,RE.MATCH_CASEINDEPENDENT);
        if(check.match(lga))
        {
           // System.out.println("LGA is  good");
            return true;
        }
        //display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
      
       public boolean checkRelation(String rela)
    {
       // pattern=Pattern.compile(LGA,Pattern.CASE_INSENSITIVE);
       // matcher=pattern.matcher(lga);
        RE check = new RE(RELA,RE.MATCH_CASEINDEPENDENT);
        if(check.match(rela))
        {
           // System.out.println("LGA is  good");
            return true;
        }
        //display("Invalid "+label+" .Your "+label+"\n should be in this format:\n"+format+"\nThank You");
        return false;
    }
    
     public boolean checkPassword(String pass)
    {
       // pattern=Pattern.compile(PASSWORD,Pattern.CASE_INSENSITIVE);
        //matcher=pattern.matcher(pass);
        RE check = new RE(PASSWORD,RE.MATCH_CASEINDEPENDENT);
        if(check.match(pass))
        {
           
            return true;
        }
      
         return false;  
    }
  
}


