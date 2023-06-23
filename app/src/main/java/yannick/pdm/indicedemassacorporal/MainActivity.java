package yannick.pdm.indicedemassacorporal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import yannick.pdm.indicedemassacorporal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        binding.btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double peso = Double.parseDouble(binding.etPeso.getText()+"");
                    double altura = Double.parseDouble(binding.etAltura.getText()+"");

                    double imc = peso/(altura*altura);

                    String classificacao = "";
                    if(imc<18.5){
                        classificacao = "Baixo Peso";
                    } else if (imc>18.5 && imc<=24.9) {
                        classificacao = "Normal";
                    } else if (imc>25) {
                        classificacao = "Sobrepeso";
                        if(imc<=30){
                            classificacao = classificacao+": Pre-Obeso";
                        }else if(imc>30 && imc <=35){
                            classificacao = classificacao+": Obeso I";
                        } else if (imc>35 && imc<=40) {
                            classificacao = classificacao+": Obeso II";
                        } else if(imc>40){
                            classificacao = classificacao+": Obeso III";
                        }
                    }
                    Alerta.alertar(MainActivity.this, "Resultado",
                            "Peso: "+peso+" Kg\n"+
                            "Altura: "+altura+" metros\n"+
                            "IMC: "+imc+"\n"+
                            "Classificacao: "+classificacao);
                }catch (NullPointerException | NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Insira corretamente todos os campos", Toast.LENGTH_SHORT).show();
                    Log.i("TRY", "onClick: "+e.getMessage()+"| "+e.getCause()+"| "+e.getLocalizedMessage());
                }
                
            }
        });
    }
}