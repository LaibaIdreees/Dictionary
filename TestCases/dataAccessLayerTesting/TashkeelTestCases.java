package dataAccessLayerTesting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dataAccessLayer.Tashkeel;

class TashkeelTestCases {
		Tashkeel d; 

		@Test
		@DisplayName("Test case to check if object is created")
		void testObjectCreation() {
			d=new Tashkeel("أاِتِّسَاخٌ");
			 assertNotNull(d);
		}
		
		@Test
		@DisplayName("Test case to check if object not created")
		void testNotObjectCreation() {
			d=null;
			 assertNull(d);
		}
		
		@Test
		@DisplayName("Test case to check if func removes all the tashkeel")
	    public void testTashkeelEqual() { 
			d=new Tashkeel("أاِتِّسَاخٌ");
	      String temp =  d.getOutput(); 
	        assertEquals("أاتساخ", temp);  
	    }  
		
		@Test
		@DisplayName("Test case to check if func does not removes all the tashkeel")
	    public void testTashkeelNotequal() { 
			d=new Tashkeel("أاِتِّسَاخٌ");
	      String temp =  d.getOutput(); 
	        assertFalse("أاِتِّسَاخٌ" == temp);  
	    }

	}

