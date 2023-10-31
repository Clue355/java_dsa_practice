# Dynamic Connectivity

Given a set of N objects. Use the Union command to connect two objects and the 
Find/Connected query to check if those two objects are connected.

- Union Command: Connect two objects
- Find/Connected query: is there a path connecting the two objects?

Model of objects:
`[0,1,2,3,4,5,6,7,8,9]`

It's more convenient to name objects 0 to n-1
- use integers as array index
- suppress details not relevant to union find

We assume "is connected to" is an equivalence relation:
- Reflexive: p is connected to p
- Symmetric: if p is connected to q, then q is connected to p
- Transitive: if p is connected to q and q is connected to r, then p is
connected to r

Connected components: Maximal set of objects that are mutually connected

ex:

```
0    1    2 -- 3
   /      |  / |
4 -- 5    6    7
```

3 connected components
{0} {1,4,5} {2,3,6,7}

Implementing the operations
- Find query: check if two objects are in the same component
- Union command: replace components containing two objects with their union

If a union command is called on the example numbers union(2,5) then
we need to combine the set of connected components. 

From: {0} {1,4,5} {2,3,6,7}\
To: {0} {1,2,3,4,5,6,7}

Goal: Design efficient data structure for union-find
- Number of objects N can be huge
- Number of operations M can be huge
- Find queries and union commands may be intermixed

Example of Data structure

Public class UF:
- UF(int N): initialize union-find data structure with N objects(0 to n-1)
- void union(int p, int q) add connection between p and q
- boolean connected(int p, int q): are p and q in the same component
- int find(int p): component identifier for p(0 to n-1)
- int count(): number of components

Dynamic-connectivity client
- Read in number of objects N from standard input
- Repeat:
    - read in pair of integers from standard input
    - if they are not yet connected, connect them and print out pair

ex.txt

```
10
4 3
3 8
6 5
9 4
2 1
// 8 9
5 0
7 2
6 1
// 1 0
// 6 7
```

```Java
public class Main {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
        }
    }
}
```