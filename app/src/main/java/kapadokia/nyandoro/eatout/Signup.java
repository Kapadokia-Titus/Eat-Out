package kapadokia.nyandoro.eatout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    private EditText username,phone,password;
    private Button regBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        username = findViewById(R.id.username_reg);
        phone = findViewById(R.id.phone_number_reg);
        password = findViewById(R.id.password_reg);


        regBtn = findViewById(R.id.register_page_btn);
    }
}
