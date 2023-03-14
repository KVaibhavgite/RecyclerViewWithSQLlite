package com.example.abc;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
public class SqliteDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "Products";
    private static final String TABLE_PRODUCTS = "Productlist";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCT_NAME = "productName";
    private static final String COLUMN_PRODUCT_QTY = "productQty";
    private static final String COLUMN_PRODUCT_PRICE = "productPrice";
    SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_PRODUCTS + "(" + COLUMN_ID
                + " INTEGER PRIMARY KEY,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_PRODUCT_QTY + " INTEGER,"
                + COLUMN_PRODUCT_PRICE + " INTEGER" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }
    ArrayList<Product> listProduct() {
        String sql = "select * from " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Product> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String qty = cursor.getString(2);
                String price = cursor.getString(3);
                storeProducts.add(new Product(id, name, qty, price));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }
    void addProducts(Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProduct_Name());
        values.put(COLUMN_PRODUCT_QTY, product.getProduct_Qty());
        values.put(COLUMN_PRODUCT_PRICE, product.getProduct_Price());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
    }
    void updateProducts(Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getProduct_Name());
        values.put(COLUMN_PRODUCT_QTY, product.getProduct_Qty());
        values.put(COLUMN_PRODUCT_PRICE, product.getProduct_Price());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_PRODUCTS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(product.getId())});
    }
    void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}