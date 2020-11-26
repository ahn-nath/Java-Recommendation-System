import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	private ThirdRatings object = new ThirdRatings("./data/ratings_short.csv");
	private ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
	
	void printAverageRatings() {
		ArrayList<Rating> movieRatings;	
	
			//print size for raters and movies
			System.out.println("Movies size:" + myMovies.size());
			System.out.println("Raters size:" + object.getRaterSize());
			
			//print ratings for each movie with minimal ratings
			movieRatings = object.getAverageRatings(1);
			//System.out.println(movieRatings);
			
			
			System.out.println("found " + movieRatings.size() + " movies");
			
			
			
			Collections.sort(movieRatings, (o1,  o2) -> o1.compareTo(o2));
			 for(Rating each:movieRatings) {
				 //ratings + title
			 System.out.println(each.getValue() + " " +  MovieDatabase.getTitle(each.getItem()));
			 }
		
	}
	
	void printAverageRatingsYear(int year) {
		ArrayList<Rating> movieRatings;	
	
			//print size for raters and movies
			System.out.println("Movies size:" + myMovies.size());
			System.out.println("Raters size:" + object.getRaterSize());
			
			//print ratings for each movie with minimal ratings and with year filter
			Filter filter = new YearAfterFilter(year);
			movieRatings = object.getAverageRatingsByFilter(1, filter);
			//System.out.println(movieRatings);
			
			
			System.out.println("found " + movieRatings.size() + " movies");
			
			
			
			Collections.sort(movieRatings, (o1,  o2) -> o1.compareTo(o2));
			 for(Rating each:movieRatings) {
				 //ratings + title
			 System.out.println(each.getValue() + " " +  MovieDatabase.getYear(each.getItem()) + " " +  MovieDatabase.getTitle(each.getItem()));
			 }
		
	}
	
	
	
	void printAverageRatingsGenre(String genre) {
		ArrayList<Rating> movieRatings;	
	
			//print size for raters and movies
			System.out.println("Movies size:" + myMovies.size());
			System.out.println("Raters size:" + object.getRaterSize());
			
			//print ratings for each movie with minimal ratings and with year filter
			Filter filter = new GenreFilter(genre);
			movieRatings = object.getAverageRatingsByFilter(1, filter);
			//System.out.println(movieRatings);
			
			
			System.out.println("found " + movieRatings.size() + " movies");
			
			
			
			Collections.sort(movieRatings, (o1,  o2) -> o1.compareTo(o2));
			 for(Rating each:movieRatings) {
				 //ratings + title
			 System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()) + 
					 '\n' +  MovieDatabase.getGenres(each.getItem()) + " ");
			 }
		
	}
	
	
	void printAverageRatingsMinutes(int start, int end) {
		ArrayList<Rating> movieRatings;	
	
			//print size for raters and movies
			System.out.println("Movies size:" + myMovies.size());
			System.out.println("Raters size:" + object.getRaterSize());
			
			//print ratings for each movie with minimal ratings and with year filter
			Filter filter = new MinutesFilter(start, end);
			movieRatings = object.getAverageRatingsByFilter(1, filter);
			//System.out.println(movieRatings);
			
			
			System.out.println("found " + movieRatings.size() + " movies");
			
			
			
			Collections.sort(movieRatings, (o1,  o2) -> o1.compareTo(o2));
			 for(Rating each:movieRatings) {
				 //ratings + title
			 System.out.println(each.getValue() + " Time: " + MovieDatabase.getMinutes(each.getItem())  + " " +  MovieDatabase.getTitle(each.getItem()) + " ");
			 }
		
	}
	
	
	void printAverageRatingsDirectors(String directors) {
		ArrayList<Rating> movieRatings;	
	
			//print size for raters and movies
			System.out.println("Movies size:" + myMovies.size());
			System.out.println("Raters size:" + object.getRaterSize());
			
			//print ratings for each movie with minimal ratings and with year filter
			Filter filter = new DirectorsFilter(directors);
			movieRatings = object.getAverageRatingsByFilter(1, filter);
			//System.out.println(movieRatings);
			
			
			System.out.println("found " + movieRatings.size() + " movies");
			
			
			
			Collections.sort(movieRatings, (o1,  o2) -> o1.compareTo(o2));
			 for(Rating each:movieRatings) {
				 //ratings + title
			 System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()) + 
					 '\n' +  MovieDatabase.getDirector(each.getItem()) + " ");
			 }
		
	}
	
	void printAverageRatingsByYearAfterAndGenre(int year, String genre) {
		ArrayList<Rating> movieRatings;	
	
			//print size for raters and movies
			System.out.println("Movies size:" + myMovies.size());
			System.out.println("Raters size:" + object.getRaterSize());
			
			//print ratings for each movie with criteria
			Filter filter1 = new GenreFilter(genre);
			Filter filter2 = new YearAfterFilter(year);
			AllFilters filters = new AllFilters();
			//add filters for genre and year
			filters.addFilter(filter1);
			filters.addFilter(filter2);
			
			movieRatings = object.getAverageRatingsByFilter(1, filters);
			//System.out.println(movieRatings);
			
			
			System.out.println("found " + movieRatings.size() + " movies");
			
			
			
			Collections.sort(movieRatings, (o1,  o2) -> o1.compareTo(o2));
			 for(Rating each:movieRatings) {
				 //ratings + title
			 System.out.println(each.getValue() + " " + MovieDatabase.getYear(each.getItem()) + " " + MovieDatabase.getTitle(each.getItem()) + 
					 '\n' +  MovieDatabase.getGenres(each.getItem()) + " ");
			 }
		
	}
	
	
	void printAverageRatingsByDirectorsAndMinutes(int start, int end, String directors) {
		ArrayList<Rating> movieRatings;	
	
			//print size for raters and movies
			System.out.println("Movies size:" + myMovies.size());
			System.out.println("Raters size:" + object.getRaterSize());
			
			//print ratings for each movie with criteria
			Filter filter1 = new DirectorsFilter(directors);
			Filter filter2 = new MinutesFilter(start, end);
			AllFilters filters = new AllFilters();
			//add filters for genre and year
			filters.addFilter(filter1);
			filters.addFilter(filter2);
			
			movieRatings = object.getAverageRatingsByFilter(1, filters);
			//System.out.println(movieRatings);
			
			
			System.out.println("found " + movieRatings.size() + " movies");
			
			
			
			Collections.sort(movieRatings, (o1,  o2) -> o1.compareTo(o2));
			 for(Rating each:movieRatings) {
				 //ratings + title
			 System.out.println(each.getValue() + " " + MovieDatabase.getMinutes(each.getItem()) + " " + MovieDatabase.getDirector(each.getItem()) + 
					 '\n' +  MovieDatabase.getGenres(each.getItem()) + " ");
			 }
		
	}
	
	

}