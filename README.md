Natalie Hlusi, aiming for grade **B**

For this project, I implemented BinaryTree class which uses code from our previous lectures. I used the printPreOrder() method from our recent quiz and modified it. I also used checkValid() which I implemented a few lectures ago to check if there is a correct number of opening and closing parentheses. The input is taken from the command line, so you have to run java BinaryTree “\<the input you wish to test\>”

I tested my code on these inputs:  
"((5 \+ 3\) \+ ((4 \- 0\) / (3 \- 1)))" //10  
"((5 \+ 3\) \* ((4 \- 0\) / (3 \- 4)))" //-32  
"((5 \+ 3\) \! (4 \- 0\) / (3 \- 3))" //Invalid operator  
"(8\*(1-(5+3)))" //-56  
"((5 \+ 3\) / (5 \- 5))" //Division by zero is not allowed.  
"((5 \+ 3\) / (5 \- 4\) \+ 1)" //9  
"((5 \+ 3.5) \+ ((4 \- 0\) / (2 \- 3)))" //4.5  
"(8+3))" //The parentheses doesn't match.  
           The expression is invalid

"((5 \+ 3.5) \+ (4 ^ 2))" //24.5

