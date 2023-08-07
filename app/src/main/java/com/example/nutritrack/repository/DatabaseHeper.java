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
    private static final String SEX = "sex";
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
                SEX + " VARCHAR, " +
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
        cv.put(SEX, userModel.getSex());
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
                String sex = cursor.getString(5);
                String dob = cursor.getString(6);
                String email = cursor.getString(7);
                Double height = Double.parseDouble(cursor.getString(8));
                Double weight = Double.parseDouble(cursor.getString(9));
                String activity_level = cursor.getString(10);
                UserModel newUser = new UserModel(fname, lname, userName, password,sex, dob, email,height,weight,activity_level);
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






    public List<nutritionModel> getAllNutritionData(){

        List<nutritionModel> returnList = new ArrayList<>();

        //get data from the database
        String query = "SELECT * FROM " + NUTRITION_TABLE_NAME  ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(query,null);

        if(cursor.moveToFirst()){

            //loop through the cursor (result set) and create new user objects. Put then into the return list.
            do{
                //int userId = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodCalories = cursor.getString(2);
                String foodTotalFat = cursor.getString(3);
                String foodSaturatedFat = cursor.getString(4);
                String foodCholesterol = cursor.getString(5);
                String foodSodium = cursor.getString(6);
                String foodtotalcarbs = cursor.getString(7);
                String fooddietaryfiber = cursor.getString(8);
                String foodtotalsugar = cursor.getString(9);
                String foodprotein = cursor.getString(10);
                String foodcalcium = cursor.getString(11);
                String foodiron = cursor.getString(12);
                String foodpotassium = cursor.getString(13);




                nutritionModel newNutrition = new nutritionModel(foodName,
                        Double.parseDouble(foodCalories),
                        Double.parseDouble(foodTotalFat),
                        Double.parseDouble(foodSaturatedFat),
                        Double.parseDouble(foodCholesterol),
                        Double.parseDouble(foodSodium),
                        Double.parseDouble(foodtotalcarbs),
                        Double.parseDouble(fooddietaryfiber),
                        Double.parseDouble(foodtotalsugar),
                        Double.parseDouble(foodprotein),
                        Double.parseDouble(foodcalcium),
                        Double.parseDouble(foodiron),
                        Double.parseDouble(foodpotassium)
                        );
                returnList.add(newNutrition);

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


    public void  updateProfileData(String fname, String lname, String userName, String password,String sex, String dob, String email, Double height,Double weight,String activity_level ){


        //calling a method to get writable database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRSTNAME, fname);
        cv.put(LASTNAME, lname);
        cv.put(USERNAME, userName);
        cv.put(PASSWORD, password);
        cv.put(SEX, sex);
        cv.put(DOB, dob);
        cv.put(EMAIL, email);
        cv.put(HEIGHT, height);
        cv.put(WEIGHT, weight);
        cv.put(ACTIVITY_LEVEL, activity_level);



        long result = db.update(USER_TABLE_NAME,cv,"username=?", new String[]{userName});
        db.close();
//        if(result==-1){
//            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
//
//        }else{
//            Toast.makeText(context, "Updated succesfully", Toast.LENGTH_SHORT).show();
//        }
    }
}
