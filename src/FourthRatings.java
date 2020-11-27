import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FourthRatings {
	private ArrayList<Rater> myRaters = RaterDatabase.getRaters();
	
	
	private int dotProduct(Rater me, Rater r) {
		int product = 0;
	
		HashMap<String, Rating> myRatings = me.getMyRatings();
		HashMap<String, Rating> theirRatings = r.getMyRatings();
		
		//get list with movie ID's of the items both rated
		ArrayList<String> keyList = matchItemsRated(myRatings, theirRatings);
		
		//get dot product
		for(String key:keyList){
			double r1 = (myRatings.get(key).getValue()) - 5;//converted value to scale of [-5-5] from [0-10]
			double r2 = (theirRatings.get(key).getValue()) - 5;
			product += (r1*r2);
			
		}
		
		return product;
	}
	
	private ArrayList<Rating> getSimilarities(String id){
		ArrayList<Rating> ratingsSimilar = new ArrayList<Rating>();
		Rater me = RaterDatabase.getRater(id);
		
		for (Rater rater:myRaters) {
			int product = dotProduct(me, rater);
			if(product > 0) {
				ratingsSimilar.addAll(rater.getMyRatings().values());
			}
		}
		
		return null;
	}
	
	private ArrayList<String> matchItemsRated(HashMap<String, Rating> myRatings, HashMap<String, Rating> theirRatings) {
		ArrayList<String> keys = new ArrayList<String>();
	
		//add keys that match
		for (String key : myRatings.keySet()) {
			if(theirRatings.containsKey(key)) {
				keys.add(key);
			}
		}
		
		return keys;
		
	}
	
	
	private double getAverageByID(String id, int minimalRaters) {	
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
