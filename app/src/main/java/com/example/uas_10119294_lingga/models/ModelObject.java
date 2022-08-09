package com.example.uas_10119294_lingga.models;

import com.example.uas_10119294_lingga.R;

public enum ModelObject {
    /*
     * NIM : 10119294
     * NAMA : Lingga Juliansyah
     * Kelas : IF-7
     * */
    RED(R.string.red, R.layout.view_red),
    BLUE(R.string.blue, R.layout.view_blue);
//    GREEN(R.string.green, R.layout.view_green);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}