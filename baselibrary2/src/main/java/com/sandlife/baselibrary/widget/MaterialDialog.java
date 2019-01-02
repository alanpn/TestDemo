package com.sandlife.baselibrary.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sandlife.baselibrary.R;
import com.sandlife.baselibrary.util.ColorUtil;

/**
 * Created by drakeet on 9/28/14.
 */
@SuppressLint("NewApi")
public class MaterialDialog {

    private final static int BUTTON_BOTTOM = 9;
    private final static int BUTTON_TOP = 9;

    private boolean mCancel = true;
    private boolean mCancelable = true;
    private Context mContext;
    private AlertDialog mAlertDialog;
    private Builder mBuilder;
    private View mView;
    private int mTitleResId;
    private CharSequence mTitle;
    private int mMessageResId;
    private CharSequence mMessage;
    private TextView mPositiveButton;
    private LinearLayout.LayoutParams mLayoutParams;
    private TextView mNegativeButton;
    private boolean mHasShow = false;
    private Drawable mBackgroundDrawable;
    private int mBackgroundResId;
    private View mMessageContentView;
    private DialogInterface.OnDismissListener mOnDismissListener;

    public MaterialDialog(Context context) {
        this.mContext = context;
    }

    public void show() {

        if (mContext != null && mContext instanceof Activity && !((Activity) mContext).isFinishing()) {

            if (mHasShow == false)
                mBuilder = new Builder();
            else
                mAlertDialog.show();
            mHasShow = true;

        }
    }

    public MaterialDialog setView(View view) {
        mView = view;
        if (mBuilder != null) {
            mBuilder.setView(view);
        }
        return this;
    }

    public MaterialDialog setContentView(View view) {
        mMessageContentView = view;
        if (mBuilder != null) {
            mBuilder.setContentView(mMessageContentView);
        }
        return this;
    }

    public MaterialDialog setBackground(Drawable drawable) {
        mBackgroundDrawable = drawable;
        if (mBuilder != null) {
            mBuilder.setBackground(mBackgroundDrawable);
        }
        return this;
    }

    public MaterialDialog setBackgroundResource(int resId) {
        mBackgroundResId = resId;
        if (mBuilder != null) {
            mBuilder.setBackgroundResource(mBackgroundResId);
        }
        return this;
    }


