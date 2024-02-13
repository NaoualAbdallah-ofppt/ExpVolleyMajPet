package aa.bb.expvolleymajpet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
Button btnCreer;
EditText editId, editStatus,editName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editId=findViewById(R.id.editId);
        editName=findViewById(R.id.editName);
        editStatus=findViewById(R.id.editStatus);
        btnCreer=findViewById(R.id.btnCreer);
        btnCreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
JSONObject newJo = new JSONObject();
                try {
                    newJo.put("id",Integer.parseInt(editId.getText().toString()));
                    newJo.put("name",editName.getText().toString());
                    newJo.put("status",editStatus.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



                String url="https://petstore3.swagger.io/api/v3/pet";
                JsonObjectRequest maRequette = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        newJo,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Log.i("mytag", "ok");


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("mytag", error.getMessage());

                            }
                        }

                );
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(maRequette);
            }
        });


    }
}