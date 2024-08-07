import java.util.List;
import java.util.Scanner;

class AdapterPrime extends SievePrime implements PrimeFinder {


     public String findPrimes(int limit) {
         String list = String.valueOf(sievePrimes(limit));
         return list;

     }
    @Override
    public List<Integer> sievePrimes(int limit) {
        return super.sievePrimes(limit);
    }
}
