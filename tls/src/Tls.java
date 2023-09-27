import java.util.ArrayList;
import java.util.List;

public class Tls {
    private String repo_path;
    private List<String[]> lines; // holds the content

    public Tls(String path) {
        this.repo_path = path;
        this.lines = new ArrayList<>();
    }

    public void explorePath() {
    }

    @Override
    public String toString() {
        String output = ""; // initialize empty String
        for (String[] line_content : this.lines) {
            for(int i = 0; i < line_content.length; i++) {
                output += line_content;
                if(i != line_content.length - 1) output += ", ";
            }
            output += "\n";
        }
        
        return super.toString();
    }


}
