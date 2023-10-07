import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tropcomp {
    private File pathIn;
    private int threshold;
    private List<List<String>> data;
    private List<List<String>> tropcompValues;

    public Tropcomp(File path, int threshold) {
        this.pathIn = path;
        this.threshold = threshold;
        this.data = new ArrayList<>();
        this.tropcompValues = new ArrayList<>();
    }

    // empty constructor
    public Tropcomp() {}

    public List<List<String>> findSuspectClasses() {
        Tls tls = new Tls(this.pathIn);
        this.data.addAll(tls.exploreLevel());
        List<List<String>> dataCopy = new ArrayList<>(this.data);
        double supThreshold = this.data.size() * (this.threshold / 100.0);
        int numToKeep = supThreshold - (int)supThreshold > 0.5 ? 1 + (int)supThreshold : (int)supThreshold ;

        // Sort the array of lists based on values at index 3 (integer value)
        this.reverseSort(this.data, 0, this.data.size() - 1, 3);
        // Filter the sorted list to include only the elements with tloc values above threshold
        List<List<String>> filteredListTloc = this.data.subList(0, numToKeep);
        // Sort the array of lists based on values at index 5 (float value)
        this.reverseSort(dataCopy, 0, this.data.size() - 1, 5);
        // Filter the sorted list to include only the element with tcmp values above threshold
        List<List<String>> filteredListTcmp = dataCopy.subList(0, numToKeep);

        // Retain only the items in common
        if(!filteredListTloc.isEmpty() && !filteredListTcmp.isEmpty()) {
            for(List<String> tlocElement : filteredListTloc) {
                for(List<String> tcmpElement : filteredListTcmp) {
                    String tlocPath = tlocElement.get(0);
                    String tcmpPath = tcmpElement.get(0);

                    if(tlocPath.compareTo(tcmpPath) == 0) this.tropcompValues.add(tlocElement);
                }
            }
        }
        return tropcompValues;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(); // initialize empty String
        for(List<String> lineContent : this.tropcompValues) {
            for(int i = 0; i < lineContent.size(); i++) {
                output.append(lineContent.get(i));
                if(i != lineContent.size() - 1) output.append(", ");
            }
            output.append("\n");
        }

        return output.toString();
    }

    // implementations based on: https://www.geeksforgeeks.org/merge-sort/
    // Uses merge-sort to sort this.data in descending order according to the
    // "idx" elements values of the inner lists, which are integer in this context
    private void reverseSort(List<List<String>> list, int l, int r, int idx) {
        if(l < r) {
            int m = l + (r - l) / 2;

            reverseSort(list, l, m, idx);
            reverseSort(list, m + 1, r, idx);

            merge(list, l, m, r, idx);
        }
    }

    private void merge(List<List<String>> list, int l, int m, int r, int idx) {
        int n1 = m - l + 1;
        int n2 = r - m;

        List<List<String>> left = new ArrayList<>(n1);
        List<List<String>> right = new ArrayList<>(n2);

        for(int i = 0; i < n1; i++) left.add(list.get(l + i));
        for(int j = 0; j < n2; j++) right.add(list.get(m + 1 + j));

        int i = 0, j = 0;

        int k = l;
        while(i < n1 && j < n2) {
            float leftValue = Float.parseFloat(left.get(i).get(idx));
            float rightValue = Float.parseFloat(right.get(j).get(idx));

            if(leftValue > rightValue || leftValue == -1) {
                list.set(k, left.get(i));
                i++;
            } else {
                list.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while(i < n1) {
            list.set(k, left.get(i));
            i++;
            k++;
        }

        while(j < n2) {
            list.set(k, right.get(j));
            j++;
            k++;
        }
    }
}
