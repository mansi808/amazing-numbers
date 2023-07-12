package numbers;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Property[] availProperties = Property.values();

        System.out.println("Welcome to Amazing Numbers!\n");

        System.out.println("Supported requests:");
        System.out.println(Request.options);

        ArrayList<String> arr = Request.requestForm(scanner);
        long n = Long.parseLong(arr.get(0));

        while (n!=0) { //first parameter -->zero input=exit
            if (arr.size() == 1) {

                Request request = new Request(n);
                request.performRequest1();

            } else if (arr.size() > 1) {

                int loop = Integer.parseInt(arr.get(1));

                if (arr.size() == 2) {

                    Request request = new Request(n, loop);
                    request.performRequest2();

                } else if (arr.size() > 2) {
                    Request request = new Request(n, loop, arr.subList(2, arr.size()));

                    //need to continue this 'while' loop
                    boolean repeatForm =false;

                    ArrayList<String> invalidProperties = request.getInvalidInputProperties(availProperties);

                    //print the request form again:-
                    //if properties are not mutually exclusive eg even odd --> request.areMutuallyExclusive()
                    //if there are any invalidProperties --> request.getInvalidInputProperties()
                    if (invalidProperties.size()>0) {
                        repeatForm=true;

                        System.out.println(((invalidProperties.size() < 2) ? "The property " : "The properties ") +
                                invalidProperties + ((invalidProperties.size() < 2) ? " is wrong.\n" : " are wrong.\n") +
                                "Available properties: " + Arrays.toString(availProperties));

                    }
                    if (request.areMutuallyExclusive()) {
                        repeatForm=true;

                        System.out.println("The request contains mutually exclusive properties: " + request.properties +
                                ".\nThere are no numbers with these properties.");

                    }

                    if (!repeatForm) {
                        request.performRequest2();
                    }
                }

            }
            arr = Request.requestForm(scanner);
            n = Long.parseLong(arr.get(0));

        }
        System.out.println("Goodbye!");

    }
}
