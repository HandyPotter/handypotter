package com.unity3d.player.Frag3;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import  com.unity3d.player.R;
import com.unity3d.player.UnityPlayerActivity;

public class SearchActivity extends AppCompatActivity {


    private List<SignLanguage> signLanguageList;
    private List<SignLanguage> signLanguageForSearchList;
    private SearchListAdapter searchListAdapter;

    //TextView textView;
    ListView listView;
    LetsConnect letsConnect;
    String result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //textView = (TextView)findViewById(R.id.test);

        listView = (ListView)findViewById(R.id.Dictionary);
        signLanguageList = new ArrayList<SignLanguage>();
        signLanguageForSearchList = new ArrayList<SignLanguage>();


        //어댑터 초기화부분 signLanguageList랑 어댑터를 연결해준다.
        searchListAdapter = new SearchListAdapter(getApplicationContext(), signLanguageList, signLanguageForSearchList);
        listView.setAdapter(searchListAdapter);

        letsConnect = new LetsConnect();

        try {
            result = letsConnect.execute().get();
            System.out.println(result);

            JSONObject jsonObject = null;
            jsonObject = new JSONObject(result);

            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            int count = 0;

            String SL_ID, SL_WORD, SL_Theme;

            for(count = 0; count<jsonArray.length(); count++){

                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                SL_ID = object.getString("SL_ID");//여기서 ID가 대문자임을 유의

                System.out.println(SL_ID);

                SL_WORD = object.getString("SL_WORD");

                System.out.println(SL_WORD);
                SL_Theme = object.getString("Theme");

                System.out.println(SL_Theme);

                SignLanguage signLanguage = new SignLanguage(SL_ID, SL_WORD, SL_Theme);
                signLanguageList.add(signLanguage);//리스트뷰에 값을 추가해줍니다
                signLanguageForSearchList.add(signLanguage);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final EditText editsearch = (EditText)findViewById(R.id.search_field1);

        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchListAdapter.searchDict(s.toString());//회원 검색 기능용

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ImageButton searchBtn = (ImageButton)findViewById(R.id.searchBtn1);
        searchBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = String.valueOf(editsearch.getText());
                searchListAdapter.searchDict(text);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SignLanguage sss = (SignLanguage) searchListAdapter.getItem(position);
                System.out.println(sss.SL_WORD);

                String word2 = sss.SL_WORD;      //여기가 검색한 단어 값 넘어가는 곳

                if(word2.matches("사랑"))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sldict.korean.go.kr/front/sign/signContentsView.do?origin_no=8240&top_category=CTE&category=&searchKeyword=%EC%82%AC%EB%9E%91&searchCondition=&search_gubun=&museum_type=00&current_pos_index=0"));
                    startActivity(intent);
                }
                else if(word2.matches("능숙하다"))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sldict.korean.go.kr/front/sign/signContentsView.do?origin_no=6788&top_category=CTE&category=&searchKeyword=%EB%8A%A5%EC%88%99%ED%95%98%EB%8B%A4&searchCondition=&search_gubun=&museum_type=00&current_pos_index=0"));
                    startActivity(intent);

                }
                else if(word2.matches("병원"))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sldict.korean.go.kr/front/sign/signContentsView.do?origin_no=10964&top_category=CTE&category=&searchKeyword=%EB%B3%91%EC%9B%90&searchCondition=&search_gubun=&museum_type=00&current_pos_index=0"));
                    startActivity(intent);

                }
                else if(word2.matches("청탁"))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sldict.korean.go.kr/front/sign/signContentsView.do?origin_no=9936&top_category=CTE&category=&searchKeyword=%EC%B2%AD%ED%83%81&searchCondition=&search_gubun=&museum_type=00&current_pos_index=0"));
                    startActivity(intent);
                }
                else if(word2.matches("정부"))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sldict.korean.go.kr/front/sign/signContentsView.do?origin_no=7618&top_category=CTE&category=&searchKeyword=%EC%A0%95%EB%B6%80&searchCondition=&search_gubun=&museum_type=00&current_pos_index=0"));
                    startActivity(intent);
                }
                else if(word2.matches("눈부시다"))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sldict.korean.go.kr/front/sign/signContentsView.do?origin_no=5437&top_category=CTE&category=&searchKeyword=%EB%88%88%EB%B6%80%EC%8B%9C%EB%8B%A4&searchCondition=&search_gubun=&museum_type=00&current_pos_index=0"));
                    startActivity(intent);
                }
                else if(word2.matches("가능성"))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sldict.korean.go.kr/front/sign/signContentsView.do?origin_no=2954&top_category=CTE&category=&searchKeyword=%EA%B0%80%EB%8A%A5%EC%84%B1&searchCondition=&search_gubun=&museum_type=00&current_pos_index=0"));
                    startActivity(intent);
                }


            }
        });


    }


    public class LetsConnect extends AsyncTask<String, Void, String> {

        public String result;

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {

            StringBuilder jsonHtml = new StringBuilder();

            try {

                String link = "http://3.18.113.12/signlanguage-controller.php";
                URL url = new URL(link);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if (conn != null) {
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF8"));
                        while (true) {
                            String line = br.readLine();
                            if (line == null)
                                break;
                            jsonHtml.append(line + "\n");

                        }
                        result = jsonHtml.toString();
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            result.toString();
        }

    }
}
