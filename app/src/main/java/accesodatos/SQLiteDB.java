package accesodatos;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SQLiteDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ventas.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "clientes";
    public static final String KEY_ID = "idcliente";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_TELEFONO = "telefono";
    public static final String KEY_DIRECCION = "direccion";
    public static final String KEY_CIUDAD = "ciudad";
    public static final String KEY_ESTADO = "estado";


    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NOMBRE + " text, " +
                KEY_DIRECCION + " text, " +
                KEY_TELEFONO + " text, " +
                KEY_CIUDAD + " text, " +
                KEY_ESTADO + " text)");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NOMBRE + " text, " +
                KEY_DIRECCION + " text, " +
                KEY_TELEFONO + " text, " +
                KEY_CIUDAD + " text, " +
                KEY_ESTADO + " text)");
    }
}
