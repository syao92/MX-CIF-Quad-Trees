package solutions;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class StudentTest {

//	@Test
//	public void test1() {
//		MXCIFQuadTree tree = new MXCIFQuadTree(10, 10, 110, 110);
//		
//		tree.insert(20, 20, 20, 20, "A");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.insert(70, 20, 20, 20, "B");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.insert(20, 70, 20, 20, "C");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.insert(70, 70, 20, 20, "D");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());	
//		tree.insert(110, 90, 5, 5, "E");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.insert(50, 50, 20, 20, "F");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.insert(60, 60, 1, 1, "G");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());	
//	}
	
//	@Test
//	public void test2() {
//		MXCIFQuadTree tree = new MXCIFQuadTree(10, 10, 110, 110);
//		
//		tree.insert(40, 40, 30, 30, "A");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.delete(40, 40, 30, 30);
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//	}
	
//	@Test
//	public void test3() {
//		MXCIFQuadTree tree = new MXCIFQuadTree(10, 10, 110, 110);
//		
//		tree.insert(10, 10, 1, 1, "A");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.insert(30, 30, 50, 50, "B");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.insert(40, 40, 50, 50, "C");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.insert(20, 60, 5, 5, "D");
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.delete(10, 10, 1, 1);
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.delete(20, 60, 5, 5);
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.delete(10, 10, 1, 1);
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());
//		tree.delete(30, 30, 50, 50);
//		System.out.println(tree.getHeight());
//		System.out.println(tree.getSize());	
//	}
	
	@Test
	public void test4() {
		MXCIFQuadTree tree = new MXCIFQuadTree(0, 0, 100, 100);
		
		tree.insert(49, 49, 2, 2, "A");
		ArrayList<String> list = tree.intersect(35, 35, 20, 20);
		for (String s: list) {
			System.out.println(s);
		}
		
	}

}
