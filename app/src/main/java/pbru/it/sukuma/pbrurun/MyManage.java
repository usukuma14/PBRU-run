package pbru.it.sukuma.pbrurun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lap324-12 on 6/14/2016 AD.
 */
public class MyManage {
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase, readSqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String colume_id = "_id";
    public static final String colume_name = "Name";
    public static final String colume_user = "User";
    public static final String colume_password = "Password";
    public static final String colume_avata = "Avata";
    public static final String colume_gold = "Gold";



    public MyManage(Context context) {
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();


    }   //constructor

    public String[] searchUser(String strUser) {

        try {

            String[] resultStrings = null;
            Cursor cursor = readSqLiteDatabase.query(user_table,
                    new String[]{colume_id, colume_name, colume_user, colume_password, colume_avata, colume_gold},
                    colume_user + "=?",
                    new String[]{String.valueOf(strUser)},null, null, null,null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {

                    resultStrings = new String[cursor.getColumnCount()];
                    for (int i=0;i<cursor.getColumnCount();i++) {
                        resultStrings[i] = cursor.getString(i);

                    }

                }   //if2
            }   //if1
            cursor.close();
            return resultStrings;




        } catch (Exception e) {
            return null;

        }



    }

    public long addNewUser(String strId,
                           String strName,
                           String strUser,
                           String strPassword,
                           String strAvata,
                           String strGold) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(colume_id, strId);
        contentValues.put(colume_name, strName);
        contentValues.put(colume_user, strUser);
        contentValues.put(colume_password, strPassword);
        contentValues.put(colume_avata, strAvata);
        contentValues.put(colume_gold, strGold);

        return sqLiteDatabase.insert(user_table, null, contentValues);


    }


}   //main class
