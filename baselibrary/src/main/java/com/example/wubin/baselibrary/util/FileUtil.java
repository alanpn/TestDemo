package com.example.wubin.baselibrary.util;

import com.example.wubin.baselibrary.activity.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    public void chomdAll(String path) {
        chomd("777", path);
    }

    /**
     * 修改文件权限
     *
     * @param permission 权限(如777 可读可写可执行)
     * @param path       文件路径
     */
    public void chomd(String permission, String path) {

        try {

            String command = StringUtil.getString("chomd ", permission, " ", path);
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

    public String readAssetsFile(String fileName) {

        InputStream is = null;

        try {

            checkAssetsFileExists(fileName);

            is = BaseActivity.myActivity.getAssets().open(fileName);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i;
            while ((i = is.read()) != -1) {
                baos.write(i);
            }

            return baos.toString();

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                ShowUtil.showErrorMessage(e);
            }
        }

        return "";
    }

    public void writeFile(String fileContent, String path) {

        FileOutputStream fos = null;

        try {

            File file = new File(path);
            file.createNewFile();
            fos = new FileOutputStream(file);
            fos.write(fileContent.getBytes());

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isAssetsFileExists(String fileName) {

        String filePath = StringUtil.getString(AssetsPath, fileName);
        File file = new File(filePath);
        return file.exists();

    }

    public void checkAssetsFileExists(String fileName) throws Exception {

        String filePath = StringUtil.getString(AssetsPath, fileName);
        File file = new File(filePath);
        if (!file.exists()) throw new MyException(className, "文件不存在");

    }


    //===========================

    public static final String AssetsPath = "file:///android_assets/";

    private final String className = FileUtil.class.getName();

    private static FileUtil util;

    public static FileUtil getInstance() {
        if (null == util) util = new FileUtil();
        return util;
    }
}
