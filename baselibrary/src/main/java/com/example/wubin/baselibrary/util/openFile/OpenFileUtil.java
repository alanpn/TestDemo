package com.example.wubin.baselibrary.util.openFile;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.DeviceUtil;
import com.example.wubin.baselibrary.util.FileUtil;
import com.example.wubin.baselibrary.util.MyException;
import com.example.wubin.baselibrary.util.ShowUtil;

import java.io.File;

public class OpenFileUtil {

    /**
     * 打开文件(自动判断文件类型)
     *
     * @param fileName 文件名(含后缀)
     */
    public void openFile(String fileName) {
        startActivity(fileName);
    }

    /**
     * 打开HTML文件
     */
    public void openHtmlFile(String fileName) throws Exception {
        startActivity(getUri(fileName), MimeType.HTML);
    }

    /**
     * 打开图片文件
     */
    public void openImageFile(String fileName) {
        startActivity(getUri(fileName), MimeType.IMAGE);
    }

    /**
     * 打开PDF文件
     */
    public void openPdfFile(String fileName) {
        startActivity(getUri(fileName), MimeType.PDF);
    }

    /**
     * 打开文本文件
     */
    public void oepnPlainFile(String fileName) {
        startActivity(getUri(fileName), MimeType.TXT);
    }

    /**
     * 打开音频文件
     */
    public void openAudioFile(String fileName) {
        startActivity(getUri(fileName), MimeType.AUDIO);
    }

    /**
     * 打开视频文件
     */
    public void openVideoFile(String fileName) {
        startActivity(getUri(fileName), MimeType.VEDIO);
    }

    /**
     * 打开CHM文件
     */
    public void openChmFile(String fileName) {
        startActivity(getUri(fileName), MimeType.CHM);
    }

    /**
     * 打开Word文件
     */
    public void oepnWordFile(String fileName) {
        startActivity(getUri(fileName), MimeType.WORD);
    }

    /**
     * 打开Excel文件
     */
    public void openExcelFile(String fileName) {
        startActivity(getUri(fileName), MimeType.EXCEL);
    }

    /**
     * 打开PPT文件
     */
    public void openPptFile(String fileName) {
        startActivity(getUri(fileName), MimeType.PPT);
    }

    /**
     * 打开apk文件
     *
     * @param fileName
     */
    public void openApkFile(String fileName) {

        if (DeviceUtil.isNotInstallApkPermission()) {
            ShowUtil.toastShow(errorMsgForNeedInstallPression);
            return;
        }

        startActivity(getUri(fileName), MimeType.APK);

    }

    //=================================================================

    private static final String className = OpenFileUtil.class.getName();

    private static final String FileProviderPath = "com.base.baselibrary.fileprovider";

    private final String errorMsgForNeedInstallPression = "请开启安装APK权限";

    private final String errorMsgForNeedAPP = "你未安装可以打开该文件的应用";

    private static OpenFileUtil util;

    private OpenFileUtil() {
    }

    public static OpenFileUtil getInstance() {
        if (null == util) util = new OpenFileUtil();
        return util;
    }

    private File getFile(String fileName) {
        // 指定文件位置
        File file = new File(BaseActivity.myActivity.getCacheDir(), "/test_" + fileName);

        if (file.exists()) {
            return file;
        }

        // 把assets文件写入CachDir中
        String filePath = file.getAbsolutePath();
        String content = FileUtil.getInstance().readAssetsFile(fileName);
        FileUtil.getInstance().writeFile(content, filePath);
        // 修改文件权限
        FileUtil.getInstance().chomdAll(filePath);

        return file;
    }

    private Uri getUri(File file) {
        if (DeviceUtil.getSDK() >= Build.VERSION_CODES.N) {   // android 7.0
            return FileProvider.getUriForFile(BaseActivity.myActivity, FileProviderPath, file);
        }
        return Uri.fromFile(file);
    }

    private Uri getUri(String fileName) {
        return getUri(getFile(fileName));
    }

    private Intent getIntent(Uri uri, String mime) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(uri, mime);
        return intent;
    }

    private boolean checkNotIntent(Intent intent) {
        if (intent.resolveActivity(DeviceUtil.getPackageManager()) == null) {
            ShowUtil.toastShow(className, errorMsgForNeedAPP);
            return true;
        }
        return false;
    }

    private void startActivity(Uri uri, String mime) {
        ActivityUtil.startActivity(getIntent(uri, mime));
    }

    private void startActivity(String fileName) {
        try {
            checkFileName(fileName);
            startActivity(getUri(fileName), getMime(fileName));
        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }
    }

    private void checkFileName(String fileName) throws MyException {
        if (!fileName.contains(".")) throw new MyException(className, "文件名没有后缀名");
    }

    private String getMime(String fileName) {

        String end = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {

            return MimeType.AUDIO;

        } else if (end.equals("3gp") || end.equals("mp4")) {

            return MimeType.VEDIO;

        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg") || end.equals("bmp")) {

            return MimeType.IMAGE;

        } else if (end.equals("apk")) {

            return MimeType.APK;

        } else if (end.equals("ppt")) {

            return MimeType.PPT;

        } else if (end.equals("xls") || end.equals("xlsx")) {

            return MimeType.EXCEL;

        } else if (end.equals("doc") || end.equals("docx")) {

            return MimeType.WORD;

        } else if (end.equals("pdf")) {

            return MimeType.PDF;

        } else if (end.equals("chm")) {

            return MimeType.CHM;

        } else if (end.equals("html")) {

            return MimeType.HTML;

        } else if (end.equals("txt")) {

            return MimeType.TXT;

        }

        return "*/*";

    }

    // 类型
    interface MimeType {

        String APK = "application/vnd.android.package-archive";

        String VEDIO = "video/*";
        String AUDIO = "audio/*";
        String IMAGE = "image/*";

        String PPT = "application/vnd.ms-powerpoint";
        String EXCEL = "application/vnd.ms-excel";
        String WORD = "application/msword";
        String PDF = "application/pdf";

        String CHM = "application/x-chm";
        String TXT = "text/plain";
        String HTML = "text/html";
    }

}
