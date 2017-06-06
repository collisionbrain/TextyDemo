package mx.libre.com.textydemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public final int OCR_REQUEST=100;
    public ImageView documento;
    public EditText edtNombre;
    public EditText edtApellidos;
    public EditText edtDireccion;
    public EditText edtClave;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        documento=(ImageView)findViewById(R.id.imgIdentificacion);
        edtNombre=(EditText) findViewById(R.id.edtNombre);
        edtApellidos=(EditText)findViewById(R.id.edtApellidos);
        edtDireccion=(EditText)findViewById(R.id.edtDireccion);
        edtClave=(EditText)findViewById(R.id.edtClaveElector);
        documento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClassName("com.libre", "com.libre.ocrsmart");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, OCR_REQUEST);


            }
        });
    }


    @SuppressWarnings("static-access")
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){

            case OCR_REQUEST:
                if(resultCode== RESULT_OK){
                    if(data!=null){
                        String url_imagen = data.getStringExtra("URL_IMAGEN");
                        String nombre = data.getStringExtra("nombre");
                        String apellidos = data.getStringExtra("apellidos");
                        String direccion = data.getStringExtra("direccion");
                        String clave = data.getStringExtra("clave");
                        Bitmap foto= BitmapFactory.decodeFile(url_imagen);

                        documento.setImageBitmap(foto);
                        edtNombre.setText(nombre);
                        edtApellidos.setText(apellidos);
                        edtDireccion.setText(direccion);
                        edtClave.setText(clave);

                    }
                }

        }



    }

    //

}
