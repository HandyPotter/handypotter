package com.unity3d.player.Frag3;
import com.unity3d.player.R;

public class ThemeItem {

    public String title;
    public int imageURL;

    int getImageURL(){
        return this.imageURL;
    }

    String getTitle(){
        return this.title;
    }

    // 화면에 표시될 문자열 초기화
    public ThemeItem(String title, int imageURL) {
        this.title = title;
        this.imageURL = imageURL;
    }

}
