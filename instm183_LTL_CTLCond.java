import java.time.chrono.ThaiBuddhistEra;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class instm183_LTL_CTLDirect {
	static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	static LinkedHashSet<String> taints;

	public MyString[] inputs = {new MyString("ai1_ce1", true),new MyString("usr4_ai1_VoidReply", true),new MyString("usr4_ni1_ne1", true),new MyString("ai1_ce2", true),new MyString("usr2_ai1_VoidReply", true)};

	public MyInt a422009172 = I.myAssign(new MyInt(-68, "a422009172"), "a422009172");
	public MyBool cf = I.myAssign(new MyBool(true, "cf"));
	public MyInt a2108127495 = I.myAssign(new MyInt(-44, "a2108127495"), "a422009172");
	public MyInt a1522448132 = I.myAssign(new MyInt(173, "a1522448132"), "a422009172");
	public MyString a1745113960 = I.myAssign(new MyString("h"));

	private  void calculateOutputm1(MyString input) {
		I.myEquals(I.bool1,input,"usr2_ai1_VoidReply");
		I.myAnd(I.bool2,I.bool1,cf);

		if(I.myIf(I.bool2, input)) {
			cf = I.myAssign(new MyBool(false, "cf"));
			a1745113960 = I.myAssign(new MyString("h", true));
			//TODO
			I.myMul(I.var1, a2108127495,a1522448132);
			I.myMod(I.var2,I.var1,14999);
			I.myMod(I.var3,I.var2,72);
			I.myDel(I.var4,I.var3,91);
			I.myDel(I.var5,I.var4,-1);
			I.myMul(I.var6,I.var5,1);
			a2108127495 = I.myAssign(I.var6, "a2108127495");
			I.myPrint("ai1_VoidReply");
		}
	}

	private  void calculateOutputm2(MyString input) {
		
		I.myEquals(I.bool1,input,"usr4_ni1_ne1");
		I.myAnd(I.bool2,I.bool1,cf);

		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf", true));
			I.myPrint("ni1_ne1");
		}

		
		I.myEquals(I.bool1,input,"ai1_ce2");
		I.myAnd(I.bool2,I.bool1,cf);

		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf"));
			a1745113960 = I.myAssign(new MyString("h"));
			I.myMod(I.var1,a2108127495,59);
			I.myDel(I.var2,I.var1,-98);
			I.myDel(I.var3,I.var2,4);
			I.myAdd(I.var4,I.var3,-42);
			a2108127495 = I.myAssign(I.var4, "a2108127495");
			I.myPrint("usr4_ai1_ce2");
		}
	}

	private  void calculateOutputm3(MyString input) {
		
		I.myEquals( I.bool1,input,"usr4_ai1_VoidReply");
		I.myAnd( I.bool2,cf,I.bool1);

		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf"));
			I.myMul(I.var1, a2108127495, a2108127495);
			I.myMod(I.var2, I.var1, 50);
			I.myDel(I.var3, I.var2, 75);
			I.myMul(I.var4, I.var3, 5);
			I.myMod(I.var5, I.var4, 50);
			I.myAdd(I.var6, I.var5, -31);
			a1522448132 = I.myAssign(I.var6, "a1522448132");
			I.myMul(I.var1, a2108127495, a1522448132);
			I.myDel(I.var2, I.var1, 22476);
			I.myMul(I.var3, I.var2, 1);
			I.myAdd(I.var4, I.var3, -3649);
			a2108127495 = I.myAssign(I.var4, "a2108127495");
			I.myPrint("usr2_ai1_ce4");
		}
	}

	private  void calculateOutputm4(MyString input) {
		
		I.myEquals( I.bool1,input,"ai1_ce2");
		I.myAnd( I.bool2,cf,I.bool1);

		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf", true));
			I.myPrint("ai1_VoidReply");
		}
		

		I.myEquals( I.bool1,input,"ai1_ce1");
		I.myAnd( I.bool2,cf,I.bool1);

		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("g", true));
			I.myDel( I.var1,a2108127495,new MyInt(-7243));
			I.myDel( I.var2,I.var1,-1395);
			I.myMod( I.var3,I.var2,59);
			I.myDel( I.var4,I.var3,-16);
			a2108127495 = I.myAssign(I.var4, "a2108127495");
			I.myPrint("usr4_ai1_ce1");
		}
		

		I.myEquals( I.bool1,input,"usr4_ni1_ne1");
		I.myAnd( I.bool2,I.bool1,cf);

		 if(I.myIf(I.bool2, input)) {
		 		
				cf = I.myAssign(new MyBool(false, "cf",true));
				a1745113960 = I.myAssign(new MyString("i", true));
				I.myPrint("usr2_ai1_ce4");
		}
	}

	private  void calculateOutputm5(MyString input) {
		
		I.myEquals( I.bool1,input,"usr2_ai1_VoidReply");
		I.myAnd( I.bool2,I.bool1,cf);

		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("h", true));
			I.myPrint("none");
		}
	}

	private  void calculateOutputm6(MyString input) {
		
		I.myEquals( I.bool1,input,"usr4_ni1_ne1");
		I.myAnd( I.bool2,I.bool1,cf);
		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf", true));
			I.myAdd( I.var1,a2108127495,new MyInt(13863));
			I.myMul( I.var2,I.var1,2);
			I.myMul( I.var3,I.var2,1);
			a2108127495 = I.myAssign(I.var3, "a2108127495");
			I.myMul( I.var1,a2108127495,a2108127495);
			I.myMod( I.var2,I.var1,14999);
			I.myAdd( I.var3,I.var2,-1380);
			I.myMod( I.var4,I.var3,47);
			I.myDel( I.var5,I.var4,-150);
			I.myAdd( I.var6,I.var5,23887);
			I.myAdd( I.var7,I.var6,-23885);
			a422009172 = I.myAssign(I.var7, "a422009172");
			I.myPrint("none");
		}
		
		I.myEquals( I.bool1,input,"usr4_ai1_VoidReply");
		I.myAnd( I.bool2,cf,I.bool1);

		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("e", true));
			I.myAdd( I.var1,a2108127495,-139);
			I.myAdd( I.var2,I.var1,-4);
			I.myAdd( I.var3,I.var2,5);
			a2108127495 = I.myAssign(I.var3, "a2108127495");
			I.myPrint("ai1_VoidReply");
		}
	}

	private  void calculateOutputm7(MyString input) {
		
		I.myEquals( I.bool1,input,"usr4_ni1_ne1");
		I.myAnd( I.bool2,I.bool1,cf);
		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("g", true));
			I.myDel(I.var1,a2108127495,new MyInt(128));
			I.myDel(I.var2,I.var1,-3384);
			I.myDel(I.var3,I.var2,4504);
			I.myDel(I.var4,I.var3,-1107);
			a2108127495 = I.myAssign(I.var4, "a2108127495");
			I.myPrint("none");
		}

		I.myEquals( I.bool1,input,"usr4_ai1_VoidReply");
		I.myAnd( I.bool2,cf,I.bool1);

		if(I.myIf(I.bool2, input)) {
			
			cf = I.myAssign(new MyBool(false, "cf", true));

			I.myDel( I.var1,a2108127495,new MyInt(22339));
			I.myDel( I.var2,I.var1,-35662);
			I.myMul( I.var3,I.var2,2);
			I.myMod( I.var4,I.var3,72);
			I.myDel( I.var5,I.var4,149);
			a2108127495 = I.myAssign(I.var5, "a2108127495");
			I.myPrint("ai1_VoidReply");
		}
	}

	private  void calculateOutputm8(MyString input) {
		
		I.myEquals( I.bool1,input,"usr4_ai1_VoidReply");
		I.myAnd( I.bool2,I.bool1,cf);

		if(I.myIf(I.bool2, input)) {
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("e", true));
			I.myMul( I.var1,a2108127495,a422009172);
			I.myMod( I.var2,I.var1,14999);
			I.myDiv( I.var3,I.var2,5);
			I.myMod( I.var4,I.var3,72);
			I.myAdd( I.var5,I.var4,-156);
			I.myMul( I.var6,I.var5,5);
			I.myMod( I.var7,I.var6,72);
			I.myAdd( I.var8,I.var7,-65);
			a2108127495 = I.myAssign(I.var8, "a2108127495");
			I.myPrint("ni1_ne1__ai1_VoidReply");
		}
	}

	public  void calculateOutput(MyString input) {
		cf = new MyBool(true, "cf");
		
		I.myLessEqual(I.bool1, a2108127495, -164);
		I.myAnd(I.bool2, cf, I.bool1);

		if (I.myIf(I.bool2, input)) {
			I.myLess(I.bool1, -83, a1522448132);
			I.myGreaterEqual(I.bool2, 18, a1522448132);
			I.myAnd(I.bool3, I.bool1, I.bool2);
			I.myAnd(I.bool4, cf, I.bool3);
			if (I.myIf(I.bool4, input)) {

				calculateOutputm1(input);
			}
		}

		I.myLess(I.bool1, -164, a2108127495);
		I.myGreaterEqual(I.bool2, -19, a2108127495);
		I.myAnd(I.bool3, I.bool1, I.bool2);
		I.myAnd(I.bool4, cf, I.bool3);

		if (I.myIf(I.bool4, input)) {
			
			I.myEquals(I.bool1, a1745113960, "e");
			I.myAnd(I.bool2, cf, I.bool1);

			if (I.myIf(I.bool2, input))
				calculateOutputm2(input);
			
			I.myEquals(I.bool1, a1745113960, "g");
			I.myAnd(I.bool2, cf, I.bool1);

			if (I.myIf(I.bool2, input))
				calculateOutputm3(input);
			
			I.myEquals(I.bool1, a1745113960, "h");
			I.myAnd(I.bool2, cf, I.bool1);

			if (I.myIf(I.bool2, input))
				calculateOutputm4(input);

			I.myEquals(I.bool1, a1745113960, "i");
			I.myAnd(I.bool2, I.bool1, cf);

			if (I.myIf(I.bool2, input))
				calculateOutputm5(input);
		}

		I.myLess(I.bool1, -19, a2108127495);
		I.myGreaterEqual(I.bool2, 100, a2108127495);
		I.myAnd(I.bool3, I.bool1, I.bool2);
		I.myAnd(I.bool4, I.bool3, cf);

		if (I.myIf(I.bool4, input)) {
			
			I.myEquals(I.bool1, a1745113960, "g");
			I.myAnd(I.bool2, cf, I.bool1);

			if (I.myIf(I.bool2, input))
				calculateOutputm6(input);
			
			I.myEquals(I.bool1, a1745113960, "h");
			I.myAnd(I.bool2, cf, I.bool1);
			if (I.myIf(I.bool2, input)) {
				calculateOutputm7(input);
			}
			

			I.myLess(I.bool1, 100, a2108127495);
			I.myAnd(I.bool2, cf, I.bool1);
			if (I.myIf(I.bool2, input)) {
				
				I.myLess(I.bool1, 103, a422009172);
				I.myGreaterEqual(I.bool2, 198, a422009172);
				I.myAnd(I.bool3, I.bool1, I.bool2);
				I.myAnd(I.bool4, I.bool3, cf);
				if (I.myIf(I.bool4, input))
					calculateOutputm8(input);
			}
			

			if (I.myIf(cf, input)) {
				throw new IllegalArgumentException("Current state has no transition for this input!");
			}
		}
	}

	public void reset() {
		this.taints = new LinkedHashSet<>();
		MyVar var = new MyVar(this.taints);

		System.out.println("reset");a422009172 = new MyInt(-68, "a422009172");
		cf = new MyBool(true, "cf");
		a2108127495 = new MyInt(-44, "a2108127495");
		a1522448132 = new MyInt(173, "a1522448132");
		a1745113960 = new MyString("h");
	}

	private static void printFlowLength(MyString input[]) {
		System.out.println("=============TASK 1================");

		int maxDepth = -1;
		for (MyString str : input) {
			if (str.depth > maxDepth)
				maxDepth = str.depth;
		}

		System.out.println("For input array of length: " + input.length);
		System.out.println("And elements: ");
		for (int i = 0; i < input.length; i++) {
			System.out.println(" " + i + ". " +input[i]);
		}
		System.out.println("Max depth is: " + maxDepth);
	}

	public static void main (String[] args) {
		//T2
		String alphabet ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		instm183_LTL_CTLDirect eca = new instm183_LTL_CTLDirect();
		//T1
		int runIndex = 0;

		while(runIndex < 10) {
			eca.reset();
			MyString[] fuzzed_inputs = Fuzzer.fuzz(eca.inputs);

			System.out.println("This is run number: " + runIndex);
			for(int i = 0; i < fuzzed_inputs.length; i++) {
				MyString input = fuzzed_inputs[i];

				System.out.println("Fuzzing: " + input.val);

				I.myEquals( I.bool1, input,"ai1_ce1");
				I.myEquals( I.bool2,input,"usr4_ai1_VoidReply");
				I.myEquals( I.bool3,input,"usr4_ni1_ne1");
				I.myEquals( I.bool4,input,"ai1_ce2");
				I.myEquals( I.bool5,input,"usr2_ai1_VoidReply");
				I.myAnd( I.bool6,I.bool1,I.bool2);
				I.myAnd( I.bool7,I.bool3,I.bool4);
				I.myAnd( I.bool8,I.bool6,I.bool7);
				I.myAnd( I.bool9,I.bool8,I.bool5);
				if(I.myIf(I.bool9, input))
					throw new IllegalArgumentException("Current state has no transition for this input!");
				try {
					eca.calculateOutput(input);
					System.out.println();
					System.out.println();
					//No idea what this does...
					//String arr[] = (String[])eca.a422009172.getFlow();
				}
				catch(IllegalArgumentException e) {
					System.err.println("Invalid input: " + e.getMessage());
				}
			}
			//T1: print the deepest the input variable has reached.
			printFlowLength(fuzzed_inputs);

			runIndex++;
		}
	}
}

