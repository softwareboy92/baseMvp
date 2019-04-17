package com.lv.qm.contract;


import com.lv.monkey.contract.BaseContract;

/**
 * 作者：created by albert on 2018/9/18 16:29
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public interface MainContract {

    interface View extends BaseContract.BaseView{}

     interface Presenter extends BaseContract.BasePresenter<View>{

     }
}
