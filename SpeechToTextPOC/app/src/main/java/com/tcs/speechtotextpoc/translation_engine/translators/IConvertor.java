package com.tcs.speechtotextpoc.translation_engine.translators;

import android.app.Activity;

/**
 * Created by Hitesh on 12-07-2016.
 */
public interface IConvertor {

    public IConvertor initialize(String message, Activity appContext);
}
