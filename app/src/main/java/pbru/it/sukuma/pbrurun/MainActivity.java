package pbru.it.sukuma.pbrurun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //explicit
    private MyManage myManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //request SQLite
        myManage = new MyManage(this);

        myManage.addNewUser("1", "name", "user", "pass", "0", "1");
       // myManage.addNewUser("2", "s", "u")


    }   //Main Method

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

}   //Main Class
