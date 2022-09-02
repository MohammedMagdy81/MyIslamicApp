package com.example.islamy.model.azkar;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.islamy.model.azkar.Zekr;
import com.example.islamy.model.azkar.ZekrType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AzkarProvider {

    public static ArrayList<Zekr> getAllAzkar(Context context){

        // to read azkar from json file ...
        try {
            InputStream azkarFile = context.getAssets().open("azkar.json");
            int size = azkarFile.available();
            byte[] bytes = new byte[size];
            azkarFile.read(bytes);
            azkarFile.close();
            String azkarString = new String(bytes, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            ArrayList<Zekr> azkar = gson.fromJson(azkarString, new TypeToken<List<Zekr>>() {
            }.getType());
            return azkar;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

     @RequiresApi(api = Build.VERSION_CODES.N)
     public static ArrayList<Zekr> getAllAzkarByType(Context context , @NonNull String zekrType){
         return  Objects.requireNonNull(getAllAzkar(context))
                 .stream()
                 .filter(zekr -> zekrType.equals(zekr.getCategory()))
                 .collect(Collectors.toCollection(ArrayList::new));
     }

     @RequiresApi(api = Build.VERSION_CODES.N)
     public static HashSet<ZekrType> getAzkarTypes(Context context ){
        return getAllAzkar(context)
                .stream()
                .map(zekr->new ZekrType(zekr.getCategory()))
                .collect(Collectors.toCollection(HashSet::new));
     }
}
