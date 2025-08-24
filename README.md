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
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/1.%20PartI-start().png)

### Output with run()
The numbers are shown in complete order since threads start when the other thread finishes, not at the same time.
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/2.%20PartI-run().png)

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
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/3.%20PartIII-1Thread.png)

**Result**
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/3.1%20PartIII-1ThreadResult.png)

### → As many threads as processing cores
These lines of code were added to Main:
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/4.0%20PartIII-Thread%3DCoresCodigo.png)

**VisualVM**
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/4.%20PartIII-Thread%3DCores.png)

**Result:** 16 processors
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/4.1%20PartIII-Thread%3DCoresResult1.png)

### → As many threads as double the processing cores
**VisualVM**
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/5.%20PartIII-NumberCores.png)

### → 50 threads
**VisualVM**
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/6.%20PartIII-50Threads.png)

### → 100 threads
**VisualVM**
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/7.%20PartIII-100Threads.png)

As each time the number of threads increased, the time decreased considerably. When reaching 100 threads, the process executed in one second or less, preventing VisualVM from providing information in the graphs.

### Time vs. Number of Threads Graph
![](https://github.com/JuanEstebanMedina/ARSW-Lab01/blob/partIII/img/8.%20PartIII-TimevsThreads.png)

**Taking into account:**
- 1 thread: 3MIN 25 SEC
- 16 threads: 57SEC
- 32 threads: 3SEC
- 50 threads: 2SEC
- 100 threads: 1SEC OR LESS

It can be evidenced that for this exercise, parallelization ends up being very effective, where after 32 threads the graph stabilized and after that point it's not worth putting so many threads to work knowing that the benefits are practically the same.
