package ua.hey.hey;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 1/19/16.
 */
public class Contacts {
    public static List <String>  s = new ArrayList<>();
    public static List <String>  phonesArray = new ArrayList<>();
    public static int b=0;
    public void contacts(){

    ContentResolver cr = Config.context.getContentResolver();
    Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null);
    if (cur.getCount() > 0) {
        while (cur.moveToNext()) {
            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            if (Integer.parseInt(cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                Cursor pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{id}, null);

                while (pCur.moveToNext()) {
                    String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    System.out.println("Name: " + name + ", Phone No: " + phoneNo + "\n");
                    System.out.println(b);
                    s.add(b, name);
                    phonesArray.add(b, phoneNo);
                    b++;

                }
                pCur.close();
            }
            }
        }
    }
}
