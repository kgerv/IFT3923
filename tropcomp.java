import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class tropcomp{
    private List<String[]> data = new ArrayList<>();

    private static List<List<String>> findSuspectClasses(String project_path, int seuil){
        Tls tls = new Tls(project_path);
        data = tls.explorelevel();

        // Sort the array of lists based on values at index 3 (integer value)
        data.sort(Comparator.comparing(item -> Integer.parseInt(item[3]), Comparator.reverseOrder()));

        // Filter the sorted list to include only values above seuil for tloc
        List<String[]> filteredListTloc = new ArrayList<>();
        for (String[] item : data) {
            int tloc = Integer.parseInt(item[3]);
            if (tloc >= (seuil / 100.0 * tloc)) {
                filteredListTloc.add(item);
            }
        }

        // Sort the filtered list based on values at index 5 (double value)
        filteredListTloc.sort(Comparator.comparing(item -> Double.parseDouble(item[5]), Comparator.reverseOrder()));

        // Filter the sorted list to include only values above seuil for tcmp
        List<String[]> filteredListTcmp = new ArrayList<>();
        for (String[] item : filteredListIndex3) {
            double tcmp = Double.parseDouble(item[5]);
            if (tcmp >= (seuil / 100.0 * tcmp)) {
                filteredListTcmp.add(item);
            }
        }
        // Print the final filtered list
        for (String[] item : filteredListIndex5) {
            System.out.println(Arrays.toString(item));
        }
    }
}








