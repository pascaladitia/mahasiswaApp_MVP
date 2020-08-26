package com.pascal.mahasiswaapp_pascaladitiamuclis.UI.input.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pascal.mahasiswaapp_pascaladitiamuclis.R
import com.pascal.mahasiswaapp_pascaladitiamuclis.UI.input.presenter.InputPresenter
import com.pascal.mahasiswaapp_pascaladitiamuclis.UI.input.presenter.InputView
import com.pascal.mahasiswaapp_pascaladitiamuclis.config.NetworkModule
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.action.ResponseAction
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.DataItem
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity(), InputView {

    private var presenter : InputPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        presenter = InputPresenter(this)

        val getData = intent.getParcelableExtra<DataItem>("data")
        if (getData != null) {
            edt_nama.setText(getData.mahasiswaNama)
            edt_nohp.setText(getData.mahasiswaNohp)
            edt_alamat.setText(getData.mahasiswaAlamat)

            btn_simpan.text = "Update"
        }

        when (btn_simpan.text) {
            "Update" -> {
                btn_simpan.setOnClickListener {
                    getData?.idMahasiswa?.let { it1 ->
                        presenter?.updateData(
                            it1,
                            edt_nama.text.toString(),
                            edt_nohp.text.toString(),
                            edt_alamat.text.toString()
                        )
                    }
                }
            }
            else -> {
                btn_simpan.setOnClickListener {
                    presenter?.inputData(
                        edt_nama.text.toString(),
                        edt_nohp.text.toString(),
                        edt_alamat.text.toString()
                    )
                }
            }
        }

        btn_cancel.setOnClickListener {
            finish()
        }
    }

    override fun inputSuccess(response: ResponseAction) {
        showToast("Data berhasil disimpan")
        finish()
    }

    override fun errorInput(msg: String) {
        showToast(msg)
    }

    override fun updateSuccess(response: ResponseAction) {
        showToast("Data berhasil diupdate")
        finish()
    }

    override fun empty() {
        showToast("Tidak boleh ada yang kosong")
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}