package week11;

import java.util.ArrayList;
import java.util.SequencedCollection;

//============================================================================
//Name        : Main.java
//============================================================================
//The classes and/or objects participating in this pattern are:
//	1. Subject  (Stock)
//		. Knows its observers. Any number of Observer objects may observe a subject.
//	    . Provides an interface for attaching and detaching Observer objects.
//	2. ConcreteSubject  (IBM)
//	    . Stores state of interest to ConcreteObserver sends a notification to its.
//		. Observers when its state changes.
//	3. Observer  (Investor)
//   . Defines an updating interface for objects that should be notified
//     of changes in a subject.
//	4. ConcreteObserver  (Investor)
//   . Maintains a reference to a ConcreteSubject object
//   . Stores state that should stay consistent with the subject's
//   . Implements the Observer updating interface to keep its state
//     consistent with the subject's


//'Subject' ==> Stock
abstract class SchoolBell {
    volatile int hour = 0;
    int minute = 0;


    public SchoolBell() {
    }
    //Register the Observers
    public void Attach (IClassroom concreteClassroom) {
        concreteClassrooms.add(concreteClassroom);
    }
    //Unregister from the list of Observers.
    public void Detach (IClassroom concreteClassroom) {
        concreteClassrooms.remove(concreteClassroom);
    }

    //Notify the Observers.
    public void Notify() {
        // set argument to something that helps
        // tell the Observers what happened
        for (int i = 0; i < concreteClassrooms.size(); i++) {
            concreteClassrooms.get(i).Update(this);
        }
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    protected ArrayList<IClassroom> concreteClassrooms = new ArrayList<IClassroom>();
}

//'ConcreteSubject' ==> IBM


class ConcreteBell extends SchoolBell {
    // Constructor
    public ConcreteBell() {
        super();
    }

    public void startClock(){
        for(;;){
            minute++;
            if (hour<24){
                checkRing();

            }
        }
    }
    public void checkRing(){

            if (minute == 20) {
                Notify();
            }

            if (minute == 60) {
                setHour(hour + 1);
                setMinute(0);
            }


    }
}

class ConcreteBell2 extends SchoolBell {
    // Constructor
    public ConcreteBell2() {
        super();
    }

    public void startClock(){
        for(;;){
            minute++;
            if (hour< 24){
                checkRing();
            }
        }
    }
    public void checkRing(){
        if(minute == 50){
            Notify();

        }
        if(minute == 60){
            setHour(hour + 1);
            setMinute(0);
         }
    }
}

//'Observer'  ==> Abstract Observer.

interface IClassroom {
    public void Update(SchoolBell schoolBell);
}

//'ConcreteObserver' ==> Investor

class Classroom1 implements IClassroom {

 public int hour;
 public int minute;
 private SchoolBell _schoolBell;


    @Override
    public void Update(SchoolBell schoolBell) {

        _schoolBell = schoolBell;
        minute = schoolBell.getMinute();
        hour = schoolBell.getHour();
            System.out.println("Break time for classroom1");
            System.out.println("Time is" + hour + "::" + minute);
    }


    public int getHour() {
        return hour;
    }


    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public SchoolBell getSchoolBell() {
        return _schoolBell;
    }

    public void setSchoolBell(SchoolBell schoolBell) {
        this._schoolBell = schoolBell;
    }


    //MainApp test application

}

class Classroom2 implements IClassroom {

    public int hour;
    public int minute;
    private SchoolBell _schoolBell;

    @Override
    public void Update(SchoolBell schoolBell) {
        _schoolBell = schoolBell;
        minute = schoolBell.getMinute();
        hour = schoolBell.getHour();
        System.out.println("Break time for classroom 2");
        System.out.println("Time is" + hour + "::" + minute);
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public SchoolBell getSchoolBell() {
        return _schoolBell;
    }

    public void setSchoolBell(SchoolBell schoolBell) {
        this._schoolBell = schoolBell;
    }


    //MainApp test application

}


class ObserverPattern {
    public static void main(String[] args) {

SchoolBell concreteBell = new  ConcreteBell();
ConcreteBell2 concreteBell2 = new ConcreteBell2();
IClassroom SE311 = new Classroom1();
IClassroom SE322 = new Classroom2();



        concreteBell2.Attach(SE322);
        concreteBell2.startClock();

    }

}
