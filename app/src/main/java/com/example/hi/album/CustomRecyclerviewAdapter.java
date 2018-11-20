package com.example.hi.album;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//***Custom các RecyclerView có trong Project***//
public class CustomRecyclerviewAdapter extends RecyclerView.Adapter<CustomRecyclerviewAdapter.MyViewHolder> {
    private Context context;

    private ArrayList<Hinh> data;

    //***album=true hoac kho ảnh=false***//
    boolean loai;
    //***Layout muốn dán***//
    private LayoutInflater inflater;
    private int pos;


    //    public CustomRecyclerviewAdapter(Context context, ArrayList<Hinh> data,boolean loai) {
//
//        this.context = context;
//        this.data = data;
//        inflater = LayoutInflater.from(context);
//        this.loai=loai;
//        this.pos=-1;
//    }
    public CustomRecyclerviewAdapter(Context context, ArrayList<Hinh> data, boolean loai, int pos) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.loai = loai;
        this.pos = pos;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.custom_item_recyclerview, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {
        long start = System.currentTimeMillis();
        File file = new File(data.get(position).getDuongdan());
        Glide.with(context).load(file)
                .into(myViewHolder.imageView);
        myViewHolder.checkBox.setChecked(data.get(position).check);
//        previousPosition = position;
//        final int currentPosition = position;
//        final Hinh infoData = data.get(position);
        if (MainActivity.status == true) {
            myViewHolder.linearLayout.setVisibility(View.VISIBLE);
        } else {
            myViewHolder.linearLayout.setVisibility(View.INVISIBLE);
        }
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //chuyen man hinh image activity
                Intent intent = new Intent(context, ImageActivity.class);
                intent.putExtra("vitri", data.get(position).getDuongdan());
                intent.putExtra("loai", loai);
                context.startActivity(intent);

            }
        });
        myViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder.checkBox.isChecked() == true) {
                    MainActivity.collectedimgs.add(new Hinh(data.get(position).getDuongdan()
                            , data.get(position).getTenhinh()
                            , data.get(position).getAddDate()));
                    if (loai == false)
                        AnhFragment.mangHinhDate.get(pos).get(position).setCheck(true);
                    else if (loai == true) {
                        MainActivity.mang.get(pos).get(position).setCheck(true);
                    }
                } else {
                    for (int i = 0; i < MainActivity.collectedimgs.size(); i++) {
                        if (MainActivity.collectedimgs.get(i).getDuongdan().equals(data.get(position).duongdan)) {
                            MainActivity.collectedimgs.remove(i);
                        }
                    }
                    if (loai == false)
                        AnhFragment.mangHinhDate.get(pos).get(position).setCheck(false);
                    else if (loai == true) {
                        MainActivity.mang.get(pos).get(position).setCheck(false);
                    }
                }
            }

        });


        MainActivity.funcExecuteTime.put("onBindViewHolder CustomRecyclerviewAdapter", System.currentTimeMillis() - start);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout linearLayout;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgrv);
            linearLayout = itemView.findViewById(R.id.LNofcardview);
            checkBox = itemView.findViewById(R.id.Check);

        }
    }
}