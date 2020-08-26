package com.pascal.mahasiswaapp_pascaladitiamuclis.UI.input.presenter

import com.pascal.mahasiswaapp_pascaladitiamuclis.model.action.ResponseAction

interface InputView {

    fun inputSuccess(response: ResponseAction)
    fun errorInput(msg: String)
    fun updateSuccess(response: ResponseAction)
    fun empty()
}