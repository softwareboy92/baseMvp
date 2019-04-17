# BaseMvp

#### 项目介绍
使用MVP架构
1.Rxjava

2.RxAndroid

3.Retrofit

4.Glide

5.七牛的视频压缩处理

6.SmartRefreshLayout：1.0.4上拉加载框架

7.Zbardecoder 的二维码扫描

8.自定义图片选择器

9.自定义Notification


#### 软件架构
软件架构使用说明


#### 安装教程

1. 将arr包导入项目中

#### 使用说明

1. Base系列使用说明
```
 1.创建一个Contract
 2.创建Presenter
 3.创建Actvitity或者Fragment
 具体实现如下：
 1).MainContract
 /**
 * 作者：created by albert on 2018/9/18 16:29
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public interface MainContract {

    interface View extends BaseContract.BaseView{}

     interface Presenter extends BaseContract.BasePresenter<View>{

     }
}

2).MainPresent
/**
 * 作者：created by albert on 2018/9/18 16:29
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
}
3).MainActivity
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{
	 @Override
    protected void initView() {
    }
     @Override
    protected MainPresenter initPresenter() {
        return null;
    }
      @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }

}

说明：如果是Activity,继承我所封装的BaseActivity ，如果是Fragment 继承我所封装的BaseFragment即可

```
2. 视频压缩使用方法
```
  /**
     * 压缩视频
     *
     * @param mContext
     * @param filepath 需要压缩的视频文件路径
     * @param newPath  压缩文件所要保存的文件路径
     */
    public void compressVideoResouce(Context mContext,String filepath,String newPath ) {
        if (TextUtils.isEmpty(filepath)) {
            CommonUtil.showToast("请先选择转码文件！");
            return;
        }
        //PLShortVideoTranscoder初始化，三个参数，第一个context，第二个要压缩文件的路径，第三个视频压缩后输出的路径
        PLShortVideoTranscoder mShortVideoTranscoder = new PLShortVideoTranscoder(mContext, filepath, newPath+System.currentTimeMillis() + ".mp4");
        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(filepath);
        String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
        String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
        String rotation = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION); // 视频旋转方向
        int transcodingBitrateLevel = 6;//我这里选择的2500*1000压缩，这里可以自己选择合适的压缩比例
        mShortVideoTranscoder.transcode(Integer.parseInt(width), Integer.parseInt(height), getEncodingBitrateLevel(transcodingBitrateLevel), false, new PLVideoSaveListener() {
            @Override
            public void onSaveVideoSuccess(String s) {
                Log.e("lv", "s" + s+"就是转换之后的文件路径");
            }
            @Override
            public void onSaveVideoFailed(final int errorCode) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (errorCode) {
                            case ERROR_NO_VIDEO_TRACK:
                                CommonUtil.showToast("该文件没有视频信息！");
                                break;
                            case ERROR_SRC_DST_SAME_FILE_PATH:
                                CommonUtil.showToast("源文件路径和目标路径不能相同！");
                                break;
                            case ERROR_LOW_MEMORY:
                                CommonUtil.showToast("手机内存不足");
                                break;
                            default:
                                CommonUtil.showToast("failed: " + errorCode);
                        }
                    }
                });
            }
            @Override
            public void onSaveVideoCanceled() {
                Log.e("lv", "onSaveVideoCanceled");
            }
            @Override
            public void onProgressUpdate(float percentage) {
                Log.e("lv", "onProgressUpdate");
            }
        });
    }
 /**
     * 设置压缩质量
     *
     * @param position
     * @return
     */
    private int getEncodingBitrateLevel(int position) {
        return ENCODING_BITRATE_LEVEL_ARRAY[position];
    }

    /**
     * 选的越高文件质量越大，质量越好
     */
    public static final int[] ENCODING_BITRATE_LEVEL_ARRAY = {
            500 * 1000,
            800 * 1000,
            1000 * 1000,
            1200 * 1000,
            1600 * 1000,
            2000 * 1000,
            2500 * 1000,
            4000 * 1000,
            8000 * 1000,
    };
```
3. SmartRefreshLayout使用方法
布局文件：
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/white"
    android:orientation="vertical">

    <com.scwang.smartrefresh.layout.SmartRefreshLayout
        android:id="@+id/fragment_find_smartref"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/white">

        <android.support.v7.widget.RecyclerView
            android:id="@+id/fragment_find_recycle"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@color/white"
            android:orientation="vertical"
            android:overScrollMode="never"
            android:scrollbars="none"
            tools:showIn="@layout/activity_home" />

    </com.scwang.smartrefresh.layout.SmartRefreshLayout>
