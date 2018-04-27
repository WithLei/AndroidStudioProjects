package appbim.example.com.weatherreport;

import com.example.databaseHelper.DatabaseStatic;
import com.example.databaseHelper.MyHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private MyHelper myHelper = null;
    private Button button = null;
    private SQLiteDatabase database = null;
    private static int bookSum = 0;
    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = new TextView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);
        layout.addView(textView);
        button = findViewById(R.id.buttonCreateDatabase);
        button.setOnClickListener(listener);
        button = (Button) findViewById(R.id.buttonInsertDatabase);
        button.setOnClickListener(listener);
        button = (Button) findViewById(R.id.buttonUpdateDatabase);
        button.setOnClickListener(listener);
        button = (Button) findViewById(R.id.buttonDeleteDatabase);
        button.setOnClickListener(listener);
        button = (Button) findViewById(R.id.buttonQueryDatabase);
        button.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonCreateDatabase:
                    createDatabase();
                    break;
                case R.id.buttonInsertDatabase:
                    insertDatabase();
                    break;
                case R.id.buttonUpdateDatabase:
                    updateDatabase();
                    break;
                case R.id.buttonDeleteDatabase:
                    deleteDatabase();
                    break;
                case R.id.buttonQueryDatabase:
                    searchDatabase();
                    break;
            }
        }
    };

    private void createDatabase() // 创建或者打开数据库
    {
        Toast.makeText(this,"rua",Toast.LENGTH_SHORT).show();
        myHelper = new MyHelper(this);

  /*
   * 调用getWritabelDatabase方法或者
   * getReadableDatabase方法时，如果数据库文
   * 件中不存在（注意一个数据库中可以存在多个表格），
   * 那么会回调MyHelper类的onCreate方法新建一个数据库文
   * 件并且在这个数据库文件中新建一
   * 个book表格
   */
        myHelper.getWritableDatabase();
    }

    private void insertDatabase() // 向数据库中插入新数据
    {
        if (myHelper == null) {
            myHelper = new MyHelper(this);
        }
        database = myHelper.getWritableDatabase();

        ContentValues cV = new ContentValues();
        cV.put(DatabaseStatic.CITY_NAME, "北京");
        cV.put(DatabaseStatic.ID, ++bookSum);
        cV.put(DatabaseStatic.DATE , "2018年4月24日");
        cV.put(DatabaseStatic.WEATHER , "晴");
        cV.put(DatabaseStatic.TEMPERATURE , "22度");
        cV.put(DatabaseStatic.HUMIDITY , "百分之50");
  /*
   * 这个方法是留给不熟悉SQL语句的小伙伴用的，Android把
   * SQLite的插入语句封装了起来，
   * 通过 ContentValues 类的对象来保存数据库中的数据，
   * 于HashMap
   */
        database.insert(DatabaseStatic.TABLE_NAME, null, cV);

  /*
   * 对应的SQL语句：
   * database.execSQL("insert into " + DatabaseStatic.TABLENAME + " values(?, ?, ?, ?)",
   * new Object[]{"C Language", ++bookSum, "zhidian", 42.6});
   * 或者是这个：
   * database.execSQL("insert into " + DatabaseStatic.TABLENAME + "(" +
   *  DatabaseStatic.BOOKNAME + ", " + DatabaseStatic.ID + ", " +
   *  DatabaseStatic.AUTHOR + ", " + DatabaseStatic.PRICE +
   *  ") values(?, ?, ?, ?)", new Object[]{"C Language", ++bookSum, "zhidian", 42.6});
   * 这里将 ？ 号理解成一个C语言里面的占位符，然后通过 Object[] 数组中的内容补全，下同
   * 参数中的 Object[] 数组是一个通用的数组，里面的数据可以转换为任意类型的数据，通过这个完成不同数据类型变量之间的储存
  */

        Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
    }

    private void updateDatabase() // 更新数据
    {
        if (myHelper == null) {
            myHelper = new MyHelper(this);
        }
        database = myHelper.getWritableDatabase();

        ContentValues cV = new ContentValues();
        cV.put(DatabaseStatic.DATE , "xiaoming");
  /*
   * 调用 update 方法，将书名为"C Language" 的书作者更新为 "xiaoming
   */
        database.update(DatabaseStatic.TABLE_NAME, cV,
                DatabaseStatic.CITY_NAME + "= ?", new String[]{"北京"});
  /*
   * 对应的SQL语句：
   * database.execSQL("update " + DatabaseStatic.TABLENAME + " set " + DatabaseStatic.AUTHOR +
   *  "= ? where " + DatabaseStatic.BOOKNAME + " = ?", new String[]{"xiaoming", "C Language"});
   */

        Toast.makeText(this, "数据更新成功", Toast.LENGTH_SHORT).show();
    }

    private void deleteDatabase() // 数据库中删除数据
    {
        if (myHelper == null) {
            myHelper = new MyHelper(this);
        }
        database = myHelper.getWritableDatabase();

  /*
   * 调用 delete 方法删除数据库中的数据
   * 对应的SQL语句：
   * database.execSQL("delete from " +
   * DatabaseStatic.TABLE_NAME + " where " +
   * DatabaseStatic.BOOK_NAME + " = ?", new
   * String[]{"C Language"});
   */
        database.delete(DatabaseStatic.TABLE_NAME, DatabaseStatic.CITY_NAME + " = ? ",
                new String[]{"北京"});

        Toast.makeText(this, "数据删除成功", Toast.LENGTH_SHORT).show();
    }

    private void searchDatabase() // 查询数据库中的数据
    {
        if (myHelper == null) {
            myHelper = new MyHelper(this);
        }
        database = myHelper.getWritableDatabase();
  /*
   * 调用database的query方法，第一个参数是要查询的表名，
   * 后面的参数是一些查询的约束条件，对应于SQL语句的一些参
   * 数， 这里全为null代表查询表格中所有的数据
   * 查询的结果返回一个 Cursor对象
   * 对应的SQL语句：
   * Cursor cursor = database.rawQuery("select * from book", null);
   */
        Cursor cursor = database.query(DatabaseStatic.TABLE_NAME, null, null, null, null, null, null);

        StringBuilder str = new StringBuilder();
        if (cursor.moveToFirst()) // 显示数据库的内容
        {
            for (; !cursor.isAfterLast(); cursor.moveToNext()) // 获取查询游标中的数据
            {
                str.append(cursor.getString(cursor.getColumnIndex(DatabaseStatic.ID)) + " ");
                str.append(cursor.getString(cursor.getColumnIndex(DatabaseStatic.CITY_NAME)) + " ");
                str.append(cursor.getString(cursor.getColumnIndex(DatabaseStatic.DATE )) + " ");
                str.append(cursor.getString(cursor.getColumnIndex(DatabaseStatic.WEATHER )) + " ");
                str.append(cursor.getString(cursor.getColumnIndex(DatabaseStatic.TEMPERATURE )) + " ");
                str.append(cursor.getString(cursor.getColumnIndex(DatabaseStatic.HUMIDITY)) + "\n");
            }
        }
        cursor.close(); // 记得关闭游标对象
        if (str.toString().equals("")) {
            str.append("数据库为空！");
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.BLACK);
        }
        textView.setText(str.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

