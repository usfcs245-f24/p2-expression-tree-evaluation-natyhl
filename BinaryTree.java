import java.util.Stack;
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

    public void buildTree(String input){
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            String number = "";
            while (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.'){
                number += input.charAt(i); //**can you add character? or to string? */
                i++; //**correct? */
            }
        }
    }


    public static void main(String[] args) {
    BinaryTree tree = new BinaryTree();

    String myS = args[0];
    if(tree.checkValid(myS)){
        tree.buildTree(myS);
        //...
    }
    }

}