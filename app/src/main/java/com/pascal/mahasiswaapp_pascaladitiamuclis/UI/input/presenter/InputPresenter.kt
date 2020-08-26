package com.pascal.mahasiswaapp_pascaladitiamuclis.UI.input.presenter

import com.pascal.mahasiswaapp_pascaladitiamuclis.config.NetworkModule
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class InputPresenter(val inputView: InputView) {

    fun inputData(nama: String, nohp: String, alamat: String) {

        if (nama.isNotEmpty() && nohp.isNotEmpty() && alamat.isNotEmpty()) {

            NetworkModule.service().insertData(nama, nohp, alamat).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response ->
                    if (response.isSuccess == true) {
                        inputView.inputSuccess(response)
                    } else {
                        inputView.errorInput(response.message ?: "")
                    }
                }, {
                    inputView.errorInput(it.localizedMessage)
                })

        } else {
            inputView.empty()
        }
    }

   fun updateData(id: String, nama: String, nohp: String, alamat: String) {

       if (nama.isNotEmpty() && nohp.isNotEmpty() && alamat.isNotEmpty()) {

           NetworkModule.service().updateData(id, nama, nohp, alamat)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe({response ->
                   if (response.isSuccess == true) {
                       inputView.updateSuccess(response)
                   } else {
                       inputView.errorInput(response.message ?: "")
                   }
               }, {
                   inputView.errorInput(it.localizedMessage)
               })

       } else {
           inputView.empty()
       }
   }
}