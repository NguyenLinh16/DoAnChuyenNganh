package com.example.learnenglish.database;

import android.content.Context;
import android.database.Cursor;

import com.example.learnenglish.model.Question;

import java.util.ArrayList;

public class TestItemDatabase extends DBHelper {
    private Context context;

    public TestItemDatabase(Context context) {
        super(context);
    }

    //Phương thức này nhận vào số bài kiểm tra (baiTest) và
    // trả về danh sách các câu hỏi được chọn ngẫu nhiên từ cơ sở dữ liệu.
    public ArrayList<Question> getListQuestion(int baiTest) {
        // Khởi tạo đối tượng Question để chứa thông tin của mỗi câu hỏi.
        Question question=null;
        ArrayList<Question> itemList = new ArrayList<>();
        //Tạo một đối tượng TestItemDatabase và mở cơ sở dữ liệu SQLite.
        // Sau đó, sử dụng câu lệnh SQL để lấy 10 câu hỏi ngẫu nhiên từ bảng Test
        // với điều kiện là baiTest phải bằng giá trị được truyền vào
        TestItemDatabase database = new TestItemDatabase(context);
        database.openDatabase();
        //Di chuyển con trỏ của Cursor đến vị trí đầu tiên và
        // sau đó lặp qua tất cả các dòng để tạo các đối tượng Question và thêm vào danh sách itemList.
        Cursor cursor = database.getDataFromSQLite("SELECT * FROM Test WHERE baiTest = '"+baiTest+"' ORDER BY RANDOM() LIMIT 10");
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            question= new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),"");
            itemList.add(question);
            cursor.moveToNext();
        }
        cursor.close();
        database.closeDatabase();
        return itemList;
    }
}
