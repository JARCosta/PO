package ex.core;

/**
 * This class is the single core entity of this small example. It holds a list of int numbers.
 **/

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class IntegerManager {
    private List<Integer> _numbers;

    public IntegerManager() {
        _numbers = new ArrayList<>();
    }

    public boolean addNumber(Integer n) {
        if (_numbers.contains(n))
          return false;

        _numbers.add(n);
	return true;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(_numbers);
    }

    public List<Integer> getOrderedNumbers(){
        ArrayList<Integer> ordered = new ArrayList<>();
        
        ordered.addAll(_numbers);
        Collections.sort(ordered);
        return ordered;
    }
}
