package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Node3{
    int data;
    Node3 left;
    Node3 right;
    public Node3(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
public class DFSinTree {
    private Node3 root;
    public void insert(int data){
        root = insertRecursively(root, data);
    }
    private Node3 insertRecursively(Node3 current,int data){
        if(current == null){
            return new Node3(data);
        }
        if(data < current.data){
            current.left = insertRecursively(current.left,data);
        }else{
            current.right = insertRecursively(current.right,data);
        }
        return current;
    }
    public void search(int data){
        searchRecursively(root, data);
    }
    private boolean searchRecursively(Node3 current,int data){
        if(current == null){
            return false;
        }
        if(data == current.data){
            return true;
        }else if(data < current.data){
            return searchRecursively(current.left,data);
        }else{
            return searchRecursively(current.right,data);
        }
    }
    public void printInOrderDFS(){
        inOrderDFS(root);
    }
    private void inOrderDFS(Node3 current){
        if(current == null){
            return;
        }
        inOrderDFS(current.left);
        System.out.println(current.data + " ");
        inOrderDFS(current.right);
    }

    public void printPreOderDFS(){
        preOrderDFS(root);
    }
    private void preOrderDFS(Node3 current){
        if(current == null){
            return;
        }
        System.out.println(current.data+" ");
        preOrderDFS(current.left);
        preOrderDFS(current.right);
    }

    public void printPostOrderDFS(){
        postOrderDFS(root);
    }

    private void postOrderDFS(Node3 current){
        if(current == null){
            return;
        }
        postOrderDFS(current.left);
        postOrderDFS(current.right);
        System.out.println(current.data+" ");
    }

    public void delete(int data){
        root = deleteRecursively(root,data);
    }
    private Node3 deleteRecursively(Node3 current, int data){//<-so here we go into right subtree and find leftmost min value
        if(current == null){
            return null;
        }
        if(data < current.data){
            current.left = deleteRecursively(current.left,data);
        }else if(data > current.data){
            current.right = deleteRecursively(current.right,data);
        }else{
            if(current.left == null){
                return current.right;
            }else if(current.right == null){
                return current.left;
            }
            current.data = minValue(current.right);
            current.right = deleteRecursively(current.right,current.data);
        }
        return current;
    }
    private int minValue(Node3 current){
        int minVal  = current.data;
        while(current.left != null){
            minVal = current.left.data;
            current = current.left;
        }
        return minVal;
    }
    public static List<Integer> findElement(Node3 root,int target){
        if(root== null){
            return null;
        }
        Stack<Node3> nodeStack = new Stack<>();
        nodeStack.push(root);
        Stack<List<Integer>> pathStack = new Stack<>();
        pathStack.push(Collections.singletonList(root.data));
        while(!nodeStack.isEmpty()){
            Node3 current = nodeStack.pop();
            List<Integer> currentPath = pathStack.pop();
            if(current.data == target){
                return currentPath;
            }
            if(current.right != null){
                List<Integer> rightPath = new ArrayList<>(currentPath);
                rightPath.add(current.right.data);
                nodeStack.push(current.right);
                pathStack.push(rightPath);
            }
            if(current.left != null){
                List<Integer> leftPath = new ArrayList<>(currentPath);
                leftPath.add(current.left.data);
                nodeStack.push(current.left);
                pathStack.push(leftPath);
            }
        }
        return null;
    }

    public static void main(String[] args){
        DFSinTree tree = new DFSinTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);
        tree.insert(18);
        tree.search(15);
        System.out.println("DFS Inorder");
        tree.printInOrderDFS();
        System.out.println("DFS Preorder");
        tree.printPreOderDFS();
        System.out.println("DFS Postorder");
        tree.printPostOrderDFS();
        tree.delete(15);
        System.out.println("New DFS Inorder");
        tree.printInOrderDFS();
        System.out.println("New DFS Preorder");
        tree.printPreOderDFS();
        System.out.println("New DFS Postorder");
        tree.printPostOrderDFS();
        int target = 18;
        List<Integer> path = DFSinTree.findElement(tree.root,target);
        if(path!=null){
            System.out.println("Path from root to "+target);
            for(int i = 0;i < path.size();i++){
                System.out.print(path.get(i)+" ");
                if(i < path.size()-1){
                    System.out.println("->");
                }
            }
        }
    }
}

