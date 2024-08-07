package week4_composite;

import java.awt.*;
import java.util.ArrayList;
//
// The classes and/or objects participating in this pattern are:
// 1. Component   (DrawingElement)
//		Declares the interface for objects in the composition. Implements
//      default behavior for the interface common to all classes, as
//      appropriate. declares an interface for accessing and managing its
//		child components.
// 2. Leaf   (PrimitiveElement)
//		represents leaf objects in the composition. A leaf has no children.
//	    Defines behavior for primitive objects in the composition.
// 3. Composite   (CompositeElement)
//		defines behavior for components having children. Stores child
//		components. Implements child-related operations in the Component interface.
// 4. Client  (CompositeApp)
//		Manipulates objects in the composition through the Component interface.

// This is the "Component". (i.e tree node.)
interface HepsiTrendy11 {
    void Add(HepsiTrendy11 d);
    void Remove(HepsiTrendy11 d);
    void Display(int indent);
    public String getName();
    public boolean find(String name);
    public int totalPrice(String name);
}
//This is the "Leaf".
class Product implements HepsiTrendy11 {
    private String name;
    private String description;
    protected int price;


    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() { return name;}

    public boolean find(String name) {
            if (this.name.equals(name)){
                return true;
            }else
            {
                return false;
            }

    }

    @Override
    public int totalPrice(String name) {
        return this.price;
    }


    public void Add(HepsiTrendy11 c) {
        System.out.println("Cannot add to a PrimitiveElement.");
    }

    public  void Remove(HepsiTrendy11 c) {
        System.out.println("Cannot remove from a PrimitiveElement.");
    }
    public void Display(int indent) {
        for(int i = 1;i <= indent;i++) 	System.out.print("-");
        System.out.println(" "  + name);
    }

}
// This is the "Composite"
 class Category implements HepsiTrendy11 {



    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() { return name;}

    public boolean find(String name) {
        for (int i = 0; i < this.elements.size(); i++){
            if (this.elements.get(i).getName().equals(name)){
                return true;
            }
        }for (int i = 0; i < this.elements.size(); i++){
           if (this.elements.get(i).find(name)){
               return true;
           }
        }
        return false;
    }

    @Override
    public int totalPrice(String name) {
        int total = 0;
        if (find(name)){

        }
      return total;
    }

    public void Add(HepsiTrendy11 d) {elements.add(d);};
    public void Remove(HepsiTrendy11 d) {
        for (int i = 0; i< elements.size(); i++) {
            if (elements.get(i).getName() == d.getName()) {
                elements.remove(i);
                return;
            }
        }
    }
    public	void Display(int indent) {
        for(int i = 1;i <= indent;i++) System.out.print("-");
        System.out.println( "+ " + getName());

        // Display each child element on this node
        for (int i= 0; i< elements.size(); i++) {
            elements.get(i).Display(indent+2);
        }
    }
    protected 	ArrayList<HepsiTrendy11> elements = new ArrayList<HepsiTrendy11>();
}
//This is the "client"
class CompositePattern {
    public static void main(String[] args) {

        // Create a tree structure
        HepsiTrendy11 root = new Category("Technology");
        root.Add(new Product("P1","asdasd",132));
        root.Add(new Product("P2","asasdasdasd",1232));
        root.Add(new Product("P3","sad",123));

        HepsiTrendy11 comp = new Category("Two Circles");
        comp.Add(new Product("P4","asdasdas",123123));
        comp.Add(new Product("P5","sdasd",233));
        root.Add(comp);

        // Add and remove a PrimitiveElement
        HepsiTrendy11 pe = new Product("P6","dasdasd",1231);
        pe.Add(new Product("P6","adsad",123));
        root.Add(pe);
        root.Remove(pe);

        // Recursively display nodes
        root.Display(1);
        System.out.println(root.find("P4"));
        System.out.println(root.totalPrice("P4"));
    }
}