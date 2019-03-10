import javax.xml.crypto.Data;
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

            results.add(newCounty);
        }
        return results;
    }



    private static String[] parseDifference(String data) {
        String [] fields;
        String finalString;

        int indexOfFirstQuote = data.indexOf("\"");

        if (indexOfFirstQuote== -1){
            fields = data.split(",");
        }else {

            finalString = removeCommasFromDifference (data);
            fields = finalString.split(",");
        }
        return fields;
    }

    private static String removeCommasFromDifference(String data) {
        String strWODifferenceB = "";
        String strWODifferenceE = "";

        int indexOfFirstQuote = data.indexOf("\"");
        int indexOfSecondQuote = data.indexOf("\"", indexOfFirstQuote+1);

        String difference = data.substring(indexOfFirstQuote+1,indexOfSecondQuote);
        strWODifferenceB = data.substring(0,indexOfFirstQuote);
        strWODifferenceE = data.substring(indexOfSecondQuote+1);

        int indexOfCommaInQuote = difference.indexOf(",");

        while (indexOfCommaInQuote != -1) {
            difference = difference.substring(0,indexOfCommaInQuote) + difference.substring(indexOfCommaInQuote+1);
            indexOfCommaInQuote = difference.indexOf(",");
        }

        return (strWODifferenceB + difference + strWODifferenceE);
    }


    public static DataManager parseAllData (String electionData, String educationData, String employmentData) {
        DataManager structure = new DataManager();

        String[] rows = educationData.split("\n");

        for (int i = 6; i < 3288; i++) {
            String[] fields = educationData.split(",");

            String stateName = fields[1];
            boolean stateExists = false;
            for (int j = 0; j < fields.length; j++) {
                if (fields[j].equals(stateName)) {
                    stateExists = true;
                }
            }
            if (stateExists == false) {
                State state = new State(stateName);
                structure.addState(state);
            }

            String countyName = fields[2];
            int fips = Integer.parseInt(fields[0]);

            double [] electionResults = getElection2016Data(electionData, countyName);
            Election2016 vote2016 = new Election2016(electionResults[0], electionResults[1], electionResults[2]);

            double [] educationResults = getEducation2016Data(educationData, countyName);
            Education2016 educ2016 = new Education2016(educationResults[0], educationResults[1], educationResults[2],
                    educationResults[3]);
            

            String [] employmentResults = getEmployment2016Data(employmentData, countyName);
            Employment2016 employ2016 = new Employment2016();


        }
        return structure;
    }

    private static double[] getEducation2016Data(String educationData, String countyName) {
        double noHighSchool;
        double onlyHighSchool;
        double someCollege;
        double bachelorsOrMore;

        double [] educationInfo = new double[4];

        String [] rows = educationData.split("\n");

        for (int i = 6; i < rows.length; i++) {
            String eduRow = rows[i];
            String [] fields = parseDifference(eduRow);

            if (fields[2].equals(countyName)){
                noHighSchool = Double.parseDouble(fields[8]);
                onlyHighSchool = Double.parseDouble(fields[9]);
                someCollege = Double.parseDouble(fields[10]);
                bachelorsOrMore = Double.parseDouble(fields[11]);

                educationInfo [0] = noHighSchool;
                educationInfo [1] = onlyHighSchool;
                educationInfo [2] = someCollege;
                educationInfo [3] = bachelorsOrMore;
            }
        }
        return educationInfo;

    }

    private static double[] getElection2016Data(String electionData, String countyName) {
        String demVotes;
        String gopVotes;
        String totalVotes;

        double [] votingInfo = new double[3];

        String[] rows = electionData.split("\n");

        for (int i = 2; i < rows.length; i++) {
            String countyData = rows [i];
            String [] fields = parseDifference (countyData);

            if (fields[fields.length-2].equals(countyName)){
                demVotes = (fields[1]);
                gopVotes = (fields[2]);
                totalVotes = (fields[3]);

                votingInfo[0] = Double.parseDouble(demVotes);
                votingInfo[1] = Double.parseDouble(gopVotes);
                votingInfo[2] = Double.parseDouble(totalVotes);
            }

            return votingInfo;
        }



    }


}
