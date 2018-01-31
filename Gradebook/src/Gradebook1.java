//***************************************************************************************************************************
// Gradebook1.java                 Author: Pittman, Nathan for; computer science 155A 
//This program is used to calculate your grade in Computer Science 155A
//***************************************************************************************************************************
import java.util.*;
import java.text.DecimalFormat;
public class Gradebook1 {
	
	public static void main(String[] args) {
		double hwork, tests, lab, prs, ecred,x,y,z,r,s,q;
		DecimalFormat fmt = new DecimalFormat ("0.##"); 
		Scanner scanner = new Scanner (System.in); 
		
		System.out.println ("Please enter your homework grade, out of a possible 600 ");
		hwork = scanner.nextDouble ();
		x =(hwork*100)/600; 
		System.out.println("Your homework grade is " +fmt.format(x));
		//This will determine your decimal point grade for homework.
		
		System.out.println("Please enter your lab grade, out of a possible 600");
		lab = scanner.nextDouble (); 
		y = (lab*100)/600;
		System.out.println ("Your grade from your labs is "+fmt.format(y));
		
		System.out.println("Please enter your combined score from both midterms out of a possible 600.");
		tests = scanner.nextDouble ();
		z = (tests*100)/600;
		System.out.println ("The grade from your midterms is " + fmt.format(z));
		//This will determine your decimal point grade for both midterms combined.
		
		System.out.println("Please enter your PRS test grade out of a possible 100");
		prs = scanner.nextDouble ();
		r = (prs*100)/100;
		System.out.println("Your PRS test grade is "+ r);
		//This will determine your decimal point grade on the PRS test.
		
		System.out.println("Please enter your extra credit points out of a possible 200");
		ecred = scanner.nextDouble ();
		/*This is purely to add in your extra credit points and has no bearing on any decimal point grades except the* 
		grade point averages calculated at the end*/
		
		q = (hwork+lab+tests+prs+ecred);
		s = ((hwork+lab+tests+prs+ecred)*100)/1900;
		System.out.println("Your decimal point grade before the final is "+fmt.format(s));
		//establishes a base line score for the class 
		scanner.close ();
		
		double aa=(2400*.9667)-q; 
		System.out.println("you need " +fmt.format(aa) +" points or "+fmt.format((aa*100)/500)+ "%"+ " on your final to get an A+");
		
		double a=(2400*.9171)-q;
		System.out.println("You need "+fmt.format(a)+" points or " +fmt.format((a*100)/500)+ "%" + " on your final to get an A");
		
		double ab = (2400*.9004)-q;
		System.out.println("You need "+fmt.format(ab)+" points or "+fmt.format((ab*100)/500)+ "%"+ " on your final to get an A-");
		
		double ba =(2400*.8671)-q;
		System.out.println("You need "+fmt.format(ba)+" points or "+fmt.format((ba*100)/500)+ "%"+ " on your final to get a B+");
		
		double b = (2400*.8171)-q;
		System.out.println("You need "+fmt.format(b)+" points or "+fmt.format((b*100)/500)+ "%"+ " on your final to get a B");
		
		double bb = (2400*.8004)-q; 
		System.out.println("You need "+fmt.format(bb)+" points or "+fmt.format((bb*100)/500)+ "%"+ " on your final to get a B-");
		
		double ca = (2400*.7671)-q;
		System.out.println("You need "+fmt.format(ca)+" points or "+fmt.format((ca*100)/500)+ "%"+ " on your final to get a C+");
		
		double c = (2400*.7171)-q;
		System.out.println("You need "+fmt.format(c)+" points or "+fmt.format((c*100)/500)+ "%"+ " on your final to get a C");
		
		double cc  = (2400*.7004)-q;
		System.out.println("You need "+fmt.format(cc)+" points or "+fmt.format((cc*100)/500)+ "%"+ " on your final to get a C-");
		
		double da= (2400*.6671)-q;
		System.out.println("You need "+fmt.format(da)+" points or "+fmt.format((da*100)/500)+ "%"+ " on your final to get a D+");
		
		double d = (2400*.6171)-q;
		System.out.println("You need "+fmt.format(d)+" points or "+fmt.format((d*100)/500)+ "%"+ " on your final to get a D");
		
		double dd = (2400*.6004)-q;
		System.out.println("You need "+fmt.format(dd)+" points or "+fmt.format((dd*100)/500)+ "%"+ " on your final to get a D-");
		
		double f = (2400*.6003)-q;
		System.out.println(""+fmt.format(f)+" points or "+fmt.format((f*100)/500)+ "%"+ "  or lower on your final will result in you getting an F");
		
		//every two lines of code return the needed points for the indicated grade level in the class.
		System.out.println("Thank you for using this gradebook program, good luck on your final.");
		
		//end of program
						
	}

}