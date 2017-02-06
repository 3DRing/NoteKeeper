package com.ringov.notekeeper.presenter.android_relations_providers;

import android.content.SharedPreferences;

/**
 * Created by Сергей on 04.02.2017.
 */

public interface SharedPreferencesProvider {
    SharedPreferences getSharedPreferences(String name);
}
