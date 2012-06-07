'''
Created on Jun 5, 2012

@author: Jason Huang
'''

'''
You have a room-full of balances and weights. Each balance weighs ten pounds and is considered perfectly balanced when
the sum of weights on its left and right sides are exactly the same. You have placed some weights on some of the balances,
and you have placed some of the balances on other balances. Given a description of how the balances are arranged and how
much additional weight is on each balance, determine how to add weight to the balances so that they are all perfectly
balanced.
There may be more than one way to balance everything, but always choose the way that places additional weight on the
lowest balances.
The input file will begin with a single integer, N, specifying how many balances there are.
Balance 0 is specified by lines 1 and 2, balance 1 is specified by lines 3 and 4, etc...
Each pair of lines is formatted as follows:
WL <balances>
WR <balances>
WL and WR indicate the weight added to the left and right sides, respectively. <balances> is a space-delimited list of the
other balance that are on that side of this balance. <balances> may contain zero or more elements.
Consider the following input:
4
0 1
0 2
0
0 3
3
0
0
0
Balance 0 has balance 1 on its left side and balance 2 on its right side
Balance 1 has balance 3 on its right side
Balance 2 has three pounds on its left side
Balance 3 has nothing on it
Since balance 3 has nothing on it it is already perfectly balanced, and weighs a total of 10 pounds.
Balance 2 has no other balance on it, so all we need to do is balance it by putting three pounds on its right side. Now it
weighs a total of 16 pounds.
Balance 1 has balance three on its right side, which weighs 10 pounds, so we just put 10 pounds on its left side. Balance 1
weighs a total of 30 pounds.
Balance 0 has balance 1 on its left side (30 pounds), and balance 2 on its right side (16 pounds), we can balance it by adding
14 pounds to the right side.
The output should be N lines long, with the nth line listing the weight added to the nth balance, formatted as follows:
<index>: <weight added to left side> <weight added to right side>

So the output for this problem would be:
0: 0 14
1: 10 0
2: 0 3
3: 0 0
'''

import sys

if __name__ == '__main__':
    
    N = int(sys.stdin.readline())
    balances = []
    for i in range(N):
        balances.append(([int(x) for x in sys.stdin.readline().split(' ')],
                         [int(x) for x in sys.stdin.readline().split(' ')]))
    
    sortedBalance = []
    
    # topological sort
    adjList = []
    inEdgeCount = [0] * N
    for b in balances:
        adjList.append(set(b[0][1:] + b[1][1:]))
        for nei in adjList[-1]:
            inEdgeCount[nei] += 1
            
    S = {i for i in range(N) if inEdgeCount[i] == 0}
    while(S):
        n = S.pop()
        sortedBalance.append(n)
        for nei in adjList[n]:
            inEdgeCount[nei] -= 1
            if(inEdgeCount[nei] == 0):
                S.add(nei)
    
    sortedBalance.reverse()
    
    resultBalance = [0] * N
    weight = [0] * N

    for b in sortedBalance:
        left = balances[b][0][0]
        for p in balances[b][0][1:]:
            left += weight[p]
        right = balances[b][1][0]
        for p in balances[b][1][1:]:
            right += weight[p]
        if(left >= right):
            resultBalance[b] = (0, left-right)
            weight[b] = 2 * left + 10
        else:
            resultBalance[b] = (right - left, 0)
            weight[b] = 2 * right + 10
    
    for i in range(N):
        print(str(i) + ":", resultBalance[i][0], resultBalance[i][1])
    