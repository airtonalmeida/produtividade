package br.com.ettec.produtividade.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.ettec.produtividade.domain.Turno;

/**
 * Created by usuario on 18/01/2017.
 */

public class TurnoDAO extends SQLiteOpenHelper {

    private static final String TABELA = "turno";
    private static final int VERSION = 3;

    public TurnoDAO(Context context) {
        super(context, TABELA, null, VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA +
                "( _id INTEGER PRIMARY KEY," +
                " codigo INTEGER," +
                " descricao TEXT" +
                ");";

        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TurnoDAO.TABELA);
        this.onCreate(db);

    }

    public void inserir(SQLiteDatabase db, Turno turno){

        ContentValues valores = new ContentValues();
        valores.put("codigo", turno.getCodigo());
        valores.put("descricao", turno.getDescricao());

        db.insert(TABELA, "_id", valores);
    }

}
