package com.exam.planner.Logic.Events;

import android.util.Log;

public class Event {
    private String tag;
    private String name;
    private DateTime startDate, endDate;
    private String id;
    private String copyId;
    private String colour;
    private Boolean isPublic = false;

    public Event(){
        this.tag = "Event";
        this.name = null;
        this.startDate = new DateTime();
        this.endDate = new DateTime(startDate, 1, 0);
        this.id = java.util.UUID.randomUUID().toString();
        this.copyId = null;
        this.colour = "grey";
    }

    public Event(int year, int month, int day, int hour, int minute) {
        this.name = null;
        this.startDate = new DateTime(year, month, day, hour, minute);
        this.endDate = new DateTime(startDate, 1, 0);
        this.id = java.util.UUID.randomUUID().toString();
        this.copyId = null;
        this.colour = "grey";
    }

    public Event(String id, int year, int month, int day, int hour, int minute, String name) {
        this.tag = "Event";
        this.name = name;
        this.startDate = new DateTime(year, month, day, hour, minute);
        this.endDate = new DateTime(startDate, 1, 0);
        this.id = id;
        this.copyId = null;
        this.colour = "grey";
    }

    public Event(String id, String tag){
        this.tag = tag;
        this.name = null;
        this.startDate = new DateTime();
        this.endDate = new DateTime(startDate, 1, 0);
        this.id = id;
        this.copyId = null;
        this.colour = "grey";
    }

    public Event(String id){
        this.tag = "Event";
        this.name = null;
        this.startDate = new DateTime();
        this.endDate = new DateTime(startDate, 1, 0);
        this.id = id;
        this.colour = "grey";
    }

    public String getName() {return this.name;}
    public DateTime getStartDate() {return this.startDate;}
    public DateTime getEndDate() {return this.endDate;}
    public String getId() {return this.id;}
    public String getCopyId() {return this.copyId;}
    public String getColour() {return this.colour;}
    public Boolean isPublic() {return this.isPublic;}

    public int getStartYear() {return this.getStartDate().getYear();}
    public int getStartMonth() {return this.getStartDate().getMonth();}
    public int getStartDay() {return this.getStartDate().getDay();}
    public int getStartHour() {return this.getStartDate().getHour();}
    public int getStartMinute() {return this.getStartDate().getMinute();}

    public int getEndYear() {return this.getEndDate().getYear();}
    public int getEndMonth() {return this.getEndDate().getMonth();}
    public int getEndDay() {return this.getEndDate().getDay();}
    public int getEndHour() {return this.getEndDate().getHour();}
    public int getEndMinute() {return this.getEndDate().getMinute();}

    public boolean compare(Event e){
        return (this.id.equals(e.id));
    }

    public void printEvent(){
        System.out.println("The Event has these values:");
        System.out.println("Name: "+this.name);
        System.out.print("Start time: ");
        this.startDate.printDate();
        System.out.print("End time: ");
        this.endDate.printDate();
        System.out.println("Id: "+this.id);
        System.out.println("Colour: "+this.colour);
        System.out.println("isPublic? "+this.isPublic);
    }

    public void editName(String newName) {this.name = newName;}

    public void editStartDate(int year, int month, int day) throws DateOutOfBoundsException{
        this.startDate.editDate(year, month, day);
    }
    public void editStartDate(int year, int month, int day, int hour, int minute)throws DateOutOfBoundsException,TimeOutOfBoundsException{
        this.startDate.editDate(year, month, day, hour, minute);
    }

    public void editEndDate(int year, int month, int day)throws DateOutOfBoundsException{
        this.endDate.editDate(year,month,day);
    }
    public void editEndDate(int year, int month, int day, int hour, int minute)throws DateOutOfBoundsException, TimeOutOfBoundsException {
        this.endDate.editDate(year,month,day, hour, minute);
    }

    public void editId (String newId){this.id = newId;}
    public void editCopyId (String newId){this.copyId = newId;}
    void editColour(String newColour){this.colour = newColour;}
    public void makeEventPublic(){this.isPublic = true;}
    public void makeEventPrivate(){this.isPublic = false;}

    public Event eventCopy(Event newEvent, int year, int month, int day) throws DateOutOfBoundsException, TimeOutOfBoundsException {
        newEvent.editName(this.name);
        newEvent.editStartDate(year, month, day, this.startDate.getHour(), this.startDate.getMinute());
        newEvent.editEndDate(year, month, day, this.endDate.getHour(), this.endDate.getMinute());
        newEvent.editColour(this.colour);
        newEvent.editCopyId(this.copyId);
        if(this.isPublic){
            newEvent.makeEventPublic();
        }
        return newEvent;
    }

    public Event deepCopy() {
        Event cpy = new Event();
        cpy.editName(this.name);
        cpy.editId(java.util.UUID.randomUUID().toString());
        cpy.editColour(this.colour);
        try {
            cpy.editStartDate(this.getStartYear(), this.getStartMonth(), this.getStartDay(), this.getStartHour(), this.getStartMinute());
            cpy.editEndDate(this.getEndYear(), this.getEndMonth(), this.getEndDay(), this.getEndHour(), this.getEndMinute());
        } catch (DateOutOfBoundsException exp) {
            Log.d("Exception", "deepCopy: DateOutOfBoundsException");
        } catch (TimeOutOfBoundsException times){
            Log.d("Exception", "deepCopy: TimeOutOfBoundsException");
        } catch (Exception e){
            Log.d("Exception", "deepCopy: Weird exception");
        }
        return cpy;
    }
}
