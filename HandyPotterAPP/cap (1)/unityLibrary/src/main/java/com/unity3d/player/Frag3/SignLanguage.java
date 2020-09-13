package com.unity3d.player.Frag3;
import com.unity3d.player.R;

public class SignLanguage {

    String SL_ID;
    String SL_WORD;
    String SL_Theme;

    public SignLanguage(String SL_ID, String SL_WORD, String SL_Theme){
        this.SL_ID = SL_ID;
        this.SL_WORD = SL_WORD;
        this.SL_Theme = SL_Theme;
    }

    public String getSL_WORD(){
        return SL_WORD;
    }

    public void setSL_WORD(String SL_WORD){
        this.SL_WORD = SL_WORD;
    }

    public String getSL_ID(){
        return SL_ID;
    }

    public void setSL_ID(String SL_ID){
        this.SL_ID = SL_ID;
    }

    public String getSL_Theme(){
        return SL_Theme;
    }

    public void setSL_Theme(String SL_Theme){
        this.SL_Theme = SL_Theme;
    }
}