</LinearLayout>
```

**创建Application 直接继承我的UtilsApp即可

代码实现部分：

```
smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });
```
在Base的基类中，还包含了RecycleAdapter和BaseAdapter的基类；具体继承和使用如下:

```
/**
 * 作者：create by albert on 2018/9/9 下午2:23
 * 邮箱：lvhzongdi@iclou.com
 * 这个是RecycleView的
 */
public class HomeAdapter extends BaseAdapter<String> {

    @Override
    protected int bindLayout(int viewType) {
        return R.layout.item_home;
    }

    @Override
    protected void bind(BaseViewHolder holder, String data) {
        TextView textView = holder.itemView.findViewById(R.id.textview);
        textView.setText(data);
    }
}
```

```
private class FindViewGridViewAdapter extends BAdapter<String> {

        private Context con;

        public FindViewGridViewAdapter(Context context, List<String> list) {
            super(context, list);
            con = context;
        }

        @Override
        public int getContentView() {
            return R.layout.item_find_context;
        }

        @Override
        public void onInitView(View view, int position) {
            TextView textView = view.findViewById(R.id.item_find_content_tv);
            FindResModel.DataBean.PageListBean.GListBean bean = getItem(position);
            textView.setText(list.get(position));
        }
    }
```

4. Zbardecoder使用方法
    生成二维码：
	```
    QRCodeUtil.createQRImage(tv_order_detail_address_center.getText().toString(), widthPix, heightPix, null)
    ```
    识别二维码：

 1. 相机的访问权限别忘了
```
       XPermission.getPermissions(this, permission, true, true, new OnPermissionsListener() {
                   @Override
                   public void missPermission(String[] strings) {
                       if (strings.length == 0) {
                           startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE);
                       }
                   }
               });
```
2.同样的，返回的时候要进行值的处理
```
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if ( requestCode == REQUEST_CODE) {
                }
            }
```

5. 自定义图片选择器使用方法：

 首先感谢：https://github.com/donkingliang/ImageSelector 这个人
```
   a.添加权限
    //储存卡的读写权限
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    //调用相机权限
    <uses-permission android:name="android.permission.CAMERA" />
    b.添加activity和7.0以后的文件访问
    //图片选择Activity
    <activity android:name=".ImageSelectorActivity"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar"
        android:configChanges="orientation|keyboardHidden|screenSize"/>
    //图片预览Activity
    <activity android:name=".PreviewActivity"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar"
        android:configChanges="orientation|keyboardHidden|screenSize"/>
    //图片剪切Activity
    <activity
        android:name=".ClipImageActivity"
        android:theme="@style/Theme.AppCompat.Light.NoActionBar" />
    <!-- Android 7.0 文件共享配置，必须配置 -->
    <provider
        android:name="android.support.v4.content.FileProvider"
        android:authorities="${applicationId}.fileprovider"
        android:exported="false"
        android:grantUriPermissions="true">
        <meta-data
            android:name="android.support.FILE_PROVIDER_PATHS"
            android:resource="@xml/file_paths" />
    </provider>
    c.在res/xml文件夹下创建file_paths.xml文件(名字可以自己定义)
    <?xml version="1.0" encoding="utf-8"?>
    <paths>
        <!-- 这个是保存拍照图片的路径,必须配置。 -->
        <external-path
            name="images"
            path="Pictures" />
    </paths>
    d.调图片选择器
      说明：ImageSelector支持图片的单选、限数量的多选和不限数量的多选。还可以设置是否使用相机、是否剪切图片等配置。ImageSelector提供了统一的调起相册的方法。
