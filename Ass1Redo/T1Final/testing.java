import java.util.HashSet;
import java.util.Set;

//base class with static member.
//update base class, see effects on children.
//update child, see effect on base class.
//update child1, see effect on child2.

class base {
    public static String j = "base";
}
class c1 extends base{}
class c2 extends base{}
public class testing {
    public static void main(String[] args) {
        base b1 = new base();
        c1 ch1 = new c1();
        c2 ch2 = new c2();

        System.out.println("base j: " + b1.j);
        System.out.println("ch1 j: " + ch1.j);
        System.out.println("ch2 j: " + ch2.j);

        System.out.println("update base");
        b1.j = "george";

        System.out.println("base j: " + b1.j);
        System.out.println("ch1 j: " + ch1.j);
        System.out.println("ch2 j: " + ch2.j);
        b1.j = "base";

        System.out.println("Update child 1");
        ch1.j = "ch1";
        System.out.println("base j: " + b1.j);
        System.out.println("ch1 j: " + ch1.j);
        System.out.println("ch2 j: " + ch2.j);
        ch1.j = "base";
    }
}
