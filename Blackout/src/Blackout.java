import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.script.*;
public class Blackout {
	public static void main (String[] args) throws ScriptException{
		String fileName=args[0];
		String line=null;

        try {
            FileReader fileReader = 
                new FileReader(fileName);

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            if ((line = bufferedReader.readLine()) != null) {
                line= bufferedReader.readLine();
            }//end if   

            bufferedReader.close();         
        }//end try
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }//end catch
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            
        }//end catch
        
		StringBuilder first=new StringBuilder(); //StringBuilders used to manipulate blackout String
		StringBuilder second= new StringBuilder();//See above
		StringBuilder firstHalfCopy= new StringBuilder();//See above
		StringBuilder secondHalfCopy= new StringBuilder();//See above
		StringBuilder sb= new StringBuilder();//See above
		StringBuilder firstHalf = new StringBuilder();//See above
		StringBuilder secondHalf= new StringBuilder();//See above
		int gateKeeper=99,firstResult = 999, secondResult=9999;//Gatekeeping ints to control flow and black out of vairables
		ScriptEngineManager mgr= new ScriptEngineManager();//engine manager to run strings as math
		ScriptEngine engine= mgr.getEngineByName("JavaScript");//engine to read mathmatical equation
		boolean gateKeep=false;//boolean used to make sure both sides of equation are true
		
		Integer check1=0,check2=0;//checking both sides of equation
		char firstdelete = 0,seconddelete = 0;//deleted chars
		
		sb.append(line);//adding line from file to allow for easy manipulation
		
		for (int c=0;c<sb.length();c++)//for loop determines where '=' sign is
		{
			char check= sb.charAt(c);
			if (check=='='){
				gateKeeper=c;
			}//end if
		}//end for
		
		for (int i=0;i<sb.length();i++)//breaks original line into 2 parts around the = sign
		{
			if (i<gateKeeper){
				firstHalf.append(sb.charAt(i));
			}//end if
			else if (i>gateKeeper){
				secondHalf.append(sb.charAt(i));
			}//end if
		}//end for
		
		while (gateKeep!=true)// actual manipulation of string and deletion of characters 
		{
			outerloop:
			for (int i=0;i<(sb.length()-1);i++)
			{ 
				firstHalfCopy.replace(0, firstHalfCopy.length(), firstHalf.toString()); 
				secondHalfCopy.replace(0, secondHalfCopy.length(), secondHalf.toString());
				if (i<=firstHalf.length())
				{
					firstdelete=firstHalfCopy.charAt(Math.min(i, firstHalfCopy.length()-1));
					firstHalfCopy.deleteCharAt(Math.min(i, firstHalfCopy.length()-1));
				}//end if
				else if (i>firstHalf.length())
				{
					int q=i-firstHalf.length();
				firstdelete=firstHalfCopy.charAt(Math.min(q, firstHalfCopy.length()-1));
					secondHalfCopy.deleteCharAt(Math.min(q, secondHalfCopy.length()-1));
				}// end else if
				for (int j=0;j<(sb.length()-1);j++)
				{ 
					
					first.replace(0, first.length(), firstHalfCopy.toString()); 
					second.replace(0, second.length(), secondHalfCopy.toString());
					
					if (j<=firstHalf.length())
					{
						seconddelete=first.charAt(Math.min(j, (first.length()-1)));
						first.deleteCharAt(Math.min(j, (first.length()-1)));
					}//end if
					else if (j>firstHalf.length())
					{
						seconddelete=first.charAt(Math.min(j, (first.length()-1)));
						second.deleteCharAt (Math.min(j, (second.length()-1)));
					}// end else if
					try {firstResult= (int) engine.eval(first.toString()); }//end try
					catch (Exception e){}
					 check1=firstResult;
					try { secondResult= (int) engine.eval(second.toString()); }//end try
					catch (Exception e){}
					 check2=secondResult;
					if (check1.equals(check2)){
						gateKeep=true;
						break outerloop;
						}//end if
					}//end for
			}//end for
	
		
		}//end while
		System.out.println("Deleted: "+firstdelete+","+seconddelete);
		System.out.println("Both sides equal:"+check1+","+check2);
	}//end main
}//end class
