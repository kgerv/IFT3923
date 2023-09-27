public class Main {
    public static void main(String[] args) {
        String file_path = args[0];
        Tloc tloc = new Tloc();
        int tloc_value = tloc.calculate(file_path);

        System.out.print("tloc = ");
        System.out.println(tloc_value);
    }
}
