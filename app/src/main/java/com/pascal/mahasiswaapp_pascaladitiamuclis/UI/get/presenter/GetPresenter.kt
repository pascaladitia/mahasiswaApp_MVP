package com.pascal.mahasiswaapp_pascaladitiamuclis.UI.get.presenter

import com.pascal.mahasiswaapp_pascaladitiamuclis.config.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class GetPresenter(val getview: GetView) {


    fun getData() {
        NetworkModule.service().getData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
                if (response.isSuccess == true) {
                    getview.getSuccess(response.message ?: "Data Kosong", response.data)
                }
            }, {
                getview.getError(it.localizedMessage)
            })
    }

    fun hapusData(id: String?) {
        NetworkModule.service().deleteData(id ?: "").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                getview.getDelete("Data berhasil dihapus")
                getData()
            }, {
                getview.getError(it.localizedMessage)
            })
    }
}