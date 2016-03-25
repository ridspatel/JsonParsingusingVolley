package com.esp.backend;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.esp.bean.DataBean;
import com.esp.util.Config;
import com.esp.util.Log;

public class ListAPI {
	private Context mCaller;
	private HashMap<String, String> mParams = null;
	private Adapter mAdapter = null;
	private ResponseListener responseListener;

	public ListAPI(Context caller, ResponseListener responseListener) {
		this.mCaller = caller;
		this.mParams = new HashMap<String, String>();
		this.responseListener = responseListener;
	}

	public void execute() {

		this.mAdapter = new Adapter(this.mCaller);
		this.mAdapter.doPost(Config.TAG_LIST, Config.API_LIST, mParams,
				new APIResponseListener() {

					@Override
					public void onResponse(String response) {
						mParams = null;
						// Parse Response and Proceed Further
						parse(response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						mParams = null;
						// Inform Caller that the API call is failed
						responseListener.onResponce(Config.TAG_LIST,
								Config.API_FAIL, null);
					}
				});
	}

	/*
	 * Parse the response and prepare for callback
	 */
	private void parse(String response) {
		int code = 0;
		String msg = null;
		JSONObject jsonObj = null;
		JSONArray jsonArray = null;
		ArrayList<DataBean> dataList = null;
		DataBean dataBean = null;

		try {
			jsonObj = new JSONObject(response);

			JSONArray contacts = jsonObj.getJSONArray("contacts");
			if (contacts != null && contacts.length() > 0) {
				
				dataList = new ArrayList<DataBean>();

				for (int i = 0; i < contacts.length(); i++) {
					JSONObject c = contacts.getJSONObject(i);
					dataBean = new DataBean();

					dataBean.id = c.getString("id");
					dataBean.name = c.getString("name");
					dataBean.email = c.getString("email");
					dataBean.address = c.getString("address");
					dataBean.gender = c.getString("gender");

					JSONObject phone = c.getJSONObject("phone");
					dataBean.mobile = phone.getString("mobile");
					dataBean.home = phone.getString("home");
					dataBean.office = phone.getString("office");

					dataList.add(dataBean);
				}
				System.out
						.println("==================DataList.Size================="
								+ dataList.size());
			}

		} catch (Exception e) {
			code = -1;
			msg = "Exception :: " + this.getClass() + " :: parse() :: "
					+ e.getLocalizedMessage();
			Log.error(this.getClass() + " :: Exception :: ", e);
			Log.print(this.getClass() + " :: Exception :: ", e);
		} finally {
			response = null;
			/** release variables */
			jsonObj = null;
			jsonArray = null;
			dataBean = null;
			System.gc();
		}

		this.doCallBack(code, msg, dataList);
	}

	/*
	 * Send control back to the caller which includes
	 * 
	 * Status: Successful or Failure Message: Its an Object, if required
	 */
	private void doCallBack(int code, String mesg, ArrayList<DataBean> dataList) {

		try {
			if (code == 0) {
				responseListener.onResponce(Config.TAG_LIST,
						Config.API_SUCCESS, dataList);
			} else if (code > 0) {
				// AlertDailogView.showAlert(mCaller, mesg, true).show();
				responseListener.onResponce(Config.TAG_LIST, Config.API_FAIL,
						null);
			} else if (code < 0) {
				responseListener.onResponce(Config.TAG_LIST, Config.API_FAIL,
						null);
			}
		} catch (Exception e) {
			Log.error(this.getClass() + " :: Exception :: ", e);
			Log.print(this.getClass() + " :: Exception :: ", e);
		} finally {
			mAdapter = null;
			mesg = null;
		}
	}

	/*
	 * Cancel API Request
	 */
	public void doCancel() {
		if (mAdapter != null)
			mAdapter.doCancel(Config.TAG_LIST);
	}
}