package com.example.kelly.logeasyfinal.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Kelly on 31/03/2018.
 */

public class Propriedades {

    private final static String urlServico = "http://10.0.0.4:8080/logeasy-webservice/";
    private final static String nomeBancoSQLite = "LogEasyBeta.db";

    public static String getUrlServico() {
        return urlServico;
    }

    public static String getNomeBancoSQLite() {
        return nomeBancoSQLite;
    }
}
