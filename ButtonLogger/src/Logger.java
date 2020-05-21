import javafx.scene.text.Text;

import java.util.ArrayList;

public class Logger extends Text implements Observable {
    private ArrayList<Observer> observers;

    public Logger() {
        this.observers = new ArrayList<>();
    }

    public void setString(String string) {
        this.setText(string);
        notifyObservers();
    }

    @Override
    public void addObserver(Object o) {
        observers.add((TextLogger) o);
    }

    @Override
    public void removeObserver(Object o) {
        if (!observers.isEmpty()) {
            observers.remove((TextLogger) o);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this.getText());
        }
    }
}
