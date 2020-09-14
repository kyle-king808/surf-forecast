package model;

import exceptions.HeightWrongOrderException;

import java.lang.reflect.Array;
import java.util.ArrayList;

//Represents a surf forecast with a list of beaches
public class SurfForecast {
    private Swell swell = new Swell();
    private ArrayList<Beach> beachList = new ArrayList<Beach>();

    //EFFECTS: creates a surf forecast
    public SurfForecast() {}

    //REQUIRES: 0 <= direction < 360
    //MODIFIES: this
    //EFFECTS: Updates swell information of all beaches in beachList, swellPeriod, oneThirdSwellHeight,
    //         oneTenthSwellHeight and swellDirection. Throws HeightWrongOrderException if oneThirdSwellHeight
    //         is larger than oneTenthSwellHeight.
    public void updateSwell(int swellDirection,
                            int swellPeriod,
                            int oneThirdSwellHeight,
                            int oneTenthSwellHeight) throws HeightWrongOrderException {
        if (oneThirdSwellHeight > oneTenthSwellHeight) {
            throw new HeightWrongOrderException();
        }
        for (Beach b: beachList) {
            b.addSwell(swellDirection,oneThirdSwellHeight,oneTenthSwellHeight);
        }
        swell.updateSwell(swellDirection,swellPeriod,oneThirdSwellHeight,oneTenthSwellHeight);
    }

    // MODIFIES: this
    // EFFECTS: adds Beach b to beachList
    public void addBeach(Beach b) {
        beachList.add(b);
    }

    public int getSwellPeriod() {
        return swell.getPeriod();
    }

    public int getOneThirdSwellHeight() {
        return swell.getOneThirdHeight();
    }

    public int getOneTenthSwellHeight() {
        return swell.getOneTenthHeight();
    }

    public int getSwellDirection() {
        return swell.getDirection();
    }

    public ArrayList<Beach> getBeachList() {
        return beachList;
    }

    // REQUIRES: there is a beach at position index
    // EFFECTS: returns beach from beachList at position
    public Beach getBeachI(int position) {
        return beachList.get(position);
    }

}
