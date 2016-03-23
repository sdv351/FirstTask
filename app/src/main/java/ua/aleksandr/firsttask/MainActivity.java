/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Oleksandr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ua.aleksandr.firsttask;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text_view_create, text_view_registration, text_view_done_for, text_view_responsible;
    private TextView text_view_description, text_topic, text_progress;
    private Calendar dateAndTime = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener data_create = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime(text_view_create);
        }
    };
    DatePickerDialog.OnDateSetListener data_registered = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime(text_view_registration);
        }
    };
    DatePickerDialog.OnDateSetListener data_done_for = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime(text_view_done_for);
        }
    };
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        finder();
        setListener();
        setStartData();
        setRecycler();
    }

    private void setRecycler() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        String[] myDataset = getResources().getStringArray(R.array.picture);
        mAdapter = new MyAdapter(this, myDataset);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void setStartData() {
        setInitialDateTime(text_view_create);
        setInitialDateTime(text_view_registration);
        setInitialDateTime(text_view_done_for);
    }

    private void finder() {
        text_view_create = (TextView) findViewById(R.id.text_view_create_date);
        text_view_registration = (TextView) findViewById(R.id.text_view_registration_date);
        text_view_done_for = (TextView) findViewById(R.id.text_view_done_for_data);
        text_view_responsible = (TextView) findViewById(R.id.text_view_responsibility_date);
        text_view_description = (TextView) findViewById(R.id.text_view_description_data);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        text_topic = (TextView) findViewById(R.id.text_topic);
        text_progress = (TextView) findViewById(R.id.text_progress);
    }

    private void setListener() {
        text_view_create.setOnClickListener(this);
        text_view_registration.setOnClickListener(this);
        text_view_done_for.setOnClickListener(this);
        text_view_responsible.setOnClickListener(this);
        text_view_description.setOnClickListener(this);
        text_topic.setOnClickListener(this);
        text_progress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_registration_date: {
                setDate(text_view_registration, data_registered);
                Toast.makeText(this, "textView", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.text_view_done_for_data: {
                setDate(text_view_done_for, data_done_for);
                Toast.makeText(this, "textView", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.text_view_responsibility_date:
            case R.id.text_topic:
            case R.id.text_progress:
            case R.id.text_view_description_data: {
                Toast.makeText(this, "textView", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.text_view_create_date: {
                setDate(text_view_create, data_create);
                Toast.makeText(this, "textView", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    private void setDate(View v, DatePickerDialog.OnDateSetListener onDateSetListener) {
        new DatePickerDialog(MainActivity.this, onDateSetListener,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void setInitialDateTime(TextView textView) {
        textView.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
        ));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
