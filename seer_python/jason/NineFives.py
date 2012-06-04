'''
Created on May 18, 2012

@author: Jason Huang

Combining nine 5s with any number of the operators +, -, *, /, (, ) , 
what is the smallest positive integer that cannot be expressed?
'''

if __name__ == '__main__':
    
    dictList = [ {} for i in range(10) ]
    dictList[1][5] = "5";
    dictList[1][-5] = "-5";
    
    limit = 1000000
    
    for i in range(2,10):
        targetList = dictList[i]
        
        # + and *
        for j in range(1, i // 2):
            op1List = dictList[j];
            op2List = dictList[i - j]
            
            for x,y in [ (x,y) for x in op1List for y in op2List]:
                z = x + y
                if z not in targetList and -limit < z and z < limit:
                    targetList[z] = "(" + op1List[x] + "+" + op2List[y] + ")"
                z = x * y
                if z not in targetList and -limit < z and z < limit:
                    targetList[z] = "(" + op1List[x] + "*" + op2List[y] + ")"
                
        # - and /
        for j in range(1, i):
            op1List = dictList[j];
            op2List = dictList[i - j]
            
            for x,y in [ (x,y) for x in op1List for y in op2List]:
                z = x - y
                if z not in targetList and -limit < z and z < limit:
                    targetList[z] = "(" + op1List[x] + "-" + op2List[y] + ")"
                
                if(y != 0):
                    z = x // y
                    if z not in targetList and -limit < z and z < limit:
                        targetList[z] = "(" + op1List[x] + "//" + op2List[y] + ")"

    answerList = dictList[9]
    val = 1
    
    while( val in answerList ):
        print(str(val) + ": " + answerList[val])
        val += 1

    print( val )
    