package common.google;

import java.util.NoSuchElementException;

/*
A. Given
interface Iterator<T> {
   T next();
   boolean hasNext();
}
interface Predicate<T> {
boolean accept(T t);
}

Implement a method that creates an "accept" iterator that returns items
accepted by the passedin pred variable.
Iterator<T> conditionIterator<T>(Iterator<T> input, Predicate<T> pred) {
}
 */


interface Iterator<T> {
	T next();
	boolean hasNext();
}

interface Predicate<T> {
	boolean accept(T t);
}

public class IteratorV2<T> implements Iterator<T> {
	
	Iterator<T> input;
	Predicate<T> pred;
	T element = null;
	
	public IteratorV2(Iterator<T> input, Predicate<T> pred){
		this.input = input;
		this.pred = pred;
		hasNext();
	}

	@Override
	public T next() {
		
		if(!hasNext()){
			throw new NoSuchElementException();
		}
		
		T ret = element;
		element = null;
		return ret;
	}

	@Override
	public boolean hasNext() {
		
		while(element == null){
			if(!input.hasNext()){
				return false;
			}
			
			T tmp = input.next();
			if(pred.accept(tmp)){
				element = tmp;
			}
		}
		
		return true;
	}

}