```
      1）单选
       ImageSelector.builder()
              .useCamera(true) // 设置是否使用拍照
              .setSingle(true)  //设置是否单选
      	.setViewImage(true) //是否点击放大图片查看,，默认为true
              .start(this, REQUEST_CODE); // 打开相册

	 2）限数量的多选(比喻最多9张)
      ImageSelector.builder()
              .useCamera(true) // 设置是否使用拍照
              .setSingle(false)  //设置是否单选
              .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
      	.setSelected(selected) // 把已选的图片传入默认选中。
      	.setViewImage(true) //是否点击放大图片查看,，默认为true
              .start(this, REQUEST_CODE); // 打开相册3）不限数量的多选

      ImageSelector.builder()
              .useCamera(true) // 设置是否使用拍照
              .setSingle(false)  //设置是否单选
              .setMaxSelectCount(0) // 图片的最大选择数量，小于等于0时，不限数量。
      	.setSelected(selected) // 把已选的图片传入默认选中。
      	.setViewImage(true) //是否点击放大图片查看,，默认为true
              .start(this, REQUEST_CODE); // 打开相册

		3）单选并剪裁

      ImageSelector.builder()
             .useCamera(true) // 设置是否使用拍照
             .setCrop(true)  // 设置是否使用图片剪切功能。
             .setSingle(true)  //设置是否单选
             .setViewImage(true) //是否点击放大图片查看,，默认为true
             .start(this, REQUEST_CODE); // 打开相册


e.REQUEST_CODE就是调用者自己定义的启动Activity时的requestCode，这个相信大家都能明白。selected可以在再次打开选择器时，把原来已经选择过的图片传入，使这些图片默认为选中状态。

f.接收选择器返回的数据在Activity的onActivityResult方法中接收选择器返回的数据。


     @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE && data != null) {
    	    //获取选择器返回的数据
                ArrayList<String> images = data.getStringArrayListExtra(
                ImageSelectorUtils.SELECT_RESULT);
            }
        }

总结：ImageSelectorUtils.SELECT_RESULT是接收数据的key。数据是以ArrayList的字符串数组返回的，就算是单选，返回的也是ArrayList数组，只不过这时候ArrayList只有一条数据而已。ArrayList里面的数据就是选中的图片的文件路径。


6.自定义Notification 使用方法
一个布局：
<RelativeLayout
      android:id="@+id/mLyout"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      >

</RelativeLayout>

Configuration cfg=new Configuration.Builder()
      .setAnimDuration(700)
      .setDispalyDuration(1500)
      .setBackgroundColor("#FFBDC3C7")
      .setTextColor("#FF444444")
      .setIconBackgroundColor("#FFFFFFFF")
      .setTextPadding(5)                      //dp
      .setViewHeight(48)                      //dp
      .setTextLines(2)                        //You had better use setViewHeight and setTextLines together
      .setTextGravity(Gravity.CENTER)         //only text def  Gravity.CENTER,contain icon Gravity.CENTER_VERTICAL
      .build();

NiftyNotificationView.build(this,msg, effect,R.id.mLyout,cfg)
      .setIcon(R.drawable.lion)               //remove this line ,only text
      .setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
      //add your code
      }
      })
      .show();

调用如下方法，其中Effect中更包含如下：
      Effect.standard
      Effect.slideOnTop
      Effect.flip
      Effect.slideIn
      Effect.jelly
      Effect.thumbSlider
      Effect.scale

