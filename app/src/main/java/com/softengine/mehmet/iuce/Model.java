package com.softengine.mehmet.iuce;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Spanned;

/**
 * Created by mehmet on 29.10.2016.
 */

public class Model {
    Spanned title;
    Spanned desc;
    Spanned link;


    public Model(Spanned title, Spanned desc, Spanned link) {
        this.title = title;
        this.desc = desc;
        this.link = link;
    }

    public Spanned getTitle() {
        return title;
    }

    public void setTitle(Spanned title) {
        this.title = title;
    }

    public Spanned getDesc() {
        return desc;
    }

    public void setDesc(Spanned desc) {
        this.desc = desc;
    }

    public Spanned getLink() {
        return link;
    }

    public void setLink(String Spanned) {
        this.link = link;
    }
}
