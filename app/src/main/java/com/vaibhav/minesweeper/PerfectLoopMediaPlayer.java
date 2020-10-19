package com.vaibhav.minesweeper;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;


public class PerfectLoopMediaPlayer {


    private static final String TAG = PerfectLoopMediaPlayer.class.getName();
    private Context mContext = null;
    private int mResId = 0;
    private String mPath = null;

    private static MediaPlayer mCurrentPlayer = null;
    private static MediaPlayer mNextPlayer = null;

    public static PerfectLoopMediaPlayer create(Context context, int resId) {
        return new PerfectLoopMediaPlayer(context, resId);
    }


    private PerfectLoopMediaPlayer(Context context, int resId) {
        mContext = context;
        mResId = resId;
        try {
            AssetFileDescriptor afd = context.getResources().openRawResourceFd(mResId);
            mCurrentPlayer = new MediaPlayer();
            mCurrentPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mCurrentPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mCurrentPlayer.start();
                }
            });
            mCurrentPlayer.prepareAsync();
            createNextMediaPlayerRaw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNextMediaPlayerRaw() {
        AssetFileDescriptor afd = mContext.getResources().openRawResourceFd(mResId);
        mNextPlayer = new MediaPlayer();
        try {
            mNextPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mNextPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mNextPlayer.seekTo(0);
                    mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
                    mCurrentPlayer.setOnCompletionListener(onCompletionListener);
                }
            });
            mNextPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static PerfectLoopMediaPlayer create(Context context, String path) {
        return new PerfectLoopMediaPlayer(context, path);
    }

    private PerfectLoopMediaPlayer(Context context, String path) {
        mContext = context;
        mPath = path;
        try {
            mCurrentPlayer.setDataSource(mPath);
            mCurrentPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mCurrentPlayer.start();
                }
            });
            mCurrentPlayer.prepareAsync();
            createNextMediaPlayerPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNextMediaPlayerPath() {
        mNextPlayer = new MediaPlayer();
        try {
            mNextPlayer.setDataSource(mPath);
            mNextPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mNextPlayer.seekTo(0);
                    mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
                    mCurrentPlayer.setOnCompletionListener(onCompletionListener);
                }
            });
            mNextPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private final MediaPlayer.OnCompletionListener onCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mCurrentPlayer = mNextPlayer;
                    createNextMediaPlayerRaw();
                    mediaPlayer.release();
                }
            };


    public boolean isPlaying() throws IllegalStateException {
        if (mCurrentPlayer != null) {
            return mCurrentPlayer.isPlaying();
        } else {
            return false;
        }
    }

    public void setVolume(float leftVolume, float rightVolume) {
        if (mCurrentPlayer != null) {
            mCurrentPlayer.setVolume(leftVolume, rightVolume);
        } else {
            Log.d(TAG, "setVolume()");
        }

    }

    public void start() throws IllegalStateException {
        if (mCurrentPlayer != null) {
            Log.d(TAG, "start()");
            mCurrentPlayer.start();
        } else {
            Log.d(TAG, "start() | mCurrentPlayer is NULL");
        }

    }

    public void stop() throws IllegalStateException {
        if (mCurrentPlayer != null && mCurrentPlayer.isPlaying()) {
            Log.d(TAG, "stop()");
            mCurrentPlayer.stop();
        } else {
            Log.d(TAG, "stop() | mCurrentPlayer " +
                    "is NULL or not playing");
        }

    }

    public void pause() throws IllegalStateException {
        if (mCurrentPlayer != null && mCurrentPlayer.isPlaying()) {
            Log.d(TAG, "pause()");
            mCurrentPlayer.pause();
        } else {
            Log.d(TAG, "pause() | mCurrentPlayer " +
                    "is NULL or not playing");
        }

    }

    public void setWakeMode(Context c, int mode) {
        if (mCurrentPlayer != null) {
            mCurrentPlayer.setWakeMode(c, mode);
            Log.d(TAG, "setWakeMode() | ");
        } else {
            Log.d(TAG, "setWakeMode() | " +
                    "mCurrentPlayer is NULL");
        }
    }

    public void setAudioStreamType(int audioStreamType) {
        if (mCurrentPlayer != null) {
            mCurrentPlayer.setAudioStreamType(audioStreamType);
        } else {
            Log.d(TAG, "setAudioStreamType() | " +
                    "mCurrentPlayer is NULL");
        }
    }

    public static void release() {
        Log.d(TAG, "release()");
        if (mCurrentPlayer != null)
            mCurrentPlayer.release();
        if (mNextPlayer != null)
            mNextPlayer.release();
    }

    public void reset() {
        if (mCurrentPlayer != null) {
            Log.d(TAG, "reset()");
            mCurrentPlayer.reset();
        } else {
            Log.d(TAG, "reset() | " +
                    "mCurrentPlayer is NULL");
        }

    }

}
