package webservices.sd.tosin.com.br.clientwebservicesd.ui.activities;

import android.app.Activity;
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

            username = "teste";
            password = "123123";
            host = "192.168.0.121";

            singin(new User(username, password), host);
        }
    }

    private void singin(User user, String host) {
        UserClient client = ServiceGenerator.createService(UserClient.class, host);
        Call<CustomResponse> call = client.createOrUpdate(user);
        call.enqueue(new Callback<CustomResponse>() {
            @Override
            public void onResponse(Call<CustomResponse> call, Response<CustomResponse> response) {
                CustomResponse resp = response.body();
                if (response.isSuccessful()) {
                    CustomMessageDialog.message(activity, "OK", resp.msg);
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
