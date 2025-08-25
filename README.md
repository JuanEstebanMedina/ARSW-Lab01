# ARSW-Lab01
## PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH

**Colombian School of Engineering Julio Garavito**  
**Software Architectures - ARSW**  
**Laboratory Number 1**

**Members:**
- Juan Esteban Medina Rivas
- María Paula Sánchez Macías

---

## Part I - Introduction to Java Threads

**start():** This function starts a new thread that runs independently in parallel. This method uses parallelism.

**run():** When calling this function, the process executes in the current thread. This method uses sequential execution.

### Output with start()
The three threads start and print numbers simultaneously.
<img src="img/1. PartI-start().png">

### Output with run()
The numbers are shown in complete order since threads start when the other thread finishes, not at the same time.
<img src="img/2. PartI-run().png">

---

## Part II - Black List Search Exercise

When refactoring, we created a new Class in the Threads folder which is the class that contains the lifecycle of our Thread.

We made method overloading in the HostBlackListsValidator class, adding a new CheckHost method that receives the IP address to check and the number of threads to create, and searches in the IP blacklist by dividing into equal segments so that each thread performs its respective search.

### Part II.I:

**How could the implementation be modified to minimize the number of queries in these cases?**
We thought that a possible solution could be having a variable shared by all threads to be able to check when an IP has already been classified as untrusted.

**What new element would this bring to the problem?**
We would have to synchronize the threads (synchronized), we would have to constantly verify the state of the variable, there would be several threads modifying the counter at the same time.

---

## Part III - Performance Evaluation

**IP:** 202.24.34.55

### → Single thread
**VisualVM**
<img src="img/3. PartIII-1Thread.png">

**Result**
<img src="img/3.1 PartIII-1ThreadResult.png">

### → As many threads as processing cores
These lines of code were added to Main:
<img src="img/4.0 PartIII-Thread=CoresCodigo.png">

**VisualVM**
<img src="img/4. PartIII-Thread=Cores.png">

**Result:** 16 processors
<img src="img/4.1 PartIII-Thread=CoresResult1.png">

### → As many threads as double the processing cores
**VisualVM**
<img src="img/5. PartIII-NumberCores.png">

### → 50 threads
**VisualVM**
<img src="img/6. PartIII-50Threads.png">

### → 100 threads
**VisualVM**
<img src="img/7. PartIII-100Threads.png">

As each time the number of threads increased, the time decreased considerably. When reaching 100 threads, the process executed in one second or less, preventing VisualVM from providing information in the graphs.

### Time vs. Number of Threads Graph
<img src="img/8. PartIII-TimevsThreads.png">

**Taking into account:**
- 1 thread: 3MIN 25 SEC
- 16 threads: 57SEC
- 32 threads: 3SEC
- 50 threads: 2SEC
- 100 threads: 1SEC OR LESS

It can be evidenced that for this exercise, parallelization ends up being very effective, where after 32 threads the graph stabilized and after that point it's not worth putting so many threads to work knowing that the benefits are practically the same.

---

## Part IV - Black List Search Exercise

**Why is the best performance not achieved with 500 threads? How does this performance compare when using 200 threads?**

P = 1. We assume the entire program is parallelizable because we haven’t learned how to isolate non-parallel sections yet. The result is deterministic, so under this assumption we treat the workload as fully parallel.

Therefore, Amdahl’s law gives S(n) = n\
S(500) = 500 \
S(200) = 200

Theoretically, performance should improve when using 500 threads. In practice, the best performance may not occur at 500 threads due to hardware limits and overheads. This machine exposes 16 processing cores according to *Runtime.getRuntime().availableProcessors()*. When *n* exceeds the number of cores, extra threads compete for the same resources. As a result, total time may stop improving, or may even get worse.


**How does the solution perform using as many processing threads as cores compared to the result of using twice as many?**

Since processors typically have two threads per physical core, 32 threads are barely sufficient for hardware limitations and therefore perform better than running it for *n=16*.

**According to the above, if for this problem, instead of 100 threads on a single CPU, 1 thread could be used on each of 100 hypothetical machines, would Amdahl's law apply better? If, instead, c threads were used on 100/c distributed machines (where c is the number of cores on those machines), would this improve the situation? Explain your answer.**

100 threads on a single CPU would exceed the physical limit of the component, which could be counterproductive and even worsen test performance in some scenarios. With this in mind, performance would be improved by using 1 thread on 100 different machines, as the threads would not need to compete for resources, thus allowing complete parallelism without hardware limitations.