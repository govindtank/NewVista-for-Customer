package com.jm.newvista.mvp.view;

import com.jm.newvista.bean.UserReviewEntity;
import com.jm.newvista.mvp.base.BaseView;

import java.util.List;

import lecho.lib.hellocharts.model.ColumnChartData;

/**
 * Created by Johnny on 2/16/2018.
 */

public interface UserReviewView extends BaseView {
    void onSetUserReviewList(List<UserReviewEntity> userReviews);

    void makeToast(String message);

    void onFailLoadingUserReview();

    void onSetUserReviewsCount(int count);

    void onUpdateChart(float score);
}
