package com.example.android.booklistingapp;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Abhas on 03-10-2017.
 */



public class Books implements Parcelable {

    private String mBookTitle;
    private String mBookAuthor;

    public Books(String bookTitle , String bookAuthor){

       mBookTitle= bookTitle;
        mBookAuthor = bookAuthor;
    }
    private Books(Parcel in) {

        mBookTitle = in.readString();
        mBookAuthor = in.readString();
    }

    public String getBookTitle(){return mBookTitle;}
    public String getBookAuthor(){return mBookAuthor;}

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mBookAuthor);
        dest.writeString(mBookTitle);
    }

    public static final Parcelable.Creator<Books> CREATOR = new Parcelable.Creator<Books>() {
        public Books createFromParcel(Parcel in) {
            return new Books(in);
        }
        public Books[] newArray(int size) {
            return new Books[size];
        }
    };
}
