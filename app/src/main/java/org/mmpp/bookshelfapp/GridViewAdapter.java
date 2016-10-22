package org.mmpp.bookshelfapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.mmpp.bookshelf.util.Book;

import java.util.LinkedList;
import java.util.List;

/**
 * GridViewの表示アダプタークラス
 * Created by wataru on 2016/10/20.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    /**
     * 表示書籍情報一覧
     */
    private List<Book> books = new LinkedList<Book>();

    /**
     * デフォルトコンストラクタ
     * @param context
     */
    public GridViewAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }
    public void setBooks(List<Book> items) {
        this.books = items;
    }


    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ColumnHolder holder;
        if (convertView == null) {
            // View情報を新規作成します
            float density = context.getResources().getDisplayMetrics().density;
            // ホルダーを初期化
            holder = new ColumnHolder();

            // column.xmlのレイアウト取得
            convertView = layoutInflater.inflate(R.layout.column, null);
            // GridViewの初期サイズを指定します
            convertView.setLayoutParams(new LinearLayout.LayoutParams( (int)(110*density) , (int)(200*density)));

            // ホルダーに画面要素を格納します
            holder.title = (TextView)convertView.findViewById(R.id.title);
            holder.cover = (ImageView)convertView.findViewById(R.id.cover);

            // 画像の表示サイズを指定します。
            float width = (107*density);
            float height = (160*density);
            // Scale
            float scale = 0.8f;
            width *= scale;
            height *= scale;
            // 画像サイズを指定します
            holder.cover.setLayoutParams(new LinearLayout.LayoutParams( (int)width , (int) height));

            // 画像データを取得します
            new ImageViewSetImageTask( holder.cover, books.get(i).amazon_image ).execute();

            convertView.setTag(holder);
        } else {
            // ホルダーを取得します
            holder = (ColumnHolder)convertView.getTag();
        }

        // 書籍タイトルを格納します
        holder.title.setText(books.get(i).title);


        return convertView;
    }

    @Override
    public int getCount() {
        return books.size();
    }


}
