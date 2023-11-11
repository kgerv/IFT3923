import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Age {
    private File testFile;
    private String testFileName;
    private long testFileAge;

    public Age(File testFile) {
        this.testFile = testFile;
        this.testFileName = testFile.getName();
        this.testFileAge = testFile.lastModified();
    }

    // returns the difference between the last modification of a file and its test file in days
    public List<String> calculateAgeDifference() {
        List<String> infos = new ArrayList<>(2);
        int days = 0;
        long diff, lastModifTest = this.testFile.lastModified();
        String pkgName = this.filePackageName();
        // remove the "Test" part of the test file name to get the name of the class tested;
        // assumes test files are named after the class they are testing
        String fileTestedName = this.testFileName.replace("Test","");
        File fileTested;


        fileTested = this.getFile(fileTestedName, pkgName);
        if(fileTested != null) {
            diff = fileTested.lastModified() - lastModifTest;
            days = (int) (diff / 8.64e+7);
            infos.add(String.valueOf(days));
        } else {
            infos.add(String.valueOf(-1));
        }
        infos.add(fileTestedName);
        return infos;
    }

    private File getFile(String fileName, String pkgName) {
        File file = new File(fileName);
        String osType = System.getProperty("os.name").toLowerCase();
        String path = osType.contains("windows") ?
                "C:\\Users\\Killian\\Desktop\\jfreechart-master\\"+"src\\main\\java\\" :
                "C:\\Users\\Killian\\Desktop\\jfreechart-master\\"+"src/main/java/";

        if(!pkgName.isEmpty()) {
            path = path.concat(pkgName + fileName);
            file = new File(path);
            if(!file.exists()) return null;
        }

        // file not found in jfreechart-master directory
        if(!file.exists()) {
            File dir = new File(file.getPath().replace(fileName, ""));
            file = this.exploreLevel(file, dir);
        }

        return file;
    }

    private String filePackageName() {
        String absoluteFilePath, packageName = "";
        int lastIdxSeparator, packNameStart;

        absoluteFilePath = this.testFile.getPath();
        packNameStart = absoluteFilePath.indexOf("java") + 5;
        lastIdxSeparator = absoluteFilePath.lastIndexOf("/");
        if(lastIdxSeparator < 0) lastIdxSeparator = absoluteFilePath.lastIndexOf("\\");
        if(packNameStart < lastIdxSeparator) // no package when this is false
            packageName = absoluteFilePath.substring(packNameStart, lastIdxSeparator + 1);

        return packageName;
    }

    // explore current directory level and look for test files
    // uses recursion to go into directory inside the current one
    private File exploreLevel(File fileWanted, File currentDir) {
        if(!currentDir.exists()) {
            return null;
        }
        File[] files = currentDir.listFiles();
        boolean containsSrc = true;
        boolean containsTest = true;
        boolean containsJava = true;

        // not currently in "src" directory or lower
        if(!currentDir.getAbsolutePath().matches(".*\\Wsrc.*")) {
            // check if current directory contains "src" directory
            for(File f : files) {
                if(f.getName().compareTo("src") == 0) {
                    return this.exploreLevel(fileWanted, f);
                }
            }
            containsSrc = false;
        }
        // not currently in "main" repository or lower
        if(containsSrc && !currentDir.getAbsolutePath().matches(".*\\Wmain.*")) {
            // check if current directory contains "test" directory
            for(File f : files) {
                if(f.getName().compareTo("main") == 0) {
                    return this.exploreLevel(fileWanted, f);
                }
            }
            containsTest = false;
        }
        // not currently in "java" repository or lower
        if(containsSrc && containsTest && !currentDir.getAbsolutePath().matches(".*\\Wjava.*")) {
            // check if current directory contains "test" directory
            for(File f : files) {
                if(f.getName().compareTo("java") == 0) {
                    return this.exploreLevel(fileWanted, f);
                }
            }
            containsJava = false;
        }
        // directory does not contains java file directory following Java & Maven format norms
        if(!containsJava) return null;

        for(File f : files) {

            if(f.isDirectory()) {
                return this.exploreLevel(fileWanted, f);
            }

            if(f.getName().compareTo(fileWanted.getName()) == 0) {
                return f;
            }
        }

        return null;
    }
}
