import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class RecommendationRunner implements Recommender{

	@Override
	public ArrayList<String> getItemsToRate() {
		//filters
		Filter filter = new TrueFilter();
		
		//return movies that meet the filterCriteria
		ArrayList<String> movies = MovieDatabase.filterBy(filter);
		int startIndex = 0;
		int endIndex = (movies.size() > 20) ? 20: movies.size();
		//ArrayList<String> moviesToDisplay = new ArrayList<String>(movies.subList(startIndex, endIndex));
		
		return new ArrayList<String>(movies.subList(startIndex, endIndex));
		
	}

	@Override
	public void printRecommendationsFor(String webRaterID) {
		ArrayList<Rating> movieRatings;
		FourthRatings object = new FourthRatings(); 
	
		int numSimilarRaters = 10;
		int minimalRaters = 3;

		//get recommended movies
		movieRatings = object.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
		
		//debug
		//getRatings method
		//System.out.println("movie ratings" + movieRatings.get(0));
		if(movieRatings.size() > 0) {
			System.out.println();
			System.out.println("Movies found: " + movieRatings.size());
			

		//sublist of movieRatings
		int startIndex = 0;
		int endIndex = (movieRatings.size() > 20) ? 20: movieRatings.size();	
		ArrayList<Rating> moviesToDisplay = new ArrayList<Rating>(movieRatings.subList(startIndex, endIndex));
		Collections.sort(movieRatings, (o1, o2) -> o1.compareTo(o2));
		
		//Test HTML
		//Heading
        System.out.println("<html><body><h1>Recommended movies for you</h1>");
        System.out.print("<html><table class=\"movTab\">");
        System.out.print("<tr class=\"movieTitle\"><th class=\"movHead\">Movie</th><th class=\"movHead\">Title</th> <th class=\\\"movHead\\\">Genre(s)</th> <th class=\\\"movHead\\\">Director(s)</th> <th class=\\\"movHead\\\">Year</th></tr>");
        
        for (Rating each : movieRatings) {
        //Section for the movies
        System.out.print("<tr>");
        
        
        //image
        System.out.print("<td>");
        System.out.print("<img src=\"");
        System.out.print(MovieDatabase.getPoster(each.getItem()));
        System.out.print("\" alt=\"Movie icon\" width=\"80\" height=\"80\">");
        System.out.print("</td>");
        
        
        //title
        System.out.print("<td>");
        System.out.print(MovieDatabase.getTitle(each.getItem()));
        System.out.print("</td>");
        
        //genres
        System.out.print("<td>");
        System.out.print(MovieDatabase.getGenres(each.getItem()));
        System.out.print("</td>");
        
        //director
        System.out.print("<td>");
        System.out.print(MovieDatabase.getDirector(each.getItem()));
        System.out.print("</td>");
        
        //year
        System.out.print("<td>");
        System.out.print(MovieDatabase.getYear(each.getItem()));
        System.out.print("</td>");
        
        
        System.out.print("</tr>");
        }
	  
        //Close
        System.out.print("</body></table></html>");
        
        

        //CSS
        System.out.println("<style>.movieTitle{color:#000000; text-transform:uppercase;}"
        		+ "h1{text-transform: uppercase;}"
        		+ "tr:nth-child(even){background-color: #dddddd;}"
        		+ "td, th {border: 1px solid #bdbdbd; text-align:center; padding: 10px;}"
        		+ "table{border-spacing: 0.5px; margin:auto;}"
        		+ "</style>");
		}else{
			System.out.println("<html><body><h1>There are no recommended movies available</h1>");
		}
		}
	

}
