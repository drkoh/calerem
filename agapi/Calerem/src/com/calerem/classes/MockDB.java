package com.calerem.classes;

public class MockDB {
	Event event1=new Event("Birthday","Maria",System.currentTimeMillis(),
			new Contact("Maria","Manikaki","6980234421","manikaki_mar@yahoo.gr",0),0,"21th birthday");
	Event event2=new Event("Anniversary","Dimitris",System.currentTimeMillis(),
			new Contact("Dimitris","Georgalis","6940234521","georgalis_dim@yahoo.gr",1),1,"2nd Anniversary");
	Event event3=new Event("Graduation","Agapi",System.currentTimeMillis(),
			new Contact("Agapi","Spiridonidou","6980222111","agapi_spiridonidou@gmx.de",2),2,"Graduation date of ICD/TEISER");
	
	private Event events[] = {event1,event2,event3};
	
	public Event[] get_events() {
		Event ev[] = events;
		return ev;
	}
	
	public Event[] editSpecifiedEventName(Integer id,String newName) {
		events[id].setEvent_name(newName);
		return events;
	}
	public Event[] editSpecifiedEventType(Integer id,String newType) {
		events[id].setEvent_type(newType);
		return events;
	}
}
