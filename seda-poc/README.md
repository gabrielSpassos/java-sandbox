# Staged Event Driven Architecture POC

### Logs
```
Published message Message{id='3b5f1fa3-0217-403d-b8ad-8f3201a9946f', content=99} to inbound queue

Inbound processing message Message{id='162a6831-ee1a-4ec5-94e5-c8147c6ac80b', content=0}
Successfully processed message Message{id='162a6831-ee1a-4ec5-94e5-c8147c6ac80b', content=0}

Inbound processing message Message{id='f766d0e0-cb7d-43bb-8da0-a5917c0a9970', content=1}
Failed to processed message Message{id='f766d0e0-cb7d-43bb-8da0-a5917c0a9970', content=1} sending to retry

Retry processing message Message{id='ec866119-8cfb-449f-9647-e4e0ad0edbca', content=15}
Failed to process retry message Message{id='ec866119-8cfb-449f-9647-e4e0ad0edbca', content=15} sending to DLQ
```

### References

- V0: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory-v0
- V1: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory
- V2: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory-v2
- V3: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory-v3
- V4: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory-v4
- V5: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory-v5
- V6: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory-v6
- V7: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory-v7
- V8: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory-v8
- V9: https://github.com/diegopacheco/java-pocs/tree/master/pocs/seda-pipeline-memory-v9

### Notes:

- V0:
  - no seda
  - for each submitted task will create this job that consists on three separated steps
    - each step will execute after the success of the previous one

- V1
  - on PipelineManager we are creating the abstraction to queues QueueManager
  - we create each worker saying what will be the input and output queue
    - that's the way we "glue" the process between the queue
  - we create and execute threads manually

- V2
  - the backpressure (for this POC is the sleep time) is controlled by feature flag
  - we have full management on flags on realtime
  - everytime that a message is processed by a worker (success or failed) is created a metric
  - these metrics also are possible to access on realtime
  - when we fetch the data we use just copy of the data (structure)

- V3 
  - changed the queue to LinkedBlockingDeque
  - we have different pools for each worker type
    - is created several instances of each worker on the specific pool type

- V4
  - we have an realtime "flag" to disable and enable the workers

- V5
  - created a flow using queues, where when the event is successfully processed by the worker 
  it send the event to the next queue
  - create event class with a map to have event metadata

- V6
  - to create the thread pool is using virtual threads `Thread.ofVirtual().factory()`

- V7
  - Added leaky bucket algorithm https://www.geeksforgeeks.org/leaky-bucket-algorithm/
    - control burst input, with a fixed output rate

- V8 
  - added snapshot mechanism
    - added each worker on a map to fetch the events to later store this info on the filesystem
      - also added a generic way to remove the event from the map after the process with done with the event
  - created context object to store the objects needed on the workers