class Fuzzer {
	public static MyString[] fuzz(MyString[] inputs){
		Random rand = new Random();
		int length = rand.nextInt(50) + 10;
		MyString[] result = new MyString[length];

		for(int i = 0; i < length; i++){
			int index = rand.nextInt(inputs.length);
			result[i] = new MyString(inputs[index].val, "input number: " + i, true, true);
		}
		return result;
	}
}

class MyVar {
	public boolean inputVar = false;
	public static LinkedHashSet<String> taintSet;
	public String varName;

	public MyVar(LinkedHashSet<String> taintSet) {this.taintSet = taintSet;}

	public MyVar(String name, boolean inputVar) {this.varName = name; this.inputVar=inputVar;}

	public MyVar(String name, boolean inputVar, LinkedHashSet<String> taintSet, boolean tainted) throws Exception {
		this.inputVar = inputVar;
		this.taintSet = taintSet;
	}

	public void addTaintedVar(String name) {
		if(this.taintSet!= null) this.taintSet.add(name);
	}

	public Object[] getFlow() {
		Object[] arr = this.taintSet.toArray();
		return Arrays.copyOf(arr, arr.length, String[].class);
	}
}

class MyInt extends MyVar{
	public int val = 0;

	//one with only val for compatibility reasons.
	public MyInt(int val) {
		super("", false);
		this.val = val;
	}

