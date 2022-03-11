package br.com.ettec.produtividade.business;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import br.com.ettec.produtividade.R;
import br.com.ettec.produtividade.domain.Coordenadas;
import br.com.ettec.produtividade.domain.OperacaoPolicial;
import br.com.ettec.produtividade.domain.ProdutividadePolicial;
import br.com.ettec.produtividade.domain.Turno;
import br.com.ettec.produtividade.persistence.ProdutividadePolicialDAO;
import br.com.ettec.produtividade.util.DataFormatada;
import br.com.ettec.produtividade.util.DetectaStatusConexao;
import br.com.ettec.produtividade.util.Mask;
import br.com.ettec.produtividade.util.SpinAdapterOperacaoPolicial;
import br.com.ettec.produtividade.util.SpinAdapterTurno;


public class Cadastrar extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private SQLiteDatabase db = null;

    private SpinAdapterTurno adapterTurno;

    private Spinner listaTurnosSpinner;

    private SpinAdapterOperacaoPolicial adapterOperacao;

    private Spinner listaOperacoesSpinner;

    ArrayList<OperacaoPolicial> arrayListOperacaoPolicial;

    ArrayList<Turno> arrayListTurno;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private GoogleApiClient mGoogleApiClient;

    private String latitude;

    private String longitude;

    Coordenadas coordenadas = null;

    DataFormatada dataFormatada;

    EditText editTextDescricao;

    private EnviarProdutividadeAsyncTask enviarProdutividadeAsyncTask = null;

    ProdutividadePolicial produtividadePolicial;

    ProdutividadePolicialDAO produtividadePolicialDAO;

    private static final String PREF_NANME = "LoginPreferences";

    private int idOpm;

    private int idPolicial;

    Mask mask;

    private EditText dataEditText;

    private EditText horaInicioEditText;

    private EditText horaFimEditText;

    DetectaStatusConexao dsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        db = openOrCreateDatabase("produtividade.db", Context.MODE_PRIVATE, null);

        callConnection();

        produtividadePolicial = new ProdutividadePolicial();

        produtividadePolicialDAO = new ProdutividadePolicialDAO(this);

        dataFormatada = new DataFormatada();


        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        SharedPreferences sp1 = getSharedPreferences(PREF_NANME, MODE_PRIVATE );

        idOpm = sp1.getInt("idOpm", 0);

        idPolicial = sp1.getInt("idPol",0);

        mask = new Mask();

        dataEditText = (EditText) findViewById(R.id.editTextData);

        horaInicioEditText = (EditText) findViewById(R.id.editTextHoraInicio);

        horaFimEditText = (EditText) findViewById(R.id.editTextHoraFim);

        dataEditText.addTextChangedListener(mask.insert("##/##/####", dataEditText));

        horaInicioEditText.addTextChangedListener(mask.insert("##:##", horaInicioEditText));

        horaFimEditText.addTextChangedListener(mask.insert("##:##", horaFimEditText));

        listaOperacoesSpinner = (Spinner)
                findViewById(R.id.listaOperacoes);

        listaTurnosSpinner = (Spinner) findViewById(R.id.listaTurnos);

        dsc = new DetectaStatusConexao(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onResume() {
        super.onResume();

        editTextDescricao = (EditText) findViewById(R.id.editTextDescricao);

        this.operacoesSpinner();

        this.turnosSpinner();

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {

            callConnection();

        }



    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Cadastrar Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());


    }



    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();


    }

    private synchronized void callConnection() {

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    //LISTENER
    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOG", "onConnected(" + bundle + ")");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (l != null) {

            latitude = String.valueOf(l.getLatitude());
            longitude = String.valueOf(l.getLongitude());

            coordenadas = new Coordenadas();

            coordenadas.setLatitude(latitude);
            coordenadas.setLongitude(longitude);

            Log.d("COORDENADAS_LATITUDE: ", coordenadas.getLatitude());
            Log.d("COORDENADAS_LONGITUDE: ", coordenadas.getLongitude());


        }


    }
    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "onConnectionSuspended(" + i + ")");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("LOG", "onConnectionFailed(" + connectionResult + ")");

    }

    private void operacoesSpinner(){

        Cursor cursorOperacao = db.rawQuery("SELECT codigo, descricao FROM operacao", null);

        arrayListOperacaoPolicial = new ArrayList<>();

        if (cursorOperacao.moveToFirst()) {
            for (int i = 0; i < cursorOperacao.getCount(); i++) {

                OperacaoPolicial operacaoPolicial2 = new OperacaoPolicial();
                operacaoPolicial2.setCodigo(cursorOperacao.getInt(0));
                operacaoPolicial2.setDescricao(cursorOperacao.getString(1));
                arrayListOperacaoPolicial.add(operacaoPolicial2);

                if(!cursorOperacao.isLast()) {
                    cursorOperacao.moveToNext();
                }
            }

        }

        cursorOperacao.close();

        adapterOperacao = new SpinAdapterOperacaoPolicial(this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayListOperacaoPolicial);

        adapterOperacao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaOperacoesSpinner.setAdapter(adapterOperacao);

        listaOperacoesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                produtividadePolicial.setOperacaoPolicial(arrayListOperacaoPolicial.get(position).getCodigo());

                Log.d("Operacao: ", String.valueOf(produtividadePolicial.getOperacaoPolicial()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void turnosSpinner(){

        Cursor cursorTurno = db.rawQuery("SELECT codigo, descricao FROM turno", null);

        arrayListTurno = new ArrayList<>();

        if(cursorTurno.moveToFirst()){

            for(int i=0;i<cursorTurno.getCount();i++){

                Turno turno2 = new Turno();

                turno2.setCodigo(cursorTurno.getInt(0));
                turno2.setDescricao(cursorTurno.getString(1));
                arrayListTurno.add(turno2);

                if(!cursorTurno.isLast()) {
                    cursorTurno.moveToNext();
                }

            }
        }

        cursorTurno.close();

        adapterTurno = new SpinAdapterTurno(this,
                android.R.layout.simple_spinner_dropdown_item,
                arrayListTurno);

        adapterTurno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listaTurnosSpinner.setAdapter(adapterTurno);

        listaTurnosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                produtividadePolicial.setTurno(arrayListTurno.get(position).getCodigo());

                Log.d("Turno: ", String.valueOf(produtividadePolicial.getTurno()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


        @SuppressLint("ShowToast")
        public void salvarClick(View v) throws ParseException {

            AlertDialog.Builder msg = new AlertDialog.Builder(Cadastrar.this);

            msg.setMessage("Salvar produtividade?");
            msg.setNegativeButton("Não", null);
            msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    produtividadePolicial.setDescricao(editTextDescricao.getText().toString());

                    Log.d("Descrição: ", String.valueOf(produtividadePolicial.getDescricao()));

                    produtividadePolicial.setLatitude(coordenadas.getLatitude());

                    produtividadePolicial.setLongitude(coordenadas.getLongitude());

                    produtividadePolicial.setDataProdutividade(dataEditText.getText().

                            toString()

                    );

                    Log.d("Produtividade_Data: ", String.valueOf(produtividadePolicial.getDataProdutividade()));

                    StringBuilder sbHoraInicio = new StringBuilder(horaInicioEditText.getText().toString());

                    sbHoraInicio.insert(horaInicioEditText.length(), ":00");

                    produtividadePolicial.setHoraInicio(sbHoraInicio.toString());

                    StringBuilder sbHoraFim = new StringBuilder(horaFimEditText.getText().toString());

                    sbHoraFim.insert(horaFimEditText.length(), ":00");

                    produtividadePolicial.setHoraFim(sbHoraFim.toString());


                    try

                    {

                        produtividadePolicialDAO.inserir(db, produtividadePolicial);

                        Toast.makeText(getBaseContext(), "Registro salvo no aparelho!", Toast.LENGTH_LONG).show();

                        Intent it = new Intent(getBaseContext(), Listar.class);

                        startActivity(it);

                        finish();

                    } catch (
                            Exception e
                            )

                    {

                        Toast.makeText(getBaseContext(), "Ocorreu um erro ao tentar salvar!", Toast.LENGTH_LONG).show();

                        e.printStackTrace();
                    }

                }

            });

            msg.show();
        }

        @SuppressLint("ShowToast")
        public void enviarClick(View v) throws ParseException {

            AlertDialog.Builder msg = new AlertDialog.Builder(Cadastrar.this);

            msg.setMessage("Enviar produtividade?");
            msg.setNegativeButton("Não", null);
            msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

            produtividadePolicial.setDescricao(editTextDescricao.getText().toString());

            Log.d("Descrição: ", String.valueOf(produtividadePolicial.getDescricao()));

            produtividadePolicial.setLatitude(coordenadas.getLatitude());

            produtividadePolicial.setLongitude(coordenadas.getLongitude());

            produtividadePolicial.setDataProdutividade(dataEditText.getText().toString());

            Log.d("Produtividade_Data: ", String.valueOf(produtividadePolicial.getDataProdutividade()));

            StringBuilder sbHoraInicio = new StringBuilder(horaInicioEditText.getText().toString());

            sbHoraInicio.insert(horaInicioEditText.length(), ":00");

            produtividadePolicial.setHoraInicio(sbHoraInicio.toString());

            StringBuilder sbHoraFim = new StringBuilder(horaFimEditText.getText().toString());

            sbHoraFim.insert(horaFimEditText.length(), ":00");

            produtividadePolicial.setHoraFim(sbHoraFim.toString());

            if (dsc.existeConexao()) {

                enviarProdutividadeAsyncTask = new EnviarProdutividadeAsyncTask();

                enviarProdutividadeAsyncTask.execute((Void) null);

            }else{

                try {


                    produtividadePolicialDAO.inserir(db,produtividadePolicial);

                    Toast.makeText(getBaseContext(), "Sem conexão com a Internet! Registro salvo no aparelho.", Toast.LENGTH_LONG).show();

                    Intent it = new Intent(getBaseContext(), Listar.class);

                    startActivity(it);

                    finish();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Ocorreu um erro ao tentar salvar!", Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }

            }

                }



            });

            msg.show();

        }

    public class EnviarProdutividadeAsyncTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean retorno = false;

            StringBuilder strURL = new StringBuilder();

            try {
                strURL.append("http://ettec.com.br/enviarprodutividade.php?descricao=");
                strURL.append(URLEncoder.encode(produtividadePolicial.getDescricao(), "UTF-8"));
                strURL.append("&dataProdutividade=");
                strURL.append(URLEncoder.encode(produtividadePolicial.getDataProdutividade(), "UTF-8"));
                strURL.append("&horaInicio=");
                strURL.append(URLEncoder.encode(produtividadePolicial.getHoraInicio(), "UTF-8"));
                strURL.append("&horaFim=");
                strURL.append(URLEncoder.encode(produtividadePolicial.getHoraFim(), "UTF-8"));
                strURL.append("&operacaoPolicial=");
                strURL.append(URLEncoder.encode(Integer.toString(produtividadePolicial.getOperacaoPolicial()), "UTF-8"));
                strURL.append("&turno=");
                strURL.append(URLEncoder.encode(Integer.toString(produtividadePolicial.getTurno()), "UTF-8"));
                strURL.append("&latitude=");
                strURL.append(URLEncoder.encode(produtividadePolicial.getLatitude(), "UTF-8"));
                strURL.append("&longitude=");
                strURL.append(URLEncoder.encode(produtividadePolicial.getLongitude(), "UTF-8"));
                strURL.append("&opm=");
                strURL.append(URLEncoder.encode(Integer.toString(idOpm), "UTF-8"));
                strURL.append("&policial=");
                strURL.append(URLEncoder.encode(Integer.toString(idPolicial), "UTF-8"));

                URL url = new URL(strURL.toString());
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStreamReader ips = new InputStreamReader(http.getInputStream());
                BufferedReader line = new BufferedReader(ips);

                String linhaRetorno = line.readLine();

                if (linhaRetorno.equals("Y")) {

                    retorno = true;

                }

            } catch (IOException e) {
                e.printStackTrace();

            }

            return retorno;
        }

        @Override
        protected void onPostExecute(final Boolean success) {


            if (success) {

                Toast.makeText(getBaseContext(), "Produtividade enviada com sucesso!", Toast.LENGTH_LONG).show();

                Intent it = new Intent(getBaseContext(), Inicio.class);

                startActivity(it);

                finish();
            }else{

                Toast.makeText(getBaseContext(), "Erro no envio da Produtividade!", Toast.LENGTH_LONG).show();

            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.home:
                this.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {

        Intent it = new Intent(getBaseContext(), Inicio.class);

        startActivity(it);

        finish();


    }

}
