public class Main {
    public static void main(String[] args) {
        String file_path = args[0];
        Tls tls = new Tls(file_path);
        tls.explorePath();

        Tloc tloc = new Tloc();
        int tloc_value = tloc.calculate(file_path);

        Tassert tassert = new Tassert();
        int tassert_value = tassert.calculate(file_path);

        System.out.print("tassert = ");
        System.out.println(tassert_value);
    }
}
