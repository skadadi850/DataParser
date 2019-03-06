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

            String countyData = rows [i];
            String [] fields = parseDifference (countyData);

            for (int j = 0; j < fields.length; j++){
                fields[j] = fields[j].trim();
            }

            double demVotes = Double.parseDouble(fields[1]);
            double gopVotes = Double.parseDouble(fields[2]);
            double totalVotes = Double.parseDouble(fields[3]);
            double perDem = Double.parseDouble(fields[4]);
            double perGop = Double.parseDouble(fields[5]);
            double difference = Double.parseDouble(fields[6]);
            double perPointDifference = Double.parseDouble(fields[7].substring(0,fields[7].length()-1));
            String stateAbbrv = fields[8];
            String countyName = fields[9];
            int fips = Integer.parseInt(fields[10]);

            ElectionResult newCounty = new ElectionResult(demVotes, gopVotes, totalVotes, perDem, perGop, difference,
                    perPointDifference, stateAbbrv, countyName, fips);
            //System.out.println(newCounty.toString());

            results.add(newCounty);
        }
        return results;
    }

    private static String[] parseDifference(String data) {
        String [] fields;
        String strWODifferenceB = "";
        String strWODifferenceE = "";
        String finalString = "";

        int indexOfFirstQuote = data.indexOf("\"");
        int indexOfSecondQuote = data.indexOf("\"", indexOfFirstQuote+1);

        if (indexOfFirstQuote== -1){
            fields = data.split(",");
        }else {
            String difference = data.substring(indexOfFirstQuote+1,indexOfSecondQuote);
            strWODifferenceB = data.substring(0,indexOfFirstQuote);
            strWODifferenceE = data.substring(indexOfSecondQuote+1);

            int indexOfCommaInQuote = difference.indexOf(",");

            while (indexOfCommaInQuote != -1) {
                difference = difference.substring(0,indexOfCommaInQuote) + difference.substring(indexOfCommaInQuote+1);
                indexOfCommaInQuote = difference.indexOf(",");
            }

            finalString = strWODifferenceB + difference + strWODifferenceE;

            fields = finalString.split(",");
        }
        return fields;
    }


}
