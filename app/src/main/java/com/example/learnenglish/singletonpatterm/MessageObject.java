package com.example.learnenglish.singletonpatterm;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.learnenglish.R;

public class MessageObject {

    private static MessageObject instance = new MessageObject();

    public MessageObject() {
    }

    public static  MessageObject getInstance(){return instance;}
// khai báo phương thức.
    public void ShowDialogMessage (int gravity, Context context, String message, int type){
        //Khởi tạo đối tượng Dialog.
        final Dialog dialog = new Dialog(context);
        //Tạo một đối tượng "Dialog" để hiển thị thông báo
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Set đặt nội dung của cửa sổ dialog từ một tệp layout được xxacs định bởi R.layout.thongbao
        dialog.setContentView(R.layout.thongbao);
        //Cấu hình cửa sổ Dialog
        Window window = dialog.getWindow();
        //Lấy cửa sổ của dialog và kiểm tra xem có tồn tại hay không
        if(window == null){
            return;
        }
        //Thiết lập kích thước của cửa sổ dialog và đặt màu nền trong suốt
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Cấu hình vị trí của cửa sổ.
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        // Lây thuộc tính cửa sổ và thiết lập trọng lực của nó theo giá trị truyền vào từ tham số gravity
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        //Quyết định tính nghỉ đông của dialog
        //Nếu trọng lực là Gravity.BOTTOM (đáy), thì cho phép dialog có thể bị hủy bỏ (setCancelable(true)), ngược lại là không cho phép hủy bỏ (setCancelable(false)).
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }

        // Tìm kiếm các phần tử trong layout
        //tìm kiếm các phần tử trog layout bằng cách sử dụng phương thức "findViewById"
        TextView txt_name = (TextView) dialog.findViewById(R.id.dialogError2_name);
        TextView txt_message = (TextView) dialog.findViewById(R.id.dialogError2_content);
        Button btn_oke = (Button) dialog.findViewById(R.id.btn_dialogError_Oke);

        //Thiết lập nội dung và loại thông báo
        //Dựa vào giá trị của tham số type, thiết lập nội dung của txt_name thành "SUCCESS" hoặc "ERROR".
        //Thiết lập nội dung của txt_message thành giá trị của tham số message.
        if(type == 1) txt_name.setText("SUCCESS");
        else txt_name.setText("ERROR");
        txt_message.setText(message);
        //đóng cửa sổ dialog
        btn_oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //Hiển thị cửa sổ dialog để người dùng thấy thông báo
        dialog.show();
    }
}
