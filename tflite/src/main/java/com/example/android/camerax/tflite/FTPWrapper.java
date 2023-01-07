package com.example.android.camerax.tflite;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;
import android.graphics.Bitmap;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class FTPWrapper {
    private FTPClient client;
    public String home;
    public ArrayList<Pair<String,String>> ftpFiles;

    /**
     * Connect to FTP Server
     */
    public boolean ftpConnect(String host, String username, String password, int port){
        try {
            client = new FTPClient();
            client.connect(host,port);

            if(FTPReply.isPositiveCompletion(client.getReplyCode())){
                boolean status = client.login(username,password);
                client.setFileType(FTP.BINARY_FILE_TYPE);
                client.enterLocalPassiveMode();
                home = client.printWorkingDirectory();
                return status;
            }
            else{
                return false;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Disconnect from FTP Server
     */
    public boolean ftpDisconnect(){
        try{
            client.logout();
            client.disconnect();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Upload to FTP Server
     */
    public boolean ftpUploadBitmap(Bitmap bitmap, String desFileName) {
        Log.i("TEST2", "ftpUpload: ");
        boolean status = false;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] fileBytes = byteArrayOutputStream.toByteArray();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
            status = client.storeFile(desFileName, inputStream);
            inputStream.close();
            byteArrayOutputStream.close();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean changeWorkingDirectory(String directory){
        boolean status = false;
        try {
            status = client.changeWorkingDirectory(directory);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

}
