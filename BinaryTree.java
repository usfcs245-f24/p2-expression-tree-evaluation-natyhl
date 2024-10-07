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

    // Constructor
    BinaryTree() {
        root = null;
    }

    static boolean isOperator(char ch){
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/'){
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
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == ')'){
                Node right = stack.pop();
                Node currOperator = stack.pop();
                Node left = stack.pop();
                currOperator.left = left;
                currOperator.right = right;
                stack.push(currOperator); //source of approach: chatGPT
            }

            if(isOperator(input.charAt(i))){
                Node temp = new Node(input);
                stack.push(temp); //push operator to the stack
            }else{
                String number = "";
                while (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.'){
                    number += input.charAt(i); //**can you add character? or to string? */
                    i++; //**correct? */
                }
                stack.push(new Node(number.toString()));
            }
        }
        return stack.pop(); //**?? */
    }

    public double evaluateTree(Node root) {
        double evaluatedSum = 0;

        if (root.left == null && root.right == null) {
            if(root.data.equals('+')){
                return Double.parseDouble(root.right.data) + Double.parseDouble(root.left.data);
            }else if(root.data.equals('-')){
                return Double.parseDouble(root.right.data) - Double.parseDouble(root.left.data);
            }else if(root.data.equals('*')){
                return Double.parseDouble(root.right.data) * Double.parseDouble(root.left.data);
            }else if(root.data.equals('/')){
                return Double.parseDouble(root.right.data) / Double.parseDouble(root.left.data);
            }
        }
        return evaluatedSum+= evaluateTree(root.left) + evaluateTree(root.right);
    }


    public static void main(String[] args) { //**where to put try/catch? */
    BinaryTree myTree = new BinaryTree();

    String myS = args[0];
    if(myTree.checkValid(myS)){
        myTree.buildTree(myS);
        double answer = myTree.evaluateTree(myTree.root); //**how to pass in the root? */
        System.out.println(answer);
    }else{
        System.out.println("The expression is invalid");
    }
    }

}