import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Progress {

	public static void main(String[] args) {
		 String csvFile = "./data/ratedmoviesfull.csv";
		 String csvFile_rate = "./data/ratings.csv";
		
		FirstRatings check = new FirstRatings();
		ThirdRatings check3 = new ThirdRatings(csvFile_rate);
		FourthRatings check4 = new FourthRatings();
		MovieRunnerWithFilters runner2 = new MovieRunnerWithFilters();
		MovieRunnerSimilarRatings runner3 = new MovieRunnerSimilarRatings();
		
		
		 
		//System.out.println("MOVIES");
		//System.out.println();
		//check.testMovies(csvFile);
		
		 
		//System.out.println("RATERS");
		//System.out.println();
		//check.testRaters(csvFile_rate);
	    
		
		//runner2.printAverageRatings(35);
		//runner2.printAverageRatingsYear(20, 2000);
		//runner2.printAverageRatingsGenre("Crime");
		//runner2.printAverageRatingsMinutes(110,170);
		//runner2.printAverageRatingsDirectors("Charles Chaplin,Michael Mann,Spike Jonze");
		//runner2.printAverageRatingsByYearAfterAndGenre( 1980,"Romance");
		//runner2.printAverageRatingsByDirectorsAndMinutes( 30, 170,"Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
		//runner3.printAverageRatingsByYearAfterAndGenre(1, 1980,"Romance");
		//runner3.printAverageRatings(35);
		
		
		
	
		
		
		 
		
		 
		
	}
	
	

}
