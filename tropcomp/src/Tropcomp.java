import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Tropcomp {
    private String path;
    private int threshold;
    private List<List<String>> data;

    public Tropcomp(String path, int threshold) {
        this.path = path;
        this.threshold = threshold;
        this.data = new ArrayList<>();
    }

    public List<List<String>> findSuspectClasses() {
        Tls tls = new Tls(this.path);
        this.data.addAll(tls.exploreLevel());
        System.out.println(this.toString());
        int numToKeep = (int)(this.data.size() * (this.threshold / 100.0));

        // Sort the array of lists based on values at index 3 (integer value)
        this.data.sort(Comparator.comparing(item -> Integer.parseInt(item.get(3)), Comparator.reverseOrder()));

        // Filter the sorted list to include only the elements with tloc values above threshold
        List<List<String>> filteredListTloc = this.data.subList(0, numToKeep);
        System.out.println(filteredListTloc.toString());

        // Sort the array of lists based on values at index 5 (double value)
        data.sort(Comparator.comparing(item -> Double.parseDouble(item.get(5)), Comparator.reverseOrder()));

        // Filter the sorted list to include only the element with tcmp values above threshold
        List<List<String>> filteredListTcmp = this.data.subList(0, numToKeep);
        System.out.println(filteredListTcmp.toString());

        // Retain only the items inn common
        filteredListTloc.retainAll(filteredListTcmp);
        this.data.clear();
        this.data.addAll(filteredListTloc);

        return data;
    }

    @Override
    public String toString() {
        String output = ""; // initialize empty String
        for(List<String> lineContent : this.data) {
            for(int i = 0; i < lineContent.size(); i++) {
                output += lineContent.get(i);
                if(i != lineContent.size() - 1) output += ", ";
            }
            output += "\n";
        }

        return output;
    }
}
