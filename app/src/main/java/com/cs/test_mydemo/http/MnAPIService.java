package com.cs.test_mydemo.http;

import com.cs.test_mydemo.entry.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by chenshuai on 2016/11/7.
 * 请求地址：http://v.juhe.cn/toutiao/index
 *请求参数：type=top&key=ce18f1786cf2e609acbc076a4b6a2df5
 *请求方式：GET
 */

public interface MnAPIService {
    @Headers("Cache-Control: public, max-age=60")
    @GET("toutiao/index")
    Call<News>getData(@Query("type") String type,@Query("key") String key);

}
