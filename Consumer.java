package ProducerConsumer;
import com.lmax.disruptor.EventHandler;

import message.MsgDisruptor;
import message.MsgObject;
import stopWatch.StopWatch;

/*create a consumer*/
public class Consumer implements EventHandler<MsgObject>{//not implementing Runnable here
	String consumerName;
	MsgDisruptor md;
	public Consumer(String consumerName,MsgDisruptor md){
		this.consumerName=consumerName;
		this.md=md;
		md.handleWithConsumer(this);//allow single disruptor to handle messages with this consumer
	}
	
	
	/*MyEventConsumer has to implement EventHandler<T>.onEvent(T,long,boolean), which I can't do anything about */
    public void onEvent(MsgObject msg, long sequence, boolean endOfBatch)
    {
    	
    		//how to prevent 2/more consumers from reading 1 object simultaneously while it hasn't been handled
			if(!msg.isHandled){
				
				msg.isNowHandled();
				/*setting this bool first makes this block relatively more atomic,
				 *  since it denies access to a concurrently accessing thread a little faster */
    		
				System.out.println("Consumer: Message: "+msg.name+" consumed by Consumer: "+consumerName);
				/*RingBuffer sends the actual object located at the sequence number, not a copy,
				 * so we can modify the ringBuffer from here*/
				
				StopWatch.msgConsumed();//decrements totalMsgCount in Stopwatch
				/*If consumed message count equals total messages produced, stopwatch automatically stops*/
        
			}
    	
    	
      
    }
	

}

