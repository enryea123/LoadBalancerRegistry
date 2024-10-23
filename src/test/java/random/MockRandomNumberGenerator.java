package random;

import org.project.random.IRandomNumberGenerator;

public class MockRandomNumberGenerator implements IRandomNumberGenerator {
    private int returnValue;

    public void setReturnValue(int returnValue) {
        this.returnValue = returnValue;
    }

    @Override
    public int getNumber(int bound) {
        return returnValue;
    }
}
