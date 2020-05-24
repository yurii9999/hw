package group244.kuzmin;

public class LinuxComputer extends Computer {
    public LinuxComputer(Infector infector) {
        setInfector(infector);
    }
    public LinuxComputer() {
        setInfector(new RandomInfector());
    }
    @Override
    public Double getProbability() {
        return 0.3;
    }

    @Override
    public String getOs() {
        return "Linux";
    }
}
