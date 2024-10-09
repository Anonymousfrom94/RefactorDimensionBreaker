package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.osall.boom.modele.Environnement.Map;

public class Position {
    private DoubleProperty x;
    private DoubleProperty y;

    public Position(double x, double y) {
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();
        setX(x);
        setY(y);
    }

    public double getX() {
        return x.get();
    }

    public double getY() {
        return y.get();
    }

    public void setX(double x)
    {
        if (x < 0 || x > Map.getSizeMondeLargeur())
            //throw new PositionInvalideExeption("x hors map");

        this.x.set(x);
    }

    public void setY(double y)
    {
        if (y < 0 || y > Map.getSizeMondeHauteur())
            //throw new PositionInvalideExeption("y hors map");

        this.y.set(y);
    }

    public DoubleProperty getXProperty() {
        return x;
    }

    public DoubleProperty3getYProperty() {
        return y;
    }




}
