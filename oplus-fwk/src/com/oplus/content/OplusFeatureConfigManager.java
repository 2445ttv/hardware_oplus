/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.content;

import android.text.TextUtils;

import java.util.List;

public class OplusFeatureConfigManager {
    public static OplusFeatureConfigManager sInstance = null;

    public static OplusFeatureConfigManager getInstance() {
        if (sInstance == null) {
            sInstance = new OplusFeatureConfigManager();
        }
        return sInstance;
    }

    public boolean hasFeature(String name) {
        if ("oplus.software.vibrator_lmvibrator".equals(name)) {
            // OnePlus 7/8 series use old OnePlus base camera. Don't let this feature break haptic feedback.
            if (useOnePlusBaseCamera()) {
                return false;
            }
            return true;
        }
        if ("oplus.software.vibrator_luxunvibrator".equals(name)) {
            // COUI seekbar / number-picker haptics go through com.oplus.os.LinearmotorVibrator,
            // which oplus-fwk now backs with the AOSP vibrator. Enable so sliders feel stock.
            return true;
        }
        return false;
    }

    public interface OnFeatureObserver {
        default void onFeatureUpdate(List<String> features) {}
    }
}
