import javafx.util.Pair;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.sql.Array;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//ToDo finish branch distance algo.

enum TypeEnum {
	myEquals,
	myAnd,
	myLessEqual,
	myGreaterEqual,
	myLess,
}

class Condition {
	public TypeEnum bTypeEnum;
	//Result
	public MyBool boolOpA;

	public MyString strOpB;
	public MyBool boolOpB;
	public MyInt intOpB;

	public MyString strOpC;
	public MyBool boolOpC;
	public MyInt intOpC;
}

class Branch{
	//Only for boolean checks.
	public MyBool dependentVariable;
	public List<Condition> condLst = new ArrayList<>();

	public String input=null;

	public Branch(String input) {this.input=input;}
}

class Calc {
	static MyString[] inputs;
	private static HashSet<Branch> branches = null;
	public Calc(MyString[] inputs, HashSet branches) {
		this.inputs=inputs; this.branches=branches;}

	private static boolean dependsOnInput(String input) {
		for (MyString word : inputs) {
			if (input.equals(word.val)) return true;
		}
		return false;
	}

	//How far the current input is from triggering the next branch
	public static HashMap<Branch, Integer> calculateDistance(String trace) {
		Branch br = null;
		//How many branches deep.
		int counter;

		HashMap<Branch, Integer> map = new HashMap<>();

		MyBool result = new MyBool(false);
		boolean depsOnInput = false;

		//Get next reachable branch.
		for (Branch b : branches) {
			counter = 0;
			if (b.input.equals(trace)) {
				br = b;
				//Increment +1, counting 0, from the main
				//branch.
				++counter;
			}
			else continue;

			if (br == null) break;

			for (Condition cond : br.condLst) {
				switch (cond.bTypeEnum) {
					case myEquals:
						//If it doesn't depend on input, then
						//the condition depends on some other variable.
						//Therefore, ++counter.
						if (!dependsOnInput(br.input)) {
							++counter;
							continue;
						}
						//The result of the computation

						if(cond.strOpB == null) {
							System.out.println("myEqual strOpB null");
							break;
						}
						if(cond.strOpC == null) {
							System.out.println("myEqual strOpC null");
							break;
						}

						/*
						 Variables placed in the order that are expected/normally passed in,
						 during the execution of the code. The third variable, is always used
						 in order to check whether trace, equals some pre-determined variable.
						 */
						I.myEquals(result, trace, cond.strOpC);
						//If result equals stored branch result, then we need to
						//increment counter, and move on.
						if (result.equals(cond.boolOpA)) {
							counter++;
							continue;
						} else break;
					case myAnd:
						//and-clauses, never directly depend on input.
						I.myEquals(result, cond.boolOpB, cond.boolOpC);
						if (result.equals(cond.boolOpA)) {
							counter++;
							continue;
						}
					case myLessEqual:
						if(cond.intOpB == null) {
							System.out.println("myLessEqual intOpB null");
							break;
						}
						if(cond.intOpC == null) {
							System.out.println("myLessEqual intOpC null");
							break;
						}
						I.myLessEqual(result, cond.intOpB, cond.intOpC);
						if (result.equals(cond.boolOpA)) {
							counter++;
							continue;
						}
					case myGreaterEqual:
						I.myGreaterEqual(result, cond.intOpB, cond.intOpC);
						if (result.equals(cond.boolOpA)) {
							counter++;
							continue;
						}
					case myLess:
						if(cond.intOpB == null) {
							System.out.println("myLess intOpB null");
							break;
						}
						if(cond.intOpC == null) {
							System.out.println("myLess intOpC null");
							break;
						}
						I.myLess(result, cond.intOpB, cond.intOpC);
						if (result.equals(cond.boolOpA)) {
							counter++;
							continue;
						}
				}
				map.put(b, counter);
			}
		}
		return map;
	}
}

class Triplet<K, V, T> {
	public K k;
	public V v;
	public T t;

	public Triplet(K k, V v, T t){this.k = k; this.v = v; this.t = t;}
}



//From every run, get 5 ArrayList<K:V, where K:MyString[], V:MaxNummerOfExceptions.
//Store that somewhere in Main.
//Pass that on another class, to calculate potential positions for maximum reachability,
//by getting the weighted average, of every MyString elem index in the array.
class Run {
	public List<Exception> exceptions = new ArrayList();
	public MyString[] inputs;
	public HashSet<Branch> branches = null;

	public Run(MyString[] inputs) {this.inputs = inputs;}

