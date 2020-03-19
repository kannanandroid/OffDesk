package com.ifazig.optdesk.callback;



public class CommonCallback {
    public interface Listener {
        public void onSuccess(Object object);
        public void onFailure(String reason);
    }

    private Listener listener;
}
