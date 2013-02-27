
public class Tree{

	private class Node{

		private int color,key;
		public Node left,right,parent;

		public Node(int color, int key){
			this.color = color;
			this.key = key;
			}

		public int getColor(){
			return color;
		}

		public void setColor(color){
			this.color = color;
		}

	}//end Node class

	private Node root;

	public Tree