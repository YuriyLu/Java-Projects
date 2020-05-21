package series;

import java.io.FileWriter;
import java.io.IOException;

abstract public class Series {
    protected int order;
    protected double firstElement;
    protected double difference;


    public Series(int order, double firstElement, double difference) {
        this.order = order;
        this.firstElement = firstElement;
        this.difference = difference;
    }

    abstract protected double getElement(int i);

    public double getSum() {
        double sum = 0;
        for (int i = 1; i <= order; i++) {
            sum += getElement(i);
        }
        return sum;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= order; i++) {
            str.append(getElement(i) + "\t");
        }
        return str.toString();
    }

    public String toString(int n){
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            str.append(getElement(i) + "\t");
        }
        return str.toString();
    }

    public void saveInFile(FileWriter file) throws IOException {
        file.write(this.toString());
    }

    public void write(FileWriter file) throws IOException {
        file.write("Sum of progression:\n" + this.getSum() + "\nElements of progression:\n");
        this.saveInFile(file);
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setFirstElement(double firstElement) {
        this.firstElement = firstElement;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public double power(double n, int m){
        double res = 1;
        for(int i = 0; i < m; i++)
            res *= n;
        return res;
    }
}