import java.util.ArrayList;
import java.util.Collections;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class RecommendationRunner implements Recommender{

	@Override
	public ArrayList<String> getItemsToRate() {
		//filters
		Filter filter1 = new GenreFilter("Thriller");
		Filter filter2 = new YearAfterFilter(2015);
		AllFilters filterCriteria = new AllFilters();
		
		// add filters for genre and year
		filterCriteria.addFilter(filter1);
		filterCriteria.addFilter(filter2);
		
		
		//return movies that meet the filterCriteria
		return MovieDatabase.filterBy(filterCriteria);
		
	}

	@Override
	public void printRecommendationsFor(String webRaterID) {
		ArrayList<Rating> movieRatings;
		FourthRatings object = new FourthRatings(); 
	
		int numSimilarRaters = 10;
		int minimalRaters = 5;
		String genre = "Thriller";

		//get recommended movies
		Filter filter = new GenreFilter(genre);
		movieRatings = object.getSimilarRatingsByFilter(webRaterID, numSimilarRaters, minimalRaters, filter);
		
		//sublist of movieRatings
		int startIndex = 0;
		int endIndex = (movieRatings.size() > 20) ? 20: movieRatings.size();
				
		ArrayList<Rating> moviesToDisplay = new ArrayList<Rating>(movieRatings.subList(startIndex, endIndex));
		
		//Test HTML
		
		String html = "<html><body><h1>hello world</h1></body></html>";

        Document doc = Jsoup.parse(html);   // pretty print HTML
        System.out.println(doc.toString());
	}

}
