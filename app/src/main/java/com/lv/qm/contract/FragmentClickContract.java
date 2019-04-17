package com.lv.qm.contract;

import com.lv.monkey.contract.BaseContract;

/**
 * 作者：create by albert on 2018/12/31 8:46 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public interface FragmentClickContract
{
    interface View extends BaseContract.BaseView{}

    interface Presenter extends BaseContract.BasePresenter<View>{}
}