	//Gets top traces with largest depth
	public static List<Triplet<MyString[], Integer, List<Exception>>> getTop100DepthTraces(List<Run> runsLst) {
		//Get all input traces, their max branch depth(that would be the maximum number of conditions reached per
		//branch), and a list of all their triggered exceptions.
		List<Triplet<MyString[], Integer, List<Exception>>> tripletLst = new ArrayList<>();
		for (Run run : runsLst) {
			int max = 0;
			Branch temp = null;
			for (Branch br : run.branches) {
				temp = br;
				int size = br.condLst.size();
				if (size > max) {
					max=size;
					temp = br;
				}
			}
			tripletLst.add(new Triplet(run.inputs, max, run.exceptions));
		}

		//Sort the list based on number of branches.
		Collections.sort(tripletLst, new Comparator<Triplet<MyString[], Integer, List<Exception>>>() {
			@Override
			public int compare(Triplet<MyString[], Integer, List<Exception>> t1, Triplet<MyString[], Integer, List<Exception>> t2) {
				if (t1.t.size() > t2.t.size()) return -1;
				if (t1.t.size() < t2.t.size()) return 1;
				return 0;
			}
		});

		//sublist
		tripletLst = new ArrayList<>(tripletLst.subList(tripletLst.size()-101, tripletLst.size()-1));
		//sublist and reverse.
		Collections.reverse(tripletLst);
		return tripletLst;
	}

	public static List<MyString[]> getSuggestedTrace(List<Triplet<MyString[], Integer, List<Exception>>> inputLst) {
		MyString[] ar = inputLst.get(0).k;
		//The MyString, is an element, of the trace, and List, will be a set of integers, indicating
		//the number of times, the MyString element, has occurred, within each index of the given
		//trace.
		HashMap<MyString, ArrayList<Integer>> map = new HashMap<>();

		for (MyString str : ar) {
			ArrayList<Integer> ints = new ArrayList<>();
			//Initialize
			for (int i = 0; i < ar.length; i++) ints.add(0);

			map.put(str, ints);
		}

		/*
		for each triplet
		  get it's array
		  for each element in the array, get it's index
		  in the map, increment by +1 the integer, in the ArrayList<Index>
		 */
		for (Triplet<MyString[] ,Integer, List<Exception>> triplet : inputLst) {
			MyString[] ar1 = triplet.k;
			int index=0;
			for (MyString str1 : ar1) {
				//Get the index of the array element.
				for (int i = 0; i < ar.length; i++) {
					if (ar[i].equals(str1)) {
						index=i;
						break;
					}
					i++;
				}

				//Get list
				ArrayList<Integer> tmp = map.get(str1);
				//Increment
				int nummer = tmp.get(index).intValue();
				nummer++;
				//reset
				tmp.set(index, nummer);
				//replace in map.
				map.replace(str1, tmp);
			}
		}

		/*
		  For each element in the map, get it's list.
		    build an array of it's most likely positions, biggest to smallest int[]
		    and replace that with in the HashSet.
		  Instantiate a new array[inputs.length];

		 create an output array/

		 for the each element, of the hashset, get the first element of the ints array, and find the greatest one.
		 get the key of the largest int, and place it in the new output array. Return the array.
		 */
		int[] ints = new int[map.keySet().size()];
		int index=0;

		for (MyString string : map.keySet()) {
			ArrayList<Integer> lst = map.get(string);
			ints[index] = lst.get(0);
			

			index++;
		}





		return null;
	}
 }

public class instm183_LTL_CTLFuzzer {
	static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	public static ArrayList<Run> runLst = new ArrayList<>();
	//String: input
	public static HashSet<Branch> branches;

	public MyString[] inputs = {new MyString("ai1_ce1", true),new MyString("usr4_ai1_VoidReply", true),new MyString("usr4_ni1_ne1", true),new MyString("ai1_ce2", true),new MyString("usr2_ai1_VoidReply", true)};

	public MyInt a422009172 = I.myAssign(new MyInt(-68, "a422009172"));
	public MyBool cf = I.myAssign(new MyBool(true, "cf"));
	public MyInt a2108127495 = I.myAssign(new MyInt(-44, "a2108127495"));
	public MyInt a1522448132 = I.myAssign(new MyInt(173, "a1522448132"));
	public MyString a1745113960 = I.myAssign(new MyString("h"));

