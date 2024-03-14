package com.example.learnenglish.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.learnenglish.R;
import com.example.learnenglish.activity.PdfViewActivity;
import com.example.learnenglish.activity.PdfViewNguPhapActivity;
import com.example.learnenglish.model.CacThiTA;
import com.example.learnenglish.model.NguPhap;
import com.squareup.picasso.Picasso;
import java.util.List;

public class NguPhapAdapter extends RecyclerView.Adapter<NguPhapAdapter.ViewHolder> {

    private List<NguPhap> dataListNP;
    private Context mcontextNP;

    public NguPhapAdapter(List<NguPhap> dataListNP, Context mcontextNP) {
        this.dataListNP = dataListNP;
        this.mcontextNP = mcontextNP;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mcontextNP= parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mcontextNP);

        View itemView = inflater.inflate(R.layout.item_nguphap, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position1) {

        NguPhap item = dataListNP.get(position1);

        TextView textViewTitle = holder.textViewTitleNP;
        textViewTitle.setText(item.getTitleNP());  // Assuming getTitle() is a method in CacThiTA

        TextView textViewNoiDung = holder.textViewNoiDungNP;
        textViewNoiDung.setText(item.getTitleNP());  // Assuming getContent() is a method in CacThiTA

        holder.layoutItemNP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(item, position1);
            }
        });
    }
    private void onClickGoToDetail(NguPhap item, int position1) {
        Intent intent = new Intent(mcontextNP, PdfViewNguPhapActivity.class);
        intent.putExtra("TitleNP", item.getTitleNP());
        intent.putExtra("positionNP", position1);
        mcontextNP.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return dataListNP.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitleNP;
        public TextView textViewNoiDungNP;
        private RelativeLayout layoutItemNP;

        public ViewHolder(View itemView) {
            super(itemView);
            layoutItemNP = itemView.findViewById(R.id.layout_itemNP);
            textViewTitleNP = itemView.findViewById(R.id.textViewTitleNP);
            textViewNoiDungNP = itemView.findViewById(R.id.textViewNoiDungNP);
        }
    }
}
