import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class EfficientRater implements Rater{
	private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, (new Rating(item,rating)));
    }

    public boolean hasRating(String item) {
        
            if (myRatings.get(item)!= null){
                return true;
            }
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public HashMap<String, Rating> getMyRatings() {
		return myRatings;
	}
    


	public double getRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        
        return list;
    }
    
    
    // Returns a string of the item's information
    @Override
    public String toString () {
        String result = "Rater [id=" + this.myID;
        result += ", ratings= " + myRatings + "]";
        return result;
    }
}
