
public class MinutesFilter implements Filter {
	private int minStart;
	private int minEnd;
	
	
	public MinutesFilter(int minStart, int minEnd) {
		this.minStart = minStart;
		this.minEnd = minEnd;
	}


	@Override
	public boolean satisfies(String id) {
		
		return MovieDatabase.getMinutes(id) <= minStart &&   MovieDatabase.getMinutes(id) >= minEnd;
	}

}
