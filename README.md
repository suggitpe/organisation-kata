# Organisation Kata

-----
## Background
Some very senior manager in an office far far away wants to know how many people in his organisation are committing 
code and how many are not.  He is also concerned that people who are committing code may be listed as non-engineers.
He is trying to ensure that in his organisation we have plenty of people who are writing code (its an IT organisation 
after all).

He needs your help!  He has a huge spreadsheet of people with people data but cant make head nor tail of it.  Can you help him?

----
## Instructions
We need to help our big friendly senior manager to visualise the data of people in 

### Requirements
1. Can read people information from a CSV with details of "Name", "Rank", "Manager", "Engineer", "Committer"
1. Can create a tree with lots of people, who all have a manager also in the tree (except this one guy).  Note the data 
will not be in order so tree partitioning must be avoided.
1. Can print the tree to a logger
1. Can use dot notation to visualise the graph in different ways:
    1. by rank
    1. by engineering status
    1. by committer status