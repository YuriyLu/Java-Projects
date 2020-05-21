import javafx.scene.text.Text;

public class TextLogger extends Text implements Observer {
    @Override
    public void update(Object o) {
        String string = "The user has pressed " + (String) o + ".\n";
        setText(getText() + string);
    }
}
