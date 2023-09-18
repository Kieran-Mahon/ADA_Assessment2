## ADA Assessment 2

This project is my group's (myself and a friend) answers to our Algorithm Design & Analysis paper's second assessment.

This assessment was about using the 3 different methods (Brute Force, Greedy, Exact) to subdivide a M by N piece of land to gain the highest profit.

The program we designed has an UI to visualise the best vision of land.


## Methods

* The **Brute Force** method goes over every single possible subdivision and selects the best highest profit one. This method had the highest time to complete as there are multiple locations where a division was already calculated.

* The **Greedy** method is similar to the Brute force method but instead of finding the best by going down each possible path, it decides which path to go down by taking the one with the current best profit. This can cause issues as depending on the costs of the land, the method could end early as it would have thought it’s the best division it decided on via being greedy is the best while if it went down a less greedy path it may have found a better division.

* The **Exact** method uses dynamic programming to build out a quick lookup table with all the answers of the divisions. This lookup table allows the method to check if an answer exists before calculating it. This method is by far the quickest out of the three.
