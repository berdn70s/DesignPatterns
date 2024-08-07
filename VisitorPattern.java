package week13_visitor;

// Element  (Element)
// defines an Accept operation that takes a visitor a
// as an argument.

interface Element {
    public void Accept(Visitor visitor);
}

//"Visitor" declares a Visit operation for each class of ConcreteElement in the
//object structure. The operation's name and signature identifies the
//class that sends the Visit request to the visitor. That lets the
//visitor determine the concrete class of the element being visited.
//Then the visitor can access the elements directly through its particular
//interface
//
//"Visitor"
interface Visitor {
    public void Visit(Apt_flat apt_flat);
    public void Visit(House house);
}

//ConcreteElement  (Employee)
//implements an Accept operation that
//takes a visitor as an argument

abstract class Places implements Element {
    private String Name;
    private String Password;
    private  boolean electric_job;
    private boolean waterworks_job;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Places(String name, boolean electric_job, boolean waterworks_job) {
        Name = name;
        this.electric_job = electric_job;
        this.waterworks_job = waterworks_job;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public boolean isElectric_job() {
        return electric_job;
    }

    public void setElectric_job(boolean electric_job) {
        this.electric_job = electric_job;
    }

    public boolean isWaterworks_job() {
        return waterworks_job;
    }

    public void setWaterworks_job(boolean waterworks_job) {
        this.waterworks_job = waterworks_job;
    }
}

class Apt_flat extends Places{

    public Apt_flat(String name, boolean electric_job, boolean waterworks_job) {
        super(name, electric_job, waterworks_job);
    }

    public void Accept(Visitor visitor) {visitor.Visit(this);}
}

class House extends Places {

    public House(String name, boolean electric_job, boolean waterworks_job) {
        super(name, electric_job, waterworks_job);
    }

    public void Accept(Visitor visitor) {visitor.Visit(this);}

}


abstract class Worker implements Visitor{


    public Worker(String name, int salary) {
        Name = name;
        Salary = salary;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public void addSalary(int salary){
        setSalary(getSalary() + salary);
    }

    private String Name;
    private int Salary;
    public String getLock(Places places) {
        return places.getPassword();
    };
}


class Electrician extends Worker {

    public Electrician(String name, int salary) {
        super(name, salary);
    }


    @Override
    public void Visit(Apt_flat apt_flat) {
        getLock(apt_flat);
        apt_flat.setElectric_job(true);
        this.addSalary(200);
        System.out.println(getName() + " has done the job");
        System.out.println( getSalary() + " is new salary ");
    }

    @Override
    public void Visit(House house) {
        getLock(house);
        house.setElectric_job(true);
        this.addSalary(200);
        System.out.println(getName() + " has done the job");
        System.out.println( getSalary() + " is new salary ");
    }
}


class Plumber extends Worker {

    public Plumber(String name, int salary) {
        super(name, salary);
    }

    @Override
    public void Visit(Apt_flat apt_flat) {
        getLock(apt_flat);
        apt_flat.setWaterworks_job(true);
        this.addSalary(250);
        System.out.println(getName() + " has done the job");
        System.out.println( getSalary() + " is new salary ");

    }

    @Override
    public void Visit(House house) {
        getLock(house);
        house.setWaterworks_job(true);
        this.addSalary(500);
        System.out.println( getName() + " has done the job");
        System.out.println( getSalary() + " is new salary ");


    }
}

// ObjectStructure can enumerate its elements  may provide a
// high-level interface to allow the visitor to visit its elements
// may either be a Composite (pattern) or a collection such as a
// list or a set
//
// ObjectStructure  (Employees)


// ConcreteVisitors (IncomeVisitor, VacationVisitor)
// implements each operation declared by Visitor. Each operation implements
// a fragment of the algorithm defined for the corresponding class or object
// in the structure. ConcreteVisitor provides the context for the algorithm
// and stores its local state. This state often accumulates results during
// the traversal of the structure.
//
// "ConcreteVisitor 1"
public class VisitorPattern {
    public static void main(String[] args) {
        // Setup employee collection
       Places apt_flat= new Apt_flat("Berdan Flat", false,false);
       Places house = new House("Berdan House", false,false);
       Visitor plumber = new Plumber("  Plumber Semih",0);
       Visitor electrician = new Electrician("  Elektrikci Semih",0);

        apt_flat.Accept(plumber);
        apt_flat.Accept(plumber);
        apt_flat.Accept(plumber);


    }
}