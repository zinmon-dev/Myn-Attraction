package com.example.asus.myn_attraction.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus on 7/6/2016.
 */
public class AttractionVO {
    @SerializedName("title")
    private String Title;

    @SerializedName("desc")
    private String Desc;
    @SerializedName("images")
    private String[] imagespath;

    private String Images;


//    ArrayList<ImagesVO> availabelImagesList;


    public AttractionVO(String Title, String Desc, String[] imagespath) {
        this.Title = Title;
        //this.availabelImagesList = imagesList;
        this.Desc = Desc;
        this.imagespath=imagespath;

    }

    public String getTitle() {
        return Title;
    }

    public String getImages() {
        this.Images=imagespath[0];
        return Images;
    }

    public String getDesc() {
        return Desc;
    }

}
