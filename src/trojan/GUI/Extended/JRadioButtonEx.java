package trojan.GUI.Extended;

import javax.swing.*;

public class JRadioButtonEx extends JRadioButton {

    private double rating;
    public JRadioButtonEx(){

    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}