	public MyInt(int val, String name) {
		super(name, false);
		this.val=val;
	}

	public MyInt(int val, String name, boolean tainted) {
		super(name, false);
		this.val=val;
		if (tainted) this.taintSet.add(name);
	}

	public MyInt(int val, String varName, HashSet<String> flowSet){
		super(varName, false);
		this.val = val;
		this.varName=varName;
	}

	public MyInt(int val, String varName, boolean br, HashSet<String> flowSet){
		super(varName, false);
		this.val = val;
		this.varName=varName;
		if (br) taintSet.add(varName);
	}

	public MyInt(int val, String varName, boolean br, boolean input, HashSet<String> flowSet){
		super(varName, input);
		this.val = val;
		this.varName=varName;
		if (br) taintSet.add(varName);
	}
}

class MyBool extends MyVar {
	public boolean val = false;
	public String varName;

	//Compatibility with the teacher provided code.
	public MyBool(boolean val) {super("", false); this.val = val;}
	public MyBool(boolean val, String varName){
		super(varName, false);
		this.val = val;
		this.varName=varName;
	}

	public MyBool(boolean val, String varName, boolean br){
		super(varName, false);
		this.val = this.val;
		this.varName = varName;
		if (br) this.taintSet.add(varName);
	}

	public MyBool(boolean val, String varName, boolean br, boolean inputVar){
		super(varName, inputVar);
		this.val = this.val;
		this.varName = varName;
		if (br) this.taintSet.add(varName);
	}
}

