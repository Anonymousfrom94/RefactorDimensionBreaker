package universite_paris8.iut.osall.boom.modele.Utilitaire;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Position {
    private IntegerProperty x;
    private IntegerProperty y;

    public Position(double x, double y) {
        this.x = new SimpleIntegerProperty();
        this.y = new SimpleIntegerProperty();
        setX(x);
        setY(y);
    }

    public int getX() {
        return x.get();
    }

    public int getY() {
        return y.get();
    }

    public void setX(int x) {
      //  if (x < 0 || x > Map.getSizeMondeLargeur())
        //throw new PositionInvalideExeption("x hors map");

        this.x.set(x);
    }

    public void setY(int y)
    {
      //  if (y < 0 || y > Map.getSizeMondeHauteur())
      //      //throw new PositionInvalideExeption("y hors map");

        this.y.set(y);
    }




}