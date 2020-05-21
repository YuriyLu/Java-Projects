package series;

public class Exponential extends Series{

    Exponential(){
        super(0,0,0);
    }

    Exponential(int order, double firstElement, double difference){
        super(order, firstElement, difference);
    }

    public double getElement(int i){
        return firstElement * power(difference, i - 1);
    }

    @Override
    public double getSum(){
        if(difference == 1){
            return super.getSum();

        }
        return firstElement * ((power(difference, order) - 1) / (difference - 1));
    }

}
