import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	private FourthRatings object = new FourthRatings(); // change to fourth
	private ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
	private ArrayList<Rater> myRaters = RaterDatabase.getRaters();

	void printAverageRatings(int minimalRatings) {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		// print ratings for each movie with minimal ratings
		movieRatings = object.getAverageRatings(minimalRatings);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()));
		}

	}

	void printAverageRatingsYear(int minimalRatings, int year) {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		// print ratings for each movie with minimal ratings and with year filter
		Filter filter = new YearAfterFilter(year);
		movieRatings = object.getAverageRatingsByFilter(minimalRatings, filter);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getYear(each.getItem()) + " "
					+ MovieDatabase.getTitle(each.getItem()));
		}

	}

	void printAverageRatingsGenre(int minimalRatings, String genre) {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		// print ratings for each movie with minimal ratings and with year filter
		Filter filter = new GenreFilter(genre);
		movieRatings = object.getAverageRatingsByFilter(minimalRatings, filter);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()) + '\n'
					+ MovieDatabase.getGenres(each.getItem()) + " ");
		}

	}

	void printAverageRatingsMinutes(int minimalRatings, int start, int end) {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		// print ratings for each movie with minimal ratings and with year filter
		Filter filter = new MinutesFilter(start, end);
		movieRatings = object.getAverageRatingsByFilter(minimalRatings, filter);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " Time: " + MovieDatabase.getMinutes(each.getItem()) + " "
					+ MovieDatabase.getTitle(each.getItem()) + " ");
		}

	}

	void printAverageRatingsDirectors(int minimalRatings, String directors) {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		// print ratings for each movie with minimal ratings and with year filter
		Filter filter = new DirectorsFilter(directors);
		movieRatings = object.getAverageRatingsByFilter(minimalRatings, filter);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()) + '\n'
					+ MovieDatabase.getDirector(each.getItem()) + " ");
		}

	}

	void printAverageRatingsByYearAfterAndGenre(int minimalRatings, int year, String genre) {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		// print ratings for each movie with criteria
		Filter filter1 = new GenreFilter(genre);
		Filter filter2 = new YearAfterFilter(year);
		AllFilters filters = new AllFilters();
		// add filters for genre and year
		filters.addFilter(filter1);
		filters.addFilter(filter2);

		movieRatings = object.getAverageRatingsByFilter(minimalRatings, filters);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getYear(each.getItem()) + " "
					+ MovieDatabase.getTitle(each.getItem()) + '\n' + MovieDatabase.getGenres(each.getItem()) + " ");
		}

	}

	void printAverageRatingsByDirectorsAndMinutes(int minimalRatings, int start, int end, String directors) {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		// print ratings for each movie with criteria
		Filter filter1 = new DirectorsFilter(directors);
		Filter filter2 = new MinutesFilter(start, end);
		AllFilters filters = new AllFilters();
		// add filters for genre and year
		filters.addFilter(filter1);
		filters.addFilter(filter2);

		movieRatings = object.getAverageRatingsByFilter(minimalRatings, filters);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " Time: " + MovieDatabase.getMinutes(each.getItem()) + " "
					+ MovieDatabase.getTitle(each.getItem()) + '\n' + MovieDatabase.getDirector(each.getItem()) + " ");
		}

	}

	// SIMILAR RATINGS
	void printSimilarRatings() {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		String id = "71";
		int numSimilarRaters = 20;
		int minimalRaters = 5;

		// print ratings for each movie with minimal ratings
		movieRatings = object.getSimilarRatings(id, numSimilarRaters, minimalRaters);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()));
		}

	}
	
	
	void printSimilarRatingsbyGenre() {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		String id = "964";
		int numSimilarRaters = 20;
		int minimalRaters = 5;
		String genre = "Mystery";

		// print ratings for each movie with minimal ratings
		Filter filter = new GenreFilter(genre);
		movieRatings = object.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filter);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()) + '\n' 
					+ MovieDatabase.getGenres(each.getItem()) );
		}

	}
	
	
	void printSimilarRatingsbyDirector() {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		String id = "120";
		int numSimilarRaters = 10;
		int minimalRaters = 2;
		String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";

		// print ratings for each movie with minimal ratings
		Filter filter = new DirectorsFilter(director);
		movieRatings = object.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filter);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()) + '\n' 
					+ MovieDatabase.getGenres(each.getItem()) );
		}

	}
	
	
	
	void printSimilarRatingsbyByGenreAndMinutes () {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		String id = "168";
		int numSimilarRaters = 10;
		int minimalRaters = 3;
		String genre = "Drama";
		

		// print ratings for each movie with minimal ratings
		Filter filter1 = new GenreFilter(genre);
		Filter filter2 = new MinutesFilter(80,160);
		AllFilters filters = new AllFilters();
		// add filters for genre and year
		filters.addFilter(filter1);
		filters.addFilter(filter2);
		movieRatings = object.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filters);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()) +  " Time: " + MovieDatabase.getMinutes(each.getItem()) + '\n' 
					+ MovieDatabase.getGenres(each.getItem()) );
		}

	}
	
	
	void printSimilarRatingsbyByYearAfterAndMinutes() {
		ArrayList<Rating> movieRatings;

		// print size for raters and movies
		printObjectsSize();

		String id = "314";
		int numSimilarRaters = 10;
		int minimalRaters = 5;
		int year = 1975;
		

		// print ratings for each movie with minimal ratings
		Filter filter1 = new YearAfterFilter(year);
		Filter filter2 = new MinutesFilter(70,200);
		AllFilters filters = new AllFilters();
		// add filters for genre and year
		filters.addFilter(filter1);
		filters.addFilter(filter2);
		movieRatings = object.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, filters);
		// System.out.println(movieRatings);

		System.out.println("found " + movieRatings.size() + " movies");

		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		for (Rating each : movieRatings) {
			// ratings + title
			System.out.println(each.getValue() + " " + MovieDatabase.getTitle(each.getItem()) + " " + MovieDatabase.getYear(each.getItem()) + '\n' 
					+  " Time: " + MovieDatabase.getMinutes(each.getItem()) );
		}

	}

	void printObjectsSize() {
		// print size for raters and movies
		System.out.println("Movies size:" + myMovies.size());
		System.out.println("Raters size:" + myRaters.size());
	}

}
