package group244.kuzmin;

public class WindowsComputer extends Computer {
    public WindowsComputer(Infector infector) {
        setInfector(infector);
    }
    public WindowsComputer() {
        setInfector(new RandomInfector());
    }
    @Override
    public Double getProbability() {
        return 0.7;
    }

    @Override
    public String getOs() {
        return "Windows";
    }
}
