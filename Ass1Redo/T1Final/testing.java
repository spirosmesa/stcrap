import java.util.LinkedHashSet;
import java.util.Random;

/*
class Calculate {
    //We can also change the method, to handle data types, other than Strings.
    private static String getDistString(Branch br) throws Exception {
        if (br.lVar!=null)
            if (br.lVar.inputVar == true)
                return ((MyString)br.lVar).val;
            else
                throw new Exception("lVar not true ");
        else if (br.lVar == null)
            throw new Exception("lVar null ");
        else if (br.rVar != null)
            if (br.rVar.inputVar == true)
                return ((MyString)br.rVar).val;
            else
                throw new Exception("rVar not true ");
        else
            throw new Exception("rVar null ");
    }

    private static String[] calcEqualDiff(String inputStr, String distVar, String alphabet, int length) {
        String[] ret = new String[length];
        char[] inputCharArr = inputStr.toCharArray(), distVarCharArr = distVar.toCharArray();

        for (int index = 0; index < length; index++) {
            //Get index of input char in alphabet.
            int inputAlphabetPos = alphabet.indexOf(inputCharArr[index]);
            int distVarAlphabetPos = alphabet.indexOf(distVarCharArr[index]);

            ret[index] = Integer.toString(inputAlphabetPos-distVarAlphabetPos);
        }
        return  ret;
    }

    private static String[] calcLessDiff(String inputStr, String distVar, String alphabet, int inLength, int distVarLength) {
        String[] ret = new String[inLength];
        char[] inputCharArr = inputStr.toCharArray(), distVarCharArr = distVar.toCharArray();

        //distVar.length() - input.length();
        int index = 0;
        //populate the elements of the output array, with the diff, up
        //to the length of the smallest, distVar array.
        while (index < distVarLength) {
            int inputAlphabetPos = alphabet.indexOf(inputCharArr[index]);
            int distVarAlphabetPos = alphabet.indexOf(distVarCharArr[index]);
            ret[index] = Integer.toString(inputAlphabetPos-distVarAlphabetPos);
            index++;
        }

        for (index = inLength; index < distVarLength; index++)
            ret[index] = "-" + alphabet.charAt(alphabet.indexOf(inputStr));
        return ret;
    }

    private static String[] calcGreaterDiff(String inputStr, String distVar, String alphabet, int inLength, int distVarLength) {
        String[] ret = new String[inLength];
        char[] inputCharArr = inputStr.toCharArray(), distVarCharArr = distVar.toCharArray();

        //distVar.length() - input.length();
        int index = 0;
        //populate the elements of the output array, with the diff, up
        //to the length of the smallest, distVar array.
        while (index < inLength) {
            int inputAlphabetPos = alphabet.indexOf(inputCharArr[index]);
            int distVarAlphabetPos = alphabet.indexOf(distVarCharArr[index]);
            ret[index] = Integer.toString(inputAlphabetPos-distVarAlphabetPos);
            index++;
        }

        for (index = distVarLength; index < inLength; index++)
            ret[index] = "+" + alphabet.charAt(alphabet.indexOf(inputStr));
        return null;
    }

    //It calculates the distance based on the length of the input.
    private static String[] calculateDistance(Branch br, String input, String alphabet) throws Exception{
        String distVar = null;

        try { distVar = getDistString(br); }
        catch (Exception e) {
            throw new Exception("calculateDistance: " + e.getMessage() + input); }

        int strLenDiff = distVar.length() - input.length();
        if (strLenDiff == 0) {
            return calcEqualDiff(input, distVar, alphabet, input.length());
        }
        else if (strLenDiff < 0) {
            return calcLessDiff(input, distVar, alphabet, input.length(), distVar.length());
        }
        else {
            return calcGreaterDiff(input, distVar, alphabet, input.length(), distVar.length());
        }
    }

    public static void printCalculatedDistance(Branch br, String input, String alphabet) {

    }
}

enum operations {
    equals,
    lessEqual,
    greaterEqual,
    and,
    less,
    mult,
    mod,
    boolAssign,
    stringAssign,
    intAssign,
    del,
    add,
    div
}

//Used to store branch operation outcome.
class Outcome {
    public Integer intOutcome = null;
    public Boolean boolOutcome = null;

    public Outcome(int outcome) {intOutcome = outcome;}
    public Outcome(boolean outcome) {boolOutcome=outcome;}
}

class BranchSet extends LinkedHashSet {
    private LinkedHashSet<Branch> branches = null;
    //Succeed set /*
    private BranchSet leftSet = null;
    //failSet set
    private BranchSet rightSet = null;

    //a number designating the branches, that belong to the same myIf.
    //public static long staticBranchSetNumber = Long.MIN_VALUE;
    public static int branchSetNumber = Integer.MIN_VALUE; //toAvoid overflows
    public static long staticBranchSetNumber = Long.MIN_VALUE;

    public BranchSet() {
        if (this.staticBranchSetNumber != Long.MAX_VALUE)
            this.staticBranchSetNumber = staticBranchSetNumber++;
    }

    public void instantiateMembers() {
        this.leftSet = new BranchSet();
        this.rightSet = new BranchSet();
        this.branches = new LinkedHashSet<>();
    }

    private void printBranches(BranchSet branches) {
        if (branches == null) return;
        if (branches.branches == null) return;
        for (Branch br : branches.branches){
            if (br == null) continue;
            br.printBranch();
        }
    }
    public void printBranchSet() {
        System.out.println("Branch set number: " + staticBranchSetNumber);
        System.out.println("-----BRANCHES INFO-----");
        for (Branch br : branches) {
            br.printBranch();
        }

        System.out.println("-----LEFT BRANCH SET INFO-----");
        if (leftSet == null)
            System.out.println("leftSet == null");
        else printBranches(leftSet);

        System.out.println("-----RIGHT BRANCH SET INFO-----");
        if (rightSet == null)
            System.out.println("rightSet == null");
        else printBranches(rightSet);
    }

    public LinkedHashSet<Branch> getBranches() {
        return this.branches;
    }

    public void addBranch(Branch br) {
        if (this.branches == null) this.branches = new LinkedHashSet<>();
        this.branches.add(br);
    }

    public void addLeftSet(BranchSet branchSet) {
        if (this.leftSet == null) this.leftSet = new BranchSet();
        this.leftSet = branchSet;
    }

    public BranchSet getLeftSet() {
        return this.leftSet;
    }

    public void addRightSet(BranchSet branchSet) {
        if (this.rightSet == null) this.rightSet = new BranchSet();
        this.rightSet = branchSet;
    }

    public BranchSet getRightSet() {
        return this.rightSet;
    }


}

class Branch {
    public Outcome outcome = null;
    public operations operation = null;
    public MyVar lVar = null, rVar = null;
    public Exception ex = null;

    public static boolean operate(Branch br, operations operation, MyVar lVar, MyVar rVar) throws Exception{
        Outcome opOutcome = null;
        if (br == null) throw new Exception("br null");
        if (operation == null) throw new Exception("operation null");
        if (lVar == null) throw new Exception("lVar null");
        if (rVar == null) throw new Exception("rVar null");
        if (br.outcome.boolOutcome ==  null) throw new Exception("br.boolOutcome null");
        else if (br.outcome.intOutcome == null) throw new Exception("br.intOutcome null");
        else opOutcome = br.outcome;

        if (!br.lVar.equals(lVar)) throw new Exception("br.lvar and lvar not equal");
        switch(br.operation) {
            case equals:
                return (((MyString) lVar).val.equals(((MyString) rVar).val) == opOutcome.boolOutcome);
            case lessEqual:
                return ((((MyInt)lVar).val <= ((MyInt)rVar).val ) == opOutcome.boolOutcome);
            case greaterEqual:
                return ((((MyInt)lVar).val >= ((MyInt)rVar).val ) == opOutcome.boolOutcome);
            case and:
                return ( (((MyBool)lVar).val & ((MyBool)rVar).val) == opOutcome.boolOutcome);
            case less:
                return ( (((MyInt)lVar).val < ((MyInt)rVar).val) == opOutcome.boolOutcome);
            case mult:
                return ((((MyInt)lVar).val * ((MyInt)rVar).val) == opOutcome.intOutcome);
            case mod:
                return ((((MyInt)lVar).val % ((MyInt)rVar).val)== opOutcome.intOutcome);
            case del:
                return ((((MyInt)lVar).val - ((MyInt)rVar).val) == opOutcome.intOutcome);
            case add:
                return ((((MyInt)lVar).val + ((MyInt)rVar).val) == opOutcome.intOutcome  );
            case div:
                return (( ((MyInt)lVar).val / ((MyInt)rVar).val) == opOutcome.intOutcome);
            default:
                throw new Exception("No such operation");
//			case boolAssign:
//			case stringAssign:
//			case intAssign:
        }
    }

    public void printBranch() {
        System.out.println("   lVar: " + lVar.toString());
        System.out.println("   rVar: " + rVar.toString());
        System.out.println("   operation: " + operation);

        if (outcome == null) System.out.println("Outcome is null.");
        if (outcome.intOutcome != null) System.out.println("  Integer outcome: " + outcome.intOutcome.intValue());
        else if (outcome.boolOutcome != null ) System.out.println("  boolean outcome: " + outcome.boolOutcome.booleanValue());
        else System.out.println("Shit happens. outcome.intOucome and outcome.boolOutcome are both null.");

        if (ex != null)
            System.out.println(" Caught Exception class: " + ex.getClass().toString());
    }
    public Branch() {}
    public Branch(Exception ex) {
        this.ex=ex;
    }

    public Branch(MyVar lVar, MyVar rVar, boolean outcome, operations operation) {
        this.lVar=lVar; this.rVar=rVar; this.outcome = new Outcome(outcome);this.operation=operation;
    }

    public Branch(MyVar lVar, MyVar rVar, int outcome, operations operation) {
        this.lVar=lVar; this.rVar=rVar; this.outcome=new Outcome(outcome);this.operation=operation;
    }
}

class MyInt extends MyVar{
    public int val = 0;

    private void initialize() {
        if (this.taintSet== null) this.taintSet=new LinkedHashSet<>();
    }

    //one with only val for compatibility reasons.
    public MyInt(int val) {
        super("", false);
        initialize();
        this.val = val;
    }

    public MyInt(int val, String name) {
        super(name, false);
        initialize();
        this.val=val;
    }

    public MyInt(int val, String name, boolean tainted) {
        super(name, false);
        initialize();
        this.val=val;
        if (tainted) this.taintSet.add(this);
    }

    public MyInt(int val, String varName, LinkedHashSet<MyVar> taintSet){
        super(varName, false);
        initialize();
        this.val = val;
        this.varName=varName;
    }

    public MyInt(int val, String varName, boolean br, LinkedHashSet<MyVar> taintSet){
        super(varName, false);
        initialize();
        this.val = val;
        this.varName=varName;
        if (br) taintSet.add(this);
    }

    public MyInt(int val, String varName, boolean br, boolean input, LinkedHashSet<MyVar> flowSet){
        super(varName, input);
        initialize();
        this.val = val;
        this.varName=varName;
        if (br) taintSet.add(this);
    }
}

class MyBool extends MyVar {
    public boolean val = false;
    public String varName;

    private void initialize() {
        if (this.taintSet== null) this.taintSet=new LinkedHashSet<>();
    }

    //Compatibility with the teacher provided code.
    public MyBool(boolean val) {super("", false); initialize(); this.val = val;}
    public MyBool(boolean val, String varName){
        super(varName, false);
        initialize();
        this.val = val;
        this.varName=varName;
    }

    public MyBool(boolean val, String varName, boolean br){
        super(varName, false);
        initialize();
        this.val = this.val;
        this.varName = varName;
        if (br) this.taintSet.add(this);
    }

    public MyBool(boolean val, String varName, boolean br, boolean inputVar){
        super(varName, inputVar);
        initialize();
        this.val = this.val;
        this.varName = varName;
        if (br) this.taintSet.add(this);
    }
}

class MyString extends MyVar{
    public String val = "";
    public int depth = 0;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(this instanceof  MyString)) return false;
        if (!this.val.equals(((MyString)obj).val) ||
                this.depth != ((MyString)obj).depth ||
                super.varName != ((MyString)obj).varName) return false;
        boolean sup = super.equals(obj);
        if (!sup) return sup;
        return true;
    }

    private void initialize() {
        if (this.taintSet== null) this.taintSet=new LinkedHashSet<>();
    }

    public MyString(String val) {
        super("", false);
        initialize();
        this.val = val;
    }

    public MyString(String val, boolean branch) {
        super("", false);
        initialize();
        if (branch) this.taintSet.add(this);
    }

    public MyString(String val, String varName, boolean branch, boolean input){
        super(varName, input);
        initialize();
        this.val=val;
        if (branch)
            this.taintSet.add(this);
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
*/

public class testing {
    static String[] arr = {"asd", "asdf", "asdfdfdf"};
    public static void main(String[] args) {
        System.out.println(arr);
    }
}