	private  void CalculateOutputm1(MyString input) {

		Branch br1 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr2_ai1_VoidReply", this, br1);
		I.myAnd( I.bool2,I.bool1,cf, br1);

		br1.dependentVariable=I.bool2;
		this.branches.add(br1);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf"));
			a1745113960 = I.myAssign(new MyString("h", true));
			I.myMul( I.var1, a2108127495,a1522448132, true);
			I.myMod( I.var2,I.var1,14999);
			I.myMod( I.var3,I.var2,72);
			I.myDel( I.var4,I.var3,91);
			I.myDel( I.var5,I.var4,-1);
			I.myMul( I.var6,I.var5,1);
			a2108127495 = I.myAssign(I.var6);
			I.myPrint("ai1_VoidReply");
		}
	}

	private  void CalculateOutputm2(MyString input) {
		Branch br1 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr4_ni1_ne1", this, br1);
		I.myAnd( I.bool2,I.bool1,cf, br1);

		br1.dependentVariable=I.bool2;
		this.branches.add(br1);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf", true));
			I.myPrint("ni1_ne1");
		}

		Branch br2 = new Branch(input.val);
		I.myEquals( I.bool1,input,"ai1_ce2", this, br2);
		I.myAnd( I.bool2,I.bool1,cf, br2);

		br2.dependentVariable=I.bool2;
		this.branches.add(br2);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf"));
			a1745113960 = I.myAssign(new MyString("h"));
			I.myMod( I.var1,a2108127495,59);
			I.myDel( I.var2,I.var1,-98);
			I.myDel( I.var3,I.var2,4);
			I.myAdd( I.var4,I.var3,-42);
			a2108127495 = I.myAssign(I.var4);
			I.myPrint("usr4_ai1_ce2");
		}
	}

	private  void CalculateOutputm3(MyString input) {
		Branch br1 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr4_ai1_VoidReply", this, br1);
		I.myAnd( I.bool2,cf,I.bool1, br1);

		br1.dependentVariable=I.bool2;
		this.branches.add(br1);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf"));
			I.myMul(I.var1, a2108127495, a2108127495, true);
			I.myMod(I.var2, I.var1, 50);
			I.myDel(I.var3, I.var2, 75);
			I.myMul(I.var4, I.var3, 5);
			I.myMod(I.var5, I.var4, 50);
			I.myAdd(I.var6, I.var5, -31);
			a1522448132 = I.myAssign(I.var6);
			I.myMul(I.var1, a2108127495, a1522448132);
			I.myDel(I.var2, I.var1, 22476);
			I.myMul(I.var3, I.var2, 1);
			I.myAdd(I.var4, I.var3, -3649);
			a2108127495 = I.myAssign(I.var4);
			I.myPrint("usr2_ai1_ce4");
		}
	}

	private  void CalculateOutputm4(MyString input) {
		Branch br1 = new Branch(input.val);
		I.myEquals( I.bool1,input,"ai1_ce2", this, br1);
		I.myAnd( I.bool2,cf,I.bool1, br1);

		br1.dependentVariable=I.bool2;
		this.branches.add(br1);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf", true));

			I.myPrint("ai1_VoidReply");
		}

		Branch br2 = new Branch(input.val);
		I.myEquals( I.bool1,input,"ai1_ce1", this, br2);
		I.myAnd( I.bool2,cf,I.bool1, br2);

		br2.dependentVariable=I.bool2;
		this.branches.add(br2);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("g", true));
			I.myDel( I.var1,a2108127495,new MyInt(-7243),true);
			I.myDel( I.var2,I.var1,-1395);
			I.myMod( I.var3,I.var2,59);
			I.myDel( I.var4,I.var3,-16);
			a2108127495 = I.myAssign(I.var4);
			I.myPrint("usr4_ai1_ce1");
		}

		Branch br3 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr4_ni1_ne1", this, br3);
		I.myAnd( I.bool2,I.bool1,cf, br3);
		br3.dependentVariable=I.bool2;
		this.branches.add(br3);

		 if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf",true));
			a1745113960 = I.myAssign(new MyString("i", true));
			I.myPrint("usr2_ai1_ce4");
		 }
	}

	private  void CalculateOutputm5(MyString input) {
		Branch br1 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr2_ai1_VoidReply", this, br1);
		I.myAnd( I.bool2,I.bool1,cf, br1);
		this.branches.add(br1);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("h", true));
			I.myPrint("none");
		}
	}

	private  void CalculateOutputm6(MyString input) {
		Branch br1 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr4_ni1_ne1", this, br1);
		I.myAnd( I.bool2,I.bool1,cf, br1);
		this.branches.add(br1);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf", true));
			I.myAdd( I.var1,a2108127495,new MyInt(13863), true);
			I.myMul( I.var2,I.var1,2);
			I.myMul( I.var3,I.var2,1);
			a2108127495 = I.myAssign(I.var3);
			I.myMul( I.var1,a2108127495,a2108127495);
			I.myMod( I.var2,I.var1,14999);
			I.myAdd( I.var3,I.var2,-1380);
			I.myMod( I.var4,I.var3,47);
			I.myDel( I.var5,I.var4,-150);
			I.myAdd( I.var6,I.var5,23887);
			I.myAdd( I.var7,I.var6,-23885);
			a422009172 = I.myAssign(I.var7);
			I.myPrint("none");
		}

		Branch br2 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr4_ai1_VoidReply", this, br2);
		I.myAnd( I.bool2,cf,I.bool1, br2);
		this.branches.add(br2);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("e", true));
			I.myAdd( I.var1,a2108127495,-139);
			I.myAdd( I.var2,I.var1,-4);
			I.myAdd( I.var3,I.var2,5);
			a2108127495 = I.myAssign(I.var3);
			I.myPrint("ai1_VoidReply");
		}
	}

	private  void CalculateOutputm7(MyString input) {
		Branch br1 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr4_ni1_ne1", this, br1);
		I.myAnd( I.bool2,I.bool1,cf, br1);
		this.branches.add(br1);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("g", true));
			I.myDel( I.var1,a2108127495,new MyInt(128));
			I.myDel( I.var2,I.var1,-3384);
			I.myDel( I.var3,I.var2,4504);
			I.myDel( I.var4,I.var3,-1107);
			a2108127495 = I.myAssign(I.var4);
			I.myPrint("none");
		}

		Branch br2 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr4_ai1_VoidReply", this, br2);
		I.myAnd( I.bool2,cf,I.bool1, br2);
		this.branches.add(br2);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf", true));
			I.myDel( I.var1,a2108127495,new MyInt(22339), true);
			I.myDel( I.var2,I.var1,-35662);
			I.myMul( I.var3,I.var2,2);
			I.myMod( I.var4,I.var3,72);
			I.myDel( I.var5,I.var4,149);
			a2108127495 = I.myAssign(I.var5);
			I.myPrint("ai1_VoidReply");
		}
	}

	private  void CalculateOutputm8(MyString input) {
		Branch br1 = new Branch(input.val);
		I.myEquals( I.bool1,input,"usr4_ai1_VoidReply", this, br1);
		I.myAnd( I.bool2,I.bool1,cf, br1);
		this.branches.add(br1);

		if(I.myIf(I.bool2)) {
			cf = I.myAssign(new MyBool(false, "cf", true));
			a1745113960 = I.myAssign(new MyString("e", true));
			I.myMul( I.var1,a2108127495,a422009172, true);
			I.myMod( I.var2,I.var1,14999);
			I.myDiv( I.var3,I.var2,5);
			I.myMod( I.var4,I.var3,72);
			I.myAdd( I.var5,I.var4,-156);
			I.myMul( I.var6,I.var5,5);
			I.myMod( I.var7,I.var6,72);
			I.myAdd( I.var8,I.var7,-65);
			a2108127495 = I.myAssign(I.var8);
			I.myPrint("ni1_ne1__ai1_VoidReply");
		}
	}

	public  void CalculateOutput(MyString input) {
		cf = new MyBool(true, "cf");

		Branch br1 = new Branch(input.val);
		I.myLessEqual( I.bool1,a2108127495,-164, br1);
		I.myAnd( I.bool2,cf,I.bool1);
		this.branches.add(br1);

		if(I.myIf(I.bool2)) {
			Branch br2 = new Branch(input.val);
			I.myLess( I.bool1,-83,a1522448132, br2);
			I.myGreaterEqual( I.bool2,18,a1522448132, br2);
			I.myAnd( I.bool3,I.bool1,I.bool2, br2);
			I.myAnd( I.bool4,cf,I.bool3, br2);
			this.branches.add(br2);

			if(I.myIf(I.bool4)) {
				CalculateOutputm1(input);
			}
		}

		Branch br3 = new Branch(input.val);
		I.myLess( I.bool1,-164,a2108127495, br3);
		I.myGreaterEqual( I.bool2,-19,a2108127495, br3);
		I.myAnd( I.bool3,I.bool1,I.bool2, br3);
		I.myAnd( I.bool4,cf,I.bool3, br3);
		this.branches.add(br3);

		if(I.myIf(I.bool4)) {
			Branch br4 = new Branch(input.val);
			I.myEquals( I.bool1,a1745113960,"e", this, br4);
			I.myAnd( I.bool2,cf,I.bool1, br4);
			this.branches.add(br4);


			if(I.myIf(I.bool2)) {
				CalculateOutputm2(input);
			}

			Branch br5 = new Branch(input.val);
			I.myEquals( I.bool1,a1745113960,"g", this, br5);
			I.myAnd( I.bool2,cf,I.bool1, br5);
			this.branches.add(br5);

			if(I.myIf(I.bool2)) {
				CalculateOutputm3(input);
			}

			Branch br6 = new Branch(input.val);
			I.myEquals( I.bool1,a1745113960,"h", this, br6);
			I.myAnd( I.bool2,cf,I.bool1, br6);
			this.branches.add(br6);

			if(I.myIf(I.bool2)) {
				CalculateOutputm4(input);
			}

			Branch br7 = new Branch(input.val);
			I.myEquals( I.bool1,a1745113960,"i", this, br7);
			I.myAnd( I.bool2,I.bool1,cf, br7);
			this.branches.add(br7);

			if(I.myIf(I.bool2)) {
				CalculateOutputm5(input);
			}
		}

		Branch br8 = new Branch(input.val);
		I.myLess( I.bool1,-19,a2108127495, br8);
		I.myGreaterEqual( I.bool2,100,a2108127495, br8);
		I.myAnd( I.bool3,I.bool1,I.bool2, br8);
		I.myAnd( I.bool4,I.bool3,cf, br8);
		this.branches.add(br8);

		if(I.myIf(I.bool4)) {
			Branch br9 = new Branch(input.val);
			I.myEquals( I.bool1,a1745113960,"g", this, br9);
			I.myAnd( I.bool2,cf,I.bool1, br9);
			this.branches.add(br9);
			if(I.myIf(I.bool2)) {
				CalculateOutputm6(input);
			}

			Branch br10 = new Branch(input.val);
			I.myEquals( I.bool1,a1745113960,"h", this, br10);
			I.myAnd( I.bool2,cf,I.bool1, br10);
			this.branches.add(br10);

			if(I.myIf(I.bool2)) {
				CalculateOutputm7(input);
			}
		}

		Branch br11 = new Branch(input.val);
		I.myLess( I.bool1,100,a2108127495, br11);
		I.myAnd( I.bool2,cf,I.bool1, br11);
		this.branches.add(br11);

		if(I.myIf(I.bool2)) {
			Branch br12 = new Branch(input.val);
			I.myLess( I.bool1,103,a422009172, br12);
			I.myGreaterEqual( I.bool2,198,a422009172, br12);
			I.myAnd( I.bool3,I.bool1,I.bool2, br12);
			I.myAnd( I.bool4,I.bool3,cf, br12);
			this.branches.add(br12);

			if(I.myIf(I.bool4)) {
				CalculateOutputm8(input);
			}
		}

		Branch br12 = new Branch(input.val);
		br12.dependentVariable=cf;
		this.branches.add(br12);
		if(I.myIf(cf))
			throw new IllegalArgumentException("Current state has no transition for this input!");
	}

	public static void main(String[] args) throws Exception {
		Run currentRun;
		int count=0;
		instm183_LTL_CTLFuzzer eca = new instm183_LTL_CTLFuzzer();
		while(count != 1000) {
			//Resetting everything
			branches = new HashSet<>();

			eca.reset();
			MyString[] fuzzed_inputs = Fuzzer.fuzz(eca.inputs);
			currentRun = new Run(fuzzed_inputs);

			System.out.println("Fuzzed input length is: " + fuzzed_inputs.length);
			for(int i = 0; i < fuzzed_inputs.length; i++) {
				MyString input = fuzzed_inputs[i];
				Branch br1 = new Branch(input.val);
				System.out.println("Fuzzing: " + input.val);
				I.myEquals( I.bool1, input,"ai1_ce1", br1);
				I.myEquals( I.bool2,input,"usr4_ai1_VoidReply", br1);
				I.myEquals( I.bool3,input,"usr4_ni1_ne1", br1);
				I.myEquals( I.bool4,input,"ai1_ce2", br1);
				I.myEquals( I.bool5,input,"usr2_ai1_VoidReply", br1);
				I.myAnd( I.bool6,I.bool1,I.bool2, br1);
				I.myAnd( I.bool7,I.bool3,I.bool4, br1);
				I.myAnd( I.bool8,I.bool6,I.bool7, br1);
				I.myAnd( I.bool9,I.bool8,I.bool5, br1);
				branches.add(br1);

				if(I.myIf(I.bool9))
					currentRun.exceptions.add(new IllegalArgumentException("Current state has no transition for this input!"));
					//throw new IllegalArgumentException("Current state has no transition for this input!");
				try {
					eca.CalculateOutput(input);
					System.out.println();
					System.out.println();
					//printing the final trace.
					String arr[] = (String[])eca.a422009172.getFlow();
					if (arr.length != 0) {
						System.out.println("Name of deepest int variable");
						System.out.println(arr[arr.length - 1]);
					}
					else {
						System.out.println("No int variables reached for this trace");
					}

					arr = (String[])eca.cf.getFlow();
					if (arr.length != 0) {
						System.out.println("Name of deepest boolean variable");
						System.out.println(arr[arr.length - 1]);
					}
					else {
						System.out.println("No int variables reached for this trace");
					}

					arr = (String[])eca.a1745113960.getFlow();
					if (arr.length != 0) {
						System.out.println("Name of deepest string variable");
						System.out.println(arr[arr.length - 1]);
					}
					else {
						System.out.println("No int variables reached for this trace");
					}
					//printing everything
					/*String[] arr = (String[])eca.a422009172.getFlow();
					System.out.println("Integer taints and their values.");

					for(String a : arr) {
						System.out.println("  name: " + a);
					}

					String[] arr2 = eca.cf.getFlow();
					System.out.println("Boolean taints and their values.");

					for(String a : arr2) {
					}

					System.out.println("String taints and their values.");
					String[] arr3 = eca.a1745113960.getFlow();

					for (String a : arr3) {
						System.out.println("  name: " + a);
					}*/
					currentRun.branches = branches;
				}
				//catchExc
				catch(IllegalArgumentException e) {
					System.err.println("Invalid input: " + e.getMessage());
					currentRun.exceptions.add(e);
				}
			}



			//Task 2
			/*Calc c = new Calc(fuzzed_inputs, branches);
			//printing branches
			System.out.println("\nPrinting branches");

			HashMap<Branch, Integer> map = null;
			Iterator it = null;
			for (MyString s : fuzzed_inputs) {
				map=c.calculateDistance(s.val);
				it = map.entrySet().iterator();
				System.out.println("  Input is " + s.val);
				System.out.println("  Branch hashes, and the distance are");
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					System.out.println("    " + pair.getKey() + " " + pair.getValue());
				}
			}*/
			//Task 3

			count++;
			runLst.add(currentRun);
		}



	}

	public void reset(){
		System.out.println("reset");a422009172 = new MyInt(-68, "a422009172");
		cf = new MyBool(true, "cf");
		a2108127495 = new MyInt(-44, "a2108127495");
		a1522448132 = new MyInt(173, "a1522448132");
		a1745113960 = new MyString("h");
	}
}

