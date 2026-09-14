public class Pointer {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder(); // New instance of StringBuilder
        SegiEmpat obj1 = new SegiEmpat(); // New instance of SegiEmpat class
        SegiEmpat obj; // This is pointer variable
        obj = obj1; // Points to obj1's address
        obj.a = "hi";

        Object objs; // Can be assigned to any instance of different class
        objs = sb;

        Data data1 = new Data();
        data1.data = 1;

        Data data2 = new Data();
        data2.data = 2;

        data1.point = data2;

        System.out.println();

        int a1 = 1;
        System.out.println(obj1.a);
        System.out.println(obj1.getClass());
        System.out.println(obj.getClass());
        System.out.println(data1.point.data);
    }
}

// ____________________________________________________________________________________________________________

class SegiEmpat {
    String a;
    int b;
    boolean c;

    public SegiEmpat(String a, int b, boolean c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public SegiEmpat() {
        a = "";
        b = 0;
        c = true;
    }
}

class Data {
    Data point;
    int data;
}