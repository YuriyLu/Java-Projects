package series;

public class Linear extends Series {

    Linear(){
        super(0, 0, 0);
    }

    Linear(int order, double firstElement, double difference) {
        super(order, firstElement, difference);
    }

    public double getElement(int i) {
        return firstElement + difference * (i - 1);
    }

    @Override
    public double getSum() {
        return ((2 * firstElement + difference * (order - 1)) / 2) * order;
    }

}
