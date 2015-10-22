import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Prueba {
	public static void main(String []args){
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
	    FileWriter fichero = null;
        PrintWriter pw = null;
  
        String frase[];
	    String miniPalabra[];
	    String miniFrase[];
	    String ficheros[]; //Los ficheros de prueba
	
	    String linea;
	    int indexPalabra= 0;
			    
			try{
				archivo = new File("test.txt");
				fr = new FileReader (archivo);
		        br = new BufferedReader(fr);
		       
		     // Lectura del fichero
		         while((linea=br.readLine())!=null){
		        	 indexPalabra=0;
		        	 frase=linea.split(":",2);//linea de la forma [id]:[content]y la divido
		        	 for(int i=1;i<frase.length; i=i+2){
		        		 indexPalabra = 0;
		        		 //Una vez que estoy en la frase la divido en minifrases
		        		 miniFrase = frase[i].split(" ");
		        		 for(int j=0; j< miniFrase.length; j++){
		        			
		        		    Pattern pat = Pattern.compile(".*"+args[indexPalabra]+".*");
		        			Matcher mat = pat.matcher(miniFrase[j]);
		        			if (mat.matches()) {
		        				System.out.println("SI");
		        		        indexPalabra+=1;
		        		        if(indexPalabra==args.length){
		        				    j=miniFrase.length;
		        		        }
		        			} else if(indexPalabra!=0){
		        				       indexPalabra = 0;
		        			}
		        			 
		        		 }
		        		if(indexPalabra==args.length){
		        			System.out.println(frase[i-1]);
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


