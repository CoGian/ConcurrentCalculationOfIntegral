# Concurrent_Calculation_of_Integral

Demonstrates the use of multithreading,monitors and sockets to calculate an approximation of pi using arithmetic integration.


## Methods 
1. Sequential Calculation.(seq)

2. Concurrent calculation using threads in a shared memory system.We create as many threads as the cores of the system. We assign to each thread the calculation of one part of the numerical integration and in the end we sum up the individual results.(shared)

3. Concurrent calculation in a distributed memory system.The number of clients-workers is known from the beginning. Every worker receives from the server-master the part that must calculate. After the calculation returns the result to the master, who makes the final summation.(distr)

4. Combination of methods 2 and 3. Each employee performs his / her local calculation using threads (as a shared memory system).(distrNshared)

## Usage & Example

*After compiling in class file directory.

```
java CalcPi <number of steps> <method> (if method is distr Or distrNshared)<number of workers>
```

#### Sequential Calculation
Type in cmd or powershell :  

```
java CalcPi 1000000000 seq
```

##### Outcome:

```
seq results with 1000000000 steps
computed pi = 3,14159265358997080000
difference between estimated pi and Math.PI = 0,00000000000017763568
time to compute = 9,190000 seconds
```

#### Calculation with shared memory
Type in cmd or powershell :  

```
java CalcPi 1000000000 shared
```

##### Outcome:

```
shared results with 1000000000 steps
computed pi = 3,14159264595212300000
difference between estimated pi and Math.PI = 0,00000000763767005196
time to compute = 0,832000 seconds
```

#### Calculation with distributed memory
Type in cmd or powershell :  

```
java CalcPi 1000000000 distr 5
```

##### Outcome:

```
distr results with 1000000000 steps
computed pi = 3,14159265358959550000
difference between estimated pi and Math.PI = 0,00000000000019761970
time to compute = 0,775000 seconds
```

#### Calculation with distributed and shared memory
Type in cmd or powershell :  

```
java CalcPi 1000000000 distrNshared 5
```

##### Outcome:

```
distrNshared results with 1000000000 steps
computed pi = 3,14159265358978650000
difference between estimated pi and Math.PI = 0,00000000000000666134
time to compute = 0,859000 seconds
```

*times may differ 
### For more information regarding this project please check my comments inside the code .
