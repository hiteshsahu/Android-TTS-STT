package com.tcs.speechtotextpoc.translation_engine.utils; 
 
/** 
 * Created by Hitesh on 12-07-2016. 
 */ 
public interface ConversionCallaback { 
 
    public void onSuccess(String result);
 
    public void onCompletion(); 
 
    public void onErrorOccured(String errorMessage);
 
} 