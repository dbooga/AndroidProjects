package com.daviancorp.csgospray;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.SystemClock;
import android.view.View;

public class GifWebView extends View {
	private static final String TAG = "GifWebView";

	private Movie mMovie = null;
	InputStream mStream = null;
	long mMoviestart = 0;

	public GifWebView(Context context, InputStream stream) {
		super(context);
		mStream = stream;
		mMovie = Movie.decodeStream(mStream);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint p = new Paint();
		p.setAntiAlias(true);

		final long now = SystemClock.uptimeMillis();

		if (mMoviestart == 0) {
			mMoviestart = now;
		}
		final int relTime = (int) ((now - mMoviestart) % mMovie.duration());

		mMovie.setTime(relTime);
		canvas.scale((float) this.getWidth() / (float) mMovie.width(),
				(float) this.getHeight() / (float) mMovie.height());
		mMovie.draw(canvas, 0, 0);
		this.invalidate();
	}
}