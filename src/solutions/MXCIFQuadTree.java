package solutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MXCIFQuadTree {
	
	Quadrant origin = null;
	
	public MXCIFQuadTree(double x, double y, double w, double h) {
		origin = new Quadrant(x, y, w, h);
	}
	
	public ArrayList<String> intersect(double x, double y, double w, double h) {
		Rectangle rect = new Rectangle(x, y, w, h);
		ArrayList<String> list = new ArrayList<String>();
		
		intersect_helper(rect, origin, list);
		return list;
	}
	
	private void intersect_helper(Rectangle rect, Quadrant current, ArrayList<String> list) {
		if (current == null) {
			return;
		}
		intersect_tree(rect, current.x_axis, list);
		intersect_tree(rect, current.y_axis, list);
		intersect_helper(rect, current.NW, list);
		intersect_helper(rect, current.NE, list);
		intersect_helper(rect, current.SW, list);
		intersect_helper(rect, current.SE, list);
	}
	
	private void intersect_tree(Rectangle rect, Node root, ArrayList<String> list) {
		if (root == null) {
			return;
		}
		
		for (Rectangle r: root.list) {
			if (rect.x < r.x && rect.x + rect.w > r.x &&
					rect.y < r.y && rect.y + rect.h > r.y) {
				list.add(r.rname);
			} else if (r.x < rect.x && r.x + r.w > rect.x &&
					r.y < rect.y && r.y + r.h > rect.y) {
				list.add(r.rname);
			} else if (rect.x < r.x && rect.x + rect.w > r.x &&
					rect.y > r.y && r.y + r.h > rect.y) {
				list.add(r.rname);
			} else if (r.x < rect.x && r.x + r.w > rect.x &&
					r.y > rect.y && rect.y + rect.h > r.y) {
				list.add(r.rname);
			
			// Same Rectangle
			} else if (r.x == rect.x && r.w == rect.w && r.y == rect.y &&
					r.h == rect.h) {
				list.add(r.rname);
			}	
		}
		
		intersect_tree(rect, root.left, list);
		intersect_tree(rect, root.right, list);
		
	}
	
	public ArrayList<String> intersect(double x, double y) {
		return intersect(x, y, 0, 0);
	}
	
	public boolean insert(double x, double y, double w, double h, String rname) {
		Quadrant current = origin;
		
		// Check if rectangle fits into the quad tree coordinates
		if (x >= origin.x && origin.w >= x + w && y >= origin.y && origin.h >= y + h) {
			Rectangle rect = new Rectangle(x, y, w, h, rname);
			find_quadrant(rect, current);
			return true;
		}
		return false;
	}
	
	private void find_quadrant(Rectangle rect, Quadrant current) {
		current.number++;

		// Check if crosses x-axis of current quadrant
		if (rect.y < current.y + (current.h)/2 && 
				rect.y + rect.h > current.y + (current.h)/2) {
			if (current.x_axis == null) {
				current.x_axis = new Node();
				current.x_axis.divide = current.y + (current.h)/2;
			}
			insert_tree(rect, current.y + (current.h)/2, current.x_axis, 0); 
			return;
	
		//Check if crosses y-axis of current quadrant
		} else if (rect.x < current.x + (current.w)/2 && 
				rect.x + rect.w > current.x + (current.w)/2) {
			if (current.y_axis == null) {
				current.y_axis = new Node();
				current.y_axis.divide = current.x + (current.w)/2;
			}
			insert_tree(rect, current.x + (current.w)/2, current.y_axis, 1);
			return;
		}
		
		// Northwest Quadrant
		if (current.x + (current.w)/2 >= rect.x + rect.w 
				&& current.y + (current.h)/2 >= rect.y + rect.h) {
			if (current.NW == null) {
				current.NW = new Quadrant(current.x, current.y, (current.w)/2, (current.h)/2);
			}
			find_quadrant(rect, current.NW);
		// Northeast Quadrant
		} else if (rect.w + rect.x >= current.x + (current.w)/2 
				&& current.y + (current.h)/2 >= rect.y + rect.h) {
			if (current.NE == null) {
				current.NE = new Quadrant(current.x + (current.w)/2, current.y, (current.w)/2, (current.h)/2);
			}
			find_quadrant(rect, current.NE);
		// Southwest Quadrant
		} else if (rect.h + rect.y >= current.y + (current.h)/2 
				&& current.x + (current.w)/2 >= rect.x + rect.w) {
			if (current.SW == null) {
				current.SW = new Quadrant(current.x, current.y + (current.h)/2, (current.w)/2, (current.h)/2);
			}
			find_quadrant(rect, current.SW);
		// Southeast Quadrant
		} else {
			if (current.SE == null) {
				current.SE = new Quadrant(current.x + (current.w)/2, current.y + (current.h)/2, (current.w)/2, (current.h)/2);
			}
			find_quadrant(rect, current.SE);
		}
	}
	
	private void insert_tree(Rectangle rect, double num, Node root, int axis) {
		if (root == null) {
			root = new Node();
			root.divide = num;
		}
		if (root.left == null) {
			root.left = new Node();
			root.divide = num - num/2;
		}
		if (root.right == null) {
			root.right = new Node();
			root.divide = num + num/2;
		}
		
		if (axis == 0) {
			if (rect.x < root.divide && rect.x + rect.w > root.divide) {
				root.list.add(rect);
			} else {
				if (rect.x > root.divide) {
					insert_tree(rect, num + num/2, root.right, 0);
				} else {
					insert_tree(rect, num - num/2, root.left, 0);
				}
			}
		} else {
			if (rect.y < root.divide && rect.y + rect.h > root.divide) {
				root.list.add(rect);
			} else {
				if (rect.y > root.divide) {
					insert_tree(rect, num + num/2, root.right, 1);
				} else {
					insert_tree(rect, num - num/2, root.left, 1);
				}
			}
		}
	}
	
	public boolean delete(double x, double y, double w, double h) {
		Rectangle rect = new Rectangle(x, y, w, h);
		return delete_helper(rect, origin, null);
	}
	
	public boolean delete_helper(Rectangle rect, Quadrant current, Quadrant prev) {
		// No Quadrant exists so rectangle doesn't exist
		if (current == null) {
			return false;
		}
		
		// Check if crosses x-axis of current quadrant
		if (rect.y < current.y + (current.h)/2 && 
				rect.y + rect.h > current.y + (current.h)/2) {
				if (delete_tree(rect, current.y + (current.h)/2, current.x_axis, 0) == true) {
					current.number--;
					if (current.number == 0) {
						current.NW = null;
						current.NE = null;
						current.SW = null;
						current.SE = null;
						current = null;
					}
					return true;
			}
			return false;
			
		//Check if crosses y-axis of current quadrant
		} else if (rect.x < current.x + (current.w)/2 && 
				rect.x + rect.w > current.x + (current.w)/2) {
			if (delete_tree(rect, current.x + (current.w)/2, current.y_axis, 1) == true) {
				current.number--;
				if (current.number == 0) {
					current.NW = null;
					current.NE = null;
					current.SW = null;
					current.SE = null;
					current = null;
				}
				return true;
			}
			return false;
		}
		
		// Northwest Quadrant
		if (current.x + (current.w)/2 >= rect.x + rect.w 
				&& current.y + (current.h)/2 >= rect.y + rect.h) {
			if (delete_helper(rect, current.NW, current) == true) {
				current.number--;
				if (current.number == 0) {
					current.NW = null;
					current.NE = null;
					current.SW = null;
					current.SE = null;
					current = null;
				}
				return true;
			}
			return false;
		// Northeast Quadrant
		} else if (rect.w + rect.x >= current.x + (current.w)/2 
				&& current.y + (current.h)/2 >= rect.y + rect.h) {
			if (delete_helper(rect, current.NE, current) == true) {
				current.number--;
				if (current.number == 0) {
					current.NW = null;
					current.NE = null;
					current.SW = null;
					current.SE = null;
					current = null;
				}
				return true;
			}
			return false;
		// Southwest Quadrant
		} else if (rect.h + rect.y >= current.y + (current.h)/2 
				&& current.x + (current.w)/2 >= rect.x + rect.w) {
			if (delete_helper(rect, current.SW, current) == true) {
				current.number--;
				if (current.number == 0) {
					current.NW = null;
					current.NE = null;
					current.SW = null;
					current.SE = null;
					current = null;
				}
				return true;
			}
			return false;
		// Southeast Quadrant
		} else {
			if (delete_helper(rect, current.SE, current) == true) {
				current.number--;
				if (current.number == 0) {
					current.NW = null;
					current.NE = null;
					current.SW = null;
					current.SE = null;
					current = null;
				}
				return true;
			}
			return false;
		}
	}
	
	public boolean delete_tree(Rectangle rect, double num, Node root, int axis) {
		if (root == null) {
			return false;
		}
		
		if (axis == 0) {
			if (rect.x < root.divide && rect.x + rect.w > root.divide) {
				for (Rectangle r: root.list) {
					if (r.x == rect.x && r.y == rect.y && r.h == rect.h && 
							r.w == rect.w) {
						root.list.remove(r);
						return true;
					}
				}
				return false;
			} else {
				if (rect.x > root.divide) {
					return delete_tree(rect, num + num/2, root.right, 0);
				} else {
					return delete_tree(rect, num - num/2, root.left, 0);
				}
			}
		} else {
			if (rect.y < root.divide && rect.y + rect.h > root.divide) {
				for (Rectangle r: root.list) {
					if (r.x == rect.x && r.y == rect.y && r.h == rect.h && 
							r.w == rect.w) {
						root.list.remove(r);
						return true;
					}
				}
				return false;
			} else {
				if (rect.y > root.divide) {
					return delete_tree(rect, num + num/2, root.right, 1);
				} else {
					return delete_tree(rect, num - num/2, root.left, 1);
				}
			}
		}
	}
	
	
	public boolean isEmpty() {
		if (origin.number == 0) {
			return true;
		}
		return false;
	}
	
	public int getSize() {
		if (origin == null) {
			return 0;
		}
		return origin.number;
	}
	
	public int getHeight() {
		if (origin.NW == null && origin.NE == null && origin.SW == null && origin.SE == null) {
			return 1;
		}
		return getHeight_helper(origin);
	}
	
	private int getHeight_helper(Quadrant current) {
		if (current == null || current.number == 0) {
			return 0;
		}
		return 1 + Math.max(getHeight_helper(current.NE), Math.max(getHeight_helper(current.NW), 
				Math.max(getHeight_helper(current.SE), getHeight_helper(current.SW))));
	}
	
	public Iterator<String> levelOrderTraversalQTInOrderBST() {
		return new QuadTreeIterator();
	}
	
	public class QuadTreeIterator implements Iterator<String> {
		private ArrayList<String> list;
		int index;
		
		public QuadTreeIterator() {
			list = new ArrayList<String>();
			addToList();
			index = 0;
		}
		
		private void addToList() {
			ArrayList<ArrayList<String>> whole_list = new ArrayList<ArrayList<String>>();
			traverseQuadrant(whole_list, origin, 0);
			
			for(int i = 0; i < whole_list.size(); i++) {
				ArrayList<String> temp = new ArrayList<String>();
				temp = whole_list.get(i);
				for (String s: temp) {
					list.add(s);
				}
			}
		}
		
		private void traverseQuadrant(ArrayList<ArrayList<String>> whole_list, Quadrant current,
				int level) {
			if (current == null) {
				return;
			}
			ArrayList<String> temp;
			
			if (whole_list.size() <= level) {
				temp = new ArrayList<String>();
				whole_list.add(level, temp);
			} else {
				temp = whole_list.get(level);
			}
			traverseTree(temp, current.x_axis);
			traverseTree(temp, current.y_axis);
			traverseQuadrant(whole_list, current.NW, level + 1);
			traverseQuadrant(whole_list, current.NE, level + 1);
			traverseQuadrant(whole_list, current.SW, level + 1);
			traverseQuadrant(whole_list, current.SE, level + 1);
		}
		
		private void traverseTree(ArrayList<String> temp, Node root) {
			if (root == null) {
				return;
			}
			traverseTree(temp, root.left);
			for (Rectangle r: root.list) {
				temp.add(r.rname);
			}
			traverseTree(temp, root.right);
		}
		
		@Override
		public boolean hasNext() {
			if (index < list.size()) {
				return true;
			}
			return false;
		}

		@Override
		public String next() {
			if (hasNext()) {
				return list.get(index++);
			} else {
				throw new NoSuchElementException();
			}
		}
		
	}
}
