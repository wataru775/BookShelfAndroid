package org.mmpp.bookshelfapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * WebにアクセスするTaskの抽象クラス
 * Created by wataru on 2016/10/22.
 */

public abstract class AbstractWebAccessTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result>{
    /** ログタグ */
    private static final String TAG = "AbstractWebAccessTask";


    /**
     * URLにアクセスして結果を取得します
     * @param url 接続先
     * @return 結果
     */
    protected Result webaccess(URL url) throws IOException {
        InputStream inputStream = null;
        try {
            // アドレスに接続する処理
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            // Webアクセス結果からJSON結果に変換します
            return castResult(inputStream);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                Log.d(TAG," : " + e.getMessage());
            }
        }
    }

    /**
     * Webアクセス結果を指定の形式に変換します
     * @param inputStream
     * @return
     */
    protected abstract Result castResult(InputStream inputStream);
}
