package com.six.qiangbao.activitys;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.Avatar;
import com.saint.netlibrary.model.Mine;
import com.six.qiangbao.BaseActivity;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.ConstantUtil;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Picasso;


import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by 志浩 on 2016/8/23.
 */
public class PersionalInfoActivity extends BaseActivity {


    @BindView(R.id.info_bar)
    Toolbar mToolbar;
    @BindView(R.id.info_head)
    ImageView mImage;
    @BindView(R.id.info_name)
    TextView mText;
    @BindView(R.id.change_head)
    RelativeLayout head;
    @BindView(R.id.change_username)
    RelativeLayout name;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private String IMAGE_PATH = "";
    private File imageFile;
    private Uri destinationUri;
    private String imageFilePath;
    private String filename;
    @OnClick(R.id.change_username) void changname(){
        Intent intent = new Intent(this,NameInfoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.change_head) void  changhead(){
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();
        Window mWindow = dialog.getWindow();
        mWindow.setGravity(Gravity.BOTTOM);
        mWindow.setContentView(R.layout.change_head_button);
        mWindow.findViewById(R.id.album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crop.pickImage(PersionalInfoActivity.this, PHOTO_REQUEST_GALLERY);
                dialog.cancel();
            }
        });
        mWindow.findViewById(R.id.tack_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                dialog.cancel();
            }
        });
        mWindow.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persional_info_layout);
        initbar();
        destinationUri = Uri.fromFile(new File(getCacheDir(), "crop"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                if (resultCode == RESULT_OK) {
                    Uri uri = Uri.fromFile(new File(imageFilePath));
                    Crop.of(uri, destinationUri).asSquare().start(mContext);
                }
                break;
            case PHOTO_REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    Uri imageUri = data.getData();
                    getImagePath(imageUri);
                    imageFile = new File(IMAGE_PATH);
                    Crop.of(imageUri, destinationUri).asSquare().start(mContext);
                }
                break;
            case Crop.REQUEST_CROP:
                if (resultCode == RESULT_OK) {
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        options.inPurgeable = true;
                        options.inInputShareable = true;
                        options.inSampleSize = 10;
                        Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(Crop.getOutput(data)), null, options);
                        if (bitmap != null) {
                            mImage.setImageBitmap(bitmap);
                        } else if (!bitmap.isRecycled()) {
                            bitmap.recycle();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    upLoadImage(Crop.getOutput(data));
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void initbar(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.left_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, PHOTO_REQUEST_TAKEPHOTO);
            }
        }
    }

    private void getImagePath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null,
                null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        IMAGE_PATH = cursor.getString(column_index);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    private void info (){
        final ApiWrapper wrapper =new ApiWrapper();
        Subscription subscription = wrapper.me()
                .subscribe(newSubscriber(new Action1<Mine>() {
                    @Override
                    public void call(Mine mine) {
                        if (mine.getUsername().equals("")){
                            mText.setText(mine.getMobile());
                        }else {
                            mText.setText(mine.getUsername());
                        }
                        Picasso.with(PersionalInfoActivity.this).load(ConstantUtil.IMAGE_HEAD+mine.getImg()).error(R.mipmap.ic_launcher).into(mImage);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }

    private void upLoadImage(Uri uri)  {
        File file = new File(uri.getPath());
        RequestBody Filedata =
                RequestBody.create(MediaType.parse("image/*"), file);
        if (file != null) {
            final ApiWrapper wrapper = new ApiWrapper();
            Subscription subscription = wrapper.avatar(Filedata)
                    .subscribe(newSubscriber(new Action1<Avatar>() {
                        @Override
                        public void call(Avatar avatar) {
                            filename = avatar.getFilename();
                            if (filename != null){
                                applyImage(filename);
                            }else {
                             Log.e("上传失败=====",avatar+"");
                            }
                        }
                    }));
            mCompositeSubscription.add(subscription);
        }
    }

    private void applyImage(String filename){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.apply(0,0,80,80,filename,1)
                .subscribe(newSubscriber(new Action1<Avatar>() {
                    @Override
                    public void call(Avatar avatar) {
                        System.out.print(avatar);
                        if (avatar.getRst() == String.valueOf(0)){
                                info();
                        }else {
                            Toast.makeText(mContext,avatar.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
        mCompositeSubscription.add(subscription);
    }
    @Override
    protected void onStart() {
        super.onStart();
        info();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("path", imageFilePath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imageFilePath = savedInstanceState.getString("path");
    }

}
