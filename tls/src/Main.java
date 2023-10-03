import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<List<String>> tlsValues;
        //String file_path = args[0];
        String file_path = "D:\\Users\\Killian\\Documents\\GitHub\\projetcompost\\";
        Tls tls = new Tls(file_path);
        tlsValues = tls.exploreLevel();
        System.out.println();
        System.out.println(tls.toString());
    }
}
