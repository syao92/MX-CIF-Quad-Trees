package solutions;

import java.util.ArrayList;
import java.util.List;

public class Node {
	Node left;
	Node right;
	double divide;
	List<Rectangle> list;
	
	public Node() {
		left = null;
		right = null;
		divide = 0;
		list = new ArrayList<Rectangle>();
	}
}
