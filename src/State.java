import java.util.ArrayList;
import java.util.List;

public class State {
    private String name;
    private List<County> counties;

    public State(String name) {
        this.name = name;
        counties = new ArrayList<County>();
    }

    public void addCounty (County c){
        counties.add(c);
    }

    public void removeCounty (County c){
        counties.remove(c);
    }

    public County removeCounty (int index){
        return counties.remove(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<County> getCounties() {
        return counties;
    }

    public void setCounties(List<County> counties) {
        this.counties = counties;
    }

}
