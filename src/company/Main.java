package company;
public class Main {
    public static void main(String[] args) {
        Patient p = new Patient();
        p.setMale(true);
        System.out.println(p.getMale());
        MainLayout layout = new MainLayout(p);

    }
}
