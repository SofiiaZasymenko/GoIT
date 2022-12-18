public class HelloWorld {

    // create variable
    // <type> <name> = <value>;
    // String[] stringArray = new String[10];

    // method structure:
    // <access_modifier> <non_access_modifier> <return_type> <name>(<param_type> <param_name>, ... ) { ... }
    // public static void main(String[] args) { }

    /**
     * This is the main method
     * @param args command line arguments
     */
    public static void main (String [] args) {
        // This line prints into console
        System.out.println ("Hello, World!");
//        System.out.println ("Old Hello, World!");
        // TODO implement greeting with name
        if (args.length > 0) {
            System.out.println("Hello, World from " + args[0] + "!");
        }
    }
}