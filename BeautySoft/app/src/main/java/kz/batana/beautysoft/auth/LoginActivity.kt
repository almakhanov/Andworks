package kz.batana.beautysoft.auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kz.batana.beautysoft.R
import kz.batana.beautysoft.RootActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login_sign_in.setOnClickListener{
            if(edit_text_sign_in_username.text.toString() == "admin" && edit_text_sign_in_password.text.toString() == "q"){
                startActivity(Intent(this, RootActivity::class.java))
                finish()
            }

            else
                Toast.makeText(this, "Неверное имя пользователя или пароль!", Toast.LENGTH_SHORT).show()
        }
    }
}
