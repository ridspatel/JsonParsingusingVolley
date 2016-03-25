package com.esp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.esp.bean.DataBean;
import com.esp.jsonparsing.R;

public class DataListAdapter extends BaseAdapter {

	public Context context;
	ArrayList<DataBean> dataList = new ArrayList<DataBean>();
	Intent intent = null;

	public DataListAdapter(Context context, ArrayList<DataBean> dataList) {

		this.context = context;
		this.dataList = dataList;

	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return dataList.indexOf(getItem(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (dataList != null && dataList.size() > 0) {

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.row_data, null);

				holder = new Holder();
				holder.txtname = (TextView) convertView
						.findViewById(R.id.textname);
				holder.txtemail = (TextView) convertView
						.findViewById(R.id.textemail);
				holder.txtadd = (TextView) convertView
						.findViewById(R.id.textadd);
				holder.txtmobile = (TextView) convertView
						.findViewById(R.id.textmobile);

				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			final DataBean dataBean = dataList.get(position);

			holder.txtname.setText(dataBean.name);
			holder.txtemail.setText(dataBean.email);
			holder.txtadd.setText(dataBean.address);
			holder.txtmobile.setText(dataBean.mobile);

		}

		return convertView;

	}

	public class Holder {
		private TextView txtname, txtemail, txtadd, txtmobile;
	}
}
