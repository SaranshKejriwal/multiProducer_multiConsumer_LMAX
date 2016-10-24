package message;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import ProducerConsumer.Consumer;
import lmaxUtils.MsgFactory;

public class MsgDisruptor {

	public Disruptor<MsgObject> disruptor;
	Executor executor;
	final int bufferSize = 1024;//has to be a power of 2
	MsgFactory factory;
	RingBuffer<MsgObject> ringBuffer;//ringBuffer base type declared here

	public MsgDisruptor() {

		// Executor that will be used to construct new threads for consumers
		executor = Executors.newCachedThreadPool();
		
		 //The factory for the event
        factory = new  MsgFactory();
		
        // Construct the Disruptor
		disruptor = new Disruptor<>(factory, bufferSize, executor);
		ringBuffer = disruptor.getRingBuffer();
		
        

	}

	public RingBuffer<MsgObject> getRingBuffer() {
		return ringBuffer;
	}

	public void handleWithConsumer(Consumer c){
		disruptor.handleEventsWith(c);// Connect the consumer
	}
	
	

}

