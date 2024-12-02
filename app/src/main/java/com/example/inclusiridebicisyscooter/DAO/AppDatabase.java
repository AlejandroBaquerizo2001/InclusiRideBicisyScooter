package com.example.inclusiridebicisyscooter.DAO;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.inclusiridebicisyscooter.Models.Cliente;
import com.example.inclusiridebicisyscooter.Models.Pedido;

@Database(entities = {Pedido.class, Cliente.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PedidoDao pedidoDao();
    public abstract ClienteDao clienteDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
