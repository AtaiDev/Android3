package com.example.unittesting.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import com.example.unittesting.databinding.ActivityMainBinding;
import com.example.unittesting.domain.Math;

public class MainActivity extends AppCompatActivity {

    private static final String UNIT = "oneone";
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

    private void subtract() {
        validateSubtracting(getFirstInput(),getSecondInput());
    }


    public void addingFunc() {
        validateAddition(getFirstInput(),getSecondInput());
    }

    public void multiple() {
        validateMultiple(getFirstInput(),getSecondInput());
    }


    public void divide() {
        validateDivide(getFirstInput(),getSecondInput());
    }

    public String getFirstInput() {
        return binding.firstNum.getText().toString();
    }

    public String getSecondInput() {
        return binding.secondNum.getText().toString();
    }



    public void validateDivide(String first,String second){

        Integer first_num;
        Integer second_num;
        if (!first.isEmpty() && !second.isEmpty()) {
            if (TextUtils.isDigitsOnly(first) && TextUtils.isDigitsOnly(second)) {
                first_num = Integer.parseInt(first);
                second_num = Integer.parseInt(second);
                if (first_num > 0 && second_num > 0)
                    binding.txtSumView.setText(String.valueOf(math.divideFunction(first_num, second_num)));
                else binding.txtSumView.setText("you can't divide to 0");
            } else {
                binding.txtSumView.setText("please provide valid input");
            }
        } else {
            binding.txtSumView.setText("field can't be empty");
        }

    }
    public void validateMultiple(String first,String second){
        Integer first_num;
        Integer second_num;
        if (!first.isEmpty() && !second.isEmpty()) {
            if (TextUtils.isDigitsOnly(first) && TextUtils.isDigitsOnly(second)) {
                first_num = Integer.parseInt(first);
                second_num = Integer.parseInt(second);
                if (first_num > 0 && second_num > 0)
                    binding.txtSumView.setText(String.valueOf(math.multiplication(first_num, second_num)));
                else binding.txtSumView.setText("you can't divide to 0");
            } else {
                binding.txtSumView.setText("please provide valid input");
            }
        } else {
            binding.txtSumView.setText("field can't be empty");
        }

    }
    public void validateAddition(String first,String second){
        Integer first_num;
        Integer second_num;
        if (!first.isEmpty() && !second.isEmpty()) {
            if (TextUtils.isDigitsOnly(first) && TextUtils.isDigitsOnly(second)) {
                first_num = Integer.parseInt(first);
                second_num = Integer.parseInt(second);
                if (first_num > 0 && second_num > 0)
                    binding.txtSumView.setText(String.valueOf(math.addFunction(first_num, second_num)));
                else binding.txtSumView.setText("you can't divide to 0");
            } else {
                binding.txtSumView.setText("please provide valid input");
            }
        } else {
            binding.txtSumView.setText("field can't be empty");
        }

    }
    public void validateSubtracting(String first, String second){
        Integer first_num;
        Integer second_num;
        if (!first.isEmpty() && !second.isEmpty()) {
            if (TextUtils.isDigitsOnly(first) && TextUtils.isDigitsOnly(second)) {
                first_num = Integer.parseInt(first);
                second_num = Integer.parseInt(second);
                if (first_num > 0 && second_num > 0)
                    binding.txtSumView.setText(String.valueOf(math.subtractFunction(first_num, second_num)));
                else binding.txtSumView.setText("you can't divide to 0");
            } else {
                binding.txtSumView.setText("please provide valid input");
            }
        } else {
            binding.txtSumView.setText("field can't be empty");
        }
    }
}