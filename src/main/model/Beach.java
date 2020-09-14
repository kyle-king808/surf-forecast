package model;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;

// Represents a beach having a name, normal direction, lists of reviews, and a range of wave heights
public class Beach implements Saveable {

    private String name;
    private int direction;
    private ArrayList<String> reviews = new ArrayList<>();
    private int oneThirdHeight;
    private int oneTenthHeight;


    /*
     * REQUIRES: 0 <= direction < 360
     * EFFECTS: name on beach is set to beachName,
     *          direction on beach set to beachDirection.
     */
    public Beach(String beachName, int beachDirection) {
        this.name = beachName;
        this.direction = beachDirection;
    }

    public String getName() {
        return this.name;
    }

    public int getDirection() {
        return this.direction;
    }

    public ArrayList<String> getReviews() {
        return this.reviews;
    }

    public int getOneThirdHeight() {
        return oneThirdHeight;
    }

    public int getOneTenthHeight() {
        return oneTenthHeight;
    }

    // MODIFIES: this
    // EFFECTS: Adds a review to list of reviews
    public void addReview(String review) {
        this.reviews.add(review);
    }

    // REQUIRES: All parameters are non-negative, 0 <= direction < 360
    // MODIFIES: this
    // EFFECTS: using swell direction, swell average of 1/3 the largest waves
    //          and average of 1/10 the highest waves, predicts actual wave height
    //          at beach for oneThirdHeight and oneTenthHeight.
    public void addSwell(int direction, int oneThirdHeight, int oneTenthHeight) {
        int angleBetween = direction - this.direction;
        int scalar;

        if ((-45 <= angleBetween && angleBetween <= 45) || angleBetween >= 315 || angleBetween <= -315)  {
            scalar = 1;
        } else if ((-90 <= angleBetween && angleBetween <= 90) || angleBetween >= 270 || angleBetween <= -270) {
            scalar = 2;
        } else {
            scalar = 0;
        }

        if (scalar == 0) {
            this.oneThirdHeight = 0;
            this.oneTenthHeight = 0;
        } else {
            this.oneThirdHeight = oneThirdHeight / scalar;
            this.oneTenthHeight = oneTenthHeight / scalar;
        }


    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(direction);
        printWriter.println();
    }
}
