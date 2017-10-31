# LoadersPack
> Android Loaders            [![BuddyBuild](https://dashboard.buddybuild.com/api/statusImage?appID=5961de4056635b00014ecda7&branch=master&build=latest)](https://dashboard.buddybuild.com/apps/5961de4056635b00014ecda7/build/latest?branch=master)

> A replacement of default android material progressbar with loaders


### ClockLoader
![clockloader](https://user-images.githubusercontent.com/12999622/32249174-f36a9eb8-be7f-11e7-98aa-48d398b178f8.gif)



Other loaders: [LinearDotsLoader](https://github.com/agrawalsuneet/DotsLoader), [CircularDotsLoader](https://github.com/agrawalsuneet/DotsLoader), [LazyLoader](https://github.com/agrawalsuneet/DotsLoader), [TashieLoader](https://github.com/agrawalsuneet/DotsLoader), [FourFoldLoader](https://github.com/agrawalsuneet/FourFoldLoader), [ZipZapLoader](https://github.com/agrawalsuneet/FourFoldLoader)

## How To use
include below dependency in build.gradle of application and compile it
```
compile 'com.agrawalsuneet.androidlibs:loaderspack:0.1'
```

### ClockLoader
##### Through XML
```
<com.agrawalsuneet.loaderspack.loaders.ClockLoader
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:clock_animSpeedMultiplier="0.5"
        app:clock_bigCircleColor="@color/colorPrimary"
        app:clock_bigCircleRadius="80dp"
        app:clock_hourHandColor="@color/colorAccent"
        app:clock_hourHandLength="40dp"
        app:clock_innerCircleColor="@color/colorPrimaryDark"
        app:clock_innerCircleRadius="5dp"
        app:clock_minuteHandColor="@color/colorAccent"
        app:clock_minuteHandLength="60dp"
        app:clock_outerCircleBorderColor="@color/colorAccent"
        app:clock_outerCircleBorderWidth="10dp" />
```
##### Through Code
* Kotlin
```
        var clock = ClockLoader(this)
                .apply {
                    outerCircleBorderWidth = 8.0f
                    bigCircleRadius = 150.0f
                    minuteHandLength = 120.0f
                    hourHandLength = 80.0f
                    innerCircleRadius = 15.0f

                    outerCircleBorderColor = ContextCompat.getColor(context, R.color.colorAccent)
                    bigCircleColor = ContextCompat.getColor(context, R.color.colorPrimary)
                    innerCircleColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
                    hourHandColor = ContextCompat.getColor(context, R.color.colorAccent)
                    minuteHandColor = ContextCompat.getColor(context, R.color.colorAccent)

                    animSpeedMultiplier = 2.0f
                }


        containerLayout.addView(clock)
```

* Java
```
        ClockLoader clockLoader = new ClockLoader(this);
        clockLoader.setOuterCircleBorderWidth(8.0f);
        clockLoader.setBigCircleRadius(150.0f);
        clockLoader.setMinuteHandLength(120.0f);
        clockLoader.setHourHandLength(80.0f);
        clockLoader.setInnerCircleRadius(15.0f);
        
        clockLoader.setOuterCircleBorderColor(ContextCompat.getColor(this, R.color.colorAccent));
        clockLoader.setBigCircleColor(ContextCompat.getColor(this, R.color.colorPrimary));
        clockLoader.setInnerCircleColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        clockLoader.setHourHandColor(ContextCompat.getColor(this, R.color.colorAccent));
        clockLoader.setMinuteHandColor(ContextCompat.getColor(this, R.color.colorAccent));
        
        clockLoader.setAnimSpeedMultiplier(2.0f);
        
        container.addView(clockLoader);
```

Please take a 2 mins survey to make this library better [here](https://goo.gl/forms/v0SZS0oI9rvInzdB3).
It won't take more than 2 mins I promise :) or feel free to drop an email at agrawalsuneet@gmail.com if face any issue or require any additional functionality in it.
```
Copyright 2017 Suneet Agrawal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
