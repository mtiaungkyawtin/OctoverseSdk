package com.octoverse.payment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

public class OctoversePay {
    public static final String EXTRA_MERCH_CODE = "EXTRA_MERCH_CODE";
    public static final String EXTRA_APPID = "EXTRA_APPID";
    public static final String EXTRA_ORDER_ID = "EXTRA_ORDER_ID";
    public static final String EXTRA_RESULT = "EXTRA_RESULT";
    public static final String EXTRA_FAIL_MSG = "EXTRA_FAIL_MSG";
    public static final int COMPLETED = 1;
    public static final int FAIL = 2;
    public static final int CANCEL = 3;

    public static final String EXTRA_REDIRECT_URL = "EXTRA_REDIRECT_URL";
    private static final String CP = "CBPAY_PIN";
    private static final String KA = "KBZPAY_APP";
    /**
     * <pre>
     * Activity var0
     * OrderInfo var1
     * SignValue var2
     * SignType var3
     * Url or Deeplink var4
     * PaymentCode var5
     * </pre>
     */
    public static void invokePayment(@NonNull Activity var0, String var1, String var2, String var3, String var4, @NonNull String var5) {
        switch (var5) {
            case KA: {
                //com.kbzbank.payment.KBZPay.startPay(var0, var1, var2, var3);
                break;
            }
            case CP: {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(var4));
                var0.startActivity(intent);
                break;
            }
            default:{
                Intent intent = new Intent().setClassName(var0.getPackageName(), "com.kbzbank.payment.sdk.webview.WebviewResultActivity");
                intent.putExtra(EXTRA_REDIRECT_URL, var4);
                var0.startActivity(intent);
                break;
            }
        }
    }

    /**
     * <pre>
     *  Activity var0
     *  String url is deep link url or redirect url
     *  String pCode is payment code
     * </pre>
     **/
    public static void invokePayment(@NonNull Activity var0, String url, @NonNull String pCode) {
        Intent intent = new Intent();
        if(pCode.equals(CP)){
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
        }else {
            intent.setClassName(var0.getPackageName(), "com.kbzbank.payment.sdk.webview.WebviewResultActivity");
            intent.putExtra(EXTRA_REDIRECT_URL, url);
        }
        var0.startActivity(intent);
    }
}
