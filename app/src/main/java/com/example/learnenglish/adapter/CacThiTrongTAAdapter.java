package com.example.learnenglish.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnenglish.R;
import com.example.learnenglish.model.CacThiTA;
import com.example.learnenglish.activity.PdfViewActivity;

import java.util.List;

public class CacThiTrongTAAdapter extends RecyclerView.Adapter<CacThiTrongTAAdapter.ViewHolder> {

    private List<CacThiTA> dataList;
    public Context mContent;

    public CacThiTrongTAAdapter(List<CacThiTA> dataList, Context mContent) {
        this.dataList = dataList;
        this.mContent = mContent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.list_item_thi_tieng_anh, parent, false);

        // Return a new holder instance
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        CacThiTA item = dataList.get(position);

        // Set item views based on your views and data model
        TextView textViewTitle = holder.textViewTitle;
        textViewTitle.setText(item.getTitle());  // Assuming getTitle() is a method in CacThiTA

        TextView textViewNoiDung = holder.textViewNoiDung;
        textViewNoiDung.setText(item.getNoidung());  // Assuming getContent() is a method in CacThiTA

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(item, position);
            }
        });
    }

    private void onClickGoToDetail(CacThiTA item, int position) {
        Intent intent = new Intent(mContent, PdfViewActivity.class);
        intent.putExtra("Title", item.getTitle());
        intent.putExtra("position", position);
        mContent.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    // Provide a direct reference to each of the views within a data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public TextView textViewNoiDung;
        private RelativeLayout layoutItem;

        public ViewHolder(View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewNoiDung = itemView.findViewById(R.id.textViewNoiDung);
        }
    }
}
