# Task Scheduler Application

## Overview
This application is a task scheduling system that allows for adding, displaying, and removing tasks with varying priorities and dates. It is designed to be run in a multithreaded environment where tasks can be manipulated concurrently.

## Race Conditions in the Unsafe Version

### Adding Tasks
- **Issue**: Concurrent execution of `addTask` by multiple threads can lead to both threads reading the state of the `tasks` list before either thread has added its new task. This can result in one of the new tasks not being added.
- **Impact**: Potential loss of tasks as they overwrite each other, leading to data inconsistency.

### Removing Tasks
- **Issue**: If `removeTask` is called concurrently with `addTask` or `displayTasks`, the `tasks` list may be modified while it is being iterated or modified by another thread.
- **Impact**: Possible incorrect behavior or a `ConcurrentModificationException` due to the unexpected mutation of the list during iteration.

### Displaying Tasks
- **Issue**: The `displayTasks` method can exhibit inconsistencies if it is executed while other threads are adding or removing tasks.
- **Impact**: Inconsistent output, such as missing tasks or duplicate entries, due to simultaneous list modification.

## Thread Safety Measures
The thread-safe version employs `ReentrantLock` to ensure exclusive access to the shared list, thus preventing race conditions. Each action that modifies the list is surrounded by a lock to ensure atomic operations.

### Key Thread Safety Strategies:
1. **Exclusive Locks:** The `ReentrantLock` is used to wrap critical sections in `addTaskRunnable`, `removeTaskRunnable`, and `JobScheduler` classes to prevent concurrent access issues.
2. **Consistent State in Task Objects:** The `Task` class uses locks in its getters and `toString` method to maintain a consistent state when accessed by multiple threads.
3. **Controlled Thread Termination:** A `done` flag, along with locking, allows threads to be terminated gracefully without interrupting ongoing operations.

By employing these synchronization mechanisms, the application ensures that only one thread can modify the task list at a time, thereby preventing race conditions and ensuring the consistency of the task list.
