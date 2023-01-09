package buissnesslayer;

public class BlSearchByText {
static int j=0;
BlSearchWord search = new BlSearchWord(); 

	public int textToWord(String text) {

		String[] words = text.split("\\s+");
		
		
		for (int i = 0; i < words.length; i++) {
		    
			search.searchWord(words[i]);
			j++;
		}

		return j;
	}
	public BlSearchWord returnList() {
		return search;
	}
	
}
