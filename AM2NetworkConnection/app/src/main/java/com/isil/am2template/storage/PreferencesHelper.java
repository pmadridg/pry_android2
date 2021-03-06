
package com.isil.am2template.storage;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;


public class PreferencesHelper {


    private static final String MYNOTES_PREFERENCES = "mynotesPreferences";
    private static final String PREFERENCES_USERNAME = MYNOTES_PREFERENCES + ".username";
    private static final String PREFERENCES_PASSWORD = MYNOTES_PREFERENCES + ".password";
    private static final String PREFERENCES_GRABADOS = MYNOTES_PREFERENCES + ".grabados";
    private static final String PREFERENCES_TOKEN = MYNOTES_PREFERENCES + ".token";
    private static final String PREFERENCES_OBJECTID = MYNOTES_PREFERENCES + ".objectId";

    private static List<String> cart;

    private PreferencesHelper() {
        //no instance
    }

    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCES_USERNAME);
        editor.remove(PREFERENCES_PASSWORD);
        editor.apply();
    }

    public static void saveSession(Context context,String username,String password)
    {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_USERNAME, username);
        editor.putString(PREFERENCES_PASSWORD, password);
        editor.apply();
    }


    public static void saveBLSession(Context context,String objectId,String username,String token)

    {

        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_OBJECTID, objectId);
        editor.putString(PREFERENCES_USERNAME, username);
        editor.putString(PREFERENCES_TOKEN, token);
        editor.apply();

    }

    public static String getUserSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String username= sharedPreferences.getString(PREFERENCES_USERNAME,null);

        return username;
    }

    public static boolean isSignedIn(Context context) {
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(PREFERENCES_USERNAME) &&
                preferences.contains(PREFERENCES_PASSWORD);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MYNOTES_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void setGrabados(Context context,String grabados)
    {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_GRABADOS, grabados);
        editor.apply();
    }


    public static String getGrabados(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String grabados= sharedPreferences.getString(PREFERENCES_GRABADOS,"");

        return grabados;
    }
}
