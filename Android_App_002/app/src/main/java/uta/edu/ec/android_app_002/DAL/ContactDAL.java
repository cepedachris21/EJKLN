package uta.edu.ec.android_app_002.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import javax.xml.namespace.QName;

import uta.edu.ec.android_app_002.Entities.Contact;
public class ContactDAL {
    private MasterPersistence persistence;// crear la BD
    private SQLiteDatabase sql;// comandos SQL
    private Context context;// para utiliza en el constructor

    public ContactDAL(Context context) {
        this.context = context;
    }

    public void openReadable() {
        // crear la base de datos
        persistence = new MasterPersistence(context, "DB_CONTACTS", null, 1);

        // abrir la base en modo: lectura/escribir
        sql = persistence.getReadableDatabase();
    }

    public void openWritable() {
        // crear la base de datos
        persistence = new MasterPersistence(context, "DB_CONTACTS", null, 1);

        // abrir la base en modo: lectura/escribir
        sql = persistence.getWritableDatabase();
    }

    public void close() {
        sql.close();
    }

    public long insert(Contact contact) {
        long count = 0;

        openWritable();

        try {
            ContentValues values = new ContentValues();
            values.put("Name", contact.getName());
            values.put("LastName", contact.getLastName());
            values.put("Age", contact.getAge());

           count =  sql.insert("Contacts", null, values);
        } catch (Exception e) {
            throw e;
        }
        finally {
            sql.close();
        }

        return count;

//        trate de concatenar no es lo mas pactico pero

    }

    public Contact searchByCode(int code){
        openReadable();

        Contact contact = null;

        try {
            String select = "SELECT Name, LastName, Age " +
                            "FROM Contacts " +
                            "WHERE Code=" + code;

            Cursor cursor = sql.rawQuery(select, null);

            if (cursor.moveToFirst()) {
                contact = new Contact();

                contact.setName(cursor.getString(0));
                contact.setLastName(cursor.getString(1));
//                contact.setAge(cursor.getInt(2));
                contact.setAge(Integer.parseInt(cursor.getString(2)));
            }
        } catch (Exception e) {
            throw e;
        }
        finally {
            sql.close();
        }

        return  contact;
    }

    public ArrayList<Contact> selectAll() {
        openReadable();

        ArrayList<Contact> contactList = null;
        Contact contact = null;

        try {
            String select = "SELECT Code, Name, LastName, Age " +
                            "FROM Contacts";

            Cursor cursor = sql.rawQuery(select, null);

            if (cursor.moveToFirst()) {
                contactList = new ArrayList<Contact>();

                do {
                    contact = new Contact();
                    contact.setCode(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setLastName(cursor.getString(2));
                    contact.setAge(Integer.parseInt(cursor.getString(3)));

                    contactList.add(contact);
                } while (cursor.moveToNext());
            }
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            sql.close();
        }

        return contactList;
    }

    public int update(Contact contact) {
        openWritable();

        int count = 0;

        try {
            ContentValues values = new ContentValues();

            values.put("Name", contact.getName());
            values.put("LastName", contact.getLastName());
            values.put("Age", contact.getAge());
            values.put("code", contact.getCode());

            count =  sql.update("Contacts", values,
                    "Code=" + contact.getCode(), null);
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            sql.close();
        }

        return count;
    }

    public int delete (int code) {
        int count = 0;

        openWritable();

        try {
            count = sql.delete("Contacts", "Code=" + code, null);
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            sql.close();
        }

        return count;
    }
}

//@Override
//
//public String toString () {
//    return code + " " + name + " " + lastName + "  " + age;
//}
