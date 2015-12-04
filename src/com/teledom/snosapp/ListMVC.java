/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teledom.snosapp;

/**
  
 * @author fupre1
 */
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.ListCellRenderer;
import com.codename1.ui.util.*;
import com.codename1.ui.list.ListModel;
import static com.teledom.snosapp.JSONConnectionObject.Min;
import static com.teledom.snosapp.JSONConnectionObject.MinimumBound;
import userclasses.StateMachine;
public class ListMVC
{
    public static String SECURITY_ALERTS_DATA [][];
    public static  Label status  = new Label("Okay \n\n\n");;
    private static   List alertList;
    public static Button next;
    public static TextField search;
    public static Button prev,searchButton;
    public static Container centerFlow;
    static boolean Isnext=false;
    Form form;
    JSONConnectionObject jsonObj;
    public ListMVC(String CONTACTS_INFO[][], Form f)
    {
        this.SECURITY_ALERTS_DATA=CONTACTS_INFO;
        this.form=f;
        jsonObj=StateMachine.getJSONConnObj();
    }
    /*
    * This is a no-argument default constructor.
    */
    public ListMVC()
    {
        
    }
    /*
    * This collects the retrieved sms alerts, constructs a List object, puts the alerts in
    * the List and put the List inside a form object to be displayed
    */
    public void buildAll()
    {
        //declare and initialize ONE alert array since SECURITY_ALERTS_DATA is a compound array
        final AlertInfo[] alertsArray = new AlertInfo[SECURITY_ALERTS_DATA.length];
        System.out.println("The contacts_info lent is:"+SECURITY_ALERTS_DATA.length);
        for (int i = 0; i < alertsArray.length; i++) 
        {
            //build and assign the alerts to the new single array
            int pos = i % SECURITY_ALERTS_DATA.length;
            alertsArray[i] = new AlertInfo(SECURITY_ALERTS_DATA[pos][1],SECURITY_ALERTS_DATA[pos][2],SECURITY_ALERTS_DATA[pos][3]);
        }
        //get an object of the alertList by declaring a new anonymous class for it.
        alertList  = new List(new ListModel()
        {
            private int selection;
            public Object getItemAt(int index) 
            {
                return alertsArray[index % alertsArray.length];
            }
            public int getSize()
            {
                return 10;
            }
            public int getSelectedIndex()
            {
                return selection;
            }
            public void setSelectedIndex(int index)
            {
                selection = index;
            }
            public void addDataChangedListener(DataChangedListener l)
            {
                
            }
            public void removeDataChangedListener(DataChangedListener l)
            {
                
            }
            public void addSelectionListener(SelectionListener l)
            {
                
            }
            public void removeSelectionListener(SelectionListener l)
            {
                
            }
            public void addItem(Object item)
            {
                
            }
            public void removeItem(int index)
            {
                
            }
        });//ends the anonymous class for the List object:alertList
        //sets a renderer and fixed selection properties for the List object
        alertList.setListCellRenderer(new AlertsRenderer());
        alertList.setFixedSelection(List.FIXED_NONE_CYCLIC); 
        //gets the sqlite db object for this class
        final SqliteDB sqldb=new SqliteDB();
        //gets a form object that will hold the alert list object
        Form alertForm=form;
        //disallow srolling ability
        alertForm.setScrollable(false);
        //gets a status object to display status messages on top of the alert form
        status = new Label("Okay \n\n\n");
        //gets a container that has flowlayout so that it can contain components horizontally.
        centerFlow = new Container(new FlowLayout(Component.CENTER));
        //gets the difference between total and current sms count
        int diff= Integer.parseInt(JSONConnectionObject.SMSTotalCount)-Integer.parseInt(JSONConnectionObject.getSMSCurrentCount());
        JSONConnectionObject.total_current_sms_DiFF=diff;
        //Has the client successfully logged in ?
        if(StateMachine.IsLoggedIn)
        {
            //Dialog.show("Empty Input", "111Hey, Please do enter the item you want to search for\nThank you", "Ok",null);
            //resign false so that it could be re-used
            StateMachine.IsLoggedIn=false;
            //gets a "Next" Alert button for viewing other sms alerts
            next = new Button("Next>>");
            //gets a "Previous" Alert button for viewing other sms alerts
            prev = new Button("<<Previous");
            //gets a search button
            searchButton = new Button("Search");
            //set it false to create more space for other components
            searchButton.setVisible(false);
            //get a text field for the search
            search=new TextField();
            //set a hint in it.
            search.setHint("Type the security alert term to SEARCH for");
            //adds the Listener that detects and fires whenever a search item changes in the search text field
            search.addDataChangeListener(new DataChangedListener()
            {
                public void dataChanged(int type, int index)
                {
                    searchButton.setVisible(true);
                }
            });
            //gets the status object
            status = new Label("Okay \n\n\n");
            centerFlow = new Container(new FlowLayout(Component.CENTER));
            //adds the search text field and searchButton to the container
            centerFlow.addComponent(searchButton);
            centerFlow.addComponent(search);
            //adds the appropriate Listener to the search button
            searchButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(com.codename1.ui.events.ActionEvent e)
                {
                    //Is the search field empty?
                    if(search.getText().equalsIgnoreCase(""))
                    {
                        Dialog.show("Empty Input", "Hey, Please do enter the item you want to search for\nThank you", "Ok",null);
                    }
                    else
                    {
                        //Is sms alerts stored locally?
                        if(sqldb.IsSMSStoredLocally())
                        {
                            int current=0;
                            //retrieve the alerts marching the search term
                            String[][] searchLocalStoredAlert = sqldb.searchLocalStoredAlert(search.getText(),current,10);
                            //Is search attempt not successfull?
                            if(searchLocalStoredAlert[0][0].equals("-1"))
                            {
                                Dialog.show("No Search Item Alert", "Sorry but there are no items marching the term:\n"+search.getText()+"\n that are found for you.\nThank you", "Ok",null);
                                ListMVC.status.setText("Sorry:There are no items found for:"+search.getText());
                            }
                            else
                            {
                                //get the alerts, alert form and build the alerts found for the search term.
                                ListMVC   lstmvc=StateMachine.getListMVCObj();
                                ListMVC.SECURITY_ALERTS_DATA= searchLocalStoredAlert;
                                lstmvc.buildAll();
                                ListMVC.status.setText("These items are found for:"+search.getText());
                                searchButton.setVisible(false);
                            }
                        }
                    }
                }
            });//ends the search listener implementaion
            //adds a an action Listener for the "next" sms button
            next.addActionListener(new ActionListener()
            {
                public void actionPerformed(com.codename1.ui.events.ActionEvent e)
                {
                    //Is alert stored locally?
                    if(sqldb.IsSMSStoredLocally())
                    {
                        //get the needed parameter and then retrieve the next 10 alerts starting from 'current' position one.
                        int current=0;
                        int minrange=(Integer.parseInt(JSONConnectionObject.Min));
                        //adds 10 since you will be getting the next 10 alerts from the current position(minrange)
                        current=minrange+10;
                        jsonObj.getAndDisplayLocalSMS(current,10,"next");
                    }
                }
            });//ends the actionListener implementation for the 'next' button
            //adds a an action Listener for the "previous" sms button
            prev.addActionListener(new ActionListener()
            {
                public void actionPerformed(com.codename1.ui.events.ActionEvent e)
                {
                    //Is alert stored locally?
                    if(sqldb.IsSMSStoredLocally())
                    {
                        //get the needed parameter and then retrieve the previous 10 alerts starting from 'current' position one.
                        int current=0;
                        int minrange=(Integer.parseInt(JSONConnectionObject.Min));
                        //If the current count is less than the total one and the client just clicked the "next" button for the first time
                        if(JSONConnectionObject.IsNextTransitioned && Integer.parseInt(JSONConnectionObject.SMSCurrentCount)<Integer.parseInt(JSONConnectionObject.SMSTotalCount))
                        {
                            //subtracts 10 since you will be getting the previous 10 alerts from the current position(minrange)
                            current=minrange-10;
                        }
                        else
                        {
                            //subtracts 20 since you will be getting the previous 10 alerts from the current position(minrange)
                            current=minrange-20;
                        }
                        jsonObj.getAndDisplayLocalSMS(current,10,"prev");
                    }
                }
            });//ends the actionListener implementation for the 'previous' button
            //Is the sms current count equal to 0
            if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())==0)
            {
                //then display the appropriate error message
                status.setText("We are sorry to inform you that there are No Messages Found for you!.\nThank you.");
            }
            //Is the minimun part of the sms (in the form 1-10 of 30 where 1=minimumbound) equal to 1 and currnt count is not more than 10
            else if(Integer.parseInt(JSONConnectionObject.MinimumBound)==1  && Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())<=10)
            {
                //adds and display the next button
                centerFlow.addComponent(next);
                //set the status label in the form 1-10 of 30 where 1=minimum, 10=current count and 30 equal to total count
                status.setText(" "+JSONConnectionObject.MinimumBound+"-"+JSONConnectionObject.getSMSCurrentCount()+" of "+JSONConnectionObject.SMSTotalCount);
            }
            //Is the sms current count greater than  10
            else if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())>10)
            {
                //Is current count less than total count
                if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())<Integer.parseInt(JSONConnectionObject.SMSTotalCount))
                {
                    //adds and display the prev button
                    centerFlow.addComponent(prev);
                    //set the status label in the form 1-10 of 30 where 1=minimum, 10=current count and 30 equal to total count
                    status.setText(" "+JSONConnectionObject.MinimumBound+"-"+JSONConnectionObject.getSMSCurrentCount()+" of "+JSONConnectionObject.SMSTotalCount);
                    //adds and display the next button
                    centerFlow.addComponent(next);
                }
                //Is current count equal to total count while total count is greater than 10
                else if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())==Integer.parseInt(JSONConnectionObject.SMSTotalCount) && Integer.parseInt(JSONConnectionObject.SMSTotalCount)>10)
                {
                    //adds and display the prev button
                    centerFlow.addComponent(prev);
                    //set the status label in the form 1-10 of 30 where 1=minimum, 10=current count and 30 equal to total count
                    status.setText(" "+JSONConnectionObject.MinimumBound+"-"+JSONConnectionObject.getSMSCurrentCount()+" of "+JSONConnectionObject.SMSTotalCount);
                }
            }
            //In any case adds and display the status label
            centerFlow.addComponent(status);
            //Build and set up the needed component for the alert form
            alertForm.setLayout(new BorderLayout());
            alertForm.addComponent(BorderLayout.NORTH, centerFlow);
            //adds the alert list to the border layout center of the form
            alertForm.addComponent(BorderLayout.CENTER, alertList);
            //sets the form object to be re-used later in this application current running session.
            StateMachine.setFormObj(alertForm);
            Log.p("ALERT FORM HAS BEEN SETTTTTTTTTTTTTTT");
            //show the form object
            alertForm.show();
           
        }
        //Is current count 0
        else if(Integer.parseInt(JSONConnectionObject.SMSTotalCount)==0)
        {
            //report the error message
            StateMachine.IsLoggedIn=true;
            status.setText("We are sorry to inform you that there are no alerts found for you!.");
            centerFlow.addComponent(status);
            alertForm.setLayout(new BorderLayout());
            alertForm.addComponent(BorderLayout.NORTH, centerFlow);
            alertForm.show();
            StateMachine.setFormObj(alertForm);
        }
        else
        {
            //remove the already added components to avoid adding stack of these components
            ListMVC.centerFlow.removeComponent(searchButton);
            ListMVC.centerFlow.removeComponent(search);
            //And new ones
            ListMVC.centerFlow.addComponent(searchButton);
            ListMVC.centerFlow.addComponent(search);
            //Is current count 0
            if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())==0)
            {
                ListMVC.status.setText("We are sorry to inform you that there are No Messages Found for you!.\nThank you.");
            }
            //Is current count not more than 10 and is minimumbound equal to 1.
            else if(Integer.parseInt(JSONConnectionObject.MinimumBound)==1  && Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())<=10)
            {
                //Is current count greater than or equal to 10 and total count more than 10
                if(Integer.parseInt(JSONConnectionObject.SMSTotalCount)>10 && Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())>=10 )
                {
                    System.out.println("Ok2");  
                    if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())==10)
                    {
                        //remove the already added (next and previous buttons)components to avoid adding stack of these components
                        ListMVC.centerFlow.removeComponent(prev);
                        ListMVC.centerFlow.removeComponent(next);
                        ListMVC.centerFlow.addComponent( ListMVC.next);
                        ListMVC.status.setText(" "+JSONConnectionObject.MinimumBound+"-"+JSONConnectionObject.getSMSCurrentCount()+" of "+JSONConnectionObject.SMSTotalCount);
                    }
                    else
                    {
                        ListMVC.status.setText(" "+JSONConnectionObject.MinimumBound+"-"+JSONConnectionObject.getSMSCurrentCount()+" of "+JSONConnectionObject.SMSTotalCount);
                        //remove the already added (next button)component to avoid adding stack of these components
                        ListMVC.centerFlow.removeComponent(next);
                        ListMVC.centerFlow.addComponent( ListMVC.next);
                    }
                }
                else
                {
                    ListMVC.status.setText(" "+JSONConnectionObject.MinimumBound+"-"+JSONConnectionObject.getSMSCurrentCount()+" of "+JSONConnectionObject.SMSTotalCount);
                }
            }
            //Is current count greater than 10
            else if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())>10)
            {
                //Is current count less than total count
                if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())<Integer.parseInt(JSONConnectionObject.SMSTotalCount))
                {
                    ListMVC.centerFlow.removeComponent(prev);
                    ListMVC. centerFlow.addComponent( ListMVC.prev);
                    ListMVC. status.setText(" "+JSONConnectionObject.MinimumBound+"-"+JSONConnectionObject.getSMSCurrentCount()+" of "+JSONConnectionObject.SMSTotalCount);
                    ListMVC.centerFlow.removeComponent(next);
                    ListMVC.centerFlow.addComponent( ListMVC.next);
                }
                //Is current count is equal to total count while total count is more than 10
                else if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())==Integer.parseInt(JSONConnectionObject.SMSTotalCount) && Integer.parseInt(JSONConnectionObject.SMSTotalCount)>10)
                {
                    ListMVC.centerFlow.removeComponent(prev);
                    ListMVC. centerFlow.addComponent( ListMVC.prev);
                    ListMVC.status.setText(" "+JSONConnectionObject.MinimumBound+"-"+JSONConnectionObject.getSMSCurrentCount()+" of "+JSONConnectionObject.SMSTotalCount);
                }
                //Is diff not more than 10
                else if(diff<=10)
                {
                    ListMVC.centerFlow.removeComponent(ListMVC.next);
                    ListMVC.centerFlow.removeComponent(prev);
                    ListMVC. centerFlow.addComponent( ListMVC.prev);
                    JSONConnectionObject.SMSCurrentCount=JSONConnectionObject.SMSTotalCount;
                    if(Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())>Integer.parseInt(JSONConnectionObject.SMSTotalCount))
                    {
                        int min=Integer.parseInt(JSONConnectionObject.SMSCurrentCount)-10;
                        MinimumBound=Integer.toString(min+1);
                        JSONConnectionObject.SMSCurrentCount=JSONConnectionObject.SMSTotalCount;
                    }
                    ListMVC.status.setText(" "+JSONConnectionObject.MinimumBound+"-"+JSONConnectionObject.getSMSCurrentCount()+" of "+JSONConnectionObject.SMSTotalCount);
                    diff=Integer.parseInt(JSONConnectionObject.getSMSCurrentCount())-diff;
                    JSONConnectionObject.SMSCurrentCount=Integer.toString(diff);
                }
            }
            //get the object of Alert Form already set at the first display of sms alerts
            alertForm=StateMachine.getFormObj();
            alertForm.removeComponent(ListMVC.centerFlow);
            alertForm.addComponent(BorderLayout.NORTH, ListMVC.centerFlow);
            ListMVC.centerFlow.removeComponent(status);

            ListMVC.centerFlow.addComponent(status);
            alertForm.removeComponent(alertList);
            alertForm.addComponent(BorderLayout.CENTER, ListMVC.alertList);
            alertForm.show();
            //re-set the form again
            StateMachine.setFormObj(alertForm);
            Log.p("ALERT FORM HAS BEEN GETTTTTTTTTTTTTTT");
            
        }
    }//ends the build method
    
    /**
    * This class is used to create get and set class used for Alerts List.
    * 
    */
    class AlertInfo 
    {
        private String alert;
        private String date1;
        private String action;
        public AlertInfo(String alert, String date1,String action ) 
        {
            this.alert = alert;
            this.date1 = date1;
            this.action=action;
        }
        public String getAlert()
        {
            return alert;
        }
        public String getDate()
        {
            return date1;
        }
        public String getAction()
        {
            return action;
        }
    }
    /**
    * This class is used to create AlertsRenderer class used for Alerts List.
    * 
    */
    class AlertsRenderer extends Container implements ListCellRenderer 
    {
        private Label alert = new Label("");
        private Label date1 = new Label("");
        private CheckBox serialNumbercheckbox = new CheckBox();
        private Button action = new Button();
        private Label focus = new Label("");
        public AlertsRenderer()
        {
            setLayout(new BorderLayout());
            addComponent(BorderLayout.WEST, serialNumbercheckbox);
            Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            alert.getStyle().setBgTransparency(0);
            date1.getStyle().setBgTransparency(0);
            cnt.addComponent(alert);
            cnt.addComponent(date1);
            cnt.addComponent(action);
            addComponent(BorderLayout.CENTER, cnt);
        }
        public Component getListCellRendererComponent(List list, Object value, int index,boolean isSelected)
        {
            AlertInfo alertInfo = (AlertInfo) value;
            alert.setText( alertInfo.getAlert());
            date1.setText(alertInfo.getDate());
            //serialNumbercheckbox.setText("true");
            action.setText("Action");
            return this;
        }
        public Component getListFocusComponent(List list)
        {
            return focus;
        }
    }
}