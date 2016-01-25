package ua.hey.hey;

import android.content.Context;
import android.telephony.TelephonyManager;

public class PhoneNumber {

    public String getPhoneNumber(Context c) {
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager)
                c.getSystemService(Context.TELEPHONY_SERVICE);
        return mTelephonyMgr.getLine1Number();
    }
}
