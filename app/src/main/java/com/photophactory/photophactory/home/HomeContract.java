package com.photophactory.photophactory.home;

import android.support.v4.app.Fragment;

/**
 * Created by rajaSaboor on 10/7/2017.
 */

public interface HomeContract {
    interface ActivityView {
    }

    interface FragmentView {
        void showToast(String message);
    }

    interface Presenter {
    }
}
