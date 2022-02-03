package com.example.banddatabase;


public class Band {
    private int mId;
    private String mName;
    private String mDescription;
    private String mURL;

    public Band() {}

    public Band(int id, String name, String description, String url) {
        mId = id;
        mName = name;
        mDescription = description;
        mURL = url;
    }

    public int getId() {
        return mId;
    }

    public String getUrl(){return mURL;}

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }
}
