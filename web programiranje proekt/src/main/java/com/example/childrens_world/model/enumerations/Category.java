package com.example.childrens_world.model.enumerations;

public enum Category {
    Action_Figures_And_Playsets("Action Figures & Playsets"),
    PreSchool_And_Electronics("PreSchool & Electronics"),
    Fashion_And_Dolls("Fashion & Dolls"),
    Construction_And_Cars("Construction & Cars"),
    Arts_Crafts_Music("Arts, Crafts & Music"),
    LEGO_And_Bricks("LEGO & Bricks"),
    Games_Puzzles_And_Books("Games, Puzzles & Books"),
    Sensory_Toys("Sensory Toys");
    final String name;

    Category(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
