package com.example.learnenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.learnenglish.model.Question;
import com.example.learnenglish.R;

import java.util.ArrayList;

public class CheckAnswerAdapter extends BaseAdapter{
    Context context;
    ArrayList arrayList;

    public CheckAnswerAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    //Trả về số lượng phần tử trong danh sách câu hỏi.
    public int getCount() {
        return arrayList.size();
    }

    @Override
    //Trả về đối tượng câu hỏi tại vị trí cụ thể trong danh sách.
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    //Phương thức này được gọi khi mỗi hàng trong danh sách cần được hiển thị.
    // Nó kiểm tra xem convertView có được tạo mới chưa, và nếu chưa, nó tạo một ViewHolder mới và inflate layout từ resource.
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_dongdapan, null);
            holder.txtCau=(TextView) convertView.findViewById(R.id.textview_cau);
            holder.txtDapan=(TextView) convertView.findViewById(R.id.textview_dapan);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder) convertView.getTag();
        }
        Question question= (Question) getItem(position);
        int i= position+1;
        holder.txtCau.setText("Câu "+i+": ");
        holder.txtDapan.setText(question.getTraLoi());
        return convertView;
    }
    class ViewHolder{
        TextView txtCau, txtDapan;
    }
}
