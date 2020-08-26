package com.pascal.mahasiswaapp_pascaladitiamuclis.UI.get.presenter

import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.DataItem
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.ResponseGetData

interface GetView {

    fun getSuccess(msg: String, data: List<DataItem>?)
    fun getError(msg: String)
    fun getDelete(msg: String)
}