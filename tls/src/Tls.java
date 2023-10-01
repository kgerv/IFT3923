import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tls {
    private String repo_path;
    private List<String[]> tls_values; //

    public Tls(String path) {
        this.repo_path = path;
        this.tls_values = new ArrayList<>();
        String filepath, packetname, classname;
        File directory = new File(path);
        try{
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".java")) {
                            filepath = file.getAbsolutePath();
                            packetname = file.getClass().getPackageName();
                            classname = file.getClass().getName();
                        }
                    }
                }
            }
        } catch ()

    }

    public void explorePath() {
        String file_path, package_name, class_name;
        int tloc_value, tassert_value, tcmp_value;

        // path to a file
        if(this.repo_path.matches(".*\\w\\.java")) {
            
        }
    }

    @Override
    public String toString() {
        String output = ""; // initialize empty String
        for (String[] line_content : this.tls_values) {
            for(int i = 0; i < line_content.length; i++) {
                output += line_content;
                if(i != line_content.length - 1) output += ", ";
            }
            output += "\n";
        }
        
        return super.toString();
    }


}
