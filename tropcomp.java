import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class tropcomp{
    private List<String[]> data = new ArrayList<>();

    private static findSuspectClasses(String project_path, int seuil){
        Tls tls = new Tls(project_path);
        data = tls.explorelevel();

        int numToKeep = data.size() * (seuil / 100.0);

        // Sort the array of lists based on values at index 3 (integer value)
        data.sort(Comparator.comparing(item -> Integer.parseInt(item[3]), Comparator.reverseOrder()));

        // Filter the sorted list to include only values above threshold
        List<String[]> filteredListTloc = data.subList(0, numToKeep);

        // Sort the array of lists based on values at index 5 (double value)
        data.sort(Comparator.comparing(item -> Double.parseDouble(item[5]), Comparator.reverseOrder()));

        // Filter the sorted list to include only values above seuil for tcmp
        List<String[]> filteredListTcmp = data.subList(0, numToKeep);

        // Retain only the items inn common
        filteredListTloc.retainAll(filteredListTcmp);

        // Print the final filtered list
        for (String[] item : filteredListTloc) {
            System.out.println(Arrays.toString(item));
        }
    }
}








