package com.example.botsort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.botsort.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'botsort' library on application startup.
    static {
        System.loadLibrary("botsort");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'botsort' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}