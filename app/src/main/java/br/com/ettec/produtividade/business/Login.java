package br.com.ettec.produtividade.business;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import br.com.ettec.produtividade.R;
import br.com.ettec.produtividade.domain.OperacaoPolicial;
import br.com.ettec.produtividade.domain.Turno;
import br.com.ettec.produtividade.domain.Usuario;
import br.com.ettec.produtividade.persistence.OperacaoPolicialDAO;
import br.com.ettec.produtividade.persistence.ProdutividadePolicialDAO;
import br.com.ettec.produtividade.persistence.TurnoDAO;
import br.com.ettec.produtividade.util.DetectaStatusConexao;
import br.com.ettec.produtividade.util.Mask;
import br.com.ettec.produtividade.util.SpinAdapterOperacaoPolicial;
import br.com.ettec.produtividade.util.SpinAdapterTurno;


public class Login extends AppCompatActivity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mLoginView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ImageView pmbaImagem;

    private int idPolicial;
    private boolean logado;

    private int matricula;
    private String papel;
    private int idOpm;
    private String descricaoOpm;
    private String nomePolicial;
    private String grauHierarquico;


    Usuario usuar;
    private boolean usuarioAtivo = true;

    DetectaStatusConexao dsc;

    Mask mask;

    private SQLiteDatabase db = null;

    private static final String PREF_NANME = "LoginPreferences";

    private SharedPreferences.OnSharedPreferenceChangeListener callback = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.i("Cod", key + "update");
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    ArrayList<OperacaoPolicial> arrayListOperacaoPolicial;

    ArrayList<Turno> arrayListTurno;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = openOrCreateDatabase("produtividade.db", Context.MODE_PRIVATE, null);

        ProdutividadePolicialDAO produtividadePolicialDAO = new ProdutividadePolicialDAO(this);

        produtividadePolicialDAO.onCreate(db);

        produtividadePolicialDAO.close();

        OperacaoPolicialDAO operacaoPolicialDAO = new OperacaoPolicialDAO(this);

        operacaoPolicialDAO.onCreate(db);

        operacaoPolicialDAO.close();

        TurnoDAO turnoDAO = new TurnoDAO(this);

        turnoDAO.onCreate(db);

        turnoDAO.close();

        mask = new Mask();

        // Set up the login form.
        mLoginView = (EditText) findViewById(R.id.login);
        mLoginView.addTextChangedListener(mask.insert("##.###.###-#", mLoginView));

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mLoginSignInButton = (Button) findViewById(R.id.login_sign_in_button);
        mLoginSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        pmbaImagem = (ImageView) findViewById(R.id.imageView);

        pmbaImagem.setImageResource(R.mipmap.pmba);

        dsc = new DetectaStatusConexao(this);


        SharedPreferences sp1 = getSharedPreferences(PREF_NANME, MODE_PRIVATE);
        idPolicial = sp1.getInt("idPol", 0);
        logado = sp1.getBoolean("logado", false);
        matricula = sp1.getInt("matricula", 0);
        papel = sp1.getString("papel", "papel");
        idOpm = sp1.getInt("idOpm", 0);
        descricaoOpm = sp1.getString("descricaoOpm", "descricaoOpm");
        nomePolicial = sp1.getString("nomePolicial", "nomePolicial");
        grauHierarquico = sp1.getString("grauHierarquico", "grauHierarquico");

        sp1.registerOnSharedPreferenceChangeListener(callback);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }

    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();

        SharedPreferences sp1 = getSharedPreferences(PREF_NANME, MODE_PRIVATE);

        logado = sp1.getBoolean("logado", false);

        if (logado) {

            Intent it = new Intent(getBaseContext(), Inicio.class);

            startActivity(it);

            finish();

        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sp1 = getSharedPreferences(PREF_NANME, MODE_PRIVATE);
        sp1.unregisterOnSharedPreferenceChangeListener(callback);

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {

        if (dsc.existeConexao()) {

            if (mAuthTask != null) {
                return;
            }

            // Reset errors.
            mLoginView.setError(null);
            mPasswordView.setError(null);

            // Store values at the time of the login attempt.
              String usuario = mask.unmask(mLoginView.getText().toString());
              String password = mPasswordView.getText().toString();


            boolean cancel = false;
            View focusView = null;


            // Check for a valid usuario.
            if (TextUtils.isEmpty(usuario) && TextUtils.isEmpty(password)) {
                mLoginView.setError(getString(R.string.error_field_required));
                focusView = mLoginView;
                cancel = true;
            }

            // Check for a valid usuario.
            if (TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(password)) {
                mLoginView.setError(getString(R.string.error_field_required));
                focusView = mLoginView;
                cancel = true;
            }


            if (TextUtils.isEmpty(password) && !TextUtils.isEmpty(usuario)) {
                mPasswordView.setError(getString(R.string.error_field_required));
                focusView = mPasswordView;
                cancel = true;
            }

            // Check for a valid password, if the user entered one.
            if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                mPasswordView.setError(getString(R.string.error_invalid_password));
                focusView = mPasswordView;
                cancel = true;
            }


            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {
                // Show a progress spinner, and kick off a background task to
                // perform the user login attempt.
                showProgress(true);
                mAuthTask = new UserLoginTask(usuario, password);
                mAuthTask.execute((Void) null);
            }
        } else {

            Toast.makeText(getBaseContext(), "Sem conexão com a Internet!", Toast.LENGTH_LONG).show();

        }

    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mLogin;
        private final String mPassword;


        UserLoginTask(String usuario, String password) {
            mLogin = usuario;
            mPassword = password;

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            ArrayList<Usuario> retorno = new ArrayList<Usuario>();

            try {
                StringBuilder strURL = new StringBuilder();
                strURL.append("http://ettec.com.br/loginprodutividade.php?login=");
                strURL.append(URLEncoder.encode(mLogin, "UTF-8"));
                strURL.append("&senha=");
                strURL.append(URLEncoder.encode(mPassword, "UTF-8"));

                URL url = new URL(strURL.toString());
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStreamReader ips = new InputStreamReader(http.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(ips);
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {

                    sb.append(line + "\n");
                    retorno = tratarJson(sb.toString());

                    usuar = new Usuario();

                    for (int i = 0; i < retorno.size(); i++) {

                        usuar.setIdPolicial(retorno.get(i).getIdPolicial());
                        usuar.setMatricula(retorno.get(i).getMatricula());
                        usuar.setAtivo(retorno.get(i).getAtivo());
                        usuar.setPapel(retorno.get(i).getPapel());
                        usuar.setIdOpm(retorno.get(i).getIdOpm());
                        usuar.setDescricaoOpm(retorno.get(i).getDescricaoOpm());
                        usuar.setNomePolicial(retorno.get(i).getNomePolicial());
                        usuar.setGrauHierarquico(retorno.get(i).getGrauHierarquico());

                    }


                    if ((mLogin.equals(String.valueOf(usuar.getMatricula()))) && (usuar.getAtivo() == 1)) {

                        usuarioAtivo = true;

                        idPolicial = usuar.getIdPolicial();
                        matricula = usuar.getMatricula();
                        papel = usuar.getPapel();
                        idOpm = usuar.getIdOpm();
                        descricaoOpm = usuar.getDescricaoOpm();
                        nomePolicial = usuar.getNomePolicial();
                        grauHierarquico = usuar.getGrauHierarquico();

                        SharedPreferences sp1 = getSharedPreferences(PREF_NANME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp1.edit();
                        editor.putInt("idPol", idPolicial);
                        editor.putBoolean("logado", true);

                        editor.putInt("matricula", matricula);
                        editor.putString("papel", papel);
                        editor.putInt("idOpm", idOpm);
                        editor.putString("descricaoOpm", descricaoOpm);
                        editor.putString("nomePolicial", nomePolicial);
                        editor.putString("grauHierarquico", grauHierarquico);
                        editor.commit();

                        operacoesList();

                        turnosList();

                        // TODO: register the new account here.
                        return true;

                    } else if ((mLogin.equals(usuar.getMatricula())) && (usuar.getAtivo() == 0)) {
                        usuarioAtivo = false;

                        return false;

                    } else {

                       return false;
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {

                mLoginView.setText("");
                mPasswordView.setText("");

                Intent it = new Intent(getBaseContext(), Inicio.class);

                startActivity(it);

                finish();
            } else if (usuarioAtivo == false) {

                mPasswordView.setError("Usuário inativo!");
                mPasswordView.requestFocus();

                usuarioAtivo = true;

            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_dados));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }


    private ArrayList<Usuario> tratarJson(String data) {

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            JSONArray jArray = new JSONArray(data);

            for (int i = 0; i < jArray.length(); i++) {

                Usuario usuario = new Usuario();

                JSONObject jo = jArray.getJSONObject(i);
                usuario.setIdPolicial(jo.getInt("cod_policial"));
                usuario.setMatricula(jo.getInt("matricula"));
                usuario.setAtivo(jo.getInt("ativo"));
                usuario.setPapel(jo.getString("papel"));
                usuario.setIdOpm(jo.getInt("cod_opm"));
                usuario.setDescricaoOpm(jo.getString("descricao"));
                usuario.setNomePolicial(jo.getString("nome"));
                usuario.setGrauHierarquico(jo.getString("gh"));
                usuarios.add(usuario);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return usuarios;


    }


    public void onBackPressed() {

        finish();

    }

    private void operacoesList(){

        arrayListOperacaoPolicial = new ArrayList<>();

        ObterOperacoesAsyncTask obterOperacoesAsyncTask =  new ObterOperacoesAsyncTask();

        arrayListOperacaoPolicial = obterOperacoesAsyncTask.doInBackground();

        OperacaoPolicialDAO operacaoPolicialDAO = new OperacaoPolicialDAO(this);

        operacaoPolicialDAO.onUpgrade(db, 1, 3);

        for (int i = 0; i < arrayListOperacaoPolicial.size(); i++) {

            OperacaoPolicial operacaoPolicial = new OperacaoPolicial();

            operacaoPolicial.setCodigo(arrayListOperacaoPolicial.get(i).getCodigo());
            operacaoPolicial.setDescricao(arrayListOperacaoPolicial.get(i).getDescricao());

            operacaoPolicialDAO.inserir(db, operacaoPolicial);

        }

    }


    private void turnosList(){

        arrayListTurno = new ArrayList<>();

        ObterTurnosAsyncTask obterTurnosAsyncTask = new ObterTurnosAsyncTask();

        arrayListTurno = obterTurnosAsyncTask.doInBackground();

        TurnoDAO turnoDAO = new TurnoDAO(this);

        turnoDAO.onUpgrade(db, 1, 3);

        for (int i = 0; i < arrayListTurno.size(); i++) {

            Turno turno = new Turno();

            turno.setCodigo(arrayListTurno.get(i).getCodigo());
            turno.setDescricao(arrayListTurno.get(i).getDescricao());

            turnoDAO.inserir(db, turno);

        }


    }

    public class ObterOperacoesAsyncTask extends AsyncTask<Void, Void, ArrayList<OperacaoPolicial>> {

        @Override
        protected ArrayList<OperacaoPolicial> doInBackground(Void... params) {


            try {
                // Simulate network access.
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return null;
            }

            StringBuilder strURL = new StringBuilder();

            ArrayList<OperacaoPolicial> retorno = new ArrayList<>();


            try {

                strURL.append("http://ettec.com.br/obteroperacoes.php?");

                URL url = new URL(strURL.toString());
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStreamReader ips = new InputStreamReader(http.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(ips);
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {

                    sb.append(line + "\n");
                    retorno = tratarJsonOP(sb.toString());

                }

                bufferedReader.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return retorno;
        }
    }


    private ArrayList<OperacaoPolicial> tratarJsonOP(String data) {

        ArrayList<OperacaoPolicial> operas = new ArrayList<>();

        try {
            JSONArray jArray = new JSONArray(data);

            for (int i = 0; i < jArray.length(); i++) {

                OperacaoPolicial opera = new OperacaoPolicial();

                JSONObject jo = jArray.getJSONObject(i);
                opera.setCodigo(jo.getInt("COD_OPERACAO_POLICIAL"));
                opera.setDescricao(jo.getString("DESCRICAO"));


                operas.add(opera);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return operas;


    }

    public class ObterTurnosAsyncTask extends AsyncTask<Void, Void, ArrayList<Turno>> {

        @Override
        protected ArrayList<Turno> doInBackground(Void... params) {


            try {
                // Simulate network access.
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return null;
            }

            StringBuilder strURL = new StringBuilder();

            ArrayList<Turno> retorno = new ArrayList<>();


            try {

                strURL.append("http://ettec.com.br/obterturnos.php?");

                URL url = new URL(strURL.toString());
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                InputStreamReader ips = new InputStreamReader(http.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(ips);
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {

                    sb.append(line + "\n");
                    retorno = tratarJsonTurno(sb.toString());

                }

                bufferedReader.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return retorno;
        }
    }


    private ArrayList<Turno> tratarJsonTurno(String data) {

        ArrayList<Turno> turs = new ArrayList<>();

        try {
            JSONArray jArray = new JSONArray(data);

            for (int i = 0; i < jArray.length(); i++) {

                Turno tur = new Turno();

                JSONObject jo = jArray.getJSONObject(i);
                tur.setCodigo(jo.getInt("COD_TURNO_EVENTO"));
                tur.setDescricao(jo.getString("DESCRICAO"));


                turs.add(tur);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return turs;



    }


}

