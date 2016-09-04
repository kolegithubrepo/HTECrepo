package com.example.ivan.htec;

/**
 * Created by Ivan on 9/2/2016.
 */
public class ListObject {

    public static final String  IMAGE_C = "image";
    public static final String  DESCRIPTION_C = "description";
    public static final String  TITLE_C = "title";

    private String image;
    private String description;
    private String title;

    public ListObject(){
        this.setImage("");
        this.setDescription("");
        this.setTitle("");
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
