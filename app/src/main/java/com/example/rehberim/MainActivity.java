package com.example.rehberim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_READ_CONTACTS = 1;
    int resultValue;
    ListView rehber_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int a = 5;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rehber_list=findViewById(R.id.rehber_list);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            ArrayList<Kisiler> kisiler = new ArrayList<Kisiler>();

            Cursor telefonun_rehberi = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,  null, null);
            while (telefonun_rehberi.moveToNext())
            {
                String isim = telefonun_rehberi.getString(telefonun_rehberi.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                String numara = telefonun_rehberi.getString(telefonun_rehberi.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                String contactID=telefonun_rehberi.getString(telefonun_rehberi.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));



                Kisiler r_nesnesi = new Kisiler();

                r_nesnesi.set_isim(isim);
                r_nesnesi.set_numara(numara);
                r_nesnesi.setResim(ContactPhoto(contactID));
                kisiler.add(r_nesnesi);
            }

            telefonun_rehberi.close();
            KisilerAdapter kisilerAdapter = new KisilerAdapter(this, kisiler);
            if (rehber_list != null) {
                rehber_list.setAdapter(kisilerAdapter);
            }
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS);
        }



    }
    public Bitmap ContactPhoto(String contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(contactId));
        Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor cursor = getContentResolver().query(photoUri,
                new String[]{ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            byte[] data = cursor.getBlob(0);
            if (data != null)
                return BitmapFactory.decodeStream(new ByteArrayInputStream(data));
            else
                return null;
        }
        cursor.close();
        return null;
    }
}
