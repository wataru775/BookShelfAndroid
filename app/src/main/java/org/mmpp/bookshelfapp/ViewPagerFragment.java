package org.mmpp.bookshelfapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.mmpp.bookshelf.util.Book;

import java.util.LinkedList;
import java.util.List;

/**
 * スワイプの１ページのクラス
 * Created by wataru on 2016/10/19.
 */

public class ViewPagerFragment extends Fragment {
    private static final String TAG = "ViewPagerFragment";
    /**
     * 表示する書籍情報一覧
     */
    private List<Book> books = new LinkedList<Book>();

    /**
     * デフォルトコンストラクタ
     */
    public ViewPagerFragment() {
        super();
        this.books = new LinkedList<Book>();
    }

    /**
     * 表示する書籍情報を指定してページクラスを取得します
     * @param books 書籍情報一覧
     * @return ページクラス
     */
    public static ViewPagerFragment getInstance(List<Book> books) {
        ViewPagerFragment placeholderFragment = new ViewPagerFragment();
        placeholderFragment.books = books;
        return placeholderFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridView);

        GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity());
        gridViewAdapter.setBooks(books);
        gridView.setAdapter(gridViewAdapter);

        return rootView;
    }


}
