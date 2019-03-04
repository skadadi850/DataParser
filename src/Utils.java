import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    public static String readFileAsString(String filepath) {
        StringBuilder output = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    public static ArrayList<ElectionResult> parse2016ElectionResults(String data) {
        ArrayList <ElectionResult> results = new ArrayList<>();

        String [] rows = data.split("\n");

        for (int i = 1; i < rows.length; i++){

            int indexOfCommaInQuotes = rows[i].indexOf(",",rows[i].indexOf("\""));
            String[] fields;

            if (indexOfCommaInQuotes == -1){
                fields = rows[i].split(",");
            }else {
                String strWOComma = rows[i].substring(0, indexOfCommaInQuotes) + rows[i].substring(indexOfCommaInQuotes + 1);
                fields = strWOComma.split(",");
            }

            double demVotes = Double.parseDouble(fields[1]);
            double gopVotes = Double.parseDouble(fields[2]);
            double totalVotes = Double.parseDouble(fields[3]);
            double perDem = Double.parseDouble(fields[4]);
            double perGop = Double.parseDouble(fields[5]);
            String difference = fields[6];
            double perPointDifference = Double.parseDouble(fields[7].substring(0,fields[7].length()-1));
            System.out.println (perPointDifference);
            String stateAbbrv = fields[8];
            String countyName = fields[9];
            int fips = Integer.parseInt(fields[10]);

            ElectionResult newCounty = new ElectionResult(demVotes, gopVotes, totalVotes, perDem, perGop, difference,
                    perPointDifference, stateAbbrv, countyName, fips);

            results.add(newCounty);
        }
        return results;
    }
}
