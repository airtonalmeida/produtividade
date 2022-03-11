package br.com.ettec.produtividade.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.ettec.produtividade.domain.ProdutividadePolicial;

/**
 * Created by usuario on 18/01/2017.
 */

public class ProdutividadePolicialDAO extends SQLiteOpenHelper {

    private static final String TABELA = "produtividade";
    String [] COLUNAS = {"descricao", "dataProdutividade"};
    private static final int VERSION = 3;

    public ProdutividadePolicialDAO(Context context) {
        super(context, TABELA, null, VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA +
                "( _id INTEGER PRIMARY KEY," +
                " codigo INTEGER," +
                " descricao TEXT," +
                " dataProdutividade TEXT," +
                " horaInicio TEXT," +
                " horaFim TEXT," +
                " operacaoPolicial INTEGER," +
                " turno INTEGER," +
                " latitude TEXT," +
                " longitude TEXT" +
                ");";

        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ProdutividadePolicialDAO.TABELA);
        this.onCreate(db);

    }

    public void inserir(SQLiteDatabase db, ProdutividadePolicial produtividadePolicial){

        ContentValues valores = new ContentValues();
        valores.put("codigo", produtividadePolicial.getCodigo());
        valores.put("descricao", produtividadePolicial.getDescricao());
        valores.put("dataProdutividade", produtividadePolicial.getDataProdutividade());
        valores.put("horaInicio", String.valueOf(produtividadePolicial.getHoraInicio()));
        valores.put("horaFim", String.valueOf(produtividadePolicial.getHoraFim()));
        valores.put("operacaoPolicial", String.valueOf(produtividadePolicial.getOperacaoPolicial()));
        valores.put("turno", String.valueOf(produtividadePolicial.getTurno()));
        valores.put("latitude", produtividadePolicial.getLatitude());
        valores.put("longitude", produtividadePolicial.getLongitude());
        db.insert(TABELA, "_id", valores);
    }

}
