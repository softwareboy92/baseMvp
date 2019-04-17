package com.lv.qm.model;

/**
 * 作者：create by albert on 2018/11/24 5:20 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class SayHiModel {


    /**
     * code : 200
     * msg : 处理成功!
     * data : {"exchange":{"id":1,"time":"2018年09月30日16:53:49","commnicate":"曹晖大傻逼"}}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * exchange : {"id":1,"time":"2018年09月30日16:53:49","commnicate":"曹晖大傻逼"}
         */

        private ExchangeBean exchange;

        public ExchangeBean getExchange() {
            return exchange;
        }

        public void setExchange(ExchangeBean exchange) {
            this.exchange = exchange;
        }

        public static class ExchangeBean {
            /**
             * id : 1
             * time : 2018年09月30日16:53:49
             * commnicate : 曹晖大傻逼
             */

            private int id;
            private String time;
            private String commnicate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getCommnicate() {
                return commnicate;
            }

            public void setCommnicate(String commnicate) {
                this.commnicate = commnicate;
            }
        }
    }
}
