// 1. To have a static variable within a class.
// 2. add the name of the variable in a static hashset
// 3. 3 instances.
// 4. print the value of the var for all those instances.

import java.util.HashSet;
import java.util.Set;

class sta{
    public String name = null;
    public static Set<String> set = new HashSet<>();
    public sta(String in) {this.name = in; set.add(name);}
}

public class testing {
    public static void main(String[] args) {
        sta sta1 = new sta("sta1"), sta2 = new sta("sta2");

        System.out.println("sta1: ");
        for (String str : sta1.set) {
            System.out.println(str);
        }
        System.out.println("sta2: ");
        for (String str : sta2.set) {
            System.out.println(str);
        }
    }
}
