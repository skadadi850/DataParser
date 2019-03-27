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
        String data1 = Utils.readFileAsString("data/Education.csv");
        String data2 = Utils.readFileAsString("data/Unemployment.csv");
        String data3 = Utils.readFileAsString("data/Crime_Data_from_2010_to_Present.csv");
        String data4 = Utils.readFileAsString("data/Crime_-2001_to_present.csv");

//        ArrayList<ElectionResult> results = Utils.parse2016ElectionResults(data);
//
////         test toString method
//        for (int i = 0; i<results.size(); i++) {
//            System.out.println(results.get(i).toString());
//        }


//        ArrayList<Education2016> results1 = Utils.parseEducationResults(data1);
//
//        for (int i = 0; i < results1.size(); i++){
//            System.out.println(results1.get(i).getBachelorsOrMore());
//        }


      //  ArrayList <Employment2016> results2 = Utils.parseEmploymentResults(data2);

         Utils.parseCrimeData2010(data3);


//        DataManager structure = Utils.parseAllData(data,data1,data2);
//
//        System.out.println (structure.getStates().size());



    }
}
