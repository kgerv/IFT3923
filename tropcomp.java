import java.io.File;
import java.util.List;

public class tropcomp{
    public static void main(String[] args){
        String project_path = args[0];
        int seuil = Integer.parseInt(args[1]);

        List<String> suspectClasses = findSuspectClasses(project_path, seuil);

        for (String className : suspectClasses) {
            System.out.println(className);
        }
    }

    private Tloc tloc = new Tloc();
    private Tassert tassert = new Tassert();

    private static List<String> findSuspectClasses(String project_path, int seuil){
        List<String> suspectClasses = new ArrayList<>();
        File project = new File(project_path);
        int tlocValue, tassertValue;
        try {
            File[] files = project.listFiles();
            for (File file : files){
                tlocValue = tloc.calculate(file.getAbsolutePath());
                tassertValue = tassert.calculate(file.getAbsolutePath());
                if (tlocValue > seuil && tassertValue > seuil){
                    suspectClasses += file.getClass().getName();
                }

            }
        } catch{}
        return suspectClasses;
    }
}