package be.ehb.ti.nielsvitse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

/*
Gebruik voor de eerste methode een normale alleenstaande klasse.
Gebruik voor de tweede een inner class
Gebruik voor de derde een anonymous inner class

 */
public class Warehouse {
    private HashSet<Product> producten = new HashSet<>();

    public TreeSet<Product> returnSortedOnName()
    {
        TreeSet<Product> p = new TreeSet<>(new SortOnName());
        return p;
    }

    public TreeSet<Product> returnSortedOnPrice()
    {
        TreeSet<Product> p = new TreeSet<>(new sortBasedOnPrice());
        return p;
    }

    public ArrayList<Product> returnSortedOnWeight()
    {
        ArrayList<Product> p = new ArrayList();
        p.sort((o1, o2) -> Double.compare(o1.getWeight(), o2.getWeight()));
        return p;
    }

    public class sortBasedOnPrice implements Comparator<Product>
    {
        @Override
        public int compare(Product o1, Product o2) {
          return Double.compare(o1.getPrice(), o2.getPrice());
        }
    }
}
