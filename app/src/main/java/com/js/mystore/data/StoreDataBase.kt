package com.js.mystore.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.js.mystore.model.Company
import com.js.mystore.model.Product
import com.js.mystore.model.Buy
import com.js.mystore.model.Sell
import com.js.mystore.data.dao.StoreDao
import com.js.mystore.model.ProductBuy
import com.js.mystore.model.ProductSell
import com.js.mystore.utils.DateUtils

@Database(
    entities = [
        Company::class,
        Product::class,
        Buy::class,
        Sell::class,
        ProductBuy::class,
        ProductSell::class
    ],
    version = 6
)
@TypeConverters(DateUtils::class)
abstract class StoreDataBase : RoomDatabase() {

    abstract val storeDao: StoreDao

    companion object {
        @Volatile
        private var INSTANCE: StoreDataBase? = null

        fun getInstance(context: Context): StoreDataBase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StoreDataBase::class.java,
                    "store_db"
                ).fallbackToDestructiveMigration().build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
