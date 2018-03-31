package com.jm.newvista.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.PaymentModel;
import com.jm.newvista.mvp.presenter.PaymentPresenter;
import com.jm.newvista.mvp.view.PaymentView;
import com.jm.newvista.ui.base.BaseActivity;
import com.twisty.ppv.PayPasswordView;

public class PaymentActivity
        extends BaseActivity<PaymentModel, PaymentView, PaymentPresenter>
        implements PaymentView {
    private Toolbar toolbar;
    private TextView movieTitle;
    private TextView showtime;
    private TextView seat;
    private TextView totalPrice;
    private ImageView poster;
    private ImageView avatar;
    private PayPasswordView payPasswordView;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initView();

        getPresenter().updateView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener((v) -> {
            finish();
        });

        movieTitle = findViewById(R.id.movieTitle);
        showtime = findViewById(R.id.showtime);
        seat = findViewById(R.id.seat);
        poster = findViewById(R.id.poster);
        avatar = findViewById(R.id.avatar);
        payPasswordView = findViewById(R.id.payPasswordView);
        payPasswordView.setOnInputDoneListener(result -> inputDone(result));
        clear = findViewById(R.id.clear);
        totalPrice = findViewById(R.id.totalPrice);
    }

    private void inputDone(String result) {
        getPresenter().postPay();
    }

    public void onClickClear(View view) {
        payPasswordView.clear();
    }

    @Override
    public PaymentView createView() {
        return this;
    }

    @Override
    public PaymentPresenter createPresenter() {
        return new PaymentPresenter();
    }

    @Override
    public Intent onGetIntent() {
        return getIntent();
    }

    @Override
    public TextView onGetMovieTitle() {
        return movieTitle;
    }

    @Override
    public TextView onGetShowtime() {
        return showtime;
    }

    @Override
    public TextView onGetSeat() {
        return seat;
    }

    @Override
    public TextView onGetTotalPrice() {
        return totalPrice;
    }

    @Override
    public ImageView onGetPoster() {
        return poster;
    }

    @Override
    public ImageView onGetAvatar() {
        return avatar;
    }
}
