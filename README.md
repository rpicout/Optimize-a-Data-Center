# Optimize-a-Data-Center


## Introduction
For over ten years, Google has been building data centers of its own design, deploying thousands of machines in locations around the globe. In each of these locations, batteries of servers are at work around the clock, running services we use every day, from Google Search and YouTube to the judge system of Hash Code.

Data center design is a multi-factor optimization problem. Surely, we want to get as much computing capacity as possible - the services need a lot of resources today and will be asking for more tomorrow. But maximizing the raw capacity is not the only goal: it’s equally important to ensure that the computing capacity is provided reliably even in the face of inevitable hardware failures.



## Task
Servers in a data center are **physically divided into rows**. Rows can share resources such as electric power. If such a shared resource fails, we assume that the entire row is lost and all servers in that row become **unavailable**.

Servers in a data center are also **logically divided into pools**. Each server belongs to exactly one pool, and provides it with some amount of computing resources, called **capacity**. The capacity of a pool is the sum of the capacities of the **available** servers in that pool.

To ensure reliability of a pool, it is therefore desirable to distribute its servers between different rows. Then, when a row fails, the pool can continue to operate on the servers from the remaining rows (albeit with a reduced capacity because of the unavailable servers). The **guaranteed capacity** of a pool is the minimum capacity it will have when at most one data center row goes down.

Given a schema of a data center and a list of available servers, your goal is to assign servers to **slots within the rows** and to **logical pools** so that the **lowest guaranteed capacity** of all pools is maximized.



## Problem description

### Slots
A data center is modeled as **rows** of **slots** in which servers can be placed.

Some of the slots might be unavailable (e.g. because of other installations occupying some of the slots).

### Servers
Each server is characterized by its **size** and **capacity**. Size is the number of consecutive slots occupied by the machine. Capacity is the total amount of CPU resources of the machine (an integer value).

### Input data
The input data is provided in a plain text file containing exclusively ASCII characters with lines terminated with a single ‘\n’ character at the end of each line (UNIX­style line endings).

The file consists of:
- one line containing the following five natural numbers separated by single spaces:
  - **R**(1 ≤ R ≤ 1000) denotes the number of rows in the data center,
  - **S**(1 ≤ S ≤ 1000) denotes the number of slots in each row of the data center,
  - **U**(0 ≤ U ≤ R × S) denotes the number of unavailable slots,
  - **P**(1 ≤ P ≤ 1000) denotes the number of pools to be created,
  - **M**(1 ≤ M ≤ R × S) denotes the number of servers to be allocated;
- **U** subsequent lines describing the unavailable slots of the data center. Each of these lines contains two natural numbers separated by a single space: **ri** and **si** ( 0 ≤ ri < R, 0 ≤ si < S ) denoting the row number (**ri**) and the slot number within the row (**si**) of the particular unavailable lot;
- **M** subsequent lines describing the servers to be allocated. Each of these lines contains two natural numbers separated by a single space: **zi** and **ci** (1 ≤ zi ≤ S, 1 ≤ ci ≤ 1000) , denoting the size of the server (**zi**), ie. the number of slots that it occupies, and the capacity (**ci**) of the machine.



## Submissions 

### File format
A submission file has to be a plain text file containing exclusively ASCII characters with lines terminated with either a single ‘\n’ character at the end of each line (UNIX-style line endings) or ‘\r\n’ characters at the end of each line (Windows-style line endings).

The file has to consists of **M** lines describing the allocation of the individual servers, in the same order as  they appeared in the input file. Each of these lines has to contain either:

- three natural numbers separated by single spaces: **ari**, **asi**, **api** (0 ≤ ari < R, 0 ≤ asi < S, 0 ≤ api , P) denoting the allocated row (**ari**) and slot within the row (**asi**) for the server, and the allocated logical pool for it (**api**);
- the single lowercase “x” letter, if the server is left unallocated.

For servers that occupy more than one slot, the slot of the lowest index (leftmost in the picture below) should be indicated as the position of the server.

### Validation
For the solution to be accepted, it has to meet the following criteria:
- the format of the file has to match the description above,
- each slot of the data center has to be occupied by at most one server,
- no server occupies any unavailable slot of the data center,
- no server extends beyond the slots of the row.

### Score
For each pool, its **guaranteed capacity** is defined as the lowest total capacity of its running servers when exactly one of the data center rows is unavailable (over all possible rows). The score awarded to the submission is the **lowest** guaranteed capacity of all pools. The goal is to maximize this scor
