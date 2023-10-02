import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tls {
    private File dirPath;
    private List<String[]> tlsValues; //
    private Tloc tloc = new Tloc();
    private Tassert tassert = new Tassert();

    public Tls(String path) {
        this.dirPath = new File(path);
        this.tlsValues = new ArrayList<>();
        /*
        String filepath, packetname, classname;
        File directory = new File(path);
        try{
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".java")) {
                            filepath = file.getPath();
                            packetname = file.getClass().getPackageName();
                            classname = file.getClass().getName();
                        }
                    }
                }
            }
        } catch (Error e) {}
*/
    }

    // explore current directory level and look for test files
    // uses recursion to go into directory inside the current one
    public List<String[]> exploreLevel() {
        File[] files = this.dirPath.listFiles();
        boolean containsSrc = true;
        boolean containsTest = true;
        boolean containsJava = true;

        // directory is empty
        if(files == null) return this.tlsValues;

        // not currently in "src" directory or lower
        if(!this.dirPath.getAbsolutePath().matches(".*\\Wsrc.*")) {
            // check if current directory contains "src" directory
            for(File f : files) {
                if(f.getName().compareTo("src") == 0) {
                    Tls tls = new Tls(f.getPath());
                    this.tlsValues = tls.exploreLevel();
                    return this.tlsValues;
                }
            }
            containsSrc = false;
        }
        // not currently in "test" repository or lower
        if(containsSrc && !this.dirPath.getAbsolutePath().matches("\\Wtest*")) {
            // check if current directory contains "test" directory
            for(File f : files) {
                if(f.getName().compareTo("test") == 0) {
                    Tls tls = new Tls(f.getPath());
                    this.tlsValues = tls.exploreLevel();
                    return this.tlsValues;
                }
            }
            containsTest = false;
        }
        // not currently in "java" repository or lower
        if(containsSrc && containsTest && !this.dirPath.getAbsolutePath().matches("\\Wjava*")) {
            // check if current directory contains "test" directory
            for(File f : files) {
                if(f.getName().compareTo("test") == 0) {
                    Tls tls = new Tls(f.getPath());
                    this.tlsValues = tls.exploreLevel();
                    return this.tlsValues;
                }
            }
            containsJava = false;
        }
        // directory does not contains java test file directory following Java & Maven format norms
        if(!containsJava) return this.tlsValues;

        for(File f : files) {
            String filePath = f.getPath(); // relative path of the file

            // file has no extension, it is a directory; explore it and add created entries to tlsValues
            if(!filePath.matches(".*\\..*")) {
                Tls tls = new Tls(filePath);
                this.tlsValues.addAll(tls.exploreLevel());
                return this.tlsValues;
            }
            // not a java file
            if(!filePath.endsWith(".java")) continue;
            // is a non-test java file
            if(!filePath.matches(".*\\W(Test)([A-Z]\\w*)+") ||
                    !filePath.matches(".*\\W([A-Z]\\w*)+(Test)")) continue;

            String[] tlsValuesEntry;
            String absoluteFilePath, packageName, className, tlocString, tasserString, tcmpString;
            int tlocValue, tassertValue;
            float tcmpValue;
            int fileExtensionIdx = filePath.indexOf(".java");
            // Unix system
            int lastIdxSeparator = filePath.lastIndexOf("/");
            // Windows system
            if(lastIdxSeparator < 0 ) lastIdxSeparator = filePath.lastIndexOf("\\");


            packageName = "";
            className = filePath.substring(lastIdxSeparator + 1, fileExtensionIdx);
            absoluteFilePath = f.getAbsolutePath();
            tlocValue = tloc.calculate(absoluteFilePath);
            tlocString = String.valueOf(tlocValue);
            tassertValue = tassert.calculate(absoluteFilePath);
            tasserString = String.valueOf(tassertValue);
            tcmpValue = (float)tlocValue / (float)tassertValue;
            tcmpString = String.valueOf(tcmpValue);

            tlsValuesEntry = new String[]{filePath, packageName, className, tlocString, tasserString, tcmpString};
            tlsValues.add(tlsValuesEntry);
        }

        return this.tlsValues;
    }



    @Override
    public String toString() {
        String output = ""; // initialize empty String
        for (String[] lineContent : this.tlsValues) {
            for(int i = 0; i < lineContent.length; i++) {
                output += lineContent;
                if(i != lineContent.length - 1) output += ", ";
            }
            output += "\n";
        }
        
        return output;
    }


}
