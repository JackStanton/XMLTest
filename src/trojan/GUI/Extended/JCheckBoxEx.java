package trojan.GUI.Extended;

import javax.swing.*;

public class JCheckBoxEx extends JCheckBox {
    private double rating;
    public JCheckBoxEx(){

    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}
