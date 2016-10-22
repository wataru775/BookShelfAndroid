package org.mmpp.bookshelfapp;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * アプリケーションのメインクラス
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://www.mmpp.org/service/bookdb/select_book.json";
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        // 1ページに表示する件数を指定します
        viewPagerAdapter.setViewCount(12);

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(viewPagerAdapter);
        try {
            // 書籍一覧情報のJSONへアクセスします
            new JSONConnectTask(new URL(url), viewPagerAdapter).execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

    }
}
