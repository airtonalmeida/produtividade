package br.com.ettec.produtividade.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by usuario on 27/03/2017.
 */

public class DataFormatada {

    public String obterFormatarData(){

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String dataFormatada = dateFormat.format(data_atual);

        return dataFormatada;

    }

    public String obterFormatarData2(){

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String dataFormatada = dateFormat.format(data_atual);

        return dataFormatada;

    }

    public String obterFormatarData3(){

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String dataFormatada = dateFormat.format(data_atual);

        return dataFormatada;

    }

    public Date obterFormatarData4(){

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        dateFormat.format(data_atual);

        return data_atual;

    }

    public String obterFormatarData5(){

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String dataFormatada = dateFormat.format(data_atual);

        return dataFormatada;

    }

    public String obterFormatarData6(Date data){

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dataFormatada = dateFormat.format(data);

        return dataFormatada;

    }
}
