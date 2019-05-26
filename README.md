# Concurrent_Calculation_of_Integral

Demonstrates the use of multithreading,monitors and sockets to calculate an approximation of � using arithmetic integration.


## Methods 

1. Concurrent calculation using threads in a shared memory system.We create as many threads as the cores of the system. We assign to each thread the calculation of one part of the numerical integration and in the end we sum up the individual results.(shared)

2. Concurrent calculation in a distributed memory system.The number of clients-workers is known from the beginning. Every worker receives from the server-master the part that must calculate. After the calculation returns the result to the master, who makes the final summation.(distr)