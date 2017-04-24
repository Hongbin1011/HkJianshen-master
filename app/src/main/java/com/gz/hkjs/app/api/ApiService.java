package com.gz.hkjs.app.api;

import com.gz.hkjs.app.bean.ChooseItem;
import com.gz.hkjs.app.bean.FindDetail;
import com.gz.hkjs.app.bean.FindSummary;
import com.gz.hkjs.app.bean.RecipesDetail;
import com.gz.hkjs.app.bean.RecipesSummary;
import com.gz.hkjs.app.bean.TrainVedioDetail;
import com.gz.hkjs.app.bean.UserHomeData;
import com.gz.hkjs.app.bean.Version;
import com.gz.hkjs.app.bean.VideoData;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

import static com.gz.hkjs.app.api.ApiConstants.API_Choose_LIST;
import static com.gz.hkjs.app.api.ApiConstants.API_FIND_DETAIL;
import static com.gz.hkjs.app.api.ApiConstants.API_FIND_LIST;
import static com.gz.hkjs.app.api.ApiConstants.API_RECIPES_DETAIL;
import static com.gz.hkjs.app.api.ApiConstants.API_RECIPES_LIST;
import static com.gz.hkjs.app.api.ApiConstants.API_TRAIN_VEDIO_DETAIL_LIST;
import static com.gz.hkjs.app.api.ApiConstants.API_USER_HOME_DATA;
import static com.gz.hkjs.app.api.ApiConstants.API_VEDIO_LIST;
import static com.gz.hkjs.app.api.ApiConstants.API_VERSION;

/**
 * des:ApiService
 * Created by zzy
 * on 2017年3月31日
 */
public interface ApiService {

    @FormUrlEncoded
    @POST(API_FIND_DETAIL)
    Observable<FindDetail> getNewDetail(
            @Header("Cache-Control") String cacheControl,
            @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST(API_RECIPES_DETAIL)
    Observable<RecipesDetail> getRecipesDetail(
            @Header("Cache-Control") String cacheControl,
            @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST(API_FIND_LIST)
    Observable<FindSummary> getFindsList(
            @Header("Cache-Control") String cacheControl,
            @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST(API_RECIPES_LIST)
    Observable<RecipesSummary> getRecipesList(
            @Header("Cache-Control") String cacheControl,
            @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST(API_VERSION)
    Observable<Version> getVersionData(
            @Header("Cache-Control") String cacheControl,
            @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST(API_VEDIO_LIST)
    Observable<VideoData> getVideoList(
            @Header("Cache-Control") String cacheControl,
            @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST(API_Choose_LIST)
    Observable<ChooseItem> getChooseItemList(
            @Header("Cache-Control") String cacheControl,
            @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST(API_TRAIN_VEDIO_DETAIL_LIST)
    Observable<TrainVedioDetail> getTrainVedioDetail(
            @Header("Cache-Control") String cacheControl,
            @FieldMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST(API_USER_HOME_DATA)
    Observable<UserHomeData> getHomeDataList(
            @Header("Cache-Control") String cacheControl,
            @FieldMap HashMap<String, String> map);
}
