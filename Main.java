
package runner;

import ProducerConsumer.Consumer;
import ProducerConsumer.ConsumerNoDisruptor;
import ProducerConsumer.Producer;
import message.MsgDisruptor;
import stopWatch.StopWatch;

public class Main {
	
	
	public static void main(String[] args){

		//Setting limits on producer threads________________________________
		StopWatch.setTotalMsgObjects(1023);//decides the total number of Objects by ALL producers combined
		StopWatch.setTotalProducers(3);//total number of producer threads
		
		MsgDisruptor md = new MsgDisruptor();// all Lmax disruptor objects instantiated here
		StopWatch.start();//start stopwatch
		
		//Declare all consumer threads first________________________
		Consumer cons1=new Consumer("cons1",md);//to have md.disruptor handleEvents with this consumer
		//Thread.sleep(5);//main waits for 50 ms before creating new consumer
		Consumer cons2=new Consumer("cons2",md);
		//Start all Consumer Threads______________
		md.disruptor.start();//start the disruptor and Consumer threads
		//consumer threads run ONLY when disruptor starts
		
		/*Note that we're controlling the number of producers, but we can have any number of consumers*/
		
		//Declare Producers__________________________________		
		Producer prods[]=new Producer[StopWatch.totalProducers];
		
		for(int i=0;i<prods.length;i++){
			String prodNameTemp="prod_"+(i+1);
			prods[i]=new Producer(md.getRingBuffer(),prodNameTemp);//starts Producer threads
		}
		
		//Producer prod1 = new Producer(md.getRingBuffer(),"prod1");//starts Producer thread
		//Thread.sleep(50);//main waits for 50 ms before creating new producer
		//Producer prod2 = new Producer(md.getRingBuffer(),"prod2");
		//Thread.sleep(50);//main waits for 50 ms before creating new producer
		//Producer prod3 = new Producer(md.getRingBuffer(),"prod3");
		//Thread.sleep(500);
		
		
		//Note: no event handlers can be added once the disruptor starts
		
				
		System.out.println("Exiting Main...");//main doesn't wait for other threads
		//Note that Producer threads will stop but consumer threads will continue running.
	}

}
