package webservices.sd.tosin.com.br.clientwebservicesd.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import webservices.sd.tosin.com.br.clientwebservicesd.R;
import webservices.sd.tosin.com.br.clientwebservicesd.models.CustomResponse;
import webservices.sd.tosin.com.br.clientwebservicesd.models.User;
import webservices.sd.tosin.com.br.clientwebservicesd.rest.ServiceGenerator;
import webservices.sd.tosin.com.br.clientwebservicesd.rest.UserClient;
import webservices.sd.tosin.com.br.clientwebservicesd.ui.dialogs.CustomMessageDialog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextHost;
    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button btnSend;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.activity = this;

        setupView();
    }

    private void setupView() {
        editTextHost = (EditText) findViewById(R.id.editText_host);
        editTextUsername = (EditText) findViewById(R.id.editTExt_username);
        editTextPassword = (EditText) findViewById(R.id.editTExt_username);

        btnSend = (Button) findViewById(R.id.btn_singIn);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSend) {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            String host = editTextHost.getText().toString();

            if (username.isEmpty())
                username = "teste";
            if (password.isEmpty())
                password = "123123";
            if (host.isEmpty())
                host = "192.168.100.146";

            singin(new User(username, password), host);
        }
    }

    /**
     * Faz a chamada do servidor para autenticar/criar usuario
     * @param user
     * @param host
     */
    private void singin(final User user, final String host) {
        UserClient client = ServiceGenerator.createService(UserClient.class, host);
        Call<CustomResponse> call = client.createOrUpdate(user);
        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                CustomResponse resp = response.body();
                if (response.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("host", host);
                    startActivity(intent);
                    finish();
                }
                else {

                    CustomMessageDialog.message(activity, "Erro", resp.msg);
                }
            }

            @Override
            public void onFailure(Call<CustomResponse> call, Throwable t) {
                CustomMessageDialog.message(activity, "Erro", "Problemas na conexao");
            }
        });
    }
}
