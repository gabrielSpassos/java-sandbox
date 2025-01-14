# Block Placement Queries POC

> https://leetcode.com/problems/block-placement-queries/description/

### Own Notes:

* query type 1 always starts => [1, x] => `x` is the place to build the obstacle
* query type 2 always starts => [2, x, sz] => `x` is the max limit to put the block and `sz` is the size of the block that should be places between [0, x]
