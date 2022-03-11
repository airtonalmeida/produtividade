package br.com.ettec.produtividade.util;

/**
 * Created by usuario on 27/03/2017.
 */

import android.app.Application;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by usuario on 14/10/2016.
 */

public class Global extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }

}
