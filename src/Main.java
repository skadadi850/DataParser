/***
 * Main class for data parsers
 * @author Simran Kadadi
 */
public class Main {
    public static void main(String[] args) {
        //Test of Utils

        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        System.out.println(data);
    }
}
