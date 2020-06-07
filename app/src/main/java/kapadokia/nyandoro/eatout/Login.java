package kapadokia.nyandoro.eatout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kapadokia.nyandoro.eatout.model.User;

public class Login extends AppCompatActivity {

    private EditText phone, password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.phone_number_ed);
        password = findViewById(R.id.password_ed);
        login = findViewById(R.id.login_page_btn);

        // firebase init
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(Login.this);
                dialog.setMessage("Please wait ....");
                dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check if the user exists in the database

                        if (dataSnapshot.child(phone.getText().toString()).exists()){

                        dialog.dismiss();
                        // get user information
                        User user = dataSnapshot.child(phone.getText().toString()).getValue(User.class);

                        if (user.getPassword().equals(password.getText().toString())){
                            Toast.makeText(Login.this, "login successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Login.this, "login failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                            Toast.makeText(Login.this, "User does not exist", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
