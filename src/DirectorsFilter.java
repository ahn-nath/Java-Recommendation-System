import java.util.Arrays;

public class DirectorsFilter implements Filter{
	private String[] myDirectors;
	
	public DirectorsFilter(String directors) {
		myDirectors = directors.split(",");
	}
	
	@Override
	public boolean satisfies(String id) {
		String movieDirectors = MovieDatabase.getDirector(id);
		return Arrays.stream(myDirectors).anyMatch(movieDirectors::contains);
	}
}

