package ProducerConsumer;

import com.lmax.disruptor.RingBuffer;

import message.MsgObject;
import stopWatch.StopWatch;

/** source for events */
public class Producer implements Runnable {

	private final RingBuffer<MsgObject> ringBuffer;
	String producerName;
	Thread t;

	public Producer(RingBuffer<MsgObject> ringBuffer, String producerName)// constructor
	{
		this.ringBuffer = ringBuffer;// gets ringBuffer of disruptor declared in PSVM
		this.producerName = producerName;
		t = new Thread(this, producerName);
		t.start();
	}

	public void run(){
    	
    	for (int i = 0; i < StopWatch.totalMsgObjects/StopWatch.totalProducers; i++) {
    		//each thread produces a no. of messages specified by Main
		String msgName=producerName+"_msg_"+(i+1);
		produceMsg(msgName,i);//implemented below
		StopWatch.msgProduced();
		
		}
    	
    	    	
    }

	public void produceMsg(String name, int i) {
		long sequence = ringBuffer.next();// (long) sequence is the sequence
											// number/index in the ringBuffer

		try {
			MsgObject msg = ringBuffer.get(sequence);// take val from ringBuffer
														// and store to Bean
			msg.setMsgValue(i); // setMyValue is implemented here, not inherited
			msg.setMsgName(name);
			System.out.println("Producer: Message "+name+" produced at buffer seq no. "+sequence);
		} finally {
			ringBuffer.publish(sequence);
		}
	}

}
