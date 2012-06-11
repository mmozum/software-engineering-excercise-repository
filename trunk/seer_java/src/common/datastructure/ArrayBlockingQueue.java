package common.datastructure;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<E> {

	int capacity;
	Object[] data;
	int count;
	int head, tail;
	
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition notEmpty = lock.newCondition();
	private final Condition notFull = lock.newCondition();
	
	
	public ArrayBlockingQueue(int capacity) {
		if(capacity <= 0){
			throw new IllegalArgumentException("capacity must be positive");
		}
		this.capacity = capacity;
		data = new Object[capacity];
	}
	
	public void put(E e) throws InterruptedException{
		
		if(e == null){
			throw new IllegalArgumentException();
		}
		
		lock.lock();
		
		try{
			
			while(count == capacity){
				notFull.await();
			}
			
			data[tail++] = e;
			count ++;
			if(tail == capacity){
				tail = 0;
			}
			
			notEmpty.signal();
			
		} finally {
			lock.unlock();
		}
		
	}
	
	public E get() throws InterruptedException {
		
		lock.lock();
		
		try{
			
			while(count == 0){
				notEmpty.await();
			}
			
			@SuppressWarnings("unchecked")
			E e = (E)data[head];
			data[head] = null;
			
			head ++;
			if(head == capacity){
				head = 0;
			}
			count --;
			
			notFull.signal();
			
			return e;
			
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
		Thread producer = new Thread(new Producer(queue));
		Thread consumer = new Thread(new Consumer(queue));
		producer.start();
		consumer.start();
		consumer.join();
		producer.join();
		System.out.println("all done~");
	}

}

class Producer implements Runnable {
	
	ArrayBlockingQueue<Integer> queue;
	Random rand = new Random();
	
	public Producer(ArrayBlockingQueue<Integer> queue){
		this.queue = queue;
	}

	@Override
	public void run() {
		
		try {
			for(int i = 0; i < 20; i ++){
				Thread.sleep(rand.nextInt(500));
				int k = rand.nextInt();
				queue.put(k);
				System.out.println(k + " is put in the queue");
			}
		} catch (InterruptedException e){
			throw new RuntimeException(e);
		}
	}
	
}

class Consumer implements Runnable {
	
	ArrayBlockingQueue<Integer> queue;
	Random rand = new Random();
	
	public Consumer(ArrayBlockingQueue<Integer> queue){
		this.queue = queue;
	}

	@Override
	public void run() {
		
		try {
			for(int i = 0; i < 20; i ++){
				Thread.sleep(rand.nextInt(1000));
				int k = queue.get();
				System.out.println(k + " is read from the queue");
			}
		} catch (InterruptedException e){
			throw new RuntimeException(e);
		}
	}
	
}