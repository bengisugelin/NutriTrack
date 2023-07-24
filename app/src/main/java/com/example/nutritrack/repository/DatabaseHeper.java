package com.example.nutritrack.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.nutritrack.models.UserModel;
import com.example.nutritrack.models.nutritionModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHeper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "NutriTrack.db";
    private static final int DATABASE_VERSION = 1;


    //user table begins
    private static final String USER_TABLE_NAME = "nutritrack_user";

    private static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DOB = "day_of_birth";
    private static final String EMAIL = "email";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";
    private static final String ACTIVITY_LEVEL = "activity_level";
    //user table ends


    //nutrition table begins

    private static final String NUTRITION_TABLE_NAME = "nutritrack_nutrition";

    private static final String NUTRITION_ID = "id";
    private static final String FOOD_NAME = "food_name";
    private static final String FOOD_CALORIES = "food_calories";
    private static final String FOOD_TOTAL_FAT = "food_total_fat";
    private static final String FOOD_SATURATED_FAT = "food_saturated_fat";
    private static final String FOOD_CHOLESTEROL = "food_cholesterol";
    private static final String FOOD_SODIUM = "food_sodium";
    private static final String FOOD_TOTAL_CARBS = "food_total_carbonhydrate";
    private static final String FOOD_DIETARY_FIBER = "food_dietary_fiber";
    private static final String FOOD_TOTAL_SUGAR = "food_total_sugars";
    private static final String FOOD_PROTEIN = "food_protein";
    private static final String FOOD_CALCIUM = "food_calcium";
    private static final String FOOD_IRON = "food_iron";
    private static final String FOOD_POTASSIUM = "food_potassium";

    //nutrition tabe ends





    public DatabaseHeper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIRSTNAME + " VARCHAR, " +
                LASTNAME + " VARCHAR, " +
                USERNAME + " VARCHAR, " +
                PASSWORD + " VARCHAR, " +
                DOB + " VARCHAR, " +
                EMAIL + " VARCHAR, " +
                HEIGHT + " VARCHAR, " +
                WEIGHT + " VARCHAR, " +
                ACTIVITY_LEVEL + " VARCHAR);" ;
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        String CREATE_NUTRITION_TABLE = "CREATE TABLE IF NOT EXISTS " + NUTRITION_TABLE_NAME +
                " (" + NUTRITION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FOOD_NAME + " VARCHAR, " +
                FOOD_CALORIES + " VARCHAR, " +
                FOOD_TOTAL_FAT + " VARCHAR, " +
                FOOD_SATURATED_FAT + " VARCHAR, " +
                FOOD_CHOLESTEROL + " VARCHAR, " +
                FOOD_SODIUM + " VARCHAR, " +
                FOOD_TOTAL_CARBS + " VARCHAR, " +
                FOOD_DIETARY_FIBER + " VARCHAR, " +
                FOOD_TOTAL_SUGAR + " VARCHAR, " +
                FOOD_PROTEIN + " VARCHAR, " +
                FOOD_CALCIUM + " VARCHAR, " +
                FOOD_IRON + " VARCHAR, " +
                FOOD_POTASSIUM + " VARCHAR);" ;
        sqLiteDatabase.execSQL(CREATE_NUTRITION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NUTRITION_TABLE_NAME);

    }

    public void addUser(UserModel userModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRSTNAME, userModel.getFname());
        cv.put(LASTNAME, userModel.getLname());
        cv.put(USERNAME, userModel.getUsername());
        cv.put(PASSWORD, userModel.getPassword());
        cv.put(DOB, userModel.getDob());
        cv.put(EMAIL, userModel.getEmail());
        cv.put(HEIGHT, userModel.getHeight());
        cv.put(WEIGHT, userModel.getWeight());
        cv.put(ACTIVITY_LEVEL, userModel.getActivityLevel());


        long result = db.insert(USER_TABLE_NAME,null, cv);

//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//            return false;
//        }else{
//            Toast.makeText(context, "User added succesfully", Toast.LENGTH_SHORT).show();
//            return  true;
//        }
    }//end of adduser




    public void addNutrition(nutritionModel nutritionModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FOOD_NAME, nutritionModel.getFood_name());
        cv.put(FOOD_CALORIES, nutritionModel.getCalories());
        cv.put(FOOD_TOTAL_FAT, nutritionModel.getTotal_fat());
        cv.put(FOOD_SATURATED_FAT, nutritionModel.getSaturated_fat());
        cv.put(FOOD_CHOLESTEROL, nutritionModel.getCholesterol());
        cv.put(FOOD_SODIUM, nutritionModel.getSodium());
        cv.put(FOOD_TOTAL_CARBS, nutritionModel.getTotal_carbonhydrate());
        cv.put(FOOD_DIETARY_FIBER, nutritionModel.getDietary_fiber());
        cv.put(FOOD_TOTAL_SUGAR, nutritionModel.getTotal_sugars());
        cv.put(FOOD_PROTEIN, nutritionModel.getProtein());
        cv.put(FOOD_CALCIUM, nutritionModel.getCalcium());
        cv.put(FOOD_IRON, nutritionModel.getIron());
        cv.put(FOOD_POTASSIUM, nutritionModel.getPotassium());


        long result = db.insert(NUTRITION_TABLE_NAME,null, cv);

//        if(result == -1){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
//            return false;
//        }else{
//            Toast.makeText(context, "Enjoy!", Toast.LENGTH_SHORT).show();
//            return  true;
//        }
    }//end of adduser


    public List<UserModel> getAllData(String username){

        List<UserModel> returnList = new ArrayList<>();

        //get data from the database
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE username = ?" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(query,new String[] {username});

        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new user objects. Put then into the return list.
            do{
                //int userId = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String userName = cursor.getString(3);
                String password = cursor.getString(4);
                String dob = cursor.getString(5);
                String email = cursor.getString(6);
                Double height = Double.parseDouble(cursor.getString(7));
                Double weight = Double.parseDouble(cursor.getString(8));
                String activity_level = cursor.getString(9);
                UserModel newUser = new UserModel(fname, lname, userName, password, dob, email,height,weight,activity_level);
                returnList.add(newUser);

            }while(cursor.moveToNext());
        }else{

            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            //failure. do not add anything to the list.
        }
        //close both the cursor and db when done
        cursor.close();
        db.close();

        return returnList;

    }//end of checkUserLogin
}
