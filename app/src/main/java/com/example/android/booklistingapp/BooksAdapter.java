package com.example.android.booklistingapp;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abhas on 03-10-2017.
 */

public class BooksAdapter extends ArrayAdapter<Books> {

    public BooksAdapter(Context context , ArrayList<Books> booksArrayList){
        super(context,0,booksArrayList);
    }

@Override
    public View getView(int position , View convertView , ViewGroup parent) {

    Books books = getItem(position);

    if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.books_list_item, parent, false);
    }

    TextView bookTitleTextView = (TextView) convertView.findViewById(R.id.book_title);
    TextView bookAuthorTextView = (TextView) convertView.findViewById(R.id.book_author);

    bookTitleTextView.setText(books.getBookTitle());
    bookAuthorTextView.setText(books.getBookAuthor());

    return convertView;
}
}
