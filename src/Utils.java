import javax.xml.crypto.Data;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
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

    public static void writeDataToFile(String filePath, String data){
        File outfile = new File(filePath);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))){
            writer.write(data);

        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public static String saveDateToFile2 (ArrayList<CrimeData2010> CrimeData2010ist, ArrayList<CrimeData2001> CrimeData2001list){
        String data = "";
        int line = 1;

        data += "Crime Code Description,Latitude,Longitude,Primary type,Latitude,Longitude\n";


        for (int i = 0; i < CrimeData2001list.size(); i++){
            CrimeData2001 data2001 = CrimeData2001list.get(i);
            CrimeData2010 data2010 = CrimeData2010ist.get(i);

            data += data2010.getCrimeCodeDescription() + "," + data2010.getLatitude() + "," + data2010.getLongitude()
                    + "," + data2001.getPrimaryType() + "," + data2001.getLatitude() + "," + data2001.getLongitude()+
                    "\n";
            line ++;
            if (line > 70){
                return data;
            }

        }
        return data;
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
        String finalString = "";


        int indexOfFirstQuote = data.indexOf("\"");

        if (indexOfFirstQuote== -1){
            fields = data.split(",");
        }else {
            finalString = removeCommasFromDifference(data);
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
        difference = difference.trim();
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

            String[] fields = rows[i].split(",");

            String stateName = fields[1];
            //System.out.println (stateName);

            getEmployment2010Data(employmentData, "Los Angeles County CA");

            State state = new State(stateName);
            structure.addState(state);

            String countyName = fields[2];
            int fips = Integer.parseInt(fields[0]);

            double[] electionResults = getElection2016Data(electionData, countyName);
            Election2016 vote2016 = new Election2016(electionResults[0], electionResults[1], electionResults[2]);

//            double[] educationResults = getEducation2016Data(educationData, countyName);
//            Education2016 educ2016 = new Education2016(countyName, educationResults[0], educationResults[1], educationResults[2],
//                    educationResults[3]);


//            String[] employmentResults = getEmployment2016Data(employmentData, countyName);
//            Employment2016 employ2016 = new Employment2016(Integer.parseInt(employmentResults[0]),
//                    Integer.parseInt(employmentResults[1]), Integer.parseInt(employmentResults[2]),
//                    Double.parseDouble(employmentResults[3]));

 //           County c = new County(countyName, fips, vote2016, educ2016, employ2016);

 //           state.addCounty(c);


        }
        return structure;
    }

    private static Employment2016 getEmployment2010Data(String employmentData, String countyName) {
        String areaName; // 3
        double civilianLaborForce; // 18
        double employed2010; // 19
        double unemployed2010; // 20
        ArrayList<Employment2016> data = new ArrayList<>();
        Employment2016 point = null;

        String[] employmentInfo = new String[4];

        String[] rows = employmentData.split("\n");


        for (int i = 9; i < rows.length; i++) {
            String[] fields = removeQuoteFromRow(rows[i]);
            for (int k = 0; k < fields.length; k++) {
                fields[k] = fields[k].trim();
            }

            areaName = fields[2];

            if (areaName.equals(countyName)) {
                civilianLaborForce = Double.parseDouble(fields[17]);
                employed2010 = (Double.parseDouble(fields[18]));
                unemployed2010 = Double.parseDouble(fields[19]);

                System.out.println("Area Name " + areaName);
                System.out.println("Civilian Labor Force" + civilianLaborForce);
                System.out.println("employed 2010" + employed2010);
                System.out.println("unemployed 2010" + unemployed2010);

                point = new Employment2016(areaName, civilianLaborForce, employed2010, unemployed2010);

                
            }

        }
        
        return point;
    }

    private static Education2016 getEducation2016Data(String educationData, String countyName) {
        double noHighSchool;
        double onlyHighSchool;
        double someCollege;
        double bachelorsOrMore;

        double [] educationInfo = new double[4];
        Education2016 point = null;

        String [] rows = educationData.split("\n");

        for (int i = 6; i < 3288; i++) {
            String [] fields = removeQuoteFromRow (rows[i]);

            if (fields[2].equals(countyName)){

                noHighSchool = Double.parseDouble(fields[fields.length-8]);
                onlyHighSchool = Double.parseDouble(fields[fields.length-7]);
                someCollege = Double.parseDouble(fields[fields.length-6]);
                bachelorsOrMore = Double.parseDouble(fields[fields.length-5]);

                educationInfo [0] = noHighSchool;
                educationInfo [1] = onlyHighSchool;
                educationInfo [2] = someCollege;
                educationInfo [3] = bachelorsOrMore;

                System.out.println(countyName + noHighSchool + onlyHighSchool);

               point = new Education2016(countyName, noHighSchool, onlyHighSchool, someCollege, bachelorsOrMore);



            }
        }

        return point;

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


        }

        return votingInfo;

    }

    private static String removeCommasFromDifference(String data, int index) {
        String strWODifferenceB = "";
        String strWODifferenceE = "";

        int indexOfFirstQuote = index;
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


    private static String[] removeQuoteFromRow (String data) {
        String [] fields;
        String finalString = data;

        int indexOfFirstQuote = data.indexOf("\"");
        if (indexOfFirstQuote== -1){
            fields = data.split(",");
        }else {

            while (indexOfFirstQuote != -1) {
                finalString = removeCommasFromDifference(finalString,indexOfFirstQuote);
                indexOfFirstQuote = finalString.indexOf("\"");
            }
            fields = finalString.split(",");
        }
        return fields;
    }

    public static ArrayList<Education2016> parseEducationResults(String data1) {

        ArrayList <Education2016> results = new ArrayList<>();

        String [] rows = data1.split("\n");
        for (int i = 6; i < 3288; i++){

            String [] fields = removeQuoteFromRow (rows[i]);

            String areaName = fields[2];
            double noHighSchool = Double.parseDouble(fields[fields.length-8]);
            double onlyHighSchool = Double.parseDouble(fields[fields.length-7]);
            double someCollege = Double.parseDouble(fields[fields.length-6]);
            int bachelorsOrMore = Integer.parseInt(fields[fields.length-5]);

            Education2016 stat = new Education2016(areaName, noHighSchool, onlyHighSchool, someCollege, bachelorsOrMore);

            results.add(stat);
        }
        return results;
    }

    public static String[] separateCoordinates (String input){
        String [] parts = new String[2];
        int indexOfSpace=input.indexOf(" ");

        parts[0] = input.substring(0,indexOfSpace);
        parts[1] = input.substring(indexOfSpace+1);

        return parts;
    }

    public static ArrayList<CrimeData2010> parseCrimeData2010 (String data){
        ArrayList <CrimeData2010> results = new ArrayList<>();

        String [] rows = data.split("\n");

        for (int i = 2; i < rows.length; i++){
            String [] fields = removeQuoteFromRow(rows[i]);

            String crimeCodeDescription = fields[8];

            if (crimeCodeDescription.contains("BURGLARY") || crimeCodeDescription.contains("THEFT")) {
                String coordinates = fields[fields.length - 1];

                String[] longLat = separateCoordinates(coordinates.substring(1, coordinates.length() - 1));

                double latitude = Double.parseDouble(longLat[0]);
                double longitude = Double.parseDouble(longLat[1]);

                CrimeData2010 point = new CrimeData2010(crimeCodeDescription, longitude,latitude);
                results.add(point);
            }
        }

        return results;
    }

    public static ArrayList<CrimeData2001> parseCrimeData2001(String data){
        ArrayList<CrimeData2001> results = new ArrayList<>();

        String [] rows = data.split("\n");
        for(int i = 2; i< rows.length; i++){
            String [] fields = removeQuoteFromRow(rows[i]);

            String primaryType = fields[5];

            if(primaryType.contains("BURGLARY") || primaryType.contains("THEFT")){
                double longitude = Double.parseDouble(fields[fields.length-2]);
                double latitude = Double.parseDouble(fields[fields.length-3]);

                CrimeData2001 point = new CrimeData2001(primaryType, longitude, latitude);
                results.add(point);
            }


        }

        return results;
    }
}
