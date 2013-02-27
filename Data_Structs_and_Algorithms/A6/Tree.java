import java.io.*;

public class Tree{

	private Node root,x,y;
	private Node nil = new Node(-1,1);

	public Tree(){
		root = nil;
		x = nil;
		y = nil;
	}

	public Node getRoot(){
		return root;
	}

	public Node bstInsert(int z, int color){
		y = nil;
		x = root;
		while(x != nil){
			y = x;
			if(z < x.key){
				x = x.left;
			}else{
				x = x.right;
			}
		}

		Node newNode = new Node(z,color);
		newNode.p = y;
		if(y == nil){
			root = newNode;
		}else if(z < y.key){
			y.left = newNode;
		}else{
			y.right = newNode;
		}
		return newNode;
	}

	public void rbInsert(int z){
		Node newNode = nil;
		newNode = bstInsert(z,0);
		rbInsertFix(newNode);
	}

	public void rbDelete(int key){
		Node z = search(root,key);

		y = z;
		int oColor = y.color;
		if(z.left == nil){
			x = z.right;
			rbTransplant(z,z.right);
		} else if(z.right == nil){
			x = z.left;
			rbTransplant(z,z.left);
		} else {
			y = treeMin(z.right);
			oColor = y.color;
			x = y.right;
			if (y.p == z){
				x.p = y;
			} else {
				rbTransplant(y,y.right);
				y.right = z.right;
				y.right.p = y;
			}
			rbTransplant(z,y);
			y.left = z.left;
			y.left.p = y;
			y.color = z.color;
		}
		if(oColor == 1){
			rbDeleteFix(x);
		}
	}

	public Node treeMin(Node x){

		while(x.left != nil){
			x = x.left;
		}
		return x;
	}

	public void rbTransplant(Node u, Node v){

		if(u.p == nil){
			root = v;
		}else if(u == u.p.left){
			u.p.left = v;
		}else{
			u.p.right = v;
		}
		v.p = u.p;
	}

	public Node search(Node x, int z){
		if (x == nil || z == x.key){
			return x;
		}
		if (z < x.key){
			return search(x.left, z);
		}else{
			return search(x.right, z);
		}
	}

	public void rbInsertFix(Node z){
		while(z.p.color == 0){
			if (z.p == z.p.p.left){
				y = z.p.p.right;
				if(y.color == 0){
					z.p.color = 1;
					y.color = 1;
					z.p.p.color = 0;
					z = z.p.p;
				} else {
					if(z == z.p.right){
						z = z.p;
						leftRotate(z);
					}
					z.p.color = 1;
					z.p.p.color = 0;
					rightRotate(z.p.p);
				}
			} else {
				if (z.p == z.p.p.right){
					y = z.p.p.left;
					if(y.color == 0){
						z.p.color = 1;
						y.color = 1;
						z.p.p.color = 0;
						z = z.p.p;
					} else {
						if(z == z.p.left){
							z = z.p;
							rightRotate(z);
						}
						z.p.color = 1;
						z.p.p.color = 0;
						leftRotate(z.p.p);
					}
				}
			}
		}
		root.color = 1;
	}

	public void rbDeleteFix(Node x){
		Node w;
		while (x != root && x.color == 1){
			if(x == x.p.left){
				w = x.p.right;
				if (w.color == 0){
					w.color = 1;
					x.p.color = 0;
					leftRotate(x.p);
					w = x.p.right;
				}
				if (w.left.color==1 && w.right.color==1){
					w.color=0;
					x=x.p;
				} else {
					if(w.right.color == 1){
						w.left.color = 1;
						w.color = 0;
						rightRotate(w);
						w = x.p.right;
					}
					w.color = x.p.color;
					x.p.color = 1;
					w.right.color = 1;
					leftRotate(x.p);
					x = root;
				}
			}
			else{
				if(x == x.p.right){
					w = x.p.left;
					if (w.color == 0){
						w.color = 1;
						x.p.color = 0;
						rightRotate(x.p);
						w = x.p.left;
					}
					if (w.right.color==1 && w.left.color==1){
						w.color=0;
						x=x.p;
					} else {
						if(w.left.color == 1){
							w.right.color = 1;
							w.color = 0;
							leftRotate(w);
							w = x.p.left;
						}
						w.color = x.p.color;
						x.p.color = 1;
						w.left.color = 1;
						rightRotate(x.p);
						x = root;
					}
				}
			}
		}
		x.color = 1;
	}

	public void leftRotate(Node x){
		y = x.right;
		x.right = y.left;
		if(y.left != nil){
			y.left.p = x;
		}
		y.p = x.p;
		if(x.p == nil){
			root = y;
		}else if(x == x.p.left){
			x.p.left = y;
		}else{
			x.p.right = y;
		}
		y.left = x;
		x.p = y;
	}

	public void rightRotate(Node x){
		y = x.left;
		x.left = y.right;
		if(y.right != nil){
			y.right.p = x;
		}
		y.p = x.p;
		if(x.p == nil){
			root = y;
		}else if(x == x.p.right){
			x.p.right = y;
		}else{
			x.p.left = y;
		}
		y.right = x;
		x.p = y;
	}

	public void print(Node x, PrintWriter outFile){
		if(x != nil){
			outFile.println(x.color + " " + x.key);
			print(x.left, outFile);
			print(x.right, outFile);
		}
	}


	private class Node{

		public int color,key;
		public Node left,right,p;

		public Node(int key, int color){
			this.color = color;
			this.key = key;
			left = nil;
			right = nil;
			p = nil;
			}

		public int getColor(){
			return color;
		}

		public void setColor(int color){
			this.color = color;
		}

	}//end Node class

}//end Tree class