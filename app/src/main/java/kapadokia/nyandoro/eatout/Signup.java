package kapadokia.nyandoro.eatout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kapadokia.nyandoro.eatout.model.User;

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

        // firebase init
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(Signup.this);
                dialog.setMessage("Please wait ....");
                dialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //check if the user alread exists
                        if (dataSnapshot.child(phone.getText().toString()).exists()){
                            dialog.dismiss();
                            Toast.makeText(Signup.this, "The number is alredy registered", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            dialog.dismiss();
                            User user = new  User(username.getText().toString(),password.getText().toString());
                            table_user.child(phone.getText().toString()).setValue(user);
                            Toast.makeText(Signup.this, "Reqgistered Successfully", Toast.LENGTH_SHORT).show();

                            finish();
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
