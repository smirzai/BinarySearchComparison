BinarySearchComparison
======================

This project is to compare the performance of Binary tree implementation In two ways.
1. The asynchronous way using Aker and Scala. The source for this part comes from Coursera "Principles of reactive programming" excersize for week 5 "Actor Binary tree" 
https://class.coursera.org/reactive-001/assignment/view?assignment_id=17

because of the terms I am not going to share it publicly. It is using Aker to create an asynchronous Binary tree. insert into the tree are asynchronously ditributed between nodes.


2. The java implementation based on Immutable Binary search tree. By each insert a new immutable binary search tree is returned. It is got from http://people.cis.ksu.edu/~rhowell/viewer/


To the comparison, a high number of insertions into the tree are done and timings measured. 