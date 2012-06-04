'''
Created on May 18, 2012

@author: Jason Huang

We are given 4 numbers say n1, n2, n3, n4. We can place 
them in any order and we can use mathematical operator 
+, -, *, / in between them to have final result as 24. 
Write an algorithm for this, it will take 4 numbers and 
return false or true whether final result 24 is possible 
with any combination.
'''


def expressable0(numList, target, curr, index):
    
    N = len(numList)
    if(index == N):
        return curr == target
    
    if(expressable0(numList, target, curr + numList[index], index + 1)):
        return True
    elif(expressable0(numList, target, curr - numList[index], index + 1)):
        return True
    elif(expressable0(numList, target, curr * numList[index], index + 1)):
        return True
    elif(expressable0(numList, target, curr // numList[index], index + 1)):
        return True

    return False

def expressable(numList, target):
    return expressable0(numList, target, numList[0], 1)


if __name__ == '__main__':
    numList = [2, 2, 3, 2]
    target = 24
    
    print(expressable(numList, target))