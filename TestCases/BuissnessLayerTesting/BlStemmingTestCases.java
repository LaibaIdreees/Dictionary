package BuissnessLayerTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import buissnesslayer.BlStemmer;

class BlStemmingTestCases {

	BlStemmer b = new BlStemmer();
	@Test 
	@DisplayName("Test case to check value of stem is equal or not")
	public void stemValueCheck() {
		String word="أَشْغالٌ";
		assertEquals("شغل", b.getStemWord(word));
	}
	
	@Test 
	@DisplayName("Test case to check value of stem is not equal")
	public void stemValueFalseCheck() {
		String word="أَشْغالٌ";
		assertFalse("شغل"== b.getStemWord(word));
	}
	
}
