public class Main {
    public static void main(String[] args) {
        String file_path = args[0];
        Tloc tloc = new Tloc();
        int tloc_value = tloc.calculateTloc(file_path);

        System.out.println("tloc = " + tloc_value);
    }
}
