package edu.imag.miage.frigo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.imag.miage.frigo.data.ArticleContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {

    public DetailFragment() {
    }

    private static final int COL_ARTICLE_ID = 0;
    private static final int COL_ARTICLE_NAME = 1;
    private static final int COL_ARTICLE_G_NAME = 2;
    private static final int COL_ARTICLE_BRAND = 3;
    private static final int COL_ARTICLE_WEIGHT = 4;
    private static final int COL_ARTICLE_BARCODE = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String articleStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            displayDetailArticle(articleStr, rootView);

        }
        return rootView;
    }

    private void displayDetailArticle(String articleName, View rootView){
        ArrayList<String> mArrayList = new ArrayList<>();
        Cursor mCursor = getContext().getContentResolver().query(
                ArticleContract.ArticleEntry.CONTENT_URI,
                null,
                ArticleContract.ArticleEntry.COLUMN_ARTICLE_NAME + " = ?",
                new String[]{articleName},
                null);

        while(mCursor.moveToNext()) {

            ((TextView) rootView.findViewById(R.id.article_name_text))
                    .setText(mCursor.getString(COL_ARTICLE_NAME));

        ((TextView) rootView.findViewById(R.id.generic_name_text))
                .setText(mCursor.getString(COL_ARTICLE_G_NAME));

        ((TextView) rootView.findViewById(R.id.brand_name_text))
                .setText(mCursor.getString(COL_ARTICLE_BRAND));

        ((TextView) rootView.findViewById(R.id.weight_text))
                .setText(mCursor.getString(COL_ARTICLE_WEIGHT));

        ((TextView) rootView.findViewById(R.id.barcode_name_text))
                .setText(mCursor.getString(COL_ARTICLE_BARCODE));
        }

        mCursor.close();
    }
}
