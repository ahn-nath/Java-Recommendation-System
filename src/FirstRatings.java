
/**
 Used to process the movie and ratings data and to answer 
  questions about them.
 * 
 * @author Nathaly 
 * @version 11/02/2020
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FirstRatings {
	/*Used to process the movie and ratings data and to answer 
	 * questions about them*/
	private String filename;
	

	public void testMovies(String filename) {
		ArrayList<Movie> movies;
		String csvFile = filename;
		
		//1. Store the ArrayList with movies in local variable 
		movies = loadMovies(csvFile);
		
		// print number of movies
		System.out.println("Size of movies:" + movies.size());

		// print each movie
		for (Movie each : movies) {
			System.out.println(each.toString());
		}
		
	

		//-
		//2. Determine how many movies include the Comedy genre
		System.out.println("Comedy: " + findToken(movies, "Comedy"));
		
		//-
		//3. Determine how many movies are greater than 150 minutes
		System.out.println("> 150: " + findMinutes(movies, 150));

		//-
		//4. Determine the maximum number of movies by any director
		
		//key, value
		System.out.println("directors with count:");
		HashMap<String, Integer> countDirectors = countDir(movies);
		
		//max
		String maxKey = Collections
				.max(countDirectors.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
		System.out.println("director, count:" + maxKey + "," + countDirectors.get(maxKey));

	}

	
	
	
	public void testRaters(String filename) {
		ArrayList<Rater> raters;
		String csvFile = filename;
		
		//1. Store the ArrayList with raters in local variable 
		raters = loadRaters(csvFile);
		
		//print number of raters
		System.out.println("Size of array for raters" + raters.size());

		//print raters
		  for(Rater each:raters){
			  System.out.println(each.toString());
		  }
		

		//-
		//2. Determine number or ratings for a rater
		System.out.println("Ratings for this rater: " + numRatings(raters, "193"));

		
		//-
		//3. Determine the maximum number of ratings by any rater
				
		//key, value
		System.out.println("directors with count:");
		HashMap<String, Integer> countRaters = countRater(raters);
		
		//max
		String maxKey = Collections
				.max(countRaters.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
		System.out.println("rater, count:" + maxKey + "," + countRaters.get(maxKey));
		
		
		//-
		//4. Number of ratings a particular movie has
		String movie = "1798709";
		List<String> ratings = ratingsForMovie(raters,  movie);
		
		//number of times the movie appears
		int numberRatings =  Collections.frequency(ratings, movie);
		System.out.println("Ratings for the movie " + movie + " was: " + numberRatings);
		
		
		//-
		//5. Determine number of movies rated
		
		//set without duplicates
	    Set<String> set = new HashSet<>(ratings); 
  
        System.out.println("Number of movies rated:" + set.size()); 

		

	}


	
	
	//ArrayList for movies
	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> moviesList = new ArrayList<Movie>();

		//process
		BufferedReader reader = null;
		String line = "";

		try {
			
			reader = new BufferedReader(new FileReader(filename));
			//skip headers of the file
			reader.readLine();

			while ((line = reader.readLine()) != null) {
				
				//split string by semicolon/comma
				String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

				//read and validate the data
				int num = Integer.parseInt(tokens[0]); //eliminate leading zeros
				String id = String.valueOf(num);
				String title = tokens[1].replaceAll("\"", ""); //remove quotations marks
				String year = tokens[2];
				String country = tokens[3];
				String genres = tokens[4];
				String director = tokens[5];
				int minutes = Integer.parseInt(tokens[6]);
				String poster = tokens[7];

				Movie movie = new Movie(id, title, year, genres, director, country, poster, minutes);
				moviesList.add(movie); // add to ArrayList

			}
		}

		catch (IOException e) {

			e.printStackTrace();
		}

		return moviesList;
	}

	
	
	//ArrayList for raters
	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> ratersList = new ArrayList<Rater>();

		//process
		BufferedReader reader = null;
		String line = "";

		try {
	
			reader = new BufferedReader(new FileReader(filename));
			//skip headers of the file
			reader.readLine(); 
			int c = -1;
			int count = -1;
			Rater rater;

			while ((line = reader.readLine()) != null) {

				String[] tokens = line.split(","); //split string by semicolon/comma

				//read and validate the data
				String id = tokens[0];

				if (c != Integer.parseInt(id)) {
					//create new rater if id of rater changes
					rater = new EfficientRater(id);
					c = Integer.parseInt(id);
					ratersList.add(rater); //add empty rater
					count++; //new item to determine in which object to add new elements
				}

				//add movie id and rating to the current Rater object
				int num = Integer.parseInt(tokens[1]); //eliminate leading zeros
				ratersList.get(count).addRating(String.valueOf(num), Double.parseDouble(tokens[2]));

			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return ratersList;
	}

	
	
	//MOVIES
	
	//2. Determine how many movies include the Comedy genre
	public int findToken(List<Movie> ar, String token) {
		int comedy = 0;
		for (Movie each : ar) {
			if (each.getGenres().contains(token)) {
				comedy++;
			}
		}
		return comedy;

	}
	
	
	//3. Determine how many movies are greater than 150 minutes
	public int findMinutes(List<Movie> ar, int value) {
		int greater = 0;
		for (Movie each : ar) {
			if (each.getMinutes() > value) {
				greater++;
			}
		}

		return greater;

	}

	
	
	//4. Determine the maximum number of movies by any director
	public HashMap<String, Integer> countDir(List<Movie> menu) {

		List<Movie> matches = menu.stream().filter(h -> h.getDirector() != null).collect(Collectors.toList());

		// create list with actual values and separated
		List<String> allNames = new ArrayList<String>();
		for (Movie each : matches) {
			String[] arr = each.getDirector().split(",");
			String[] arr2 = Arrays.stream(arr).map(String::trim).toArray(String[]::new);
			Collections.addAll(allNames, arr2);
		}

		// get unique names from list of directors
		Set<String> values = new HashSet<>(allNames);

		// HashMap to save director name and count
		HashMap<String, Integer> countDirectors = new HashMap<String, Integer>();

		for (String each : values) {
			String director = each; // unique name to search for
			
			// String director = i.next().getName();
			int size = Collections.frequency(allNames, director);
			countDirectors.put(director, size);

		}
	
		return countDirectors;
	}



	// RATER

	public int numRatings(ArrayList<Rater> raters, String id) {

		for (int k = 0; k < raters.size(); k++) {
			if (raters.get(k).getID().equals(id)) {
				return raters.get(k).numRatings();
			}
		}
		return 0;

	}
	

	public HashMap<String, Integer> countRater(ArrayList<Rater> raters) {

		List<Rater> matches = raters.stream().filter(h -> h.getID() != null).collect(Collectors.toList());

		
		// HashMap to save director name and count
		HashMap<String, Integer> countRaters = new HashMap<String, Integer>();

		for (Rater each : matches) {
			String rater = each.getID(); // unique name to search for
			
			int size = each.numRatings();
			//rater and the number of ratings
			countRaters.put(rater, size);

		}
	
		return countRaters;
	}
	
	
	
	public List<String> ratingsForMovie(ArrayList<Rater> raters, String movie) {

			
		//get all objects where the ratings are not null
		 List<Rater> matches = raters.stream()
                .filter(h -> h.getItemsRated()!= null)
                .collect(Collectors.toList());
		
		 
		//create list with actual values and separated
	       List<String> ratings = new ArrayList<String>();
	       for(Rater each:matches) {
	    	   ArrayList<String> arr = each.getItemsRated();
	    	 
	    	   ratings.addAll(arr);
	       }
	      
		   
		   
		return ratings;

	}

}
