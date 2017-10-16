package com.photophactory.photophactory.welcome;

/**
 * Created by rajaSaboor on 10/1/2017.
 */

public interface WelcomeContract {

    interface ActivityView {
        void startHomeActivity();
    }

    interface Presenter {
        void setWelcomeCompleteSharedPrefs();

        void deleteSharedPrefs();

        boolean isWelcomeIsComplete();

        void skipTutorialActivity();

        void nextTutorialButton(int currentIndex);
    }

    interface FragmentView {
        void nextSlide(int slideIndex);
    }
}
