package group244.kuzmin;

import java.util.Random;

public class RandomInfector implements Infector {
    @Override
    public Double getProbability() {
        Random random = new Random();
        return random.nextDouble();
    }
}
