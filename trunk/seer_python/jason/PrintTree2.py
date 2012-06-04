'''
Created on Jun 2, 2012

@author: Jason Huang

In the input, AND and OR are binary functions. Each of the two
arguments may either be a string of lower-case letters or a recursive
call to AND or OR.

The output of the method should print a tree-like representation of
the input string (see examples for the format). Moreover, this
tree-like representation should flatten contiguous recursive calls to
AND and OR when doing so does not change the logic of the function
(see examples). The top-down ordering of the leaves of the printed
trees should be the same as their left-right order in the input
string.

You should strive for an algorithm whose running time is O(n + m),
where n is the length of the input string and m is the number of
characters printed in the output. Assume the length of the input
string could be up to millions of characters.


Examples:

input: "AND(a,b)"
output:
AND
 |
 +-a
 |
 +-b

(arguments can be more than one character)
input: "OR(foo,bar)"
output:
OR
 |
 +-foo
 |
 +-bar

(output should flatten boolean operators when doing so cannot change
the value of the root expression)
input: "AND(a,AND(b,c))"
output:
AND
 |
 +-a
 |
 +-b
 |
 +-c

input: "OR(OR(a,b),c)"
output:
OR
 |
 +-a
 |
 +-b
 |
 +-c

input: "AND(OR(a,b),OR(c,d))"
output:
AND
 |
 +-OR
 |  |
 |  +-a
 |  |
 |  +-b
 |
 +-OR
    |
    +-c
    |
    +-d

input: "AND(AND(a,b),OR(OR(OR(c,OR(OR(OR(d,e),f),g)),h),OR(i,AND(AND(j,k),
AND(l,m)))))"
output without flattening (this is incorrect output because flattening has
not been performed):
AND
 |
 +-AND
 |  |
 |  +-a
 |  |
 |  +-b
 |
 +-OR
    |
    +-OR
    |  |
    |  +-OR
    |  |  |
    |  |  +-c
    |  |  |
    |  |  +-OR
    |  |     |
    |  |     +-OR
    |  |     |  |
    |  |     |  +-OR
    |  |     |  |  |
    |  |     |  |  +-d
    |  |     |  |  |
    |  |     |  |  +-e
    |  |     |  |
    |  |     |  +-f
    |  |     |
    |  |     +-g
    |  |
    |  +-h
    |
    +-OR
       |
       +-i
       |
       +-AND
          |
          +-AND
          |  |
          |  +-j
          |  |
          |  +-k
          |
          +-AND
             |
             +-l
             |
             +-m
correct output with flattening:
AND
 |
 +-a
 |
 +-b
 |
 +-OR
    |
    +-c
    |
    +-d
    |
    +-e
    |
    +-f
    |
    +-g
    |
    +-h
    |
    +-i
    |
    +-AND
       |
       +-j
       |
       +-k
       |
       +-l
       |
       +-m
'''

import re

def printTok(tok, opStk, childStk):
    
    # compute prefix
    prefix = ''
    
    i = 0
    
    while(i < len(opStk)):
        previousOp = opStk[i]
        showBar = False
        if(childStk[i] == 'L'):
            showBar = True
        i += 1
        while(i < len(opStk) and opStk[i] == previousOp):
            if(childStk[i] == 'L'):
                showBar = True
            i += 1
        if(showBar or i == len(opStk)):
            prefix += " |"
        else:
            prefix += "  "
        
    if(prefix):
        print(prefix)
        print(prefix[:-1] + "+-", end='')
    print(tok)


def printTree(treeStr):
    tokens = re.split('([(),])', treeStr)
    opStk = []
    childStk = []
    
    
    for tok in tokens:
        if(tok == 'AND' or tok == 'OR'):
            if(not opStk or opStk[-1] != tok):
                printTok(tok, opStk, childStk)
            opStk.append(tok)
        elif(tok == ')'):
            opStk.pop()
            childStk.pop()
        elif(tok == '('):
            childStk.append('L')
        elif(tok == ','):
            childStk.pop()
            childStk.append('R')
        elif(tok):
            printTok(tok, opStk, childStk)


if __name__ == '__main__':
    treeStr = 'AND(OR(a,OR(j,h)),OR(c,AND(x,y)))'
#    treeStr = 'AND(a,b)'
    printTree(treeStr)
    treeStr = 'AND(a,AND(b,c))'
    printTree(treeStr)
    treeStr = 'AND(AND(a,b),OR(OR(OR(c,OR(OR(OR(d,e),f),g)),h),OR(i,AND(AND(j,k),AND(l,m)))))'
    printTree(treeStr)
    