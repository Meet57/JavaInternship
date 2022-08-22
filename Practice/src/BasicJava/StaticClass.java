package BasicJava;

/*
* Static class can access static members of the outer class
* Inner Class can access all members of the outer class
*
* We use inner classes to provide more security
* Anonymous class enable you to make your code more concise
* */
class TopLevelClass {

    void accessMembers(StaticClass outer) {
        // Compiler error: Cannot make a static reference to the non-static
        //     field OuterClass.outerField
        // System.out.println(OuterClass.outerField);
        System.out.println(outer.outerField);
        System.out.println(StaticClass.staticOuterField);
        StaticClass.InnerClass innerObject = outer.new InnerClass();
        innerObject.accessMembers();
        StaticClass.StaticNestedClass staticNestedObject = new StaticClass.StaticNestedClass();
//        staticNestedObject.accessMembers();
    }
}
public class StaticClass {

    String outerField = "Outer field";
    static String staticOuterField = "Static outer field";

    class InnerClass {
        void accessMembers() {
            System.out.println(outerField);
            System.out.println(staticOuterField);
        }
    }

    static class StaticNestedClass {
        void accessMembers(StaticClass outer) {
            // Compiler error: Cannot make a static reference to the non-static
            //     field outerField
            // System.out.println(outerField);
            System.out.println(outer.outerField);
            System.out.println(staticOuterField);
        }
    }

    public static void main(String[] args) {
        System.out.println("Inner class:");
        System.out.println("------------");
        StaticClass outerObject = new StaticClass();
        StaticClass.InnerClass innerObject = outerObject.new InnerClass();
        innerObject.accessMembers();

        System.out.println("\nStatic nested class:");
        System.out.println("--------------------");
        StaticNestedClass staticNestedObject = new StaticNestedClass();
        staticNestedObject.accessMembers(outerObject);

        System.out.println("\nTop-level class:");
        System.out.println("--------------------");
        TopLevelClass topLevelObject = new TopLevelClass();

        topLevelObject.accessMembers(outerObject);
    }
}