package com.example.viewmodellivedata;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<Integer> counter;

    public MutableLiveData<Integer> getCounter(){
        if(counter == null){
            counter = new MutableLiveData<>();
        }
        return counter;
    }

    public void startCounting(Integer i){
        TaskInverseCounting taskInverseCounting = new TaskInverseCounting();
        taskInverseCounting.execute(i);
    }

    private class TaskInverseCounting extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... count) {

            for(Integer i=count[0]; i>=0; i--){
                try {
                    System.out.println("Task integer = i"+ i );
                    publishProgress(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            counter.postValue(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }




}
