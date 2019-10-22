package trojan.GUI.Extended;

import javax.swing.*;

public class ButtonGroupEx extends ButtonGroup {

    private double rating;
    public ButtonGroupEx(){

    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}