class MyString extends MyVar{
	public String val = "";
	public int depth = 0;

	public MyString(String val) {
		super("", false);
		this.val = val;
	}

	public MyString(String val, boolean branch) {
		super("", false);
		if (branch) this.taintSet.add(val);
	}

	public MyString(String val, String varName, boolean branch, boolean input){
		super(varName, input);
		this.val=val;
		if (branch)
			this.taintSet.add(varName);
	}
}

class I {
	public static MyInt var1 = new MyInt(0);
	public static MyInt var2 = new MyInt(0);
	public static MyInt var3 = new MyInt(0);
	public static MyInt var4 = new MyInt(0);
	public static MyInt var5 = new MyInt(0);
	public static MyInt var6 = new MyInt(0);
	public static MyInt var7 = new MyInt(0);
	public static MyInt var8 = new MyInt(0);
	public static MyInt var9 = new MyInt(0);
	public static MyInt var10 = new MyInt(0);
	public static MyInt var11 = new MyInt(0);
	public static MyInt var12 = new MyInt(0);
	public static MyInt var13 = new MyInt(0);
	public static MyInt var14 = new MyInt(0);
	public static MyInt var15 = new MyInt(0);
	public static MyInt var16 = new MyInt(0);
	public static MyInt var17 = new MyInt(0);
	public static MyInt var18 = new MyInt(0);
	public static MyInt var19 = new MyInt(0);
	public static MyInt var20 = new MyInt(0);
	public static MyInt var21 = new MyInt(0);
	public static MyInt var22 = new MyInt(0);
	public static MyInt var23 = new MyInt(0);
	public static MyInt var24 = new MyInt(0);
	public static MyInt var25 = new MyInt(0);
	public static MyInt var26 = new MyInt(0);
	public static MyInt var27 = new MyInt(0);
	public static MyInt var28 = new MyInt(0);
	public static MyInt var29 = new MyInt(0);
	public static MyInt var30 = new MyInt(0);
	public static MyInt var31 = new MyInt(0);
	public static MyInt var32 = new MyInt(0);
	public static MyInt var33 = new MyInt(0);
	public static MyInt var34 = new MyInt(0);
	public static MyInt var35 = new MyInt(0);
	public static MyInt var36 = new MyInt(0);
	public static MyInt var37 = new MyInt(0);
	public static MyInt var38 = new MyInt(0);
	public static MyInt var39 = new MyInt(0);
	public static MyInt var40 = new MyInt(0);
	public static MyInt var41 = new MyInt(0);
	public static MyInt var42 = new MyInt(0);
	public static MyInt var43 = new MyInt(0);
	public static MyInt var44 = new MyInt(0);
	public static MyInt var45 = new MyInt(0);
	public static MyInt var46 = new MyInt(0);
	public static MyInt var47 = new MyInt(0);
	public static MyInt var48 = new MyInt(0);
	public static MyInt var49 = new MyInt(0);

