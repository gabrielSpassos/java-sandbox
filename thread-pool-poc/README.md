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