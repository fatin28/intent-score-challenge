package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText homeText, awayText;
    private ImageView homeImage, awayImage;
    Uri imageUri1, imageUri2;
    Bitmap bitmap1, bitmap2;

    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeText = findViewById(R.id.home_team);
        awayText = findViewById(R.id.away_team);
        homeImage =findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

        //TODO

    }

    //3. Ganti Logo Home Team
    public void handleHomeLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    //4. Ganti Logo Away Team
    public void handleAwayLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_CANCELED){
            return;
        }

        if(requestCode == 1){
            if(data != null){
                try {
                    imageUri1 = data.getData();
                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri1);
                    homeImage.setImageBitmap(bitmap1);
                }catch (IOException e){
                    Toast.makeText(this,"Can't Load Image",Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }else if(requestCode == 2){
            if(data != null){
                try {
                    imageUri2 = data.getData();
                    bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri2);
                    awayImage.setImageBitmap(bitmap2);
                }catch (IOException e){
                    Toast.makeText(this,"Can't Load Image",Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    //5. Next Button Pindah Ke MatchActivity
    public void handleNext(View view) {

        //Untuk mengambil data berupa string
        String inputHome = homeText.getText().toString();
        String inputAway = awayText.getText().toString();

        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        if (!inputHome.equals("") && !inputAway.equals("") ) {
            if (bitmap1 != null && bitmap2 != null) {
                Intent intent = new Intent(this, MatchActivity.class);
                intent.putExtra("inputHome", inputHome);
                intent.putExtra("inputAway", inputAway);
                intent.putExtra("logoHome", imageUri1.toString());
                intent.putExtra("logoAway", imageUri2.toString());

                startActivity(intent);
            } else {
                Toast.makeText(this, "Anda belum memasukkan logo", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Anda belum memasukkan data", Toast.LENGTH_SHORT).show();
        }

    }

}