package eu.gotoinc.requesinjava_mvvm.util;

import com.squareup.picasso.Picasso;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

public class BindingAdapters {

	@BindingAdapter("bind:setImage")
	public static void setImage(AppCompatImageView iv, String src){
		Picasso.get().load(src).centerCrop().fit().into(iv);
	}
}
