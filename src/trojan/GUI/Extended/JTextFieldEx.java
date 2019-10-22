package trojan.GUI.Extended;

import javax.swing.*;

public class JTextFieldEx extends JTextField {
    private double rating;
    public JTextFieldEx(){

    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}
