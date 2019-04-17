package com.lv.qm.contract;

import com.lv.monkey.contract.BaseContract;

/**
 * 作者：created by albert on 2019/1/8 14:10
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public interface FragmentLazyContract {
    interface View extends BaseContract.BaseView{}

    interface Presenter extends BaseContract.BasePresenter<View>{}
}
