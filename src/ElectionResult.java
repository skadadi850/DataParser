public class ElectionResult {
    int DemVotes, GOPVotes, totalVotes, perDem, perGOP, difference, perPointDiff, stateAbbr, countyName, fips;

    public ElectionResult (int DV, int GOPV, int total_votes, int per_dem, int per_gop, int diff, int per_point_diff, int state_abbr, int county_name, int combined_fips){
        this.DemVotes = DV;
        this.GOPVotes = GOPV;
        this.totalVotes = total_votes;
        this.perDem = per_dem;
        this.perGOP = per_gop;
        this.difference = diff;
        this.perPointDiff = per_point_diff;
        this.stateAbbr = state_abbr;
        this.countyName = county_name;
        this.fips = combined_fips;
    }

    public int getDemVotes() {
        return DemVotes;
    }

    public void setDemVotes(int demVotes) {
        DemVotes = demVotes;
    }

    public int getGOPVotes() {
        return GOPVotes;
    }

    public void setGOPVotes(int GOPVotes) {
        this.GOPVotes = GOPVotes;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public int getPerDem() {
        return perDem;
    }

    public void setPerDem(int perDem) {
        this.perDem = perDem;
    }

    public int getPerGOP() {
        return perGOP;
    }

    public void setPerGOP(int perGOP) {
        this.perGOP = perGOP;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public int getPerPointDiff() {
        return perPointDiff;
    }

    public void setPerPointDiff(int perPointDiff) {
        this.perPointDiff = perPointDiff;
    }

    public int getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(int stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public int getCountyName() {
        return countyName;
    }

    public void setCountyName(int countyName) {
        this.countyName = countyName;
    }

    public int getFips() {
        return fips;
    }

    public void setFips(int fips) {
        this.fips = fips;
    }

    public String toString (){
        return (DemVotes + ", " + GOPVotes + ", " + totalVotes + ", " + perDem + ", " + perGOP + ", "
                + difference + ", " + perPointDiff + ", " + stateAbbr + "," + countyName + "," + fips);

    }


}
