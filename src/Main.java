import javax.xml.crypto.Data;
import java.util.ArrayList;

/***
 * Main class for data parsers
 * @author Simran Kadadi
 */
public class Main {
    public static void main(String[] args) {
        //Test of Utils

        String data = Utils.readFileAsString("data/2016_Presidential_Results.csv");
        ArrayList<ElectionResult> results = Utils.parse2016ElectionResults(data);

        // test toString method
        for (int i = 0; i<results.size(); i++) {
            System.out.println(results.get(i).toString());
        }

    }
}
