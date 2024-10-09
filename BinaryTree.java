import java.util.Stack;
//source used: https://www.geeksforgeeks.org/expression-tree/

public class BinaryTree{

    // Node class representing each node in the tree
    static class Node {
        String data;
        Node left, right;

        public Node(String s) {
            data = s;
            left = right = null;
        }
    }

    // Root of the binary tree
    Node root;
    static int index; //**to go through*/

    // Constructor
    BinaryTree() {
        this.root = null;
        this.index = 0;
    }

    static boolean isOperator(char ch){
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^'){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkValid(String s){
        String input = s;
        Stack<Character> stack = new Stack<>();
        boolean matched=true;
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='(' || input.charAt(i)=='[' || input.charAt(i)=='{'){
                if(i==input.length()-1){
                    matched = false;
                    System.out.println("The parentheses doesn't match.");
                }else{
                stack.push(input.charAt(i));
                }
            }else if(input.charAt(i)==')'){
                if(stack.size()==0){
                    System.out.println("The parentheses doesn't match.");
                    matched = false;
                    break;
                }else if(stack.peek()!='('){
                    //stack.pop();
                    System.out.println("The parentheses doesn't match.");
                    matched = false;
                    break;
                }else{stack.pop();}
            }else if(input.charAt(i)==']'){
                if(stack.size()==0){
                    System.out.println("The parentheses doesn't match.");
                    matched = false;
                    break;
                }else if(stack.peek()!='['){
                    //stack.pop();
                    System.out.println("The parentheses doesn't match.");
                    matched = false;
                    break;
                }else{stack.pop();}
            }else if(input.charAt(i)=='}'){
                if(stack.size()==0){
                    System.out.println("The parentheses doesn't match.");
                    matched = false;
                    break;
                }else if(stack.peek()!='{'){
                    //stack.pop();
                    System.out.println("The parentheses doesn't match.");
                    matched = false;
                    break;
                }else{stack.pop();}
            }  
        }
        if(matched) {System.out.println("The parentheses do match!");}
        return matched; //**??
    }

    public Node buildTree(String input){
        if (input.length() == 0) {
            throw new IllegalArgumentException("Invalid expression.");
        }

        Node root = null;

        while(index < input.length()){
            if(input.charAt(index) == '('){ //start subtree
                index++; //move on
                root = new Node(null); //for now assign it to null, will be changed later
                root.left = buildTree(input);
            }else if(isOperator(input.charAt(index))){
                root.data = Character.toString(input.charAt(index));
                index++; //**using index to move - chatGPT */
                root.right = buildTree(input); //because we are past operator

            }else if(Character.isDigit(input.charAt(index)) || input.charAt(index) == '.'){ //encountered a number
                String number = "";
                while (Character.isDigit(input.charAt(index)) || input.charAt(index) == '.'){ //get the double number
                    number += input.charAt(index); //**can you add character? or to string? */
                    index++;
                }
                return new Node(number);
            }else if(input.charAt(index) == ')'){
                index++;
                return root;  // Finished constructing this subtree, return the current root to return to parent
            }else if(input.charAt(index) == ' '){
                index++; //**check? //skip spaces
            }else{
                throw new IllegalArgumentException("Invalid operator"); //throw error if invalid sign
            }
        }
        return root;
    }

    public double evaluate(Node root) {
        double evaluatedSum = 0;

        if (root.left == null && root.right == null) {
            return Double.parseDouble(root.data);
        }else{
            if(root.data.equals("+")){
                evaluatedSum += evaluate(root.left) + evaluate(root.right);
            }else if(root.data.equals("-")){
                evaluatedSum += evaluate(root.left) - evaluate(root.right);
            }else if(root.data.equals("*")){
                evaluatedSum += evaluate(root.left) * evaluate(root.right);
            }else if(root.data.equals("/")){
                if (root.right.data.equals("0")){
                    throw new ArithmeticException("Division by zero is not allowed.");
                }else{
                    evaluatedSum += evaluate(root.left) / evaluate(root.right);
                }
            }else if(root.data.equals("^")){
                evaluatedSum += Math.pow(evaluate(root.left),evaluate(root.right)); //source: https://www.geeksforgeeks.org/math-pow-method-in-java-with-example/
            }
        }
        return evaluatedSum;
    }

    public static String dashes(int count) { //**chatGPT */ - I didn't realize I have to define dashes (called "spaces" in the quiz)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    public static void printPreorderIndent(BinaryTree T, Node p, int d){ //code from quiz 7, edited
        System.out.println(dashes(2*d) + p.data);
         
        if (p.right != null) { //**print right first? */ or while?
            printPreorderIndent(T, p.right, d + 1);
        }
    
        if (p.left != null) {
            printPreorderIndent(T, p.left, d + 1);
        }
    }


    public static void main(String[] args) { //**where to put try/catch? */
    BinaryTree myTree = new BinaryTree();

    //String myS = args[0];
    String myS = "(5 + 3) * (4 - 0) / (3 - 3)";
    if(myTree.checkValid(myS)){
        myTree.buildTree(myS);
        double answer = myTree.evaluate(myTree.root); //**how to pass in the root? */
        System.out.println(answer);
        myTree.printPreorderIndent(myTree,myTree.root, 0); //**check what you're passing in */
    }else{
        System.out.println("The expression is invalid");
    }
    }

}