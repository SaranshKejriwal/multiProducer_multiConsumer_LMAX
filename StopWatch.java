
package stopWatch;

public class StopWatch {
	private static double startTime;
	private static double stopTime;
	private static double timeElapsed;
	private static double timeElapsedSoFar;
	
	private static boolean isStarted=false;
	private static boolean isStopped=true;
	
	
	public static int totalMsgObjects=10;//this contains the total number of messages that all producers will produce
	public static int totalMsgObjectsConsumed=0;//this contains the total number of messages that all consumers have consumed so far
	public static int totalMsgObjectsProduced=0;//this contains the total number of messages that all producers have created so far
	
	public static void setTotalMsgObjects(int totalMsgObjects) {
		StopWatch.totalMsgObjects = totalMsgObjects;
		System.out.println("Total MsgObjects="+StopWatch.totalMsgObjects);
	}
	
	public static int totalProducers=1;//decides the total number of producer threads
	
	public static void setTotalProducers(int totalProducers) {
		StopWatch.totalProducers = totalProducers;
		System.out.println("Total Producers="+StopWatch.totalProducers);
	}

	public static void start(){
		if(isStarted){
			System.out.println("Stopwatch already running");
			return;
		}
		startTime=System.currentTimeMillis();
		//System.out.println("StopWatch started at t="+startTime+" ms");
		isStarted=true;
		isStopped=false;
	}
	
	public static void stop(){
		if(isStopped || !isStarted){
			System.out.println("Stopwatch already stopped");
			return;
		}
		stopTime=System.currentTimeMillis();
		isStarted=false;
		isStopped=true;
		timeElapsed=stopTime-startTime;
		System.out.println("_____________Total Time elapsed= "+timeElapsed+" ms");
	}
	
	public static void getTimeElapsedSoFar(){
		
		if(!isStarted || isStopped){
			System.out.println("Stopwatch not running...no time elapsed so far");
			return;
		}
		timeElapsedSoFar=System.currentTimeMillis()-startTime;
		System.out.println("Time elapsed so far= "+timeElapsedSoFar+" ms");
		//startTime=System.currentTimeMillis();//assuming that stopwatch is started before it is stopped
	}
	
	
	public static void msgConsumed(){
		/*since producer and consumer are working async, changing totalMsgProduced from consumer 
		 * will affect the number of msgObjects that the producer produces after the consumer has started consuming*/
		totalMsgObjectsConsumed++;
		System.out.println("StopWatch: created: "+totalMsgObjectsProduced+"; Consumed: "+totalMsgObjectsConsumed);
		if(totalMsgObjectsConsumed==totalMsgObjects && totalMsgObjectsProduced==totalMsgObjects){
			//when all objects have been created as well as consumed
			System.out.println("________Process Complete for "+totalMsgObjects+" msgObjects. Stopping stopWatch...");
			stop();//so that any consumer thread can be the last consumer
		}
	}
	
	public static void msgProduced(){
		totalMsgObjectsProduced++;
	}
	

}
