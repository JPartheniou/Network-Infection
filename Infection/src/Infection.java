import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Infection {
	
	public static void main(String args[])throws  IOException{
		
	
		
	           Scanner scanner1 = new Scanner(new File("soc-Epinions2.txt")); 
	           int [][] epinions = new int [508837][2];
	           
	           
	           while(scanner1.hasNextInt()){			// assigns the soc-Epinions2.txt in an array
	        	   for (int i=0;i<508837;i++){
	        		   for (int j=0;j<2;j++){
	        			   epinions[i][j] = scanner1.nextInt();
	        			   	        		   }
	        		  	        	   }
	           					}
	           
	           Scanner scanner2 = new Scanner(new File("AuthorityWeights2.txt"));
	           int [] authority = new int [3794];
	           int x = 0;
	           while(scanner2.hasNextInt()){			//assigns the AuthorityWeights2.txt file in an array
	            authority[x] = scanner2.nextInt();	            
	            x++;
	           }
	           
	           
	           Scanner scanner3 = new Scanner(new File("InputDegree2.txt"));
	           int [] degree = new int [3794];
	           int w = 0;
	           while(scanner3.hasNextInt()){			//assigns the InputDegree2.txt file in an array
	            degree[w] = scanner3.nextInt();	            
	            w++;
	           }
	           
	           for (int run=0;run<2;run++){				//the for that changes from degree to authority
	        	   int prog;
	        	   if (run==0){
	           System.out.println("Running for In-Degree Weights");
	                
	           	   }
	        	   else{
	        		   System.out.println("Running for Authority Weights"); 
	        		  
	        	   }
	          
	           
	           for (int roll=0;roll<3794;roll++){   		
	        	   
	        	   if (run==0){
	    	          
	    	                 prog = degree[roll];			 //assigns the value of Degree to the prog variable 
	    	           	   }
	    	        	   else{
	    	        		   
	    	        		   prog = authority[roll];		//assigns the value of Authority to the prog variable 
	    	        	   }
	        	   System.out.println(prog);
	            String [] Status = new String [75879];		//the array to save the status of the nodes
	            int count=0;
	            for (int a=0;a<75879;a++){					//initialize the Status array
	        	   Status[a] = "Not reached"; 
	            }
	            
	            int ff = prog;
	            Status[ff] = "Infected";					//infects the selected node
	            
	            for (int a=1;a<75879;a++){		            //loops to infect the neighbors of the selected node
	            	for(int y=0; y<508837 ; y++){
	            		Random generator = new Random();		            
			            
	            		if(prog == epinions[y][0]){
	            			int chance1 = generator.nextInt(100);		
	            			if (chance1 <= 4){						//the chance to infect is 4%
	            			int q = epinions[y][1];
	            			Status[q]= "Infected";
	            			}
	            		else {
	            			int q = epinions[y][1];
            			Status[q]= "Immune";
            			}
	            		}
	            	}
	            	for(int y=0; y<508837 ; y++){				//loops to infect the neighbors of the neighbors
	            		Random generator = new Random();	    
			            
	            		if(Status[a]=="Infected"){
	            			if(a == epinions[y][0]){	
	            				int chance = generator.nextInt(100);
		            			int q = epinions[y][1];
		            				if (Status[q] == "Not reached"){
		            					if (chance <= 4){
		            					Status[q] = "Infected";
		            					
		            					}
		            				  
		            				else{
		            					Status[q] = "Immune";
		            				}
	            				}
	            			}
	            			}            		
	            		}
	           
	            	}
	          
	            for(int p=0;p<75879;p++){
		        	   if (Status[p]=="Infected"){			//counts to find the number of infected nodes
		        	   count++;
		           
		           }
	           
	        	   }
	        	   System.out.println(count/75879*100 + "%"); //calculates the percentage of the infected nodes
	          
	           }
	           
	           }
	           
	       }          
	}
