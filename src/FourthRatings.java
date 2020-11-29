import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FourthRatings {
	private ArrayList<Rater> myRaters = RaterDatabase.getRaters();
	
	//Similar Ratings
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
			//if rater and me are the same objects, don't compare them/calculate dotProduct
			if(rater.equals(me)) {
				continue;
			}
			
			int product = dotProduct(me, rater);
			if(product > 0) {
				ratingsSimilar.add(new Rating(rater.getID(), product));
			}
		}
		//sort Rating objects in ascending order
		Collections.sort(ratingsSimilar, Collections.reverseOrder());
		return ratingsSimilar;
	}
	
	
	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
		//returns similar Ratings (no filters)
		return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
	}
	
	
	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filter) {
		ArrayList<Rating> movieRatings = new ArrayList<Rating>();
		ArrayList<String> myMovies = MovieDatabase.filterBy(filter);
		
		
		//get all Raters with dot product (similarity score)
		ArrayList<Rating> raters = getSimilarities(id);

		for(String movieID: myMovies) {
			double movieAverage = getAverageByIDTop(movieID, minimalRaters, raters, numSimilarRaters);
			//add if result is greater than 0
			if(movieAverage > 0) {
			movieRatings.add(new Rating(movieID, movieAverage));
		}
		
		}
		
		
		return movieRatings;
	}
	
	private double getAverageByIDTop(String movieId, int minimalRaters, ArrayList<Rating> listRaters,
			int numSimilarRaters) {

		ArrayList<Rating> topRaters = new ArrayList<Rating>();
		int endIndex = (listRaters.size() >= numSimilarRaters) ? numSimilarRaters: listRaters.size();
		for (int i=0; i < endIndex; i++) {
		    topRaters.add(new Rating(listRaters.get(i).getItem(), listRaters.get(i).getValue()));
		    
		 
		}
		
		// get all Rating objects for particular movie
		HashMap<Double, Double> matches = new HashMap<Double, Double>();
		
		int i = 0;
		for (Rating each: topRaters) {
			//get Rater id
			
			i++; //rater num
			
			Rater rater = RaterDatabase.getRater(each.getItem());
			HashMap<String, Rating> raterMovieRatings = rater.getMyRatings();
			
			// if Rater rated that movie (id), get rating given and add it to matches with
			// weighted rating for Rater
			if(raterMovieRatings.containsKey(movieId)) {
				// get rating Rater gave to a particular movie from list of Ratings
				Double ratingGivenMovie = raterMovieRatings.get(movieId).getValue();
				Double ratingWeight = each.getValue();

				//store rating weight and rating value
				matches.put(ratingWeight, ratingGivenMovie);
				
				
			}

		}

		double sum = 0;
		if (matches.size() >= minimalRaters) {

			for (Double weight : matches.keySet()) {
			
				Double rating = matches.get(weight);
				//multiply rating per its value and add it to sum
				sum += (weight * rating);
			}

			//average for this movie (sum / total ratings)
			return sum / matches.size();

		}

		return 0.0;

	}
	
	
	
	
	
	
	
	
	//Get list of movies that both rated
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
