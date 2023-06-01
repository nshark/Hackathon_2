package company;
public class Main {
    public static void main(String[] args) {
        ApiInterface.key = ApiInterface.updateAPIkey();
        Patient p = new Patient();
        p.setMale(true);
        System.out.println(p.getMale());
        MainLayout layout = new MainLayout(p);
    }
}
