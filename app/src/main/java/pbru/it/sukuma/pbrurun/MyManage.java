package pbru.it.sukuma.pbrurun;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lap324-12 on 6/14/2016 AD.
 */
public class MyManage {
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

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

    }   //constructor

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
