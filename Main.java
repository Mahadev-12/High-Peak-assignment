import java.io.*;
import java.util.*;

//creating class of Goodies
class Goodies {
  String name;
  int price;

  //initializing instance variables
  public Goodies(String name, int price) {
    this.name = name;
    this.price = price;
  }

  //overriding toString method
  public String toString() { 
      return this.name + ": " + this.price;
  }
}
public class Main {
    public static void main(String[] args) throws Exception {
      // creating object of FileInputStream which includes the path of the input file
    FileInputStream input_path=new FileInputStream("C:\\Users\\MAHADEV\\Desktop\\input_file1.txt");   
    // FileInputStream input_path=new FileInputStream("C:\\Users\\MAHADEV\\Desktop\\input_file2.txt");   
    // FileInputStream input_path=new FileInputStream("C:\\Users\\MAHADEV\\Desktop\\input_file3.txt");   
   
    // Creating object of Scanner class
    Scanner sc=new Scanner(input_path);
    // reading number of employees from file
    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); 
    sc.nextLine();
    sc.nextLine();

    // creating an arraylist of goodies
    ArrayList<Goodies> goodies = new ArrayList<Goodies>();

    //reading data from file until the end of the file
    while(sc.hasNextLine())  
    {
      //spliting String based on ':' 
      String current[] = sc.nextLine().split(": ");
      goodies.add(new Goodies(current[0], Integer.parseInt(current[1])));
    }

    //sorting goodies in ascending order
    Collections.sort(goodies, new Comparator<Goodies>(){
      public int compare(Goodies a, Goodies b) { 
        return a.price - b.price; 
      } 
    });

    //initializing min_val with the maximum price from goodies list
    int min_val = goodies.get(goodies.size()-1).price; 
    int min_index = 0;
    for(int i=0;i<goodies.size()-number_of_employees+1;i++) {
      int diff = goodies.get(number_of_employees+i-1).price-goodies.get(i).price;
      //calculating difference and comparing with minimum difference
      if(diff<=min_val) {
        min_val = diff;
        min_index = i;
      }
    }

    //creating object of FileWriter with output file path
    FileWriter output_path = new FileWriter("C:\\Users\\MAHADEV\\Desktop\\output_file.txt");
    output_path.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + number_of_employees; i++) {
      output_path.write(goodies.get(i).toString() + "\n");
    }

    output_path.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_val);
	  output_path.close();
  }
}