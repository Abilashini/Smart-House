package com.example.abilashini.smarthouse;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.abilashini.smarthouse.domain.Door;
import com.example.abilashini.smarthouse.domain.MotionSensor;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abilashini on 1/6/2016.
 */
public class RoomState extends Activity {

    private List<Door> doorList;
    private List<MotionSensor> motionSensorList;
    private Activity activity = this;
    private boolean isGot =false;
    private boolean canRun =true;
    private ImageView location1;
    private ImageView location2;
    private ImageView location3;
    private ImageView location4;
    private ImageView door;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomstate);
        doorList = new ArrayList<>();
        motionSensorList = new ArrayList<>();
        location1 = (ImageView) findViewById(R.id.L1);
        location2 = (ImageView) findViewById(R.id.L2);
        location3 = (ImageView) findViewById(R.id.L3);
        location4 = (ImageView) findViewById(R.id.L4);
        door = (ImageView) findViewById(R.id.door);


        (new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                while (!Thread.interrupted())
                    try
                    {
                        Thread.sleep(1000);
                        if(canRun) {
                            new getDoor().execute();
                            canRun = false;
                        }
                        if(isGot){
                            runOnUiThread(new Runnable() // start actions in UI thread
                            {

                                @Override
                                public void run()
                                {
                                    changeRoomColor(motionSensorList.get(motionSensorList.size()-1).getMotionSensorId(),motionSensorList.get(motionSensorList.size()-1).getMotionSensorValue());
                                    changeDoorColor(doorList.get(doorList.size() - 1).getDoorSensorId(), doorList.get(doorList.size() - 1).getDoorSensorValue());
                                    doorList = new ArrayList<Door>();
                                    motionSensorList = new ArrayList<MotionSensor>();
                                    isGot = false;
                                    canRun = true;
                                }
                            });
                        }
                    }
                    catch (InterruptedException e)
                    {
                        // ooops
                    }
            }
        })).start();


    }




    public void changeRoomColor(int R_id, int value) {
        if (R_id == 0) {
            if (value == 1) {
                location1.setImageResource(R.drawable.blue);
            } else {
                location1.setImageResource(R.drawable.yellow);
            }
        }
        if (R_id == 1) {
            if (value == 1) {
                location2.setImageResource(R.drawable.blue);
            } else {
                location2.setImageResource(R.drawable.yellow);
            }
        }
        if (R_id == 2) {
            if (value == 1) {
                location3.setImageResource(R.drawable.blue);
            } else {
                location3.setImageResource(R.drawable.yellow);
            }
        }
        if (R_id == 3) {
            if (value == 1) {
                location4.setImageResource(R.drawable.blue);
            } else {
                location4.setImageResource(R.drawable.yellow);
            }
        }
    }

    public void changeDoorColor(int D_id, int value) {
        if (D_id == 0) {
            if (value == 0) {
                door.setImageResource(R.drawable.red);
            } else {
                door.setImageResource(R.drawable.green);
            }
        }
    }
    private class getDoor extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://smarthouseautomationsystem.netau.net/getDoorState.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setUseCaches(false);//TO DO checkout this
                urlConnection.setConnectTimeout(120000);
                urlConnection.setReadTimeout(120000);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                int HttpResult = urlConnection.getResponseCode();

                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(), "utf-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                        Log.d("**********succss", "asdb");
                    }
//                    [{"doorSensorID":"0","doorSensorValue":"0"}]
                    JSONArray doorDataArray = new JSONArray(stringBuilder.toString());
                    JSONObject doorJsonObject;
                    for (int i = 0; i < doorDataArray.length(); i++) {
                        doorJsonObject = doorDataArray.getJSONObject(i);
                        Door door = new Gson().fromJson(doorJsonObject.toString(), Door.class);
                        doorList.add(door);
                    }
                    Log.d("dddddd", doorList.size() + "");
                    Log.d("ggggg", doorList.get(doorList.size() - 1) + "");
                } else {
                    Toast.makeText(activity, "Check your internet connection!", Toast.LENGTH_LONG).show();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            new getSensor().execute();
        }
    }

    private class getSensor extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://smarthouseautomationsystem.netau.net/getRoomState.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setUseCaches(false);//TO DO checkout this
                urlConnection.setConnectTimeout(120000);
                urlConnection.setReadTimeout(120000);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                int HttpResult = urlConnection.getResponseCode();

                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(), "utf-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                        Log.d("**********succss", "asdb");
                    }
//                    [{"doorSensorID":"0","doorSensorValue":"0"}]
                    JSONArray motionSensorDataArray = new JSONArray(stringBuilder.toString());
                    JSONObject motionSensorJsonObject;
                    for (int i = 0; i < motionSensorDataArray.length(); i++) {
                        motionSensorJsonObject = motionSensorDataArray.getJSONObject(i);
                        MotionSensor motionSensor = new Gson().fromJson(motionSensorJsonObject.toString(), MotionSensor.class);
                        motionSensorList.add(motionSensor);
                    }
                    Log.d("dddddd", motionSensorList.size() + "");
                    Log.d("ggggg", motionSensorList.get(motionSensorList.size() - 1) + "");
                } else {
                    Toast.makeText(activity, "Check your internet connection!", Toast.LENGTH_LONG).show();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            isGot = true;
        }
    }
}
