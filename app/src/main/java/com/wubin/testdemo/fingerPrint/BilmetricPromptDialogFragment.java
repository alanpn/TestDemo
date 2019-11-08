package com.wubin.testdemo.fingerPrint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.wubin.testdemo.R;

import java.security.Signature;

import javax.crypto.Cipher;

/**
 * @author wubin
 * @description
 * @date 2019-05-09
 */

@TargetApi(28)
public class BilmetricPromptDialogFragment extends DialogFragment {

    private CancellationSignal mCancellationSignal;

    private Cipher mCipher;

    private FingerPrintActivity mActivity;

    private TextView errorMsg;

    /**
     * 标识是否是用户主动取消的认证。
     */
    private boolean isSelfCancelled;


    //=========
    private BiometricPrompt mBiometricPrompt;
    private Signature mSignature;
    private String mToBeSignedMessage;

    public void setSignature(Signature signature) {
        mSignature = signature;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (FingerPrintActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fingerprint_dialog, container, false);
        errorMsg = v.findViewById(R.id.error_msg);
        TextView cancel = v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                stopListening();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 开始指纹认证监听
        startListening(mSignature);
    }

    @Override
    public void onPause() {
        super.onPause();
        // 停止指纹认证监听
        stopListening();
    }

    /**
     * Missing permissions required by FingerprintManager.authenticate: android.permission.USE_BIOMETRIC or android.permission.USE_FINGERPRINT less... (⌘F1)
     */
    private void startListening(Signature mSignature) {

        isSelfCancelled = false;


        mCancellationSignal = new CancellationSignal();

        mBiometricPrompt = new BiometricPrompt.Builder(mActivity)
                .setTitle("指纹验证")
                .setDescription("描述")
                .setNegativeButton("取消", mActivity.getMainExecutor(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("xxx", "Cancel button clicked");
                    }
                })
                .build();

        mBiometricPrompt.authenticate(new BiometricPrompt.CryptoObject(mSignature),
                mCancellationSignal, mActivity.getMainExecutor(), new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, CharSequence errString) {
                        if (!isSelfCancelled) {
                            errorMsg.setText(errString);
                            if (errorCode == FingerprintManager.FINGERPRINT_ERROR_LOCKOUT) {
                                Toast.makeText(mActivity, errString, Toast.LENGTH_SHORT).show();
                                dismiss();
                            }
                        }
                    }

                    @Override
                    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                        errorMsg.setText(helpString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        Toast.makeText(mActivity, "指纹认证成功", Toast.LENGTH_SHORT).show();
                        mActivity.onAuthenticated();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                    }
                });


    }

    private void stopListening() {
        if (mCancellationSignal != null) {
            mCancellationSignal.cancel();
            mCancellationSignal = null;
            isSelfCancelled = true;
        }
    }
}
