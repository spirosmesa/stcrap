import java.util.HashSet;
import java.util.Set;

class set{
    public static int sta = 0;
    public int sta2;

    public set() {
        this.sta2 = sta++;
    }
}

class set2{
    public static int sta = 0;
    public int sta2;

    public set2() {
        this.sta2 = ++sta;
    }
}

public class testing {
    public static void main(String[] args) {
        set s = new set();
        System.out.println("Post increment: sta " + s.sta + "sta 2 " + s.sta2);
        set2 s2 = new set2();
        System.out.println("Pre increment: sta: " + s2.sta + "sta 2 " + s2.sta2);
    }
}
