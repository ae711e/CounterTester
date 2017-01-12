/*
 * Copyright (c) 2017. Aleksey Eremin
 * 12.01.17 16:49
 *
 */

package edu;

/**
 * Created by ae on 12.01.2017.
 * Многопоточность и доступ к ресурсу
 * by novel  http://java-course.ru/begin/multithread_02/
 */
public class CounterTester
{
  public static void main(String[] args) throws InterruptedException
  {
    Counter counter = new Counter();
    for(int i=0; i<200; i++) {
      CounterThread ct = new CounterThread(counter);
      ct.start();
    }
    Thread.sleep(1000);
  
    System.out.println("Counter: " + counter.getCounter());
  }
}
// end class  CounterTester


class Counter
{
  private long counter = 0L;
  
  // делаем ресурс синхронным, т.е. ограниченным по доступу (synchronized)
  public synchronized void increaseCounter()
  {
    counter++;
  }
  
  public long getCounter()
  {
    return counter;
  }
} // end class  Counter

class CounterThread extends Thread {
  private Counter count;
  
  public CounterThread(Counter counter)
  {
    count = counter;
  }
  
  @Override
  public void run() {
    for(int i=0; i<1000; i++) {
      count.increaseCounter();
    }
  }
}// end class  CounterThread