class Fuzzer {
	public static MyString[] fuzz(MyString[] inputs){
		Random rand = new Random();
		int length = rand.nextInt(50) + 10;
		MyString[] result = new MyString[length];

		for(int i = 0; i < length; i++){
			int index = rand.nextInt(inputs.length);
			result[i] = new MyString(inputs[index].val, true);
		}
		return result;
	}
}

class MyInt {
	public int val = 0;
	public boolean flow = false;
	public Set<String> flowTrack = new HashSet<String>();
	public String varName;

	public MyInt(int v) {
		val=v;
	}

	public MyInt(int v, String varName, boolean b){
		val = v;
		this.varName=varName;
		if ( (flow = b) == true) flowTrack.add(varName);
	}

	public MyInt(int v, String varName){
		val = v;
		flowTrack.add(varName);
	}
	public void addFlowVar(String varName) {
		flowTrack.add(varName);
	}

	public Object[] getFlow() {
		Object[] arr = flowTrack.toArray();
		return Arrays.copyOf(arr, arr.length, String[].class);
	}
}
class MyBool {
	public boolean val = false;
	public boolean flow = false;
	public String varName;
	public Set<String> flowTrack = new HashSet<String>();

	public MyBool(boolean v) {val = v;}
	public MyBool(boolean v, String varName){
		this.val = v;
		this.varName=varName;
	}

