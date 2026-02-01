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
		String[] fields;
		Movie data;
		try(BufferedReader in = new BufferedReader(new FileReader(fileName))){
			line = in.readLine();
			while(line != null) {
				line = line.trim();
				if (line.isBlank()) { // quick end instead of run through the whole loop again 
					continue;
				}
				fields = line.split(delimiter);
				if (fields.length == 3){
					int duration = Integer.parseInt(fields[0].trim());
					String title = (fields[1].trim());
					int year = Integer.parseInt(fields[2].trim());
					data = new Movie(duration,title,year);
					movieList.add(data);//adding data to the list
					line = in.readLine();//preping the while loop to either continue with the next line or grabing nothing form the file to end the loop
					//System.out.println(line);//testing purposes
				}
			}
		
		}catch (IOException e) {
			System.out.println("Error: Input output exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(movieList);//testing purposes
		return movieList;
		
	}
	
	public static void saveMovieListToFile(ArrayList<Movie> movieList) {
		if(movieList != null) {
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
				//System.out.println(movieList); //testing purposes
				for(Movie m : movieList) {
					out.print(m.getDuration()+ delimiter +m.getTitle() + delimiter + m.getYear());//print these things to file
					out.println();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
    public static void generateMovieListInYear(ArrayList<Movie> movieList, Scanner sc) {

        System.out.println("\nEnter in year: ");
        int year = sc.nextInt();
        sc.nextLine();

        System.out.println("\nMovie List");
        System.out.printf("%-10s %-6s %s%n", "Duration", "Year", "Title");


        int totalDuration = 0;

        for (Movie m : movieList) {
            if (m.getYear() == year) {
                System.out.printf("%-10d %-6d %s%n",
                        m.getDuration(),
                        m.getYear(),
                        m.getTitle());
                totalDuration += m.getDuration();
            }
        }

        System.out.println("Total duration: " + totalDuration + " minutes\n");
    }

	public static void generateRandomMovieList(ArrayList<Movie> movieList) {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many movies should be listed: ");
		int quantity = Integer.parseInt(sc.nextLine());
		
		if (quantity <= 0 || quantity > movieList.size()) {
			System.out.println("Invalid number.");
			return;
		}
		
		int totalDuration = 0;
		Random rand = new Random();
		
		System.out.println("\nDuration   Year   Title");
		
		for (int i = 0; i < quantity; i++) {
			int index = rand.nextInt(movieList.size());
			Movie m = movieList.get(index);
			
			System.out.println(m);
			totalDuration += m.getDuration();
		}
		
		System.out.println("\nTotal duration: " + totalDuration + " minutes");
	}
	
	public static void addMovie(ArrayList<Movie> movieList) {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		while (loop == true) {
			try {
			System.out.println("Enter Duration: ");
			int duration = Integer.parseInt(sc.nextLine().trim());
			System.out.println("Enter Movie Title: ");
			String title = sc.nextLine().trim();
			System.out.println("Enter Movie Year:  ");
			int year = Integer.parseInt(sc.nextLine().trim());
			if (title.isBlank()) {
				continue;
			}
			Movie data;
			data = new Movie((duration),(title),(year));
			movieList.add(data);
			loop = false;
			}catch (NumberFormatException e) {
				System.out.println("Invalid input! Please try again.");
			}
		}
		saveMovieListToFile(movieList);
		System.out.println("Saving Movies. . .");
		System.out.println("Added movie to the data file.");
	}
	
	public static void displayMenu(ArrayList<Movie> movieList) {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while(run == true) {
			System.out.println("Movie Management system");
			System.out.println("1		Add New Movie and Save");
			System.out.println("2		Generate List Of Movies Released in a Year");
			System.out.println("3		Generate List of Random Movies");
			System.out.println("4		Exit\n");
			System.out.println("Enter an option: ");
			int choice = Integer.parseInt(sc.nextLine());//getting the input from the user
			if(choice == 1) {
				addMovie(movieList);
			}else if (choice == 2) {
				generateMovieListInYear(movieList, sc);
			}else if (choice == 3) {
				generateRandomMovieList(movieList);
			}else if (choice == 4) {
				saveMovieListToFile(movieList);
				System.out.println("Goodbye!");
				run = false;
			}else {
				System.out.println("Invalid Option!");
			}
			
		}
	}
}

