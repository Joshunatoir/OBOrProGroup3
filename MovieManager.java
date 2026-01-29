import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class MovieManager {
	
	

	//thing to get file info in this program mainly copied from d2l files
	final static String fileName = "res\\movies.txt";
	final static String delimiter = ","; // to separate the info in the files
	
	public static ArrayList<Movie> loadMovieList(){
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		
		String line;
		String [] fields;
		Movie data;
		try(BufferedReader in = new BufferedReader(new FileReader(fileName))){
			line = in.readLine();
			while(line != null) {
				fields = line.split(delimiter);
				data = new Movie((fields[0]),(fields[1]),(fields[2]));
				movieList.add(data);//adding data to the list
				line = in.readLine();//preping the while loop to either continue with the next line or grabing nothing form the file to end the loop 				
			}
		
		}catch (IOException e) {
			System.out.println("Error: Input output exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieList;
		
	}
	
	public static void saveMovieListToFile(ArrayList<Movie> movieList) {
		if(movieList != null) {
			try(PrintWriter out = new PrintWriter(
					new BufferedWriter(new FileWriter(fileName)))){
				for(Movie m : movieList) {
					out.print(m.getDuration()+ delimiter);//print these things to file
					out.print(m.getTitle()+ delimiter);
					out.print(m.getYear());
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void addMovie(ArrayList<Movie> movieList) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Duration: ");
		String duration = sc.nextLine();
		System.out.println("Enter Movie Title: ");
		String title = sc.nextLine();
		System.out.println("Enter Movie Year:  ");
		String year = sc.nextLine();
		Movie data;
		data = new Movie((duration),(title),(year));
		movieList.add(data);
		System.out.println("Saving Movies. . .");
		System.out.println("Added movie to the data file.");
	}
	public static void displayMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Movie Management system");
		System.out.println("1		Add New Movie and Save");
		System.out.println("2		Generate List Of Movies Released in a Year");
		System.out.println("3		Generate List of Random Movies");
		System.out.println("4		Exit\n");
		System.out.println("Enter an option:	");
		int choice = sc.nextInt();
		if(choice == 1) {
			//addMovie();
		}
		
		}
}
