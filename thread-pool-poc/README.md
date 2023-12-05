# Thread Pool POC

> Build a task framework where you submit tasks to your own pool of threads.

### Constraints

- no external libraries
- implement stress test

### Usage
```java
Task<R> {
    R execute();
}

TaskA<PersonDTO> implements Task {
    PersonDTO execute() {
        return new PersonDTO();
    };
}

GabrielPool.addTask(new TaskA());
GabrielPool.addTask(new TaskA());
GabrielPool.addTask(new TaskA());
GabrielPool.addTask(new TaskA());
GabrielPool.addTask(new TaskA());
```

### Results of tasks with delay
| # of tasks | Time took in ms | 
|:----------:|:---------------:|
|     1      |        2        |
|    1000    |        5        |
|  1000000   |       365       |
|  5000000   |  4972 (4.972s)  |
|  30000000  |        1        |

### Results of tasks without delay
| # of tasks | Time took in ms | 
|:----------:|:---------------:|
|     1      |        3        |
|    1000    |        5        |
|  1000000   |       208       |
|  5000000   |       717       |
|  30000000  |        1        |