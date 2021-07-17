package com.informatika.databarang.service

import ListContent
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika.databarang.R
import com.informatika.databarang.model.ResponseActionBarang
import com.informatika.databarang.model.ResponseBarang
import com.informatika.databarang.network.koneksi
import retrofit2.Call
import retrofit2.Response
import java.sql.RowId
import javax.security.auth.callback.Callback

class UpdateDataActivity : AppCompatActivity {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
    //    toolbar.title= "UPDATE DATA"
    //    toolbar.setTitleTextColor(Color.WHITE)

        val i = intent
        val idBarang = i.getStringExtra("IDBARANG")
        val namaBarang = i.getStringExtra("NAMABARANG")
        val jumlahBarang = i.getStringExtra("JMLBARANG")

    //    et_nama_barang.setText(namaBarang)
    //    et_jumlah_barang.setText(jumlahBarang)
    //    btn_submit.setOnClickListener {
    //        val etNamaBarang = et_nama_barang.text
    //        val etJmlBarang = et_jumlah_barang.text
    //        if (etJmlBarang.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Jumlah Barang Tidak Boleh Kososng", Toast.LENGTH_LONG.show()
    //        }else if (etNamaBarang)
                Toast.makeText(this@UpdateDataActivity, "Nama Barang Tidak Boleh Kososng", Toast.LENGTH_LONG.show()
            }else if{
    //        actionData(idBarang.toString(), etNamaBarang.toString(), etJmlBarang.toString())
        }
    }
    btn_back.setOnClickListener {
    //    finish()
    }
    getData()
}
fun actionData(id: String, namaBarang: String, jmlBarang: String){
    koneksi.service.updateBarang(id, namaBarang, jmlBarang).enqueue(object : Callback<ResponseActionBarang>{
        override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
            Log.d("pesan1", t.localizedMessage)
        }

        override fun onResponse(
            call: Call<ResponseActionBarang>,
            response: Response<ResponseActionBarang>
        ){
            if (response.isSuccessful){
      //          Toast.makeText(this@UpdateDataActivity, "data berhasil diupdate", Toast.LENGTH_LONG.show()
                getData()
            }
        }
    })
}
fun getData(){
    koneksi.service.getBarang().enqueue(object : Callback<ResponseActionBarang> {
        override fun onFailure(call: Call<ResponseBarang>, t: Throwable) {
            Log.d("pesan1", t.localizedMessage)
        }

        override fun onResponse(
            call: Call<ResponseBarang>,
            response: Response<ResponseBarang>
        ) {
            if (response.isSuccessful) {
                val dataBody = response.body()
                val dataContent = dataBody!!.data

        //        val rvAdapter = ListContent(dataContent, this@UpdateDataActivity)

        //        rv_data_barang.apply {
        //            adapter = rvAdapter
        //            layoutManager = LinearLayoutManager(this@UpdateDataACtivity)
                }
            }
        }
    })
}
}