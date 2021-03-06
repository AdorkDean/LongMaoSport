package com.live.longmao.fragment.person;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.model.CointoBeanInfo;
import com.live.longmao.presenters.CoinToBeanHelper;
import com.live.longmao.presenters.RechargeCoinHelper;
import com.live.longmao.presenters.viewinface.CoinToBeanView;
import com.live.longmao.presenters.viewinface.RechargeCoinView;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;
import com.pingplusplus.android.Pingpp;

import okhttp3.Call;

/**
 * Created by HPDN on 2017/1/9.
 */
public class LMOneFragment extends Fragment implements CoinToBeanView, RechargeCoinView, View.OnClickListener {

    private TextView phone, tv1, tv2, tv3, tv4, tv5, tv6, m1, m2, m3, m4, m5, m6;
    private RelativeLayout rl_one, rl_two, rl_three, rl_four, rl_five, rl_six;
    private Button coin_pay;
    private RadioGroup rg_select;
    private RadioButton rbtn_zfbpay, rbtn_wxpay;
    private CoinToBeanHelper coinToBeanHelper;
    private String ply;
    private String number = "";
    private int pay = 1;
    //private EditText et_money;
//    private TextView coin;
    

    private static final int SDK_PAY_FLAG = 1;
    private Handler mHandler;

