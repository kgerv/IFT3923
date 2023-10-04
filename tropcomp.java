import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class tropcomp{
    private List<String[]> data = new ArrayList<>();

    private static List<List<String>> findSuspectClasses(String project_path, int seuil){
        Tls tls = new Tls(project_path);
        data = tls.explorelevel();
        data.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] v1, String[] v2) {
                // Parse values at index 3 as integers
                int tloc = Integer.parseInt(v1[3]);
                int tloc2 = Integer.parseInt(v2[3]);

                // Compare based on index 3 (integer value)
                int compareResult = Integer.compare(tloc2, tloc); // Descending order

                if (compareResult == 0) {
                    // If index 3 values are equal, compare based on index 5 (double value)
                    double tcmp1 = Double.parseDouble(v1[5]);
                    double tcmp2 = Double.parseDouble(v2[5]);
                    return Double.compare(tcmp2, tcmp1); // Descending order
                }

                return compareResult;
            }
        });



        // Print the sorted data
        for (String[] item : data) {
            System.out.println(Arrays.toString(item));
        }
    }
}








