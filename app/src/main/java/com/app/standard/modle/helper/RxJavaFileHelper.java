package com.app.standard.modle.helper;

import com.app.standard.modle.http.api.FileService;
import com.app.standard.modle.http.response.BaseResponse;
import com.app.standard.modle.rxjava.file.ANetWorkConfig;
import com.app.standard.modle.rxjava.file.DownLoadListener;
import com.app.standard.modle.rxjava.file.UploadEntry;
import com.app.standard.modle.rxjava.file.UploadListener;
import com.app.standard.util.BitmapUtil;
import com.app.standard.util.RxJavaUtil;

import org.reactivestreams.Publisher;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/*
 * Created by ruibing.han on 2018/2/23.
 */

public class RxJavaFileHelper {

    static CompositeDisposable mCompositeDisposable;

    /*
     * 单文件上传
     *
     * @param filePath 上传的文件路径
     */
    public static void singleFileUpLoad(String filePath, final UploadListener<UploadEntry> uploadListener) {
        File file = new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(ANetWorkConfig.getRetrofit(ANetWorkConfig.TYPE_UPLOAD, uploadListener, null)
                .create(FileService.class)
                .singleFileUpLoad(body)
                .compose(RxJavaUtil.<BaseResponse<UploadEntry>>rxSchedulerHelper())
                .compose(RxJavaUtil.<UploadEntry>handleBaseResponseResult())
                .subscribeWith(new ResourceSubscriber<UploadEntry>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        uploadListener.uploadStart();
                    }

                    @Override
                    public void onNext(UploadEntry uploadEntry) {
                        uploadListener.uploadSuccess(uploadEntry);
                    }

                    @Override
                    public void onError(Throwable e) {
                        uploadListener.uploadFail(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        uploadListener.uploadComplete();
                    }
                })
        );
    }

    /*
     * 多文件上传
     *
     * @param fileList 上传的文件路径列表
     */
    public static void multiFileUpLoad(List<String> fileList, final UploadListener<String> uploadListener) {
        //添加额外的参数
        final Map<String, RequestBody> map = new HashMap<>();
        map.put("userName", RequestBody.create(MediaType.parse("form-data"), "小明"));
        map.put("password", RequestBody.create(MediaType.parse("form-data"), "123456"));
        map.put("userId", RequestBody.create(MediaType.parse("form-data"), "35234"));

        final MultipartBody.Builder builder = new MultipartBody.Builder();

        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(Flowable.fromIterable(fileList)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String url) throws Exception {
                        //调用图片压缩，返回压缩后路径path
                        String path = BitmapUtil.saveBitmap(url, "newIcon");
                        //注意，FileData是后台给你的对应的字段
                        builder.addFormDataPart("FileData", "avatar.png", RequestBody.create(MultipartBody.FORM, new File(path)));
                        return path;
                    }
                })
                .flatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(String s) throws Exception {
                        return ANetWorkConfig.getRetrofit(ANetWorkConfig.TYPE_UPLOAD, uploadListener, null)
                                .create(FileService.class)
                                .multiFileUpLoad(map, builder.build());
                    }
                })
                .compose(RxJavaUtil.<String>rxSchedulerHelper())
                .subscribeWith(new ResourceSubscriber<String>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        uploadListener.uploadStart();
                    }

                    @Override
                    public void onNext(String s) {
                        uploadListener.uploadSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        uploadListener.uploadFail(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        uploadListener.uploadComplete();
                    }
                })
        );
    }


    /*
    * 单文件下载
    * @param loadListener
    */
    public static void singleFileDownLoad(final DownLoadListener<ResponseBody> downLoadListener) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(ANetWorkConfig.getRetrofit(ANetWorkConfig.TYPE_DOWNLOAD, null, downLoadListener)
                .create(FileService.class)
                .downLoadFile()
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        //保存到文件中 在onNext之前执行
                        downLoadListener.downLoadSaving();
                    }
                })
                .compose(RxJavaUtil.<ResponseBody>rxSchedulerHelper())
                .subscribeWith(new ResourceSubscriber<ResponseBody>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        downLoadListener.downLoadStart();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        downLoadListener.downLoadSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable t) {
                        downLoadListener.downLoadFail(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        downLoadListener.downLoadComplete();
                    }
                })
        );
    }

    /*
     * 单文件断点下载
     * @param loadListener
     */
    public static void singleFileBreakpointDownLoad(final DownLoadListener<ResponseBody> downLoadListener) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(ANetWorkConfig.getRetrofit(ANetWorkConfig.TYPE_DOWNLOAD, null, downLoadListener)
                .create(FileService.class)
                .downloadBreakpoint("url","100000")//"bytes=" + info.getReadLength() + "-",info.getUrl()
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        //保存到文件中 在onNext之前执行
                        downLoadListener.downLoadSaving();
                    }
                })
                .compose(RxJavaUtil.<ResponseBody>rxSchedulerHelper())
                .subscribeWith(new ResourceSubscriber<ResponseBody>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        downLoadListener.downLoadStart();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        downLoadListener.downLoadSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable t) {
                        downLoadListener.downLoadFail(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        downLoadListener.downLoadComplete();
                    }
                })
        );
    }

    //取消上传
    public void cancelSubscriber() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
