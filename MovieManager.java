import java.util.ArrayList;
import java.io.*;
public class MovieManager {
	
	

	//thing to get file info in this program mainly copied from d2l files
	final static String fileName = "res\\movies.txt";
	final static String delimiter = ","; // to separate the info in the files
	
	public static ArrayList<Movie> loadMovieList(){
		ArrayList<Movie> moviesList = new ArrayList<Movie>();
		
		String line;
		String [] fields;
		Movie data;
		try(BufferedReader in = new BufferedReader(new FileReader(fileName))){
			line = in.readLine();
			while(line != null) {
				fields = line.split(delimiter);
				data = new Movie((fields[0]),(fields[1]),(fields[2]));
				moviesList.add(data);//adding data to the list
				line = in.readLine();//preping the while loop to either continue with the next line or grabing nothing form the file to end the loop 				
			}
		
		}catch (IOException e) {
			System.out.println("Error: Input output exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return moviesList;
		
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
	
	public static String addMovie() {
		
	}
}
