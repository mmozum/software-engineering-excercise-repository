'''
Created on Jun 6, 2012

@author: jjhuang
'''

def findSum(nums):
    
    K = 20 * 100000
    dp = [[]] + [None] * K
    for v in nums:
        for i in range(K, v-1, -1):
            if(dp[i - v] != None):
                if(dp[i] != None):
                    return dp[i],dp[i-v] + [v]
                else:
                    dp[i] = dp[i-v] + [v]
    return None
                        

if __name__ == '__main__':
    
    in_file = open('input.txt')
    out_file = open('out.txt', mode='w', encoding='utf-8')
    
    T = int(in_file.readline())
    

    for t in range(1, T+1):
        nums = [int(i) for i in in_file.readline().split(' ') ]
        N = nums[0]
        nums = nums[1:]
        ret = findSum(nums)
        if(ret == None):
            print('Case #{0}:\n{1}'.format(t, 'Impossible'))
            out_file.write('Case #{0}:\n{1}\n'.format(t, 'Impossible'))
        else:
            a,b = ret
            a = ' '.join([str(x) for x in a])
            b = ' '.join([str(x) for x in b])
            print('Case #{0}:\n{1}\n{2}'.format(t, a, b))
            out_file.write('Case #{0}:\n{1}\n{2}\n'.format(t, a, b))

    out_file.close()
    in_file.close()