'''
Created on Jun 1, 2012

@author: Jason Huang
'''
from random import shuffle
from collections import namedtuple

def printTreeImpl(treeDict, node, level):
    print( '   ' * level, treeDict[node].label )
    
    for child in treeDict[node].children:
        printTreeImpl(treeDict, child, level + 1)


def printTree(treeArr):
    
    Node = namedtuple('Node', ['id','pid','label','children'])
    
    treeDict = {n[0]:Node(n[0],n[1],n[2],[]) for n in treeArr}
    
    for nid,pid,_ in treeArr:
        if(nid != pid):
            treeDict[pid].children.append(nid)
    
    # find root
    root = list(treeDict.keys())[0]
    while(treeDict[root].pid != root):
        root = treeDict[root]

    printTreeImpl(treeDict, root, 0)

if __name__ == '__main__':
    treeArr = []
    treeArr.append((0, 0, 'A')) # id, parent_id, label
    treeArr.append((1, 0, 'B'))
    treeArr.append((2, 0, 'C'))
    treeArr.append((3, 1, 'D'))
    treeArr.append((4, 1, 'E'))
    treeArr.append((5, 1, 'F'))
    treeArr.append((6, 4, 'G'))
    
    shuffle(treeArr)
    printTree(treeArr)
    