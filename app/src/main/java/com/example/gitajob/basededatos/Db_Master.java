package com.example.gitajob.basededatos;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.gitajob.activity.LoginActivity;

public class Db_Master extends SQLiteOpenHelper {
    private static final String DB_NAME = "GitAJob";

    //Table name
    private static final String DB_TABLE_NAME = "USERS";

    //Database version must be >= 1
    private static final int DB_VERSION = 1;

    //Columns
    private static final String USER_NAME_COLUMN = "CUSER";

    private static final String USER_PASSWORD_COLUMN = "CPASS";


    private Context miContexto;

    public Db_Master(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        miContexto = context;

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + DB_TABLE_NAME + "("
                + USER_NAME_COLUMN + " TEXT ," + USER_PASSWORD_COLUMN + " TEXT " + ")";

        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        Log.d("base de datos","tablas creadas");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
        //para obtener las version de la base de datos
    public int getVersionDB() {
        int version = this.getReadableDatabase().getVersion();
        return version;

    }

    public long insertUsuarios(String name, String pass) {

        SQLiteDatabase db = this.getWritableDatabase();
        long result = -1;

        // Contenedor clave,valor -> columna, valor de entrada registro
        ContentValues values = new ContentValues();

        values.put(USER_NAME_COLUMN, name);
        values.put(USER_PASSWORD_COLUMN, pass);
        Log.d("base de datos","usuario creado");
        Toast.makeText(miContexto, "USUARIO CREADO", Toast.LENGTH_LONG + 2).show();
         result = db.insert(DB_TABLE_NAME, null, values);

        //cerramos las conexion
        db.close();

        return result;

    }


    public Boolean verifica(String user, String password) {
        //comprueba primero si existe un registro con usuario.
        SQLiteDatabase db = this.getWritableDatabase();            //importante dejar espacio entre las comillas que si no da error
        Cursor cursorpass;
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE " + USER_NAME_COLUMN + "= ?", new String[]{user});
        if (cursor.getCount() >= 1) {
            cursorpass = db.rawQuery("SELECT '" + user + "' FROM " + DB_TABLE_NAME + " WHERE " + USER_PASSWORD_COLUMN + " = ? ", new String[]{password});
            if (cursorpass.getCount() >=1) {
                Log.d("base de datos","el usuario existe, entra, o se ha pulsado el boton de registrar por tanto no hace nada");
                return true;
            } else {
                Log.d("base de datos" , "usuario existe, pero la contesañe es incorrecta");
                return false;
            }
        } else {

            Log.d("base de datos","el usuario no existe");
            return  false;
        }
    }

}