	public MyBool(boolean v, String varName, boolean b){
		this.val = v;
		this.varName = varName;
		if ( (flow = b) == true) flowTrack.add(varName);
	}

	public void addFlowVar(String varName) {
		flowTrack.add(varName);
	}

	public String[] getFlow() {
		Object[] arr = flowTrack.toArray();
		return Arrays.copyOf(arr, arr.length, String[].class);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MyBool myBool = (MyBool) o;
		return val == myBool.val;
	}
}

class MyString {
	public String val = "";
	public boolean flow = false;
	public Set<String> flowTrack = new HashSet<String>();
	public MyString(String v) {
		this.val = v;
	}

	public MyString(String v, boolean b){
		this.val = v;
		flow=b;
		if ( flow == true)
			flowTrack.add(v);
	}

	public void addFlowVar(String varName) {
		flowTrack.add(varName);
	}

	public String[] getFlow() {
		Object[] arr = flowTrack.toArray();
		return Arrays.copyOf(arr, arr.length, String[].class);
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

public static void myAdd(MyInt a, MyInt b, MyInt c){ a.val = b.val+c.val;
if (b.flow == true || c.flow== true) a.flow=true;
}
public static void myAdd(MyInt a, MyInt b, MyInt c, boolean bo){ a.val = b.val+c.val; a.flow=bo;}

public static void myDel(MyInt a, MyInt b, MyInt c, boolean bo){ a.val = b.val-c.val; c.flow=bo; }
public static void myDel(MyInt a, MyInt b, MyInt c){ a.val = b.val-c.val;
if (b.flow == true || c.flow== true) a.flow=true;}

public static void myMul(MyInt a, MyInt b, MyInt c, boolean bo){ a.val = b.val*c.val; a.flow=bo; }
public static void myMul(MyInt a, MyInt b, MyInt c){ a.val = b.val*c.val;
if (b.flow == true || c.flow == true) a.flow=true;}

public static void myDiv(MyInt a, MyInt b, MyInt c, boolean bo){ a.val = b.val/c.val; a.flow=bo;}
public static void myDiv(MyInt a, MyInt b, MyInt c){ a.val = b.val/c.val;
	if (b.flow == true || c.flow == true) a.flow=true;}

public static void myMod(MyInt a, MyInt b, MyInt c, boolean bo){ a.val = b.val%c.val;  }
public static void myMod(MyInt a, MyInt b, MyInt c){ a.val = b.val%c.val;
	if (b.flow == true || c.flow == true) a.flow=true;}

public static void myInd(MyInt a, MyInt[] b, MyInt c, boolean bo){ a.val = b[c.val].val; }
public static void myInd(MyInt a, MyInt[] b, MyInt c){ a.val = b[c.val].val;
	}

public static void myInd(MyString a, MyString[] b, MyInt c, boolean bo){ a.val = b[c.val].val; }
public static void myInd(MyString a, MyString[] b, MyInt c){ a.val = b[c.val].val; }

public static void myEquals(MyBool a, MyBool b, MyBool c){ a.val = (b.val == c.val); }
public static void myEquals(MyBool a, MyInt b, MyInt c){ a.val = (b.val == c.val); }
public static void myEquals(MyBool a, MyString b, MyString c,
							instm183_LTL_CTLFuzzer o) {
	a.val = (b.val.equals(c.val));
	MyString ins[] = o.inputs;
	boolean eq=false;
	for (MyString s : ins) {
		if (ins.equals(s)) {
			a.flow=true;
		}
	}
}

public static void myEquals(MyBool a, MyString b, MyString c,
		instm183_LTL_CTLFuzzer o, Branch br) {
	a.val = (b.val.equals(c.val));

	Condition cond = new Condition();
	cond.bTypeEnum=TypeEnum.myEquals;
	cond.boolOpA=a;
	cond.strOpB=b;
	cond.strOpC=c;

	br.condLst.add(cond);

	MyString ins[] = o.inputs;
	boolean eq=false;
	for (MyString s : ins) {
		if (ins.equals(s)) {
			a.flow=true;
		}
	}
}

public static void myEquals(MyBool a, MyString b, MyString c) {
	a.val = (b.val.equals(c.val));
}

public static void myEquals(MyBool a, MyString b, MyString c, Branch br) {
	a.val = (b.val.equals(c.val));
	Condition cond = new Condition();
	cond.bTypeEnum=TypeEnum.myEquals;
	cond.boolOpA=a; cond.strOpB=b; cond.strOpC=c;
	br.condLst.add(cond);
}

public static void myLess(MyBool a, MyInt b, MyInt c){ a.val = (b.val < c.val); }

public static void myLess(MyBool a, MyInt b, MyInt c, Branch br){
	a.val = (b.val < c.val);
	Condition cond = new Condition();
	cond.bTypeEnum=TypeEnum.myLess;
	cond.boolOpA=a; cond.intOpB=b; cond.intOpC=c;
	br.condLst.add(cond);
}

public static void myGreater(MyBool a, MyInt b, MyInt c){ a.val = (b.val > c.val); }

public static void myLessEqual(MyBool a, MyInt b, MyInt c){ a.val = (b.val <= c.val); }

public static void myLessEqual(MyBool a, MyInt b, MyInt c, Branch br){
	a.val = (b.val <= c.val);
	Condition cond = new Condition();
	cond.bTypeEnum=TypeEnum.myLessEqual;
	cond.boolOpA=a; cond.intOpB=b; cond.intOpC=c;
	br.condLst.add(cond);
}

public static void myGreaterEqual(MyBool a, MyInt b, MyInt c){ a.val = (b.val >= c.val); }
public static void myGreaterEqual(MyBool a, MyInt b, MyInt c, Branch br){
	a.val = (b.val >= c.val);
	Condition cond = new Condition();
	cond.bTypeEnum=TypeEnum.myGreaterEqual;
	cond.boolOpA=a; cond.intOpB=b; cond.intOpC=c;
	br.condLst.add(cond);
}

public static MyBool myAssign(MyBool b){
	MyBool a = new MyBool(b.val, b.varName, b.flow);
	return a;
}

public static MyInt myAssign(MyInt b) {
	MyInt a = new MyInt(b.val, b.varName, b.flow);
	return a;
}

public static MyInt[] myAssign(MyInt[] b){
	MyInt a[] = new MyInt[b.length];
		for(int i = 0; i < b.length; i++)
			a[i] = new MyInt(b[i].val, b[i].varName, b[i].flow);
		return a;
}
public static MyString myAssign(MyString b){
	MyString a = new MyString(b.val, b.flow);
	return a;
}

public static void myAnd(MyBool a, MyBool b, MyBool c){ a.val = (b.val && c.val); }

public static void myAnd(MyBool a, MyBool b, MyBool c,
						 Branch br){
	a.val = (b.val && c.val);
	Condition cond = new Condition();
	cond.bTypeEnum=TypeEnum.myAnd;
	cond.boolOpA=a;
	cond.boolOpB=b;
	cond.boolOpC=c;
	br.condLst.add(cond);
}

public static void myOr(MyBool a, MyBool b, MyBool c){ a.val = (b.val || c.val); }
public static void myNot(MyBool a, MyBool b){ a.val = (!b.val); }
public static void myPrint(MyString a){ System.out.println("\n"+a.val); }
public static void myPrint(String a) {
	myPrint(new MyString(a));
}

public static boolean myIf(MyBool a){
	System.out.print("b" + a.val + " ");
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

public static void myMod(MyInt a, MyInt b, int c){ myMod(a,b,new MyInt(c)); }

public static void myMod(MyInt a, int b, MyInt c){ myMod(a,new MyInt(b),c); }
public static void myMod(MyInt a, int b, int c){ myMod(a,new MyInt(b),new MyInt(c)); }
public static void myInd(MyInt a, MyInt[] b, int c){ myInd(a,b,new MyInt(c)); }
public static void myInd(MyInt a, int[] b, int c){ myInd(a,I.myAssign(b),new MyInt(c)); }
public static void myInd(MyString a, MyString[] b, int c){ myInd(a,b,new MyInt(c)); }
public static void myEquals(MyBool a, MyBool b, boolean c){ myEquals(a, b, new MyBool(c)); }
public static void myEquals(MyBool a, MyInt b, int c){ myEquals(a, b, new MyInt(c)); }

public static void myEquals(MyBool a, MyString b, String c,
							instm183_LTL_CTLFuzzer o) {
	myEquals(a, b, new MyString(c), o);
}

public static void myEquals(MyBool a, MyString b, String c,
				instm183_LTL_CTLFuzzer o, Branch br) {
	myEquals(a, b, new MyString(c), o, br);
}

public static void myEquals(MyBool a, MyString b, String c) {
		myEquals(a, b, new MyString(c));
}

public static void myEquals(MyBool a, MyString b, String c, Branch br) {
	myEquals(a, b, new MyString(c), br);
}

public static void myEquals(MyBool a, boolean b, MyBool c){ myEquals(a, new MyBool(b), c); }
public static void myEquals(MyBool a, int b, MyInt c){ myEquals(a, new MyInt(b), c); }

public static void myEquals(MyBool a, String b, MyString c, instm183_LTL_CTLFuzzer o){ myEquals(a, new MyString(b), c, o); }
public static void myEquals(MyBool a, String b, MyString c) { myEquals(a, new MyString(b), c); }

public static void myEquals(MyBool a, boolean b, boolean c){ myEquals(a, new MyBool(b), new MyBool(c)); }
public static void myEquals(MyBool a, int b, int c){ myEquals(a, new MyInt(b), new MyInt(c)); }

public static void myEquals(MyBool a, String b, String c, instm183_LTL_CTLFuzzer o){
	myEquals(a, new MyString(b), new MyString(c), o);
}

public static void myLess(MyBool a, MyInt b, int c){ myLess(a, b, new MyInt(c)); }
public static void myGreater(MyBool a, MyInt b, int c){ myGreater(a, b, new MyInt(c)); }

public static void myLessEqual(MyBool a, MyInt b, int c){
	myLessEqual(a, b, new MyInt(c)); }

public static void myLessEqual(MyBool a, MyInt b, int c, Branch br){
	myLessEqual(a, b, new MyInt(c), br);
}

public static void myGreaterEqual(MyBool a, MyInt b, int c){ myGreaterEqual(a, b, new MyInt(c));
}

public static void myLess(MyBool a, int b, MyInt c){myLess(a, new MyInt(b), c); }

public static void myLess(MyBool a, int b, MyInt c, Branch br){
	myLess(a, new MyInt(b), c, br); }

public static void myGreater(MyBool a, int b, MyInt c){ myGreater(a, new MyInt(b), c); }
public static void myLessEqual(MyBool a, int b, MyInt c){ myLessEqual(a, new MyInt(b), c); }

public static void myGreaterEqual(MyBool a, int b, MyInt c){ myGreaterEqual(a, new MyInt(b), c); }
public static void myGreaterEqual(MyBool a, int b, MyInt c, Branch br){
	myGreaterEqual(a, new MyInt(b), c, br); }

public static void myLess(MyBool a, int b, int c){ myLess(a, new MyInt(b), new MyInt(c)); }
public static void myGreater(MyBool a, int b, int c){ myGreater(a, new MyInt(b), new MyInt(c)); }
public static void myLessEqual(MyBool a, int b, int c){ myLessEqual(a, new MyInt(b), new MyInt(c)); }
public static void myGreaterEqual(MyBool a, int b, int c){ myGreaterEqual(a, new MyInt(b), new MyInt(c)); }
public static MyBool myAssign(boolean b){ return myAssign(new MyBool(b)); }
public static MyInt myAssign(int b){ return myAssign(new MyInt(b)); }
public static MyInt[] myAssign(int[] b){ MyInt a[] = new MyInt[b.length]; for(int i = 0; i < b.length; i++) a[i] = new MyInt(b[i]); return myAssign(a); }
public static MyString myAssign(String b){ return myAssign(new MyString(b)); }
}
