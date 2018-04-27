package com.example.databaseHelper;

/**
 * Created by Administrator on 2018/4/27.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/*
 * 在这个类的构造函数里面我们调用了父类的构造方法用来创建数据库文
 * 件，第二个构造方法只是为了方便构造（不用些那么多的参数）
 * 这个类继承了 SQLiteOpenHelper 类，并且重写了父类里面的
onCreate方法和 onUpgrade方法，
 * onCreate方法当数据库文件不存在的时候会被调用来创建一个新的数
 * 据库文件（不懂的小伙伴可以百度一下）
 */

public class MyHelper extends SQLiteOpenHelper{

    public static String CREATE_TABLE = "create table "+ DatabaseStatic.TABLE_NAME +"(" +
            DatabaseStatic.CITY_NAME + " varchar(30), " +
            DatabaseStatic.ID + " Integer primary key autoincrement, " +
            DatabaseStatic.DATE  + " varchar(20) not null, " +
            DatabaseStatic.WEATHER  + " varchar(30), "+
            DatabaseStatic.TEMPERATURE+" varchar(30), "+
            DatabaseStatic.HUMIDITY+" varchar(30)) ";
            // 用于创建表的SQL语句
    private Context myContext = null;

    public MyHelper(Context context, String name,
                    CursorFactory factory, int version) {
        super(context, DatabaseStatic.DATABASE_NAME, null, DatabaseStatic.DATABASE_VERSION);
    }

    public MyHelper(Context context)
    {
        super(context, DatabaseStatic.DATABASE_NAME, null, DatabaseStatic.DATABASE_VERSION);
        myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("UseDatabase", "创建数据库");
        Toast.makeText(myContext, "创建数据库", Toast.LENGTH_SHORT).show();
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}
