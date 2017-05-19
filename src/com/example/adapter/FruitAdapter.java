package com.example.adapter;

import java.util.List;

import com.example.activitytest.R;
import com.example.entity.Fruit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitAdapter extends ArrayAdapter<Fruit>{
	private int resourceId;
	public FruitAdapter (Context context, int textViewResourceId, List<Fruit> objects){
		super(context,textViewResourceId,objects);
		resourceId = textViewResourceId;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Fruit fruit = getItem(position);//��ȡ��ǰ���Fruitʵ��
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		ImageView fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
		TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
		fruitImage.setImageResource(fruit.getImageId());
		fruitName.setText(fruit.getName());
		return view;
		
	}

}
