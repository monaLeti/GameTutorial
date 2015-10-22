/*
 *Author: Leticia Trinidad Valderas Rodríguez
 * 
 * Siteimprove problem
 * 
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Siteimprove {
	
	
	public static void main(String []args){

	    String ficheros[]; //Different files
	    
	   //Read the directory where the files are saved
	    try{
	    	File directorio= new File(args[0]);
	    	ficheros = directorio.list();
	    	for(int n=0; n< ficheros.length; n++){
	    		search(ficheros[n], args);		    	
		   }
	    }catch(Exception e){
	    	 e.printStackTrace();
	    }
	  
	}
	
	public static void search(String fileName, String []miniWord){
		//Variables for opening the file
		File file = null;
	    FileReader fr = null;
	    BufferedReader br = null;

		String sentence[]; //Divide the file into sentences
	    String miniSentence[]; //Divide the previous sentences into words

	    String line; //Line read
	    int indexWord;
		try{
			file = new File(miniWord[0]+"/"+fileName);
			fr = new FileReader (file);
	        br = new BufferedReader(fr);
	                   
	         while((line=br.readLine())!=null){
	        	 indexWord = 1; //The pattern word starts args[1]
	        	 sentence=line.split(":",2);//Line divided by [id]:[content]
	        	 for(int i=1;i<sentence.length; i=i+2){
	        		 indexWord = 1;
	        		 miniSentence = sentence[i].split(" ");
	        		 for(int j=0; j< miniSentence.length; j++){   
	        			 /*Compare each word of the line with the search pattern*/
	        		    	Pattern pat = Pattern.compile(".*"+miniWord[indexWord]+".*");
	        				Matcher mat = pat.matcher(miniSentence[j]);
	        				if (mat.matches()) {
	        				       indexWord+=1;
	        				       if(indexWord==miniWord.length){
	        				    	   j=miniSentence.length;
	        				       }
	        				} else if(indexWord!=1){
	        				       indexWord = 1;
	        				}
	    			        			 
	        		 }
	        		if(indexWord==miniWord.length){
	        			System.out.println(sentence[i-1]);
	        			
	        		}
	        		
	        	 }
	         }
	        	
		}catch(Exception e){
			 e.printStackTrace();
		}finally{
			try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
			
		}
	}
}