	public static MyBool bool1 = new MyBool(false);
	public static MyBool bool2 = new MyBool(false);
	public static MyBool bool3 = new MyBool(false);
	public static MyBool bool4 = new MyBool(false);
	public static MyBool bool5 = new MyBool(false);
	public static MyBool bool6 = new MyBool(false);
	public static MyBool bool7 = new MyBool(false);
	public static MyBool bool8 = new MyBool(false);
	public static MyBool bool9 = new MyBool(false);
	public static MyBool bool10 = new MyBool(false);
	public static MyBool bool11 = new MyBool(false);
	public static MyBool bool12 = new MyBool(false);
	public static MyBool bool13 = new MyBool(false);
	public static MyBool bool14 = new MyBool(false);
	public static MyBool bool15 = new MyBool(false);
	public static MyBool bool16 = new MyBool(false);
	public static MyBool bool17 = new MyBool(false);
	public static MyBool bool18 = new MyBool(false);
	public static MyBool bool19 = new MyBool(false);
	public static MyBool bool20 = new MyBool(false);
	public static MyBool bool21 = new MyBool(false);
	public static MyBool bool22 = new MyBool(false);
	public static MyBool bool23 = new MyBool(false);
	public static MyBool bool24 = new MyBool(false);
	public static MyBool bool25 = new MyBool(false);
	public static MyBool bool26 = new MyBool(false);
	public static MyBool bool27 = new MyBool(false);
	public static MyBool bool28 = new MyBool(false);
	public static MyBool bool29 = new MyBool(false);
	public static MyBool bool30 = new MyBool(false);
	public static MyBool bool31 = new MyBool(false);
	public static MyBool bool32 = new MyBool(false);
	public static MyBool bool33 = new MyBool(false);
	public static MyBool bool34 = new MyBool(false);
	public static MyBool bool35 = new MyBool(false);
	public static MyBool bool36 = new MyBool(false);
	public static MyBool bool37 = new MyBool(false);
	public static MyBool bool38 = new MyBool(false);
	public static MyBool bool39 = new MyBool(false);
	public static MyBool bool40 = new MyBool(false);
	public static MyBool bool41 = new MyBool(false);
	public static MyBool bool42 = new MyBool(false);
	public static MyBool bool43 = new MyBool(false);
	public static MyBool bool44 = new MyBool(false);
	public static MyBool bool45 = new MyBool(false);
	public static MyBool bool46 = new MyBool(false);
	public static MyBool bool47 = new MyBool(false);
	public static MyBool bool48 = new MyBool(false);
	public static MyBool bool49 = new MyBool(false);
	public static MyString str1 = new MyString("");
	public static MyString str2 = new MyString("");
	public static MyString str3 = new MyString("");
	public static MyString str4 = new MyString("");
	public static MyString str5 = new MyString("");
	public static MyString str6 = new MyString("");
	public static MyString str7 = new MyString("");
	public static MyString str8 = new MyString("");
	public static MyString str9 = new MyString("");
	public static MyString str10 = new MyString("");
	public static MyString str11 = new MyString("");
	public static MyString str12 = new MyString("");
	public static MyString str13 = new MyString("");
	public static MyString str14 = new MyString("");
	public static MyString str15 = new MyString("");
	public static MyString str16 = new MyString("");
	public static MyString str17 = new MyString("");
	public static MyString str18 = new MyString("");
	public static MyString str19 = new MyString("");
	public static MyString str20 = new MyString("");
	public static MyString str21 = new MyString("");
	public static MyString str22 = new MyString("");
	public static MyString str23 = new MyString("");
	public static MyString str24 = new MyString("");
	public static MyString str25 = new MyString("");
	public static MyString str26 = new MyString("");
	public static MyString str27 = new MyString("");
	public static MyString str28 = new MyString("");
	public static MyString str29 = new MyString("");
	public static MyString str30 = new MyString("");
	public static MyString str31 = new MyString("");
	public static MyString str32 = new MyString("");
	public static MyString str33 = new MyString("");
	public static MyString str34 = new MyString("");
	public static MyString str35 = new MyString("");
	public static MyString str36 = new MyString("");
	public static MyString str37 = new MyString("");
	public static MyString str38 = new MyString("");
	public static MyString str39 = new MyString("");
	public static MyString str40 = new MyString("");
	public static MyString str41 = new MyString("");
	public static MyString str42 = new MyString("");
	public static MyString str43 = new MyString("");
	public static MyString str44 = new MyString("");
	public static MyString str45 = new MyString("");
	public static MyString str46 = new MyString("");
	public static MyString str47 = new MyString("");
	public static MyString str48 = new MyString("");
	public static MyString str49 = new MyString("");

