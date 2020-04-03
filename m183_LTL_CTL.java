import java.io.BufferedReader;
import java.io.InputStreamReader;

public class m183_LTL {
	static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	private String[] inputs = {"ai1_ce1","usr4_ai1_VoidReply","usr4_ni1_ne1","ai1_ce2","usr2_ai1_VoidReply"};

	public int a422009172 = -68;
	public boolean cf = true;
	public int a2108127495 = -44;
	public int a1522448132 = 173;
	public String a1745113960 = "h";

private  void calculateOutputm1(String input) {
    if(((input.equals("usr2_ai1_VoidReply")) && cf)) {
    	cf = false;
    	a1745113960 = "h";
    	a2108127495 = ((((((a2108127495 * a1522448132) % 14999) % 72) - 91) - -1) * 1); 
    	System.out.println("ai1_VoidReply");
    } 
}
private  void calculateOutputm2(String input) {
    if(((input.equals("usr4_ni1_ne1")) && cf)) {
    	cf = false;
    	 
    	System.out.println("ni1_ne1");
    } if(((input.equals("ai1_ce2")) && cf)) {
    	cf = false;
    	a1745113960 = "h";
    	a2108127495 = ((((a2108127495 % 59) - -98) - 4) + -42); 
    	System.out.println("usr4_ai1_ce2");
    } 
}
private  void calculateOutputm3(String input) {
    if((cf && (input.equals("usr4_ai1_VoidReply")))) {
    	cf = false;
    	a1522448132 = ((((((a2108127495 * a2108127495) % 50) - 75) * 5) % 50) + -31);
    	a2108127495 = ((((a2108127495 * a1522448132) - 22476) * 1) + -3649); 
    	System.out.println("usr2_ai1_ce4");
    } 
}
private  void calculateOutputm4(String input) {
    if((cf && (input.equals("ai1_ce2")))) {
    	cf = false;
    	 
    	System.out.println("ai1_VoidReply");
    } if((cf && (input.equals("ai1_ce1")))) {
    	cf = false;
    	a1745113960 = "g";
    	a2108127495 = ((((a2108127495 - -7243) - -1395) % 59) - -16); 
    	System.out.println("usr4_ai1_ce1");
    } if(((input.equals("usr4_ni1_ne1")) && cf)) {
    	cf = false;
    	a1745113960 = "i"; 
    	System.out.println("usr2_ai1_ce4");
    } 
}
private  void calculateOutputm5(String input) {
    if(((input.equals("usr2_ai1_VoidReply")) && cf)) {
    	cf = false;
    	a1745113960 = "h"; 
    	System.out.println("none");
    } 
}
private  void calculateOutputm6(String input) {
    if(((input.equals("usr4_ni1_ne1")) && cf)) {
    	cf = false;
    	a2108127495 = (((a2108127495 + 13863) * 2) * 1);
    	a422009172 = (((((((a2108127495 * a2108127495) % 14999) + -1380) % 47) - -150) + 23887) + -23885); 
    	System.out.println("none");
    } if((cf && (input.equals("usr4_ai1_VoidReply")))) {
    	cf = false;
    	a1745113960 = "e";
    	a2108127495 = (((a2108127495 + -139) + -4) + 5); 
    	System.out.println("ai1_VoidReply");
    } 
}
private  void calculateOutputm7(String input) {
    if(((input.equals("usr4_ni1_ne1")) && cf)) {
    	cf = false;
    	a1745113960 = "g";
    	a2108127495 = ((((a2108127495 - 128) - -3384) - 4504) - -1107); 
    	System.out.println("none");
    } if((cf && (input.equals("usr4_ai1_VoidReply")))) {
    	cf = false;
    	a2108127495 = (((((a2108127495 - 22339) - -35662) * 2) % 72) - 149); 
    	System.out.println("ai1_VoidReply");
    } 
}
private  void calculateOutputm8(String input) {
    if(((input.equals("usr4_ai1_VoidReply")) && cf)) {
    	cf = false;
    	a1745113960 = "e";
    	a2108127495 = ((((((((a2108127495 * a422009172) % 14999) / 5) % 72) + -156) * 5) % 72) + -65); 
    	System.out.println("ni1_ne1__ai1_VoidReply");
    } 
}



public  void calculateOutput(String input) {
 	cf = true;
    if((cf && a2108127495 <=  -164)) {
    	if((cf && ((-83 < a1522448132) && (18 >= a1522448132)))) {
    		calculateOutputm1(input);
    	} 
    } 
    if((cf && ((-164 < a2108127495) && (-19 >= a2108127495)))) {
    	if((cf && (a1745113960.equals("e")))) {
    		calculateOutputm2(input);
    	} 
    	if((cf && (a1745113960.equals("g")))) {
    		calculateOutputm3(input);
    	} 
    	if((cf && (a1745113960.equals("h")))) {
    		calculateOutputm4(input);
    	} 
    	if(((a1745113960.equals("i")) && cf)) {
    		calculateOutputm5(input);
    	} 
    } 
    if((((-19 < a2108127495) && (100 >= a2108127495)) && cf)) {
    	if((cf && (a1745113960.equals("g")))) {
    		calculateOutputm6(input);
    	} 
    	if((cf && (a1745113960.equals("h")))) {
    		calculateOutputm7(input);
    	} 
    } 
    if((cf && 100 < a2108127495)) {
    	if((((103 < a422009172) && (198 >= a422009172)) && cf)) {
    		calculateOutputm8(input);
    	} 
    } 

    if(cf)
    	throw new IllegalArgumentException("Current state has no transition for this input!");
}

public static void main(String[] args) throws Exception {
	// init system and input reader
	m183_LTL eca = new m183_LTL();

	// main i/o-loop
	while(true) {
		//read input
		String input = stdin.readLine();

		 if((input.equals("ai1_ce1")) && (input.equals("usr4_ai1_VoidReply")) && (input.equals("usr4_ni1_ne1")) && (input.equals("ai1_ce2")) && (input.equals("usr2_ai1_VoidReply")))
			throw new IllegalArgumentException("Current state has no transition for this input!");
		try {
			//operate eca engine output = 
			eca.calculateOutput(input);
		} catch(IllegalArgumentException e) {
			System.err.println("Invalid input: " + e.getMessage());
		}
	}
}
}