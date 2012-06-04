'''
Created on May 19, 2012

@author: Jason Huang

http://www.careercup.com/question?id=13262681

'''


def find_depth(st):
    
    max_dep = -1
    dep = -1
    stack = []
    err = False
    
    for c in st:
        if(c == '('):
            stack.append(c)
            dep += 1
        elif(c == '0'):
            stack.append(c)
        elif(c == ')'):
            if(len(stack) < 3 or stack[-1] != '0' or stack[-2] != '0' or stack[-3] != '('):
                err = True
                break
            else: # pop
                stack = stack[:-3]
                dep -= 1
                if(stack):
                    stack.append('0')
        else:
            err = True
            break
        
        max_dep = max(dep, max_dep)

    if(err or stack):
        return -1
    else:
        return max_dep

if __name__ == '__main__':
    
    print(find_depth('(00)'))
    print(find_depth('((00)0)'))
    print(find_depth('((00)(00))'))
    print(find_depth('((00)(0(00)))'))
    print(find_depth('((00)(0(0(00))))'))
    print(find_depth('x'))
    print(find_depth('0'))
    print(find_depth('()'))
    print(find_depth('((00))'))
        