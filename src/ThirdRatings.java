import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ThirdRatings {
	
	
	    private ArrayList<Rater> myRaters;
	    
	    public ThirdRatings() {
	        // default constructor
	        this("ratings.csv");
	    }

		public ThirdRatings(String ratingsFile) {
			FirstRatings object = new FirstRatings();
		
			myRaters =  object.loadRaters(ratingsFile);
		}
		
		
		public int getRaterSize() {
			return myRaters.size();
		}
		
		//make private later
		public double getAverageByID(String id, int minimalRaters) {
			
			//List with all Ratings objects from ArrayList
			List<HashMap<String, Rating>> allRatings = myRaters.stream()
					.map(Rater::getMyRatings)
					.collect(Collectors.toList());
			
			
			//get all Rating objects for particular movie
			List<Rating> matches = new ArrayList<Rating>();
			for(HashMap<String, Rating> each:allRatings) {
				
				//loop through each Hash map and get rating of key equal to id
				if(each.containsKey(id)) {
				matches.add(each.get(id));
				}
			}
			
			
		 	
			if(matches.size() >= minimalRaters) {
			
			//get all scores for particular movie
			List<Double> allScores = matches.stream()
					.map(Rating::getValue)
					.collect(Collectors.toList());
			
			
			//get values for particular movie id
			double sum = allScores.stream().mapToDouble(Double::doubleValue).sum();
			
			//average for this movie
			return sum/allScores.size();
			
			
			}
			
			return 0.0;
			
			
		}
		
		
		public ArrayList<Rating> getAverageRatings(int minimalRaters){
			ArrayList<Rating> movieRatings = new ArrayList<Rating>();
			ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
			
			//ArrayList with Movie IDs as strings
			for(String each:myMovies) {
				
				String movieID = each;
				Double movieAverage = getAverageByID(movieID, minimalRaters);
				
				if(movieAverage > 0.0) {
				movieRatings.add(new Rating(movieID, movieAverage));
				}
			}
			
	
			
			return movieRatings;
			
		}
		
		public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
			ArrayList<Rating> movieRatingsY = new ArrayList<Rating>();
			ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria); //YearsAfterFilter filter
			
			//ArrayList with Movie IDs as strings
			for(String each:myMovies) {
				
				String movieID = each;
				Double movieAverage = getAverageByID(movieID, minimalRaters);
				
				if(movieAverage > 0.0) {
				movieRatingsY.add(new Rating(movieID, movieAverage));
				}
			}
			
	
			
			return movieRatingsY;
		
		}
		
		
		
		
}