	//T1
	public static void myAdd(MyInt a, MyInt b, MyInt c){
		a.val = b.val+c.val;
		if (MyInt.taintSet.contains(b.varName) || MyInt.taintSet.contains(c.varName))
			a.addTaintedVar(a.varName);
	}

	public static void myAdd(MyInt a, MyInt b, MyInt c, boolean bo){
		a.val = b.val+c.val;
		if(bo) a.addTaintedVar(a.varName);}

	public static void myDel(MyInt a, MyInt b, MyInt c, boolean bo){
		a.val = b.val-c.val;
		if (bo) a.addTaintedVar(a.varName);}

	public static void myDel(MyInt a, MyInt b, MyInt c){
		a.val = b.val-c.val;
		if (b.taintSet.contains(b.varName) || b.taintSet.contains(b.varName))
			a.addTaintedVar(a.varName);}

	public static void myMul(MyInt a, MyInt b, MyInt c){
		a.val = b.val*c.val;
		if (b.taintSet.contains(b.varName) || c.taintSet.contains(c.varName))
			a.addTaintedVar(a.varName);}

	public static void myDiv(MyInt a, MyInt b, MyInt c, boolean bo){
		a.val = b.val/c.val;
		if (bo) a.addTaintedVar(a.varName);}

	//T1
	public static void myDiv(MyInt a, MyInt b, MyInt c){
		a.val = b.val/c.val;
		if (b.taintSet.contains(b.varName) || c.taintSet.contains(c.varName))
			a.addTaintedVar(a.varName);}

