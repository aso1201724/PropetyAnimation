package com.example.propetyanimation;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	float degree = 45;
	float distance = -200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();


		Button target = (Button)findViewById(R.id.target);
		target.setOnClickListener(this);

		Button bt = (Button)findViewById(R.id.bt);
		bt.setOnClickListener(this);

		ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat( bt, "alpha", 0f, 0f );



		// AnimatorSetに渡すAnimatorのリストです
	    List<Animator> animatorList= new ArrayList<Animator>();

	    // alphaプロパティを0fから1fに変化させます
	    ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat( target, "alpha", 0f, 1f );
	    // 2秒かけて実行させます
	    alphaAnimator.setDuration( 2000 );
	    // リストに追加します
	    animatorList.add( alphaAnimator );

	    // 距離と半径から到達点となるX座標、Y座標を求めます
	    float toX = (float) ( distance * Math.cos( Math.toRadians( degree ) ) );
	    float toY = (float) ( distance * Math.sin( Math.toRadians( degree ) ) );

	    // translationXプロパティを0fからtoXに変化させます
	    PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat( "translationX", 0f, toX );
	    // translationYプロパティを0fからtoYに変化させます
	    PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat( "translationY", 0f, toY );
	    // rotationプロパティを0fから360に変化させます
	    PropertyValuesHolder holderRotaion = PropertyValuesHolder.ofFloat( "rotation", 0f, 360f );

	    // targetに対してholderX, holderY, holderRotationを同時に実行します
	    ObjectAnimator translationXYAnimator =
	            ObjectAnimator.ofPropertyValuesHolder( target, holderX, holderY, holderRotaion );
	    // 2秒かけて実行させます
	    translationXYAnimator.setDuration( 2000 );
	    // リストに追加します
	    animatorList.add( translationXYAnimator );

	    final AnimatorSet animatorSet = new AnimatorSet();
	    // リストのAnimatorを順番に実行します
	    animatorSet.playSequentially( animatorList );

	    // アニメーションを開始します
	    animatorSet.start();
	    objectAnimator2.start();
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.target:

			Button target = (Button)findViewById(R.id.target);
			Button bt = (Button)findViewById(R.id.bt);

			// rotationプロパティを0fから360fに変化させます
		    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat( target, "rotation", 0f, 360f );
		    PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat( "alpha", 0f, 1f );
		    PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat( "rotation", 0f, 360f );
		    ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(
		            bt, holderX, holderY );


		    // 3秒かけて実行させます
		    objectAnimator.setDuration( 3000 );
		    objectAnimator2.setDuration( 3000 );

		    // アニメーションを開始します
		    objectAnimator.start();
		    objectAnimator2.start();

			break;
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



}
