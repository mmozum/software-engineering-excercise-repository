/**
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
 */
package common.tree;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class PrintAndOrTree {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		//String str = scanner.nextLine();
		String str = "AND(AND(a,b),OR(OR(OR(c,OR(OR(OR(d,e),f),g)),h),OR(i,AND(AND(j,k),AND(l,m)))))";
		System.out.println("Input string is:\n" + str);
		printAndOrTreeRecursive(str);

	}

	// Use stack
	static void printAndOrTree(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, "(,)", true);
		
		Stack<String> opStack = new Stack<String>();
		Stack<String> childrenStack = new Stack<String>();
		
		while(tokenizer.hasMoreTokens()){
			
			String tok = tokenizer.nextToken();
			
			if(tok.equals("AND") || tok.equals("OR")){
				
				if(opStack.isEmpty() || !opStack.peek().equals(tok))
					printToken(tok, opStack, childrenStack);
				opStack.push(tok);
				
			} else if(tok.equals("(")){
				
				childrenStack.push("L");
				
			} else if(tok.equals(")")){
				
				opStack.pop();
				childrenStack.pop();
				
			} else if(tok.equals(",")){
				
				childrenStack.pop();
				childrenStack.push("R");
				
			} else if(!tok.isEmpty()){
				
				printToken(tok, opStack, childrenStack);
			}
		}
	}

	private static void printToken(String tok, Stack<String> opStack,
			Stack<String> childrenStack) {
		
		StringBuilder prefix = new StringBuilder();
		
		for(int i = 0; i < opStack.size();){
			
			String currentOp = opStack.get(i);
			boolean showBar = childrenStack.get(i).equals("L");
			
			while(i < opStack.size() && opStack.get(i).equals(currentOp)){
				
				if(childrenStack.get(i).equals("L")){
					showBar = true;
				}
				i ++;
			}
			
			if(showBar || i == opStack.size()){
				prefix.append(" |");
			} else {
				prefix.append("  ");
			}
		}
		
		if(prefix.length() > 0){
			System.out.println(prefix);
			prefix.setLength(prefix.length() - 1);
			System.out.print(prefix);
			System.out.print("+-");
		}
		
		System.out.println(tok);
	}
	
	// Use recursion
	static void printAndOrTreeRecursive(String str) {
		
		StringTokenizer tokenizer = new StringTokenizer(str, "(,)", true);
		StringBuilder prefix = new StringBuilder();
		
		if(tokenizer.hasMoreTokens()){
			String tok = tokenizer.nextToken();
			System.out.println(tok);
			
			printAndOrTreeImpl(tokenizer, tok, prefix);
		}
	}

	private static void printAndOrTreeImpl(StringTokenizer tokenizer,
			String lastOp, StringBuilder prefix) {
		
		prefix.append(" |");
		int childrenCount = 0;
		int hideBarCount = 0;
		while(tokenizer.hasMoreTokens()){
			
			String tok = tokenizer.nextToken();
			
			if(tok.equals("(")){
				childrenCount += 2;
				++hideBarCount;
			} else if(tok.equals(",")){
				childrenCount --;
			} else if(tok.equals(")")){
				childrenCount --;
				--hideBarCount;
			} else if(tok.equals("AND") || tok.equals("OR")){
				if(!tok.equals(lastOp)){
					printTokenHelper(prefix, childrenCount, hideBarCount, tok);
					
					printAndOrTreeImpl(tokenizer, tok, prefix);
				}
			} else if(!tok.isEmpty()){
				printTokenHelper(prefix, childrenCount, hideBarCount, tok);
			}
			
		}
		
	}

	private static void printTokenHelper(StringBuilder prefix,
			int childrenCount, int hideBarCount, String tok) {
		System.out.println(prefix);
		System.out.print(prefix.substring(0, prefix.length() - 1));
		System.out.println("+-" + tok);
		
		if(childrenCount == hideBarCount){
			prefix.setCharAt(prefix.length() - 1, ' ');
		}
	}

}
