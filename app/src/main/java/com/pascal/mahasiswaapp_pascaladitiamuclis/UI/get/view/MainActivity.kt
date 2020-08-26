package com.pascal.mahasiswaapp_pascaladitiamuclis.UI.get.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.pascal.mahasiswaapp_pascaladitiamuclis.R
import com.pascal.mahasiswaapp_pascaladitiamuclis.UI.get.presenter.GetPresenter
import com.pascal.mahasiswaapp_pascaladitiamuclis.UI.get.presenter.GetView
import com.pascal.mahasiswaapp_pascaladitiamuclis.UI.input.view.InputActivity
import com.pascal.mahasiswaapp_pascaladitiamuclis.adapter.AdapterMain
import com.pascal.mahasiswaapp_pascaladitiamuclis.config.NetworkModule
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.action.ResponseAction
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.DataItem
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.ResponseGetData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), GetView {

    private var presenter : GetPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = GetPresenter(this)
        presenter?.getData()

        btn_floating.setOnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getSuccess(msg: String, data: List<DataItem>?) {
        val adapter = AdapterMain(data, object : AdapterMain.OnClickListener {
            override fun detail(item: DataItem?) {
                val intent = Intent(applicationContext, InputActivity::class.java)
                intent.putExtra("data", item)
                startActivity(intent)
            }

            override fun delete(item: DataItem?) {
                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("Delete Data")
                    setMessage("apakah anda yakin ingin menghapus?")
                    setPositiveButton("Delete") {dialogInterface, i ->
                        presenter?.hapusData(item?.idMahasiswa)
                        dialogInterface.dismiss()
                    }
                    setNegativeButton("Cancel") {dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }

        })

        recyclerview_main.adapter = adapter
    }

    override fun getError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun getDelete(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        presenter?.getData()
    }
}