	public static void myMod(MyInt a, MyInt b, MyInt c, boolean bo){ a.val = b.val%c.val;  }

	public static void myInd(MyInt a, MyInt[] b, MyInt c, boolean bo){ a.val = b[c.val].val; }
	public static void myInd(MyInt a, MyInt[] b, MyInt c){ a.val = b[c.val].val; }

	public static void myInd(MyString a, MyString[] b, MyInt c, boolean bo){ a.val = b[c.val].val; }
	public static void myInd(MyString a, MyString[] b, MyInt c){ a.val = b[c.val].val; }

	public static void myEquals(MyBool a, MyBool b, MyBool c){ a.val = (b.val == c.val); }
	public static void myEquals(MyBool a, MyInt b, MyInt c){ a.val = (b.val == c.val); }

	public static void myEquals(MyBool a, MyString b, MyString c) {
		a.val = (b.val.equals(c.val));
	}

	public static void myLess(MyBool a, MyInt b, MyInt c){ a.val = (b.val < c.val); }

	public static void myGreater(MyBool a, MyInt b, MyInt c){ a.val = (b.val > c.val); }

	public static void myLessEqual(MyBool a, MyInt b, MyInt c){ a.val = (b.val <= c.val); }

	public static void myGreaterEqual(MyBool a, MyInt b, MyInt c){ a.val = (b.val >= c.val); }

	public static MyBool myAssign(MyBool b){
		MyBool a = new MyBool(b.val, b.varName, b.taintSet.contains(b.varName));
		return a;
	}

	public static MyInt myAssign(MyInt b, String name) {
		//modify with flow var.
		MyInt ret = new MyInt(b.val, name);
		if (b.taintSet.contains(b.varName))
			ret.taintSet.add(name);
		return ret;
	}

	public static MyInt myAssign(MyInt a) {
		return new MyInt(a.val);
	}
	public static MyInt[] myAssign(MyInt[] b){
		MyInt a[] = new MyInt[b.length];
			for(int i = 0; i < b.length; i++)
				a[i] = new MyInt(b[i].val, b[i].varName, b[i].taintSet.contains(b[i].varName));
			return a;
	}
	public static MyString myAssign(MyString b){
		MyString a = new MyString(b.val, b.taintSet.contains(b.val));
		return a;
	}
	public static void myAnd(MyBool a, MyBool b, MyBool c){ a.val = (b.val && c.val); }

	public static void myOr(MyBool a, MyBool b, MyBool c){ a.val = (b.val || c.val); }
	public static void myNot(MyBool a, MyBool b){ a.val = (!b.val); }
	public static void myPrint(MyString a){ System.out.println("\n"+a.val); }
	public static void myPrint(String a) {
		myPrint(new MyString(a));
	}

	//T2
	public static boolean myIf(MyBool a, MyString input){
		System.out.print("b " + a.val + " ");
		
		if(a.val)
			input.depth++;
		return a.val; }

	public static void myAdd(MyInt a, MyInt b, int c){ myAdd(a,b,new MyInt(c)); }

	public static void myAdd(MyInt a, int b, MyInt c){ myAdd(a,new MyInt(b),c); }
	public static void myAdd(MyInt a, int b, int c){ myAdd(a,new MyInt(b),new MyInt(c)); }

	public static void myDel(MyInt a, MyInt b, int c){ myDel(a,b,new MyInt(c)); }

	public static void myDel(MyInt a, int b, MyInt c){ myDel(a,new MyInt(b),c); }
	public static void myDel(MyInt a, int b, int c){ myDel(a,new MyInt(b),new MyInt(c)); }
	public static void myMul(MyInt a, MyInt b, int c){ myMul(a,b,new MyInt(c)); }

