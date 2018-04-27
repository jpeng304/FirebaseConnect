package com.example.jpeng.teacherapp.adapter;

/**
 * Created by jpeng on 4/25/2018.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jpeng.teacherapp.R;
import com.example.jpeng.teacherapp.model.Student;

import java.util.List;

public class ListStudentInfo extends BaseAdapter{
    private Context mContext;
    private List<Student> mStudentList;
    public ListStudentInfo(Context mContext, List<Student> mProductList) {
        this.mContext = mContext;
        this.mStudentList = mProductList;
    }

    @Override
    public int getCount() {
        return mStudentList.size();
    }

    @Override
    public Object getItem(int i) {
        return mStudentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mStudentList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.student_listview_model,null);
        TextView tvStudent_name = (TextView)v.findViewById(R.id.txtName);
        TextView tvStudent_id = (TextView)v.findViewById(R.id.txtID);
        tvStudent_name.setText(mStudentList.get(i).getStudent_name());
        tvStudent_id.setText(mStudentList.get(i).getStudent_ID());
        return v;
    }
}
