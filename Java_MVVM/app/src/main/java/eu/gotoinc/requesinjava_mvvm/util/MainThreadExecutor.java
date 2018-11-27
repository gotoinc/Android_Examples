package eu.gotoinc.requesinjava_mvvm.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

import androidx.annotation.NonNull;

public class MainThreadExecutor implements Executor {
	private final Handler mHandler = new Handler(Looper.getMainLooper());

	@Override
	public void execute(@NonNull Runnable command) {
		mHandler.post(command);
	}
}