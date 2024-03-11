package week3_iterator;
import java.util.ArrayList;

//
//Iterator pattern:
//
//Provide a way to access the elements of an aggregate object
//sequentially without exposing its underlying representation.
//
//The classes and/or objects participating in this pattern are:

//1. Iterator  (AbstractIterator)
//		defines an interface for accessing and traversing elements.
//2. ConcreteIterator  (Iterator)
//		implements the Iterator interface.
//		keeps track of the current position in the traversal of the aggregate.
//3. Aggregate  (AbstractCollection)
//		defines an interface for creating an Iterator object
//4. ConcreteAggregate  (Collection)
//		implements the Iterator creation interface to return an instance of the proper ConcreteIterator
//

public class IteratorPattern {
    static void printAggregate(AbstractIterator i) {
        System.out.println("Iterating over collection:");
        for(i.First();  !i.IsDone(); i.Next()) {
            System.out.println(i.CurrentItem().getName());
        }
        System.out.println();
    }


    public static void main(String[] args) {
        // Create Aggregate.
        AbstractAggregate aggregate = new Collection();
        aggregate.add(new Channel("Das Erste",10,"Germany"));
        aggregate.add(new Channel("NOW",555,"Türkiye"));
        aggregate.add(new Channel("“CCTV-1",657,"China"));
        aggregate.add(new Channel("“Show Tv",0,"Türkiye"));
        aggregate.add(new Channel("TVNZ-1,555,",999,"New Zealand"));
        aggregate.add(new Channel("CNC World",789,"China"));
        aggregate.add(new Channel("TRT-1",676,"Türkiye"));
        aggregate.add(new Channel("ZDF",155,"Germany"));
        aggregate.add(new Channel("Mehwar TV",56,"Egypt"));

        // Create Iterator
        AbstractIterator Turkiyeiterator = aggregate.CreateIterator();
        // Traverse the Aggregate.
        printAggregate(Turkiyeiterator);
    }
}

class Channel{
    String name;
    int frequency;
    String countryOrigin;

    Channel(String name, int frequency, String countryOrigin) {
        this.name = name;
        this.frequency = frequency;
        this.countryOrigin = countryOrigin;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", frequency=" + frequency +
                ", countryOrigin='" + countryOrigin + '\'' +
                '}';
    }

    private String getCountryOrigin() {
        return countryOrigin;
    }

    private int getFrequency() {
        return frequency;
    }

    String getName() {
        return name;
    }

}

//
//Our concrete collection consists of Items.
//

//
//This is the abstract "Iterator".
//		AbstractIterator
//

interface  AbstractIterator {
    void First();
    void Next();
    Boolean IsDone () ;
    Channel CurrentItem() ;
}

//
//This is the "concrete" Iterator for collection.
//		CollectionIterator
//

class specialIterator implements AbstractIterator{

    public specialIterator(Collection collection) {
        _collection = collection;
        _current = 0;
    }
    private final Collection _collection;
    private int _current;

    @Override
    public void First() {
        for (int i = 0 ; i < _collection.getCount(); i ++){
            if (_collection.get(i).countryOrigin == "Türkiye"){
                _current = i ;
            }
        }

    }

    @Override
    public void Next() {

        for (int i = _current ; i < _collection.getCount(); i ++){
            if (_collection.get(i).countryOrigin == "Türkiye"){
              _current+=i;
            }
        }
    }

    @Override
    public Boolean IsDone() {
        return _current >= _collection.getCount();
    }

    @Override
    public Channel CurrentItem() {

        return (IsDone()?null:_collection.get(_current));

    }
}
class CollectionIterator implements AbstractIterator {
    public void First() {_current = 0;}
    public void Next()  {_current++; }
    public Channel CurrentItem() { return (IsDone()?null:_collection.get(_current)); }
    public Boolean IsDone() {	return _current >= _collection.getCount(); }
    public CollectionIterator(Collection collection) {
        _collection = collection;
        _current = 0;
    }
    private Collection _collection;
    private int _current;
};


//
//This is the abstract "Aggregate".
//			AbstractAggregate
//

interface AbstractAggregate {
    public AbstractIterator CreateIterator();
    public void add(Channel channel); 		// Not needed for iteration.
    public int getCount (); // Needed for iteration.
    public Channel get(int idx); // Needed for iteration.
};

//
//This is the concrete Aggregate.
//			Collection
//

class Collection implements AbstractAggregate {
    private final ArrayList<Channel> channels = new ArrayList<Channel>();
    public	specialIterator CreateIterator() {
        return new specialIterator(this);
    }


    public int getCount () {return channels.size(); }
    public void add(Channel item) {channels.add(item);};
    public Channel get(int index) { return (Channel) channels.get(index);};
};
