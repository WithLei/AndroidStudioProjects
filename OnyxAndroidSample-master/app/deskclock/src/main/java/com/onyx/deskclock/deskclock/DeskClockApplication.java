/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onyx.deskclock.deskclock;

import android.app.Application;

import com.onyx.deskclock.deskclock.data.DataModel;
import com.onyx.deskclock.deskclock.events.Events;
import com.onyx.deskclock.deskclock.events.LogEventTracker;

public class DeskClockApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DataModel.getDataModel().setContext(getApplicationContext());

        Events.addEventTracker(new LogEventTracker(getApplicationContext()));
    }
}