    public void dismiss() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }

    }

    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @SuppressLint("NewApi")
    private static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= 22;
    }

    public MaterialDialog setTitle(int resId) {
        mTitleResId = resId;
        if (mBuilder != null) {
            mBuilder.setTitle(resId);
        }
        return this;
    }

    public MaterialDialog setTitle(CharSequence title) {
        mTitle = title;
        if (mBuilder != null) {
            mBuilder.setTitle(title);
        }
        return this;
    }

    public MaterialDialog setMessage(int resId) {
        mMessageResId = resId;
        if (mBuilder != null) {
            mBuilder.setMessage(resId);
        }
        return this;
    }

    public MaterialDialog setMessage(CharSequence message) {
        mMessage = message;
        if (mBuilder != null) {
            mBuilder.setMessage(message);
        }
        return this;
    }

    public MaterialDialog setPositiveButton(String text, final View.OnClickListener listener) {
        mPositiveButton = new TextView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        mPositiveButton.setLayoutParams(params);
        mPositiveButton.setBackgroundResource(R.drawable.button);
        mPositiveButton.setTextColor(ColorUtil.getCOLOR_FF5B10(mContext));//Color.argb(255, 35, 159, 242)
        mPositiveButton.setText(text);
        mPositiveButton.setGravity(Gravity.CENTER);
        mPositiveButton.setTextSize(14);
        mPositiveButton.setPadding(20, 25, 20, 25);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(dip2px(2), 0, dip2px(12), dip2px(BUTTON_BOTTOM));
        mPositiveButton.setLayoutParams(layoutParams);
        mPositiveButton.setOnClickListener(listener);
        if (isLollipop()) {
            mPositiveButton.setBackgroundResource(android.R.color.transparent);
        }
        return this;
    }

    public MaterialDialog setNegativeButton(String text, final View.OnClickListener listener) {
        mNegativeButton = new TextView(mContext);
        mLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        mNegativeButton.setLayoutParams(mLayoutParams);
        mNegativeButton.setBackgroundResource(R.drawable.button);
        mNegativeButton.setTextColor(Color.argb(222, 0, 0, 0));
        mNegativeButton.setText(text);
        mNegativeButton.setGravity(Gravity.CENTER);
        mNegativeButton.setTextSize(14);
        mNegativeButton.setPadding(20, 25, 20, 25);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(dip2px(2), 0, dip2px(2), dip2px(BUTTON_BOTTOM));
        mNegativeButton.setLayoutParams(layoutParams);
        mNegativeButton.setOnClickListener(listener);

        if (isLollipop()) {
            mNegativeButton.setBackgroundResource(android.R.color.transparent);
        }

        return this;
    }

    public MaterialDialog setCanceledOnTouchOutside(boolean cancel) {
        this.mCancel = cancel;
        if (mBuilder != null) {
            mBuilder.setCanceledOnTouchOutside(mCancel);
        }
        return this;
    }

    public MaterialDialog setCancelable(boolean cancel) {
        this.mCancelable = cancel;
        if (mBuilder != null) {
            if (!cancel) {
                mBuilder.setCanceledOnTouchOutside(mCancelable);
            }
            mBuilder.setCancelable(mCancelable);
        }
        return this;
    }

    public MaterialDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }


    private class Builder {

        private TextView mTitleView;
        private TextView mMessageView;
        private Window mAlertDialogWindow;
        private LinearLayout mButtonLayout;

        @SuppressLint("NewApi")
        private Builder() {
            mAlertDialog = new AlertDialog.Builder(mContext).create();
            mAlertDialog.show();
            mAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

            mAlertDialogWindow = mAlertDialog.getWindow();
            View contv = LayoutInflater.from(mContext).inflate(R.layout.layout_materialdialog, null);
            contv.setFocusable(true);
            contv.setFocusableInTouchMode(true);

            mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);

            mAlertDialogWindow.setContentView(contv);

            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                    PixelFormat.TRANSLUCENT
            );

            mTitleView = (TextView) mAlertDialogWindow.findViewById(R.id.title);
            mMessageView = (TextView) mAlertDialogWindow.findViewById(R.id.message);
            mButtonLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.buttonLayout);
            if (mView != null) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.contentView);
                linearLayout.removeAllViews();
                linearLayout.addView(mView);
            }
            if (mTitleResId != 0) {
                setTitle(mTitleResId);
            }
            if (mTitle != null) {
                setTitle(mTitle);
            }
            if (mTitle == null && mTitleResId == 0) {
                mTitleView.setVisibility(View.GONE);
            }
            if (mMessageResId != 0) {
                setMessage(mMessageResId);
            }
            if (mMessage != null) {
                setMessage(mMessage);
            }
            if (mPositiveButton != null) {
                mButtonLayout.addView(mPositiveButton);
            }

            if (mNegativeButton != null) {
                if (mButtonLayout.getChildCount() > 0) {
                    mButtonLayout.addView(mNegativeButton, 1);
                } else {
                    mButtonLayout.addView(mNegativeButton);
                }
            }


            if (mBackgroundResId != 0) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.material_background);
                linearLayout.setBackgroundResource(mBackgroundResId);
            }
            if (mBackgroundDrawable != null) {
                LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.material_background);
                linearLayout.setBackground(mBackgroundDrawable);
            }

            if (mMessageContentView != null) {
                this.setContentView(mMessageContentView);
            }
            mAlertDialog.setCanceledOnTouchOutside(mCancel);
            mAlertDialog.setCancelable(mCancelable);
            if (mOnDismissListener != null) {
                mAlertDialog.setOnDismissListener(mOnDismissListener);
            }
        }

        public void setTitle(int resId) {
            mTitleView.setText(resId);
        }

        public void setTitle(CharSequence title) {
            mTitleView.setText(title);
        }

        public void setMessage(int resId) {
            mMessageView.setText(resId);
        }

        public void setMessage(CharSequence message) {
            mMessageView.setText(message);
        }


        public void setView(View view) {
            LinearLayout l = (LinearLayout) mAlertDialogWindow.findViewById(R.id.contentView);
            l.removeAllViews();
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);

            view.setOnFocusChangeListener(
                    new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            mAlertDialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                            // show imm
                            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(
                                    InputMethodManager.SHOW_FORCED,
                                    InputMethodManager.HIDE_IMPLICIT_ONLY
                            );

                        }
                    }
            );

            l.addView(view);

            if (view instanceof ViewGroup) {

                ViewGroup viewGroup = (ViewGroup) view;

                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof EditText) {
                        EditText editText = (EditText) viewGroup.getChildAt(i);
                        editText.setFocusable(true);
                        editText.requestFocus();
                        editText.setFocusableInTouchMode(true);
                    }
                }
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) viewGroup.getChildAt(i);
                        autoCompleteTextView.setFocusable(true);
                        autoCompleteTextView.requestFocus();
                        autoCompleteTextView.setFocusableInTouchMode(true);
                    }
                }
            }
        }

        public void setContentView(View contentView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            contentView.setLayoutParams(layoutParams);
            if (contentView instanceof ListView) {
                setListViewHeightBasedOnChildren((ListView) contentView);
            }
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.message_content_view);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(contentView);
            }
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                if (linearLayout.getChildAt(i) instanceof AutoCompleteTextView) {
                    AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) linearLayout.getChildAt(i);
                    autoCompleteTextView.setFocusable(true);
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.setFocusableInTouchMode(true);
                }
            }
        }

        public void setBackground(Drawable drawable) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.material_background);
            linearLayout.setBackground(drawable);
        }

        public void setBackgroundResource(int resId) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.material_background);
            linearLayout.setBackgroundResource(resId);
        }


        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mAlertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }

        public void setCancelable(boolean cancelable) {
            mAlertDialog.setCancelable(cancelable);
        }

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int count = listAdapter.getCount();

        if (count > 1) {

            int totalHeight = 0;

            for (int i = 0; i < count; i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }


            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }
    }

}
