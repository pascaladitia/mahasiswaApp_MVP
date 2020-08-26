package com.pascal.mahasiswaapp_pascaladitiamuclis.config

import com.pascal.mahasiswaapp_pascaladitiamuclis.model.action.ResponseAction
import com.pascal.mahasiswaapp_pascaladitiamuclis.model.getData.ResponseGetData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //getData
    @GET("getData.php")
    fun getData(): Flowable<ResponseGetData>

    //getDataById
    @GET("getData.php")
    fun getDataById(@Query("mahasiswa_id") id: String) : Call<ResponseGetData>

    //insert
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData( @Field("mahasiswa_nama") nama: String,
                    @Field("mahasiswa_nohp") nohp: String,
                    @Field("mahasiswa_alamat") alamat: String) : Flowable<ResponseAction>

    //update
    @FormUrlEncoded
    @POST("update.php")
    fun updateData( @Field("id_mahasiswa") id: String,
                    @Field("mahasiswa_nama") nama: String,
                    @Field("mahasiswa_nohp") nohp: String,
                    @Field("mahasiswa_alamat") alamat: String) : Flowable<ResponseAction>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(@Field("id_mahasiswa") id: String) : Flowable<ResponseAction>
}