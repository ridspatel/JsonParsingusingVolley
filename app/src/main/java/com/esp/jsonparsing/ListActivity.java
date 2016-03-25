package com.esp.jsonparsing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.esp.adapter.DataListAdapter;
import com.esp.backend.ListAPI;
import com.esp.backend.ResponseListener;
import com.esp.bean.DataBean;
import com.esp.util.Config;
import com.esp.util.Utils;

import java.util.ArrayList;

public class ListActivity extends Activity implements ResponseListener {

    private ListView dataListView;
    private ListAPI listAPI;
    private ArrayList<DataBean> dataList;
    private DataListAdapter dataListAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_list);

        dataListView = (ListView) findViewById(R.id.list);

        callAPI();

    }

    private void callAPI() {
        if (Utils.isOnline(ListActivity.this)) {

            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please Wait...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

            listAPI = new ListAPI(ListActivity.this, ListActivity.this);
            listAPI.execute();

        } else {
            Toast.makeText(ListActivity.this, "Error is occured",
                    Toast.LENGTH_LONG).show();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onResponce(String tag, int result, Object obj) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }

        if (tag.equals(Config.TAG_LIST)) {
            if (result == Config.API_SUCCESS) {

                dataList = new ArrayList<DataBean>();
                dataList = (ArrayList<DataBean>) obj; // get arraylist from ListAPI

                if (dataList != null && dataList.size() > 0) {
                    /* bind adapter */
                    if (dataList != null && dataList.size() > 0) {
                        dataListAdapter = new DataListAdapter(ListActivity.this, dataList);
                        dataListView.setAdapter(dataListAdapter);
                    }
                }
            }
        }
    }
}
