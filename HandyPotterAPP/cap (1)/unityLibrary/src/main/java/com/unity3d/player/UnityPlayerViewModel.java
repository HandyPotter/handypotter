package com.unity3d.player;

import android.os.Environment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class UnityPlayerViewModel extends ViewModel {
    public MutableLiveData<String> mResult = new MutableLiveData<>();
    public MutableLiveData<String> subtitle = new MutableLiveData<>();
    public String playTime = new String();
    private FileOutputStream fos;

    public UnityPlayerViewModel(){
         mResult.setValue("");
         subtitle.setValue("");

    }

    public void setPlayTime(String str){
        this.playTime = str;

    }

    public void saveSubtitleText() throws IOException {


            File file = new File(Environment.getDataDirectory(),"output");
           if(!file.exists()){
               file.mkdirs();
           }
            File outText = new File("myText.text");
            FileWriter writer = new FileWriter(outText);

            writer.append(subtitle.getValue());
            writer.flush();
            writer.close();
            System.out.println("saved file ");



        }
    public void makeSub(){

        StringBuilder tv = new StringBuilder();

                if(!(mResult.getValue().startsWith("　"))) {
                    tv.append(playTime).append("\n");
                    tv.append(mResult.getValue()).append("\n\n");
                    subtitle.setValue(subtitle.getValue() + tv);
                }
        }
    }


