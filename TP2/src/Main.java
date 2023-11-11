import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int ageBoundary = 7;

        File f = new File("src\\test");
        List<List<String>> outdatedTests = new ArrayList<>();
        List<List<String>> ageList = new ArrayList<>();
        Stack<File> fileStack = new Stack<>();

        if(args.length > 0) {
            ageBoundary = Integer.parseInt(args[0]);
        }

        if(f.listFiles() != null) {
            fileStack.addAll(Arrays.asList(f.listFiles()));
            // go through every test file, get the age metric and put it in a list
            while (!fileStack.isEmpty()) {
                File current = fileStack.pop();
                if(current.isDirectory() && current.listFiles() != null) {
                    fileStack.addAll(Arrays.asList(current.listFiles()));
                }

                if(!current.getName().contains(".java")) continue;

                Age a = new Age(current);
                List<String> infos = a.calculateAgeDifference();
                if(!ageList.contains(infos))
                    ageList.add(infos);
                if(Integer.parseInt(infos.get(0)) > ageBoundary || Integer.parseInt(infos.get(0)) == -1) {
                    outdatedTests.add(infos);
                }
            }

            if(args.length == 2) {
                CreateCSV.convertToCSV(new File(args[1]), outdatedTests);
            }

            for(List<String> l: outdatedTests) {
                System.out.println(l);
            }
        } else {
            System.out.println("File not found");
        }
    }
}
