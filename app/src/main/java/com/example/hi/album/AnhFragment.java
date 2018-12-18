package com.example.hi.album;

import android.app.Dialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

//Activity dùng để show tất cả ảnh trong điện thoại theo ngày chụp
public class AnhFragment extends android.support.v4.app.Fragment implements FragmentCallbacks {

    Context context = null;
    Uri Image_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    static ArrayList<Hinh> mangHinh = new ArrayList<>();

    static ArrayList<ArrayList<Hinh>> mangHinhDate = new ArrayList<>();

    static Map<Integer, ArrayList<Hinh>> mapImage = new TreeMap<>(Collections.<Integer>reverseOrder());
    boolean hasNewChanged = false;

    //listview chua nhieu recycleview
    ListView listView;
    CustomListviewImageAdapter customListviewImageAdapter;

    View view;

    @Nullable


    @Override
    public void onResume() {
        long start = System.currentTimeMillis();
        super.onResume();

        hasNewChanged = false;
        //***Khởi tạo các mảng***//
//
//        mangHinh = new ArrayList<>();
//        mangHinhDate = new ArrayList<>();

        ContentResolver contentResolver = getActivity().getContentResolver();

        Cursor cursor = contentResolver.query(Image_URI, null, null, null, null);
        cursor.moveToLast();
        while (!cursor.isBeforeFirst()) {
            final String duongdanhinhanh = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String tenhinh = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            File TempFiles = new File(duongdanhinhanh);
            Date lastModDate = new Date(TempFiles.lastModified());
            SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
            Integer dateText = Integer.valueOf(df2.format(lastModDate));
            if (TempFiles.exists()) {
                Hinh currentImage = new Hinh(duongdanhinhanh, tenhinh, dateText);
                    mangHinh.add(currentImage);
                if (mapImage.containsKey(dateText)) {
                    {
                        if (!mapImage.get(dateText).contains(currentImage)) {
                            mapImage.get(dateText).add(currentImage);
                            hasNewChanged = true;
                        }
                    }
                } else {
                    hasNewChanged = true;
                    ArrayList<Hinh> temp = new ArrayList<>();
                    temp.add(currentImage);
                    mapImage.put(dateText, temp);
                }

            }
            cursor.moveToPrevious();
        }
        if (hasNewChanged) {
            mangHinhDate.clear();
            mangHinhDate.addAll(mapImage.values());
            customListviewImageAdapter = new CustomListviewImageAdapter(context, mangHinhDate, R.layout.custom_item_listview_img);
            listView.setAdapter(customListviewImageAdapter);
            listView.setDivider(null);
            listView.setFastScrollEnabled(true);

        }


        MainActivity.funcExecuteTime.put("onResume AnhFragment", System.currentTimeMillis() - start);

        //
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        view = inflater.inflate(R.layout.anh_layout, container, false);
        listView = (ListView) view.findViewById(R.id.lvimg);


        return view;
    }


    @Override
    public void onMsgFromMainToFragment(String strValue) {
    }
}
