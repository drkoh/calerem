import java.util.ArrayList;


public class notification_controler
{
	private ArrayList<String> contacts;
	private ArrayList<String> generalCelebrations;

	private FindContacts findcontacts;
	
	
	
	
	
	public notification_controler() {
		
		EortologioApi eortologio = new EortologioApi ();
		c_contacts mycontacts = new c_contacts();
		
		contactsController(eortologio, mycontacts);
		
	}

	
	
	public void contactsController(EortologioApi eortologio,c_contacts mycontacts)
	{
		generalCelebrations = eortologio.returnVal() ; 
		c_contacts = mycontacts.getContacts(); //to get arraylist pou 8a mas dinetai apo c_contacts (h klash tou swsta pou th m epistrefei ena arraylist, 
											   //tha mporousa na ta parw etsi mycontacts.v_name = mycontacts.getV_name(); ena ena kai ta 5 ala etsi tha
											   //kataferna na parw mono gia Mia epafi, k oxi olles.)
		
		
		
	}
	
	public ArrayList<String> getControllerContacts()
	{
		
		
		return c_contacts;
		
		
	}
	
	public ArrayList<String> getControllerCelebrations()
	{
		
		return generalCelebrations;
		
	}

	
	
	/**
	 * @return
	 * 
	 * Synarthsh gia na pernei ta id twn epafwn gia to notification
	 */
	public ArrayList<Integer> getControllerContactsid()
	{
		
		findcontacts.searchNames(contacts ,generalCelebrations);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