    private RechargeCoinHelper rechargeCoinHelper;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_coin, null);
            initInfo();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;

    }

    private void initInfo() {
        coinToBeanHelper = new CoinToBeanHelper(this);
        rechargeCoinHelper = new RechargeCoinHelper(this);

        coin_pay = (Button) view.findViewById(R.id.coin_pay);
        //   phone = (TextView) findViewById(R.id.phone);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        tv4 = (TextView) view.findViewById(R.id.tv4);
        tv5 = (TextView) view.findViewById(R.id.tv5);
        tv6 = (TextView) view.findViewById(R.id.tv6);
        m1 = (TextView) view.findViewById(R.id.tv_m1);
        m2 = (TextView) view.findViewById(R.id.tv_m2);
        m3 = (TextView) view.findViewById(R.id.tv_m3);
        m4 = (TextView) view.findViewById(R.id.tv_m4);
        m5 = (TextView) view.findViewById(R.id.tv_m5);
        m6 = (TextView) view.findViewById(R.id.tv_m6);
        rl_one = (RelativeLayout) view.findViewById(R.id.rl_one);
        rl_two = (RelativeLayout) view.findViewById(R.id.rl_two);
        rl_three = (RelativeLayout) view.findViewById(R.id.rl_three);
        rl_four = (RelativeLayout) view.findViewById(R.id.rl_four);
        rl_five = (RelativeLayout) view.findViewById(R.id.rl_five);
        rl_six = (RelativeLayout) view.findViewById(R.id.rl_six);

        rg_select = (RadioGroup) view.findViewById(R.id.rg_select);
        rbtn_zfbpay = (RadioButton) view.findViewById(R.id.rbtn_zfbpay);
        rbtn_wxpay = (RadioButton) view.findViewById(R.id.rbtn_wxpay);

//        coin = (TextView) view.findViewById(R.id.coin);

//        et_money = (EditText) view.findViewById(R.id.et_money);
//        et_money.setOnClickListener(this);
//        et_money.addTextChangedListener(PlyWatcher);

        coin_pay.setOnClickListener(this);
        //  phone.setOnClickListener(this);
        rl_one.setOnClickListener(this);
        rl_two.setOnClickListener(this);
        rl_three.setOnClickListener(this);
        rl_four.setOnClickListener(this);
        rl_five.setOnClickListener(this);
        rl_six.setOnClickListener(this);
        rbtn_zfbpay.setOnClickListener(this);
        rbtn_wxpay.setOnClickListener(this);
    }


    private void initBgOne() {
        rl_one.setBackgroundResource(R.drawable.btn_money_share_bg_on);
        tv1.setTextColor(Color.WHITE);
        m1.setTextColor(Color.WHITE);

        rl_two.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_three.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_four.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_five.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_six.setBackgroundResource(R.drawable.btn_money_share_bg);

        tv2.setTextColor(getResources().getColor(R.color.title_color));
        tv3.setTextColor(getResources().getColor(R.color.title_color));
        tv4.setTextColor(getResources().getColor(R.color.title_color));
        tv5.setTextColor(getResources().getColor(R.color.title_color));
        tv6.setTextColor(getResources().getColor(R.color.title_color));

        m2.setTextColor(getResources().getColor(R.color.title_color));
        m3.setTextColor(getResources().getColor(R.color.title_color));
        m4.setTextColor(getResources().getColor(R.color.title_color));
        m5.setTextColor(getResources().getColor(R.color.title_color));
        m6.setTextColor(getResources().getColor(R.color.title_color));
    }

    private void initBgTwo() {
        rl_two.setBackgroundResource(R.drawable.btn_money_share_bg_on);
        tv2.setTextColor(Color.WHITE);
        m2.setTextColor(Color.WHITE);

        rl_one.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_three.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_four.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_five.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_six.setBackgroundResource(R.drawable.btn_money_share_bg);

        tv1.setTextColor(getResources().getColor(R.color.title_color));
        tv3.setTextColor(getResources().getColor(R.color.title_color));
        tv4.setTextColor(getResources().getColor(R.color.title_color));
        tv5.setTextColor(getResources().getColor(R.color.title_color));
        tv6.setTextColor(getResources().getColor(R.color.title_color));

        m1.setTextColor(getResources().getColor(R.color.title_color));
        m3.setTextColor(getResources().getColor(R.color.title_color));
        m4.setTextColor(getResources().getColor(R.color.title_color));
        m5.setTextColor(getResources().getColor(R.color.title_color));
        m6.setTextColor(getResources().getColor(R.color.title_color));
    }

    private void initBgThree() {
        rl_three.setBackgroundResource(R.drawable.btn_money_share_bg_on);
        tv3.setTextColor(Color.WHITE);
        m3.setTextColor(Color.WHITE);

        rl_one.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_two.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_four.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_five.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_six.setBackgroundResource(R.drawable.btn_money_share_bg);

        tv1.setTextColor(getResources().getColor(R.color.title_color));
        tv2.setTextColor(getResources().getColor(R.color.title_color));
        tv4.setTextColor(getResources().getColor(R.color.title_color));
        tv5.setTextColor(getResources().getColor(R.color.title_color));
        tv6.setTextColor(getResources().getColor(R.color.title_color));

        m1.setTextColor(getResources().getColor(R.color.title_color));
        m2.setTextColor(getResources().getColor(R.color.title_color));
        m4.setTextColor(getResources().getColor(R.color.title_color));
        m5.setTextColor(getResources().getColor(R.color.title_color));
        m6.setTextColor(getResources().getColor(R.color.title_color));
    }

    private void initBgFour() {
        rl_four.setBackgroundResource(R.drawable.btn_money_share_bg_on);
        tv4.setTextColor(Color.WHITE);
        m4.setTextColor(Color.WHITE);
        rl_one.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_two.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_three.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_five.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_six.setBackgroundResource(R.drawable.btn_money_share_bg);
        tv1.setTextColor(getResources().getColor(R.color.title_color));
        tv2.setTextColor(getResources().getColor(R.color.title_color));
        tv3.setTextColor(getResources().getColor(R.color.title_color));
        tv5.setTextColor(getResources().getColor(R.color.title_color));
        tv6.setTextColor(getResources().getColor(R.color.title_color));

        m1.setTextColor(getResources().getColor(R.color.title_color));
        m2.setTextColor(getResources().getColor(R.color.title_color));
        m3.setTextColor(getResources().getColor(R.color.title_color));
        m5.setTextColor(getResources().getColor(R.color.title_color));
        m6.setTextColor(getResources().getColor(R.color.title_color));
    }

    private void initBgFive() {
        rl_five.setBackgroundResource(R.drawable.btn_money_share_bg_on);
        tv5.setTextColor(Color.WHITE);
        m5.setTextColor(Color.WHITE);
        rl_one.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_two.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_four.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_three.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_six.setBackgroundResource(R.drawable.btn_money_share_bg);
        tv1.setTextColor(getResources().getColor(R.color.title_color));
        tv2.setTextColor(getResources().getColor(R.color.title_color));
        tv4.setTextColor(getResources().getColor(R.color.title_color));
        tv3.setTextColor(getResources().getColor(R.color.title_color));
        tv6.setTextColor(getResources().getColor(R.color.title_color));

        m1.setTextColor(getResources().getColor(R.color.title_color));
        m2.setTextColor(getResources().getColor(R.color.title_color));
        m4.setTextColor(getResources().getColor(R.color.title_color));
        m3.setTextColor(getResources().getColor(R.color.title_color));
        m6.setTextColor(getResources().getColor(R.color.title_color));
    }

    private void initBgSix() {
        rl_six.setBackgroundResource(R.drawable.btn_money_share_bg_on);
        tv6.setTextColor(Color.WHITE);
        m6.setTextColor(Color.WHITE);
        rl_one.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_two.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_four.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_five.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_three.setBackgroundResource(R.drawable.btn_money_share_bg);
        tv1.setTextColor(getResources().getColor(R.color.title_color));
        tv2.setTextColor(getResources().getColor(R.color.title_color));
        tv3.setTextColor(getResources().getColor(R.color.title_color));
        tv4.setTextColor(getResources().getColor(R.color.title_color));
        tv5.setTextColor(getResources().getColor(R.color.title_color));

        m1.setTextColor(getResources().getColor(R.color.title_color));
        m2.setTextColor(getResources().getColor(R.color.title_color));
        m3.setTextColor(getResources().getColor(R.color.title_color));
        m4.setTextColor(getResources().getColor(R.color.title_color));
        m5.setTextColor(getResources().getColor(R.color.title_color));
    }

    private void initBgSeven() {
        rl_one.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_two.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_four.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_five.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_three.setBackgroundResource(R.drawable.btn_money_share_bg);
        rl_six.setBackgroundResource(R.drawable.btn_money_share_bg);
        tv1.setTextColor(getResources().getColor(R.color.title_color));
        tv2.setTextColor(getResources().getColor(R.color.title_color));
        tv3.setTextColor(getResources().getColor(R.color.title_color));
        tv4.setTextColor(getResources().getColor(R.color.title_color));
        tv5.setTextColor(getResources().getColor(R.color.title_color));
        tv6.setTextColor(getResources().getColor(R.color.title_color));

        m1.setTextColor(getResources().getColor(R.color.title_color));
        m2.setTextColor(getResources().getColor(R.color.title_color));
        m3.setTextColor(getResources().getColor(R.color.title_color));
        m4.setTextColor(getResources().getColor(R.color.title_color));
        m5.setTextColor(getResources().getColor(R.color.title_color));
        m6.setTextColor(getResources().getColor(R.color.title_color));
    }

    @Override
    public void onCoinToBeanSucc(CointoBeanInfo result) {

    }

    @Override
    public void onCtoBError(String msg) {

    }


    /**
     * onActivityResult 获得支付结果，如果支付成功，服务器会收到ping++ 服务器发送的异步通知。
     * 最终支付成功根据异步通知为准
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getExtras().getString("pay_result");
                /* 处理返回值
                 * "success" - payment succeed
                 * "fail"    - payment failed
                 * "cancel"  - user canceld
                 * "invalid" - payment plugin not installed
                 */
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息


                showMsg(result, errorMsg, extraMsg);
                // rechargeCoinHelper.getRechargeCoin(number);
            }
        }
    }

    public void showMsg(String title, String msg1, String msg2) {
        String str = title;
        if (null != msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null != msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }

        if (title.equals("success")) {
            rechargeCoinHelper.getRechargeCoin(number);
            Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", null);
        builder.create().show();
    }

    public void getPingPly() {

        OkHttpUtils
                .post()
                        // .url("http://121.40.65.153:82/charge/createCharge")
                .url(OkHttpUtils.Ping_Url)
                .addParams("channel", ply)
                .addParams("amount", number)
                .build()
                .execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.i("支付的接口回调", response);
                        // Pingpp.createPayment(ClientSDKActivity.this, response, "qwalletXXXXXXX");
                        Pingpp.createPayment(getActivity(), response);


                    }
                });
    }


    @Override
    public void onRechargeCoinSucc(String result) {

    }

    @Override
    public void onRechargeCoinErr(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_one:
                number = "600";
                initBgOne();
                break;
            case R.id.rl_two:
                number = "3000";
                initBgTwo();
                break;
            case R.id.rl_three:
                number = "9800";
                initBgThree();
                break;
            case R.id.rl_four:
                number = "29800";
                initBgFour();
                break;
            case R.id.rl_five:
                number = "58800";
                initBgFive();
                break;
            case R.id.rl_six:
                number = "199900";
                initBgSix();
                break;
            case R.id.coin_pay:
                if (!number.equals("")) {
                    if (pay == 1) {
                        ply = "alipay";
                        getPingPly();
                    } else if (pay == 2) {
                        ply = "wx";
                        getPingPly();
                    }
                } else {
                    Toast.makeText(getContext(), "请输入金额", Toast.LENGTH_SHORT).show();
                }
                break;
//            case R.id.et_money:
//
//                initBgSeven();
//                break;

            case R.id.rbtn_zfbpay:
                pay = 1;
                break;
            case R.id.rbtn_wxpay:
                pay = 2;
                break;

        }
    }


//    private TextWatcher PlyWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            coin.setText(s + "  元");
//        }
//    };


}
