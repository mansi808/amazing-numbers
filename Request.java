package numbers;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Request {

    long naturalNum; //main number
    int loopFor; // natural number for properties of 'x' times
    List properties = new ArrayList();
    static String options =
            "- enter a natural number to know its properties; \n" +
                    "- enter two natural numbers to obtain the properties of the list:\n" +
                    "  * the first parameter represents a starting number;\n" +
                    "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                    "- two natural numbers and properties to search for;\n" +
                    "- a property preceded by minus must not be present in numbers;\n" +
                    "- separate the parameters with one space;\n" +
                    "- enter 0 to exit.";


    //multiple constructors
    //for performRequest1
    Request(long naturalNum) {
        this.naturalNum = naturalNum;
    }

    //for performRequest2
    Request(long naturalNum, int loopFor) {
        this.naturalNum = naturalNum;
        this.loopFor = loopFor;
    }

    //for performRequest2
    Request(long naturalNum, int loopFor, List properties) {
        this.naturalNum = naturalNum;
        this.loopFor = loopFor;

        for (Object property : properties) {
            this.properties.add(property.toString().toUpperCase());
        }

    }

    //for input of one number
    public void performRequest1() {
        System.out.print("\nProperties of " + naturalNum + "\n" +
                "\t\tbuzz: " + Property.BUZZ.check(naturalNum) + "\n" +
                "\t\tduck: " + Property.DUCK.check(naturalNum) + "\n" +
                "\t\tpalindromic: " + Property.PALINDROMIC.check(naturalNum) + "\n" +
                "\t\tgapful: " + Property.GAPFUL.check(naturalNum) + "\n" +
                "\t\tspy: " + Property.SPY.check(naturalNum) + "\n" +
                "\t\tsquare: " + Property.SQUARE.check(naturalNum) + "\n" +
                "\t\tsunny: " + Property.SUNNY.check(naturalNum) + "\n" +
                "\t\tjumping: " + Property.JUMPING.check(naturalNum) + "\n" +
                "\t\thappy: " + Property.HAPPY.check(naturalNum) + "\n" +
                "\t\tsad: " + Property.SAD.check(naturalNum) + "\n" +
                "\t\teven: " + Property.EVEN.check(naturalNum) + "\n" +
                "\t\todd: " + Property.ODD.check(naturalNum) + "\n");
    }

    //for input of two numbers
    //for input of two numbers and properties
    public void performRequest2() {

        //arr stores the numbers which satisfy conditions
        ArrayList<String> arr = new ArrayList<>();
        int i = 0;

        mainLoop:
        while (arr.size() < loopFor) {

            //properties present in the current number
            ArrayList<String> presentProperties = new ArrayList<>();

            List excludeProperties = new ArrayList<>();

            long newNum = naturalNum + i;

            if (Property.BUZZ.check(newNum)) presentProperties.add("buzz");
            if (Property.DUCK.check(newNum)) presentProperties.add("duck");
            if (Property.PALINDROMIC.check(newNum)) presentProperties.add("palindromic");
            if (Property.GAPFUL.check(newNum)) presentProperties.add("gapful");
            if (Property.SPY.check(newNum)) presentProperties.add("spy");
            if (Property.SQUARE.check(newNum)) presentProperties.add("square");
            if (Property.SUNNY.check(newNum)) presentProperties.add("sunny");
            if (Property.JUMPING.check(newNum)) presentProperties.add("jumping");
            if (Property.HAPPY.check(newNum)) presentProperties.add("happy");
            if (Property.SAD.check(newNum)) presentProperties.add("sad");
            if (Property.EVEN.check(newNum)) presentProperties.add("even");
            if (Property.ODD.check(newNum)) presentProperties.add("odd");

            //present properties are in lower case
            String message = newNum + " is " + presentProperties;

            //if specific properties are requested by user
            if (properties.size() > 0) {

                for (int x = 0; x < properties.size(); x++) {
                    if (properties.get(x).toString().startsWith("-")) {
                        excludeProperties.add(properties.get(x).toString().replace("-",""));
                    }
                }

                int elementsCheck = 0;

                for (Object property : properties) {
                    for (String presentProperty : presentProperties) {
                        //if properties of current number match properties requested

                        if (property.toString().equalsIgnoreCase(presentProperty)) {
                            elementsCheck++;
                        }
                    }
                }
                for (Object excludeProperty: excludeProperties) {
                    boolean x = (presentProperties.toString().toUpperCase().contains(excludeProperty.toString()));
                    if (!x) {
                        elementsCheck++;
                    }
                }


                //if all current properties matched with requested properties
                if (elementsCheck == properties.size()) {
                    arr.add(message);
                }
            } else {       //if no properties are requested
                arr.add(message);
            }
            i++;

        }
        System.out.println();
        for (String x : arr) {
            System.out.print("\t\t");
            System.out.println(x);
        }
    }

    //takes input from user and return the String[]
    public static ArrayList<String> requestForm(Scanner scanner) {


        System.out.println("\nEnter a request:");
        String[] array = scanner.nextLine().trim().split("[\s]+");
        boolean repeat = false;

        // String[] into ArrayList
        ArrayList<String> arr = new ArrayList<String>(
                Arrays.asList(array));


        //handles exception of first parameter not being long ie(NumberFormatException)
        //handles naturalNumber (at first parameter) if it is negative
        try {
            long n = Long.parseLong(arr.get(0));

            if (n < 0) {
                System.out.println("The first parameter should be a natural number or zero.\n");
                repeat =true;
            }

        } catch (NumberFormatException e) {

            System.out.println("The first parameter should be a natural number or zero.\n");
            repeat =true;

        }

        //handles naturalNumber (at second parameter) if it is negative
        if (arr.size() >= 2) {

            if ( Long.parseLong(arr.get(1))<= 0) {
                System.out.println("The second parameter should be a natural number.\n");
                repeat =true;

            }

        }
        if (repeat) {
            arr = requestForm(scanner);
        } return arr;
    }

    //numbers cannot have mutually exclusive properties
    public boolean areMutuallyExclusive() {
        if ( (properties.contains("EVEN") && properties.contains("ODD")) || (properties.contains("-EVEN") && properties.contains("-ODD")) ||
                (properties.contains("DUCK") && properties.contains("SPY")) || (properties.contains("DUCK") && properties.contains("-SPY")) ||
                (properties.contains("SUNNY") && properties.contains("SQUARE")) || (properties.contains("-SUNNY") && properties.contains("-SQUARE")) ||
                (properties.contains("ODD") && properties.contains("EVEN")) ||  (properties.contains("-ODD") && properties.contains("-EVEN"))
        ) {
            return true;
        }
        for (Property property : Property.values()) {
            if (properties.contains(property.name) && properties.contains("-" + property.name)) {
                return true;
            }
        }
        return false;
    }

    //    returns all properties not availaible and/or are invalid
    public ArrayList<String> getInvalidInputProperties(Property[] availProperties) {

        ArrayList<String> invalidProperties = new ArrayList<>();

        for (int i = 0; i < properties.size(); i++) {
            for (int j = 0; j < availProperties.length; j++) {
                if ((availProperties[j].name.equalsIgnoreCase(properties.get(i).toString())) || (("-"+availProperties[j].name).equalsIgnoreCase(properties.get(i).toString()))) {

                    break;
                }
                if (j == availProperties.length - 1) {
                    invalidProperties.add(properties.get(i).toString());
                }
            }
        }
        return invalidProperties;
    }
}
