/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.android.settings.widget;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;

import com.android.settings.TestConfig;
import com.android.settings.testutils.SettingsRobolectricTestRunner;
import com.android.settingslib.core.AbstractPreferenceController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

@RunWith(SettingsRobolectricTestRunner.class)
@Config(manifest = TestConfig.MANIFEST_PATH, sdk = TestConfig.SDK_VERSION)
public class WorkOnlyCategoryPreferenceControllerTest {

    private Context mContext;
    private WorkOnlyCategoryPreferenceController mController;
    private List<AbstractPreferenceController> mChildren;

    @Before
    public void setUp() {
        mContext = RuntimeEnvironment.application;
        mChildren = new ArrayList<>();
        mController = new WorkOnlyCategoryPreferenceController(mContext, "pref_key", mChildren);
    }

    @Test
    public void isAvailable_noChildren_true() {
        assertThat(mController.isAvailable()).isTrue();
    }

    @Test
    public void isAvailable_childrenAvailable_true() {
        final AbstractPreferenceController child = mock(AbstractPreferenceController.class);
        when(child.isAvailable()).thenReturn(true);
        mChildren.add(child);

        assertThat(mController.isAvailable()).isTrue();
    }

    @Test
    public void isAvailable_childrenUnavailable_false() {
        final AbstractPreferenceController child = mock(AbstractPreferenceController.class);
        when(child.isAvailable()).thenReturn(false);
        mChildren.add(child);

        assertThat(mController.isAvailable()).isFalse();
    }
}