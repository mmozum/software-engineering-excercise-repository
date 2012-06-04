'''
Created on Jun 1, 2012

@author: Jason Huang
'''


def printAllCombinationsImpl(li, target, soFar, soFarList, idx):
    
    if(soFar > target or idx >= len(li)):
        return
    
    if(soFar == target):
        print(soFarList)
        return
    
    soFarList.append(li[idx])
    printAllCombinationsImpl(li, target, soFar + li[idx], soFarList, idx)
    
    soFarList.pop()
    printAllCombinationsImpl(li, target, soFar, soFarList, idx + 1)


def printAllCombinations(li, target):
    """
    Given a target number, and a series of candidate numbers, print out 
    all combinations, so that the sum of candidate numbers equals to the 
    target. Candidate can be reused multiple times.
    """
    printAllCombinationsImpl(li, target, 0, [], 0)
    
def printAllCombinationsImplII(li, target, soFar, soFarList, idx):
    
    if(soFar > target or idx >= len(li)):
        return
    
    if(soFar == target):
        print(soFarList)
        return
    
    nextIndex = idx + 1
    while(nextIndex < len(li) and li[nextIndex] == li[idx]):
        nextIndex += 1

    soFarList.append(li[idx])
    printAllCombinationsImplII(li, target, soFar + li[idx], soFarList, nextIndex)
    soFarList.pop()
    
    printAllCombinationsImplII(li, target, soFar, soFarList, nextIndex)

def printAllCombinationsII(li, target):
    """
    Given a target number, and a series of candidate numbers, print out 
    all combinations, so that the sum of candidate numbers equals to the 
    target. Candidate can be at most once.
    """
    li.sort()
    printAllCombinationsImplII(li, target, 0, [], 0)

if __name__ == '__main__':
    li = [10, 1, 2, 7, 6, 1, 5]
    s = 8
    printAllCombinationsII(li, s)
