package com.example.unittesting.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.unittesting.databinding.ActivityMainBinding;
import com.example.unittesting.domain.Math;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Math math;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        math = new Math();
        binding.btnAdd.setOnClickListener(v -> addingFunc());
        binding.btnSubtract.setOnClickListener(v -> subtract());
        binding.btnMultiple.setOnClickListener(v -> multiple());
        binding.btnDivide.setOnClickListener(v -> divide());


    }

    public void subtract() {
        int first_num = Integer.parseInt(getFirstInput());
        int second_num = Integer.parseInt(getSecondInput());
        binding.txtSumView.setText(String.valueOf(math.subtractFunction(first_num, second_num)));
    }

    public void addingFunc() {
        int first_num = Integer.parseInt(getFirstInput());
        int second_num = Integer.parseInt(getSecondInput());
        binding.txtSumView.setText(String.valueOf(math.addFunction(first_num, second_num)));
    }

    public void multiple() {
        int first_num = Integer.parseInt(getFirstInput());
        int second_num = Integer.parseInt(getSecondInput());
        binding.txtSumView.setText(String.valueOf(math.multiplication(first_num, second_num)));

    }

    public void divide() {
        int first_num = Integer.parseInt(getFirstInput());
        int second_num = Integer.parseInt(getSecondInput());
        binding.txtSumView.setText(String.valueOf(math.divideFunction(first_num, second_num)));
    }


    public String getFirstInput() {
        return binding.firstNum.getText().toString();
    }

    public String getSecondInput() {
        return binding.secondNum.getText().toString();
    }

}