package com.example.thirtyonestudio;

public class Upload {
    private String mName;
    private String mImageUrl;

    public Upload() {

    }

    public Upload (String name, String imageUrl){
        if (name.trim().equals("")){
            name = "Tidak ada nama";
        }
        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName = name;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
