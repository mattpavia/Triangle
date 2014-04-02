/**
 * Matthew Pavia
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Triangle {
  public static void main(String args[]) throws Exception {
    if (args.length != 1) {
      System.out.println("No file specified. Usage: java Triangle file");
      System.exit(1);
    }
    
    Scanner scan = new Scanner(new File(args[0]));
    
    //use a ragged 2d array to make finding the sum easier
    ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
    
    for (int i = 0; i < 100; i++) {
      ArrayList<Integer> row = new ArrayList<Integer>();
      for (int j = 0; j < i+1; j++) {
        row.add(scan.nextInt());
      }
      triangle.add(row);
    }
    
    //set each element to itself + the higher of the two above adjacent elements,
    //storing the result in the current element to keep track of the sum
    for (int i = 1; i < triangle.size(); i++) {
      for (int j = 0; j < triangle.get(i).size(); j++) {
        if (j == 0) { //if the element is the one on the left, add it to the only possible adjacent element above it
          triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j));
        } else if (j == triangle.get(i).size()-1) { //if the element is the one on the right, add it to the only possible adjacent element above it
          triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j-1));
        } else { //if we have two adjacent options above, add the element to the higher of the two
          int left = triangle.get(i-1).get(j-1);
          int right = triangle.get(i-1).get(j);
          triangle.get(i).set(j, triangle.get(i).get(j) + ((left >= right) ? left : right));
        }
      }
    }
    
    //the last row now contains the sums of the greatest paths up until that point. choose the highest one and we have our answer!
    int maxSum = triangle.get(99).get(0);
    for (int i = 1; i < triangle.get(99).size(); i++) {
      if (triangle.get(99).get(i) > maxSum) {
        maxSum = triangle.get(99).get(i);
      }
    }
    
    System.out.println("Max sum: " + maxSum);
  }
}