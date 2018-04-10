package com.app.standard.modle.http.api;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.modle.http.response.BaseResponse;
import com.app.standard.modle.rxjava.file.UploadEntry;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface FileService {
    String FILE_URL = "http://180.168.134.212:18601/";

    /**
     * 单文件上传
     */
    @Multipart
    @POST("Ui/Image/uploadUnlimited.html")
    Flowable<BaseResponse<UploadEntry>> singleFileUpLoad(@Part MultipartBody.Part file);

    /**
     * 多文件上传
     */
    @Multipart
    @POST("Ui/Image/uploadUnlimited.html")
    Flowable<String> multiFileUpLoad(@PartMap Map<String, RequestBody> parameterMap, @Part("imgs") MultipartBody body);


    @Streaming//注明为流文件，防止retrofit将大文件读入内存
    @GET()
    Flowable<ResponseBody> downLoadFile();//通过@Url覆盖baseurl

    /*断点续传下载接口*/
    @Streaming/*大文件需要加入这个判断，防止下载过程中写入到内存中*/
    @GET
    Flowable<ResponseBody> downloadBreakpoint(@Url String url,@Header("RANGE") String start);
}
