package com.example.gestionaleTesina;

import javax.swing.text.html.Option;
import java.util.ArrayList;

public class Travel {
    String name;
    ArrayList<TravelOption> options;
    Option favouriteOption;

    public Travel(String name, ArrayList<TravelOption> options, Option favouriteOption) {
        this.name = name;
        this.options = options;
        this.favouriteOption = favouriteOption;
    }

    public Option getFavouriteOption() {
        return favouriteOption;
    }

    public void setFavouriteOption(Option favouriteOption) {
        this.favouriteOption = favouriteOption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TravelOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<TravelOption> options) {
        this.options = options;
    }
}
