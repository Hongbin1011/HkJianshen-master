package com.gz.hkjs.app.ui.main.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.gz.hkjs.app.R;
import com.gz.hkjs.app.app.AppApplication;
import com.gz.hkjs.app.util.XUtilNet;
import com.gz.hkjs.app.util.permission.PermissionCallBack;
import com.gz.hkjs.app.util.permission.PermissionUtil;
import com.gz.hkjs.app.util.popwindow.ShowPopupPhoto;
import com.gz.hkjs.app.widget.Photo_State;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.MineTitleBar;
import com.orhanobut.logger.Logger;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gz.hkjs.app.ui.main.fragment.MineMainFragment.IMAGE_COMPLETE;
import static com.gz.hkjs.app.ui.main.fragment.MineMainFragment.PHOTOTAKE;
import static com.gz.hkjs.app.ui.main.fragment.MineMainFragment.PHOTOZOOM;

/**
 * 意见反馈 lwy
 */

public class FeedbackActivity extends BaseActivity {


    @Bind(R.id.mtb_feedback)
    MineTitleBar mineTitleBar;
    @Bind(R.id.tv_feedback)
    TextView tvFeedback;
    @Bind(R.id.et_feedback_content)
    EditText etFeedbackContent;
    @Bind(R.id.iv_feedback_img)
    ImageView ivFeedbackImg;
    @Bind(R.id.tv_feedback_phone)
    TextView tvFeedbackPhone;
    @Bind(R.id.et_qq_email)
    EditText etQqEmail;

    //Data
    private Intent intent;
    private String photoSaveName = "temp_photo.jpg";//照片的名字
    //    private String photoSavePath;//保存路径
    private File imageFile;
    private String feedbackContent = "";//反馈内容
    private String feedbackContactWay = "";//联系方式
    protected Handler mHandler = new Handler();
    private String mUploadFilePath = "/storage/emulated/0/DCIM/Camera/IMG_20150701_104558.jpg";

    @Override
    public int getLayoutId() {
        return R.layout.mine_feedback;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mineTitleBar.setLeftBack(true);
        mineTitleBar.setOnLeftImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mineTitleBar.setRightcommit(true);
        mineTitleBar.setTtileRightText(getString(R.string.my_feedback_up));
        mineTitleBar.setTitleText(getString(R.string.my_feedback));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_title_right, R.id.et_feedback_content, R.id.iv_feedback_img, R.id.et_qq_email})
    public void onViewClicked(final View view) {
        switch (view.getId()) {

            case R.id.tv_title_right://提交
                feedbackContent = etFeedbackContent.getText().toString();
                feedbackContactWay = etQqEmail.getText().toString();
                if (TextUtils.isEmpty(feedbackContent)) {//必须发送文字
                    ToastUitl.showShort("输入不能为空");
                } else {
                    if (XUtilNet.isNetConnected()) {
                        upLoadImage(mUploadFilePath);
                    } else {
                        ToastUitl.showShort("请检查网络连接");
                    }
                }
                break;
            case R.id.et_feedback_content:
                break;
            case R.id.iv_feedback_img://选择图片
                PermissionUtil.getCameraPermission(this, new PermissionCallBack() {
                    @Override
                    public void PermissionSuccess() {
                        new ShowPopupPhoto(FeedbackActivity.this).setConfig(0, new Photo_State() {
                            @Override
                            public void first_item() {//相册
                                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(i, PHOTOZOOM);
                            }

                            @Override
                            public void second_item() {//拍照
                                camera(view);

                            }
                        }).showPopupWindow(ivFeedbackImg);
                    }

                    @Override
                    public void PermissionFail() {

                        Logger.i("授权失败");
                    }
                });
                break;
            case R.id.et_qq_email:
                break;
        }
    }

    /**
     * 从相机获取
     */
    public void camera(View view) {
        // 激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            imageFile = new File(Environment.getExternalStorageDirectory(),
                    photoSaveName);
            // 从文件中创建uri
            Uri uri = Uri.fromFile(imageFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTOTAKE);
    }

    /**
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, IMAGE_COMPLETE);
    }

    /*
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 反馈的数据
     */
    private void feedBackData() {
        //如果有图片先传图片
//        if (imageFile != null) {
//            upLoadImage();
//        } else {
//            feedBack();
//        }
    }

    private void feedBack() {

    }


    private void upLoadImage(String uploadFilePath) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ToastUitl.showShort("上传中，请等待");
            }
        });
        PutObjectRequest put = new PutObjectRequest(AppApplication.OSS_BUCKET, mUploadFilePath + "/" + getImageObjectKey("123456789"), uploadFilePath);
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Logger.i("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);

            }
        });
        AppApplication.oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(final PutObjectRequest request, PutObjectResult result) {
                Logger.i("PutObject", "UploadSuccess");
                Logger.i("ETag", result.getETag());
                Logger.i("RequestId", result.getRequestId());
                //将上传成功的图片地址传给自己的服务器后台，比如修改用户数据库中，用户头像的url。
                //修改后台url成功后，再利用glide 下载最新的照片，修改本地头像图片。
                //request.getObjectKey() 是图片地址，但是不包含，OSS 图片域名
              uploadImage(request.getObjectKey());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //如果上传失败了，通过mHandler ，发出失败的消息到主线程中。处理异常。
//                          showNetErrorInfo();
                    }
                });
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }

            }
        });
    }

    protected void uploadImage(String objectKey) {
//        String url=Http

    }

    /**
     * 通过创建照片的时间来命名照片
     */

    private String getImageObjectKey(String strUserCode) {

        Date date = new Date();
        return new SimpleDateFormat("yyyy/M/d").format(date) + "/" + strUserCode + new SimpleDateFormat("yyyyMMddssSSS").format(date) + ".jpg";

    }


    /**
     * 图片选择或拍照结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case PHOTOZOOM://相册

                if (data != null) {
                    // 得到图片的全路径
                    Uri uri = data.getData();
                    crop(uri);
                }
                break;
            case PHOTOTAKE://拍照
                if (hasSdcard()) {
                    crop(Uri.fromFile(imageFile));
                } else {
                    ToastUitl.showShort("未找到存储卡，无法存储照片！");
                }
                break;
            default:
                break;
        }
        if (requestCode == IMAGE_COMPLETE) {
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                this.ivFeedbackImg.setImageBitmap(bitmap);
            }
            try {
                // 将临时文件删除
                imageFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