	public static void myMul(MyInt a, int b, MyInt c){ myMul(a,new MyInt(b),c); }
	public static void myMul(MyInt a, int b, int c){ myMul(a,new MyInt(b),new MyInt(c)); }

	public static void myDiv(MyInt a, MyInt b, int c){ myDiv(a,b,new MyInt(c)); }
	
	public static void myDiv(MyInt a, int b, MyInt c){ myDiv(a,new MyInt(b),c); }
	public static void myDiv(MyInt a, int b, int c){ myDiv(a,new MyInt(b),new MyInt(c)); }

	public static void myMod(MyInt a, MyInt b, MyInt c) {a.val = b.val % c.val;}
	public static void myMod(MyInt a, MyInt b, int c){ myMod(a,b,new MyInt(c)); }
	public static void myMod(MyInt a, int b, MyInt c){ myMod(a,new MyInt(b),c); }
	public static void myMod(MyInt a, int b, int c){ myMod(a,new MyInt(b),new MyInt(c)); }

	public static void myInd(MyInt a, MyInt[] b, int c){ myInd(a,b,new MyInt(c)); }
	public static void myInd(MyInt a, int[] b, int c){ myInd(a,I.myAssign(b),new MyInt(c)); }
	public static void myInd(MyString a, MyString[] b, int c){ myInd(a,b,new MyInt(c)); }
	public static void myEquals(MyBool a, MyBool b, boolean c){ myEquals(a, b, new MyBool(c)); }
	public static void myEquals(MyBool a, MyInt b, int c){ myEquals(a, b, new MyInt(c)); }

	public static void myEquals(MyBool a, MyString b, String c) {myEquals(a, b, new MyString(c));}

	public static void myEquals(MyBool a, boolean b, MyBool c){ myEquals(a, new MyBool(b), c); }
	public static void myEquals(MyBool a, int b, MyInt c){ myEquals(a, new MyInt(b), c); }

	public static void myEquals(MyBool a, String b, MyString c) { myEquals(a, new MyString(b), c); }

	public static void myEquals(MyBool a, boolean b, boolean c){ myEquals(a, new MyBool(b), new MyBool(c)); }
	public static void myEquals(MyBool a, int b, int c){ myEquals(a, new MyInt(b), new MyInt(c)); }

	public static void myEquals(MyBool a, String b, String c){
		myEquals(a, new MyString(b), new MyString(c));	}

	public static void myLess(MyBool a, MyInt b, int c){ myLess(a, b, new MyInt(c)); }
	public static void myGreater(MyBool a, MyInt b, int c){ myGreater(a, b, new MyInt(c)); }

	public static void myLessEqual(MyBool a, MyInt b, int c){ myLessEqual(a, b, new MyInt(c)); }

	public static void myGreaterEqual(MyBool a, MyInt b, int c){ myGreaterEqual(a, b, new MyInt(c)); }

	public static void myLess(MyBool a, int b, MyInt c){ myLess(a, new MyInt(b), c); }

	public static void myGreater(MyBool a, int b, MyInt c){ myGreater(a, new MyInt(b), c); }
	public static void myLessEqual(MyBool a, int b, MyInt c){ myLessEqual(a, new MyInt(b), c); }
	public static void myGreaterEqual(MyBool a, int b, MyInt c){ myGreaterEqual(a, new MyInt(b), c); }

	public static void myLess(MyBool a, int b, int c){ myLess(a, new MyInt(b), new MyInt(c)); }
	public static void myGreater(MyBool a, int b, int c){ myGreater(a, new MyInt(b), new MyInt(c)); }
	public static void myLessEqual(MyBool a, int b, int c){ myLessEqual(a, new MyInt(b), new MyInt(c)); }
	public static void myGreaterEqual(MyBool a, int b, int c){ myGreaterEqual(a, new MyInt(b), new MyInt(c)); }
	public static MyBool myAssign(boolean b){ return myAssign(new MyBool(b)); }
	public static MyInt myAssign(int b){ return myAssign(new MyInt(b)); }
	public static MyInt[] myAssign(int[] b){ MyInt a[] = new MyInt[b.length]; for(int i = 0; i < b.length; i++) a[i] = new MyInt(b[i]); return myAssign(a); }
	public static MyString myAssign(String b){ return myAssign(new MyString(b)); }
}
