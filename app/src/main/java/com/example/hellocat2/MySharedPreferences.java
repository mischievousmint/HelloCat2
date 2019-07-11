package com.example.hellocat2;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MySharedPreferences {

    private  static SharedPreferences mySharedPref;
    static final String KEYTITLE = "cat";
/*    static final String KEYIMAGE = "image";
    static final Double KEYLAT = "";
    static final Double KEYLON = "";*/

    private MySharedPreferences() {

    }

    public static void  init (Context context) {
        if(mySharedPref == null) {
            mySharedPref = context.getSharedPreferences("MYPREFS", AppCompatActivity.MODE_PRIVATE); //MYPREFS es el nombre del a BBDD
        }
    }


    // SAVE MODEL

    public static void  saveCat(CatModel catModel) {
        SharedPreferences.Editor prefsEditor = mySharedPref.edit();

        Gson gson = new Gson();
        String json = gson.toJson(catModel);
        prefsEditor.putString(KEYTITLE, json);

        prefsEditor.apply();
    }


    // LOAD MODEL

    public static CatModel loadCat() {

        Gson gson = new Gson();
        String json = mySharedPref.getString(KEYTITLE, "");
        CatModel catModel = gson.fromJson(json, CatModel.class);

        return catModel;
    }

    // SAVE LIST

    public static void saveCatInArray(ArrayList<CatModel> arrayListCat){
        SharedPreferences.Editor prefsEditor = mySharedPref.edit();

        Gson gson = new Gson();
        String json = gson.toJson(arrayListCat);
        prefsEditor.putString(KEYTITLE, json);
    }



    // SAVE MODEL (JOSE)
        public static void saveCat2(CatModel catModel){
            ArrayList<CatModel> catModelList = loadCatList();
            catModelList.add(catModel);
            saveCatList(catModelList);
        }

    // SAVE LIST (JOSE)
        public static void saveCatList(ArrayList<CatModel> arrayListCat){
            SharedPreferences.Editor prefsEditor = mySharedPref.edit();

            Gson gson = new Gson();
            String json = gson.toJson(arrayListCat);
            prefsEditor.putString("cats", json);

            prefsEditor.apply();
        }

    // LOAD LIST (JOSE)
        public static ArrayList<CatModel> loadCatList(){
            Gson gson = new Gson();
            String json = mySharedPref.getString("cats", "");
            Type type = new TypeToken<List<CatModel>>() {}.getType();
            ArrayList<CatModel> arrayListCat = gson.fromJson(json, type);
            if (arrayListCat == null)
                arrayListCat = new ArrayList<>();
            return arrayListCat;
        }

/*    public class ApplicationPreferences {
        static final String KEYNAME = "MYPREFS";

        private static SharedPreferences mSharedPref;

        private ApplicationPreferences() {}

        public static void init(Context context) {
            if(mSharedPref == null) {
                mSharedPref = context.getSharedPreferences(KEYNAME, Activity.MODE_PRIVATE);
            }
        }

        public static void savePajaro(PajarracoModel pajarracoModel){
            ArrayList<PajarracoModel> pajarracoList = loadPajaroList();
            pajarracoList.add(pajarracoModel);
            savePajaroList(pajarracoList);
        }

        public static void savePajaroList(ArrayList<PajarracoModel> arrayListPajarracos){
            SharedPreferences.Editor prefsEditor = mSharedPref.edit();

            Gson gson = new Gson();
            String json = gson.toJson(arrayListPajarracos);
            prefsEditor.putString("lista_pajarracos", json);

            prefsEditor.apply();
        }

        public static ArrayList<PajarracoModel> loadPajaroList(){
            Gson gson = new Gson();
            String json = mSharedPref.getString("lista_pajarracos", "");
            Type type = new TypeToken<List<PajarracoModel>>() {}.getType();
            ArrayList<PajarracoModel> pajarracolist = gson.fromJson(json, type);

            return pajarracolist;
        }
    }*/

}