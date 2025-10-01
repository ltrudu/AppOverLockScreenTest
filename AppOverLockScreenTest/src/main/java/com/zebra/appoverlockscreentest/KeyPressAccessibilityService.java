package com.zebra.appoverlockscreentest;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class KeyPressAccessibilityService extends AccessibilityService {

    private static final String TAG = "KeyPressAccessibility";
    private static final int TARGET_KEY_CODE = 102;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // This method is called for accessibility events, but key events are handled in onKeyEvent
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "Service interrupted");
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            int keyCode = event.getKeyCode();
            Log.d(TAG, "Key pressed: " + KeyEvent.keyCodeToString(keyCode) + " (keyCode: " + keyCode + ")");

            if (keyCode == TARGET_KEY_CODE) {
                Log.d(TAG, "Target key code " + TARGET_KEY_CODE + " detected! Opening MainActivity");
                openMainActivity();
                return true;
            }
        }
        return super.onKeyEvent(event);
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "KeyPressAccessibilityService connected");
    }
}
