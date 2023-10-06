import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Tls {
    private File dirPath;
    private List<List<String>> tlsValues;
    private Tloc tloc = new Tloc();
    private Tassert tassert = new Tassert();

    public Tls(File path) {
        this.dirPath = path;
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
    public List<List<String>> exploreLevel() {
        if(!this.dirPath.exists()) {
            System.out.println("The system cannot find the path specified");
            return tlsValues;
        }
        File[] files = this.dirPath.listFiles();
        boolean filesIsEmpty = files == null;
        boolean containsSrc = true;
        boolean containsTest = true;
        boolean containsJava = true;

        // path to an empty directory
        //if(files == null) return this.tlsValues;

        // not currently in "src" directory or lower
        if(!this.dirPath.getAbsolutePath().matches(".*\\Wsrc.*")) {
            // check if current directory contains "src" directory
            for(File f : files) {
                if(f.getName().compareTo("src") == 0) {
                    Tls tls = new Tls(f);
                    this.tlsValues = tls.exploreLevel();
                    return this.tlsValues;
                }
            }
            containsSrc = false;
        }
        // not currently in "test" repository or lower
        if(containsSrc && !this.dirPath.getAbsolutePath().matches(".*\\Wtest.*")) {
            // check if current directory contains "test" directory
            for(File f : files) {
                if(f.getName().compareTo("test") == 0) {
                    Tls tls = new Tls(f);
                    this.tlsValues = tls.exploreLevel();
                    return this.tlsValues;
                }
            }
            containsTest = false;
        }
        // not currently in "java" repository or lower
        if(containsSrc && containsTest && !this.dirPath.getAbsolutePath().matches(".*\\Wjava.*")) {
            // check if current directory contains "test" directory
            for(File f : files) {
                if(f.getName().compareTo("java") == 0) {
                    Tls tls = new Tls(f);
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
                Tls tls = new Tls(f);
                this.tlsValues.addAll(tls.exploreLevel());
            }
            // not a java file
            if(!filePath.endsWith(".java")) continue;
            // is a non-test java file
            if(!filePath.matches(".*\\W(Test)([A-Z]\\w*)+.java") &&
                    !filePath.matches(".*\\W([A-Z]\\w*)+(Test).java")) continue;

            List<String> tlsValuesEntry = new ArrayList<>();
            String absoluteFilePath, packageName = "", className;
            boolean packNameExtracted = false;
            int tlocValue, tassertValue, packNameStart;
            float tcmpValue;
            int fileExtensionIdx = filePath.indexOf(".java");
            // Unix system
            int lastIdxSeparator = filePath.lastIndexOf("/");
            // Windows system
            if(lastIdxSeparator < 0) lastIdxSeparator = filePath.lastIndexOf("\\");

            absoluteFilePath = f.getAbsolutePath();
            className = filePath.substring(lastIdxSeparator + 1, fileExtensionIdx);
            tlocValue = tloc.calculate(f);
            tassertValue = tassert.calculate(f);
            // is tassertValue is null we return -1, as to not have Infinity case
            tcmpValue = tassertValue > 0 ? (float)tlocValue / (float)tassertValue : -1;
            // package name start after ".*/test/java/"
            packNameStart = absoluteFilePath.indexOf("java") + 5;
            lastIdxSeparator = absoluteFilePath.lastIndexOf("/");
            if(lastIdxSeparator < 0) lastIdxSeparator = absoluteFilePath.lastIndexOf("\\");
            if(packNameStart < lastIdxSeparator) // no package when this is false
                packageName = absoluteFilePath.substring(packNameStart, lastIdxSeparator);

            // add the values to the List<String> tlsValuesEntry and then to tlsValues
            tlsValuesEntry.add(absoluteFilePath);
            tlsValuesEntry.add(packageName);
            tlsValuesEntry.add(className);
            tlsValuesEntry.add(String.valueOf(tlocValue));
            tlsValuesEntry.add(String.valueOf(tassertValue));
            tlsValuesEntry.add(String.valueOf(tcmpValue));
            tlsValues.add(tlsValuesEntry);
        }

        return this.tlsValues;
    }

    @Override
    public String toString() {
        String output = ""; // initialize empty String
        for(List<String> lineContent : this.tlsValues) {
            for(int i = 0; i < lineContent.size(); i++) {
                output += lineContent.get(i);
                if(i != lineContent.size() - 1) output += ", ";
            }
            output += "\n";
        }
        
        return output;
    }

}
