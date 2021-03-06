package com.jm.newvista.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.newvista.R;
import com.jm.newvista.mvp.model.OrderHistoryModel;
import com.jm.newvista.mvp.presenter.OrderHistoryPresenter;
import com.jm.newvista.mvp.view.OrderHistoryView;
import com.jm.newvista.ui.adapter.OrderHistoryRecyclerViewAdapter;
import com.jm.newvista.ui.base.BaseActivity;

public class OrderHistoryActivity
        extends BaseActivity<OrderHistoryModel, OrderHistoryView, OrderHistoryPresenter>
        implements OrderHistoryView,
        PopupMenu.OnMenuItemClickListener {
    private Toolbar toolbar;
    private TextView sortByOrderDatetime;
    private ImageButton sort;
    private RecyclerView orderHistoryRecyclerView;

    private OrderHistoryRecyclerViewAdapter orderHistoryRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        initView();
        getPresenter().getAndDisplayOrderHistory();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        sortByOrderDatetime = findViewById(R.id.sortByOrderDatetime);
        sort = findViewById(R.id.sort);
        orderHistoryRecyclerView = findViewById(R.id.orderHistoryRecyclerView);

        orderHistoryRecyclerViewAdapter = new OrderHistoryRecyclerViewAdapter();
        orderHistoryRecyclerView.setAdapter(orderHistoryRecyclerViewAdapter);

        linearLayoutManager = new LinearLayoutManager(this);
        orderHistoryRecyclerView.setLayoutManager(linearLayoutManager);

        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, R.anim
                .animation_layout_from_bottom_to_top);
        orderHistoryRecyclerView.setLayoutAnimation(animation);
    }

    public void onClickSort(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.fragment_user_review_sort, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newestFirst:
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                break;
            case R.id.oldestFirst:
                linearLayoutManager.setReverseLayout(false);
                linearLayoutManager.setStackFromEnd(false);
                break;
        }
        return false;
    }

    @Override
    public OrderHistoryView createView() {
        return this;
    }

    @Override
    public OrderHistoryPresenter createPresenter() {
        return new OrderHistoryPresenter();
    }

    @Override
    public OrderHistoryRecyclerViewAdapter onGetOrderHistoryRecyclerViewAdapter() {
        return orderHistoryRecyclerViewAdapter;
    }

    @Override
    public RecyclerView onGetOrderHistoryRecyclerView() {
        return orderHistoryRecyclerView;
    }

    @Override
    public void onMakeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
