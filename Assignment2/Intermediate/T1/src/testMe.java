class me {}

class me2{}

class test {
    Class varClass;
}

public class testMe {
    public static void main(String[] args) {
        me m1 = new me(); me2 m2 = new me2();

        test t1 = new test(), t2 = new test();

        t1.varClass=m1.getClass(); t2.varClass=m2.getClass();

        System.out.println("t1 is " + t1.varClass.getCanonicalName());
    }
}
