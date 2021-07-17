package com.informatika.databarang.service

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.informatika.databarang.MainActivity
import com.informatika.databarang.network.koneksi
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionPreferences: SessionPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        windowManager.(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            windowManager.Layout.Params.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_login)
        btn_submit.setOnClickListener {
            val userName = et_username.text.toString()
            val password = et_password.text.toString()

            if (userName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Form tidak boleh kososng!", Toast.LENGTH_LONG.show()
            }else{
                actionData(userName, password)
            }
        }
        btn_clean.setOnClickListener {
            fromClear()
        }
        tv_disini.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    fun actionData(username: String, password : String){
        koneksi.service.register(username, password).enqueue(object : Callback<ResponseAdmin>) {
            override fun onFailure(call: Call<ResponseAdmin>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseAdmin>, response: Response<ResponseAdmin>){
                if (response.isSuccessful){
                    val resbody = response.body()
                    val resStatus = resbody.status
                    val resUserName = resbody?.data?.get(0)?.username
                    log.d("pesan", resUserName.toString())
                    if (resStatus == true){
                        sessionPreferences = SessionPreferences(this@RegisterActivity)
                        sessionPreferences.actionLogIn(resUserName.toString())
                        val i = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(i)
                        finish()
                    }else if(resStatus == false){
                        Toast.makeText(this@RegisterActivity,
                            "Username atau Password Anda Salah!", Toast.LENGTH_LONG.show()
                    }
                }
            }
        })
    }
    fun formClear(){
        et_username.text.clear()
        et_password.text.clear()
    }