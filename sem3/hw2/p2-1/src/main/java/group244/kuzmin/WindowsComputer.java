package group244.kuzmin;

public class WindowsComputer extends Computer {
    @Override
    public Double getProbability() {
        return 0.7;
    }

    @Override
    public String getOs() {
        return "Windows";
    }
}
