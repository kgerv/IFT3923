import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tropcomp {
    private File path;
    private int threshold;
    private List<List<String>> data;
    private List<List<String>> tropcompValues;

    public Tropcomp(File path, int threshold) {
        this.path = path;
        this.threshold = threshold;
        this.data = new ArrayList<>();
        this.tropcompValues = new ArrayList<>();
    }

    public List<List<String>> findSuspectClasses() {
        Tls tls = new Tls(this.path);
        this.data.addAll(tls.exploreLevel());
        int numToKeep = (int)(this.data.size() * (this.threshold / 100.0));

        // Sort the array of lists based on values at index 3 (integer value)
        //this.data.sort(Comparator.comparing(item -> Integer.parseInt(item.get(3)), Comparator.reverseOrder()));
        this.reverseSort(0, this.data.size() - 1, 3);
        // Filter the sorted list to include only the elements with tloc values above threshold
        List<List<String>> filteredListTloc = this.data.subList(0, numToKeep);

        // Sort the array of lists based on values at index 5 (double value)
        //this.data.sort(Comparator.comparing(item -> Double.parseDouble(item.get(5)), Comparator.reverseOrder()));
        this.reverseSort(0, this.data.size() - 1, 5);
        // Filter the sorted list to include only the element with tcmp values above threshold
        //List<List<String>> filteredListTcmp = this.data.subList(0, numToKeep);
        List<List<String>> filteredListTcmp = this.data.subList(0, numToKeep);

        // Retain only the items inn common
        //filteredListTloc.retainAll(filteredListTcmp); Seems to not work
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
        for(List<String> lineContent : this.data) {
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
    public  void reverseSort(int l, int r, int idx) {
        if(l < r) {
            int m = l + (r - l) / 2;

            reverseSort(l, m, idx);
            reverseSort(m + 1, r, idx);

            merge(l, m, r, idx);
        }
    }

    private void merge(int l, int m, int r, int idx) {
        int n1 = m - l + 1;
        int n2 = r - m;

        List<List<String>> left = new ArrayList<>(n1);
        List<List<String>> right = new ArrayList<>(n2);

        for(int i = 0; i < n1; i++) left.add(this.data.get(l + i));
        for(int j = 0; j < n2; j++) right.add(this.data.get(m + 1 + j));

        int i = 0, j = 0;

        int k = l;
        while(i < n1 && j < n2) {
            int leftValue = Integer.parseInt(left.get(i).get(idx));
            int rightValue = Integer.parseInt(right.get(j).get(idx));
            if(leftValue > rightValue || leftValue == -1) {
                this.data.set(k, left.get(i));
                i++;
            } else {
                this.data.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while(i < n1) {
            this.data.set(k, left.get(i));
            i++;
            k++;
        }

        while(j < n2) {
            this.data.set(k, right.get(j));
            j++;
            k++;
        }
    }
}
