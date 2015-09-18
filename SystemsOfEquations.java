import java.util.Scanner;

/*
 * This program also makes use of Cramer's rule
 * Note program requires x and y on the same side
 * Ex equaions below 2x2
 *   -x - 7y = -11
 *   -9x - y = -37
 *   Answer (4,1)
 */

public class SystemsOfEquations 
{
	static Scanner in = new Scanner(System.in);
	
	static int[][] matrix = new int[2][2];
	
	static int[] xy = new int[4];
	
	static int[] equal = new int[3];
	
	static int[] determinantsA = new int[3];
	
	public static void main(String[] args)
	{
		enterMatrix();
		determinantsA[0] = getDeterminant();
		getEquals();
		replace("x");
		determinantsA[1] = getDeterminant();
		
		boolean sameSlope = checkSlope();
		
		if(determinantsA[0] == 0 && sameSlope == false)
		{
			System.out.println("Answer is they are the same line!");
			System.out.println("Congrats mortal I have done your hardwork for you so go enjoy a cookie");
			return;
		}
		else if(sameSlope == true)
		{
			System.out.println("This is a parallel line");
			return;
		}
		
		replace("y");
		
		determinantsA[2] = getDeterminant();
		
		reduce();
	}
	
	public static boolean checkSlope()
	{
		int first = xy[0];
		int second = xy[2];
		
		if(first == second)
			return true;
		else
			return false;
	}
	
	public static void enterMatrix()
	{
		int input;
		int indexing = 0;
		
		  for(int o = 0; o < matrix.length; o++)
		  {
		  		for(int i = 0; i <matrix[0].length; i++)
		  		{
		  			String whichSide;
		  			if(o == 1)
		  			{
		  				whichSide = "second";
		  			}
		  			else
		  			{
		  				whichSide = "first";
		  			}
		  			if(i % 2 == 0)
		  			{
		  				System.out.print("Please enter  x coeffcient of " + whichSide + " equation: ");
		  				input = in.nextInt();
		  			}
		  			else
		  			{
		  				System.out.print("Please enter  y coeffcient of " + whichSide + " equation: ");
		  				input = in.nextInt();
		  			}
		  			
		  			matrix[o][i] = input;
		  			xy[indexing] = input;
		  			
		  			indexing++;
		  		}
		  }
		  
		  System.out.println();
	}
	
	public static void getEquals()
	{
		int equals;
		
		System.out.print("Please enter the constant on the other side of the first equation: ");
		equals = in.nextInt();
		
		equal[0] = equals;
		
		System.out.print("Please enter the constant on the other side of the second equation: ");
		equals = in.nextInt();
		
		equal[1] = equals;
		
		System.out.println();
	}
	
	public static int getDeterminant()//Cross multiply from the matrix then subtract ad-cb = result
	{
		int[] multiply = new int[4];
		
		multiply[0] = matrix[0][0];
		
		multiply[1] = matrix[1][1];
		
		multiply[2] = matrix[1][0];
		
		multiply[3] = matrix[0][1];
		
		int[] multiplyed = new int[2];
		
		multiplyed[0] = multiply[0] * multiply[1];
		
		multiplyed[1] = multiply[2] * multiply[3];
		
		int determinant = multiplyed[0] - multiplyed[1];
		
		return determinant; //Return is captured by the arraylist for later use
	}
	
	public static void replace(String l)
	{
		if(l.equals("x"))
		{
			matrix[0][0] = equal[0];
			matrix[1][0] = equal[1];
		}
		else if(l.equals("y"))
		{
			matrix[0][1] = equal[0];
			matrix[1][1] = equal[1];
			
			matrix[0][0] = xy[0];
			matrix[1][0] = xy[2];
		}
	}
	
	public static void reduce()
	{
		boolean xB = false, yB = false;
		
		int x = determinantsA[1] % determinantsA[0];
		int y = determinantsA[2] % determinantsA[0];
		
		if(x == 0)
		{
			xB = true;
		}
		
		if(y == 0)
		{
			yB = true;
		}
		
		print(xB,yB);
		
	}
	
	public static void print(boolean f, boolean s)
	{
		
		int x = determinantsA[1] / determinantsA[0];
		int y = determinantsA[2] / determinantsA[0];
		
		if(f && s)
		{	
			System.out.println("(" + x + "," + y + ")");
		}
		else if(f && !s)
		{
			System.out.println("(" + x + "," + determinantsA[2] + "/" + determinantsA[0] + ")");
		}
		else if(!f && s)
		{
			System.out.println("(" + determinantsA[1] + "/" + determinantsA[0] + "," + y);
		}
		else
			System.out.println("(" + determinantsA[1] + "/" + determinantsA[0] + "," + determinantsA[2] + "/" + determinantsA[0] + ")");
	}
}