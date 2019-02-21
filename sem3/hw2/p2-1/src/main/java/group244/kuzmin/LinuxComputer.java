package group244.kuzmin;

public class LinuxComputer extends Computer {
    @Override
    public Double getProbability() {
        return 0.3;
    }

    @Override
    public String getOs() {
        return "Linux";
    }
}
