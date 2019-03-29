import javax.xml.crypto.Data;
import java.lang.reflect.Array;
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
        String data3 = Utils.readFileAsString("data/CrimeData2010.csv");
        String data4 = Utils.readFileAsString("data/CrimeData2001.csv");




        ArrayList <CrimeData2010> crimeData10 = Utils.parseCrimeData2010(data3);
        ArrayList <CrimeData2001> crimeData01 = Utils.parseCrimeData2001(data4);

        String csvData2 = Utils.saveDateToFile2(crimeData10, crimeData01);


        Utils.writeDataToFile("CSV2.csv",csvData2);


    }
}
