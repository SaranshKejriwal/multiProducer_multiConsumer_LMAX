# multiProducer_multiConsumer_LMAX

This project uses LMAX disruptor to develop a solution to the classic producer-consumer problem.

Since we're dealing with a scenario containing multiple producers and multiple consumers,
each producer and/or consumer is a separate thread.

Main.java contains the main method, which creates the producer and consumer threads, initiates 
the LMax ringBuffer and starts the stopwatch.

The producer threads fill the ringBuffer and consumers empty it.

The Stopwatch stops when all produced messages are consumed
