package hw8;

import java.util.*;

public class TwoDTree {
    private Node root;

   static class Point2D {
    	double x;
    	double y;
    	
    	public Point2D(double x, double y){
    		this.x = x;
    		this.y = y;
    	}
    }
    	
    static class Node {
        private Point2D p;
        private Node left;
        private Node right;

        Node(Point2D p) {
            this.p = p;
        }
    }
    
    public TwoDTree(){
    	root = null;
    }
    
    public void insert(Point2D p){
    	if (root == null) {
    		root = new Node(p);
    	}
    	else {
    		inx(root, p);
    	}
    }
    
    void inx(Node n, Point2D p) {
    	if (p.x < n.p.x) {
    		if (n.left != null) {
    			iny(n.left, p);
    		}
    		else {
    			n.left = new Node(p);
    		}
    	}
    	else {
    		if (p.x > n.p.x) {
    			if (n.right != null) {
    				iny(n.right, p);
    			}
    			else {
    				n.right = new Node(p);
    			}
    		}
    	}
    }
    
    void iny(Node n, Point2D p) {
    	if (p.y < n.p.y) {
    		if (n.left != null) {
    			inx(n.left, p);
    		}
    		else {
    			n.left = new Node(p);
    		}
    	}
    	else {
    		if (p.y > n.p.y) {
    			if (n.right != null) {
    				iny(n.right, p);
    			}
    			else {
    				n.right = new Node(p);
    			}
    		}
    	}
    }
    
    public boolean search(Point2D p){
    	ArrayList<Node> q = new ArrayList<Node>();
    	q.add(root);
    	boolean found = false;
    	while (!q.isEmpty() && found == false) {
    		Node n1 = q.remove(0);
    		if (p.x == n1.p.x && p.y == n1.p.y) {
    			found = true;
    		}
    		if (n1.left != null) {
    			q.add(n1.left);
    		}
    		if (n1.right != null) {
    			q.add(n1.right);
    		}
    	}
    	return found;
    }
    
    public void display(){
    	dis(root);
    }
    
    void dis(Node n) {
    	ArrayList<Node> q = new ArrayList<Node>();
    	q.add(n);
    	while (!q.isEmpty()) {
    		System.out.println("(" + q.get(0).p.x + ", " + q.get(0).p.y + ")");
    		Node n1 = q.remove(0);
    		if (n1.left != null) {
    			q.add(n1.left);
    		}
    		if (n1.right != null) {
    			q.add(n1.right);
    		}
    	}
    }
    
    public static void main(String[] args) throws Exception {
    	TwoDTree a = new TwoDTree();
    	a.insert(new Point2D(0.40636, 0.6781));
    	a.insert(new Point2D(0.010189, 0.742363));
    	a.insert(new Point2D(0.740024, 0.021714));
    	a.insert(new Point2D(0.014067, 0.805079));
    	a.insert(new Point2D(0.371858, 0.169457));
    	a.insert(new Point2D(0.095179, 0.523114));
    	a.insert(new Point2D(0.537098, 0.43615));
    	a.insert(new Point2D(0.970874, 0.005952));
    	a.insert(new Point2D(0.01869, 0.959379));
    	a.insert(new Point2D(0.147733, 0.203388));
    	
    	a.display();
    }
}